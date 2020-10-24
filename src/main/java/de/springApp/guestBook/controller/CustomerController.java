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
import org.springframework.web.bind.annotation.*;

import de.springApp.guestBook.entities.CustomerEntry;
import de.springApp.guestBook.repository.CustomerRepository;

@Controller
@RequestMapping("/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

	private final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerServices customerServices;

	public CustomerController(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}

	/*
    *
    * */
	@RequestMapping(value = "/",
		method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerEntry>> getAllCustomers(){
	        return new ResponseEntity<>(customerServices.getAllCustomers(),
			                    HttpStatus.OK);
	}

	
	/*
	*
	* */
	@RequestMapping(value = "{customer_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional> getCustomerWithId(@PathVariable Long customer_id){
	        return new ResponseEntity<Optional>(customerServices.getCustomerById(customer_id),HttpStatus.OK);
	}


    /*
    *
    * */
	@RequestMapping(value = "/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCustomer(@RequestBody CustomerEntry newCustomerEntry){
	       customerServices.createCustomer(newCustomerEntry);
		   return new ResponseEntity<Void>(HttpStatus.OK);
	}


	/*
	 *
	 * */
	@RequestMapping(value = "{customer_id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerEntry> updateCustomer(@PathVariable Long customer_id, @RequestBody CustomerEntry newCustomerEntry){
	       return new ResponseEntity<CustomerEntry>(customerServices
				      .updateCustomer(customer_id,newCustomerEntry),HttpStatus.OK);
	}


	/*
	*
	* */
	@RequestMapping(value = "{customer_id}",
			method = RequestMethod.DELETE
			)
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customer_id){
		customerServices.deleteCustomer(customer_id);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	}

}
