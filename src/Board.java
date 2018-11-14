import java.util.Scanner;
import java.util.ArrayList;

public class Board {
	public static Tile[][] board;
	public static Scanner in;
	public static ArrayList<Piece> whitePieces, blackPieces;
	public static boolean gameOn;
	public static Team currentTeam;

	public static void main(String[] args) {
		in = new Scanner(System.in);

		whitePieces = new ArrayList<>();
		blackPieces = new ArrayList<>();

		String[][] config = {
				{"r","n","b","q","k","b","n","r"},
				{"p","p","p","p","p","p","p","p"},
				{" "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "},
				{"P","P","P","P","P","P","P","P"},
				{"R","N","B","Q","K","B","N","R"}
		};

		board = loadBoard(config);
		gameOn = true;
		currentTeam = Team.WHITE;

		while(gameOn) {

			show();

			selectPiece(currentTeam);
			currentTeam = currentTeam == Team.WHITE ? Team.BLACK : Team.WHITE;
		}

		in.close();
	}



	public static void selectPiece(Team team) {
		System.out.print("Select a "+team+" piece:");

		Piece temp = null;
		String selectedPiece;
		do {
			selectedPiece = in.next();

			if(selectedPiece.equals("print")) {
				selectedPiece = in.next();

				int file = selectedPiece.charAt(0)-'a';
				int rank = selectedPiece.charAt(1)-49;

				System.out.print(getTile(new Coordinate(file,rank)));
			}


			int file = selectedPiece.charAt(0)-'a';
			int rank = selectedPiece.charAt(1)-49;

			Coordinate tempCoord = new Coordinate(file,rank);

			if(getTile(tempCoord)!=null) {

				switch(team) {
				case WHITE :

					if(getTile(tempCoord).isOccupiedByWhite()
							&&!getTile(tempCoord).getPiece().legalMoves().isEmpty()) {
						temp = getTile(tempCoord).getPiece();
					}
					break;
				case BLACK :
					if(getTile(tempCoord).isOccupiedByBlack()
							&&!getTile(tempCoord).getPiece().legalMoves().isEmpty()) {
						temp = getTile(tempCoord).getPiece();
					}
					break;
				}
				if(temp==null) System.out.print("Selection not valid. Please enter a valid piece: ");
			}else {
				System.out.print("Tile not valid. Please enter a valid tile: ");
			}

		}while(temp==null);

		movePiece(temp);
	}

	public static void movePiece(Piece piece) {
		String nextMove = "";
		int file, rank;

		System.out.print(piece+" chosen. Pick a move : "+piece.legalMoves());
		nextMove = in.next();

		file = nextMove.charAt(0)-'a';
		rank = nextMove.charAt(1)-49;

		Coordinate coordinate = new Coordinate(file,rank);

		if(piece.testMoveTo(coordinate)) {
			piece.moveTo(coordinate);
		}else {
			selectPiece(piece.getTeam());
		}
	}

	public static void show() {
		System.out.println("   ----------------------------------------");
		for(int i = board.length-1; i >= 0; i--) {
			System.out.print(i+1+" |");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(""+board[i][j]+"|");
			}
			System.out.print("\n");
			if(i > 0) System.out.println("   ----------------------------------------");
			else System.out.println("   a    b     c    d    e    f    g    h");
		}
	}

	public static Tile getTile(Coordinate coordinate) {
		try {
			return board[coordinate.getRankIndex()][coordinate.getFileIndex()];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static boolean kingInCheck(Team team) {
		Coordinate target = null;
		switch(team) {
		case WHITE :
			for(int j = 0; j < whitePieces.size(); j++) {
				if(whitePieces.get(j).getClass()==King.class) {
					target = whitePieces.get(j).getCoordinate();
					break;
				}
			}
			//System.out.println("white king at:"+target);

			for(int i = 0; i < blackPieces.size(); i++) {
				//System.out.println(blackPieces.get(i)+""+blackPieces.get(i).legalMoves());
				if(blackPieces.get(i).possibleMoves().contains(target+"")) return true;
			}

			return false;

		case BLACK :
			for(int j = 0; j < blackPieces.size(); j++) {
				if(blackPieces.get(j).getClass()==King.class) {
					target = blackPieces.get(j).getCoordinate();
					break;
				}
			}
			//System.out.println("black king at:"+target);

			for(int i = 0; i < whitePieces.size(); i++) {
				//System.out.println(whitePieces.get(i)+""+whitePieces.get(i).legalMoves());
				if(whitePieces.get(i).possibleMoves().contains(target+"")) return true;

			}

			return false;
		default :
			return false;
		}

	}

	public static void win(Team team) {

		boolean checkmate = false;

		switch(team) {
		case WHITE :
			//System.out.println("Moved "+this+". Checking for black king check.");
			if(Board.kingInCheck(Team.BLACK)) {
				checkmate = true;
				//System.out.println("Black king is in check.");
				for(int i = 0; i < Board.blackPieces.size(); i++) {
					//System.out.println(Board.blackPieces.get(i)+""+Board.blackPieces.get(i).legalMoves());
					if(!Board.blackPieces.get(i).legalMoves().isEmpty()) {
						checkmate = false;
					}
				}
			}
			break;
		case BLACK:
			if(Board.kingInCheck(Team.WHITE)) {
				checkmate = true;
				for(int i = 0; i < Board.whitePieces.size(); i++) {
					if(!Board.whitePieces.get(i).legalMoves().isEmpty()) {
						checkmate = false;
					}
				}
			}
			break;
		}

		//System.out.println("@@@ checkmate is "+checkmate);
		if(checkmate) {
			System.out.println("CHECKMATE! "+team+" wins.");
			gameOver();
		}
	}

	public static void gameOver() {
		gameOn = false;
		show();
	}

	public static Tile[][] loadBoard(String[][]config){
		Tile[][] board = new Tile[config.length][config[0].length];
		Coordinate temp;
		for(int r = 0; r < config.length; r++) {
			for(int f = 0; f < config[r].length; f++) {
				temp = new Coordinate(f,r);
				switch(config[config.length-r-1][f]) {
				case "K":
					board[r][f] = new Tile(temp,new King(Team.WHITE,temp));
					break;
				case "k":
					board[r][f] = new Tile(temp,new King(Team.BLACK,temp));
					break;
				case "Q":
					board[r][f] = new Tile(temp,new Queen(Team.WHITE,temp));
					break;
				case "q":
					board[r][f] = new Tile(temp,new Queen(Team.BLACK,temp));
					break;
				case "R":
					board[r][f] = new Tile(temp,new Rook(Team.WHITE,temp));
					break;
				case "r":
					board[r][f] = new Tile(temp,new Rook(Team.BLACK,temp));
					break;
				case "B":
					board[r][f] = new Tile(temp,new Bishop(Team.WHITE,temp));
					break;
				case "b":
					board[r][f] = new Tile(temp,new Bishop(Team.BLACK,temp));
					break;
				case "N":
					board[r][f] = new Tile(temp,new Knight(Team.WHITE,temp));
					break;
				case "n":
					board[r][f] = new Tile(temp,new Knight(Team.BLACK,temp));
					break;				
				case "P":
					board[r][f] = new Tile(temp,new Pawn(Team.WHITE,temp));
					break;
				case "p":
					board[r][f] = new Tile(temp,new Pawn(Team.BLACK,temp));
					break;
				default:
					board[r][f] = new Tile(temp,null);
				}
			}
		}

		return board;
	}

}
