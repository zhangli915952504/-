package com.zhangli.db;

public class Person {
	private String name;
	private String number;
	private int id;

	public Person(String name, String number, int id) {
		this.name = name;
		this.number = number;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", number=" + number + ", id=" + id + "]";
	}

	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}