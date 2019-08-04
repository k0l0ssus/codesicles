package com.tayo.samples.guesstimator.dto;

import java.io.Serializable;

public class GameAnswerResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2257830638646292839L;
	private boolean isCorrectAnswer;
	private long yourAnswer;
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
	public long getYourAnswer() {
		return yourAnswer;
	}
	public void setYourAnswer(long yourAnswer) {
		this.yourAnswer = yourAnswer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isCorrectAnswer ? 1231 : 1237);
		result = prime * result + (int) (theCorrectAnswer ^ (theCorrectAnswer >>> 32));
		result = prime * result + (int) (yourAnswer ^ (yourAnswer >>> 32));
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
		if (isCorrectAnswer != other.isCorrectAnswer)
			return false;
		if (theCorrectAnswer != other.theCorrectAnswer)
			return false;
		if (yourAnswer != other.yourAnswer)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GameAnswerResponse [isCorrectAnswer=" + isCorrectAnswer + ", yourAnswer=" + yourAnswer
				+ ", theCorrectAnswer=" + theCorrectAnswer + "]";
	}
	
	

}
