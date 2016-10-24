package com.gmail.imccannascii;

import java.util.ArrayList;

public class Address {

	private ArrayList <String> links;
	private String address;
	
	public Address(String address){
		this.address = address; 
		links = new ArrayList <String>();
	}
	
	public void addLinkToArrayList(String link){
		this.links.add(link);
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public ArrayList <String> getLinks(){
		return this.links;
	}
	
}
