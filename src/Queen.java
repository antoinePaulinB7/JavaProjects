import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
			return "Q";
		case BLACK :
			return "q";
		default :
			return "Q";
		}
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		String team = getTeam() == Team.WHITE ? "white" : "black";
		String fileName = team+"_queen.png";
		
		URL url = getClass().getClassLoader().getResource(fileName);
		
		try {
			image = new ImageIcon(ImageIO.read(url));
		}catch (IOException e) {
			System.out.println("Couldn't load the queen's image");
		}
		
	}
	
}
