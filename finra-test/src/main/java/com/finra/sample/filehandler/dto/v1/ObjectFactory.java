/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.dto.v1;

import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author SIGINT-X
 */
@XmlRegistry
public class ObjectFactory {

    public SendFileCommandRequest createSendFileCommandRequest() {
        return new SendFileCommandRequest();
    }

    public BasicFINRACommand createBasicFINRACommand() {
        return new BasicFINRACommand();
    }

    public RetrieveMetadataCommandRequest createRetrieveMetadataCommandRequest() {
        return new RetrieveMetadataCommandRequest();
    }

    public SendFileCommandResponse createSendFileCommandResponse() {
        return new SendFileCommandResponse();
    }
    
    public RetrieveMetadataCommandResponse createRetrieveMetadataCommandResponse(){
        return new RetrieveMetadataCommandResponse();
    }
}
