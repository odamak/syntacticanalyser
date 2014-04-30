//Classe Lexique


public class Lexique {
	public String lexeme;
	public String type;

	public Lexique(String word , String type){
		this.lexeme = word;
		this.type = type;
	}
	
	public Lexique(char caractere , String type){
		this.lexeme = ""+caractere;
		this.type = type;		
	}

	@Override
	public String toString() {
		return "[lexeme=" + lexeme + ", type=" + type + "]";
	}

}
