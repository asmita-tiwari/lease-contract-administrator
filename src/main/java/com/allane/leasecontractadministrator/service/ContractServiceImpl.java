package com.allane.leasecontractadministrator.service;

import static com.allane.leasecontractadministrator.service.ContractMapper.mapToContract;
import static com.allane.leasecontractadministrator.service.ContractMapper.mapToLeasingContractResponse;
import static com.allane.leasecontractadministrator.service.ContractValidator.validateContractRequest;
import static com.allane.leasecontractadministrator.service.ContractValidator.validateUpdateContractRequest;

import com.allane.leasecontractadministrator.domain.Customer;
import com.allane.leasecontractadministrator.domain.LeasingContract;
import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.dto.ContractResponse;
import com.allane.leasecontractadministrator.dto.CustomerRequest;
import com.allane.leasecontractadministrator.exception.DataNotFoundException;
import com.allane.leasecontractadministrator.exception.DatabaseAccessException;
import com.allane.leasecontractadministrator.exception.InvalidRequestException;
import com.allane.leasecontractadministrator.repository.CustomerRepository;
import com.allane.leasecontractadministrator.repository.LeasingContractRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * ContractServiceImpl is responsible for handling the contract operation.
 */
@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {

  private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

  private final LeasingContractRepository leasingContractRepository;
  private final CustomerRepository customerRepository;

  /**
   * Creates a new leasing contract based on the provided contract request.
   *
   * @param contractRequest The contract request containing the details of the contract.
   * @return The response containing the details of the created contract.
   * @throws InvalidRequestException If the contract request fails validation.
   */
  @Override
  public ContractResponse createContract(ContractRequest contractRequest)
      throws InvalidRequestException {
    logger.info("Creating contract: {}", contractRequest);
    validateContractRequest(contractRequest);

    var customer = fetchCustomerDetails(contractRequest.getCustomerRequest());
    logger.info("Mapping contract request to entity: {}", contractRequest);
    LeasingContract leasingContract = mapToContract(contractRequest, customer);

    return persistContractDetails(leasingContract);
  }

  /**
   * Persists the details of a leasing contract in the database.
   *
   * @param leasingContract The leasing contract to be persisted.
   * @return The response containing the details of the persisted contract.
   * @throws DatabaseAccessException If there is an error accessing the data during persistence.
   */
  private ContractResponse persistContractDetails(LeasingContract leasingContract)
      throws DatabaseAccessException {
    try {
      var savedLeasingContract = leasingContractRepository.save(leasingContract);
      logger.info("Leasing Contract Details saved: {}", savedLeasingContract);
      return mapToLeasingContractResponse(savedLeasingContract);
    } catch (DataAccessException | ConstraintViolationException ex) {
      logger.error("Error accessing or validating data while saving contract detail record: {}",
          ex.getMessage());
      throw new DatabaseAccessException("Error accessing or validating data");
    } catch (Exception ex) {
      logger.error("Unexpected exception occurred while persisting data: {}", ex.getMessage());
      throw new DatabaseAccessException("Unexpected exception occurred");
    }
  }

  /**
   * Updates an existing leasing contract based on the provided contract request.
   *
   * @param contractRequest The contract request containing the updated details of the contract.
   * @return The response containing the details of the updated contract.
   * @throws DataNotFoundException   If the contract with the given contract number is not found.
   * @throws InvalidRequestException If the updated contract request fails validation.
   * @throws DatabaseAccessException If there is an error accessing the data during update.
   */
  @Override
  public ContractResponse updateContract(ContractRequest contractRequest) throws
      DatabaseAccessException,
      InvalidRequestException,
      DataNotFoundException {
    logger.info("Update contract: {}", contractRequest);
    var contract = findContractById(contractRequest.getContractNo());
    validateUpdateContractRequest(contractRequest, contract);
    LeasingContract leasingContract = mapToContract(contractRequest);
    logger.info("Updating contract into database: {}", leasingContract);
    return persistContractDetails(leasingContract);
  }


  /**
   * Retrieves the details of a leasing contract based on the provided contract number.
   *
   * @param contractNo The contract number for which to retrieve the details.
   * @return The response containing the details of the contract.
   * @throws DataNotFoundException If the contract with the given contract number is not found.
   */
  @Override
  public ContractResponse getContract(Long contractNo) throws DataNotFoundException {
    logger.info("Get contract details for: {}", contractNo);
    var contract = findContractById(contractNo);
    return mapToLeasingContractResponse(contract);
  }

  /**
   * Finds a leasing contract by the given contract number.
   *
   * @param contractNo The contract number of the leasing contract to find.
   * @return The found leasing contract.
   * @throws DataNotFoundException If the contract with the given contract number is not found.
   */
  private LeasingContract findContractById(Long contractNo) throws DataNotFoundException {
    Optional<LeasingContract> contract = leasingContractRepository.findById(contractNo);
    return contract.orElseThrow(() -> {
      logger.error("Data Not found for given Contract No: {}", contractNo);
      return new DataNotFoundException("Data Not found for given Contract No: " + contractNo);
    });
  }

  /**
   * Fetches the customer details based on the provided customer request.
   *
   * @param customerRequest The customer request containing the details of the customer.
   * @return The fetched customer details.
   */
  private Customer fetchCustomerDetails(CustomerRequest customerRequest) {
    logger.info("Fetching Customer details contract: {}", customerRequest);
    var existingCustomer =
        customerRepository.findByFirstNameAndLastNameAndBirthDate(customerRequest.getFirstName(),
            customerRequest.getLastName(), customerRequest.getBirthDate());

    return existingCustomer.orElseGet(() -> {
      Customer customer = new Customer();
      BeanUtils.copyProperties(customerRequest, customer);
      return customer;
    });
  }

}
