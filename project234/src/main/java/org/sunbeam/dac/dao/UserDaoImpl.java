package org.sunbeam.dac.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sunbeam.dac.pojos.OrderDetails;
import org.sunbeam.dac.pojos.Orders;
import org.sunbeam.dac.pojos.UserRole;
import org.sunbeam.dac.pojos.Users;


@Repository
@Transactional
public class UserDaoImpl implements IUserDao {

	@Autowired
	private EntityManager mgr;
	@Override
	public List<Users> getAllusers() {
		String jpql="select u from Users u";
		return mgr.unwrap(Session.class).createQuery(jpql, Users.class).getResultList();
	}
	@Override
	public Users checkUser(String email, String password) 
	{
		String jpql="select u from Users u where u.email=:em and u.password=:pwd";	
		Users u = mgr.unwrap(Session.class).createQuery(jpql, Users.class).setParameter("em", email).setParameter("pwd", password).getSingleResult();
		if(u==null)
		{
			System.out.println("No User Found");
			return null;
		}else
		{
			System.out.println(" User Found");
		return u;
		}
	}
	@Override
	public Users registerUser(Users u) {
		
		u.setRole(UserRole.CUSTOMER);
		System.out.println(u);
	
	
		mgr.unwrap(Session.class).save(u);
		
		return u;
	}
	@Override
	public Users getUserOrderDetails(int id) {
		String jpql="select u from Users u left outer join fetch u.ordr where u.id=:id";
		return mgr.unwrap(Session.class).createQuery(jpql, Users.class).setParameter("id", id).getSingleResult();
	}
	@Override
	public Users getrole(String email) {
		String jpql="select u from Users u where u.email=:em";
		System.out.println(email);
		Users u = mgr.unwrap(Session.class).createQuery(jpql, Users.class).setParameter("em", email).getSingleResult();
		if(u==null)
		{
			System.out.println("No User Found");
			return null;
		}else
		{
			System.out.println(" User Found");
		return u;
		}
	}
	
	@Override
	public int getId(String email) {
		String jpql="select u from Users u where u.email=:em";
		System.out.println(email);
		Users u = mgr.unwrap(Session.class).createQuery(jpql, Users.class).setParameter("em", email).getSingleResult();
		if(u==null)
		{
			System.out.println("No User Found");
			return 0;
		}else
		{
			System.out.println(" User Found");
		return u.getId();
		}
	}
	@Override
	public Users getuserbyid(int id) {
	return mgr.unwrap(Session.class).get(Users.class, id);
	}
	@Override
	public Void updateUser(Users user) {
		
		mgr.unwrap(Session.class).update(user);
		return null;
	}
	@Override
	public List<Orders> getcartDetails(int id) {
		String jpql="select o from Orders o left outer join fetch o.usr where o.usr.id=:id";
		
		return mgr.unwrap(Session.class).createQuery(jpql, Orders.class).setParameter("id",id ).getResultList();
	}
	@Override
	public Void addOrders(Orders order) {
		mgr.unwrap(Session.class).persist(order);
		return null;
	}

}
