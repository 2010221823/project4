package com.accp.project4.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.accp.project4.dao.tb_countMapper;
import com.accp.project4.pojo.tb_count;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("CountBiz")
public class CountBiz {

	@Resource
	private tb_countMapper dao;

	/**
	 * 出纳打钱后新增统计表
	 * 
	 * @param count
	 * @return
	 */
	public int addCount(tb_count count) {
		return dao.insertSelective(count);
	}

	/**
	 * 月度统计
	 * 
	 * @param pageNum
	 * @param year
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	public PageInfo<tb_count> findByPage(Integer pageNum, String year, String startMonth, String endMonth,
			Integer positionId, Integer departmentId) {
		PageHelper.startPage(pageNum, 10);
		if (positionId != 3) {// 总经理，财务
			return new PageInfo<tb_count>(dao.selectByPrimaryKey(year, startMonth, endMonth, null));
		} else {// 部门经理
			return new PageInfo<tb_count>(dao.selectByPrimaryKey(year, startMonth, endMonth, departmentId));
		}
	}

	/**
	 * 年度统计
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public List<tb_count> findByYear(Integer startYear, Integer endYear, Integer positionId, Integer departmentId) {
		if (positionId != 3) {// 总经理，财务
			return dao.selectByYear(startYear, endYear, null);
		} else {// 部门经理
			return dao.selectByYear(startYear, endYear, departmentId);
		}
	}

	/**
	 * 年·月度详情
	 * 
	 * @param year
	 * @param month
	 * @param positionId
	 * @param departmentId
	 * @return
	 */
	public List<tb_count> findCountDetails(Integer year, Integer month, Integer positionId, Integer departmentId,
			Integer type) {
		if (type == 1) {
			if (positionId == 3) {
				return dao.queryByPrimaryKey(year, month, null, departmentId);
			} else if (positionId == 1 || positionId == 5) {
				return dao.queryByPrimaryKey(year, month, positionId, null);
			}
		} else if (type == 2) {
			if (positionId == 3) {
				return dao.queryByPrimaryKey(year, null, null, departmentId);
			} else if (positionId == 1 || positionId == 5) {
				return dao.queryByPrimaryKey(year, null, positionId, null);
			}
		}
		return null;
	}
}
