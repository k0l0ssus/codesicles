package com.apress.demo.guesstimator.converter;


import com.apress.demo.guesstimator.dto.Game;
import com.apress.demo.guesstimator.dto.Game.GameStatus;
import com.apress.demo.guesstimator.dto.GameAnswerRequest;
import com.apress.demo.guesstimator.dto.GameAnswerResponse;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("gameAnswerResponseConverter")
public class GameAnswerResponseConverter implements ResponseConverter<GameAnswerRequest,GameAnswerResponse> {

	@Override
	public GameAnswerResponse convert(Game game, GameAnswerRequest dto) {
		GameAnswerResponse response = new GameAnswerResponse();
		response.setYourAnswer(dto.getPlayerAnswer());
		response.setTheCorrectAnswer(game.getAnswer());
		if(game.getGameStatus() == GameStatus.CORRECTLY_ANSWERED) {
			response.setCorrectAnswer(true);
		}else {
			response.setCorrectAnswer(false);
		}
		return response;
	}

}
