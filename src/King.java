import java.util.ArrayList;

public class King extends Piece {

	public King(Team team, Coordinate coordinate) {
		super(team, coordinate);
		switch(team) {
		case WHITE :
			Board.whitePieces.add(this);
			break;
		case BLACK :
			Board.blackPieces.add(this);
			break;
		}
	}

	@Override
	public ArrayList<String> possibleMoves() {
		ArrayList<String> moves = new ArrayList<>();
		Coordinate temp;

		switch(getTeam()) {

		case WHITE :
			for(int j = 1; j <= 8; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

				switch(j) {
				case 1 :
					temp = temp.up();
					break;
				case 2 : 
					temp = temp.up().right();
					break;
				case 3 : 
					temp = temp.right();
					break;
				case 4 :
					temp = temp.right().down();
					break;
				case 5 :
					temp = temp.down();
					break;
				case 6 : 
					temp = temp.down().left();
					break;
				case 7 :
					temp = temp.left();
					break;
				case 8 :
					temp = temp.left().up();
					break;
				}

				if(Board.getTile(temp)!=null
						&&!Board.getTile(temp).isOccupiedByWhite()) {
					moves.add(temp+"");
				}

			}
			break;
		case BLACK :
			for(int j = 1; j <= 8; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

				switch(j) {
				case 1 :
					temp = temp.up();
					break;
				case 2 : 
					temp = temp.up().right();
					break;
				case 3 : 
					temp = temp.right();
					break;
				case 4 :
					temp = temp.right().down();
					break;
				case 5 :
					temp = temp.down();
					break;
				case 6 : 
					temp = temp.down().left();
					break;
				case 7 :
					temp = temp.left();
					break;
				case 8 :
					temp = temp.left().up();
					break;
				}

				if(Board.getTile(temp)!=null
				&&!Board.getTile(temp).isOccupiedByBlack()) {
					moves.add(temp+"");
				}

			}
			break;
		}

		return moves;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch(getTeam()) {
		case WHITE :
			return (char)9812+"";
		case BLACK :
			return (char)9818+"";
		default :
			return "K";
		}
	}

}
