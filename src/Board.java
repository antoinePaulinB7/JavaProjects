import java.util.Scanner;

import javax.swing.JComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Board extends JComponent implements Runnable {
	public static Tile[][] board;
	public static Scanner in;
	public static ArrayList<Piece> whitePieces, blackPieces;
	public static boolean gameOn;
	public static Player currentPlayer;
	public static Player white, black;
	public boolean animationRunning = false;
	public int mouseX = 0, mouseY = 0;

	public Board() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		setBackground(Color.darkGray);
		setFocusable(true);

		initialize();
		start();
	}

	public void initialize() {
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

		for (Piece piece : whitePieces) {
			piece.updatePossibleMoves();
		}
		for(Piece piece : blackPieces) {
			piece.updatePossibleMoves();
		}

		white = new Player(Team.WHITE, whitePieces);
		black = new Player(Team.BLACK, blackPieces);

		gameOn = true;
		currentPlayer = white;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(animationRunning) {
			
			repaint();

			try {
				Thread.sleep((long)(1000/60));
			}catch(InterruptedException e) {
				System.out.println("Error encountered during the animation sleep.");
			}
		}

	}

	public void start() {
		if(!animationRunning) {
			System.out.println("Animation loop started.");
			Thread animProcess = new Thread(this);
			animProcess.start();
			animationRunning = true;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(Color.darkGray);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		drawBoard(g2d);
		drawUnderlay(g2d);
		drawPieces(g2d);

	}

	public void drawBoard(Graphics2D g2d) {
		g2d.setColor(Color.white);

		int width = board.length;
		int height = board[0].length;

		int xW = getWidth()/width;
		int yH = getHeight()/height;

		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if((i+j)%2==0)g2d.fillRect(i*xW, j*yH, xW, yH);
			}
		}

	}

	public void drawUnderlay(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(4F));

		int width = board.length;
		int height = board[0].length;

		int xW = getWidth()/width;
		int yH = getHeight()/height;

		int x = mouseX/xW;
		int y = mouseY/yH;

		g2d.drawRect(x*xW, y*yH, xW, yH);

	}
	
	public void drawPieces(Graphics2D g2d) {
		
	}

	public static void main(String[] args) {
		in = new Scanner(System.in);

		whitePieces = new ArrayList<>();
		blackPieces = new ArrayList<>();

		//		Game board
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

		//		Test board
		//		String[][] config = {
		//				{"r"," "," "," ","k"," "," ","r"},
		//				{"p","p","p"," ","p"," ","p","p"},
		//				{" "," "," "," "," "," "," "," "},
		//				{" "," "," "," "," "," "," "," "},
		//				{" "," "," "," "," "," "," "," "},
		//				{" "," "," "," "," "," "," "," "},
		//				{"P","P","P"," ","P"," ","P","P"},
		//				{"R"," "," ","Q","K","Q"," ","R"}
		//		};

		board = loadBoard(config);
		for (Piece piece : whitePieces) {
			piece.updatePossibleMoves();
		}
		for(Piece piece : blackPieces) {
			piece.updatePossibleMoves();
		}
		white = new Player(Team.WHITE, whitePieces);
		black = new Player(Team.BLACK, blackPieces);

		gameOn = true;
		currentPlayer = white;

		while(gameOn) {

			currentPlayer.updatePossibleMoves();
			currentPlayer.updateLegalMoves();
			showInConsole();

			selectPiece(currentPlayer.getTeam());

			currentPlayer.updatePossibleMoves();
			currentPlayer.updateControlledTiles();
			currentPlayer = currentPlayer.getTeam() == Team.WHITE ? black : white;
		}

		in.close();
	}



	public static void selectPiece(Team team) {
		System.out.println("Select a "+team+" piece:");

		switch(team) {
		case WHITE:
			for(Piece piece : whitePieces) {
				System.out.println(piece+" "+piece.getLegalMoves());
			}
			break;
		case BLACK:
			for(Piece piece : blackPieces) {
				System.out.println(piece+" "+piece.getLegalMoves());
			}
			break;
		}

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

		System.out.print(piece+" chosen. Pick a move : "+piece.getLegalMoves());
		nextMove = in.next();

		if(nextMove.equals("O--O")&&piece.getPossibleMoves().contains("O--O")) {
			piece.moveTo(new Coordinate('c',piece.getCoordinate().getRank()));
			getTile(new Coordinate('a',piece.getCoordinate().getRank())).getPiece().moveTo(new Coordinate('d',piece.getCoordinate().getRank()));
		}else if(nextMove.equals("O-O")&&piece.getPossibleMoves().contains("O-O")){
			piece.moveTo(new Coordinate('g',piece.getCoordinate().getRank()));
			getTile(new Coordinate('h',piece.getCoordinate().getRank())).getPiece().moveTo(new Coordinate('f',piece.getCoordinate().getRank()));
		}else {

			file = nextMove.charAt(0)-'a';
			rank = nextMove.charAt(1)-49;

			Coordinate coordinate = new Coordinate(file,rank);

			if(piece.testMoveTo(coordinate)) {
				piece.moveTo(coordinate);
			}else {
				selectPiece(piece.getTeam());
			}
		}
	}

	public static void showInConsole() {
		System.out.println("   - - - - - - - -");
		for(int i = board.length-1; i >= 0; i--) {
			System.out.print(i+1+" |");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(""+board[i][j]+"|");
			}
			System.out.print("\n");
			if(i > 0) System.out.println("   - - - - - - - -");
			else System.out.println("   a b c d e f g h");
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

			if(black.controls(target)) return true;

			return false;

		case BLACK :
			for(int j = 0; j < blackPieces.size(); j++) {
				if(blackPieces.get(j).getClass()==King.class) {
					target = blackPieces.get(j).getCoordinate();
					break;
				}
			}
			//System.out.println("black king at:"+target);

			if(white.controls(target)) return true;

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
				for(int i = 0; i < blackPieces.size(); i++) {
					//System.out.println(Board.blackPieces.get(i)+""+Board.blackPieces.get(i).legalMoves());
					if(!blackPieces.get(i).legalMoves().isEmpty()) {
						checkmate = false;
					}
				}
			}
			break;
		case BLACK:
			if(Board.kingInCheck(Team.WHITE)) {
				checkmate = true;
				for(int i = 0; i < whitePieces.size(); i++) {
					if(!whitePieces.get(i).legalMoves().isEmpty()) {
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
		showInConsole();
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
