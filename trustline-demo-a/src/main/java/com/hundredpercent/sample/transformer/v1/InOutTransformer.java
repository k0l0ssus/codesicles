/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hundredpercent.sample.transformer.v1;

/**
 *
 * @author SIGINT-X
 */
public interface InOutTransformer<In,Out> {
    
     Out transform(In input);
    
}
