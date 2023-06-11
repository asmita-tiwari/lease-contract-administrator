package com.allane.leasecontractadministrator.service;

import static com.allane.leasecontractadministrator.TestUtils.assertContractResponse;
import static com.allane.leasecontractadministrator.TestUtils.buildContractDetails;
import static com.allane.leasecontractadministrator.TestUtils.buildExistingCustomerDetails;
import static com.allane.leasecontractadministrator.TestUtils.buildLeaseContractRequest;
import static com.allane.leasecontractadministrator.TestUtils.buildLeaseContractResponse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import com.allane.leasecontractadministrator.domain.LeasingContract;
import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.dto.ContractResponse;
import com.allane.leasecontractadministrator.exception.DataNotFoundException;
import com.allane.leasecontractadministrator.exception.DatabaseAccessException;
import com.allane.leasecontractadministrator.exception.DuplicateRecordException;
import com.allane.leasecontractadministrator.exception.InvalidRequestException;
import com.allane.leasecontractadministrator.repository.CustomerRepository;
import com.allane.leasecontractadministrator.repository.LeasingContractRepository;
import java.util.Optional;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContractServiceImplTest {

  @Mock
  LeasingContractRepository contractRepository;

  @Mock
  CustomerRepository customerRepository;

  @InjectMocks
  ContractServiceImpl contractService;


  @Test
  @DisplayName("Should successfully saves the new contract details record")
  void saveContractRecordTest() {

    Mockito.when(contractRepository.save(any(LeasingContract.class)))
        .thenReturn(buildContractDetails());

    ContractResponse contract = contractService.createContract(
        buildLeaseContractRequest(0L, 0L, 0L));

    assertContractResponse(buildLeaseContractResponse(), contract);
  }

  @Test
  @DisplayName("Should successfully update the contract details")
  void updateContractRecordTest() {

    Mockito.when(contractRepository.findById(anyLong()))
        .thenReturn(Optional.of(buildContractDetails()));
    Mockito.when(contractRepository.save(any(LeasingContract.class)))
        .thenReturn(buildContractDetails());

    ContractResponse contract = contractService.updateContract(
        buildLeaseContractRequest(1234567L, 1L, 1001L));

    assertContractResponse(buildLeaseContractResponse(), contract);
  }

  @Test
  @DisplayName("Should successfully fetch contract details")
  void getContractRecordTest() {
    Mockito.when(contractRepository.findById(any()))
        .thenReturn(Optional.of(buildContractDetails()));
    ContractResponse contract = contractService.getContract(1234567L);
    assertContractResponse(buildLeaseContractResponse(), contract);
  }

  @Test
  @DisplayName("Should throw Data Found when data validation fails")
  void updateContractDataNotFoundExceptionForInvalidIdsTest() {

    Mockito.when(contractRepository.findById(anyLong()))
        .thenReturn(Optional.of(buildContractDetails()));

    assertThrows(DataNotFoundException.class,
        () -> contractService.updateContract(
            buildLeaseContractRequest(1234567L, 0L, 0L)));

  }


  @Test
  @DisplayName("Should throw DataNotFoundException when contract data is not found")
  void updateContractRecordDataNotFoundExceptionTest() {

    assertThrows(DataNotFoundException.class,
        () -> contractService.updateContract(buildLeaseContractRequest(1234567L, 1L, 1001L)));
  }


  @Test
  @DisplayName("Exception occurred while performing database operations")
  void saveContractRecordDatabaseAccessExceptionTest() {

    Mockito.when(contractRepository.save(any(LeasingContract.class)))
        .thenThrow(RuntimeException.class);

    assertThrows(DatabaseAccessException.class,
        () -> contractService.createContract(buildLeaseContractRequest(0L, 0L, 0L)));

  }

  @Test
  @DisplayName("Throw ConstraintViolationException when constraint violation exception occurs ")
  void saveContractRecordConstraintViolationExceptionTest() {

    Mockito.when(contractRepository.save(any(LeasingContract.class)))
        .thenThrow(ConstraintViolationException.class);

    assertThrows(DatabaseAccessException.class,
        () -> contractService.createContract(buildLeaseContractRequest(0L, 0L, 0L)));

  }

  @Test
  @DisplayName("Should throw Invalid RequestException when invalid contract details provided")
  void saveContractRecordInvalidRequestException() {
    // when Contract number is not null for new contract
    ContractRequest contractRequest = new ContractRequest();
    contractRequest.setContractNo(20002L);
    assertThrows(InvalidRequestException.class,
        () -> contractService.createContract(contractRequest));
  }


  @Test
  @DisplayName("Should throw DuplicateRecordException when existing contract details provided")
  void saveContractRecordDuplicateRecordException() {


    Mockito.when(customerRepository
        .findByFirstNameAndLastNameAndBirthDate(anyString(),
            anyString(), any())).thenReturn(Optional.of(buildExistingCustomerDetails()));
    // when leasing contract is duplicate
    assertThrows(DuplicateRecordException.class,
        () -> contractService.createContract(buildLeaseContractRequest(0L, 0L, 0L)));
  }
}