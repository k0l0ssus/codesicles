/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.transformer.v1;

import com.finra.sample.filehandler.dto.v1.FINRACommand;
import com.finra.sample.filehandler.dto.common.FileUploaderConstants;
import com.finra.sample.filehandler.dto.v1.SendFileCommandRequest;
import java.util.UUID;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import com.finra.sample.filehandler.transformer.common.InNOutTransformer;

/**
 *
 * @author SIGINT-X
 */

@Component("sendFileRequestTransformer")
public class SendFileCommandRequestTransformer implements InNOutTransformer<SendFileCommandRequest, Exchange> {

    @Override
    public SendFileCommandRequest transform(Exchange value) {
        FINRACommand req = value.getIn().getBody(FINRACommand.class);
        SendFileCommandRequest command = null;
        if (req.getCommandName().equals("SendFileCommandRequest")) {
            command = (SendFileCommandRequest) req;
            String name = UUID.randomUUID().toString();
            value.setProperty(FileUploaderConstants.FILE_UPLOAD_COMMAND_PAYLOAD, command.getFile());
            value.getIn().setHeader(Exchange.FILE_NAME, name);
            value.setProperty(FileUploaderConstants.FILE_UPLOAD_COMMAND_FILE_NAME, name);
            value.setProperty(FileUploaderConstants.FILE_UPLOAD_COMMAND_METADATA, command.getMetadata());
        }
        
        return command;
    }

}
