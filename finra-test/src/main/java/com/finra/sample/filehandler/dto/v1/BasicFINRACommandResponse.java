/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.dto.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SIGINT-X
 */
@XmlRootElement(name = "basicFINRACommandResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class BasicFINRACommandResponse {
    
    private String responseMessage;
    
    @XmlAnyElement(lax = true)
    private FINRACommandResponse commandResponse;

    /**
     * @return the responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @param responseMessage the responseMessage to set
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * @return the commandResponse
     */
    public FINRACommandResponse getCommandResponse() {
        return commandResponse;
    }

    /**
     * @param commandResponse the commandResponse to set
     */
    public void setCommandResponse(FINRACommandResponse commandResponse) {
        this.commandResponse = commandResponse;
    }
    
}
