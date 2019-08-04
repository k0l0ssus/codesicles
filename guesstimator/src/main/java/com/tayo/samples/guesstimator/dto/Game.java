package com.tayo.samples.guesstimator.dto;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;

public class Game implements Serializable, Comparable<Game> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1511858129071664550L;

	private Instant started;

	private Instant ended;

	private String gameId;

	private GameStatus gameStatus = GameStatus.INCORRECTLY_ANSWERED;

	private LinkedList<GameGuess> guesses;

	public Game() {
		started = Instant.now();
		guesses = new LinkedList<>();
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	private String gamePlayer;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGamePlayer() {
		return gamePlayer;
	}

	public void setGamePlayer(String gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	private long answer;

	public long getAnswer() {
		return answer;
	}

	public void setAnswer(long answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (answer ^ (answer >>> 32));
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((gamePlayer == null) ? 0 : gamePlayer.hashCode());
		result = prime * result + ((gameStatus == null) ? 0 : gameStatus.hashCode());
		result = prime * result + ((guesses == null) ? 0 : guesses.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (answer != other.answer)
			return false;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		if (gamePlayer == null) {
			if (other.gamePlayer != null)
				return false;
		} else if (!gamePlayer.equals(other.gamePlayer))
			return false;
		if (gameStatus != other.gameStatus)
			return false;
		if (guesses == null) {
			if (other.guesses != null)
				return false;
		} else if (!guesses.equals(other.guesses))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", gameStatus=" + gameStatus + ", guess=" + guesses + ", gamePlayer="
				+ gamePlayer + ", answer=" + answer + "]";
	}

	class Guess {
		long value;

		public long getValue() {
			return value;
		}

		public void setValue(long value) {
			this.value = value;
		}
	}

	public enum GameStatus {
		CORRECTLY_ANSWERED, INCORRECTLY_ANSWERED;
	}

	public enum GuessOperator {

		GREATER_THAN("greaterThan"), LESS_THAN("lessThan"), EVEN("even"), ODD("odd");

		private final String operator;

		GuessOperator(String op) {
			this.operator = op;
		}

		public String operator() {
			return operator;
		}
		
		public static GuessOperator findValue(String op) {
			GuessOperator[] operators = GuessOperator.values();
			for(GuessOperator operator: operators) {
				if(operator.operator.equals(op)) {
					return operator;
				}
			}
			
			return null;
		}
	}

	public class GameGuess implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7571016460913364900L;
		private GuessOperator op;
		private long guessValue;
		private boolean guessResult;

		@Override
		public String toString() {
			return "GameGuess [op=" + op + ", guessValue=" + guessValue + ", guessResult=" + guessResult + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + (guessResult ? 1231 : 1237);
			result = prime * result + (int) (guessValue ^ (guessValue >>> 32));
			result = prime * result + ((op == null) ? 0 : op.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GameGuess other = (GameGuess) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (guessResult != other.guessResult)
				return false;
			if (guessValue != other.guessValue)
				return false;
			if (op != other.op)
				return false;
			return true;
		}

		public GuessOperator getOp() {
			return op;
		}

		public void setOp(GuessOperator op) {
			this.op = op;
		}

		public long getGuessValue() {
			return guessValue;
		}

		public void setGuessValue(long guessValue) {
			this.guessValue = guessValue;
		}

		private Game getEnclosingInstance() {
			return Game.this;
		}

		public boolean isGuessResult() {
			return guessResult;
		}

		public void setGuessResult(boolean guessResult) {
			this.guessResult = guessResult;
		}
	}

	public Instant getStarted() {
		return started;
	}

	public void setStarted(Instant started) {
		this.started = started;
	}

	public Instant getEnded() {
		return ended;
	}

	public void setEnded(Instant ended) {
		this.ended = ended;
	}

	public LinkedList<GameGuess> getGuesses() {
		return guesses;
	}

	public void setGuesses(LinkedList<GameGuess> guesses) {
		this.guesses = guesses;
	}

	@Override
	public int compareTo(Game o) {
		
		if(o.getGuesses().size() == 0 )
		
		/**
		 * if it took fewer guesses and a less time
		 */
		if (this.getGuesses().size() < o.getGuesses().size() && ((Duration.between(started, ended).toMillis() < Duration
				.between(o.started, o.getEnded()).toMillis()))) {
			return -1;
		} /**
			 * if it took fewer guesses
			 **/
		else if (this.getGuesses().size() < o.getGuesses().size()) {
			return -1;
		}
		/**
		 * If it took less time
		 */
		else if (((Duration.between(started, ended).toMillis() < Duration.between(o.started, o.getEnded())
				.toMillis()))) {
			return -1;
		} else if (this.getGuesses().size() == o.getGuesses().size()) {
			return 0;
		}

		return 1;
	}
}
