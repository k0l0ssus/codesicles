/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.storage.v1;

import com.finra.sample.filehandler.dto.common.FileUploaderConstants;
import com.finra.sample.filehandler.dto.v1.BasicFINRACommand;
import com.finra.sample.filehandler.dto.v1.RetrieveMetadataCommandRequest;
import com.finra.sample.filehandler.dto.v1.SendFileCommandMetadata;
import com.finra.sample.filehandler.dto.v1.SendFileCommandRequest;
import com.finra.sample.filehandler.storage.common.Retrieval;
import com.finra.sample.filehandler.storage.common.Storage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component("basicStorageImpl")

public class BasicStorageImpl implements Storage<String, Exchange>, Retrieval<SendFileCommandMetadata[], Exchange> {

    private Map<String, SendFileCommandMetadata[]> store;

    public BasicStorageImpl() {
        store = new ConcurrentHashMap<>();
    }

    @Override
    public String store(Exchange payload) {
        SendFileCommandRequest req = (SendFileCommandRequest) payload.getProperty(FileUploaderConstants.FINRA_COMMAND);
        store.put(payload.getExchangeId(), req.getMetadata());
        return payload.getExchangeId();
    }

    @Override
    public SendFileCommandMetadata[] retrieve(Exchange queryParam) {
        BasicFINRACommand parentReq = queryParam.getIn().getBody(BasicFINRACommand.class);
        RetrieveMetadataCommandRequest req = (RetrieveMetadataCommandRequest) parentReq.getCommand();
        String fileId = req.getFileId();
        SendFileCommandMetadata[] results = new SendFileCommandMetadata[1];
        SendFileCommandMetadata[] probableResults = store.get(fileId);

        if (probableResults != null && probableResults.length > 0) {
            List<SendFileCommandMetadata> fileCommand = new ArrayList<>();
            SendFileCommandMetadata[] filteredResults = fileCommand.toArray(results);
            queryParam.setProperty(FileUploaderConstants.FINRA_COMMAND_RESPONSE, filteredResults);
            return filteredResults;
        }
        return results;
    }

}
