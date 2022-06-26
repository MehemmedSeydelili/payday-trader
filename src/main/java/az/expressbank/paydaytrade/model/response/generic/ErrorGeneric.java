package az.expressbank.paydaytrade.model.response.generic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ErrorGeneric<E> {
    private E error;
}
