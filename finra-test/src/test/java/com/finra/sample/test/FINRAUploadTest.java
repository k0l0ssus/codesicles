/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.test;

import com.finra.sample.filehandler.dto.v1.BasicFINRACommand;
import com.finra.sample.filehandler.dto.v1.RetrieveMetadataCommandRequest;
import com.finra.sample.filehandler.dto.v1.SendFileCommandMetadata;
import com.finra.sample.filehandler.dto.v1.SendFileCommandRequest;
import java.util.Calendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelTestSupport;
//import org.junit.Test;

/**
 *
 * @author SIGINT-X
 */
public class FINRAUploadTest extends CamelTestSupport {
    
    @EndpointInject(uri = "direct:filedrop")
    ProducerTemplate sender;
    
    //@Test
    public void dropFile() {
        sender.sendBodyAndProperty("Hello world", "uploaded_file_name", "sample.txt");
        
    }
    
    public static void main(String[] args) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance("com.finra.sample.filehandler.dto.v1");
        BasicFINRACommand basicCommand = new BasicFINRACommand();
        RetrieveMetadataCommandRequest req = new RetrieveMetadataCommandRequest();
        
        SendFileCommandRequest command = new SendFileCommandRequest();
        SendFileCommandMetadata[] metadata = new SendFileCommandMetadata[3];
        SendFileCommandMetadata meta1 = new SendFileCommandMetadata();
        meta1.setMetadataTitle("SendDate");
        meta1.setMetadataValue(Calendar.getInstance().getTime().toString());
        SendFileCommandMetadata meta2 = new SendFileCommandMetadata();
        meta2.setMetadataTitle("file_size");
        meta2.setMetadataValue("4000");
        metadata[0] = meta1;
        metadata[1] = meta2;
        command.setMetadata(metadata);
        command.setFile(new String("Setting a file").getBytes());
       // basicCommand.setCommand(command);
        
        req.setMetadataRequests(meta2);
        req.setFileId("fileId");
        basicCommand.setCommand(req);
        ctx.createMarshaller().marshal(basicCommand, System.out);
    }
    
}
