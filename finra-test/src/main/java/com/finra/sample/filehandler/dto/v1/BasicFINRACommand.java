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

@XmlRootElement(name = "basicFINRACommand")
@XmlAccessorType(XmlAccessType.FIELD)
public class BasicFINRACommand {
    
    @XmlAnyElement(lax = true)
    protected FINRACommand command;

    /**
     * @return the command
     */
    public FINRACommand getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(FINRACommand command) {
        this.command = command;
    }
    
}
