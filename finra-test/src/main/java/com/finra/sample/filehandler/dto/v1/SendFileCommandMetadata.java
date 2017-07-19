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
public class SendFileCommandMetadata {
    private String metadataTitle;
    private String metadataValue;

    /**
     * @return the metadataTitle
     */
    public String getMetadataTitle() {
        return metadataTitle;
    }

    /**
     * @param metadataTitle the metadataTitle to set
     */
    public void setMetadataTitle(String metadataTitle) {
        this.metadataTitle = metadataTitle;
    }

    /**
     * @return the metadataValue
     */
    public String getMetadataValue() {
        return metadataValue;
    }

    /**
     * @param metadataValue the metadataValue to set
     */
    public void setMetadataValue(String metadataValue) {
        this.metadataValue = metadataValue;
    }
    
}
