package com.allane.leasecontractadministrator.repository;

import com.allane.leasecontractadministrator.domain.Customer;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository is responsible to handle the database operations for Customer Entity.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


  Optional<Customer> findByFirstNameAndLastNameAndBirthDate(String firstName, String lastName,
                                                            LocalDate birthDate);
}
