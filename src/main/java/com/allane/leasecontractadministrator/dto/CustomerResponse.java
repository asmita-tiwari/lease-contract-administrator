package com.allane.leasecontractadministrator.dto;

import java.time.LocalDate;
import lombok.Data;

/**
 * CustomerResponse class represents a response for a customer details.
 */
@Data
public class CustomerResponse {

  Long customerId;
  String firstName;
  String lastName;
  LocalDate birthDate;
}
