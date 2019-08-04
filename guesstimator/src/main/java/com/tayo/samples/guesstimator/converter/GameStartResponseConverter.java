package com.tayo.samples.guesstimator.converter;


import com.tayo.samples.guesstimator.dto.Game;
import com.tayo.samples.guesstimator.dto.GameStartRequest;
import com.tayo.samples.guesstimator.dto.GameStartResponse;

public class GameStartResponseConverter implements ResponseConverter<GameStartRequest,GameStartResponse> {
	
	
	public GameStartResponse convert(Game game,GameStartRequest req) {
		GameStartResponse response = new GameStartResponse();
		response.setGameId(game.getGameId());
		return response;
	}

}
