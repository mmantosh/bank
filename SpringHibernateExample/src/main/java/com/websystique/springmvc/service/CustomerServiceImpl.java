package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.CustomerDao;
import com.websystique.springmvc.model.Customer;
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
@Autowired
CustomerDao dao;
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public void saveCustomer(Customer customer) {
		dao.saveCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		Customer entity=dao.findById(customer.getId());
		if(entity !=null){
			entity.setName(customer.getName());
			entity.setCity(customer.getCity());
			entity.setStreet(customer.getStreet());
			entity.setSsn(customer.getSsn());
		}

	}

	public void deleteCustomerBySsn(String ssn) {
		dao.deleteCustomerBySsn(ssn);

	}

	public List<Customer> findAllCustomers() {
		return dao.findAllCustomers();
	}

	public Customer findCustomerBySsn(String ssn) {
		return dao.findCustomerBySsn(ssn);
	}

	public boolean isCustomerSsnUnique(Integer id, String ssn) {
		Customer customer=findCustomerBySsn(ssn);
		return (customer==null||((id !=null) &&(customer.getId()==id)));
	}

}
