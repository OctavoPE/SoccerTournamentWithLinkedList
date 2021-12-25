/**
 * @author Jeanmarco
 * LinkedList.java interacts, creates, and alters the node units, GameUnit to create a functioning LinkedList DS.
 */
public class LinkedList {
	GameUnit head;
	
	/** Adds a GameUnit node to the LL
	 * @param day
	 * @param Team1
	 * @param Team2
	 * @param Team1Score
	 * @param Team2Score
	 */
	public void append(int gameNumber, String Team1, String Team2, int Team1Score, int Team2Score) {
		if(head == null) {
			head = new GameUnit(gameNumber, Team1, Team2, Team1Score, Team2Score);
			return;
		}
		GameUnit current = head;
		while (current.next != null){
			current = current.next;
		}
		current.next = new GameUnit(gameNumber, Team1, Team2, Team1Score, Team2Score);
	}
	
	public GameUnit search(int data) {
		GameUnit current = head;
		while (current != null && current.gameNumber != data)
		{
			current = current.next;
		}
		if (current != null && current.gameNumber == data) {
			return current;
		}
		else {
			return current;
		}
	}
}
