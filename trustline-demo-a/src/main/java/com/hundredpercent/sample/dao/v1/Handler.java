/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hundredpercent.sample.dao.v1;

/**
 *
 * @author SIGINT-X
 */
public interface Handler<In> {
    void credit(In input) throws Exception;
    
    void debit(In input) throws Exception;
    
}
