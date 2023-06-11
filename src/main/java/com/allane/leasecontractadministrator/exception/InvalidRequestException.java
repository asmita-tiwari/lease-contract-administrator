package com.allane.leasecontractadministrator.exception;

/**
 * InvalidRequestException is thrown request is invalid.
 */

public class InvalidRequestException extends RuntimeException {

  public InvalidRequestException(String message) {
    super(message);
  }
}
