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
public class SendFileCommandResponse extends FINRACommandResponse {
    private String fileID;
   
    /**
     * @return the fileID
     */
    public String getFileID() {
        return fileID;
    }

    /**
     * @param fileID the fileID to set
     */
    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

   
}
