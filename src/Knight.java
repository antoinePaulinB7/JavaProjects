import java.util.ArrayList;

public class Knight extends Piece{

	public Knight(Team team, Coordinate coordinate) {
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
		// TODO Auto-generated method stub
		ArrayList<String> moves = new ArrayList<>();

		switch(getTeam()) {
		case WHITE :
			if(Board.getTile(getCoordinate().up().up().right())!=null
			&&!Board.getTile(getCoordinate().up().up().right()).isOccupiedByWhite()) {
				moves.add(getCoordinate().up().up().right()+"");
			}
			if(Board.getTile(getCoordinate().up().up().left())!=null
			&&!Board.getTile(getCoordinate().up().up().left()).isOccupiedByWhite()) {
				moves.add(getCoordinate().up().up().left()+"");
			}
			if(Board.getTile(getCoordinate().down().down().right())!=null
			&&!Board.getTile(getCoordinate().down().down().right()).isOccupiedByWhite()) {
				moves.add(getCoordinate().down().down().right()+"");
			}
			if(Board.getTile(getCoordinate().down().down().left())!=null
			&&!Board.getTile(getCoordinate().down().down().left()).isOccupiedByWhite()) {
				moves.add(getCoordinate().down().down().left()+"");
			}
			if(Board.getTile(getCoordinate().right().right().up())!=null
			&&!Board.getTile(getCoordinate().right().right().up()).isOccupiedByWhite()) {
				moves.add(getCoordinate().right().right().up()+"");
			}
			if(Board.getTile(getCoordinate().right().right().down())!=null
			&&!Board.getTile(getCoordinate().right().right().down()).isOccupiedByWhite()) {
				moves.add(getCoordinate().right().right().down()+"");
			}
			if(Board.getTile(getCoordinate().left().left().up())!=null
			&&!Board.getTile(getCoordinate().left().left().up()).isOccupiedByWhite()) {
				moves.add(getCoordinate().left().left().up()+"");
			}
			if(Board.getTile(getCoordinate().left().left().down())!=null
			&&!Board.getTile(getCoordinate().left().left().down()).isOccupiedByWhite()) {
				moves.add(getCoordinate().left().left().down()+"");
			}
			break;
		case BLACK :
			if(Board.getTile(getCoordinate().up().up().right())!=null
			&&!Board.getTile(getCoordinate().up().up().right()).isOccupiedByBlack()) {
				moves.add(getCoordinate().up().up().right()+"");
			}
			if(Board.getTile(getCoordinate().up().up().left())!=null
			&&!Board.getTile(getCoordinate().up().up().left()).isOccupiedByBlack()) {
				moves.add(getCoordinate().up().up().left()+"");
			}
			if(Board.getTile(getCoordinate().down().down().right())!=null
			&&!Board.getTile(getCoordinate().down().down().right()).isOccupiedByBlack()) {
				moves.add(getCoordinate().down().down().right()+"");
			}
			if(Board.getTile(getCoordinate().down().down().left())!=null
			&&!Board.getTile(getCoordinate().down().down().left()).isOccupiedByBlack()) {
				moves.add(getCoordinate().down().down().left()+"");
			}
			if(Board.getTile(getCoordinate().right().right().up())!=null
			&&!Board.getTile(getCoordinate().right().right().up()).isOccupiedByBlack()) {
				moves.add(getCoordinate().right().right().up()+"");
			}
			if(Board.getTile(getCoordinate().right().right().down())!=null
			&&!Board.getTile(getCoordinate().right().right().down()).isOccupiedByBlack()) {
				moves.add(getCoordinate().right().right().down()+"");
			}
			if(Board.getTile(getCoordinate().left().left().up())!=null
			&&!Board.getTile(getCoordinate().left().left().up()).isOccupiedByBlack()) {
				moves.add(getCoordinate().left().left().up()+"");
			}
			if(Board.getTile(getCoordinate().left().left().down())!=null
			&&!Board.getTile(getCoordinate().left().left().down()).isOccupiedByBlack()) {
				moves.add(getCoordinate().left().left().down()+"");
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
			return "N";
		case BLACK :
			return "n";
		default :
			return "N";
		}
	}

}
