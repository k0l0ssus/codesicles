package com.tayo.samples.guesstimator.converter;

import com.tayo.samples.guesstimator.dto.Game;

public interface ResponseConverter<T1, T2> {
	
	public T2 convert(Game game, T1 dto);

}
