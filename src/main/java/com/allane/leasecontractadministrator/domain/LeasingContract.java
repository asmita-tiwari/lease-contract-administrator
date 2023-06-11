package com.allane.leasecontractadministrator.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Contract class represents the Contract details for leasing.
 */
@Data
@Entity
@Table(name = "leasing_contract")
public class LeasingContract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long contractNo;

  @Column(name = "monthly_rate")
  BigDecimal monthlyRate;

  @Column(name = "created_at")
  OffsetDateTime createdAt = OffsetDateTime.now();

  @Column(name = "last_updated_at")
  OffsetDateTime lastUpdatedAt = OffsetDateTime.now();
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id_ref")
  private Customer customer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "vehicle_id_ref")
  private Vehicle vehicle;

}
