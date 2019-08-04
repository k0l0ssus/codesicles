package com.me.samples.guesstimate.dto;

import java.io.Serializable;


public class GameAnswerResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2257830638646292839L;
	private boolean isCorrectAnswer;
	private String yourAnswer;
	private long theCorrectAnswer;
	
	public boolean isCorrectAnswer() {
		return isCorrectAnswer;
	}
	public void setCorrectAnswer(boolean isCorrectAnswer) {
		this.isCorrectAnswer = isCorrectAnswer;
	}
	public long getTheCorrectAnswer() {
		return theCorrectAnswer;
	}
	public void setTheCorrectAnswer(long answer) {
		this.theCorrectAnswer = answer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (theCorrectAnswer ^ (theCorrectAnswer >>> 32));
		result = prime * result + (isCorrectAnswer ? 1231 : 1237);
		result = prime * result + ((yourAnswer == null) ? 0 : yourAnswer.hashCode());
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
		GameAnswerResponse other = (GameAnswerResponse) obj;
		if (theCorrectAnswer != other.theCorrectAnswer)
			return false;
		if (isCorrectAnswer != other.isCorrectAnswer)
			return false;
		if (yourAnswer == null) {
			if (other.yourAnswer != null)
				return false;
		} else if (!yourAnswer.equals(other.yourAnswer))
			return false;
		return true;
	}
	public String getYourAnswer() {
		return yourAnswer;
	}
	public void setYourAnswer(String yourAnswer) {
		this.yourAnswer = yourAnswer;
	}

}
