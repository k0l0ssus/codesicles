
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
                .log("Trustline balance is: ${exchangeProperty.trustLineBalance}")
                .log("Validating request from ${header.clientId}")
                .bean("requestValidator") //validate the request
                .bean("requestProcessor","credit") //execute the credit
                .log("You were paid ${body.value}")
                .log("Trustline balance is ${exchangeProperty.trustLineBalance}")
                .bean("responseTransformer"); //build response object for outbound JSON
    }

}
