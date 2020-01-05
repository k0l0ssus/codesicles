package com.apress.demo.guesstimator.dao;

import com.apress.demo.guesstimator.converter.ResponseConverter;
import com.apress.demo.guesstimator.dto.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Queue;


/*
 * Translation layer between RESTful interface and core game functionality
 */
@Singleton
public class GameRequestHandlerImpl {

	@Inject
    GameController gameController;
	@Inject
	@Named("gameStartResponseConverter")
	ResponseConverter<GameStartRequest, GameStartResponse> gameStartResponseConverter; //convert responses to start a new game
	@Inject
	@Named("gameAnswerResponseConverter")
	ResponseConverter<GameAnswerRequest, GameAnswerResponse> gameAnswerResponseConverter; //convert responses to end a game
	@Inject
	@Named("gameGuessResponseConverter")
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
