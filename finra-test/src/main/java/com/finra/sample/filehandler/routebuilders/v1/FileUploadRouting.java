/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.routebuilders.v1;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component
public class FileUploadRouting extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:filedrop")
                .log("Saving the file")
                .to("file:///resources?autoCreate=true&doneFileName=${file:name.noext}.complete")
                .log("File ${header.CamelFileName} saved")
                .to("direct:metadata-upload");
    }
    
}
