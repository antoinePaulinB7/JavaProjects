import java.util.Random;

public class AI {

	public Player perspective = null;
	public Random random;	

	public void pickAMove() {
		random = new Random();
		perspective = Board.currentPlayer;
		int index;
		Piece randomP;
		char file = 0;
		int rank = 0;
		boolean keepSearching;

		do {
			index = random.nextInt(perspective.getPieces().size());
			randomP = perspective.getPieces().get(index);

			if(!randomP.getLegalMoves().isEmpty()) {
				System.out.println(randomP);

				index = random.nextInt(randomP.getLegalMoves().size());
				String randomS = randomP.getLegalMoves().get(index);

				System.out.println(randomS);

				if(randomS.equals("O-O")||randomS.equals("O--O")) {
					keepSearching = true;
				}else {
					file = randomS.charAt(0);
					rank = Integer.parseInt(randomS.charAt(1)+"");
					keepSearching = false;
				}
			}else {
				keepSearching = true;
			}


		}while(keepSearching);

		if(randomP.testMoveTo(new Coordinate(file,rank))){
			randomP.moveTo(new Coordinate(file,rank));
		}else {
			pickAMove();
		}
	}
	
	public void pickBestMove() {
		perspective = Board.currentPlayer;
		int highestValue = -1000;
		char file,chosenFile = 0;
		int rank,chosenRank = 0;
		Piece chosenPiece = null;
		
		for(Piece p : perspective.getPieces()) {
			for(String s : p.getLegalMoves()) {
				if(!(s.equals("O-O")||s.equals("O--O"))) {
					file = s.charAt(0);
					rank = Integer.parseInt(s.charAt(1)+"");
					if(p.testMoveTo(new Coordinate(file,rank))) {
						if(perspective.calculateValue()>highestValue) {
							chosenPiece = p;
							chosenFile = file;
							chosenRank = rank;
						}
					}
				}
			}
		}
		
		chosenPiece.moveTo(new Coordinate(chosenFile,chosenRank));
	}
}
