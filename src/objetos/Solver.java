package objetos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;



public class Solver {
	private HashMap<Integer, ArrayList<Integer>> conjuntoDeManzanas;
	private ArrayList<Censista> censistas;
	
	public Solver(ArrayList<Censista> censistas,HashMap<Integer, ArrayList<Integer>> metodoDeAsignacion) {
		this.censistas=censistas;
		this.conjuntoDeManzanas=metodoDeAsignacion;
		Collections.shuffle(this.censistas); // desordeno la lista para que la seleccion de censista sea aleatoria
	}
	
	public Solucion resolver() {
		
		Solucion ret = new Solucion ();
	
		int i=0;
		
		for(ArrayList<Integer> conjuntoManzana : this.conjuntoDeManzanas.values()) { // Recorro hasta tamano de conjuntoDemanzanas y asigno censista
			ret.asignarCensista(censistas.get(i).getNombre(),conjuntoManzana);
			i++;
		}
		return ret;
	}
	
	@Override
	public String toString() {
		return resolver().toString();
	}
	

	
}
