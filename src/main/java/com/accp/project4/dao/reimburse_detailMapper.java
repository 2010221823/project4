package com.accp.project4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.project4.pojo.reimburse_detail;

public interface reimburse_detailMapper {
	/**
	 * 新增详表
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(@Param("record") reimburse_detail record);

	/**
	 * 查询具体报销详情
	 * 
	 * @param mainId
	 * @return
	 */
	List<reimburse_detail> selectByPrimaryKey(@Param("mainId")Integer mainId);
	/**
	 * 根据报销id删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(@Param("id")Integer id);

	int insert(reimburse_detail record);

	int updateByPrimaryKeySelective(reimburse_detail record);

	int updateByPrimaryKey(reimburse_detail record);
}