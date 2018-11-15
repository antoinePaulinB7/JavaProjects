import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public abstract class Piece extends JComponent implements Drawable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6163315931953633666L;
	private Team team;
	private Coordinate coordinate;
	private boolean firstMove;
	private ArrayList<String>possibleMoves;
	private ArrayList<String>legalMoves;
	protected ImageIcon image = null;

	public Piece(Team team, Coordinate coordinate) {
		this.team = team;
		this.coordinate = coordinate;
		this.firstMove = true;
	};

	public abstract void loadImage();
	
	public void draw(Graphics g) {
		int width = application.board.getWidth();
		int height = application.board.getHeight();
		
		int xW = Board.board.length;
		int yH = Board.board[0].length;
		
		int x = width/xW;
		int y = height/yH;
		
		if(image!=null)image.paintIcon(this, g, coordinate.getFileIndex()*x, coordinate.getRankIndex()*y);
	}
	
	public abstract ArrayList<String> possibleMoves();

	public void updatePossibleMoves() {
		possibleMoves = possibleMoves();
	}
	
	public void updateLegalMoves() {
		legalMoves = legalMoves();
	}
	
	public ArrayList<String> legalMoves(){
		ArrayList<String> lMoves = new ArrayList<>(possibleMoves.size());

		int file, rank;

		for(int i = 0; i < possibleMoves.size(); i++) {
			String temp = possibleMoves.get(i);
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

		if(firstMove) setFirstMove(false);
		
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
	
	public boolean getFirstMove() {
		return firstMove;
	};
	
	public ArrayList<String> getPossibleMoves(){
		return possibleMoves;
	};
	
	public ArrayList<String> getLegalMoves(){
		return legalMoves;
	};

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	};
	
	public void setFirstMove(boolean value) {
		this.firstMove = value;
	};
}
