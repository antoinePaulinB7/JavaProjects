import java.util.ArrayList;

public class Bishop extends Piece{

	public Bishop(Team team, Coordinate coordinate) {
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
						temp = temp.up().right();
						break;
					case 2 : 
						temp = temp.up().left();
						break;
					case 3 : 
						temp = temp.down().right();
						break;
					case 4 :
						temp = temp.down().left();
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
						temp = temp.up().right();
						break;
					case 2 : 
						temp = temp.up().left();
						break;
					case 3 : 
						temp = temp.down().right();
						break;
					case 4 :
						temp = temp.down().left();
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
			return (char)9815+"";
		case BLACK :
			return (char)9821+"";
		default :
			return "B";
		}
	}

}
