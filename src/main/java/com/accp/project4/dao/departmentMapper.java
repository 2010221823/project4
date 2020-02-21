package com.accp.project4.dao;

import com.accp.project4.pojo.department;

public interface departmentMapper {
    int deleteByPrimaryKey(Integer departmentId);

    int insert(department record);

    int insertSelective(department record);

    department selectByPrimaryKey(Integer departmentId);

    int updateByPrimaryKeySelective(department record);

    int updateByPrimaryKey(department record);
}