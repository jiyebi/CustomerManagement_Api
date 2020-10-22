package de.springApp.guestBook.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customers")
@Getter @Setter
public class CustomerEntry {
	
	public static final String QUERY_GetCustomerById = "Customer.GetById";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerID;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@JsonFormat(pattern = "dd.mm.yyyy")
	private Date birthdate;
	
	private String city;
	
	private String state;
	
	private int poBox;
	
	private String country;
	
	private Long tel;

}
