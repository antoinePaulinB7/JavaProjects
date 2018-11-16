import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class King extends Piece {
	private boolean canCastleLong, canCastleShort;

	public King(Team team, Coordinate coordinate) {
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

		boolean localCheckTest = false;

		switch(getTeam()) {

		case WHITE :
			for(int j = 1; j <= 8; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

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
				}

			}

			temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

			try{
				if(Board.black.controls(temp)) localCheckTest = true;


				//check long and short castle
				if(getFirstMove() && !localCheckTest){

					canCastleLong = false;
					if(Board.getTile(new Coordinate('a',1)).getPiece().getFirstMove()) canCastleLong = true;

					if(canCastleLong) {

						for(int i = 3; i > 0; i--) {
							temp = temp.left();
							if(!Board.getTile(temp).isOccupied()) {
								if(i>1) {
									if(Board.black.controls(temp)) {
										canCastleLong = false;
										break;
									}
								}
							}else {
								canCastleLong = false;
								break;
							}
						}

						if(canCastleLong) {
							System.out.println("white king can castle long");
							moves.add("O--O");
						}
					}

					canCastleShort = false;
					if(Board.getTile(new Coordinate('h',1)).getPiece().getFirstMove()) canCastleShort = true;

					if(canCastleShort) {
						temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

						for(int i = 2; i > 0; i--) {
							temp = temp.right();
							if(!Board.getTile(temp).isOccupied()) {
								if(Board.black.controls(temp)) {
									canCastleShort = false;
									break;
								}
							}else {
								canCastleShort = false;
								break;
							}
						}

						if(canCastleShort) {
							System.out.println("white king can castle short");
							moves.add("O-O");
						}
					}
				}
			}catch(NullPointerException e) {

			}
			break;
		case BLACK :
			for(int j = 1; j <= 8; j++) {

				temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

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
				}

			}
			temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

			try{
				if(Board.white.controls(temp)) localCheckTest = true;


				//check long and short castle
				if(getFirstMove() && !localCheckTest){

					canCastleLong = false;
					if(Board.getTile(new Coordinate('a',8)).getPiece().getFirstMove()) canCastleLong = true;

					if(canCastleLong) {

						for(int i = 3; i > 0; i--) {
							temp = temp.left();
							if(!Board.getTile(temp).isOccupied()) {
								if(i>1) {
									if(Board.white.controls(temp)) {
										canCastleLong = false;
										break;
									}
								}
							}else {
								canCastleLong = false;
								break;
							}
						}

						if(canCastleLong) {
							System.out.println("black king can castle long");
							moves.add("O--O");
						}
					}

					canCastleShort = false;
					if(Board.getTile(new Coordinate('h',8)).getPiece().getFirstMove()) canCastleShort = true;

					if(canCastleShort) {
						temp = new Coordinate(getCoordinate().getFile(),getCoordinate().getRank());

						for(int i = 2; i > 0; i--) {
							temp = temp.right();
							if(!Board.getTile(temp).isOccupied()) {
								if(Board.white.controls(temp)) {
									canCastleShort = false;
									break;
								}

							}else {
								canCastleShort = false;
								break;
							}
						}

						if(canCastleShort) {
							System.out.println("black king can castle short");
							moves.add("O-O");
						}
					}
				}
				break;
			}catch(NullPointerException e) {

			}
		}

		return moves;
	}

	@Override
	public ArrayList<String> legalMoves(){
		ArrayList<String> lMoves = super.legalMoves();

		if(getPossibleMoves().contains("O--O")) {
			lMoves.add("O--O");
		}

		if(getPossibleMoves().contains("O-O")) {
			lMoves.add("O-O");
		}

		return lMoves;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch(getTeam()) {
		case WHITE :
			return "K";
		case BLACK :
			return "k";
		default :
			return "K";
		}
	}
	
	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		String team = getTeam() == Team.WHITE ? "white" : "black";
		String fileName = team+"_king.png";
		
		URL url;
		try {
			url = getClass().getResource(fileName);
			image = new ImageIcon(ImageIO.read(url));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't load the king's image");
			e.printStackTrace();
		}
		
		image = new ImageIcon(Utils.getScaledImage(image.getImage(), 50, 50));
		
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
	}

}
