package org.sunbeam.dac.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunbeam.dac.dao.IProductDao;
import org.sunbeam.dac.dao.IUserDao;
import org.sunbeam.dac.pojos.OrderDetails;
import org.sunbeam.dac.pojos.Orders;
import org.sunbeam.dac.pojos.Product;
import org.sunbeam.dac.pojos.UserRole;
import org.sunbeam.dac.pojos.Users;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserDao dao;
	@Autowired
	private IProductDao dao2;
	@GetMapping("/{id}")
	public ResponseEntity<?> orderlist(@PathVariable int id){
		Users user = dao.getUserOrderDetails(id);
		
		if(user==null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Users>(user,HttpStatus.OK);
	}
	@GetMapping("")
	public ResponseEntity<?> getallUser(){
		  List<Users> allusers = dao.getAllusers();
		
		if(allusers==null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Users>>(allusers,HttpStatus.OK);
	}
	@GetMapping("/val/{email}")
	public ResponseEntity<?> validateuser(@PathVariable String email){
		 int id = dao.getId(email);
		  Users u = dao.getuserbyid(id);
		if(u==null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Users>(u,HttpStatus.OK);
	}
	@GetMapping("/getid/{email}")
	public ResponseEntity<?> getuserid(@PathVariable String email){
		 int id = dao.getId(email);
		  System.out.println(id);
		if(id==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(id,HttpStatus.OK);
	}
	@GetMapping("/cart/{email}")
	public ResponseEntity<?> showCart(@PathVariable String email){
		 int id = dao.getId(email);
		  List<Orders> or = dao.getcartDetails(id);
		if(or==null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Orders>>(or,HttpStatus.OK);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<?> checkUser(@RequestBody Users u)
	{
		  Users user = dao.checkUser(u.getEmail(),u.getPassword());
		  user.setAddr(null);
		  user.setOrdr(null);
		  
		  try {
				if (user != null)
					return new ResponseEntity<UserRole>(user.getRole(), HttpStatus.OK);
				
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Inside Catch Block");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
	}
	
	
	@PostMapping("/getuser")
	public ResponseEntity<?> getuser(@RequestBody Users u)
	{
		  Users user = dao.checkUser(u.getEmail(),u.getPassword());
		  user.setAddr(null);
		  user.setOrdr(null);
		  
		  try {
				if (user != null)
					return new ResponseEntity<Users>(user, HttpStatus.OK);
				
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Inside Catch Block");
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Users u)
	{
		System.out.println(u);
		System.out.println(u.getAddr().getCountry());
		
		try {
			return new ResponseEntity<Users>(dao.registerUser(u), HttpStatus.CREATED);
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody Users u)
	{
		System.out.println(u);
		
		try {
			return new ResponseEntity<Void>(dao.updateUser(u), HttpStatus.CREATED);
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PostMapping("/addtocart/{uid}/{pid}")
	public ResponseEntity<?> updateUser(@RequestBody int quantity,@PathVariable int uid,@PathVariable int pid)
	{
		System.out.println("in addtocart");
		
		System.out.println(quantity);
		Product p=dao2.getProductByid(pid);
		float price = p.getP_price();
		Users u=dao.getuserbyid(uid);
		float am=price*quantity;
		OrderDetails od=new OrderDetails(quantity,price);
		od.setProd(p);
		Orders order=new Orders(am,LocalDate.now());
		order.setUsr(u);
		order.setOrderdtls(od);
		try {
			return new ResponseEntity<Void>(dao.addOrders(order), HttpStatus.CREATED);
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
