package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import CoreGameTopTrumps.GameManager;
import CoreGameTopTrumps.Human;
import CoreGameTopTrumps.InputReader;
import CoreGameTopTrumps.TurnStatsHelper;
import CoreGameTopTrumps.User;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 *
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	GameManager gm;
	ArrayList<User> players;
	TurnStatsHelper turnStats;

	private int numberOfAIPlayers;
	private boolean isPlayerChoice;

	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws IOException{

		gm = new GameManager();
	}

	@GET
	@Path("/setUpGame")
	/**
	 * Initiats a game
	 * @param int numberOfPlayers
	 * @throws IOException
	 */
	public void setUpGameREST(@QueryParam("numberOfPlayers") int numberOfPlayers) throws IOException{
		System.out.println("AI Players: " + numberOfPlayers);
		numberOfAIPlayers = numberOfPlayers;
		setUpGame(numberOfPlayers);
		determinPlayerChoice();
	}
	
	/**
	 * Returns a String 'true' if the next players is human, false otherwise
	 * @return String
	 * @throws IOException
	 */
	@GET
	@Path("/isNextPlayerHuman")
	public String determinNextPlayerREST() throws IOException{
		return Boolean.toString(isPlayerChoice);
	}
	/**
	 * Returns the number of AI players
	 * @return int numOfAIPlayers
	 * @throws IOException
	 */
	@GET
	@Path("/numOpponents")
	public int numberofOpponents() throws IOException{

		return numberOfAIPlayers;
	}
	/**
	 * Returns a JSON string of the cards currently in play
	 * @return String
	 * @throws IOException
	 */
	@GET
	@Path("/displayCards")
	// http://localhost:7777/toptrumps/displayCards
	public String displayCards() throws IOException {

		return generateCardsJSON();
	}
	/**
	 * Sets the users choice
	 * @param int choice
	 * @throws IOException
	 */
	@GET
	@Path("/userChoice")

	// test with: http://localhost:7777/toptrumps/userChoice?choice=2
	public void userChoice(@QueryParam("choice") int choice) throws IOException{
		setUserChoice(choice);
		System.out.println(choice);
	}
	/**
	 * Plays a single round of the game and returns the results as a JSON String
	 * @return turnStatsJSON
	 * @throws IOException
	 */
	@GET
	@Path("/playRound")
	public String playRoundREST() throws IOException{
		String currentTurnStats = playRound();

		System.out.println(currentTurnStats);

		String turnStatsJSON = "{ \"turnStats\": [ " + currentTurnStats/* oWriter.writeValueAsString(currentTurnStats)*/+ "], \"points\": [" + oWriter.writeValueAsString(gm.getPoints()) + "]}";

		System.out.println(turnStatsJSON);


		return turnStatsJSON;
	}
	/**
	 * Automatically completes the game if required eg. Human has lost all their cards
	 * Returns the final results as a JSON String
	 * @return turnStatsJSON
	 * @throws IOException
	 */
	@GET
	@Path("/autoFinishGame")
	public String autoFinishGame() throws IOException{
		String currentTurnStats;
		
		do {
			currentTurnStats = playRound();
			
		}while(!gm.gameOver());
		
		String turnStatsJSON = "{ \"turnStats\": [ " + currentTurnStats/* oWriter.writeValueAsString(currentTurnStats)*/+ "], \"points\": [" + oWriter.writeValueAsString(gm.getPoints()) + "]}";
		
		return turnStatsJSON;
	}
	/**
	 * Returns a JSON String of the GameStats object, which connects to the database
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/previousGameStats")
	public String getPreviousGameStats() throws IOException {
		String previousGameStats = oWriter.writeValueAsString(gm.getPreviousGameStats());
		return previousGameStats;
	}

	/**
	 * Generates a JSON String of useful stats and the cards currently in play
	 * @return String 
	 */
	private String generateCardsJSON() {
		StringBuffer buffer = new StringBuffer();
		players = gm.getPlayers();

		buffer.append("{\n\"Stats\" : [{ \"roundNumber\" :\""+(gm.getTotalRounds())+"\", "
					+ "\n  \"isHumanChoice\" : \""+ isPlayerChoice +"\","
					+ "\n  \"numOfPlayers\" : \""+ players.size() +"\","
					+ "\n  \"nameOfNextPlayer\" : \""+ players.get(gm.getLastWinner()).getName() +"\"  }]," + System.lineSeparator() );

		for(int i = 0; i < players.size(); i++) {
			try {
				buffer.append(System.lineSeparator() + " \"" + players.get(i).getName()
						+"\" : [" + oWriter.writeValueAsString(players.get(i).getTopCard()) +  " ]");
				//puts commas in the appropriate place
				if(i<players.size()-1) {
					buffer.append(",");
				}


			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		buffer.append(System.lineSeparator() + "}");

		System.out.println(buffer);

		return buffer.toString();
	}

	/**
	 * Plays a single round and serialises the resuts into a JSON String
	 * 
	 * @return turnStatsJSON
	 */
	private String playRound() {

		gm.applyAICardChoice();

		gm.playRound();

		gm.handleEndOfRound();

		turnStats = gm.getTurnStatsHelper();

	//	TurnStatsHelper current = turnStats.get(turnStats.size()-1);

	//	System.out.println("fromREST API current turnstats : " + current);

		determinPlayerChoice();

		if(gm.gameOver()) {
			return "{ \"GAME\" : \"OVER\",\n \"MY\" : \"DUDE\"}";
		}

		String turnStatsJSON = "";
		try {
			turnStatsJSON = oWriter.writeValueAsString(turnStats);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return turnStatsJSON;
	}

	/**
	 * Creates a new game with the number of AI players and deals cards
	 * @param numberOfAIPlayers
	 */
	private void setUpGame(int numberOfAIPlayers) {
		gm = new GameManager();
		gm.deal(numberOfAIPlayers);
		System.out.println("GAME STARTED");
	}
	
	/**
	 * Uses the GameManagers method to establish if it is the players turn to choose
	 */
	private void determinPlayerChoice() {

		players = gm.getPlayers();

		if(gm.determinNextPlayer()) {
			this.isPlayerChoice = true;
		}else {
			this.isPlayerChoice =  false;
		}

	}
	/**
	 * Sets the humans current choice
	 * @param choice
	 */
	private void setUserChoice(int choice) {
		gm.setCurrentChoice(choice);
	}
}
