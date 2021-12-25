/*
 * Created by Jeanmarco Allain | CSIS 2420 Final Project | November 18, 2021
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, IOException {
		LinkedList GameList = new LinkedList();
		ArrayList<String> AllCountries = new ArrayList<String>();      // AllCountries will always contain all the countries that are in the tournament
		ArrayList<String> CountriesInPlay = new ArrayList<String>();   // CountriesInPlay will lose elements over time as countries play, they're removed from the random pool of countries to pair.
		
		// --- Read from File ---
		String file = "src\\Countries.csv";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedReader br = null;
		int numLines = 0;
		while(reader.readLine()!=null) numLines++;
		reader.close();
		String line = "";
		
		br = new BufferedReader(new FileReader(file));
		for(int i=0; i<numLines; i++) {
			line = br.readLine();
			AllCountries.add(line.toUpperCase());
			CountriesInPlay.add(line.toUpperCase());
		}
		
		// --- Read from File ---
		
		Scanner scanner = new Scanner(System.in);
		int day = 1;
		int gameNumber = 1;
		int uInput = 2;
		int gamesPerDay = 5;
		int timeToSleep = 750;
		boolean quit = false;
		
		System.out.println("//-------- FIFA WORLD CUP --------\\\\");
		Thread.sleep(timeToSleep);	
		
		do {
			System.out.println("Welcome to day " + day + " of the tournament!"); // put into loop to keep making game?
			Thread.sleep(timeToSleep);
			
			if(CountriesInPlay.size() > 1 && !quit) {
				for (int i=0;i<gamesPerDay;i++) { // Start at one so that we can have a game 
					selectTeams(CountriesInPlay, day, gameNumber, GameList);
					gameNumber++;
					Thread.sleep(timeToSleep);
				}
				System.out.println("\nPress any number to continue to tomorrow, or press 0 (zero) to quit.");
				uInput = scanner.nextInt();
			}
			if (CountriesInPlay.size() == 0) { // Reasons to end the game, user specified or no more teams left to play.
				quit = true;
				System.out.println("All of the matches have been played! We hope our winners enjoy their glory! See you next year!");
			}
			if (uInput == 0) {
				quit = true;
				System.out.println("Unfortunately, the tournament has been cancelled. We apologize for the situation.");
			}
			day++;
		
		} while (!quit);
		
		if (quit) {
			System.out.println("The games have concluded.");
		}
		
		boolean quitSearch = false;
		int uInputSearch;
		do {
			System.out.println("\n\nIf you would like to view a game's results, please type in the game's number. Otherwise, enter 0 (zero) to quit.");
			uInputSearch = scanner.nextInt();
			if(uInputSearch == 0) {
				quitSearch = true;
				System.out.println("You have quit the search.");
			}
			else {
				searchThisGame(uInputSearch, GameList);
			}
			
		} while (!quitSearch);
	}
	private static void searchThisGame(int uInputSearch, LinkedList GameList) {
		if(GameList.search(uInputSearch) != null) {
			long beginningTime = System.nanoTime();
			String team1 = (GameList.search(uInputSearch).Team1);
			String team2 = (GameList.search(uInputSearch).Team2);
			int team1Score = (GameList.search(uInputSearch).Team1Score);
			int team2Score = (GameList.search(uInputSearch).Team2Score);
			long endingTime = System.nanoTime();
			
			System.out.println("-/--// Match Replay: Game " + uInputSearch + " \\\\--\\-");
			System.out.println("--|| " + team1 + " vs " + team2 + " ||--");
			System.out.println("     Score: " + team1Score + " - " + team2Score);
			if (team1Score > team2Score) {
				System.out.println("     Winner: " + team1);
				System.out.println("(Time to retrieve results: "+(endingTime-beginningTime)+" nanoseconds)");
				return;
			}
			else if (team1Score < team2Score) {
				System.out.println("     Winner: " + team2);
				System.out.println("(Time to retrieve results: "+(endingTime-beginningTime)+" nanoseconds)");
				return;
			}
			System.out.println("     Winner: DRAW");
			System.out.println("(Time to retrieve results: "+(endingTime-beginningTime)+" nanoseconds)");
		}
		else {
			System.out.println("Invalid game number!");
		}
	}
	/**
	 * Selects the two teams to participate. Then calls the generateGame() method
	 * @param countriesInPlay
	 * @param day
	 * @param gameNumber
	 * @param GameList
	 */
	private static void selectTeams(ArrayList<String> countriesInPlay, int day, int gameNumber, LinkedList GameList) {
		int range = (countriesInPlay.size());
		int SelectedTeam = (int)(Math.random()*range);
		//System.out.println("Array Total: " + countriesInPlay.size() + ", selected team element number: " + SelectedTeam);
		String t1 = countriesInPlay.get(SelectedTeam);
		countriesInPlay.remove(SelectedTeam);
		
		range = (countriesInPlay.size());
		SelectedTeam = (int)(Math.random()*range);
		//System.out.println("Array Total: " + countriesInPlay.size() + ", selected team element number: " + SelectedTeam);
		String t2 = countriesInPlay.get(SelectedTeam);
		countriesInPlay.remove(SelectedTeam);
		
		//System.out.println("Teams selected: " + t1 + " " + t2);
		generateGame(day, gameNumber, GameList, t1, t2);
	}

	/**
	 * Generates custom scores for both teams, and creates a node in the LL. Will then print the results.
	 */
	private static void generateGame(int day, int gameNumber, LinkedList GameList, String t1, String t2) {

		int team1Score = (int)(Math.random()*7); // Generates the scores for the two teams
		int team2Score = (int)(Math.random()*7);
		
		GameList.append(gameNumber, t1, t2, team1Score, team2Score); // Stores the game into the LL.
		
		System.out.println("\n------// Day " + day + "; Game " + gameNumber + " \\\\------");
		System.out.println("--|| " + t1 + " vs " + t2 + " ||--");
		System.out.println("     Score: " + team1Score + " - " + team2Score);
		if (team1Score > team2Score) {
			System.out.println("     Winner: " + t1);
			return;
		}
		else if (team1Score < team2Score) {
			System.out.println("     Winner: " + t2);
			return;
		}
		System.out.println("     Winner: DRAW");
	}
}
