//package IA.probTreballadors;

import java.util.*;
import java.util.Queue;

public class probTreballadorsCiutat {

	public class Treballador{
		boolean conductor;
		Posicio origen, desti;
		public int cotxe;

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
		int ordre[];
		int size;
		int idConductor;

		public Cotxe (Treballador c){
			conductor = c;
			ordre = new int[2*M];
			size=0;
		}

		public Cotxe (Treballador t, int c){
			conductor = t;
			ordre = new int[M];
			id=c;
			size=0;
		}

		/*x es l'id del treballador al array de treballadors*/
		public void afegirAcompanyant(int x){
			ordre[size]=x;
			ordre[size+1]=x;

			treballadors[x].ie = size;
			treballadors[x].is = size+1;
			treballadors[x].cotxe = id;

			size+=2;
		}
	}

	private int N; //Nombre de treballadors
	private int M; //Nombre de treballadors conductors.

	private Cotxe [] cotxes;
	private Treballador [] treballadors;
	private int [] distanciaRecorrida;

	private static int maxTrabajadoresCoche = 2;
	private static int maximaDistanciaConductor = 300;
	

	public probTreballadorsCiutat(){
		N = 10;
		M = 7;

		Date date = new Date();
		long time = date.getTime();

		Random rnd = new Random(time);

		int n_conductors = 0;
		int n_cotxes = 0;

		treballadors = new Treballador[N];
		cotxes = new Cotxe[N-M];
		distanciaRecorrida = new int[N-M];

		for (int i=0; i<N; i++){ 
			int x_ori = rnd.nextInt(100);
			int y_ori = rnd.nextInt(100);
			Posicio origen = new Posicio (x_ori,y_ori);

			int x_desti = rnd.nextInt(100);
			int y_desti = rnd.nextInt(100);
			Posicio desti = new Posicio (x_desti,y_desti);

			Treballador t = new Treballador(origen,desti);

			treballadors[i] = t;

			if (n_conductors < N-M && rnd.nextBoolean()){
				treballadors[i].conductor = true;
				Cotxe c = new Cotxe(t, n_cotxes);
				c.idConductor = i;
				treballadors[i].cotxe = n_cotxes;
				cotxes[n_cotxes] = c;
				n_cotxes++;
				n_conductors++;
			}
		}
		
		solucioInicial();
		recalcularDistanciesCotxes();

		for (int i=0; i<N; i++){ 
			Treballador t = treballadors[i];

			/* Prints dels resultats aleatoris */
			System.out.println("=========Treballador " + i+ " ========");
			System.out.println("origen amb x: " + t.origen.x + " y: " + t.origen.y);
			System.out.println("desti amb x: " + t.desti.x + " y: " + t.desti.y);

			if (t.conductor){
				System.out.println("SOC CONDUCTOR FUCK YEAHH!");
			}

			System.out.println("======================================");

			System.out.println("===========Distancia camí=============");

			System.out.println(distancia_recorregut(t));
		}

		for(int i = 0; i < N-M; i++ ){
			//obj.getClass().isInstance(Statement.class);
			
			Cotxe c = cotxes[i];
			System.out.println("Cotxe numero " + i );
			System.out.println("Conductor del cotxe" + c.idConductor);
			for( int j=0; j<c.size; j++ ){
				System.out.print(c.ordre[j]+" ");	
			}
			System.out.println("eol");

			System.out.println("Distancia recorreguda cotxe "+ i);
			System.out.println(distanciaRecorrida[i]);
		}

	}

	private void solucioInicial(){
		int i; //Acompanyants colocats
		int j; //Iterador de treballadors
		int c; //Iterador de cotxes modular a N-M
		boolean trobat;

		j = 0;
		c = 0;
		for( i = 0; i < M; ){
			trobat = false;
			while ( !trobat ){
				//busquem un acompanyant
				
				//System.out.println("Mirant treballador " + j);

				if(!treballadors[j].conductor){
					//System.out.println("No es conductor");
					//System.out.println("Va al conductor " + c);
					cotxes[c].afegirAcompanyant(j);

					trobat = true; //Sortim del while
					i++; //Ja hem col.locat un mes
					c = (c+1) % (N-M); //fem un bucle a modul N-M repartint al maxim els acompanyants
				}
				j++;
			}
		}
	}

	/*
		Operadors i funcions de transformacio	
	 */
	public boolean avansar_sortida(int i){
		Treballador t = treballadors[i];
		if( t.is == t.ie+1 ) return false;
		if( t.is == 0 ) return false;

		swap_cua(t.cotxe, t.is, t.is-1);
		return true;
	}

	public boolean avansar_entrada( int i ){
		Treballador t = treballadors[i];

		if( t.ie == 0 ) return false;
		swap_cua(t.cotxe, t.is, t.is-1);
		return true;
	}

	public boolean canviar_conductor( int c1, int c2 ){
		int[] aux;
		aux = new int[2*M];

		aux =  cotxes[c1].ordre;

		cotxes[c1].ordre = cotxes[c2].ordre;
		cotxes[c2].ordre = aux;

		int i;
		for( i = 0; i < N && treballadors[i].conductor; i++ ) { 
			if ( treballadors[i].cotxe == c1 ){
				treballadors[i].cotxe = c2;
			}else if ( treballadors[i].cotxe == c2 ){
				treballadors[i].cotxe = c1;
			}
		}
		return true;
	}


	/*
	 Funcions utils que ens faran falta
	 */

	void swap_cua( int c, int index1, int index2 ) {
		int aux = cotxes[c].ordre[index1];
		cotxes[c].ordre[index1] = cotxes[c].ordre[index2];
		cotxes[c].ordre[index2] = aux;

		aux = treballadors[index1].ie;
		int aux2 = treballadors[index1].is;
		treballadors[index1].ie = treballadors[index2].ie;
		treballadors[index1].is = treballadors[index2].is;	
		treballadors[index1].ie = aux;
		treballadors[index1].is = aux2;
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

		distanciaPrimer = distancia_dos_punts(c.conductor.origen,treballadors[c.ordre[0]].origen);
		distanciaUltim = distancia_dos_punts(treballadors[c.ordre[c.size-1]].desti,c.conductor.desti);

		System.out.println("distancia primer: " + distanciaPrimer);
		System.out.println("distancia ultim: " + distanciaUltim);

		int a=0;
		int b=0;
		boolean sortida = false;

		Posicio anterior = treballadors[c.ordre[0]].origen;

		for (int i=0;i<c.size;i++){

			if (c.ordre[i] == a){
				a = 0;
				sortida = true;
			}
			else if	(c.ordre[i] == b){
				b = 0;
				sortida = true;
			}
			else if (a == 0){
				a = c.ordre[i];
				sortida = false;
			}
			else if (b == 0){
				b = c.ordre[i];
				sortida = false;
			}

			int distanciaActual = distanciaAcompanyants;

			Posicio actual;

			if (sortida){
				actual = treballadors[c.ordre[i]].desti;
			}
			else{
				actual = treballadors[c.ordre[i]].origen;
			}

			distanciaAcompanyants += distancia_dos_punts(actual,anterior); 

			anterior = actual;
		}
		
		return distanciaPrimer + distanciaAcompanyants + distanciaUltim;
	}
	void recalcularDistanciesCotxes(){
		for (int i=0;i<N-M ;i++) distanciaRecorrida[i] = distanciaRecorregutCotxe(cotxes[i]);
	}	
	
}

