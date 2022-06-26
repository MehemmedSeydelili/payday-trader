package az.expressbank.paydaytrade.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class EmailConfirmationResponse {
    private boolean confirmed;
    private String message;
}
