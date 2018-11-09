
public class Coordinate {
	private char file;
	private int rank;
	
	public Coordinate(char file, int rank) {
		this.file = file;
		this.rank = rank;
	}
	
	public Coordinate(int file, int rank) {
		this.file = (char)('a'+file);
		this.rank = rank+1;
	}
	
	public Coordinate up() {
		Coordinate temp = new Coordinate(this.file,this.rank);
		temp.setRank(rank+1);
		
		return temp;
	}	
	
	public Coordinate down() {
		Coordinate temp = new Coordinate(this.file,this.rank);
		temp.setRank(rank-1);
		
		return temp;
	}	
	
	public Coordinate left() {
		Coordinate temp = new Coordinate(this.file,this.rank);
		temp.setFile((char)(file-1));
		
		return temp;
	}
	
	public Coordinate right() {
		Coordinate temp = new Coordinate(this.file,this.rank);
		temp.setFile((char)(file+1));
		
		return temp;
	}
	
	public char getFile() {
		return file;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getFileIndex() {
		return file-'a';
	}
	
	public int getRankIndex() {
		return rank-1;
	}
	
	public void setFile(char file) {
		this.file = file;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return file+""+rank;
	}
	
}
