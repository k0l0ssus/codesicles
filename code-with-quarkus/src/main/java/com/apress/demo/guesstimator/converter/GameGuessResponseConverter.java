package com.apress.demo.guesstimator.converter;

import com.apress.demo.guesstimator.dto.Game;
import com.apress.demo.guesstimator.dto.GameGuessRequest;
import com.apress.demo.guesstimator.dto.GameGuessResponse;

import javax.inject.Named;
import javax.inject.Singleton;


@Singleton
@Named("gameGuessResponseConverter")
public class GameGuessResponseConverter implements ResponseConverter<GameGuessRequest, GameGuessResponse> {

	@Override
	public GameGuessResponse convert(Game game, GameGuessRequest dto) {
		GameGuessResponse response = new GameGuessResponse();
		response.setGuessStatus(game.getGuesses().getLast().isGuessResult());
		return response;
	}

}
