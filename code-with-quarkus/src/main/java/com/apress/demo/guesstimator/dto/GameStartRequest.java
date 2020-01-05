package com.apress.demo.guesstimator.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class GameStartRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8981556964853090576L;
	@NotBlank(message = "Please provide a player name")
	@Size(min = 2,message = "Player name can't be less than 2 characters")
	private String playerId;

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerId == null) ? 0 : playerId.hashCode());
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
		GameStartRequest other = (GameStartRequest) obj;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameStartRequest [playerId=" + playerId + "]";
	}

}
