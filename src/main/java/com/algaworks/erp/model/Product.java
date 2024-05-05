package com.algaworks.erp.model;

public class Product {

	private String pname;
	private int quantity;
	private float pricePerPeice;

	public Product(String pname, int quantity, float pricePerPeice) {
		this.pname = pname;
		this.quantity = quantity;
		this.pricePerPeice = pricePerPeice;
	}

	public String getPname() {
		return pname;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getPricePerPeice() {
		return pricePerPeice;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPricePerPeice(float pricePerPeice) {
		this.pricePerPeice = pricePerPeice;
	}

}
