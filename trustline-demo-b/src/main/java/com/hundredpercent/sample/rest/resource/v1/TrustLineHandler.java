
package com.hundredpercent.sample.rest.resource.v1;

import com.hundredpercent.sample.dto.v1.TrustLineCreditRequest;
import com.hundredpercent.sample.dto.v1.TrustLineCreditResponse;
import com.hundredpercent.sample.dto.v1.TrustLineDebitRequest;
import com.hundredpercent.sample.dto.v1.TrustLineDebitResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author SIGINT-X
 */

@Path("/trustline/{clientId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TrustLineHandler {
    
    @Path("/credit")
    @POST
    public TrustLineCreditResponse credit(TrustLineCreditRequest req);
    
    @Path("/debit")
    @POST
    public TrustLineDebitResponse debit(TrustLineDebitRequest req);
    
}
