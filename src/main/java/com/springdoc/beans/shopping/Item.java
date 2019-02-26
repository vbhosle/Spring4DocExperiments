package com.springdoc.beans.shopping;

public class Item {

	private static long nextId = 0;
	private long id;
	private String name;
	private String category;
	private double price;

	public Item(String name, String category, double price) {
		this.id = nextId++;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public Item() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Item)) return false;
		return this.id == ((Item)obj).getId();
	}
	
	@Override
	public String toString() {
		return "[ Item id = " + this.id + ", name=" + this.name + ", category = " + this.category + ", price=" + this.price +" ]";
	}

}
