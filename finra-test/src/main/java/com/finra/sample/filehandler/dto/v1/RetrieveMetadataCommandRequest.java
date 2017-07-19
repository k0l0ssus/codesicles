/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.dto.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SIGINT-X
 */
@XmlRootElement(name="retrieveMetadataCommandRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class RetrieveMetadataCommandRequest extends FINRACommand {
   protected String fileId; 

   protected SendFileCommandMetadata metadataRequest;

    /**
     * @return the metadataRequests
     */
    public SendFileCommandMetadata getMetadataRequests() {
        return metadataRequest;
    }

    /**
     * @param metadataRequests the metadataRequests to set
     */
    public void setMetadataRequests(SendFileCommandMetadata metadataRequest) {
        this.metadataRequest = metadataRequest;
    }

    /**
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

}
