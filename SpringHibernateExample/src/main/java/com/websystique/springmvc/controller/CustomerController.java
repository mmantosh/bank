package com.websystique.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.Customer;
import com.websystique.springmvc.service.CustomerService;
@Controller
@RequestMapping(value={"/customer"})
public class CustomerController {
	@Autowired
	CustomerService service;
	
	@Autowired
	MessageSource messageSource;

	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listCustomers(ModelMap model) {

		List<Customer> customers = service.findAllCustomers();
		model.addAttribute("customers", customers);
		return "allcustomers";
	}

	
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newCustomer(ModelMap model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		model.addAttribute("edit", false);
		return "customerRegistration";
	}

	
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveCustomer(@Valid Customer customer, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "customerRegistration";
		}

		
		if(!service.isCustomerSsnUnique(customer.getId(), customer.getSsn())){
			FieldError ssnError =new FieldError("customer","ssn",messageSource.getMessage("non.unique.ssn", new String[]{customer.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "customerRegistration";
		}
		
		service.saveCustomer(customer);

		model.addAttribute("success", "Customer " + customer.getName() + " registered successfully");
		return "customerSuccess";
	}


	
	@RequestMapping(value = { "/edit-{ssn}-customer" }, method = RequestMethod.GET)
	public String editCustomer(@PathVariable String ssn, ModelMap model) {
		Customer customer = service.findCustomerBySsn(ssn);
		model.addAttribute("customer", customer);
		model.addAttribute("edit", true);
		return "customerRegistration";
	}
	
	
	@RequestMapping(value = { "/edit-{ssn}-customer" }, method = RequestMethod.POST)
	public String updateCustomer(@Valid Customer customer, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "customerRegistration";
		}

		if(!service.isCustomerSsnUnique(customer.getId(), customer.getSsn())){
			FieldError ssnError =new FieldError("customer","ssn",messageSource.getMessage("non.unique.ssn", new String[]{customer.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "customerRegistration";
		}

		service.updateCustomer(customer);

		model.addAttribute("success", "Customer " + customer.getName()	+ " updated successfully");
		return "customerSuccess";
	}

	
	
	@RequestMapping(value = { "/delete-{ssn}-customer" }, method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable String ssn) {
		service.deleteCustomerBySsn(ssn);
		return "redirect:/customer/list";
	}

}



