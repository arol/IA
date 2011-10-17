//package IA.probTreballadors;

import java.util.Random;
import java.util.Queue;

public class probTreballadorsCiutat {

	public class Treballador{
		boolean conductor;
		Posicio origen, desti;

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
		Queue ordre;

		public Cotxe (Treballador c){
			conductor = c;
		}

		/*x es l'id del treballador al array de treballadors*/
		public void afegirAcompanyant(int x){
			ordre.add(x);
		}
		public void prioritzarEntrada(int x){
			/*Realment necessitem una cua? Amb les cues de java natives no podem avançar elements.*/
		}
		public void prioritzarSortida(int x){
			/*Realment necessitem una cua? Amb les cues de java natives no podem avançar elements.*/
		}
	}

	private int N; //Nombre de treballadors
	private int M; //Nombre de treballadors conductors.

	private Cotxe [] cotxes;
	private Treballador [] treballador;
	private int [] distanciaRecorrida;

	private static int maxTrabajadoresCoche = 2;
	private static int maximaDistanciaConductor = 3000;

}
