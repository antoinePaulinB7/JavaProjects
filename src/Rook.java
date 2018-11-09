import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Team team, Coordinate coordinate) {
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

			for(int j = 1; j <= 4; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

				for(int i = 1; i < 8; i++) {
					switch(j) {
					case 1 :
						temp = temp.up();
						break;
					case 2 : 
						temp = temp.down();
						break;
					case 3 : 
						temp = temp.left();
						break;
					case 4 :
						temp = temp.right();
						break;
					}
					if(Board.getTile(temp)!=null
							&&!Board.getTile(temp).isOccupiedByWhite()) {
						moves.add(temp+"");
						if(Board.getTile(temp).isOccupiedByBlack())break;
					}else {
						break;
					}
				}
			}
			break;
			
		case BLACK :

			for(int j = 1; j <= 4; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

				for(int i = 1; i < 8; i++) {
					switch(j) {
					case 1 :
						temp = temp.up();
						break;
					case 2 : 
						temp = temp.down();
						break;
					case 3 : 
						temp = temp.left();
						break;
					case 4 :
						temp = temp.right();
						break;
					}
					if(Board.getTile(temp)!=null
							&&!Board.getTile(temp).isOccupiedByBlack()) {
						moves.add(temp+"");
						if(Board.getTile(temp).isOccupiedByWhite())break;
					}else {
						break;
					}
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
			return "R";
		case BLACK :
			return "r";
		default :
			return "R";
		}
	}

}
