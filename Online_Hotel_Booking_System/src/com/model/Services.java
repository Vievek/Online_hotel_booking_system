package com.model;

public class Services {
	int services_id;
	String name;
	String description;
	String price;
	int m_id;
	
	public Services(int services_id, String name, String description, String price, int m_id) {
		
		this.services_id = services_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.m_id = m_id;
	}

	public int getServices_id() {
		return services_id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getPrice() {
		return price;
	}

	public int getM_id() {
		return m_id;
	}
	
	
	
	
}
