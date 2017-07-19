/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.dto.v1;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author SIGINT-X
 */
@XmlType
public abstract class FINRACommand<T,N> {
    
    public String getCommandName(){
        return this.getClass().getSimpleName();
    }
    
}
