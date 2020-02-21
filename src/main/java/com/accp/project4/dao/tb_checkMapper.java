package com.accp.project4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.project4.pojo.tb_check;

public interface tb_checkMapper {
	/**
	 * 新增请假审查
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(@Param("record") tb_check record);

	/**
	 * 查询报销记录表
	 * 
	 * @param bizId
	 * @return
	 */
	List<tb_check> selectByPrimaryKey(@Param("bizId") Integer bizId);

	int deleteByPrimaryKey(Integer checkId);

	int insert(tb_check record);

	int updateByPrimaryKeySelective(tb_check record);

	int updateByPrimaryKey(tb_check record);
}