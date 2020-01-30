package org.sunbeam.dac.controller;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sunbeam.dac.dao.IProductDao;
import org.sunbeam.dac.pojos.Category;
import org.sunbeam.dac.pojos.OrderDetails;
import org.sunbeam.dac.pojos.Orders;
import org.sunbeam.dac.pojos.Product;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductDao dao;
	@GetMapping("/prod")
	public ResponseEntity<?> listProducts(){
		List<Product> products = dao.getAllProducts();
		if(products.size()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	@GetMapping("/getsp/{pid}")
	public ResponseEntity<?> getproduct(@PathVariable int pid){
		Product products = dao.getProductByid(pid);
		if(products==null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Product>(products,HttpStatus.OK);
		
	}
	@PostMapping("/add")
	public ResponseEntity<?> addProducts(@RequestParam String p_name,@RequestParam float p_weight
			,@RequestParam String p_desc,@RequestParam float  p_price,@RequestParam int stock,@RequestParam String category,
			@RequestParam double ratings,@RequestParam(value = "p_image", required = false) MultipartFile p_image)
	{
		
		try 
		{	
		Product p =new Product(p_name,p_desc,p_price,p_weight,stock,
				ratings,Category.valueOf(category));
		p.setP_date(LocalDate.now());
		if(p_image!=null)
		{
			p.setP_image(p_image.getBytes());
		}
		dao.addProduct(p);
		return new ResponseEntity<Void>( HttpStatus.CREATED);
	} catch(Exception e1) {
		e1.printStackTrace();// only for debugging
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	}
	
	
	@PutMapping("/update/{pid}")
	public ResponseEntity<?> editProducts(@RequestParam String p_name,@RequestParam float p_weight
			,@RequestParam String p_desc,@RequestParam float  p_price,@RequestParam int stock,@RequestParam String category,
			@RequestParam double ratings,@RequestParam(value = "p_image", required = false) MultipartFile p_image,@PathVariable int pid)
	{
		System.out.println("beforecteory");
		Product p = dao.getProductByid(pid);
		p.setCategory(Category.valueOf(Category.class,category));
		System.out.println("aftercat");
		p.setP_date(LocalDate.now());
		p.setP_desc(p_desc);
		p.setP_name(p_name);
		p.setP_price(p_price);
		p.setP_weight(p_weight);
		p.setRatings(ratings);
		p.setStock(stock);
		try {
		if(p_image!=null)
		{
			p.setP_image(p_image.getBytes());
		}
		System.out.println(p);
		dao.editproduct(p);
		return new ResponseEntity<Void>( HttpStatus.CREATED);
	} catch(Exception e1) {
		e1.printStackTrace();// only for debugging
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	}
	
	
	@GetMapping("/bat")
	public ResponseEntity<?> listOfBat(){
		
		List<Product> products = dao.getProductBybat();
		if(products.size()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	@GetMapping("/ball")
	public ResponseEntity<?> listOfBall(){
		List<Product> products = dao.getProductByball();
		if(products.size()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	@GetMapping("/gloves")
	public ResponseEntity<?> listOfgloves(){
		List<Product> products = dao.getProductBygloves();
		if(products.size()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	@GetMapping("/shoes")
	public ResponseEntity<?> listOfshoes(){
		List<Product> products = dao.getProductByshoes();
		if(products.size()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	@GetMapping("/clothing")
	public ResponseEntity<?> listOfclothing(){
		List<Product> products = dao.getProductByclothing();
		if(products.size()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		String status = dao.deleteProd(id);
		if(status==null)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	@GetMapping("/orderdtls")
	public ResponseEntity<?> listOforders(){
		List<OrderDetails> ordr = dao.getAllOrders();
		if(ordr.size()==0)
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<OrderDetails>>(ordr,HttpStatus.OK);
		
	}
	
	
}
