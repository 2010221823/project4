package com.accp.project4.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.project4.pojo.tb_reimburse;
import com.accp.project4.vo.ReimburseVo;

public interface tb_reimburseMapper {
	/**
	 * 新增报销和报销详细
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(tb_reimburse record);

	/**
	 * 根据职位查看报销
	 * 
	 * @param reimburseId
	 * @return
	 */
	List<ReimburseVo> selectByPrimaryKey(@Param("createMan") Integer createMan, @Param("status") Integer status,
			@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("positionId") Integer positionId, @Param("departmentId") Integer departmentId,
			@Param("type") Integer type,@Param("count")Integer count);

	/**
	 * 查看具体报销
	 * 
	 * @param reimburseId
	 * @return
	 */
	ReimburseVo queryByPrimaryKey(@Param("reimburseId") Integer reimburseId);

	/**
	 * 修改报销表
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(@Param("record") tb_reimburse record);

	/**
	 * 主详删除报销表
	 * 
	 * @param reimburseId
	 * @return
	 */
	int deleteByPrimaryKey(@Param("reimburseId") Integer reimburseId);

	int insert(tb_reimburse record);

	int updateByPrimaryKey(tb_reimburse record);
}