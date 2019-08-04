package com.tayo.samples.guesstimator.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class GameGuessRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5486877146112719560L;
	private long guessValue;
	
	@NotBlank(message="Please provide one of the following guess operators: greaterThan, lessThan")
	private String guessOperator;
	
	@NotBlank(message="Please supply a valid gameId")
	private String gameId;
	
	public long getGuessValue() {
		return guessValue;
	}
	public void setGuessValue(long guessValue) {
		this.guessValue = guessValue;
	}
	public String getGuessOperator() {
		return guessOperator;
	}
	public void setGuessOperator(String guessOperator) {
		this.guessOperator = guessOperator;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((guessOperator == null) ? 0 : guessOperator.hashCode());
		result = prime * result + (int) (guessValue ^ (guessValue >>> 32));
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
		GameGuessRequest other = (GameGuessRequest) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		if (guessOperator == null) {
			if (other.guessOperator != null)
				return false;
		} else if (!guessOperator.equals(other.guessOperator))
			return false;
		if (guessValue != other.guessValue)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GameGuessRequest [guessValue=" + guessValue + ", guessOperator=" + guessOperator + ", gameId=" + gameId
				+ "]";
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	

}
