
package com.hundredpercent.sample.transformer.v1;

import com.hundredpercent.sample.constants.v1.RouteConstants;
import com.hundredpercent.sample.dto.v1.TrustLineDebitRequest;
import com.hundredpercent.sample.dto.v1.TrustLineDebitResponse;
import java.math.BigDecimal;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * 
 * Transforms incoming debit requests
 * 
 */

@Component("debitRequestTransformer")
public class TrustLineDebitRequestTransformer implements InOnlyTransformer<Exchange> {

    @Override
    public void transform(Exchange input) {
        TrustLineDebitRequest req = new TrustLineDebitRequest();
        req.setValue(input.getProperty(RouteConstants.TRANSFER_VALUE, BigDecimal.class));
        input.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
        input.getIn().setHeader("CamelCxfRsResponseClass", TrustLineDebitResponse.class);
        input.getIn().setBody(req);
        
    }

}
