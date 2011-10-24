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
                String x = "Heurisitic amb valors: ";
                int mitja=0;
                
		for (int i=0;i<n_conductors; i++){ 
			int j = ciutat.distanciaRecorregutCotxe(ciutat.cotxes[i]);
			//if (j > maximRecorregut)	return(java.lang.Integer.MAX_VALUE);
                        if (j > maximRecorregut) h += j*j*2;
			mitja += ciutat.abs(300-j);
                        
                        x += ", "+ j + " ";
		}
                mitja = (int)(mitja/n_conductors);
                //mitja = ciutat.abs(300-mitja) * n_conductors;
                mitja = mitja * n_conductors;
                                
                System.out.println("");
                
                System.out.println(x);
                
                System.out.println("");
                
                System.out.println(h*n_conductors);
                
		return h+mitja;
	}

}
