package de.springApp.guestBook.controller;

import java.util.List;
import java.util.Optional;

import de.springApp.guestBook.services.CustomerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.springApp.guestBook.entities.CustomerEntry;
import de.springApp.guestBook.repository.CustomerRepository;

@Controller
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

	private final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private CustomerServices customerServices;

	public CustomerController(CustomerServices customerServices){ this.customerServices = customerServices;}


    /*
    *
    * */
	@RequestMapping(value = "/",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCustomers(){
		return ResponseEntity.ok(customerServices.getAllCustomers());
	}

	
	/*
	*
	* */
	@RequestMapping(value = "/{customerID}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomerWithId(@PathVariable Long customerID){
		return ResponseEntity.ok(customerServices.getCustomerById(customerID));
	}


	/*
	 *
	 * */
	@RequestMapping(value = "/{customerLastName}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerWithLastName(@PathVariable String customerLastName){
		return ResponseEntity.ok(customerServices.getCustomerByLastName(customerLastName));
	}


    /*
    *
    * */
	@RequestMapping(value = "/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCustomer(@RequestBody CustomerEntry newCustomerEntry){
		return ResponseEntity.ok(customerServices.createCustomer(newCustomerEntry));
	}


	/*
	 *
	 * */
	@RequestMapping(value = "/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCustomer(@PathVariable Long customer_id, @RequestBody CustomerEntry newCustomerEntry){
		return ResponseEntity.ok(customerServices.updateCustomer(customer_id,newCustomerEntry));
	}


	/*
	*
	* */
	@RequestMapping(value = "/",
			method = RequestMethod.DELETE
			)
	public  ResponseEntity<Void> deleteCustomer(@PathVariable Long customerID){
		customerServices.deleteCustomer(customerID);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}

}
