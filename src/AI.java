import java.util.Random;

public class AI {
	
	public Player perspective = null;
	public Random random;	
	
	public void pickAMove() {
		random = new Random();
		perspective = Board.currentPlayer;
		int index;
		Piece randomP;
		
		do {
			index = random.nextInt(perspective.getPieces().size());
			randomP = perspective.getPieces().get(index);
		}while(randomP.getLegalMoves().isEmpty());
		
		System.out.println(randomP);
		
		index = random.nextInt(randomP.getLegalMoves().size());
		String randomS = randomP.getLegalMoves().get(index);
		
		System.out.println(randomS);
		
		char file = randomS.charAt(0);
		int rank = Integer.parseInt(randomS.charAt(1)+"");
		
		if(randomP.testMoveTo(new Coordinate(file,rank))){
			randomP.moveTo(new Coordinate(file,rank));
		}
	}
}
