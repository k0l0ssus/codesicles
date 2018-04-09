package com.hundredpercent.sample.transformer.v1;

import com.hundredpercent.sample.constants.v1.RouteConstants;
import com.hundredpercent.sample.dto.v1.TrustLineCreditResponse;
import com.hundredpercent.sample.dto.v1.TrustLineDebitResponse;
import java.math.BigDecimal;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * 
 * Transforms response to trustline debit requests
 * 
 */

@Component("debitResponseTransformer")
public class TrustLineDebitResponseTransformer implements InOutTransformer<Exchange, TrustLineDebitResponse> {

    @Override
    public TrustLineDebitResponse transform(Exchange input) {
        TrustLineDebitResponse resp = new TrustLineDebitResponse();
        resp.setNewBalance(input.getProperty(RouteConstants.TRUST_LINE_BALANCE, BigDecimal.class));
        return resp;
    }

}
