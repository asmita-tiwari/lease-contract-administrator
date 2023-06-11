package com.allane.leasecontractadministrator.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * Customer class represents the Customer details.
 */
@Data
@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long customerId;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "last_name")
  String lastName;

  @Column(name = "birth_date")
  LocalDate birthDate;

  @Column(name = "created_at")
  OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(name = "last_updated_at")
  OffsetDateTime lastUpdatedAt = OffsetDateTime.now();
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<LeasingContract> leasingContracts = new ArrayList<>();

  public String getFullName() {
    return firstName + " " + lastName;
  }
}
