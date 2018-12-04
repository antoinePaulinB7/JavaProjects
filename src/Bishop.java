import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
		setValue(3);
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
			return "B"+calculateValue();
		case BLACK :
			return "b"+calculateValue();
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

		image = new ImageIcon(Utils.getScaledImage(image.getImage(), 50, 50));

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
	}

	@Override
	public Set<Tile> calculateControlledTiles() {
		Set<Tile> tiles = new HashSet<Tile>();
		Coordinate temp;

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
				if(Board.getTile(temp)!=null) {
					tiles.add(Board.getTile(temp));
					if(Board.getTile(temp).isOccupied())break;
				}else {
					break;
				}
			}
		}

		return tiles;
	}

	@Override
	public int calculateValue() {
		int value = getValue();
		int numberOfBishops = 0;
		//If the bishop pair is intact
		switch(getTeam()) {
		case WHITE :
			for(Piece p : Board.whitePieces) {
				if(p.getClass()==Bishop.class)numberOfBishops++;
			}
			break;
		case BLACK :
			for(Piece p : Board.blackPieces) {
				if(p.getClass()==Bishop.class)numberOfBishops++;
			}
			break;
		}
		
		if(numberOfBishops>1)value++;
		
		return value;
	}


}
