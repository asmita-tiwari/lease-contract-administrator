package com.allane.leasecontractadministrator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.allane.leasecontractadministrator.domain.Customer;
import com.allane.leasecontractadministrator.domain.LeasingContract;
import com.allane.leasecontractadministrator.domain.Vehicle;
import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.dto.ContractResponse;
import com.allane.leasecontractadministrator.dto.CustomerRequest;
import com.allane.leasecontractadministrator.dto.CustomerResponse;
import com.allane.leasecontractadministrator.dto.VehicleRequest;
import com.allane.leasecontractadministrator.dto.VehicleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods to generate objects to test.
 */
public class TestUtils {


  /**
   * To build LeaseContract Response.
   *
   * @return contract response.
   */
  public static ContractResponse buildLeaseContractResponse() {

    ContractResponse contractResponse = new ContractResponse();
    contractResponse.setContractNo(1234567L);
    contractResponse.setMonthlyRate(BigDecimal.valueOf(35000));
    contractResponse.setCustomerResponse(buildCustomerResponse());
    contractResponse.setVehicleResponse(buildVehicleResponse());
    return contractResponse;
  }

  /**
   * To build Vehicle Response.
   *
   * @return VehicleResponse.
   */
  private static VehicleResponse buildVehicleResponse() {
    VehicleResponse vehicleResponse = new VehicleResponse();
    vehicleResponse.setVehicleId(1L);
    vehicleResponse.setYear(2022);
    vehicleResponse.setBrand("BMW");
    vehicleResponse.setModel("X3");
    vehicleResponse.setVin("1GNEK13ZX3R298984");
    vehicleResponse.setPrice(BigDecimal.valueOf(43.350));
    return vehicleResponse;
  }

  /**
   * To build Customer Response.
   *
   * @return CustomerResponse.
   */
  private static CustomerResponse buildCustomerResponse() {

    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setCustomerId(1001L);
    customerResponse.setFirstName("Max");
    customerResponse.setLastName("Mustermann");
    customerResponse.setBirthDate(LocalDate.of(1980, Month.JANUARY, 01));
    return customerResponse;

  }

  /**
   * To build Contract Request.
   *
   * @return ContractRequest.
   */
  public static ContractRequest buildLeaseContractRequest(Long contractNo, Long vehicleId,
                                                          Long customerId) {
    ContractRequest contractRequest = new ContractRequest();
    contractRequest.setCustomerRequest(buildCustomerRequest(customerId));
    contractRequest.setContractNo(contractNo);
    contractRequest.setVehicleRequest(buildVehicleRequest(vehicleId));
    contractRequest.setMonthlyRate(BigDecimal.valueOf(35000));
    return contractRequest;
  }

  /**
   * To build Vehicle Request.
   *
   * @return VehicleRequest.
   */
  private static VehicleRequest buildVehicleRequest(Long vehicleId) {
    VehicleRequest vehicleRequest = new VehicleRequest();
    vehicleRequest.setVehicleId(vehicleId);
    vehicleRequest.setYear(2022);
    vehicleRequest.setBrand("BMW");
    vehicleRequest.setModel("X3");
    vehicleRequest.setVin("1GNEK13ZX3R298984");
    vehicleRequest.setPrice(BigDecimal.valueOf(43.350));
    return vehicleRequest;
  }

  /**
   * To build Customer request.
   *
   * @return customer request.
   */
  private static CustomerRequest buildCustomerRequest(Long customerId) {
    CustomerRequest customerRequest = new CustomerRequest();
    customerRequest.setCustomerId(customerId);
    customerRequest.setFirstName("Max");
    customerRequest.setLastName("Mustermann");
    customerRequest.setBirthDate(LocalDate.of(1980, Month.JANUARY, 1));
    return customerRequest;
  }

  /**
   * Convert the Contract request to json string.
   *
   * @param obj contract request.
   * @return string of contract request.
   * @throws Exception Exception
   */
  public static String asJsonString(ContractRequest obj) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.writeValueAsString(obj);
  }

  /**
   * To build Contract entity.
   *
   * @return LeasingContract.
   */
  public static LeasingContract buildContractDetails() {

    LeasingContract contract = new LeasingContract();
    contract.setContractNo(1234567L);
    contract.setMonthlyRate(BigDecimal.valueOf(35000));
    contract.setCustomer(buildCustomerDetails());
    contract.setVehicle(buildVehicleDetails());
    return contract;
  }

  /**
   * To Build Vehicle entity.
   *
   * @return Vehicle Entity.
   */
  private static Vehicle buildVehicleDetails() {
    Vehicle vehicle = new Vehicle();
    vehicle.setVehicleId(1L);
    vehicle.setYear(2022);
    vehicle.setBrand("BMW");
    vehicle.setModel("X3");
    vehicle.setVin("1GNEK13ZX3R298984");
    vehicle.setPrice(BigDecimal.valueOf(43.350));
    return vehicle;
  }

  /**
   * To build customer entity.
   *
   * @return Customer entity.
   */
  public static Customer buildCustomerDetails() {

    Customer customer = new Customer();
    customer.setCustomerId(1001L);
    customer.setFirstName("Max");
    customer.setLastName("Mustermann");
    customer.setBirthDate(LocalDate.of(1980, Month.JANUARY, 01));
    return customer;

  }

  /**
   * To build and test existing contract.
   *
   * @return LeasingContract.
   */
  public static LeasingContract buildExistingContractDetails() {

    LeasingContract contract = new LeasingContract();
    contract.setContractNo(1234567L);
    contract.setMonthlyRate(BigDecimal.valueOf(35000));
    contract.setVehicle(buildVehicleDetails());
    contract.setCustomer(buildCustomerDetails());
    return contract;
  }

  /**
   * To build existing customer details.
   *
   * @return Customer entity.
   */
  public static Customer buildExistingCustomerDetails() {

    Customer customer = new Customer();
    customer.setCustomerId(1001L);
    customer.setFirstName("Max");
    customer.setLastName("Mustermann");
    customer.setBirthDate(LocalDate.of(1980, Month.JANUARY, 01));
    List<LeasingContract> list = new ArrayList<>();
    list.add(buildExistingContractDetails());
    customer.setLeasingContracts(list);
    return customer;

  }

  /**
   * Assert Contract Response.
   *
   * @param expectedContractResponse expectedContractResponse.
   * @param actualContractResponse   actualContractResponse.
   */
  public static void assertContractResponse(ContractResponse expectedContractResponse,
                                            ContractResponse actualContractResponse) {

    assertEquals(expectedContractResponse.getContractNo(), actualContractResponse.getContractNo());
    assertEquals(expectedContractResponse.getMonthlyRate(),
        actualContractResponse.getMonthlyRate());
    assertEquals(expectedContractResponse.getCustomerResponse(),
        actualContractResponse.getCustomerResponse());
    assertEquals(expectedContractResponse.getVehicleResponse(),
        actualContractResponse.getVehicleResponse());

  }
}