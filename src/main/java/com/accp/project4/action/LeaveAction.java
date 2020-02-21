package com.accp.project4.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.project4.biz.CheckBiz;
import com.accp.project4.biz.EmployeeBiz;
import com.accp.project4.biz.LeaveBiz;
import com.accp.project4.pojo.tb_check;
import com.accp.project4.pojo.tb_leave;
import com.accp.project4.vo.LeaveVo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/c/leave")
public class LeaveAction {
	@Resource
	private LeaveBiz biz;
	@Resource
	private CheckBiz cbiz;
	@Resource
	private EmployeeBiz ebiz;

	/**
	 * 新增请假表
	 * 
	 * @return
	 */
	@RequestMapping("insertLeave")
	public String insertleave(tb_leave leave, Integer positionId, Integer departmentId) {
		leave.setNextDealMan(ebiz.findByPositionId(positionId, departmentId).getEmployeeId());
		leave.setCreateTime(new Date());
		leave.setStatusId(2);
		biz.addLeave(leave);
		return "qjList";
	}

	/**
	 * 根据不同职位查看请假
	 * 
	 * @param departmentId
	 * @param positionId
	 * @param employeeId
	 * @param pageNum
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@PostMapping("qjlists")
	@ResponseBody
	public PageInfo<LeaveVo> queryByPage(Integer departmentId, Integer positionId, Integer employeeId, Integer pageNum,
			String startTime, String endTime) {
		if (startTime == "" && endTime == "") {
			return biz.findLeaveBy(departmentId, positionId, employeeId, pageNum, null, null);
		} else {
			return biz.findLeaveBy(departmentId, positionId, employeeId, pageNum, startTime, endTime);
		}
	}

	/**
	 * 查看请假详情
	 * 
	 * @param model
	 * @param leaveId
	 * @param type
	 * @return
	 */
	@GetMapping("qjxq")
	public String queryByleaveId(Model model, Integer leaveId, Integer type) {
		model.addAttribute("list", biz.findByleaveId(leaveId));
		model.addAttribute("type", type);
		return "qjxq";
	}

	/**
	 * 请假审批
	 * 
	 * @param check
	 * @return
	 */
	@PostMapping("updateLeave")
	@ResponseBody
	public String updateLeave(tb_check check, Integer positionId) {
		if (positionId == 3||positionId==1) {//部门经理和总经理
			if (check.getCheckResult() == 1) {
				tb_leave leave = new tb_leave(check.getBizId(), null, null, null, null, null, null, 1004, null, null,
						3);
				int i = biz.modifyLeave(leave);
				if (i > 0) {
					check.setTypeId(1);
					check.setCheckTime(new Date());
					cbiz.addCheck(check);
				}
			} else if (check.getCheckResult() == 3) {
				tb_leave leave = new tb_leave(check.getBizId(), null, null, null, null, null, null, 10000, null, null,
						4);
				int i = biz.modifyLeave(leave);
				if (i > 0) {
					check.setTypeId(1);
					check.setCheckTime(new Date());
					cbiz.addCheck(check);
				}
			}
		} else if (positionId == 4) {//人事存档
			tb_leave leave = new tb_leave(check.getBizId(), null, null, null, null, null, null, 10000, null, null, 6);
			int i = biz.modifyLeave(leave);
			if (i > 0) {
				check.setTypeId(1);
				check.setCheckTime(new Date());
				cbiz.addCheck(check);
			}
		}
		return "ok";
	}
}
