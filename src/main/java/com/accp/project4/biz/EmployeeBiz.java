package com.accp.project4.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.accp.project4.dao.employeeMapper;
import com.accp.project4.pojo.employee;
import com.accp.project4.vo.EmployeeVo;

@Service("EmployeeBiz")
public class EmployeeBiz {
	@Resource
	private employeeMapper dao;

	/**
	 * 员工登录
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 */
	public EmployeeVo findLogin(Integer id, String pwd) {
		return dao.selectByPrimaryKey(id, pwd);
	}

	/**
	 * 查询上级
	 * 
	 * @param positionId
	 * @param departmentId
	 * @return
	 */
	public employee findByPositionId(Integer positionId, Integer departmentId) {
		if (positionId == 2) {
			return dao.queryByPositionId(3, departmentId);
		} else if (positionId>2) {
			return dao.queryByPositionId(1, 4);
		}
		return null;
	}

}
