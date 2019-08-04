# Welcome to the Guesstimator  {Client-Side}!

The Guesstimator Client connects to a running Guesstimator server to play the number-guessing game. It requires a functioning [Apache Maven](https://maven.apache.org/) installation


# Starting the client

 1. Navigate to the filesystem directory into which you've unpacked the Guesstimator client project
 2. From within the parent folder, run the following command in sequence: 
 
  	 - mvn clean install 
	 - mvn exec:java -Dexec.mainClass=com.me.samples.guesstimate.App


This process will pull dependencies necessary to run the client app.

 
 
## Playing the game

The client app supports the following commands. All commands are case-sensitive:
 - Starting a new game ("N") : enter "N" at the prompt to start a new game
 - "**lessThan** x " :  enter a guess x for the upper range of the answer 
 - "**greaterThan** y": enter a guess y for the lower range of the answer
 - "**odd**" : whether the answer is an odd number
 - "**even**": whether the answer is an even number
 - "**A** z": submit your definite answer z 	 
 - "**Q**": terminate the app

## Leaderboard
The App receives a real-time feed of updates to the leaderboard. So when a player gets to the top, an event is pushed from the server to the client. You can view the full leaderboard with an HTTP GET to: `http://localhost:8080/guesstimator/guess/game/leaderboard`
