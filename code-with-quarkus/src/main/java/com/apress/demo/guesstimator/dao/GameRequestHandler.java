package com.apress.demo.guesstimator.dao;

import com.apress.demo.guesstimator.dto.GuesstimatorRequestDTO;
import com.apress.demo.guesstimator.dto.GuesstimatorResponseDTO;

public interface GameRequestHandler {
	
	public GuesstimatorResponseDTO handle(GuesstimatorRequestDTO request);

}
