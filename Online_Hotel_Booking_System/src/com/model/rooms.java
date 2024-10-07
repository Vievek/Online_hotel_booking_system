package com.model;

public class rooms {
	 private int roomId;
    private String roomType;
    private String description;
    private int noOfPerson;
    private double price;
    private String availabilityStatus;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String ac_type;
    
	public rooms(int roomId, String roomType, String description, int noOfPerson, double price,
			String availabilityStatus, String img1, String img2, String img3, String img4, String ac_type) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.description = description;
		this.noOfPerson = noOfPerson;
		this.price = price;
		this.availabilityStatus = availabilityStatus;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.ac_type = ac_type;
	}

	public int getRoomId() {
		return roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getDescription() {
		return description;
	}

	public int getNoOfPerson() {
		return noOfPerson;
	}

	public double getPrice() {
		return price;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public String getImg1() {
		return img1;
	}

	public String getImg2() {
		return img2;
	}

	public String getImg3() {
		return img3;
	}

	public String getImg4() {
		return img4;
	}

	public String getAc_type() {
		return ac_type;
	}
    
	
	

    
    
}
