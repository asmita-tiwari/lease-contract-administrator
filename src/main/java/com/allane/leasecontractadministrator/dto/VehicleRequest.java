package com.allane.leasecontractadministrator.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VehicleRequest class represents a request for storing a vehicle details.
 */
@Data
@NoArgsConstructor
public class VehicleRequest {

  private Long vehicleId;

  @NotEmpty
  private String brand;

  @NotEmpty
  private String model;

  @NotNull
  private int year;

  private String vin;

  @NotNull
  private BigDecimal price;

}
