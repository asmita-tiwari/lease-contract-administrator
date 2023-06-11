package com.allane.leasecontractadministrator.service;

import static com.allane.leasecontractadministrator.service.ContractValidator.validateVehicle;

import com.allane.leasecontractadministrator.domain.Customer;
import com.allane.leasecontractadministrator.domain.LeasingContract;
import com.allane.leasecontractadministrator.domain.Vehicle;
import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.dto.ContractResponse;
import com.allane.leasecontractadministrator.dto.CustomerRequest;
import com.allane.leasecontractadministrator.dto.CustomerResponse;
import com.allane.leasecontractadministrator.dto.VehicleRequest;
import com.allane.leasecontractadministrator.dto.VehicleResponse;
import org.springframework.beans.BeanUtils;

/**
 * ContractMapper is responsible for mapping the request and response objects with entities.
 */
public class ContractMapper {

  /**
   * Map the Contract Entity to Contract Response.
   *
   * @param leasingContract Accepts the Contract Entity
   * @return Final ContractResponse
   */

  public static ContractResponse mapToLeasingContractResponse(LeasingContract leasingContract) {
    ContractResponse contractResponse = new ContractResponse();
    BeanUtils.copyProperties(leasingContract, contractResponse);
    contractResponse.setCustomerResponse(
        mapToCustomerResponse(leasingContract.getCustomer())
    );
    contractResponse.setVehicleResponse(
        mapToVehicleResponse(leasingContract.getVehicle())
    );
    return contractResponse;
  }

  /**
   * Map the vehicle entity to vehicle response.
   *
   * @param vehicle accepts the vehicle Entity
   * @return Final VehicleResponse
   */
  private static VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
    VehicleResponse vehicleResponse = new VehicleResponse();
    BeanUtils.copyProperties(vehicle, vehicleResponse);
    return vehicleResponse;
  }

  /**
   * Map the customer entity to customer response.
   *
   * @param customer accepts the customer Entity
   * @return Final CustomerResponse
   */
  private static CustomerResponse mapToCustomerResponse(Customer customer) {
    CustomerResponse customerResponse = new CustomerResponse();

    BeanUtils.copyProperties(customer, customerResponse);
    return customerResponse;
  }

  /**
   * Map the LeasingContract request to LeasingContract Entity.
   *
   * @param contractRequest contractRequest
   * @param customer        customer entity
   * @return LeasingContract Entity
   */
  public static LeasingContract mapToContract(ContractRequest contractRequest, Customer customer) {

    Vehicle vehicle = mapToVehicle(contractRequest.getVehicleRequest());

    validateVehicle(customer.getLeasingContracts(), vehicle);

    LeasingContract leasingContract = new LeasingContract();
    leasingContract.setCustomer(customer);
    leasingContract.setVehicle(vehicle);
    BeanUtils.copyProperties(contractRequest, leasingContract);
    return leasingContract;
  }

  /**
   * Map the LeasingContract request to LeasingContract Entity to Update the database.
   *
   * @param contractRequest contractRequest
   * @return LeasingContract Entity
   */
  public static LeasingContract mapToContract(ContractRequest contractRequest) {

    LeasingContract leasingContract = new LeasingContract();
    leasingContract.setCustomer(mapToCustomer(contractRequest.getCustomerRequest()));
    leasingContract.setVehicle(mapToVehicle(contractRequest.getVehicleRequest()));
    BeanUtils.copyProperties(contractRequest, leasingContract);
    return leasingContract;
  }

  /**
   * Map the Vehicle Request Object to Customer Entity.
   *
   * @param customerRequest customerRequest
   * @return Customer entity
   */
  private static Customer mapToCustomer(CustomerRequest customerRequest) {

    Customer customer = new Customer();
    BeanUtils.copyProperties(customerRequest, customer);
    return customer;
  }

  /**
   * Map the Vehicle Request Object to Vehicle Entity.
   *
   * @param vehicleRequest vehicleRequest
   * @return Vehicle entity
   */
  private static Vehicle mapToVehicle(VehicleRequest vehicleRequest) {
    Vehicle vehicle = new Vehicle();
    BeanUtils.copyProperties(vehicleRequest, vehicle);
    return vehicle;
  }
}
