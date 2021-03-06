package com.pharmacy;

import java.util.HashMap;

/*
 * This class prepares individual drug record with setters & getters to access each record
 * */
public class Drug 
{
	String id;
	String name;
	Double cost;
	String pFirstName;
	String pLastName;
	int count;
	
	Drug(String id, String s, String firstName, String lastName,Double d){
		this.id = id;
		this.name=s;
		this.cost = d;
		this.pFirstName = firstName;
		this.pLastName = lastName;
	}
	
	Drug(String s, int count, Double d){
		this.name=s;
		this.cost = d;
		this.count = count;
	}
	
	
	public Drug() {
		super();
	}


	public String getId() {
		return id;
	}
	public String getpFirstName() {
		return pFirstName;
	}


	public void setpFirstName(String pFirstName) {
		this.pFirstName = pFirstName;
	}


	public String getpLastName() {
		return pLastName;
	}


	public void setpLastName(String pLastName) {
		this.pLastName = pLastName;
	}


	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public String getPrescriberFullName() {
		return this.pFirstName +" "+ this.pLastName;
	}
	
	public void setCount(int count) {
		this.count= count;
	}
	
	public int getCount() {
		return this.count;
	}
	
}
