package com.me.samples.guesstimate.dto;

import java.io.Serializable;

public class GameStartResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7663261460490537920L;
	private String gameId;
	
	
	@Override
	public String toString() {
		return "GameStartResponse [gameId=" + gameId + "]";
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		GameStartResponse other = (GameStartResponse) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		return true;
	}


}
