 package org.sunbeam.dac.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Users {
	
	private Integer id;
	private String email;
	private String password;
	private String fName;
	private String lName;
	private String phoneNo;
	private UserRole role;

	private Address addr;
	@JsonIgnore
	private List<Orders> ordr=new ArrayList<>();
	
	public Users() {
		
	}

	public Users(String email, String password, String fName, String lName, String phoneNo) {
		super();
		this.email = email;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.phoneNo = phoneNo;
	}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(length = 30,nullable = false,unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 20,nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(length = 20,nullable = false)
	public String getfName() {
		return fName;
	}
    
	public void setfName(String fName) {
		this.fName = fName;
	}
	@Column(length = 20,nullable = false)
	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	@Column(length = 20,nullable = false)
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
    @Enumerated(EnumType.STRING)
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public void setOrder(Orders o)
	{
       this.ordr.add(o);
       o.setUsr(this);
       
	}
	public void removeOrderdtls(Orders o)
	{
		this.ordr.remove(o);
	       o.setUsr(null);	
	}
	
	@Embedded
	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}
	
	
	
    @OneToMany(mappedBy = "usr",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    public List<Orders> getOrdr() {
		return ordr;
	}

	public void setOrdr(List<Orders> ordr) {
		this.ordr = ordr;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", password=" + password + ", fName=" + fName + ", lName="
				+ lName + ", phoneNo=" + phoneNo + ", role=" + role + ", addr=" + addr + ", ordr=" + ordr + "]";
	}

	

		
	

}
