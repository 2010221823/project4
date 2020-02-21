package com.accp.project4.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.project4.pojo.tb_leave;
import com.accp.project4.vo.LeaveVo;

public interface tb_leaveMapper {
	/**
	 * 新增请假
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(@Param("record") tb_leave record);

	/**
	 * 按不同职位查询请假
	 * 
	 * @param leaveId
	 * @return
	 */
	List<LeaveVo> selectByPrimaryKey(@Param("departmentId") Integer departmentId,
			@Param("positionId") Integer positionId, @Param("employeeId") Integer employeeId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	/**
	 * 查询请假详情
	 * 
	 * @return
	 */
	LeaveVo queryByPrimaryKey(@Param("leaveId") Integer leaveId);

	/**
	 * 修改请假状态
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(@Param("record") tb_leave record);

	int deleteByPrimaryKey(Integer leaveId);

	int insert(tb_leave record);

	int updateByPrimaryKey(tb_leave record);
}