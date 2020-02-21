package com.accp.project4.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.accp.project4.dao.reimburse_detailMapper;
import com.accp.project4.pojo.reimburse_detail;

@Service("RdetailBIz")
public class RdetailBIz {

	@Resource
	private reimburse_detailMapper dao;

	/**
	 * 报销详表新增
	 * 
	 * @param detail
	 * @return
	 */
	public int addRdetail(reimburse_detail detail) {
		return dao.insertSelective(detail);
	}

	/**
	 * 查询具体报销详表
	 * 
	 * @param mainId
	 * @return
	 */
	public List<reimburse_detail> findByMainId(Integer mainId) {
		return dao.selectByPrimaryKey(mainId);
	}

	/**
	 * 根据报销id删除
	 * 
	 * @param MainId
	 * @return
	 */
	public int removeByMainId(Integer MainId) {
		return dao.deleteByPrimaryKey(MainId);
	}
}
