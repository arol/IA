package IA.probTreballadors;

import aima.search.framework.HeuristicFunction;

public class probTreballadorsCiutatHeuristicFunction implements HeuristicFunction {
	
	public int getHeuristicValue( Object o ){
		int h;
		
		h=0;

		probTreballadorsCiutat ciutat = (probTreballadorsCiutat)o;
                
                ciutat.imprimeixSolucio();
		
                ciutat.recalcularDistanciesCotxes();
                
		int N = ciutat.getN();
		int M = ciutat.getM();
		int maximRecorregut = ciutat.getMaxDistanciaRecorrida();

		h = 0;
                String x = "Heurisitic amb valors: ";
                
		for (int i=0;i<N-M; i++){ 
			int j = ciutat.getRecorregutCotxe(i);
			//if (j > maximRecorregut) return(java.lang.Integer.MAX_VALUE);
			h += j;
                        x += ", "+ j + " ";
		}
                
                System.out.println("");
                
                System.out.println(x);
                
                System.out.println("");
                
                System.out.println(h);
		return h;
	}

}
