package IA.probTreballadors;

import java.util.*;

public class probTreballadorsCiutat {

	public class Treballador{
		boolean conductor;
		private Posicio origen, desti;
		Cotxe cotxe;

		int ie,is;

		public Treballador(Posicio origen, Posicio desti){
			conductor = false;
			this.origen = origen;
			this.desti = desti;
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


                public Cotxe(int id,ArrayList ordre,Posicio origen,Posicio desti,int idconductor,int size){
                    this.ordre = (ArrayList) ordre.clone();

                    this.id = id;
                    this.size = size;
                    this.conductor = new Treballador(origen,desti);
                    this.conductor.setConductor(true);
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
                    //System.out.println("Cotxe: "+id);
                    
                    //System.out.print("Ordre: ");
                    
                    for (int i = 0; i < size; i++) {
                        //System.out.print(ordre.get(i)+" ");
                    }
                    
                    //System.out.println(" ");
                    //System.out.println("Size: "+ size);
                }
		/*x es l'id del treballador al array de treballadors*/
		public void afegirAcompanyant(int x){
                        //System.out.println("Afegint acompanyant " + x + " al cotxe " + id);
                        ////System.out.println(size + " < " + 2*M + " en el cotxe " + id);
			ordre.add(x);
			ordre.add(x);

			treballadors[x].ie = size;
			treballadors[x].is = size+1;
			treballadors[x].cotxe = cotxes[id];

                        //System.out.println( "    Actual size is " + size );
			size+=2;
                        //System.out.println( "    Size after adding is " + size );

		}

		public void eliminarAcompanyant(int i){
                        //System.out.println( "Eliminant acompanyant " + i + " del cotxe " + id );
                        
                        Treballador t = treballadors[i];
			
                        //System.out.println( "    Actual size is " + size );
                        
			int count = 0;
			size-=2;
                        //System.out.println("");
                        //System.out.println("size: "+size);
                        //System.out.println("");
			for(Integer j=0; j < size; j++){
				if(count!=2 && ordre.get(j) == (Integer)i){
					count++;
				}
				
				if ( count == 1 ) {
					ordre.add(j,ordre.get(j+1));
					if(ordre.get(j) == (Integer)i){
						count++;
						ordre.add(j,ordre.get(j+2));
					}
				}else if ( count == 2 ){
					ordre.add(j,ordre.get(j+2));
				}
			}
                        
                        //if(id==1) System.out.println("    Result size after delete is " + size );

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
		//distanciaRecorrida = new int[N-M];

		int i;

		for (i=0; i<N; i++){ 
			int x_ori = rnd.nextInt(100);
			int y_ori = rnd.nextInt(100);
			Posicio origen = new Posicio (x_ori,y_ori);

                        //rnd = new Random(System.currentTimeMillis());
                        
			int x_desti = rnd.nextInt(100);
			int y_desti = rnd.nextInt(100);
			Posicio desti = new Posicio (x_desti,y_desti);

			Treballador t = new Treballador(origen,desti);

			treballadors[i] = t;

		}

                for (i=0; i<N; i++){ 
			Treballador t = treballadors[i];

			// Prints dels resultats aleatoris
			//System.out.println("=========Treballador " + i+ " ========");
			//System.out.println("origen amb x: " + t.origen.x + " y: " + t.origen.y);
			//System.out.println("desti amb x: " + t.desti.x + " y: " + t.desti.y);

			if (t.conductor){
				//System.out.println("SOC CONDUCTOR FUCK YEAHH!");
			}

			//System.out.println("======================================");

			//System.out.println("===========Distancia camí=============");

			//System.out.println(distancia_recorregut(t));
		}

            
		//		Els conductors s'han d'assignar a la solucio inicial
		//i = 0;
		//while (n_conductors<N-M){
		//	if (i == N) i = 0;
		//	if (rnd.nextBoolean()){
		//		treballadors[i].conductor = true;
		//		Cotxe c = new Cotxe(treballadors[i], n_cotxes);
		//		c.idConductor = i;
		//		treballadors[i].cotxe = n_cotxes;
		//		cotxes[n_cotxes] = c;
		//		n_cotxes++;
		//		n_conductors++;
		//	}
		//	i++;
		//}
	
// Espai de proves a la constructora
//		solucioInicial();
//		recalcularDistanciesCotxes();
//
//		if (esSolucioValida()) //System.out.println("Es solucio valida");
//		else //System.out.println("No es solucio valida");
//
//
//		for (i=0; i<N; i++){ 
//			Treballador t = treballadors[i];
//
//			/* Prints dels resultats aleatoris */
//			//System.out.println("=========Treballador " + i+ " ========");
//			//System.out.println("origen amb x: " + t.origen.x + " y: " + t.origen.y);
//			//System.out.println("desti amb x: " + t.desti.x + " y: " + t.desti.y);
//
//			if (t.conductor){
//				//System.out.println("SOC CONDUCTOR FUCK YEAHH!");
//			}
//
//			//System.out.println("======================================");
//
//			//System.out.println("===========Distancia camí=============");
//
//			//System.out.println(distancia_recorregut(t));
//		}
//
//		for(i = 0; i < N-M; i++ ){
//			//obj.getClass().isInstance(Statement.class);
//			
//			Cotxe c = cotxes[i];
//			//System.out.println("Cotxe numero " + i );
//			//System.out.println("Conductor del cotxe" + c.idConductor);
//			for( int j=0; j<c.size; j++ ){
//				//System.out.print(c.ordre[j]+" ");	
//			}
//			//System.out.println("eol");
//
//			//System.out.println("Distancia recorreguda cotxe "+ i);
//			//System.out.println(distanciaRecorrida[i]);
//		}
//
//		canviar_de_cotxe(5, 0);
//		canviar_de_cotxe(8, 0);
//		no_conduir (4);
//		
//		
//		for(i = 0; i < n_conductors; i++ ){
//			//obj.getClass().isInstance(Statement.class);
//			
//			Cotxe c = cotxes[i];
//			//System.out.println("Cotxe numero " + i );
//			for( int j=0; j<c.size; j++ ){
//				//System.out.print(c.ordre[j]+" ");	
//			}
//			//System.out.println("eol");
//		}
	}
        
        public probTreballadorsCiutat( probTreballadorsCiutat o ){
//              N = 10;
//		M = 7;

                N = o.getN();
                M = o.getM();
                n_conductors = o.getNConductors();
                
                treballadors = new Treballador[N];
                cotxes = new Cotxe[N-M];
   //             distanciaRecorrida = new int[N-M];
                //System.arraycopy(o.getTreballadors(), 0, treballadors, 0, N);
                // o
                
                Cotxe[] auxc = o.getCotxes();
                for( int i=0; i < n_conductors; i++){
                    Cotxe c = new Cotxe(auxc[i].id,
                                                      auxc[i].ordre,
                                                      auxc[i].conductor.origen,
                                                      auxc[i].conductor.desti,
                                                      auxc[i].idConductor,
                                                      auxc[i].size);
                    cotxes[auxc[i].id] = c;
                    
                }       
                
                Treballador[] aux = o.getTreballadors();
                for(int i=0; i < N; i++) {
                    treballadors[i] = new Treballador(aux[i].origen, aux[i].desti);
                    treballadors[i].conductor = aux[i].conductor;  
                    
                    try{
                        treballadors[i].cotxe = cotxes[aux[i].cotxe.getId()];
                    }catch( NullPointerException e ){
                        //System.out.println( "LAPUTA!!! " );
                        //System.out.println( "Treballador: " + i);
                        //System.out.println( "Cotxe: " + aux[i].cotxe);
                        
                        System.exit(0);
                    }
                    
                    
                    treballadors[i].ie = aux[i].ie;         
                    treballadors[i].is = aux[i].is;
                }

                
    //            distanciaRecorrida = o.getArrayDistancies().clone();
            
//		Date date = new Date();
//		long time = date.getTime();
//		Random rnd = new Random(time);
//
//		n_conductors = 0;
//
//		treballadors = new Treballador[N];
//		cotxes = new Cotxe[N-M];
//		distanciaRecorrida = new int[N-M];
//
//		int i;
//
//		for (i=0; i<N; i++){ 
//			int x_ori = rnd.nextInt(100);
//			int y_ori = rnd.nextInt(100);
//			Posicio origen = new Posicio (x_ori,y_ori);
//
//			int x_desti = rnd.nextInt(100);
//			int y_desti = rnd.nextInt(100);
//			Posicio desti = new Posicio (x_desti,y_desti);
//
//			Treballador t = new Treballador(origen,desti);
//
//			treballadors[i] = t;
//
//		}
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
			if (rnd.nextBoolean() && !treballadors[i].conductor){
//                                //System.out.println( "El treballador " + i + " condueix al cotxe " + n_conductors );
                                
				treballadors[i].conductor = true;
				cotxe = new Cotxe(treballadors[i], n_conductors);
				cotxe.idConductor = i;
				
				cotxes[n_conductors] = cotxe;
                                treballadors[i].cotxe = cotxe;
				//n_cotxes++; --> si n_cotxes = n_conductors ja no fa falta
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
				
//				//System.out.println("Mirant treballador " + j);

				if(!treballadors[j].conductor){
//					//System.out.println("No es conductor");
//					//System.out.println("Va al conductor " + c);
					cotxes[c].afegirAcompanyant(j);

					trobat = true; //Sortim del while
					i++; //Ja hem col.locat un mes
					c = (c+1) % (N-M+1); //fem un bucle a modul N-M repartint al maxim els acompanyants
				}
				j++;
			}
		}
                //this.imprimeixSolucio();
                //System.exit(0);
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
	/*public int getRecorregutCotxe(int i){
		return distanciaRecorrida[i];	
	}*/
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
        /*
        public int[] getArrayDistancies(){
            return distanciaRecorrida;
        }*/
	/*
		Operadors i funcions de transformacio	
	 */
	public boolean avansar_sortida(int i){
                //System.out.println( "Avansant sortida per " + i );                
		Treballador t = treballadors[i];
		if( t.is == t.ie+1 ) return false;
		if( t.is == 0 ) return false;
	
		Treballador t2 = treballadors[((Integer)t.cotxe.ordre.get(t.is-1)).intValue()];
		
                swap_cua(t.cotxe, t.is, t.is-1);

		//Canviem les seves posicion de sortida
		int aux;
                
		if(t2.ie == t.is-1){
			aux = t2.ie;
			t2.ie = t.is;
			t.is = aux;
		}else if(t2.is == t.is-1){
			aux = t2.is;
			t2.is = t.is;
			t.is = aux;
		
                }
                //this.recalcularDistanciesCotxes();
                
		return true;
	}

	public boolean avansar_entrada( int i ){
                //System.out.println( "Avansant entrada per " + i );
                
		////System.out.println("---->");
		Treballador t = treballadors[i];
                

                if(t.conductor) return false;
                
                try{
                    t.cotxe.mostraCua();
                }catch ( NullPointerException e ){
                    //System.out.println( "NULL POINTER EXCEPTION!!! ? AVANSAR ENTRADA" );
                    //System.out.println(e);
                    this.imprimeixSolucio();
                    //System.out.println( "Avansant entrada per " + i );
                    //System.out.println( "Cotxe: " + t.cotxe );
                    //System.out.println( "Conductor? " + t.conductor );
                    //System.out.println( "numero de conductors " + n_conductors );
                    System.exit(0);
                    
                }
                
		////System.out.println("    Avansant numero " + i + " amb ie:" + t.ie + " i is:" + t.is);	
		if( t.ie == 0 ) return false;
	
                
                int index = ((Integer) t.cotxe.ordre.get(t.ie-1)).intValue();
		Treballador t2 = treballadors[index];
                
                
                
                
		swap_cua(t.cotxe, t.ie, t.ie-1);

		//Canviem les seves posicions d'entradai
		int aux;
		if(t2.ie == t.ie-1){
			aux = t2.ie;
			t2.ie = t.ie;
			t.ie = aux;
		}else if(t2.is == t.ie-1){
			aux = t2.is;
			t2.is = t.ie;
			t.ie = aux;
		}
                
		////System.out.println("Nous ie "+ t.ie +" i " + t2.ie );
                t.cotxe.mostraCua();
                
		return true;
	}

	public boolean canviar_conductor( int c1, int c2 ){
                //System.out.println( "Canviant conductor entre " + c1 + " i " + c2 );
//		int[] aux;

//		aux = new int[2*M];
//
//		aux =  cotxes[c1].ordre;
//
//		cotxes[c1].ordre = cotxes[c2].ordre;
//		cotxes[c2].ordre = aux;
//
//		int i;
//		i = cotxes[c1].size;
//		cotxes[c1].size = cotxes[c2].size;
//		cotxes[c2].size = i;

                Treballador t = cotxes[c1].conductor;
                int idt = cotxes[c1].idConductor;
                cotxes[c1].conductor = cotxes[c2].conductor;
                cotxes[c1].idConductor = cotxes[c2].idConductor;
                
                cotxes[c2].conductor = t;
                cotxes[c2].idConductor = idt;
                

                //System.out.println( "{{ El conductor del cotxe " + c1 + " es " + cotxes[c1].idConductor);
                //System.out.println( "{{ El conductor del cotxe " + c2 + " es " + cotxes[c2].idConductor);
                
                
//                int i;
//		for( i = 0; i < N && treballadors[i].conductor; i++ ) { 
//			if ( treballadors[i].cotxe == c1 ){
//				treballadors[i].cotxe = c2;
//			}else if ( treballadors[i].cotxe == c2 ){
//				treballadors[i].cotxe = c1;
//			}
//                }
                
                cotxes[c1].conductor.cotxe = cotxes[c1];
                cotxes[c2].conductor.cotxe = cotxes[c2];
              
		return true;
	}

	public boolean canviar_de_cotxe( int i, int c){
		Treballador t = treballadors[i];
                //System.out.println( "Canviant al treballador " + i + " al cotxe " + c +" desde el cotxe "+t.cotxe.getId());
                //System.out.println( "Cotxe actual " + t.cotxe);
		if( t.cotxe.getId() == c ) return false;
                if( t.conductor ) return false;

                Cotxe cotxe_actual = t.cotxe;
		Cotxe cotxe = cotxes[c];
                if( cotxe_actual.size <= 0 ) return false;
		if( cotxe.size >= 2*M ) return false; //cas no possible, pero comprobat
               
                cotxe_actual.mostraCua();
		cotxe_actual.eliminarAcompanyant(i); 
                cotxe_actual.mostraCua();
                
                cotxe.mostraCua();
                //System.out.println("i: "+i);
                //System.out.println("cotxe de i: "+t.cotxe.getId());
                try{
                    cotxe.afegirAcompanyant(i);
                    //System.out.println("i: "+i);
                    //System.out.println("cotxe de i despres del canvi: "+cotxe.getId());
                    //System.out.println("referencia del cotxe de i despres del canvi: "+cotxe);
                    cotxe.mostraCua();
                    treballadors[i].cotxe = cotxe;
                    //System.out.println("cotxe al array:"+treballadors[i].cotxe);
                }
                catch(Exception e){
                    //System.exit(0);
                }
                
		return true;
	}
        
	public boolean no_conduir ( int idt ){
		Treballador t = treballadors[idt];
		if(!t.conductor) return false;

		if(t.cotxe.size != 0) return false;

		//Avansar cues de conductors
		n_conductors--;
		int i;

		
		//Fer que el treballador ja no es mes conductor
		t.conductor = false;

                for(i=t.cotxe.id; i < n_conductors; i++){
                    cotxes[i] = cotxes[i+1];
                    cotxes[i].id = i;
//                    for(int j=0; j < N; j++){
//                        if(treballadors[j].cotxe.id == i+1)
//                            treballadors[j].cotxe.id = i;
//                    }
                }
                
		//Colocal el treballador en algun cotxe
		for (i=0; i < n_conductors; i++){
			if(cotxes[i].size < 2*M){
				cotxes[i].afegirAcompanyant(idt);
                                break;
                        }
		}
		
                
                
		return true;
	}

	public boolean conduir( int idt ){
                //System.out.println("Fent conduir a " + idt);
		Treballador t = treballadors[idt];
                //System.out.println("Es conductor? " + t.conductor);
                
		if ( t.conductor ) return false;
		//if (!t.pot_conduir ) return false;
                if ( n_conductors >= N-M ) return false; 


		//System.out.println("nou cotxe: " + n_conductors);
                
                t.cotxe.eliminarAcompanyant(idt);
		cotxes[n_conductors] = new Cotxe( t, n_conductors);
//		t.cotxe.id = n_conductors;

                
                //System.out.println( "{{ El conductor del cotxe " + n_conductors + " es " + cotxes[n_conductors].idConductor);
                
                n_conductors++;
                
		return true;
	}

	public boolean permutarConduccio( int i ){
		//Treballador t = treballadors[i];
		if( !conduir( i ) ){
			if( !no_conduir( i ) ){
				return false;
			}
		}

		return true;
	}

	/*
	 Funcions utils que ens faran falta
	 */

	void swap_cua( Cotxe c, int index1, int index2 ) {
		////System.out.println("    -----> SWAP");
		////System.out.println("        cotxe:" + c + " index1:" + index1 + "index2:" + index2 );
		
		Integer aux = (Integer)c.ordre.get(index1);
		c.ordre.add(index1,c.ordre.get(index2));
		c.ordre.add(index2,aux);

		//aux = treballadors[index1].ie;
		//int aux2 = treballadors[index1].is;
		//treballadors[index1].ie = treballadors[index2].ie;
		//treballadors[index1].is = treballadors[index2].is;	
		//treballadors[index1].ie = aux;
		//treballadors[index1].is = aux2;
	}

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

		/*Depenem de com montem l'estructura dels acompanyants*/
                try{
                    //System.out.println(c.size);
                }catch( NullPointerException e ){
                    //System.out.println( "NULL POINTER!!! ? distanciarecorregut cotxe" );
                        //System.out.println(e);
                        this.imprimeixSolucio();
                        //System.out.println( "Cotxe: " + c.id );
                        //System.out.println( "Size: " + c.size );
                    
                }
                
                
                if (c.size != 0){

                    distanciaPrimer = distancia_dos_punts(c.conductor.origen,treballadors[((Integer)c.ordre.get(0)).intValue()].origen);
                    
                    try{
                        distanciaUltim = distancia_dos_punts(treballadors[((Integer)c.ordre.get(c.ordre.size()-1)).intValue()].desti,c.conductor.desti);
                    }catch (NullPointerException e){
                        //System.out.println( "NULL POINTER!!! ? DISTANCIA_DOS_PUNTS" );
                        //System.out.println(e);
                        this.imprimeixSolucio();
                        //System.out.println( "Cotxe: " + c.id );
                        //System.out.println( "Size: " + c.size );
                        
                        System.exit(0);
                    }catch (ArrayIndexOutOfBoundsException e){
                        //System.out.println( "ARRAY IOB!!! ? DISTANCIA_DOS_PUNTS" );
                        //System.out.println(e);
                        this.imprimeixSolucio();
                        //System.out.println( "Cotxe: " + c.id );
                        //System.out.println( "Size: " + c.size );
                        
                        System.exit(0);
                    }

                    int a=0;
                    int b=0;
                    boolean sortida = false;

                    Posicio anterior = treballadors[((Integer)c.ordre.get(0)).intValue()].origen;

                    for (int i=0;i<c.size;i++){

                            if (((Integer)c.ordre.get(i)).intValue() == a){
                                    a = 0;
                                    sortida = true;
                            }
                            else if	(((Integer)c.ordre.get(i)).intValue() == b){
                                    b = 0;
                                    sortida = true;
                            }
                            else if (a == 0){
                                    a = ((Integer)c.ordre.get(i)).intValue();
                                    sortida = false;
                            }
                            else if (b == 0){
                                    b = ((Integer)c.ordre.get(i)).intValue();
                                    sortida = false;
                            }

                            int distanciaActual = distanciaAcompanyants;

                            Posicio actual;

                            if (sortida){
                                    actual = treballadors[((Integer)c.ordre.get(i)).intValue()].desti;
                            }
                            else{
                                    actual = treballadors[((Integer)c.ordre.get(i)).intValue()].origen;
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
/*
	public int recalcularDistanciesCotxes(){
		int total = 0;
		for (int i=0;i<n_conductors ;i++) {
			distanciaRecorrida[i] = distanciaRecorregutCotxe(cotxes[i]);
			total += distanciaRecorrida[i];
		}
		return total;
	}	
*/
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
				//System.out.println("SOC CONDUCTOR FUCK YEAHH!");
			}

			System.out.println("======================================");

			System.out.println("===========Distancia cam�=============");

			System.out.println(distancia_recorregut(t));
		}

            
                System.out.println();
                System.out.println();
                System.out.println("Nova solucio!");
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<");
                
		for(i = 0; i < n_conductors; i++ ){
			//obj.getClass().isInstance(Statement.class);
                    
                        System.out.println();
                        System.out.println("COTXE " + i);
                        System.out.println("==============================");
                    
			Cotxe c = cotxes[i];
			System.out.println("Cotxe numero " + i );
			System.out.println("Conductor del cotxe" + c.idConductor);
			for( int j=0; j<c.size; j++ ){
				System.out.print(((Integer)c.ordre.get(j)).intValue() + " ");	
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
