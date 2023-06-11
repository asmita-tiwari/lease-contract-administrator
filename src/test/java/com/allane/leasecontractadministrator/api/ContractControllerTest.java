package com.allane.leasecontractadministrator.api;


import static com.allane.leasecontractadministrator.TestUtils.asJsonString;
import static com.allane.leasecontractadministrator.TestUtils.buildLeaseContractRequest;
import static com.allane.leasecontractadministrator.TestUtils.buildLeaseContractResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.allane.leasecontractadministrator.dto.ContractRequest;
import com.allane.leasecontractadministrator.dto.ContractResponse;
import com.allane.leasecontractadministrator.service.ContractService;
import java.util.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * ContractControllerTest to test the Rest APIs for Contract.
 */

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WebMvcTest(ContractController.class)
@AutoConfigureMockMvc
@WithMockUser(username = "test", password = "test")
class ContractControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ContractService contractService;

  @Test
  @DisplayName("Successfully persist the contract Details Record")
  public void saveContractRecordTest() throws Exception {

    var contractResponse = buildLeaseContractResponse();
    // Mock the contractService
    Mockito.when(contractService.createContract(any(ContractRequest.class)))
        .thenReturn(contractResponse);
    performContractRequest(MockMvcRequestBuilders.post("/contract"),
        contractResponse, MockMvcResultMatchers.status().isCreated());
  }

  @Test
  @DisplayName("Successfully updated the contract Details Record")
  public void updateContractRecordTest() throws Exception {

    var contractResponse = buildLeaseContractResponse();
    // Mock the contractService
    Mockito.when(contractService.updateContract(any(ContractRequest.class)))
        .thenReturn(contractResponse);

    // Perform the PUT request to the "/contract" endpoint
    performContractRequest(MockMvcRequestBuilders.put("/contract"), contractResponse,
        MockMvcResultMatchers.status().isOk());
  }

  @Test
  @DisplayName("Successfully get the contract details record")
  public void getContractRecordTest() throws Exception {

    var contractResponse = buildLeaseContractResponse();
    // Mock the contractService
    Mockito.when(contractService.getContract(any()))
        .thenReturn(contractResponse);

    // Perform the GET request to the "/contract" endpoint
    performContractRequest(MockMvcRequestBuilders.get("/contract").param("contractNo",
            String.valueOf(1234567L)), contractResponse,
        MockMvcResultMatchers.status().isOk());
  }

  private void performContractRequest(MockHttpServletRequestBuilder requestBuilder,
                                      ContractResponse contractResponse,
                                      ResultMatcher resultMatcher) throws Exception {

    mockMvc.perform(requestBuilder
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Basic " + Base64.getEncoder()
                .encodeToString(("test" + ":" + "test").getBytes()))
            .with(csrf())
            .content(asJsonString(buildLeaseContractRequest(0L, 0L, 0L))))
        .andExpect(resultMatcher)
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.contractNo")
                .value(contractResponse.getContractNo()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.monthlyRate")
                .value(contractResponse.getMonthlyRate()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.customer.customerId")
                .value(contractResponse.getCustomerResponse().getCustomerId()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.customer.firstName")
                .value(contractResponse.getCustomerResponse().getFirstName()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.customer.lastName")
                .value(contractResponse.getCustomerResponse().getLastName()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.customer.birthDate")
                .value(contractResponse.getCustomerResponse().getBirthDate().toString()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.vehicle.vehicleId")
                .value(contractResponse.getVehicleResponse().getVehicleId()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.vehicle.brand")
                .value(contractResponse.getVehicleResponse().getBrand()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.vehicle.model")
                .value(contractResponse.getVehicleResponse().getModel()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.vehicle.year")
                .value(contractResponse.getVehicleResponse().getYear()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.vehicle.vin")
                .value(contractResponse.getVehicleResponse().getVin()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.vehicle.price")
                .value(contractResponse.getVehicleResponse().getPrice()))
        .andReturn();
  }

}