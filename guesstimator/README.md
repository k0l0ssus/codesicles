# Welcome to the Guesstimator  {Server-Side}

The Guesstimator server fields gameplay requests from the client programme and so needs to be running prior to any game requests. It runs on port 8080 and requires a functioning [Apache Maven](https://maven.apache.org/) installation


# Starting the server

 1. Navigate to the filesystem directory into which you've unpacked the Guesstimator project
 2. From within the parent folder, run the following command:

    mvn exec:java -Dexec.mainClass=com.me.samples.guesstimate.resource.Main

This process will pull dependencies necessary to run the server

 
 
## Running the server

The server terminal window will display all incoming and outgoing HTTP requests. Watch the console for the answer to each game launched. The server provides support for the following functions:

 - Starting a new game ("N")
 - Guessing game answer ranges
	 - "lessThan  x"
	 - "greaterThan y"
	 - "odd"
	 - "even"
 - A realtime feed of the leaderboard . All connected clients will get a notification whenever a player climbs to the top of the leaderboard
 - End the game by providing an answer. The server doesn't quit as a result
 - Terminate the server by hitting the return key in the terminal