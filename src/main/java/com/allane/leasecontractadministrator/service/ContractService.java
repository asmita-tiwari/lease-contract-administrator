package com.allane.leasecontractadministrator.service;

import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.dto.ContractResponse;
import org.springframework.stereotype.Service;

/**
 * Contract Service to manage the operation request in between controller
 * and service implementation.
 */
@Service
public interface ContractService {
  ContractResponse createContract(ContractRequest contractRequest);

  ContractResponse updateContract(ContractRequest contractRequest);

  ContractResponse getContract(Long contractNo);
}
