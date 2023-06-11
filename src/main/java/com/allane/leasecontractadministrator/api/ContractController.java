package com.allane.leasecontractadministrator.api;

import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.dto.ContractResponse;
import com.allane.leasecontractadministrator.dto.ErrorResponse;
import com.allane.leasecontractadministrator.service.ContractService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contract Controller is to handle the Rest APIs to manage the lease contract.
 */
@RestController
@AllArgsConstructor
public class ContractController {
  private static final Logger logger = LoggerFactory.getLogger(ContractController.class);
  private final ContractService contractService;

  /**
   * Create a new Lease Contract Record.
   *
   * @param contractRequest Leasing contract request
   * @return Created Lease Contract Response
   */
  @ApiOperation("Create a new Charge Detail Record")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Charge detail record saved successfully",
          response = ContractResponse.class),
      @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
      @ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "Access Denied", response = ErrorResponse.class),
      @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)
  })
  @PostMapping("/contract")
  public ResponseEntity<ContractResponse> createContract(
      @RequestBody ContractRequest contractRequest) {
    logger.info("Received createContract request with contractRequest: {}", contractRequest);
    ContractResponse contractDetailsResponse =
        contractService.createContract(contractRequest);
    logger.info("Created contractDetailsResponse: {}", contractDetailsResponse);
    return ResponseEntity.status(HttpStatus.CREATED).body(contractDetailsResponse);
  }


  /**
   * Update Lease Contract Record.
   *
   * @param contractRequest Leasing contract request to update the contract
   * @return Updated Lease Contract Response
   */
  @ApiOperation("Create a new Charge Detail Record")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Charge detail record saved successfully",
          response = ContractResponse.class),
      @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "Data Not Found", response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "Access Denied", response = ErrorResponse.class),
      @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)
  })
  @PutMapping("/contract")
  public ResponseEntity<ContractResponse> updateContract(
      @RequestBody ContractRequest contractRequest) {
    logger.info("Received update contract request to update the contract: {}", contractRequest);
    ContractResponse contractDetailsResponse =
        contractService.updateContract(contractRequest);
    logger.info("Updated contractDetailsResponse: {}", contractDetailsResponse);
    return ResponseEntity.status(HttpStatus.OK).body(contractDetailsResponse);
  }

  /**
   * Get Lease Contract Record.
   *
   * @param contractNo contract no to fetch the contract details
   * @return Lease Contract Response
   */
  @ApiOperation("Create a new Charge Detail Record")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Charge detail record saved successfully",
          response = ContractResponse.class),
      @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "Data Not Found", response = ErrorResponse.class),
      @ApiResponse(code = 403, message = "Access Denied", response = ErrorResponse.class),
      @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse.class)
  })
  @GetMapping("/contract")
  public ResponseEntity<ContractResponse> getContract(
      @NonNull @RequestParam Long contractNo) {
    logger.info("Received request to get the contract by contract no: {}", contractNo);
    ContractResponse contractDetailsResponse =
        contractService.getContract(contractNo);
    logger.info("ContractDetailsResponse: {}", contractDetailsResponse);
    return ResponseEntity.status(HttpStatus.OK).body(contractDetailsResponse);
  }
}
