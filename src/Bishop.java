import java.awt.Graphics2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
		loadImage();
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
			return "B";
		case BLACK :
			return "b";
		default :
			return "B";
		}
	}


	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		String team = getTeam() == Team.WHITE ? "white" : "black";
		String fileName = team+"_bishop.png";
		
		URL url;
		try {
			url = getClass().getResource(fileName);
			image = new ImageIcon(ImageIO.read(url));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load the bishop's image");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}


}
