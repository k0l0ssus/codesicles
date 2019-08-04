package com.tayo.samples.guesstimator.converter;

import com.tayo.samples.guesstimator.dto.Game;
import com.tayo.samples.guesstimator.dto.GameGuessRequest;
import com.tayo.samples.guesstimator.dto.GameGuessResponse;


public class GameGuessResponseConverter implements ResponseConverter<GameGuessRequest,GameGuessResponse> {

	@Override
	public GameGuessResponse convert(Game game, GameGuessRequest dto) {
		GameGuessResponse response = new GameGuessResponse();
		response.setGuessStatus(game.getGuesses().getLast().isGuessResult());
		return response;
	}

}
