package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Customer;

public interface CustomerDao {
	Customer findById(int id);

	void saveCustomer(Customer customer);
	
	void deleteCustomerBySsn(String ssn);
	
	List<Customer> findAllCustomers();

	Customer findCustomerBySsn(String ssn);


}
