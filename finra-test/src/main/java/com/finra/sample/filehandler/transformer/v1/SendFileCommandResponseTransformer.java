/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.transformer.v1;

import com.finra.sample.filehandler.dto.common.FileUploaderConstants;
import com.finra.sample.filehandler.dto.v1.SendFileCommandResponse;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import com.finra.sample.filehandler.transformer.common.InNOutTransformer;

/**
 *
 * @author SIGINT-X
 */
@Component("sendFileResponseTransformer")
public class SendFileCommandResponseTransformer implements InNOutTransformer<SendFileCommandResponse, Exchange> {

    @Override
    public SendFileCommandResponse transform(Exchange value) {
        SendFileCommandResponse resp = new SendFileCommandResponse();
        resp.setFileID(value.getExchangeId());

        return resp;

    }

}
