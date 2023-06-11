package com.allane.leasecontractadministrator.exception;


import com.allane.leasecontractadministrator.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * ChargeExceptionHandler to manage the response for exceptions.
 */

@ControllerAdvice
public class ContractExceptionHandler {

  /**
   * Handle Response for DuplicateDataFoundException.
   *
   * @param exception         The exception that was thrown during the processing of the request.
   * @param servletWebRequest The current servlet web request.
   * @return Return Error Response for DuplicateDataFoundException.
   */
  @ExceptionHandler(value = DuplicateRecordException.class)
  public ResponseEntity<ErrorResponse> handleDuplicateDataFoundException(
      DuplicateRecordException exception,
      ServletWebRequest servletWebRequest) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
        mapToErrorResponse(exception.getMessage(),
            servletWebRequest,
            HttpStatus.CONFLICT));
  }

  /**
   * Handle Response for DataNotFoundException.
   *
   * @param exception         The exception that was thrown during the processing of the request.
   * @param servletWebRequest The current servlet web request.
   * @return Return Error Response for DataNotFoundException.
   */
  @ExceptionHandler(value = DataNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleDataNotFoundException(
      DataNotFoundException exception,
      ServletWebRequest servletWebRequest) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        mapToErrorResponse(exception.getMessage(),
            servletWebRequest,
            HttpStatus.NOT_FOUND));
  }

  /**
   * Handle Response for Validation Error.
   *
   * @param exception         The exception that was thrown during the processing of the request.
   * @param servletWebRequest The current servlet web request.
   * @return Return Error Response for Validation Error.
   */
  @ExceptionHandler({InvalidRequestException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorResponse> handleValidationException(
      Exception exception,
      ServletWebRequest servletWebRequest) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        mapToErrorResponse(exception.getMessage(),
            servletWebRequest,
            HttpStatus.BAD_REQUEST));
  }

  /**
   * To Map the Error Response.
   *
   * @param message           The error message or description associated with the exception.
   * @param servletWebRequest The current servlet web request.
   * @param httpStatus        The HTTP status code to be set in the response.
   * @return Return Error Response.
   */
  private ErrorResponse mapToErrorResponse(String message,
                                           ServletWebRequest servletWebRequest,
                                           HttpStatus httpStatus) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrorMessage(message);
    errorResponse.setPath(servletWebRequest.getRequest().getRequestURI());
    errorResponse.setStatusCode(httpStatus.value());
    errorResponse.setStatusMessage(httpStatus.getReasonPhrase());
    return errorResponse;
  }
}