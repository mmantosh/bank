package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Customer;

public interface CustomerService {
   
	Customer findById(int id);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomerBySsn(String ssn);

	List<Customer> findAllCustomers(); 
	
	Customer findCustomerBySsn(String ssn);

	boolean isCustomerSsnUnique(Integer id, String ssn);
	


}
