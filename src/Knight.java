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
		setValue(3);
		loadImage();
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
	
	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		String team = getTeam() == Team.WHITE ? "white" : "black";
		String fileName = team+"_knight.png";
		
		URL url;
		try {
			url = getClass().getResource(fileName);
			image = new ImageIcon(ImageIO.read(url));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load the knight's image");
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
			
			switch(j) {
			case 1 :
				temp = temp.up().up().right();
				break;
			case 2 : 
				temp = temp.up().up().left();
				break;
			case 3 : 
				temp = temp.up().right().right();
				break;
			case 4 :
				temp = temp.up().left().left();
				break;
			case 5 :
				temp = temp.down().down().right();
				break;
			case 6 : 
				temp = temp.down().down().left();
				break;
			case 7 :
				temp = temp.down().right().right();
				break;
			case 8 :
				temp = temp.down().left().left();
				break;
			}
			
			if(Board.getTile(temp)!=null){
				tiles.add(Board.getTile(temp));
			}

		}
		return tiles;
	}
}
