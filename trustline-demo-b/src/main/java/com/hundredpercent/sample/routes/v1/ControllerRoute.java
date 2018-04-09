package com.hundredpercent.sample.routes.v1;

import com.hundredpercent.sample.rest.resource.v1.TrustLineHandler;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * 
 * Main entry point into the application. Defines REST endpoint and URL.
 * 
 */
@Component
public class ControllerRoute extends RouteBuilder {
    
    private final String ROUTE_ID = "trustline-server-entry";

    @Override
    public void configure() throws Exception {
        
        onException(Exception.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,simple("400"))
                .setHeader(Exchange.HTTP_RESPONSE_TEXT,simple("An error occurred"))
                .removeHeaders("*",Exchange.HTTP_RESPONSE_CODE,Exchange.HTTP_RESPONSE_TEXT);
        
        from("cxfrs:?resourceClasses=" + TrustLineHandler.class.getName() + "&bindingStyle=SimpleConsumer&providers=#jacksonJsonProvider")
                    .routeId(ROUTE_ID)
                    .choice()
                        .when(simple("${header.operationName} == 'credit'")) //credit api endpoint was hit
                        .log("Request: ${body}")
                        .to("direct:trustline-credit-processor") //send request payload to credit processing flow
                    .endChoice()
                        .when(simple("${header.operationName} == 'debit'")) //debit api endpoint was hit
                        .log("Request: ${body}")
                        .setProperty("transferValue",simple("${body.value}"))
                        .to("direct:trustline-debit-processor") //send request payload to debit processing flow
                    .endChoice()
                    .end();
    }

}
