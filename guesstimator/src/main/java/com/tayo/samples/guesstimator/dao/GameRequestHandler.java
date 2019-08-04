package com.tayo.samples.guesstimator.dao;

import com.tayo.samples.guesstimator.dto.GuesstimatorRequestDTO;
import com.tayo.samples.guesstimator.dto.GuesstimatorResponseDTO;

public interface GameRequestHandler {
	
	public GuesstimatorResponseDTO handle(GuesstimatorRequestDTO request);

}
