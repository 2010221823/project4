package com.accp.project4.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.accp.project4.dao.tb_checkMapper;
import com.accp.project4.pojo.tb_check;

@Service("CheckBiz")
public class CheckBiz {
	@Resource
	private tb_checkMapper dao;

	/**
	 * 新增请假审查
	 * 
	 * @param check
	 * @return
	 */
	public int addCheck(tb_check check) {
		return dao.insertSelective(check);
	}

	/**
	 * 查询报销记录表
	 * 
	 * @param BizId
	 * @return
	 */
	public List<tb_check> findByBizId(Integer BizId) {
		return dao.selectByPrimaryKey(BizId);
	}
}
