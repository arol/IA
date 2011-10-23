package IA.probTreballadors;
import aima.search.framework.HeuristicFunction;

public class probTreballadorsCiutatHeuristicFunction2 implements HeuristicFunction {
	
	public int getHeuristicValue( Object o ){
		int h;
		
		h=0;

		probTreballadorsCiutat ciutat = (probTreballadorsCiutat)o;
		
		int N = ciutat.getN();
		int M = ciutat.getM();
		int maximRecorregut = ciutat.getMaxDistanciaRecorrida();
		int n_conductors = ciutat.getNConductors();

		h = 0;

		for (int i=0;i<N-M; i++){ 
			int j = ciutat.getRecorregutCotxe(i);
			if (j > maximRecorregut)	return(java.lang.Integer.MAX_VALUE);
			h += j;
		}

		return h*n_conductors;
	}

}
