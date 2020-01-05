package com.apress.demo.guesstimator.dao;

import com.apress.demo.guesstimator.dto.Game;
import com.apress.demo.guesstimator.dto.GameAnswerRequest;
import com.apress.demo.guesstimator.dto.GameGuessRequest;
import com.apress.demo.guesstimator.dto.GameStartRequest;

import java.util.Collection;

public interface GameController {
	
	public Game startNewGame(GameStartRequest dto);
	
	public Game checkGuess(GameGuessRequest dto);
	
	public Game endGame(GameAnswerRequest dto);
	
	public Collection getLeaderBoard();

}
