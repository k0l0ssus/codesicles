
package com.hundredpercent.sample.routes.v1;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */

@Component
public class TrustLineRequestProcessorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(Exception.class).logStackTrace(true);
        
        from("direct:trustline-req-processor")
                .log("Validating request from ${header.clientId}")
                .bean("requestValidator")
                .log("Processing trustline request from ${header.clientId} for ${body.value}")
                .bean("requestProcessor")
                .log("Trustline credit request processed; new balance: ${exchangeProperty.trustLineBalance}")
                .bean("responseTransformer");
    }

}
