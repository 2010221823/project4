package com.accp.project4.action;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accp.project4.biz.CheckBiz;
import com.accp.project4.biz.CountBiz;
import com.accp.project4.biz.EmployeeBiz;
import com.accp.project4.biz.RdetailBIz;
import com.accp.project4.biz.ReimburseBiz;
import com.accp.project4.pojo.reimburse_detail;
import com.accp.project4.pojo.tb_count;
import com.accp.project4.pojo.tb_reimburse;
import com.accp.project4.vo.EmployeeVo;
import com.accp.project4.vo.ReimburseVo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api/reimburses")
public class ReimburseAction {
	@Resource
	private ReimburseBiz biz;
	@Resource
	private EmployeeBiz ebiz;
	@Resource
	private RdetailBIz rbiz;
	@Resource
	private CheckBiz cbiz;
	@Resource
	private CountBiz countbiz;

	/**
	 * 报销表主详新增
	 * 
	 * @param reimburse
	 * @return
	 */
	@PostMapping("/reimburse")
	public String insertreimburse(@RequestBody tb_reimburse reimburse) {
		reimburse.setNextDealMan(
				ebiz.findByPositionId(reimburse.getPositionId(), reimburse.getDepartmentId()).getEmployeeId());
		reimburse.setCreateTime(new Date());
		int i = biz.addReimburse(reimburse);
		if (i > 0) {
			for (reimburse_detail detail : reimburse.getList()) {
				detail.setMainId(reimburse.getReimburseId());
				rbiz.addRdetail(detail);
			}
		}
		return "ok";
	}

	/**
	 * 根据不同职位查询报销
	 * 
	 * @param session
	 * @param type
	 * @param pageNum
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@GetMapping("/reimburse/{EmployeeId}/{type}/{pageNum}/{status}/{startTime}/{endTime}/{positionId}/{departmentId}")
	public PageInfo<ReimburseVo> queryListByPage(@PathVariable Integer EmployeeId, @PathVariable Integer type,
			@PathVariable Integer pageNum, @PathVariable String status, @PathVariable String startTime,
			@PathVariable String endTime, @PathVariable Integer positionId, @PathVariable Integer departmentId) {
		if (("null").equals(startTime) && ("null").equals(endTime) && ("null").equals(status)) {
			return biz.findByPage(pageNum, EmployeeId, type, null, null, null, positionId, departmentId);
		} else {
			return biz.findByPage(pageNum, EmployeeId, type, ("null").equals(status) ? null : Integer.parseInt(status),
					startTime, endTime, positionId, departmentId);
		}
	}

	/**
	 * 查询具体报销详情
	 * 
	 * @param reimburseId
	 * @param type
	 * @return
	 */
	@GetMapping("/reimburse/{reimburseId}")
	public ReimburseVo queryByReimburseIds(@PathVariable Integer reimburseId) {
		ReimburseVo r = biz.findByReimburseId(reimburseId);
		if (r != null) {
			r.setDlist(rbiz.findByMainId(r.getReimburseId()));
			r.setRlist(cbiz.findByBizId(r.getReimburseId()));
		}
		return r;
	}

	/**
	 * 修改报销表
	 * 
	 * @param reimburse
	 * @return
	 */
	@PutMapping("/reimburse")
	public String updateByReimburseId(@RequestBody tb_reimburse reimburse) {
		if (reimburse.getStatusId() != 11) {// 没有点击审批通过按钮
			if (reimburse.getStatusId() > 2) {// 如果状态不是提交，新创建就要新增记录表
				if (reimburse.getStatusId() == 4) {// 点拒绝按钮
					reimburse.setNextDealMan(10000);
					reimburse.getCheck().setCheckResult(3);
				} else if (reimburse.getStatusId() == 7) {// 点了打回按钮
					reimburse.setNextDealMan(reimburse.getCreateMan());
					reimburse.getCheck().setCheckResult(2);
				}
				reimburse.getCheck().setBizId(reimburse.getReimburseId());
				reimburse.getCheck().setTypeId(2);
				reimburse.getCheck().setCheckTime(new Date());
				cbiz.addCheck(reimburse.getCheck());
			} else if (reimburse.getStatusId() == 1) {// 没提交时修改
				int j = rbiz.removeByMainId(reimburse.getReimburseId());
				if (j > 0) {
					for (reimburse_detail item : reimburse.getList()) {
						item.setMainId(reimburse.getReimburseId());
						rbiz.addRdetail(item);
					}
				}
			} else if (reimburse.getStatusId() == 2) {
				if (reimburse.getPositionId() != null) {
					// 打回后再一次提交
					reimburse.setNextDealMan(ebiz
							.findByPositionId(reimburse.getPositionId(), reimburse.getDepartmentId()).getEmployeeId());
					int j = rbiz.removeByMainId(reimburse.getReimburseId());
					if (j > 0) {
						for (reimburse_detail item : reimburse.getList()) {
							item.setMainId(reimburse.getReimburseId());
							rbiz.addRdetail(item);
						}
					}
				}
			}
			biz.modifyByReimburseId(reimburse);
		} else {// 点击审批通过按钮
			if (reimburse.getPositionId() == 3) {
				// 部门经理修改报销状态为审核中
				reimburse.setStatusId(5);
				if (reimburse.getTotalCount() > 5000) {// 大于五千交由总经理再一次审批
					reimburse.setNextDealMan(1001);
				} else {
					reimburse.setNextDealMan(1002);
				}
			} else if (reimburse.getPositionId() == 1) {
				// 总经理修改报销状态为审核中
				reimburse.setStatusId(5);
				reimburse.setNextDealMan(1002);
			} else if (reimburse.getPositionId() == 5) {
				// 财务修改报销状态为已审批
				reimburse.setNextDealMan(1003);
				reimburse.setStatusId(3);
			} else if (reimburse.getPositionId() == 6) {
				// 出纳修改报销状态为已打钱
				reimburse.setNextDealMan(10000);
				reimburse.setStatusId(8);
				// 打钱后就新增统计表
				tb_count cc = new tb_count(null, reimburse.getTotalCount(), 1900 + new Date().getYear(),
						new Date().getMonth() + 1, reimburse.getDepartmentId(), reimburse.getCreateMan());
				countbiz.addCount(cc);
			}
			biz.modifyByReimburseId(reimburse);
			reimburse.getCheck().setCheckResult(1);
			reimburse.getCheck().setBizId(reimburse.getReimburseId());
			reimburse.getCheck().setTypeId(2);
			reimburse.getCheck().setCheckTime(new Date());
			cbiz.addCheck(reimburse.getCheck());
		}
		return "ok";
	}

	/**
	 * 根据报销id主详删除
	 * 
	 * @param reimburseId
	 * @return
	 */
	@DeleteMapping("/reimburse/{reimburseId}")
	public String deleteByReimburseId(@PathVariable Integer reimburseId) {
		biz.removeByReimburseId(reimburseId);
		return "ok";
	}
}
