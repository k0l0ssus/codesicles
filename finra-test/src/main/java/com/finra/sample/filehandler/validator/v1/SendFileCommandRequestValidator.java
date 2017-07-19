/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.validator.v1;

import com.finra.sample.filehandler.dto.v1.BasicFINRACommand;
import com.finra.sample.filehandler.dto.v1.SendFileCommandRequest;
import com.finra.sample.filehandler.validator.common.Validator;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component("fileCommandValidator")
public class SendFileCommandRequestValidator implements Validator<Exchange> {
    
    @Override
    public void validate(Exchange value) {
        Logger.getAnonymousLogger().info(value.getIn().getBody().toString());
        BasicFINRACommand parentReq = value.getIn().getBody(BasicFINRACommand.class);
        SendFileCommandRequest req = (SendFileCommandRequest) parentReq.getCommand();
        if (req.getFile() == null || req.getMetadata().length==0) {
            throw new IllegalArgumentException("No file Supplied");
        }
    }
    
}
