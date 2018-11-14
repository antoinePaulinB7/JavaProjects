import java.util.ArrayList;

public abstract class Piece {
	private Team team;
	private Coordinate coordinate;

	public Piece(Team team, Coordinate coordinate) {
		this.team = team;
		this.coordinate = coordinate;
	};

	public abstract ArrayList<String> possibleMoves();

	public ArrayList<String> legalMoves(){
		ArrayList<String> pMoves = this.possibleMoves();
		ArrayList<String> lMoves = new ArrayList<>(pMoves.size());

		int file, rank;

		for(int i = 0; i < pMoves.size(); i++) {
			String temp = pMoves.get(i);
			file = temp.charAt(0)-'a';
			rank = temp.charAt(1)-49;

			if(this.testMoveTo(new Coordinate(file, rank))) {
				lMoves.add(temp);
			}
		}

		return lMoves;
	};

	public void moveTo(Coordinate coordinate) {
		if(Board.getTile(coordinate).isOccupied()) {
			Board.getTile(coordinate).getPiece().die();
		}
		Board.getTile(getCoordinate()).setPiece(null);
		setCoordinate(coordinate);
		Board.getTile(coordinate).setPiece(this);

		Board.win(team);
	}

	public boolean testMoveTo(Coordinate coordinate) {
		Piece deadPiece = null;
		Coordinate oldCoordinate = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());
		boolean moveIsPossible = false;

		if(possibleMoves().contains(coordinate+"")) {
			moveIsPossible = true;

			if(Board.getTile(coordinate).isOccupied()) {
				deadPiece = Board.getTile(coordinate).getPiece();
				Board.getTile(coordinate).getPiece().die();
			}
			Board.getTile(getCoordinate()).setPiece(null);
			setCoordinate(coordinate);
			Board.getTile(coordinate).setPiece(this);

			//System.out.println(Board.kingInCheck(team));

			if(Board.kingInCheck(team)) {
				System.out.println("Can't let your King get checked.");
				//Board.show();

				moveIsPossible=false;

			}
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
		}

		return moveIsPossible;
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
