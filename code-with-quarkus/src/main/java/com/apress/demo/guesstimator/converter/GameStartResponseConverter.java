package com.apress.demo.guesstimator.converter;


import com.apress.demo.guesstimator.dto.Game;
import com.apress.demo.guesstimator.dto.GameStartRequest;
import com.apress.demo.guesstimator.dto.GameStartResponse;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("gameStartResponseConverter")
public class GameStartResponseConverter implements ResponseConverter<GameStartRequest,GameStartResponse> {
	
	
	public GameStartResponse convert(Game game, GameStartRequest req) {
		GameStartResponse response = new GameStartResponse();
		response.setGameId(game.getGameId());
		return response;
	}

}
