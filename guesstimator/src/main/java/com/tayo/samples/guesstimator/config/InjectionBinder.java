package com.tayo.samples.guesstimator.config;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.tayo.samples.guesstimator.converter.GameAnswerResponseConverter;
import com.tayo.samples.guesstimator.converter.GameGuessResponseConverter;
import com.tayo.samples.guesstimator.converter.GameStartResponseConverter;
import com.tayo.samples.guesstimator.converter.ResponseConverter;
import com.tayo.samples.guesstimator.dao.GameController;
import com.tayo.samples.guesstimator.dao.GameControllerImpl;
import com.tayo.samples.guesstimator.dao.GameRequestHandlerImpl;
import com.tayo.samples.guesstimator.dto.GameAnswerRequest;
import com.tayo.samples.guesstimator.dto.GameAnswerResponse;
import com.tayo.samples.guesstimator.dto.GameGuessRequest;
import com.tayo.samples.guesstimator.dto.GameGuessResponse;
import com.tayo.samples.guesstimator.dto.GameStartRequest;
import com.tayo.samples.guesstimator.dto.GameStartResponse;
import com.tayo.samples.guesstimator.feed.LeaderBoardBroadcaster;

public class InjectionBinder extends AbstractBinder {

	@Override
	protected void configure() {
		LeaderBoardBroadcaster broadcaster = new LeaderBoardBroadcaster();
		bind(broadcaster).to(LeaderBoardBroadcaster.class);
		bind(GameControllerImpl.class).to(GameController.class);
		bind(GameRequestHandlerImpl.class).to(GameRequestHandlerImpl.class);
		bind(GameAnswerResponseConverter.class).to(new TypeLiteral<ResponseConverter<GameAnswerRequest,GameAnswerResponse>>(){});
		bind(GameGuessResponseConverter.class).to(new TypeLiteral<ResponseConverter<GameGuessRequest,GameGuessResponse>>(){});
		bind(GameStartResponseConverter.class).to(new TypeLiteral<ResponseConverter<GameStartRequest,GameStartResponse>>(){});

	}

}
