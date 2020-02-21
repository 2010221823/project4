package com.accp.project4.dao;

import com.accp.project4.pojo.position;

public interface positionMapper {
	/**
	 * 查询所担任职位
	 * @param positionId
	 * @return
	 */
	position selectByPrimaryKey(Integer positionId);

	int deleteByPrimaryKey(Integer positionId);

	int insert(position record);

	int insertSelective(position record);

	int updateByPrimaryKeySelective(position record);

	int updateByPrimaryKey(position record);
}