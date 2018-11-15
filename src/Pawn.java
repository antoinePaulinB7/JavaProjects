import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pawn extends Piece{
	
	public Pawn(Team team, Coordinate coordinate) {
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
	public ArrayList<String> possibleMoves(){
		ArrayList<String> moves = new ArrayList<>();
		switch(getTeam()) {
		case WHITE :
			if(Board.getTile(getCoordinate().up())!=null
			&&!Board.getTile(getCoordinate().up()).isOccupied()) {
				moves.add(getCoordinate().up()+"");
				if(getFirstMove()
				&&Board.getTile(getCoordinate().up().up())!=null
				&&!Board.getTile(getCoordinate().up().up()).isOccupied()) {
					moves.add(getCoordinate().up().up()+"");
				}
			}
			if(Board.getTile(getCoordinate().up().right())!=null
			&&Board.getTile(getCoordinate().up().right()).isOccupiedByBlack()) {
				moves.add(getCoordinate().up().right()+"");
			}
			if(Board.getTile(getCoordinate().up().left())!=null
			&&Board.getTile(getCoordinate().up().left()).isOccupiedByBlack()) {
				moves.add(getCoordinate().up().left()+"");
			}
			break;
		case BLACK :
			if(Board.getTile(getCoordinate().down())!=null
			&&!Board.getTile(getCoordinate().down()).isOccupied()) {
				moves.add(getCoordinate().down()+"");
				if(getFirstMove()
				&&Board.getTile(getCoordinate().down().down())!=null
				&&!Board.getTile(getCoordinate().down().down()).isOccupied()) {
					moves.add(getCoordinate().down().down()+"");
				}
			}
			if(Board.getTile(getCoordinate().down().right())!=null
			&&Board.getTile(getCoordinate().down().right()).isOccupiedByWhite()) {
				moves.add(getCoordinate().down().right()+"");
			}
			if(Board.getTile(getCoordinate().down().left())!=null
			&&Board.getTile(getCoordinate().down().left()).isOccupiedByWhite()) {
				moves.add(getCoordinate().down().left()+"");
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
			return "P";
		case BLACK :
			return "p";
		default :
			return "P";
		}
	}
	
	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		String team = getTeam() == Team.WHITE ? "white" : "black";
		String fileName = team+"_pawn.png";
		
		URL url;
		try {
			url = getClass().getResource(fileName);
			image = new ImageIcon(ImageIO.read(url));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load the pawn's image");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
	}
}
