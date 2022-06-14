package objetos;

import java.util.ArrayList;
import java.util.HashMap;


public class Solucion {
	
	private HashMap<String,ArrayList<Integer>> asignacionCensistas;
	
	public Solucion() {
		asignacionCensistas = new HashMap<>();
	}
	public void asignarCensista(String nombre, ArrayList <Integer> conjuntoManzanas) {
		asignacionCensistas.put(nombre,  conjuntoManzanas);
	}
	
	@Override
	public String toString() {
		 StringBuilder  solucion = new  StringBuilder ();
		
		for(String ID : asignacionCensistas.keySet())
			solucion.append(" ").append("\n").append(" Censista: ").append(ID).append(" ").append("\n").
			append(" Conjunto de Manzanas asignadas: ").append(asignacionCensistas.get(ID)).
			append("\n").append("\n").append("\n").append("\n")
			.append("-----------------------------------------------").append("\n");
			
		return solucion.toString();
	}
	
	
}
