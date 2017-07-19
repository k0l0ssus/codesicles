/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.validator.v1;

import com.finra.sample.filehandler.dto.common.FileUploaderConstants;
import com.finra.sample.filehandler.dto.v1.RetrieveMetadataCommandRequest;
import com.finra.sample.filehandler.validator.common.Validator;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author SIGINT-X
 */
@Component("retrieveMetadataValidator")
public class RetrieveMetadataCommandReqValidator implements Validator<Exchange> {

    @Override
    public void validate(Exchange value) {
        RetrieveMetadataCommandRequest req = (RetrieveMetadataCommandRequest) value.getProperty(FileUploaderConstants.FINRA_COMMAND);
        if (req.getFileId() == null || StringUtils.isEmpty(req.getFileId())) {
            throw new IllegalArgumentException("Invalid file ID supplied");
        }
    }

}
