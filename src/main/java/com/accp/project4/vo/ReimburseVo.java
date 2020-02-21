package com.accp.project4.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.accp.project4.pojo.reimburse_detail;
import com.accp.project4.pojo.tb_check;

public class ReimburseVo {
	private Integer reimburseId;

	private Integer createMan;

	private Date createTime;

	private Integer nextDealMan;

	private Float totalCount;

	private Integer statusId;

	private String statusName;

	private Integer employeeId;

	private String employeeName;

	private String nextDealMans;

	private String event;

	private Integer departmentId;

	private String departmentName;

	private String positionName;

	List<tb_check> rlist = new ArrayList<tb_check>(0);

	List<reimburse_detail> dlist = new ArrayList<reimburse_detail>(0);

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public List<tb_check> getRlist() {
		return rlist;
	}

	public void setRlist(List<tb_check> rlist) {
		this.rlist = rlist;
	}

	public List<reimburse_detail> getDlist() {
		return dlist;
	}

	public void setDlist(List<reimburse_detail> dlist) {
		this.dlist = dlist;
	}

	public Integer getReimburseId() {
		return reimburseId;
	}

	public void setReimburseId(Integer reimburseId) {
		this.reimburseId = reimburseId;
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

	public Integer getNextDealMan() {
		return nextDealMan;
	}

	public void setNextDealMan(Integer nextDealMan) {
		this.nextDealMan = nextDealMan;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getNextDealMans() {
		return nextDealMans;
	}

	public void setNextDealMans(String nextDealMans) {
		this.nextDealMans = nextDealMans;
	}
}
