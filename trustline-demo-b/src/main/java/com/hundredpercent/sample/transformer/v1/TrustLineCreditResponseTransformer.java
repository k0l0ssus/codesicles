
package com.hundredpercent.sample.transformer.v1;

import com.hundredpercent.sample.constants.v1.RouteConstants;
import com.hundredpercent.sample.dto.v1.TrustLineCreditResponse;
import java.math.BigDecimal;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * 
 * Transforms outgoing trustline credit responses
 */

@Component("responseTransformer")
public class TrustLineCreditResponseTransformer implements InOutTransformer<Exchange,TrustLineCreditResponse> {

    @Override
    public TrustLineCreditResponse transform(Exchange input) {
        TrustLineCreditResponse resp = new TrustLineCreditResponse();
        resp.setNewBalance(input.getProperty(RouteConstants.TRUST_LINE_BALANCE, BigDecimal.class));
        return resp;
    }

}
