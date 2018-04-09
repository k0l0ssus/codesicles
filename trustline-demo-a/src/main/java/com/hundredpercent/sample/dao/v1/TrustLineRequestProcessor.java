
package com.hundredpercent.sample.dao.v1;

import com.hundredpercent.sample.constants.v1.RouteConstants;
import com.hundredpercent.sample.dto.v1.TrustLineCreditRequest;
import com.hundredpercent.sample.dto.v1.Client;
import com.hundredpercent.sample.dto.v1.TrustLineDebitRequest;
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

    
    /*
    **Execute a credit request:
    ** Adjusts trustline value for the creditor
    **
    */
    @Override
    public void credit(Exchange input) throws Exception {
        
        TrustLineCreditRequest req = input.getIn().getBody(TrustLineCreditRequest.class);
        String clientId = input.getIn().getHeader(RouteConstants.CLIENT_ID, String.class);
        Client client = new Client(clientId);
        TrustLine<BigDecimal> trustLine = repo.getTrustLine(client);
        trustLine.credit(req.getValue());
        input.setProperty(RouteConstants.TRUST_LINE_BALANCE, trustLine.getTrustLineBalance());
        
    }

    
    /*
    *Executes a debit request
    *adjusts the trustline value for the debtor
    */
    @Override
    public void debit(Exchange input) throws Exception {
        String clientId = input.getIn().getHeader(RouteConstants.CLIENT_ID, String.class); //get debtor's ID 
        BigDecimal value = input.getProperty(RouteConstants.TRANSFER_VALUE,BigDecimal.class); //get requested transfer value
        Client client = new Client(clientId);
        TrustLine<BigDecimal> trustLine = repo.getTrustLine(client); //retrieve debtor's trustline
        trustLine.debit(value); //execute debit
        input.setProperty(RouteConstants.TRUST_LINE_BALANCE, trustLine.getTrustLineBalance()); //set the new trustline as a global property
    }

}
