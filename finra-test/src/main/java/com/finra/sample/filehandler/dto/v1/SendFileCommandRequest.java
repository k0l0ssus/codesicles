/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.dto.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SIGINT-X
 */
@XmlRootElement(name = "sendFileCommandRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class SendFileCommandRequest extends FINRACommand {
    
    @XmlElement
    protected byte[] file;
    
    @XmlElement
    protected SendFileCommandMetadata[] metadata;

    /**
     * @return the file
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * @return the metadata
     */
    public SendFileCommandMetadata[] getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(SendFileCommandMetadata[] metadata) {
        this.metadata = metadata;
    }
    
    
}
