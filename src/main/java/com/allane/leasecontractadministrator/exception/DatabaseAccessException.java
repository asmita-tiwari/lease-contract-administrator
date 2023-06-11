package com.allane.leasecontractadministrator.exception;

/**
 * DatabaseAccessException to throw the exception related to Database.
 */
public class DatabaseAccessException extends RuntimeException {

  public DatabaseAccessException(String message) {
    super(message);
  }
}