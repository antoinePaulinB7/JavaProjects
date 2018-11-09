import java.util.ArrayList;

public abstract class Piece {
	private Team team;
	private Coordinate coordinate;

	public Piece(Team team, Coordinate coordinate) {
		this.team = team;
		this.coordinate = coordinate;
	};

	public abstract ArrayList<String> possibleMoves();

	public boolean moveTo(Coordinate coordinate) {
		Piece deadPiece = null;
		Coordinate oldCoordinate = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

		if(possibleMoves().contains(coordinate+"")) {
			if(Board.getTile(coordinate).isOccupied()) {
				deadPiece = Board.getTile(coordinate).getPiece();
				Board.getTile(coordinate).getPiece().die();
			}
			Board.getTile(getCoordinate()).setPiece(null);
			setCoordinate(coordinate);
			Board.getTile(coordinate).setPiece(this);

			if(Board.kingInCheck(team)) {
				System.out.println("Can't let your King get checked.");
				Board.show();
				setCoordinate(oldCoordinate);
				if(deadPiece!=null) {
					switch(deadPiece.getTeam()){
					case WHITE :
						Board.whitePieces.add(deadPiece);
						break;
					case BLACK :
						Board.blackPieces.add(deadPiece);
					}
				}
				Board.getTile(coordinate).setPiece(deadPiece);
				Board.getTile(getCoordinate()).setPiece(this);
				return false;

			}
		}else {
			return false;
		}
		return true;
	}

	public void die() {
		// TODO Auto-generated method stub
		switch(getTeam()) {
		case WHITE :
			Board.whitePieces.remove(this);
			break;
		case BLACK :
			Board.blackPieces.remove(this);
			break;
		}

		Board.getTile(getCoordinate()).setPiece(null);
	}

	@Override
	public abstract String toString();

	public Team getTeam() {
		return team;
	};

	public Coordinate getCoordinate() {
		return coordinate;
	};

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	};
}
