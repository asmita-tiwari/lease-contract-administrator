package com.allane.leasecontractadministrator.exception;

/**
 * DuplicateRecordException is thrown when duplicate data found.
 */
public class DuplicateRecordException extends RuntimeException {

  public DuplicateRecordException(String message) {
    super(message);
  }
}
