package com.apress.demo.quarkus;

import com.apress.demo.guesstimator.dao.GameRequestHandlerImpl;
import com.apress.demo.guesstimator.dto.*;
import com.apress.demo.guesstimator.feed.LeaderBoardBroadcaster;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.util.Queue;

@Path("guess")
@Singleton
public class Guesstimator {

    /**
     * private Sse sse;
     * private SseBroadcaster broadcaster;
     * <p>
     * public Guesstimator(@Context Sse sse) {
     * this.sse = sse;
     * this.broadcaster = sse.newBroadcaster();
     * }
     **/

    @Inject
    GameRequestHandlerImpl handler;

    @Inject
    LeaderBoardBroadcaster leaderboardBroadcaster;

    /**
     * @PostConstruct public void init() {
     * leaderboardBroadcaster.init(broadcaster,sse);
     * }
     **/

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
    /**
     @Path("/game/leaderboard/feed")
     @GET
     @Produces(MediaType.SERVER_SENT_EVENTS) public void leaderBoardListener(@Context SseEventSink eventSink, @Context Sse sseUtil) {
     this.broadcaster.register(eventSink);
     }
     **/
}
