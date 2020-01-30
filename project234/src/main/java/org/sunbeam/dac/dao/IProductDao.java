package org.sunbeam.dac.dao;

import java.util.List;

import org.sunbeam.dac.pojos.OrderDetails;
import org.sunbeam.dac.pojos.Orders;
import org.sunbeam.dac.pojos.Product;

public interface IProductDao 
{
 List<Product> getAllProducts();
 List<Product> getProductBybat();
 List<Product> getProductByball();
 List<Product> getProductBygloves();
 List<Product> getProductByclothing();
 List<Product> getProductByshoes();
 Product addProduct(Product p);
 String deleteProd(int id);
Product getProductByid(int pid);
Product editproduct(Product p);
List<OrderDetails> getAllOrders();

 
}
