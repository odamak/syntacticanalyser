
//Classe Triplet

public class Triplet {
	public int etati;
	public String transition;
	public int etatf;
	
	
	Triplet(int a,String b, int c){
		etati = a;
		transition = b;
		etatf = c;
	}


	@Override
	public String toString() {
		return " [" + etati + "," + transition	+ "," + etatf + "]";
	}
	
	
	
}


