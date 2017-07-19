/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.dto.v1;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SIGINT-X
 */

@XmlRootElement
public class RetrieveMetadataCommandResponse extends FINRACommandResponse {
    private SendFileCommandMetadata[] metadaResponses;

    /**
     * @return the metadaResponses
     */
    public SendFileCommandMetadata[] getMetadaResponses() {
        return metadaResponses;
    }

    /**
     * @param metadaResponses the metadaResponses to set
     */
    public void setMetadaResponses(SendFileCommandMetadata[] metadaResponses) {
        this.metadaResponses = metadaResponses;
    }
}
