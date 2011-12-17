package IA.probTreballadors;

import java.util.*;

public class probTreballadorsCiutat {

	public class Treballador{
                int id;
		boolean conductor;
		private Posicio origen, desti;
		Cotxe cotxe;
                boolean potConduir;

		int ie,is;

		public Treballador(Posicio origen, Posicio desti){
			conductor = false;
			this.origen = origen;
			this.desti = desti;
                        potConduir = true;
		}
                public Treballador(int identificador, Posicio origen, Posicio desti){
                        id = identificador;
			conductor = false;
			this.origen = origen;
			this.desti = desti;
                        potConduir = true;
                }

		public void setConductor(boolean cond){
			conductor = cond;
		}

		public Posicio getOrigen() {
			return this.origen;
		}

		public Posicio getDesti() {
			return this.desti;
		}
	}

	public class Posicio{

		int x;
		int y;

		public Posicio(int x, int y){
			this.x = x;
			this.y = y;
		}

		//Getters i setters
		public void setX (int valor){
			x = valor;
		}

		public int getX(){
			return(x);
		}

		public void setY (int valor){
			y = valor;
		}

		public int getY(){
			return(y);
		}

	}

	public class Cotxe{
		Treballador conductor;
		int id;
		ArrayList ordre;
		int size;
		int idConductor;


                public Cotxe(int id,int idconductor,int size){
                    this.ordre = new ArrayList();
                    this.id = id;
                    this.size = size;
                    this.idConductor = idconductor;
                }
                
                public int getId(){
                    return id;
                }
		public Cotxe (Treballador c){
                    conductor = c;
                    ordre = new ArrayList();
                    size=0;
		}

		public Cotxe (Treballador t, int c){
                    conductor = t;
                    ordre = new ArrayList();
                    id=c;
                    size=0;
		}

                public void mostraCua()
                {
                    System.out.println("Cotxe: "+id);
                    
                    System.out.print("Ordre: ");
                    
                    for (int i = 0; i < size; i++) {
                        System.out.print(((Treballador)ordre.get(i)).id+" ");
                    }
                    
                    System.out.println(" ");
                    System.out.println("Size: "+ size);
                }
		/*x es l'id del treballador al array de treballadors*/
		public void afegirAcompanyant(Treballador t){
                  
                    ordre.add(t);
                    ordre.add(t);

                    t.cotxe = cotxes[id];

                    size+=2;
		}

		public void eliminarAcompanyant(Treballador t){
                    size-=2;

                    this.ordre.remove(t);
                    this.ordre.remove(t);

		}

                private void referenciar(ArrayList o) {
                    conductor = treballadors[idConductor];
                    conductor.conductor = true;
                    
                    for( int i = 0; i < o.size(); i++){
                        Treballador t = treballadors[((Treballador)o.get(i)).id];
                        ordre.add(i, t);
                    }
                }
	}

	private int N; //Nombre de treballadors
	private int M; //Nombre de treballadors conductors.

	public Cotxe [] cotxes;
	public Treballador [] treballadors;

	private static int maxTrabajadoresCoche = 2;
	private static int maximaDistanciaConductor = 300;
	
	private int n_conductors;

	public probTreballadorsCiutat(int numN, int numM){

		N = numN;
		M = numM;

		Date date = new Date();
		long time = date.getTime();
		Random rnd = new Random(System.currentTimeMillis());

		n_conductors = 0;

		treballadors = new Treballador[N];
		cotxes = new Cotxe[N-M];

		int i;

		for (i=0; i<N; i++){ 
			int x_ori = rnd.nextInt(100);
			int y_ori = rnd.nextInt(100);
			Posicio origen = new Posicio (x_ori,y_ori);
                 
			int x_desti = rnd.nextInt(100);
			int y_desti = rnd.nextInt(100);
			Posicio desti = new Posicio (x_desti,y_desti);

			Treballador t = new Treballador(i, origen,desti);

			treballadors[i] = t;

		}

                //Marquem els que no poden conduir
                for (i=0; i < M; i++){ 
                    treballadors[i].potConduir=false;
		}

	}
        
        public probTreballadorsCiutat( probTreballadorsCiutat o ){
                N = o.getN();
                M = o.getM();
                n_conductors = o.getNConductors();
                
                treballadors = new Treballador[N];
                cotxes = new Cotxe[N-M];

                
                Cotxe[] auxc = o.getCotxes();
                for( int i=0; i < n_conductors; i++){
                    Cotxe c = new Cotxe(auxc[i].id,
                                                      auxc[i].conductor.id,
                                                      auxc[i].size);
                    cotxes[auxc[i].id] = c;
                    
                }       
                
                Treballador[] aux = o.getTreballadors();
                for(int i=0; i < N; i++) {
                    treballadors[i] = new Treballador(i, aux[i].origen, aux[i].desti);
                    treballadors[i].conductor = aux[i].conductor;
                    treballadors[i].potConduir = aux[i].potConduir;
                    
                    try{
                        treballadors[i].cotxe = cotxes[aux[i].cotxe.getId()];
                    }catch( NullPointerException e ){
                        System.exit(0);
                    }
                    
                    
                    treballadors[i].ie = aux[i].ie;         
                    treballadors[i].is = aux[i].is;
                }

                
                for( int i=0; i < n_conductors; i++){
                    cotxes[i].referenciar(auxc[i].ordre);
                }
                
        }

	public void solucioInicial(){
		int i; //Acompanyants colocats
		int j; //Iterador de treballadors
		int c; //Iterador de cotxes modular a N-M
		boolean trobat;
		
		Date date = new Date();
		long time = date.getTime();
		Random rnd = new Random(time);

		Cotxe cotxe;
		i=0;
                n_conductors=0;
		while (n_conductors < N-M){
			if (i == N) i = 0;
			if (rnd.nextBoolean() && !treballadors[i].conductor && treballadors[i].potConduir){
				treballadors[i].conductor = true;
				cotxe = new Cotxe(treballadors[i], n_conductors);
				cotxe.idConductor = i;
				
				cotxes[n_conductors] = cotxe;
                                treballadors[i].cotxe = cotxe;			
				n_conductors++;
			}
			i++;
		}

		j = 0;
		c = 0;
		for( i = 0; i < M; ){
			trobat = false;
			while ( !trobat ){
				//busquem un acompanyant
				
				if(!treballadors[j].conductor){
					cotxes[c].afegirAcompanyant(treballadors[j]);

					trobat = true; //Sortim del while
					i++; //Ja hem col.locat un mes
					c = (c+1) % (N-M); //fem un bucle a modul N-M repartint al maxim els acompanyants
				}
				j++;
			}
		}
	}
        
        public void solucioInicial2(){
		int i; //Acompanyants colocats
		int j; //Iterador de treballadors
		int c; //Iterador de cotxes modular a N-M
		boolean trobat;
		
		Date date = new Date();
		long time = date.getTime();
		Random rnd = new Random(time);

		Cotxe cotxe;
		i=0;
                n_conductors=0;
		
                while ( n_conductors < 1 ){
			if (i == N) i = 0;
			if (rnd.nextBoolean() && !treballadors[i].conductor && treballadors[i].potConduir){
				treballadors[i].conductor = true;
				cotxe = new Cotxe(treballadors[i], n_conductors);
				cotxe.idConductor = i;
				
				cotxes[n_conductors] = cotxe;
                                treballadors[i].cotxe = cotxe;
				n_conductors++;
			}
			i++;
		}

		j = 0;
		c = 0;
		for( i = 0; i < N-n_conductors; ){
			trobat = false;
			while ( !trobat ){
				//busquem un acompanyant
				if(!treballadors[j].conductor){
					cotxes[0].afegirAcompanyant(treballadors[j]);

					trobat = true; //Sortim del while
					i++; //Ja hem col.locat un mes
					c = (c+1) % (N-M); //fem un bucle a modul N-M repartint al maxim els acompanyants
				}
				j++;
			}
		}
	}

	/* getters */
	public int getN(){
		return N;
	}
	public int getM(){
		return M;
	}
	public int getNM(){
		return N-M;
	}
	public int getMaxDistanciaRecorrida(){
		return maximaDistanciaConductor;
	}
	public int getNConductors(){
		return n_conductors;
	}
        public Treballador[] getTreballadors(){
            return treballadors;
        }
        public Cotxe[] getCotxes(){
            return cotxes;
        }
	public boolean avansar_sortida(int i){
            
                Treballador t = treballadors[i];
            
                int index = t.cotxe.ordre.lastIndexOf(t);
                if( index == -1 ) return false;
                if( t.cotxe.ordre.indexOf(t) == index-1) return false;
                
                
                
                Treballador t2;
                try{
                    t2 = (Treballador) t.cotxe.ordre.get(index-1);
                }catch (ArrayIndexOutOfBoundsException e ){
                    System.out.println("Avansar sortida nullpointer");
                    
                    System.out.println("treballador: " + t.id);
                    System.out.println("first: " + t.cotxe.ordre.indexOf(t));
                    System.out.println("index: " + index);
                    t2=null;
                    
                    t.cotxe.mostraCua();
                    
                    System.exit(0);
                }

                t.cotxe.ordre.set(index, t2);
                t.cotxe.ordre.set(index-1, t);               
                
		return true;
	}

	public boolean avansar_entrada( int i ){
                Treballador t = treballadors[i];
                int index = t.cotxe.ordre.indexOf(t);
                if(index == -1) return false;
                if(index == 0) return false;
                Treballador t2 = (Treballador) t.cotxe.ordre.get(index-1);
                
                t.cotxe.ordre.set(index, t2);
                t.cotxe.ordre.set(index-1, t);
                
		return true;
	}

	public boolean canviar_conductor( int c1, int c2 ){
                Treballador t = cotxes[c1].conductor;
                int idt = cotxes[c1].idConductor;
                cotxes[c1].conductor = cotxes[c2].conductor;
                cotxes[c1].idConductor = cotxes[c2].idConductor;
                
                cotxes[c2].conductor = t;
                cotxes[c2].idConductor = idt;
                
                cotxes[c1].conductor.cotxe = cotxes[c1];
                cotxes[c2].conductor.cotxe = cotxes[c2];
              
		return true;
	}

	public boolean canviar_de_cotxe( int i, int c){
		Treballador t = treballadors[i];
		if( t.cotxe.getId() == c ) return false;
                if( t.conductor ) return false;

                Cotxe cotxe_actual = t.cotxe;
		Cotxe cotxe = cotxes[c];
                if( cotxe_actual.size <= 0 ) return false;
		if( cotxe.size >= 2*M ) return false; //cas no possible, pero comprobat
               
		cotxe_actual.eliminarAcompanyant(t); 
                try{
                    cotxe.afegirAcompanyant(t);
                    t.cotxe = cotxe;
                }
                catch(Exception e){
                }
                
		return true;
	}
        
	public boolean no_conduir ( Treballador t ){
		if(!t.conductor) return false;
                
                boolean trobat;
                while(!t.cotxe.ordre.isEmpty()){
                    trobat = false;
                    for(int i=0; i < n_conductors; i++){
                        if( i != t.cotxe.id){
                            t.cotxe.eliminarAcompanyant(t);
                            cotxes[i].afegirAcompanyant((Treballador)t.cotxe.ordre.get(0));
                            break;
                        }
                    }
                        
                    if(!trobat) return false;
                }

		//Avansar cues de conductors
		n_conductors--;
		int i;

		
		//Fer que el treballador ja no es mes conductor
		t.conductor = false;

                for(i=t.cotxe.id; i < n_conductors; i++){
                    cotxes[i] = cotxes[i+1];
                    cotxes[i].id = i;
                }
                
		//Colocal el treballador en algun cotxe
		for (i=0; i < n_conductors; i++){
			if(cotxes[i].size < 2*M){
				cotxes[i].afegirAcompanyant(t);
                                break;
                        }
		}
		
                
                
		return true;
	}

	public boolean conduir( Treballador t ){
                
		if ( t.conductor ) return false;
                if ( n_conductors >= N-M ) return false; 
                if (!t.potConduir) return false;
                
                t.cotxe.eliminarAcompanyant(t);
		cotxes[n_conductors] = new Cotxe( t, n_conductors);
                
                n_conductors++;
                
		return true;
	}

	public boolean permutarConduccio( int i ){
		Treballador t = treballadors[i];
		if( !conduir( t ) ){
			if( !no_conduir( t ) ){
				return false;
			}
		}

		return true;
	}

	/*
	 Funcions utils
	 */


	int abs (int x){
		if (x<0) return -x;
		else return x;
	}

	int distancia_recorregut(Treballador t){
		return abs(t.desti.x - t.origen.x) + abs(t.desti.y - t.origen.y);
	}

	int distancia_dos_punts(Posicio a, Posicio b){
		return abs(a.getX()-b.getX()) + abs(a.getY()-b.getY());
	}
	int distanciaRecorregutCotxe(Cotxe c){

		int distanciaPrimer = 0;
		int distanciaAcompanyants = 0;
		int distanciaUltim = 0;
                
                if (!c.ordre.isEmpty()){

                    distanciaPrimer = distancia_dos_punts(c.conductor.origen,((Treballador)c.ordre.get(0)).origen);
                    
                    try{
                        distanciaUltim = distancia_dos_punts(
                                ((Treballador)c.ordre.get(c.ordre.size()-1)).desti,
                                c.conductor.desti);
                    }catch (NullPointerException e){
                        System.exit(0);
                    }catch (ArrayIndexOutOfBoundsException e){                        
                        System.exit(0);
                    }

                    Treballador a = null;
                    Treballador b = null;
                    boolean sortida = false;

                    Posicio anterior = ((Treballador)c.ordre.get(0)).origen;

                    for (int i=0;i<c.size;i++){

                            if (c.ordre.get(i) == a){
                                    a = null;
                                    sortida = true;
                            }
                            else if (c.ordre.get(i) == b){
                                    b = null;
                                    sortida = true;
                            }
                            else if (a == null){
                                    a = (Treballador) c.ordre.get(i);
                                    sortida = false;
                            }
                            else if (b == null){
                                    b = (Treballador) c.ordre.get(i);
                                    sortida = false;
                            }

                            int distanciaActual = distanciaAcompanyants;

                            Posicio actual;

                            if (sortida){
                                    actual = ((Treballador)c.ordre.get(i)).desti;
                            }
                            else{
                                    actual = ((Treballador)c.ordre.get(i)).origen;
                            }

                            distanciaAcompanyants += distancia_dos_punts(actual,anterior); 

                            anterior = actual;
                    }

                    return distanciaPrimer + distanciaAcompanyants + distanciaUltim;
                }
                else{
                    return distancia_dos_punts(c.conductor.origen,c.conductor.desti);
                }
                  
	}

	boolean esSolucioValida(){
		
		for (int i=0;i<n_conductors;i++){
			if (distanciaRecorregutCotxe(cotxes[i]) > maximaDistanciaConductor){
				return false;
			}
		}

		return true;
	}

        public void imprimeixSolucio(){
            int i;
            for (i=0; i<N; i++){ 
			Treballador t = treballadors[i];

			// Prints dels resultats aleatoris
			System.out.println("=========Treballador " + i+ " ========");
			System.out.println("origen amb x: " + t.getOrigen().x + " y: " + t.getOrigen().y);
			System.out.println("desti amb x: " + t.getDesti().x + " y: " + t.getDesti().y);

			if (t.conductor){
				System.out.println("Es conductor");
			}

			System.out.println("======================================");

			System.out.println("===========Distancia camí=============");

			System.out.println(distancia_recorregut(t));
		}

            
                System.out.println();
                System.out.println();
                System.out.println("Nova solucio!");
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<");
                
		for(i = 0; i < n_conductors; i++ ){                    
                        System.out.println();
                        System.out.println("COTXE " + i);
                        System.out.println("==============================");
                    
			Cotxe c = cotxes[i];
			System.out.println("Cotxe numero " + i );
			System.out.println("Conductor del cotxe" + c.idConductor);
			for( int j=0; j<c.size; j++ ){
				System.out.print(((Treballador)c.ordre.get(j)).id + " ");	
			}
			System.out.println("eol");

			System.out.println("Distancia recorreguda cotxe "+ i);
			System.out.println(this.distanciaRecorregutCotxe(c));
                        
                        System.out.println("==============================");
		}
                
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println();
                System.out.println();

        }

}
