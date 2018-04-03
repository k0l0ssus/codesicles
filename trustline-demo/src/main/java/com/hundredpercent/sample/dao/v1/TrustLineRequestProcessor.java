
package com.hundredpercent.sample.dao.v1;

import com.hundredpercent.sample.constants.v1.RouteConstants;
import com.hundredpercent.sample.dto.v1.TrustLineCreditRequest;
import com.hundredpercent.sample.dto.v1.Client;
import java.math.BigDecimal;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */

@Component("requestProcessor")
public class TrustLineRequestProcessor implements Handler<Exchange> {
    
    @Autowired
    TrustLineRepository repo;

    @Override
    public void handle(Exchange input) throws Exception {
        
        TrustLineCreditRequest req = input.getIn().getBody(TrustLineCreditRequest.class);
        String clientId = input.getIn().getHeader(RouteConstants.CLIENT_ID, String.class);
        Client client = new Client(clientId);
        TrustLine<BigDecimal> trustLine = repo.getTrustLine(client);
        trustLine.credit(req.getValue());
        input.setProperty("trustLineBalance", trustLine.getTrustLineBalance());
        
    }

}
