import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private Team team;
	private ArrayList<Piece> pieces;
	private Set<Tile> controlledTiles;
	private int value;

	public Player(Team team, ArrayList<Piece> pieces) {
		this.team = team;
		this.pieces = pieces;
		controlledTiles = calculateControlledTiles();
		value = calculateValue();
	}

	public Set<Tile> calculateControlledTiles(){
		Set<Tile> temp = new HashSet<Tile>();
		int file, rank;
		for(Piece piece : pieces) {
			ArrayList<String> pieceMoves = piece.possibleMoves();
			for(String move : pieceMoves) {
				file = move.charAt(0)-'a';
				rank = move.charAt(1)-49;
				temp.add(Board.getTile(new Coordinate(file,rank)));
			}
		}

		return temp;
	}

	public int calculateValue() {
		int value = 0;
		for(Piece piece : pieces) {
			value += piece.getValue();
			value += piece.legalMoves().size();
			switch(team) {
			case WHITE :
				if(Board.black!=null&&Board.black.controls(piece.getCoordinate())){
					value -= 3;
				}
				break;
			case BLACK:
				if(Board.white!=null&&Board.white.controls(piece.getCoordinate())) {
					value -= 3;
				}
				break;
			}
			//if(!controls(piece.getCoordinate()))value -= 1;
		}
		value += controlledTiles.size();
		return value;
	}

	public boolean controls(Coordinate coordinate) {
		return controlledTiles.contains(Board.getTile(coordinate));
	}

	public void update() {
		updatePossibleMoves();
		updateLegalMoves();
		updateControlledTiles();
		updateValue();
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
		Board.updates++;
		//System.out.println("Updating controlled tiles for "+team);
		controlledTiles = calculateControlledTiles();
	}

	public void updateValue() {
		value = calculateValue();
	}

	public Team getTeam() {
		return team;
	}

	public Set<Tile> getControlledTiles(){
		return controlledTiles;
	}

	public ArrayList<Piece> getPieces(){
		return pieces;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		String temp = "";
		for(Piece piece : pieces) {
			temp+=piece+""+piece.getLegalMoves()+"\n";
		}
		return temp;
	}

}
