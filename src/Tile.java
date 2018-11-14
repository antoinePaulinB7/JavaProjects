
public class Tile {
	private Coordinate coordinate;
	private Piece piece;
	
	public Tile(Coordinate coordinate, Piece piece) {
		this.coordinate = coordinate;
		this.piece = piece;
	}
	
	public boolean isOccupied() {
		if(piece != null) return true;
		else return false;
	}
	
	public boolean isOccupiedByWhite() {
		if(piece != null) return piece.getTeam()==Team.WHITE;
		else return false;
	}
	
	public boolean isOccupiedByBlack() {
		if(piece != null) return piece.getTeam()==Team.BLACK;
		else return false;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	@Override
	public String toString() {
		if(isOccupied()) return " "+piece+"    ";
		else return "    ";
	} 
}
