package objetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AsignacionHeuristica {
	
	    private HashMap <Integer,ArrayList<Integer>> conjuntoDeManzanas;
	    private ArrayList<Set<Integer>> manzanas;
	
    public AsignacionHeuristica (RadioCensal radio) {
    	
    	if(BFS.esConexo(radio)) {
    		this.conjuntoDeManzanas = new HashMap <Integer,ArrayList<Integer>>();
    		this.manzanas = radio.getVecinos();
    	}
    	else
    		throw new IllegalArgumentException("El radio tiene manzanas sin conectar");
    }

    private ArrayList<Integer> armarConjuntoManzanas(int s) {
		ArrayList<Integer> conjuntoManzanas = new ArrayList<>();
		Iterator<Integer> it = manzanas.get(s).iterator();
		agregarManzana(s,conjuntoManzanas);
		
		if(existeManzanaEnConjunto(s)) {
			return null;
		}
		
		while (it.hasNext()) {
			Integer manzana = it.next();
			agregarManzana(manzana,conjuntoManzanas);
			}
		
		return conjuntoManzanas;
	}
    // Si es posible,agrega una manzana al conjunto de manzanas pasado por parametro
	private void  agregarManzana(int i, ArrayList<Integer> conjuntoManzanas) {
		
		if(conjuntoManzanas.size()<3 && !conjuntoManzanas.contains(i) && !existeManzanaEnConjunto(i)) {
			conjuntoManzanas.add(i);
		}
	}
	
	//Carga el hashMap con los conjuntos de manzanas  sin repetir
	private void cargarConjuntosManzanas() {
		for (int i = 0; i < manzanas.size(); i++) {
			if(armarConjuntoManzanas(i) != null) {
				conjuntoDeManzanas.put(i, new ArrayList<Integer>(armarConjuntoManzanas(i)));
			}
		}
	}

	// Verifica que  la manzana no exista en conjuntoManzanass para que no se repita
	private boolean existeManzanaEnConjunto(int s) {
		for(ArrayList<Integer> manzanasContiguas : conjuntoDeManzanas.values())
			for( Integer manzanas : manzanasContiguas)
				if(manzanas==s)
					return true;
				
		return false;	
    }

	public HashMap<Integer, ArrayList<Integer>> obtenerConjuntoManzanas() {
		cargarConjuntosManzanas();
		return this.conjuntoDeManzanas;
	}

	@Override
	public String toString() {
		return "Heuristica [" + conjuntoDeManzanas + "]";
	}
	
	
}
