package com.tayo.samples.guesstimator.util;

import java.util.concurrent.ThreadLocalRandom;

import com.tayo.samples.guesstimator.dto.Game;
import com.tayo.samples.guesstimator.dto.Game.GuessOperator;
import com.tayo.samples.guesstimator.dto.GameGuessRequest;

public class GameUtils {
	/*
	 * generate random longs within a range
	 */
	public static long generateAnswer(long lower, long upper) {
		long rand = ThreadLocalRandom.current().nextLong(lower, upper);
		return rand;
	}

	/*
	 * check incoming guesses for correctness
	 */
	public static boolean checkGuess(GameGuessRequest guess, Game game) {
		GuessOperator operator = GuessOperator.findValue(guess.getGuessOperator());
		if(operator == null) {
			throw new IllegalArgumentException("invalid guess operator");
		}
		switch (operator) {
		case GREATER_THAN:
			return game.getAnswer() > guess.getGuessValue();
		case LESS_THAN:
			return game.getAnswer() < guess.getGuessValue();
		case EVEN:
			return (game.getAnswer()%2) == 0;
		case ODD:
			return (game.getAnswer()%2) != 0;
		}

		return false;
	}

}
