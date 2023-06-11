package com.allane.leasecontractadministrator.exception;

/**
 * DuplicateRecordException is thrown when duplicate data found.
 */
public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException(String message) {
    super(message);
  }
}
