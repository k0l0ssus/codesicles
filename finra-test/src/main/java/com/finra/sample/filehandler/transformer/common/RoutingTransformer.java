/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.transformer.common;

import com.finra.sample.filehandler.dto.common.FileUploaderConstants;
import com.finra.sample.filehandler.dto.v1.BasicFINRACommand;
import com.finra.sample.filehandler.dto.v1.FINRACommand;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component("routingTransformer")
public class RoutingTransformer implements InOnlyTransformer<Exchange> {

    @Override
    public void transform(Exchange value) {
        BasicFINRACommand commandParent = value.getIn().getBody(BasicFINRACommand.class);
        FINRACommand command = commandParent.getCommand();
        value.setProperty("commandType", command.getCommandName());
        value.setProperty(FileUploaderConstants.FINRA_COMMAND, command);

    }

}
