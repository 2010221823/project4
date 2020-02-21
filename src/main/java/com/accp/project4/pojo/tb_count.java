package com.accp.project4.pojo;

public class tb_count {
	private Integer countId;

	private Float money;

	private Integer year;

	private Integer month;

	private Integer departmentId;

	private String departmentName;

	private String employeeName;

	private Integer employeeId;

	public tb_count() {
		// TODO Auto-generated constructor stub
		super();
	}

	public tb_count(Integer countId, Float money, Integer year, Integer month, Integer departmentId,
			Integer employeeId) {
		super();
		this.countId = countId;
		this.money = money;
		this.year = year;
		this.month = month;
		this.departmentId = departmentId;
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getCountId() {
		return countId;
	}

	public void setCountId(Integer countId) {
		this.countId = countId;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

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
}