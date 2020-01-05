package com.apress.demo.guesstimator.dao;

import com.apress.demo.guesstimator.dto.Game;
import com.apress.demo.guesstimator.dto.Game.GameGuess;
import com.apress.demo.guesstimator.dto.Game.GameStatus;
import com.apress.demo.guesstimator.dto.Game.GuessOperator;
import com.apress.demo.guesstimator.dto.GameAnswerRequest;
import com.apress.demo.guesstimator.dto.GameGuessRequest;
import com.apress.demo.guesstimator.dto.GameStartRequest;
import com.apress.demo.guesstimator.feed.LeaderBoardBroadcaster;
import com.apress.demo.guesstimator.util.GameUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Logger;

/*
 * Handle core game functionality
 * 
 */

@Singleton
public class GameControllerImpl implements GameController {
	
	private static final String NEW_LEADER_UPDATE = "NEW_LEADER_BY_GUESSES";
	
	@Inject
	LeaderBoardBroadcaster broadcaster;

	private long answerLowerLimit = 1;
	private long answerUpperLimit = 100;

	private Map<String, Game> games = new ConcurrentHashMap<>();
	private Queue<Game> leaderBoard = new PriorityBlockingQueue<>();

	@Override
	public Game startNewGame(GameStartRequest gameStartReq) {
		Game game = new Game();
		if (gameStartReq.getPlayerId() != null && !gameStartReq.getPlayerId().trim().equals("")) {
			game.setGameId(UUID.randomUUID().toString());
			game.setGamePlayer(gameStartReq.getPlayerId());
			long answer = GameUtils.generateAnswer(answerLowerLimit, answerUpperLimit);
			Logger.getAnonymousLogger().info("The answer for game " + game.getGameId() + ": " + answer);
			game.setAnswer(answer);
		} else {
			throw new IllegalArgumentException("We can't start a new game with an unnamed player");
		}
		games.put(game.getGameId(), game);
		return game;
	}

	@Override
	public Game checkGuess(GameGuessRequest dto) {
		GameGuess guess = null;
		Game game = games.get(dto.getGameId());
		System.out.println("About to guess");
		if (game != null) {
			guess = game.new GameGuess();
			boolean result = GameUtils.checkGuess(dto, game);
			guess.setGuessValue(dto.getGuessValue());
			guess.setOp(GuessOperator.findValue(dto.getGuessOperator()));
			guess.setGuessResult(result);
			game.getGuesses().add(guess);
		}
		return game;
	}

	@Override
	public Game endGame(GameAnswerRequest dto) {
		Game game = games.get(dto.getGameId());
		try {
		if (game != null) {
			game.setEnded(Instant.now());
			if (game.getAnswer() == dto.getPlayerAnswer()) {
				leaderBoard.add(game);
				game.setGameStatus(GameStatus.CORRECTLY_ANSWERED);
			} else {
				game.setGameStatus(GameStatus.INCORRECTLY_ANSWERED);
			}
			
			if (leaderBoard.peek() != null && leaderBoard.peek().equals(game)) {
				Logger.getAnonymousLogger().info("We have a new leader");
				leaderboardUpdate(game,NEW_LEADER_UPDATE);
			}
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		return game;
	}

	@Override
	public Collection<Game> getLeaderBoard() {
		// TODO Auto-generated method stub
		return this.getLeaderBoard();
	}

	public void leaderboardUpdate(Game game, String eventType) {
		broadcaster.broadcast(game, eventType);
	}

}
