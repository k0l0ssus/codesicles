package com.tayo.samples.guesstimator.converter;


import com.tayo.samples.guesstimator.dto.Game;
import com.tayo.samples.guesstimator.dto.Game.GameStatus;
import com.tayo.samples.guesstimator.dto.GameAnswerRequest;
import com.tayo.samples.guesstimator.dto.GameAnswerResponse;

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
