package com.apress.demo.guesstimator.util;

import com.apress.demo.guesstimator.dto.Game;
import com.apress.demo.guesstimator.dto.Game.GuessOperator;
import com.apress.demo.guesstimator.dto.GameGuessRequest;

import java.util.concurrent.ThreadLocalRandom;

public class GameUtils {
	/*
	 * generate random longs within a range
	 */
	public static long generateAnswer(long lower, long upper) {
		return ThreadLocalRandom.current().nextLong(lower, upper);
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
