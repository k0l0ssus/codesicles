/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.facade.v1;

import com.finra.sample.filehandler.dto.v1.FINRACommand;
import com.finra.sample.filehandler.dto.v1.FINRACommandResponse;
import com.finra.sample.filehandler.service.common.Facade;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author SIGINT-X
 */

@Path("/finrauploader/")
public interface FINRAFacade extends Facade<FINRACommandResponse, FINRACommand> {
    
    @POST
    @Consumes("application/json")
    public FINRACommandResponse execute(FINRACommand command);
    
    
}
