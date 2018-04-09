
package com.hundredpercent.sample.routes.v1;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * 
 * Handles trustline credit requests
 * 
 */

@Component
public class TrustLineCreditRequestProcessorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(Exception.class).logStackTrace(true);
        
        from("direct:trustline-credit-processor")
                .log("Validating request from ${header.clientId}")
                .bean("requestValidator") //validate the request
                .log("Processing trustline request from ${header.clientId} for ${body.value}")
                .bean("requestProcessor","credit") //execute the credit
                .log("Trustline credit request processed; new balance: ${exchangeProperty.trustLineBalance}")
                .bean("responseTransformer"); //build response object for outbound JSON
    }

}
