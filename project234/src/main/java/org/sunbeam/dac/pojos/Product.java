package org.sunbeam.dac.pojos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Product {
	private Integer p_id;
	private String p_name, p_desc;
	private float p_price;
	private byte[] p_image;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private LocalDate p_date;
	private float p_weight;
	private int stock;
	private double ratings;
	@JsonIgnore
	private Category category;

	public Product() {

	}

	public Product(String p_name, String p_desc, float p_price,float p_weight, int stock,
			double ratings, Category category) {
		super();
		this.p_name = p_name;
		this.p_desc = p_desc;
		this.p_price = p_price;
		this.p_date=LocalDate.now();
		this.p_weight = p_weight;
		this.stock = stock;
		this.ratings = ratings;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	@Column(length = 50)
	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	@Column(length = 50)
	public String getP_desc() {
		return p_desc;
	}

	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}

	public float getP_price() {
		return p_price;
	}

	public void setP_price(float p_price) {
		this.p_price = p_price;
	}

	@Lob
	public byte[] getP_image() {
		return p_image;
	}

	public void setP_image(byte[] p_image) {
		this.p_image = p_image;
	}

	
	public LocalDate getP_date() {
		return p_date;
	}

	public void setP_date(LocalDate p_date) {
		this.p_date = p_date;
	}

	public float getP_weight() {
		return p_weight;
	}

	public void setP_weight(float p_weight) {
		this.p_weight = p_weight;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	@Enumerated(EnumType.STRING)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [p_id=" + p_id + ", p_name=" + p_name + ", p_desc=" + p_desc + ", p_price=" + p_price
				+ ", p_image=" + Arrays.toString(p_image) + ", p_date=" + p_date + ", p_weight=" + p_weight + ", stock="
				+ stock + ", ratings=" + ratings + ", category=" + category + "]";
	}

}
