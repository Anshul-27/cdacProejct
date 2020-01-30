package org.sunbeam.dac.pojos;

import javax.persistence.*;

@Embeddable
public class Address {

	private String city, state, country;
	private int pincode;
	

	public Address() {

	}

	public Address(String city, String state, String country, int pincode) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
		
		this.pincode = pincode;
	}

	@Column(length = 20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(length = 20)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(length = 20)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(length = 7)
	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	
	@Override
	public String toString() {
		return "Address [id=" + ", city=" + city + ", state=" + state + ", country=" + country + ", cellNo="
				+ ", pincode=" + pincode + ", user="  + "]";
	}

}
