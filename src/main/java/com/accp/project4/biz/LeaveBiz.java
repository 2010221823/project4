package com.accp.project4.biz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.accp.project4.dao.tb_leaveMapper;
import com.accp.project4.pojo.tb_leave;
import com.accp.project4.vo.LeaveVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("LeaveBiz")
public class LeaveBiz {

	@Resource
	private tb_leaveMapper dao;

	/**
	 * 新增请假
	 * 
	 * @param leave
	 * @return
	 */
	public int addLeave(tb_leave leave) {
		return dao.insertSelective(leave);
	}

	/**
	 * 根据不同职位查看不同的人
	 * 
	 * @param departmentId
	 * @param positionId
	 * @param employeeId
	 * @return
	 */
	public PageInfo<LeaveVo> findLeaveBy(Integer departmentId, Integer positionId, Integer employeeId, Integer pageNum,
			String startTime, String endTime) {
		PageHelper.startPage(pageNum, 5);
		if (positionId == 3) {//部门经理
			PageInfo<LeaveVo> info = new PageInfo<LeaveVo>(
					dao.selectByPrimaryKey(departmentId, null, null, startTime, endTime));
			return info;
		} else if (positionId == 2||positionId==5||positionId==6) {//普通员工，财务，出纳
			PageInfo<LeaveVo> info = new PageInfo<LeaveVo>(
					dao.selectByPrimaryKey(null, null, employeeId, startTime, endTime));
			return info;
		} else if (positionId == 1) {//总经理
			PageInfo<LeaveVo> info = new PageInfo<LeaveVo>(
					dao.selectByPrimaryKey(null, positionId, null, startTime, endTime));
			return info;
		} else if (positionId == 4) {//人事
			PageInfo<LeaveVo> info = new PageInfo<LeaveVo>(
					dao.selectByPrimaryKey(null, null, null, startTime, endTime));
			return info;
		}
		return null;
	}

	/**
	 * 查询请假详情
	 * 
	 * @param leaveId
	 * @return
	 */
	public LeaveVo findByleaveId(Integer leaveId) {
		return dao.queryByPrimaryKey(leaveId);
	}

	/**
	 * 修改请假状态
	 * 
	 * @param leave
	 * @return
	 */
	public int modifyLeave(tb_leave leave) {
		return dao.updateByPrimaryKeySelective(leave);
	}
}
