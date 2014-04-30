//Classe Automate

import java.util.Vector; //ctr+shift+o


public class Automate {
	
	Vector<Integer> etat;
	Vector<String> alphabet;
	Vector<Triplet> transition;
	int initial;
	Vector<Integer> accepteur;
	
	
	static int etatInitProchAut = 1 ;
	
	Automate (){
		
		etat = new Vector<Integer>();
		alphabet = new Vector<String>();
		transition = new Vector<Triplet>();
		accepteur = new Vector <Integer>();
						
	}
	
	Automate(String ID){
		
		this();
		
		initial = etatInitProchAut;
		//etat = new Vector<Integer>();		
		etat.add(etatInitProchAut);
		etat.add(etatInitProchAut+1);
		
		//alphabet = new Vector<String>();
		alphabet.add(ID);
		
		Triplet trip = new Triplet(etatInitProchAut,ID,etatInitProchAut+1);
		//transition = new Vector<Triplet>();
		
		transition.add(trip);
		
		//accepteur = new Vector <Integer>();
		accepteur.add(etatInitProchAut+1);
		
		etatInitProchAut+=2;
	}
	
	
	public Vector<Integer> unionEtat (Vector<Integer> vect1, Vector<Integer> vect2)
	{
		Vector<Integer> vectResult = new Vector<Integer>();
		vectResult.addAll(vect1);
		vectResult.addAll(vect2);
	
		return vectResult;
	}
	
	public Vector<String> unionAlphabet (Vector<String> vect1, Vector<String> vect2)
	{
		Vector<String> vectResult = new Vector<String>();
		vectResult = vect1;
		
		for (int i=0; i<vect2.size(); i++)
		{
			if (!vect1.contains(vect2.elementAt(i)))
				vectResult.add(vect2.elementAt(i));
				
		}
	
		return vectResult;
	}
	
	public Vector<Triplet> unionTransition (Vector<Triplet> vect1, Vector<Triplet> vect2)
	{
		Vector<Triplet> vectResult = new Vector<Triplet>();
		vectResult.addAll(vect1);
		vectResult.addAll(vect2);
		return(vectResult);
	}
	
	
	Vector<Triplet> transitionsDansAutxDuEtatInitial(Automate aut2){
		Vector<Triplet> vectTrip = new Vector<Triplet>();
		for (int i=0; i< aut2.transition.size();i++){
			if (aut2.transition.elementAt(i).etati == aut2.initial)
				
				vectTrip.add(new Triplet(aut2.transition.elementAt(i).etati, aut2.transition.elementAt(i).transition, aut2.transition.elementAt(i).etatf));
		}
		return(vectTrip);		
		
	}
	
	
	// ETOILE
	
	Automate (Automate aut1, String operateur){
		
		if (operateur == "*"){
			
			etat = aut1.etat;
			alphabet = aut1.alphabet;
			transition = aut1.transition;
			initial = etatInitProchAut;
			etat.add(initial);
			etatInitProchAut++;
			
			accepteur = aut1.accepteur;
			accepteur.add(initial);
			
			
			
			
			Vector<Triplet> transitionsAut3 = transitionsDansAutxDuEtatInitial(aut1);
			
			for (int indexTransitionsAut3=0; indexTransitionsAut3 < transitionsAut3.size(); indexTransitionsAut3++)
			{
				for(int i=0;i<accepteur.size();i++){
					transition.add(new Triplet(accepteur.elementAt(i), transitionsAut3.elementAt(indexTransitionsAut3).transition,transitionsAut3.elementAt(indexTransitionsAut3).etatf ));
				}
				
			}
			
			
			
			
			
		}
		
		else System.out.println("erreur!!!");
		
		
	}
	
	
	
	Automate (Automate aut1, Automate aut2, String operateur){
		
		this();
		
		
		if (operateur.equals("."))
		{
			
			etat = unionEtat(aut1.etat,aut2.etat);
			alphabet = unionAlphabet (aut1.alphabet,aut2.alphabet);
			transition = unionTransition(aut1.transition,aut2.transition);
			initial = aut1.initial;
			
			
			if (accepteur.contains(aut2.initial))
				accepteur.addAll(aut1.accepteur);
			
			accepteur.addAll(aut2.accepteur);
			
			
			
			Vector<Triplet> transitionsAut2 = transitionsDansAutxDuEtatInitial(aut2);
			Vector<Triplet> transitionsAut1Aut2 = new Vector<Triplet>();
			
			int indexTransitAjoute = 0;
			
		
			
			for (int indexEtatAcceptAut1=0; indexEtatAcceptAut1<aut1.accepteur.size(); indexEtatAcceptAut1++)
			{
				
				for (int indexTransitionsAut2=0; indexTransitionsAut2 < transitionsAut2.size(); indexTransitionsAut2++)
				{
					
					
					transitionsAut1Aut2.add(new Triplet(aut1.accepteur.elementAt(indexEtatAcceptAut1),transitionsAut2.elementAt(indexTransitionsAut2).transition,transitionsAut2.elementAt(indexTransitionsAut2).etatf));
					
					
					indexTransitAjoute++;
					
					
				}

				
			}
			
			
			transition = unionTransition(aut1.transition,aut2.transition);
			
			transition.addAll(transitionsAut1Aut2);
			
			//fin construction et ajout transitions
			
		}
		
		
		
		else if (operateur.equals("+"))
		{
			
			
			
			
			etat = unionEtat(aut1.etat,aut2.etat);
			alphabet = unionAlphabet (aut1.alphabet,aut2.alphabet);
			transition = unionTransition(aut1.transition,aut2.transition);
			initial = etatInitProchAut;
			etat.add(initial);
			etatInitProchAut++;
			
		
			accepteur.addAll(aut2.accepteur);
			accepteur.addAll(aut1.accepteur);
			
			if (accepteur.contains(aut1.initial) || accepteur.contains(aut2.initial))
				accepteur.add(initial);
			
			
			
			Vector<Triplet> transitionsAut2 = transitionsDansAutxDuEtatInitial(aut2);
			Vector<Triplet> transitionsAut1 = transitionsDansAutxDuEtatInitial(aut1);
			Vector<Triplet> transitionsEtatAjouteVersAut1 = new Vector<Triplet>();
			Vector<Triplet> transitionsEtatAjouteVersAut2 = new Vector<Triplet>();
			
			
			//construction des transitions entre les deux automates qu'on concatène (état ajouté et aut2)
			
				int indexTransitAjoute = 0;
			
			
				for (int indexTransitionsAut2=0; indexTransitionsAut2 < transitionsAut2.size(); indexTransitionsAut2++)
				{
					transitionsEtatAjouteVersAut2.add(transitionsAut2.elementAt(indexTransitionsAut2));
					transitionsEtatAjouteVersAut2.elementAt(indexTransitAjoute).etati = initial;
					indexTransitAjoute++;
				}
						
			
			//construction des transitions entre les deux automates qu'on concatène (état ajouté et aut1)
			
				indexTransitAjoute = 0;
			
				for (int indexTransitionsAut1=0; indexTransitionsAut1 < transitionsAut1.size(); indexTransitionsAut1++)
				{
					transitionsEtatAjouteVersAut1.add(transitionsAut1.elementAt(indexTransitionsAut1));
					transitionsEtatAjouteVersAut1.elementAt(indexTransitAjoute).etati = initial;
					indexTransitAjoute++;
				}
					
				
			
			
			transition = unionTransition(aut1.transition,aut2.transition);
			transition.addAll(transitionsEtatAjouteVersAut1);
			transition.addAll(transitionsEtatAjouteVersAut2);

			//fin construction et ajout transitions
			
			
			
			
			
		}
		
		else System.out.println("erreur!!!!!!!!!!");
			
			
			
			

			
		}
		
		
	
	
	

	@Override
	public String toString() {
		return " " + etat + "," + alphabet	+ "," + transition.toString() + "," + initial	+ "," + accepteur + "";
	}
	
	
	
	
	
	
}
