package de.springApp.guestBook.services;

import com.sun.javafx.tools.packager.Log;
import de.springApp.guestBook.entities.CustomerEntry;
import de.springApp.guestBook.repository.CustomerRepository;
import lombok.extern.log4j.Log4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
//@Log4j
public class CustomerServices {

    private CustomerRepository customerRepository;
     Logger log = new Logger();

    public CustomerServices(){
        this.customerRepository = customerRepository;
    }

    /*
     * getAllCustomers: this method calls the findAll method
     *                 of CustomerRepository in order to get
     *                 the list of all customers.
     *
     * @Exception: throws a RuntimeException if the customerRepository
     *             is empty.
     *
     * @Return: returns a list of Customers .
     * */
    public List<CustomerEntry> getAllCustomers(){
        if (customerRepository.findAll() == null){
        throw new RuntimeException("the Customer list is empty!");
        }
        Log.debug("successfully returned the list of customers.");
        return customerRepository.findAll();
    }

    /*
    * getCustomerById: this method calls the findById Method of the
    *                  CustomerRepository to get a customer with a
    *                  given Id.
    *
    * @Exception: throws a Runtime Exception when no customer is found
    *             with the given id.
    *
    * @Param: customer_id ==> the given customer Id to be search for.
    *
    * @Return: returns an Optional of Type Customer.
    * */
    public Optional getCustomerById(Long customer_id){
        if (customerRepository.findById(customer_id) == null){
        throw new RuntimeException("there is no customer with the given Id.");
        }
        Log.debug("successfully returned the customer with the id: " + customer_id);
        return customerRepository.findById(customer_id);
    }

    /*
     * getCustomerByLastName: this method calls the findByLastName Method of the
     *                  CustomerRepository to get a customer(s) with a
     *                  given lastName.
     *
     * @Exception: throws a Runtime Exception when no customer is found
     *             with the given lastName.
     *
     * @Param: customerLastName ==> the given customer lastName to be search for.
     *
     * @Return: returns an Optional of Type CustomerEntry.
     * */
    public Optional<CustomerEntry> getCustomerByLastName(String CustomerLastName){
        if (customerRepository.findByLastName(CustomerLastName) == null){
            throw new RuntimeException("there is no customer with the given lastName.");
        }
        Log.debug("successfully returned the customer(s) with the lastName: " + CustomerLastName);
        return customerRepository.findByLastName(CustomerLastName);
    }

    /*
    * createCustomer: this method calls the save method of the customerRepository
    *                 to save a new customer.
    *
    * @Param: Customer to be created.
    *
    * @Return: returns the created customer.
    * */
    public CustomerEntry createCustomer(CustomerEntry customerToBeCreated){
       customerRepository.save(customerToBeCreated);
       Log.debug("the customer with the customer_id: " + customerToBeCreated.getCustomerID() + "was successfully created.");
       return customerToBeCreated;
    }

    /*
    * updateCustomer: this method will first of all check if there is a customer existing
    *                 with the given id at all. In case there is a customer with the given id,
    *                 the existing customer will be attributed the properties of
    *                 updatedCustomer and will be saved with the save method of the
    *                 customerRepository.
    *
    * @Param: customer_id ==> id of the customer which will be updated.
    *         updatedCustomer ==> customer with the updated properties.
    *
    * @Return: returns the updated Customer.
    * */
    public CustomerEntry updateCustomer(Long customer_id, CustomerEntry updatedCustomer) {
        if (customerRepository.findById(customer_id).isPresent()) {
            CustomerEntry existingCustomer = customerRepository.findById(customer_id).get();

            existingCustomer.setFirstName(updatedCustomer.getFirstName());
            existingCustomer.setLastName(updatedCustomer.getLastName());
            existingCustomer.setBirthdate(updatedCustomer.getBirthdate());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setCity(updatedCustomer.getCity());
            existingCustomer.setPoBox(updatedCustomer.getPoBox());
            existingCustomer.setCountry(updatedCustomer.getCountry());
            existingCustomer.setState(updatedCustomer.getState());
            existingCustomer.setTel(updatedCustomer.getTel());

            Log.debug("successfully updated the customer with the id: " + customer_id);
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    /*
    * deleteCustomer: this method searches for the customer with the given id
    *                 and if the customer is found then it will be deleted.
    *
    * Exception: throws a Runtime Exception when no customer is found with the
    *            given id
    *
    * @Param: takes the customer_id of the customer to be deleted as a parameter.
    * */
    public void deleteCustomer(Long customer_id){
        if (customerRepository.findById(customer_id) != null ) {
            Log.debug("successfully deleted the customer with id: " + customer_id);
            customerRepository.deleteById(customer_id);
        }
    }
}
