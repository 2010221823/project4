package com.accp.project4.dao;

import org.apache.ibatis.annotations.Param;

import com.accp.project4.pojo.employee;
import com.accp.project4.vo.EmployeeVo;

public interface employeeMapper {
	/**
	 * 员工登录
	 * 
	 * @param employeeId
	 * @return
	 */
	EmployeeVo selectByPrimaryKey(@Param("employeeId") Integer employeeId, @Param("pwd") String pwd);

	/**
	 * 查询上级
	 * 
	 * @param positionId
	 * @param departmentId
	 * @return
	 */
	employee queryByPositionId(@Param("positionId") Integer positionId, @Param("departmentId") Integer departmentId);

	int deleteByPrimaryKey(Integer employeeId);

	int insert(employee record);

	int insertSelective(employee record);

	int updateByPrimaryKeySelective(employee record);

	int updateByPrimaryKey(employee record);
}