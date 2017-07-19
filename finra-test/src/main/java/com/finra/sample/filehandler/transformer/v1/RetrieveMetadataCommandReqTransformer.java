/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.transformer.v1;

import com.finra.sample.filehandler.dto.v1.RetrieveMetadataCommandRequest;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import com.finra.sample.filehandler.transformer.common.InNOutTransformer;

/**
 *
 * @author SIGINT-X
 */

@Component("retrieveMetadataReqTransformer")
public class RetrieveMetadataCommandReqTransformer implements InNOutTransformer<RetrieveMetadataCommandRequest, Exchange> {

    @Override
    public RetrieveMetadataCommandRequest transform(Exchange value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
