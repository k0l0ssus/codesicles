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
public class MetadataDownloadRouting extends RouteBuilder {
    
    

    @Override
    public void configure() throws Exception {
        from("direct:metadata-download")
                .log("retrieving file metadata")
                .bean("basicStorageImpl","retrieve")
                .log("Metadata retrieved")
                .bean("retrieveMetadataResponseTransformer","transform");
    }

    
}
