package com.allane.leasecontractadministrator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * ContractResponse class represents a response of lease contract.
 * It contains the necessary data related to stored lease contract.
 */
@Data
public class ContractResponse {

  private Long contractNo;


  private BigDecimal monthlyRate;

  @JsonProperty("customer")
  private CustomerResponse customerResponse;

  @JsonProperty("vehicle")
  private VehicleResponse vehicleResponse;

}
