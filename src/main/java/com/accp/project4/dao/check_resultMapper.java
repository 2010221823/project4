package com.accp.project4.dao;

import com.accp.project4.pojo.check_result;

public interface check_resultMapper {
    int deleteByPrimaryKey(Integer resultId);

    int insert(check_result record);

    int insertSelective(check_result record);

    check_result selectByPrimaryKey(Integer resultId);

    int updateByPrimaryKeySelective(check_result record);

    int updateByPrimaryKey(check_result record);
}