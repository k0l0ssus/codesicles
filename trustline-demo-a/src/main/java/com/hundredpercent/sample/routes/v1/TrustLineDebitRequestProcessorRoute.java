
package com.hundredpercent.sample.routes.v1;

import com.hundredpercent.sample.constants.v1.RouteConstants;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * Route for processing trustline debit requests
 */

@Component
public class TrustLineDebitRequestProcessorRoute extends RouteBuilder {
    
    @Value("${service.id}")
    private String SERVICE_ID;
    @Value("${partner.port}")
    private String PARTNER_PORT;
    private final String ROUTE_ID = "trustline-debit-route";
    private final String SERVICE_ADDR = "localhost:";

    @Override
    public void configure() throws Exception {
        from("direct:trustline-debit-processor").routeId(ROUTE_ID)
                                      .bean("debitRequestTransformer")
                                      .log("Processing trustline debit request from ${header.clientId} for ${body.value}")
                                      .removeHeader(Exchange.HTTP_PATH)
                                      .setProperty(RouteConstants.TRANSFER_VALUE,simple("${body.value}"))
                                      .to("cxfrs://http://"+SERVICE_ADDR+PARTNER_PORT+"/trustline-processor/trustline/"+SERVICE_ID+"/credit?httpClientAPI=true&providers=#jacksonJsonProvider")
                                      .bean("requestProcessor","debit") //execute debit operation
                                      .log("Trustline debit request processed; new balance: ${exchangeProperty.trustLineBalance}")
                                      .bean("debitResponseTransformer"); //prepare api response payload
        
    }

}
