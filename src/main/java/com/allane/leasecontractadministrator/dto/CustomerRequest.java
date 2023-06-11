package com.allane.leasecontractadministrator.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * CustomerRequest class represents a request for storing a customer details.
 */
@Data
@NoArgsConstructor
public class CustomerRequest {

  private Long customerId;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NonNull
  private LocalDate birthDate;
}
