package com.allane.leasecontractadministrator.repository;

import com.allane.leasecontractadministrator.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * VehicleRepository is responsible to handle the database operations for Vehicle Entity.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
