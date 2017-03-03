package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Customer;
@Repository("CustomerDao")
public class CustomerDaoImpl extends AbstractDao<Integer,Customer> implements CustomerDao {

	public Customer findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	public void saveCustomer(Customer customer) {
persist(customer);
	}

	public void deleteCustomerBySsn(String ssn) {
		Query query=getSession().createSQLQuery("delete from Customer where ssn=:ssn");
		query.setString("ssn", ssn);
		query.executeUpdate();
	}
@SuppressWarnings("unchecked")
	public List<Customer> findAllCustomers() {
		Criteria criteria=createEntityCriteria();
		return (List<Customer>) criteria.list() ;
	}

	public Customer findCustomerBySsn(String ssn) {
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("ssn",ssn));
		return (Customer)criteria.uniqueResult();
	}

}
