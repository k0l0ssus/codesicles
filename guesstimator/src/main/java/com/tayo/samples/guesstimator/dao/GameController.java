package com.tayo.samples.guesstimator.dao;

import java.util.Collection;

import com.tayo.samples.guesstimator.dto.Game;
import com.tayo.samples.guesstimator.dto.Game.GameGuess;
import com.tayo.samples.guesstimator.dto.GameAnswerRequest;
import com.tayo.samples.guesstimator.dto.GameGuessRequest;
import com.tayo.samples.guesstimator.dto.GameStartRequest;

public interface GameController {
	
	public Game startNewGame(GameStartRequest dto);
	
	public Game checkGuess(GameGuessRequest dto);
	
	public Game endGame(GameAnswerRequest dto);
	
	public Collection getLeaderBoard();

}
