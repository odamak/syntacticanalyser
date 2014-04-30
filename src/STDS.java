//Code Source STDS

import java.util.Scanner;


public class STDS {
	
	
	
	
	public static void main(String[] args){
	
		String s;
		Scanner scan = new Scanner(System.in);
		System.out.println("Entrer l'expression régulière");
		s = scan.nextLine();
		
		//System.out.println(AnalyseurLexical.analyser(s));
		AnalyseurSyntaxique AnSynt1 = new AnalyseurSyntaxique(s);
	
		
		scan.close();
		
		
		
		
	}

}
