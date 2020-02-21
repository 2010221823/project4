package com.accp.project4.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class tb_leave {
	private Integer leaveId;

	private Integer typeId;

	private Integer createMan;

	private Integer departmentId;

	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;

	private Integer nextDealMan;

	private String event;

	private Integer totalCount;

	private Integer statusId;

	public tb_leave() {
		// TODO Auto-generated constructor stub
		super();
	}

	public tb_leave(Integer leaveId, Integer typeId, Integer createMan, Integer departmentId, Date createTime,
			Date startTime, Date endTime, Integer nextDealMan, String event, Integer totalCount, Integer statusId) {
		super();
		this.leaveId = leaveId;
		this.typeId = typeId;
		this.createMan = createMan;
		this.departmentId = departmentId;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.nextDealMan = nextDealMan;
		this.event = event;
		this.totalCount = totalCount;
		this.statusId = statusId;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
}