package com.gdg.androidtraining;

public class Student {

	private int id;
	private String name, image, phone, address;
	

	public Student(int id, String name, String image, String phone, String address) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
