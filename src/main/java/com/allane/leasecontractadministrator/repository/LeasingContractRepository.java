package com.allane.leasecontractadministrator.repository;

import com.allane.leasecontractadministrator.domain.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LeasingContractRepository is responsible to handle the database operations for Contract Entity.
 */
@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {
}
