//Classe Analyseur Lexical

import java.util.Scanner;
import java.util.Vector;

public class AnalyseurLexical {
	

	public static Vector<Lexique> analyser(String s){
	
	
	s = s+"$";
	int stringIndex = 0;
	int etat = 0;
	int wordIndex = 0;
	
	Vector <Lexique> lexemes = new Vector<Lexique>();
	
	String word="";
	char caractere;
	boolean activerFinChaine = false;
	boolean finChaine = false;
	while (s.charAt(stringIndex) != '$' && finChaine==false){
		
		caractere = s.charAt(stringIndex);
		
		switch (etat){
			
			case 0: //System.out.println("case0 "+s.charAt(stringIndex));
					if (Character.isLetter(s.charAt(stringIndex)) /*|| Character.isDigit(s.charAt(stringIndex))*/ )
					{
						if (s.charAt(stringIndex+1)=='$')
						{
							word+=caractere;
							etat=2;
							activerFinChaine = true;
						}
						
						else
						{
							word+=caractere;
							stringIndex++;
							etat = 1;							
						}
						
					}
					else
					{
						switch(s.charAt(stringIndex)){						
						 
						case '*': etat = 3; break;
						case '.': etat = 4; break;
						case '+': etat = 5; break;
						case '(': etat = 6; break;
						case ')': etat = 7; break;
						
						}
					}
					break;
			
			case 1: //System.out.println("case1 "+s.charAt(stringIndex));
					if (Character.isLetter(s.charAt(stringIndex)) || Character.isDigit(s.charAt(stringIndex)) || s.charAt(stringIndex)=='_' )
					{
						if (s.charAt(stringIndex+1)=='$')
						{
							word+=caractere;
							etat=2;
							activerFinChaine = true;
						}
						
						else
						{
							word+=caractere;
							stringIndex++;
						}
				
					}
					
					else
					{
						etat = 2;						
						
					}
					break;
			
			case 2: //System.out.println("case2 "+s.charAt(stringIndex));
					Lexique lexique2 = new Lexique(word,"ID");
					lexemes.add(lexique2);
					word = "";
					if (activerFinChaine==true)
						finChaine=true;
					etat = 0;
					break;
					
			case 3: //System.out.println("case3 "+s.charAt(stringIndex));
					lexique2 = new Lexique(caractere,"ETOILE");
					lexemes.add(lexique2);
					stringIndex++;
					word = "";
					etat = 0;
				    break;
					
			case 4: //System.out.println("case4 "+s.charAt(stringIndex));
					lexique2 = new Lexique(caractere,"CONCAT");
					lexemes.add(lexique2);
					stringIndex++;
					word="";
					etat = 0;
				    break;
			
			case 5: //System.out.println("case5 "+s.charAt(stringIndex));
					lexique2 = new Lexique(caractere,"PLUS");
					lexemes.add(lexique2);
					stringIndex++;
					word="";
					etat = 0;
				    break;
				
			case 6: //System.out.println("case6 "+s.charAt(stringIndex));
					lexique2 = new Lexique(caractere,"POUV");
					lexemes.add(lexique2);
					stringIndex++;
					word="";
					etat = 0;
				    break;
				
			case 7: //System.out.println("case7 "+s.charAt(stringIndex));
					lexique2 = new Lexique(caractere,"PFERM");
					lexemes.add(lexique2);
					stringIndex++;
					word="";
					etat = 0;
				    break;						
						
					
		
		}
			
		
		
	}
	
	//System.out.println(lexemes.toString());
	
	return(lexemes);

}
}