/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.routebuilders.v1;

import com.finra.sample.filehandler.dto.v1.BasicFINRACommand;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component
public class FrontEndRouting extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        onException(IllegalArgumentException.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody().constant("Invalid request data");
        
        JaxbDataFormat jaxb = new JaxbDataFormat();
        
        jaxb.setContextPath("com.finra.sample.filehandler.dto.v1");
        restConfiguration().component("netty4-http").componentProperty("httpMethodRestrict", "DELETE").host("localhost").port(9000).bindingMode(RestBindingMode.auto);
        rest("/finrauploads/").consumes("application/xml").post("/file/").type(BasicFINRACommand.class).route().unmarshal(jaxb)
                .bean("routingTransformer", "transform")
                .choice()
                .when(exchangeProperty("commandType").isEqualTo("SendFileCommandRequest"))
                .bean("fileCommandValidator", "validate")
                .to("direct:filedrop")
                .when(exchangeProperty("commandType").isEqualTo("RetrieveMetadataCommandRequest"))
                .bean("retrieveMetadataValidator", "validate")
                .to("direct:metadata-download");

    }

}
