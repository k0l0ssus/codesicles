/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.service.common;

/**
 *
 * @author SIGINT-X
 */
public interface Facade<M,N> {
    
    
    public M execute(N request);
    
}
