package com.apress.demo.guesstimator.converter;

import com.apress.demo.guesstimator.dto.Game;

public interface ResponseConverter<T1, T2> {
	
	public T2 convert(Game game, T1 dto);

}
