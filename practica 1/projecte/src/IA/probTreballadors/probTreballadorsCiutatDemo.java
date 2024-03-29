package IA.probTreballadors;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import java.util.Scanner;

public class probTreballadorsCiutatDemo {
	
	public static void main( String[] args ) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		
                int numTreballadors, numNoConductors;
                int numS, numH, numSearch;
                
                //Definir M i N
                System.out.println("Quants treballadors hi ha?");
                numTreballadors = scan.nextInt();
                System.out.println("Quantes persones no poden conduir?");
                numNoConductors = scan.nextInt();
                
                //Triar solucio inicial
                System.out.println("Quin solucio inicial es vol fer servir? (1/2)");
                numS = scan.nextInt();
                
                //Triar heuristic
                System.out.println("Quin heuristic es vol fer servir? (1/2)");
                numH = scan.nextInt();
                
                //Triar algoritme de de cerca
                System.out.println("Quin algoritme de cerca? (1:Heuristic/2:SAnnealing)");
                numSearch = scan.nextInt();
                
                
                //Generar l'estat
		probTreballadorsCiutat estat = new probTreballadorsCiutat(numTreballadors, numNoConductors );
                
		//Generar la solucio inicial
                if(numS == 1)
                    estat.solucioInicial();
                else
                    estat.solucioInicial2();
		
		//Generar el problema segons el criteri de cerca (crear dos problemes)
                Problem problem;
                if(numH == 1)
                    problem = new Problem(estat, new probTreballadorsCiutatSuccessorFunction(), new probTreballadorsCiutatGoalTest(), new probTreballadorsCiutatHeuristicFunction());
                else
                    problem = new Problem(estat, new probTreballadorsCiutatSuccessorFunction(), new probTreballadorsCiutatGoalTest(), new probTreballadorsCiutatHeuristicFunction2());
		
                Search search;
		if(numSearch==1)
                    search = new HillClimbingSearch();
                else
                    search = new SimulatedAnnealingSearch(1000, 50, 5, 0.001);
                
                
		long inici = System.currentTimeMillis();
		SearchAgent agent = new SearchAgent( problem, search );
		long fi = System.currentTimeMillis();
		
                if (numSearch == 1){
                    HillClimbingSearch s = (HillClimbingSearch) search;
                }
                Search s = search;
        
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
		System.out.println();
		System.out.println();
		System.out.print((fi-inici)+" ");
		System.out.print(i+" ");
		
                
		
	}
	
}
