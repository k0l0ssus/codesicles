package com.me.samples.guesstimate.dto;

import java.io.Serializable;


public class GameAnswerRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5201701182034407737L;
	
	private long playerAnswer;
	
	private String gameId;

	public long getPlayerAnswer() {
		return playerAnswer;
	}

	public void setPlayerAnswer(long answer) {
		this.playerAnswer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (playerAnswer ^ (playerAnswer >>> 32));
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
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
		GameAnswerRequest other = (GameAnswerRequest) obj;
		if (playerAnswer != other.playerAnswer)
			return false;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameAnswerRequest [answer=" + playerAnswer + ", gameId=" + gameId + "]";
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

}
