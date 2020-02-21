package com.accp.project4.dao;

import com.accp.project4.pojo.tb_status;

public interface tb_statusMapper {
    int deleteByPrimaryKey(Integer statusId);

    int insert(tb_status record);

    int insertSelective(tb_status record);

    tb_status selectByPrimaryKey(Integer statusId);

    int updateByPrimaryKeySelective(tb_status record);

    int updateByPrimaryKey(tb_status record);
}