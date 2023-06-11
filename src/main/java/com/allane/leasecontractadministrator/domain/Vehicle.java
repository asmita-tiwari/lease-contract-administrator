package com.allane.leasecontractadministrator.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Vehicle class represents the customized Vehicle details for leasing.
 */
@Data
@Entity
@Table(name = "vehicle")
@EqualsAndHashCode(of = {"brand", "model", "year"})
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long vehicleId;

  @Column(name = "brand")
  String brand;

  @Column(name = "model")
  String model;

  @Column(name = "year")
  int year;
  @Column(name = "vin")
  String vin;
  @Column(name = "price")
  BigDecimal price;
  @Column(name = "created_at")
  OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(name = "last_updated_at")
  OffsetDateTime lastUpdatedAt = OffsetDateTime.now();

  @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
  private LeasingContract leasingContracts;

}
