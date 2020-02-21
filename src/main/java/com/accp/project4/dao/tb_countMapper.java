package com.accp.project4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.project4.pojo.tb_count;

public interface tb_countMapper {
	/**
	 * 出纳打钱新增统计表
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(@Param("record") tb_count record);

	/**
	 * 查询月度总结
	 * 
	 * @param year
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<tb_count> selectByPrimaryKey(@Param("year") String year, @Param("startMonth") String startMonth,
			@Param("endMonth") String endMonth, @Param("departmentId") Integer departmentId);

	/**
	 * 年度统计
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	List<tb_count> selectByYear(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear,
			@Param("departmentId") Integer departmentId);

	/**
	 * 查询月度详情
	 * 
	 * @return
	 */
	List<tb_count> queryByPrimaryKey(@Param("year") Integer year, @Param("month") Integer month,
			@Param("positionId") Integer positionId, @Param("departmentId") Integer departmentId);

	int deleteByPrimaryKey(Integer countId);

	int insert(tb_count record);

	int updateByPrimaryKeySelective(tb_count record);

	int updateByPrimaryKey(tb_count record);
}