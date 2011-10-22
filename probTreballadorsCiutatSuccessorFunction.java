package probTreballadorsCiutatSuccessorFunction.java

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;

public class probTreballadorsSuccessorFunction implements SuccessorFunction {
	public List getSuccessors( Object o ){
		ArrayList s = new ArrayList();
		probTreballadorsCiutatEstat solucio = (probTreballadorsCiutatEstat) o;

		//per cada x
		//  per cada y
		//		estatNou = new estat(solucio)
		//    boolean ok = estatNou.aplicarFuncioTransformacio(x,y);
		//		if(ok) s.add(new Successor("Funcio Transformacio", estatNou);

		probTreballadorsCiutatEstat aux ;

		for(int i=0; i < solucio.getNumTreballadors; i++){ //per cada treballador
			aux = new probTreballadorsCiutatEstat(solucio);
			if( aux.avansar_entrada(i) )
				s.add( new Successor( "Avançar entrada", aux) );
			
			aux = new probTreballadorsCiutatEstat(solucio);
			if( aux.avansar_sortida(i) )
				s.add( new Successor( "Avançar sortida", aux) );i

			for( int j=0; j < solucio.getNumCotxes; j++ ){ //per cada cotxe
				aux = new probTreballadorsCiutatEstat( solucio );
				if( aux.canviar_de_cotxe( i, j ) )
					s.add( new Successor( "Canvi de cotxe", aux ));
			}

			aux = new probTreballadorsCiutatEstat( solucio );
			if( aux.permutarConduccio( i ) ){
				s.add( new Successor( "Permutar conduccio". aux );
			}
					
		}

		for(int i=0; i < solucio.getNumCotxes; i++){
			for(int j=0; j < solucio.getNumCotxes; j++){
				if(i!=j){
					aux = probTreballadorsCiutatEstat( solucio );
					if( aux.canviar_conductor(i,j) )
						s.add( new Successor( "Intercanvi conductor", aux ));
				}
			}
		}

				

	}
}
