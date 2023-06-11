package com.allane.leasecontractadministrator.dto;

import java.time.OffsetDateTime;
import lombok.Data;

/**
 * ErrorResponse represent the error response.
 */
@Data
public class ErrorResponse {
  OffsetDateTime timestamp = OffsetDateTime.now();
  String path;
  int statusCode;
  String statusMessage;
  String errorMessage;

}