package de.springApp.guestBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.springApp.guestBook.entities.CustomerEntry;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntry, Long>{
    Optional<CustomerEntry> findByLastName(String lastName);
}