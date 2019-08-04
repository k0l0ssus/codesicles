package com.tayo.samples.guesstimator.dao;

import java.util.Queue;

import javax.inject.Inject;
import javax.inject.Named;

import com.tayo.samples.guesstimator.converter.ResponseConverter;
import com.tayo.samples.guesstimator.dto.Game;
import com.tayo.samples.guesstimator.dto.GameAnswerRequest;
import com.tayo.samples.guesstimator.dto.GameAnswerResponse;
import com.tayo.samples.guesstimator.dto.GameGuessRequest;
import com.tayo.samples.guesstimator.dto.GameGuessResponse;
import com.tayo.samples.guesstimator.dto.GameStartRequest;
import com.tayo.samples.guesstimator.dto.GameStartResponse;


/*
 * Translation layer between RESTful interface and core game functionality
 */
public class GameRequestHandlerImpl {

	@Inject
	GameController gameController;
	@Inject
	ResponseConverter<GameStartRequest, GameStartResponse> gameStartResponseConverter; //convert responses to start a new game
	@Inject
	ResponseConverter<GameAnswerRequest, GameAnswerResponse> gameAnswerResponseConverter; //convert responses to end a game
	@Inject
	ResponseConverter<GameGuessRequest, GameGuessResponse> gameGuessResponseConverter; //convert responses to guess the answer in a game

	public GameAnswerResponse handle(GameAnswerRequest req) {
		Game game = gameController.endGame(req);
		return gameAnswerResponseConverter.convert(game, req);
	}

	public GameGuessResponse handle(GameGuessRequest req) {
		Game game = gameController.checkGuess(req);
		return gameGuessResponseConverter.convert(game, req);
	}

	public GameStartResponse handle(GameStartRequest req) {
		Game game = gameController.startNewGame(req);
		return gameStartResponseConverter.convert(game, req);
	}

	public Queue<Game> getLeaderBoard() {
		return (Queue<Game>) gameController.getLeaderBoard();
	}

}
