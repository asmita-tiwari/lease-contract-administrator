package com.allane.leasecontractadministrator.service;

import com.allane.leasecontractadministrator.domain.LeasingContract;
import com.allane.leasecontractadministrator.domain.Vehicle;
import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.exception.DataNotFoundException;
import com.allane.leasecontractadministrator.exception.DuplicateRecordException;
import com.allane.leasecontractadministrator.exception.InvalidRequestException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ContractValidator is responsible to perform the validation on contract details.
 */
public class ContractValidator {

  private static final Logger logger = LoggerFactory.getLogger(ContractValidator.class);

  /**
   * Validates that the contract number should not exist for a new contract request.
   *
   * @param contractRequest The contract request containing the contract number.
   * @throws InvalidRequestException If the contract number is not empty for a new contract request.
   */
  public static void validateContractRequest(ContractRequest contractRequest) {
    if (contractRequest.getContractNo() != 0) {
      logger.error("Contract Number should be empty for new contract request");
      throw new InvalidRequestException(
          "Contract Number should be empty for new contract request.");
    }
  }

  /**
   * Validate the Contract request before updating.
   *
   * @param contractRequest Contains the contract to update.
   * @param contract        Existing contract details.
   */
  public static void validateUpdateContractRequest(ContractRequest contractRequest,
                                                   LeasingContract contract) {
    if (!Objects.equals(contractRequest.getVehicleRequest().getVehicleId(),
        contract.getVehicle().getVehicleId())
        || !Objects.equals(contractRequest.getCustomerRequest().getCustomerId(),
        contract.getCustomer().getCustomerId())) {
      logger.error("Data Not Found for given contract no : {}", contractRequest.getContractNo());
      throw new DataNotFoundException("Data Not Found for given contract no :"
          + contractRequest.getContractNo());
    }
  }

  /**
   * Validates if a leasing contract already exists for the provided customer and vehicle.
   *
   * @param leasingContracts The list of leasing contracts of the customer.
   * @param vehicle          The requested vehicle details.
   * @throws DuplicateRecordException If a leasing contract already exists.
   */
  public static void validateVehicle(List<LeasingContract> leasingContracts,
                                     Vehicle vehicle) {

    Optional<LeasingContract> matchingContract = leasingContracts.stream().filter(
            leasingContract -> leasingContract.getVehicle()
                .equals(vehicle))
        .findFirst();

    if (matchingContract.isPresent()) {
      logger.error("Leasing contract : {} is already exist for customer name {} and Vehicle Id: {}",
          matchingContract.get().getContractNo(),
          matchingContract.get().getCustomer().getFullName(),
          matchingContract.get().getVehicle().getVehicleId());
      throw new DuplicateRecordException(
          "Leasing contract : " + matchingContract.get().getContractNo()
              + " is already exist for customer name: "
              + matchingContract.get().getCustomer().getFullName()
              + " and Vehicle Id: " + matchingContract.get().getVehicle().getVehicleId());
    }

  }
}
