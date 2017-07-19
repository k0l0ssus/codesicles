/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.transformer.v1;

import com.finra.sample.filehandler.dto.common.FileUploaderConstants;
import com.finra.sample.filehandler.dto.v1.RetrieveMetadataCommandResponse;
import com.finra.sample.filehandler.dto.v1.SendFileCommandMetadata;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import com.finra.sample.filehandler.transformer.common.InNOutTransformer;

/**
 *
 * @author SIGINT-X
 */
@Component("retrieveMetadataResponseTransformer")
public class RetrieveMetadataCommandRespTransformer implements InNOutTransformer<RetrieveMetadataCommandResponse, Exchange> {

    @Override
    public RetrieveMetadataCommandResponse transform(Exchange value) {
        SendFileCommandMetadata[] results = (SendFileCommandMetadata[]) value.getProperty(FileUploaderConstants.FINRA_COMMAND_RESPONSE);
        RetrieveMetadataCommandResponse resp = new RetrieveMetadataCommandResponse();
        if(results != null){
            resp.setMetadaResponses(results);
        }
        
        return resp;
    }
    
}
