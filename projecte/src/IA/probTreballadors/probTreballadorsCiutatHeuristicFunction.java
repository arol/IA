package IA.probTreballadors;

import aima.search.framework.HeuristicFunction;

public class probTreballadorsCiutatHeuristicFunction implements HeuristicFunction {
	
	public int getHeuristicValue( Object o ){
		int h;
		
		h=0;

		probTreballadorsCiutat ciutat = (probTreballadorsCiutat)o;
		
                int n_conductors = ciutat.getNConductors();
		int maximRecorregut = ciutat.getMaxDistanciaRecorrida();
                
		h = 0;
                String x = "Heurisitic amb valors: ";
                
		for (int i=0;i<n_conductors; i++){ 
                        int j = ciutat.distanciaRecorregutCotxe(ciutat.cotxes[i]);
			if (j > maximRecorregut) h += j*j;
                        else h += j;
                        x += ", "+ j + " ";
		}
                
		return h;
	}

}
