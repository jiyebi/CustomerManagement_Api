package de.springApp.guestBook.entities;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Getter @Setter
public class CustomerEntry {
	
	public static final String QUERY_GetCustomerById = "Customer.GetById";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerID;

	@Column(length = 15)
	@NonNull
	private String firstName;

	@Column(length = 15)
	@NonNull
	private String lastName;

	@Column
	@NonNull
	private String email;
	
	@JsonFormat(pattern = "dd.mm.yyyy")
	@NonNull
	private Date birthdate;

	@Column(length = 20)
	private String city;

	@Column(length = 20)
	private String state;

	@Column(length = 8)
	private int poBox;

	@Column(length = 20)
	private String country;

	@Column(length = 15)
	@NonNull
	private Long tel;

}
