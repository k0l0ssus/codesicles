package com.tayo.samples.guesstimator.resource;

import java.util.Queue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import com.tayo.samples.guesstimator.dao.GameRequestHandlerImpl;
import com.tayo.samples.guesstimator.dto.Game;
import com.tayo.samples.guesstimator.dto.GameAnswerRequest;
import com.tayo.samples.guesstimator.dto.GameAnswerResponse;
import com.tayo.samples.guesstimator.dto.GameGuessRequest;
import com.tayo.samples.guesstimator.dto.GameGuessResponse;
import com.tayo.samples.guesstimator.dto.GameStartRequest;
import com.tayo.samples.guesstimator.dto.GameStartResponse;
import com.tayo.samples.guesstimator.feed.LeaderBoardBroadcaster;

@Path("guess")
@Singleton
public class Guesstimator {
	
	private Sse sse;
	private SseBroadcaster broadcaster;
	
	public Guesstimator(@Context Sse sse) {
		this.sse = sse;
		this.broadcaster = sse.newBroadcaster();
	}

	@Inject
	GameRequestHandlerImpl handler;
	
	@Inject
	LeaderBoardBroadcaster leaderboardBroadcaster;
	
	@PostConstruct
	public void init() {
		leaderboardBroadcaster.init(broadcaster,sse);
	}

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

	@GET
	@Path("/game/leaderboard")
	@Produces(MediaType.APPLICATION_JSON)
	public Queue<Game> leaderBoard() {
		return handler.getLeaderBoard();
	}
	
	@Path("/game/leaderboard/feed")
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void leaderBoardListener(@Context SseEventSink eventSink, @Context Sse sseUtil) {
		this.broadcaster.register(eventSink);
	}
}
