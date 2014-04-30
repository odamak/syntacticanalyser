//Classe Analyseur Syntaxique

import java.awt.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.Vector;


public class AnalyseurSyntaxique {
	
	Vector<Lexique> vect ;
	Lexique lexCourant;
	int pos;
	boolean validite;
	Vector<String> vectTest;
	
	Vector <Automate> vectTestAut;
	//int first_time = 1;
	
	
	Stack<Automate> pile;
	
	
	AnalyseurSyntaxique(String s){
		
		vect =AnalyseurLexical.analyser(s);
		pos = 0;
		vectTest = new Vector<String>();
		symboleSuivant();
		
		pile = new Stack<Automate>();
		
		
		procE();
		Collections.sort(pile.peek().etat);
		Collections.sort(pile.peek().alphabet);
		System.out.println(vect);
		System.out.println("M = " + pile.toString());
		
		
	}
	

	
	public void symboleSuivant(){
		
		if (pos<vect.size())
		{
			lexCourant = vect.elementAt(pos);
			pos++;
	
		}
		
		//if (lexCourant.equals("$") && vect.elementAt(pos-2).lexeme.equals("*"))
			//System.out.println("chaine se termine par étoile");
		
	
		
	}
	
	public void procE(){
		
		procT();
		procE1();
		
	}
	
	public void procT(){
		
		procF();
		procT1();
		
		
	}
	
	public void procE1(){
		if (lexCourant.lexeme.equals("+"))
		{
			
			symboleSuivant();
			procT();
			procE1();
			
			Automate autTemp3 = pile.pop();
			Automate autTemp4 = pile.pop();
			Automate autTemp5 = new Automate (autTemp3,autTemp4,"+");
			pile.push(autTemp5);
			
			
		}
	}
	
	public void procT1(){
		if (lexCourant.lexeme.equals("."))
		{
			
			symboleSuivant();
			procF();
			procT1();
			
			
			Automate autTemp1 = pile.pop();
			Automate autTemp2 = pile.pop();
			Automate autTemp = new Automate (autTemp2,autTemp1,".");
			pile.push(autTemp);
			
			
		}
		
	}
	
	public void procF(){
		
		procA();
		procF1();
		
	}
	
	public void procF1(){
		
		if (lexCourant.lexeme.equals("*"))
		{
			
			symboleSuivant();
			
			Automate autTemp9 = pile.pop();
			
			Automate autTemp10 = new Automate (autTemp9,"*");
			pile.push(autTemp10);
			
			
			
			procF1();
			
		}
		
	}
	
	public void procA(){
		
		if (lexCourant.type == "ID")
		{
			
			Automate aut = new Automate(lexCourant.lexeme);
			
			pile.push(aut);
			
			
			
			symboleSuivant();
		}
		
		else if (lexCourant.lexeme.equals("("))
		{
			symboleSuivant();
			procE();
			if (lexCourant.lexeme.equals(")"))
				symboleSuivant();
			else
			{
				validite = false;
				
			}
				
		}
		
		else
			validite = false;
			
		
	}
	
	

}
