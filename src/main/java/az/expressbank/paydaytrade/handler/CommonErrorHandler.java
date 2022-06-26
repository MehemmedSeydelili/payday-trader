package az.expressbank.paydaytrade.handler;

import az.expressbank.paydaytrade.error.ResourceConflictException;
import az.expressbank.paydaytrade.error.ResourceNotFoundException;
import az.expressbank.paydaytrade.model.response.ApiErrorResponse;
import az.expressbank.paydaytrade.model.response.FieldError;
import az.expressbank.paydaytrade.model.response.generic.ErrorGeneric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CommonErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getFieldErrors().stream()
                .map(fieldError -> FieldError.of(
                        fieldError.getField(),
                        fieldError.getRejectedValue(),
                        fieldError.getDefaultMessage())).toList();

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .fieldErrors(fieldErrors)
                .build();

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(ResourceConflictException.class)
    protected ResponseEntity<Object> handleResourceConflictException(ResourceConflictException ex) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT)
                .build();

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return buildResponseEntity(apiErrorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
        return new ResponseEntity<>(ErrorGeneric.of(apiErrorResponse), apiErrorResponse.getStatus());
    }
}
