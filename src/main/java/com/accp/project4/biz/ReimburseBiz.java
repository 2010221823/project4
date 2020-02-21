package com.accp.project4.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.accp.project4.dao.tb_reimburseMapper;
import com.accp.project4.pojo.tb_reimburse;
import com.accp.project4.vo.ReimburseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("ReimburseBiz")
public class ReimburseBiz {

	@Resource
	private tb_reimburseMapper dao;

	/**
	 * 新增报销和报销详情表
	 * 
	 * @param reimburse
	 * @return
	 */
	public int addReimburse(tb_reimburse reimburse) {
		return dao.insertSelective(reimburse);
	}

	/**
	 * 根据不同职位查询报销
	 * 
	 * @param pageNum
	 * @param employeeId
	 * @param type
	 * @param status
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public PageInfo<ReimburseVo> findByPage(Integer pageNum, Integer employeeId, Integer type, Integer status,
			String startTime, String endTime, Integer positionId, Integer departmentId) {
		PageHelper.startPage(pageNum, 10);
		if (type == 1) {
			return new PageInfo<ReimburseVo>(
					dao.selectByPrimaryKey(employeeId, status, startTime, endTime, null, null, null,null));
		} else if (type == 2) {// 部门经理
			if (positionId == 3) {
				return new PageInfo<ReimburseVo>(
						dao.selectByPrimaryKey(null, status, startTime, endTime, 2, departmentId, null,null));
			} else if (positionId == 1) {
				return new PageInfo<ReimburseVo>(
						dao.selectByPrimaryKey(null, status, startTime, endTime, 3, null, null,1));
			} else if (positionId == 5 || positionId == 6) {
				return new PageInfo<ReimburseVo>(
						dao.selectByPrimaryKey(null, status, startTime, endTime, null, null, employeeId,null));
			}
		}
		return null;
	}

	/**
	 * 查询具体报销
	 * 
	 * @param reimburseId
	 * @return
	 */
	public ReimburseVo findByReimburseId(Integer reimburseId) {
		return dao.queryByPrimaryKey(reimburseId);
	}

	/**
	 * 修改报销
	 * 
	 * @param reimburse
	 * @return
	 */
	public int modifyByReimburseId(tb_reimburse reimburse) {
		return dao.updateByPrimaryKeySelective(reimburse);
	}

	/**
	 * 根据报销id删除
	 * 
	 * @param reimburseId
	 * @return
	 */
	public int removeByReimburseId(Integer reimburseId) {
		return dao.deleteByPrimaryKey(reimburseId);
	}
}
