package com.allane.leasecontractadministrator.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * VehicleResponse class represents a response for a vehicle details.
 */
@Data
public class VehicleResponse {

  private Long vehicleId;
  private String brand;
  private String model;
  private int year;
  private String vin;
  private BigDecimal price;
}
