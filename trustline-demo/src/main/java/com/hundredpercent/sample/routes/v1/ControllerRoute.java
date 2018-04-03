package com.hundredpercent.sample.routes.v1;

import com.hundredpercent.sample.rest.resource.v1.TrustLineHandler;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component
public class ControllerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        onException(Exception.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE,simple("400"))
                .setHeader(Exchange.HTTP_RESPONSE_TEXT,simple("An error occurred"))
                .removeHeaders("*",Exchange.HTTP_RESPONSE_CODE,Exchange.HTTP_RESPONSE_TEXT);
        
        from("cxfrs:?resourceClasses=" + TrustLineHandler.class.getName() + "&bindingStyle=SimpleConsumer&providers=#jacksonJsonProvider").log("Request ${body}").to("direct:trustline-req-processor");
    }

}
