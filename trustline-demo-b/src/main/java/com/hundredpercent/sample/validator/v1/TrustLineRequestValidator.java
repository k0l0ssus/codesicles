package com.hundredpercent.sample.validator.v1;

import com.hundredpercent.sample.dto.v1.TrustLineCreditRequest;
import java.math.BigDecimal;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component("requestValidator")
public class TrustLineRequestValidator implements Validator<Exchange> {

    
    @Override
    public void validate(Exchange input) {
        TrustLineCreditRequest req = input.getIn().getBody(TrustLineCreditRequest.class);

        if (req == null) {
            throw new IllegalArgumentException("Empty Request");
        } else if (req.getValue() == null) {
            throw new IllegalArgumentException("Empty body");
        } else if (req.getValue().compareTo(BigDecimal.ONE) == -1) {
            throw new IllegalArgumentException("Transaction value below minimum");
        }

    }

}
