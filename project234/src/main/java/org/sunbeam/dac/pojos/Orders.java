package org.sunbeam.dac.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orders {
	private Integer id;
	private float amount;
	private Users usr;
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "IST")
	private LocalDate order_date;
	@JsonIgnore
	private List<OrderDetails> or = new ArrayList<>();
    public Orders() {

	}

	public Orders(float amount, LocalDate order_date) {
		super();
		this.amount = amount;
		this.order_date = order_date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	public Integer getId() {
		return id;
	}

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	public List<OrderDetails> getOr() {
		return or;
	}

	public void setOr(List<OrderDetails> or) {
		this.or = or;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name = "uid")
	public Users getUsr() {
		return usr;
	}

	public void setUsr(Users usr) {
		this.usr = usr;
	}

	
	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}
	public void setOrderdtls(OrderDetails od)
	{
       this.or.add(od);
       od.setOrder(this);
       
	}
	public void removeOrderdtls(OrderDetails od)
	{
		   this.or.remove(od);
	       od.setOrder(null);	
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", amount=" + amount + ", usr=" + usr + ", order_date=" + order_date + "]";
	}

}

