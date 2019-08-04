package com.me.samples.guesstimate;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.SseEventSource;

import org.glassfish.jersey.logging.LoggingFeature;

import com.me.samples.guesstimate.dto.GameAnswerRequest;
import com.me.samples.guesstimate.dto.GameAnswerResponse;
import com.me.samples.guesstimate.dto.GameGuessRequest;
import com.me.samples.guesstimate.dto.GameGuessResponse;
import com.me.samples.guesstimate.dto.GameStartRequest;
import com.me.samples.guesstimate.dto.GameStartResponse;

public final class App {
	private final Client client = ClientBuilder.newClient();
	private final Client sseClient = ClientBuilder.newClient();
	private int runningGames;
	private final Logger logger = Logger.getLogger("Guestimate Client");
	private final String START_GUESSTIMATE_GAME_TARGET = "http://localhost:8080/guestimate/guess/game/start";
	private final String END_GUESSTIMATE_TARGET = "http://localhost:8080/guestimate/guess/game/{gameId}/answer";
	private final String GUESS_TARGET = "http://localhost:8080/guestimate/guess/game/{gameId}/guess-answer";
	private final String LEADERBOARD_UPDATES = "http://localhost:8080/guestimate/guess/game/leaderboard/feed";
	private String playerId;
	private SseEventSource leaderboard;
	private String gameId;
	private final Scanner inputHandler = new Scanner(System.in);
	private String currentCommand = "";

	private App() {
	}

	public void preliminaries() {
		do {
			System.out.println(
					"Welcome to Guesstimate!\n" + "Please enter your name to play the game; minimum is 3 characters");
			playerId = inputHandler.nextLine();
		} while (playerId == null && playerId.trim().equals("") && playerId.length() < 3);

	}

	public void gameplay() {
		do {
			System.out.println(playerId + "," + "please start by entering 'N' to begin a new game or 'Q' to quit:");
			currentCommand = inputHandler.nextLine();
		} while (currentCommand.equals(""));

		try {
			handleCommands(currentCommand, 0);
		} catch (Exception e) {
			logger.info("Something has gone wrong: " + e.getMessage());
		}

		do {
			System.out.println("With your new game, you can either guess the answer \nor, "
					+ "guess the range of the answer by supplying the keywords: 'lessThan','odd','even' and 'greaterThan' \n"
					+ "for example: 'greaterThan 6', 'lessThan 35', 'odd' or 'even'. Then you can supply an answer by entering 'A 15'");
			currentCommand = inputHandler.nextLine();
			String[] commands = currentCommand.split(" ");
			if (commands.length == 2) {
				try {
					handleCommands(commands[0], Long.parseLong(commands[1]));

				} catch (NumberFormatException e) {
					logger.warning("You supplied an invalid numerical value \n");
					e.printStackTrace();
				} catch (Exception e) {
					logger.warning("Something's gone wrong: " + e.getMessage());
					e.printStackTrace();
				}
			} else if (commands.length == 1) {
				try {
					handleCommands(commands[0], 0);
				} catch (Exception e) {
					logger.warning("Something's gone wrong: " + e.getMessage());
					e.printStackTrace();
				}
			} else {
				logger.info("You offered an invalid number of command parameters;try again:\n");
			}
			currentCommand = "";
		} while (currentCommand.equals(""));

	}

	public static void main(String[] args) {
		App app = new App();
		app.preliminaries();
		app.gameplay();

	}

	public void handleCommands(String command, long value) throws Exception {

		switch (command) {

		case "N":
			if (runningGames == 0) {
				++runningGames;
				GameStartResponse response = startGame();
				gameId = response.getGameId();
				feedUpdate();
				break;
			} else {
				logger.info("You have a running game, you can't start a new one until it's finished");
			}

		case "Q":
			client.close();
			sseClient.close();
			System.exit(0);
			break;

		case "A":
			if (runningGames == 1) {
				GameAnswerResponse answerResponse = answer(value);
				runningGames = 0;
				if (answerResponse.isCorrectAnswer()) {
					logger.info("Brilliant! You've correctly guessed the answer! "
							+ answerResponse.getTheCorrectAnswer() + "\n Goodbye!");
				} else {
					logger.info("Ahh nuts! You just missed the correct answer: " + answerResponse.getTheCorrectAnswer()
							+ "; your answer was: " + answerResponse.getYourAnswer());
				}
			} else {
				logger.info("You need to start a game before you can provide an answer");
			}
			break;

		case "lessThan":
			if (runningGames == 1) {
				GameGuessResponse ltThan = guess(command, value);
				if (ltThan.isGuessStatus()) {
					logger.info("You guessed correctly with lessThan " + value);
				} else {
					logger.info("You guessed incorrectly with lessThan " + value + "; try again! \n");
				}
			}
			break;

		case "greaterThan":
			if (runningGames == 1) {
				GameGuessResponse gtThan = guess("greaterThan", value);
				if (gtThan.isGuessStatus()) {
					logger.info("You guessed correctly with greaterThan " + value + "\n");
				} else {
					logger.info("You guessed incorrectly with greaterThan " + value + "; try again! \n");
				}
			}
			break;
		case "odd":
			if (runningGames == 1) {
				GameGuessResponse gtThan = guess("odd", value);
				if (gtThan.isGuessStatus()) {
					logger.info("You guessed correctly with odd\n");
				} else {
					logger.info("You guessed incorrectly with odd try again!\n");
				}
			}
			break;
		case "even":
			if (runningGames == 1) {
				GameGuessResponse gtThan = guess("even", value);
				if (gtThan.isGuessStatus()) {
					logger.info("You guessed correctly with even\n");
				} else {
					logger.info("You guessed incorrectly with even; try again!\n");
				}
			}
			break;

		default:
			logger.info("Unknown command - quitting.");
			System.exit(1);
		}
	}

	public GameStartResponse startGame() throws Exception {

		GameStartRequest request = new GameStartRequest();
		request.setPlayerId(playerId);

		Entity<GameStartRequest> requestEntity = Entity.entity(request, MediaType.APPLICATION_JSON);
		Response getResponse = client.target(START_GUESSTIMATE_GAME_TARGET).request(MediaType.APPLICATION_JSON)
				.post(requestEntity);
		if (getResponse.getStatus() != 200) {
			throw new Exception("Something has gone wrong");
		}

		return getResponse.readEntity(GameStartResponse.class);
	}

	public GameGuessResponse guess(String operator, long value) throws Exception {
		GameGuessRequest request = new GameGuessRequest();
		request.setGameId(gameId);
		request.setGuessOperator(operator);
		request.setGuessValue(value);

		Entity<GameGuessRequest> requestEntity = Entity.entity(request, MediaType.APPLICATION_JSON);
		Response getResponse = client.target(GUESS_TARGET).resolveTemplate("gameId", gameId)
				.request(MediaType.APPLICATION_JSON).post(requestEntity);
		if (getResponse.getStatus() != 200) {
			throw new Exception("Something has gone wrong");
		}
		return getResponse.readEntity(GameGuessResponse.class);
	}

	public GameAnswerResponse answer(long answer) {
		GameAnswerRequest request = new GameAnswerRequest();
		request.setPlayerAnswer(answer);
		request.setGameId(gameId);
		Entity<GameAnswerRequest> requestEntity = Entity.entity(request, MediaType.APPLICATION_JSON);
		Response getResponse = client.target(END_GUESSTIMATE_TARGET).resolveTemplate("gameId", gameId)
				.request(MediaType.APPLICATION_JSON).post(requestEntity);

		return getResponse.readEntity(GameAnswerResponse.class);

	}

	public void feedUpdate() {
		WebTarget target = sseClient.target(LEADERBOARD_UPDATES);
		leaderboard = SseEventSource.target(target).build();
		leaderboard.register(event -> logger.info("Leaderboard update: " + event.readData()));
		leaderboard.open();
	}

}
