package com.allane.leasecontractadministrator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * ContractRequest class represents a request for lease contract.
 * It contains the necessary data for creating the contract.
 */
@Data
@NoArgsConstructor
public class ContractRequest {

  private Long contractNo;

  @NonNull
  @DecimalMin(value = "0.01", message = "Total cost must be greater than 0")
  private BigDecimal monthlyRate;


  @JsonProperty("customer")
  private CustomerRequest customerRequest;

  @JsonProperty("vehicle")
  private VehicleRequest vehicleRequest;

}
