import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Queen extends Piece {

	public Queen(Team team, Coordinate coordinate) {
		super(team, coordinate);
		switch(team) {
		case WHITE :
			Board.whitePieces.add(this);
			break;
		case BLACK :
			Board.blackPieces.add(this);
			break;
		}
		setValue(9);
		loadImage();
	}

	@Override
	public ArrayList<String> possibleMoves() {
		ArrayList<String> moves = new ArrayList<>();
		Coordinate temp;

		switch(getTeam()) {
		case WHITE :
			for(int j = 1; j <= 8; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

				for(int i = 1; i < 8; i++) {
					switch(j) {
					case 1 :
						temp = temp.up();
						break;
					case 2 : 
						temp = temp.up().right();
						break;
					case 3 : 
						temp = temp.right();
						break;
					case 4 :
						temp = temp.right().down();
						break;
					case 5 :
						temp = temp.down();
						break;
					case 6 : 
						temp = temp.down().left();
						break;
					case 7 :
						temp = temp.left();
						break;
					case 8 :
						temp = temp.left().up();
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
			for(int j = 1; j <= 8; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

				for(int i = 1; i < 8; i++) {
					switch(j) {
					case 1 :
						temp = temp.up();
						break;
					case 2 : 
						temp = temp.up().right();
						break;
					case 3 : 
						temp = temp.right();
						break;
					case 4 :
						temp = temp.right().down();
						break;
					case 5 :
						temp = temp.down();
						break;
					case 6 : 
						temp = temp.down().left();
						break;
					case 7 :
						temp = temp.left();
						break;
					case 8 :
						temp = temp.left().up();
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
			return "Q"+calculateValue();
		case BLACK :
			return "q"+calculateValue();
		default :
			return "Q";
		}
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		String team = getTeam() == Team.WHITE ? "white" : "black";
		String fileName = team+"_queen.png";
		
		URL url;
		try {
			url = getClass().getResource(fileName);
			image = new ImageIcon(ImageIO.read(url));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load the queen's image");
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

		for(int j = 1; j <= 8; j++) {

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
				case 5 :
					temp = temp.up();
					break;
				case 6 : 
					temp = temp.right();
					break;
				case 7 : 
					temp = temp.down();
					break;
				case 8 :
					temp = temp.left();
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
		try {

			//if queen is safe
			switch(getTeam()) {
			case WHITE :
				if(!Board.black.controls(getCoordinate()))value+=5;
				break;
			case BLACK :
				if(!Board.white.controls(getCoordinate()))value+=5;
				break;
			}
		}catch(NullPointerException e) {
		
		}
		
		return value;
	}
	
}
