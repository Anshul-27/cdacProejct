package org.sunbeam.dac.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sunbeam.dac.pojos.Category;
import org.sunbeam.dac.pojos.OrderDetails;

import org.sunbeam.dac.pojos.Product;
@Repository
@Transactional
public class ProductDaoImpl implements IProductDao {
	@Autowired
	private EntityManager mgr;
	@Override
	public List<Product> getAllProducts() {
		String jpql ="select p from Product p";
		return mgr.unwrap(Session.class).createQuery(jpql, Product.class).getResultList();
		
	}
	@Override
	public List<Product> getProductBybat() {
		
		String jpql="select p from Product p where p.category=:cat";
		return mgr.unwrap(Session.class).createQuery(jpql, Product.class).setParameter("cat",Category.BAT).getResultList();
	}
	@Override
	public List<Product> getProductByball() {

		String jpql="select p from Product p where p.category=:cat";
		return mgr.unwrap(Session.class).createQuery(jpql, Product.class).setParameter("cat",Category.BALL).getResultList();
	}
	@Override
	public List<Product> getProductBygloves() {

		String jpql="select p from Product p where p.category=:cat";
		return mgr.unwrap(Session.class).createQuery(jpql, Product.class).setParameter("cat",Category.GLOVES).getResultList();
	}
	@Override
	public List<Product> getProductByclothing() {

		String jpql="select p from Product p where p.category=:cat";
		return mgr.unwrap(Session.class).createQuery(jpql, Product.class).setParameter("cat",Category.CLOTHING).getResultList();
	}
	@Override
	public List<Product> getProductByshoes() {

		String jpql="select p from Product p where p.category=:cat";
		return mgr.unwrap(Session.class).createQuery(jpql, Product.class).setParameter("cat",Category.SHOES).getResultList();
	}
	@Override
	public Product addProduct(Product p) {
		mgr.unwrap(Session.class).persist(p);
		
		return p;
	}
	@Override
	public String deleteProd(int id) {
		Product p = mgr.unwrap(Session.class).get(Product.class,id);
		mgr.unwrap(Session.class).delete(p);
		return "deleted";
	}
	@Override
	public Product getProductByid(int pid) {
	
		return mgr.unwrap(Session.class).get(Product.class,pid);
	}
	@Override
	public Product editproduct(Product p) {
		mgr.unwrap(Session.class).update(p);
		return p;
	}
	@Override
	public List<OrderDetails> getAllOrders() {
		String jpql="select o from OrderDetails o left outer join fetch o.order";
		return mgr.unwrap(Session.class).createQuery(jpql, OrderDetails.class).getResultList();
	}

}
