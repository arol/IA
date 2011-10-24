package IA.probTreballadors;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

public class probTreballadorsCiutatSuccessorFunction implements SuccessorFunction {
	public List getSuccessors( Object o ){
		ArrayList s = new ArrayList();
		probTreballadorsCiutat solucio = (probTreballadorsCiutat) o;

		probTreballadorsCiutat aux ;
                
		for(int i=0; i < solucio.getN(); i++){ //per cada treballador
			aux = new probTreballadorsCiutat(solucio);
			if( aux.avansar_entrada(i) ){
				s.add( new Successor( "Avansar entrada", aux) );
                                
                        }
			
			aux = new probTreballadorsCiutat(solucio);
			if( aux.avansar_sortida(i) ){
				s.add( new Successor( "Avansar sortida", aux) );
                        }

			for( int j=0; j < solucio.getNConductors(); j++ ){ //per cada cotxe
				aux = new probTreballadorsCiutat( solucio );
				if( aux.canviar_de_cotxe( i, j ) ){
					s.add( new Successor( "Canvi de cotxe", aux ));
                                }
			}


			aux = new probTreballadorsCiutat( solucio );
			if( aux.permutarConduccio( i ) ){
				s.add( new Successor( "Permutar conduccio", aux ));
			}
					

		}
                
                System.out.print(".");
		return s;	

	}
}
