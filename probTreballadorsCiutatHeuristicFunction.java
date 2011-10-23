package probTreballadorsCiutat;
import aima.search.framework.HeuristicFunction;

public class probTreballadorsCiutatHeuristicFunction implements HeuristicFunction {
	
	public double getHeuristicValue( Object o ){
		double h;
		
		h=0;

		probTreballadorsCiutat ciutat = (probTreballadorsCiutat)o;
		
		int N = ciutat.getN();
		int M = ciutat.getM();
		int maximRecorregut = ciutat.getMaxDistanciaRecorrida();

		int h = 0;

		for (int i=0;i<N-M; i++){ 
			int i = ciutat.getRecorregutCotxe(i);
			if (i > maximRecorregut)	return(java.lang.Integer.MAX_VALUE);
			h += i;
		}

		return h;
	}

}
