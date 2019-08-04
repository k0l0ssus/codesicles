

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.tayo.samples.guesstimator.dao.GameRequestHandlerImpl;
import com.tayo.samples.guesstimator.dto.GameAnswerRequest;
import com.tayo.samples.guesstimator.dto.GameAnswerResponse;
import com.tayo.samples.guesstimator.dto.GameGuessRequest;
import com.tayo.samples.guesstimator.dto.GameGuessResponse;
import com.tayo.samples.guesstimator.dto.GameStartRequest;
import com.tayo.samples.guesstimator.dto.GameStartResponse;

@Path("guess")
@Singleton
public class Guesstimator {

	@Inject
	GameRequestHandlerImpl handler;
	
	
	@POST
	@Path("/game/start")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GameStartResponse startGame(@Valid GameStartRequest req) {
		return handler.handle(req);
	}
	
	
	@POST
	@Path("/game/{gameId}/guess-answer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GameGuessResponse guess(@Valid GameGuessRequest req) {
		return handler.handle(req);
	}
	
	@POST
	@Path("/game/{gameId}/answer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GameAnswerResponse answer(@Valid GameAnswerRequest req) {
		return handler.handle(req);
		
	}
}
