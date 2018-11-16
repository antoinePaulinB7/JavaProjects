import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private Team team;
	private ArrayList<Piece> pieces;
	private Set<Tile> controlledTiles;

	public Player(Team team, ArrayList<Piece> pieces) {
		this.team = team;
		this.pieces = pieces;
		controlledTiles = calculateControlledTiles();
	}

	public Set<Tile> calculateControlledTiles(){
		Set<Tile> temp = new HashSet<Tile>();
		int file, rank;
		for(Piece piece : pieces) {
			ArrayList<String> pieceMoves = piece.getLegalMoves();
			for(String move : pieceMoves) {
				file = move.charAt(0)-'a';
				rank = move.charAt(1)-49;
				temp.add(Board.getTile(new Coordinate(file,rank)));
			}
		}

		return temp;
	}

	public boolean controls(Coordinate coordinate) {
		return controlledTiles.contains(Board.getTile(coordinate));
	}

	public void update() {
		updatePossibleMoves();
		updateLegalMoves();
		updateControlledTiles();
	}

	public void updatePossibleMoves() {

		System.out.println("Updating possible moves for "+team);
		for(Piece piece : pieces) {
			piece.updatePossibleMoves();
		}
	}

	public void updateLegalMoves() {

		System.out.println("Updating legal moves for "+team);
		for(Piece piece : pieces) {
			piece.updateLegalMoves();
		}
	}

	public void updateControlledTiles() {

		System.out.println("Updating controlled tiles for "+team);
		controlledTiles = calculateControlledTiles();
	}

	public Team getTeam() {
		return team;
	}

	public Set<Tile> getControlledTiles(){
		return controlledTiles;
	}
	
	@Override
	public String toString() {
		String temp = "";
		for(Piece piece : pieces) {
			temp+=piece+""+piece.getPossibleMoves()+"\n";
		}
		return temp;
	}

}
