package com.accp.project4.pojo;

public class reimburse_detail {
	private Integer id;

	private Integer mainId;

	private Float subtotal;

	private String des;

	private String pictrueName;

	private String pictruePath;

	public reimburse_detail() {
		// TODO Auto-generated constructor stub
		super();
	}

	public reimburse_detail(Integer id, Integer mainId, Float subtotal, String des, String pictrueName,
			String pictruePath) {
		super();
		this.id = id;
		this.mainId = mainId;
		this.subtotal = subtotal;
		this.des = des;
		this.pictrueName = pictrueName;
		this.pictruePath = pictruePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getPictrueName() {
		return pictrueName;
	}

	public void setPictrueName(String pictrueName) {
		this.pictrueName = pictrueName == null ? null : pictrueName.trim();
	}

	public String getPictruePath() {
		return pictruePath;
	}

	public void setPictruePath(String pictruePath) {
		this.pictruePath = pictruePath == null ? null : pictruePath.trim();
	}
}