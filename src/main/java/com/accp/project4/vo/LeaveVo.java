package com.accp.project4.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class LeaveVo {
	private Integer leaveId;

	private String employeeName;

	private Date createTime;

	private String nextDealMan;

	private Integer totalCount;

	private String statusName;

	private Date checkTime;

	private String checkComment;

	private String departmentName;
	
	private Integer nextDealMa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;

	private String event;

	private List<CheckVo> list = new ArrayList<CheckVo>(0);

	public Integer getNextDealMa() {
		return nextDealMa;
	}

	public void setNextDealMa(Integer nextDealMa) {
		this.nextDealMa = nextDealMa;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public List<CheckVo> getList() {
		return list;
	}

	public void setList(List<CheckVo> list) {
		this.list = list;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNextDealMan() {
		return nextDealMan;
	}

	public void setNextDealMan(String nextDealMan) {
		this.nextDealMan = nextDealMan;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckComment() {
		return checkComment;
	}

	public void setCheckComment(String checkComment) {
		this.checkComment = checkComment;
	}

}
