package com.accp.project4.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class tb_reimburse {
	private Integer reimburseId;

	private Integer typeId;

	private Integer createMan;

	private Date createTime;

	private Integer departmentId;

	private Integer nextDealMan;

	private String event;

	private Float totalCount;

	private Integer statusId;

	private Integer positionId;

	private tb_check check;

	public tb_check getCheck() {
		return check;
	}

	public void setCheck(tb_check check) {
		this.check = check;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	private List<reimburse_detail> list = new ArrayList<reimburse_detail>(0);

	public tb_reimburse() {
		// TODO Auto-generated constructor stub
		super();
	}

	public tb_reimburse(Integer reimburseId, Integer typeId, Integer createMan, Date createTime, Integer departmentId,
			Integer nextDealMan, String event, Float totalCount, Integer statusId, List<reimburse_detail> list) {
		super();
		this.reimburseId = reimburseId;
		this.typeId = typeId;
		this.createMan = createMan;
		this.createTime = createTime;
		this.departmentId = departmentId;
		this.nextDealMan = nextDealMan;
		this.event = event;
		this.totalCount = totalCount;
		this.statusId = statusId;
		this.list = list;
	}

	public List<reimburse_detail> getList() {
		return list;
	}

	public void setList(List<reimburse_detail> list) {
		this.list = list;
	}

	public Integer getReimburseId() {
		return reimburseId;
	}

	public void setReimburseId(Integer reimburseId) {
		this.reimburseId = reimburseId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getCreateMan() {
		return createMan;
	}

	public void setCreateMan(Integer createMan) {
		this.createMan = createMan;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getNextDealMan() {
		return nextDealMan;
	}

	public void setNextDealMan(Integer nextDealMan) {
		this.nextDealMan = nextDealMan;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event == null ? null : event.trim();
	}

	public Float getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Float totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
}