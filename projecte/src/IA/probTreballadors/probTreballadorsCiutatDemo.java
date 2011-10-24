package IA.probTreballadors;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import java.util.Scanner;

public class probTreballadorsCiutatDemo {
	
	public static void main( String[] args ) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		
		//Generar l'estat
		probTreballadorsCiutat estat = new probTreballadorsCiutat( );
		
		//Generar la solucio inicial
		estat.solucioInicial();
		
		//Generar el problema segons el criteri de cerca (crear dos problemes)
		Problem problem = new Problem(estat, new probTreballadorsCiutatSuccessorFunction(), new probTreballadorsCiutatGoalTest(), new probTreballadorsCiutatHeuristicFunction());
		
		Search search = new HillClimbingSearch();
		long inici = System.currentTimeMillis();
		SearchAgent agent = new SearchAgent( problem, search );
		long fi = System.currentTimeMillis();
		
		HillClimbingSearch s = (HillClimbingSearch) search;
		probTreballadorsCiutat e = (probTreballadorsCiutat) s.getGoalState();
		
		e.imprimeixSolucio();
		System.out.println("Accions realitzades: ");
		int i;
		for (i = 0; i < agent.getActions().size(); i++) {
			String action = (String) agent.getActions().get(i);
			System.out.println(i+"->" + action);
		}
		
		System.out.println("Temps d'execucio (en milisegons): "+ (fi-inici));
		System.out.println("Solucio Final");
//		System.out.println("Temps Total: " + e.recalcularDistanciesCotxes());
		System.out.println();
		System.out.println();
		System.out.print((fi-inici)+" ");
		System.out.print(i+" ");
		
                e.imprimeixSolucio();
		//Generar la cerca (2 cerques)
		
	}
	
}
