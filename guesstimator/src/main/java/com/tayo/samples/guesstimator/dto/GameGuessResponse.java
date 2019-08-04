package com.tayo.samples.guesstimator.dto;

import java.io.Serializable;

public class GameGuessResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean guessStatus;
	
	@Override
	public String toString() {
		return "GameGuessResponse [guessStatus=" + guessStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (guessStatus ? 1231 : 1237);
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
		GameGuessResponse other = (GameGuessResponse) obj;
		if (guessStatus != other.guessStatus)
			return false;
		return true;
	}

	public boolean isGuessStatus() {
		return guessStatus;
	}

	public void setGuessStatus(boolean guessStatus) {
		this.guessStatus = guessStatus;
	}

}
