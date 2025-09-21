package btn.jmt.hermetization.configuration;

import btn.jmt.hermetization.controller.internal.model.Error;
import btn.jmt.hermetization.controller.internal.model.ErrorCode;
import btn.jmt.hermetization.controller.internal.model.ErrorResponse;
import btn.jmt.hermetization.service.document.exception.DocumentFileNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
class RestExceptionHandler {

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
    log.error(
        "Unexpected exception has occurred httpMethod={} URI={} msg={}",
        request.getMethod(),
        request.getRequestURI(),
        ex.getMessage(),
        ex);

    return ResponseEntity.internalServerError()
        .body(createErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
  }

  @ExceptionHandler(DocumentFileNotFoundException.class)
  ResponseEntity<ErrorResponse> handleFileNotFoundException(
      DocumentFileNotFoundException ex, HttpServletRequest request) {
    log.error(
        "Cant find file for {} httpMethod={} URI={} ",
        ex.getProcessNumber(),
        request.getMethod(),
        request.getRequestURI(),
        ex);

    return ResponseEntity.badRequest().body(createErrorResponse(ErrorCode.DOCUMENT_FILE_NOT_FOUND));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception, HttpServletRequest request) {
    log.error(
        "The MethodArgumentNotValidException has occurred for method={} uri={} msg={}",
        request.getMethod(),
        request.getRequestURI(),
        exception.getMessage(),
        exception);

    final List<Error> errors =
        exception.getBindingResult().getFieldErrors().stream()
            .map(
                error ->
                    new Error(
                        ErrorCode.DEFAULT100,
                        String.format(
                            "Argument %s - %s", error.getField(), error.getDefaultMessage())))
            .toList();
    return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(ErrorResponse.with(errors));
  }

  private ErrorResponse createErrorResponse(ErrorCode errorCode) {
    final var errorResponse = new ErrorResponse();
    final var error = new Error(errorCode, "Something gone wrong");
    error.setCode(errorCode);
    errorResponse.setErrors(List.of(error));
    return errorResponse;
  }
}
