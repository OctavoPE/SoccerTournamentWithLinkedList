/**
 * @author Jeanmarco
 *	GameUnit.java serves as the node object for the LL.
 */
public class GameUnit {
	GameUnit next;
	int gameNumber;
	String Team1;
	String Team2;
	int Team1Score = 0;
	int Team2Score = 0;
	
	public GameUnit(int gameNumber, String team1, String team2, int team1Score, int team2Score) {
		this.gameNumber = gameNumber;
		this.Team1 = team1;
		this.Team2 = team2;
		this.Team1Score = team1Score;
		this.Team2Score = team2Score;
	}
}
