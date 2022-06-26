package az.expressbank.paydaytrade.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private String message;
    private HttpStatus status;
    private List<FieldError> fieldErrors;
    private LocalDateTime timeStamp;
}
