package com.tayo.samples.guesstimator.feed;

import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import com.tayo.samples.guesstimator.dto.Game;

/*
 * Provide realtime feed of leaderboard lead updates
 * 
 */
@Singleton
public class LeaderBoardBroadcaster {
	@Context
	private SseEventSink eventSink;

	@Context
	private Sse sseUtil;
	
	private SseBroadcaster broadcaster;

	public void init(SseBroadcaster broadcaster, Sse sseUtil) {
		System.out.println("Initializing broadcaster");
		this.broadcaster = broadcaster;
		this.sseUtil = sseUtil;
	}

	public void broadcast(Game game, String eventType) {
		switch (eventType) {
		case "NEW_LEADER_BY_GUESSES":
			try {
				OutboundSseEvent sseEvent = sseUtil
						.newEvent(game.getGamePlayer() + " just took the lead with " + game.getGuesses().size()+" guesses!");
				broadcaster.broadcast(sseEvent);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
	}

}
