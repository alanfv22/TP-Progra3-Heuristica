package objetos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {

	private static List<Integer> L;
	private static boolean[] marcados;
	
	// Dado un grafo, retorna True si es conexo
	// Genera una excepcion IllegalArgumentException si el grafo es nulo
	public static boolean esConexo(RadioCensal g) {
		if (g==null)
			throw new IllegalArgumentException("El radio censal no puede ser nulo!");
		if (g.getCantManzanas()==0)
			return true;
		return alcanzables(g,0).size()==g.getCantManzanas();
	}

	// Retorna un conjunto conteniendo todos los vertices alcanzables
	// en un grafo, desde un vertice origen dado
	public static Set<Integer> alcanzables(RadioCensal g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();
		
		// Inicializar las estructuras para la busqueda
		inicializarBusqueda(g, origen);
		
		while (L.size()>0) {
			int i = seleccionarYMarcarVertice(ret);
			agregarVecinosNoMarcados(g, i);
			removerSeleccionado();
		}
		
		return ret;
	}
	
	
	private static void removerSeleccionado() {
		L.remove(0);
	}

	private static int seleccionarYMarcarVertice(Set<Integer> ret) {
		int i = L.get(0);
		marcados[i] = true;
		ret.add(i);
		return i;
	}

	private static void agregarVecinosNoMarcados(RadioCensal g, int i) {
		for (int vecino: g.vecinos(i)) {
			if (!marcados[vecino] && !L.contains(vecino))
				L.add(vecino);
		}
	}

	private static void inicializarBusqueda(RadioCensal g, int origen) {
		L = new LinkedList<Integer>();
		L.add(origen);
		marcados = new boolean[g.getCantManzanas()];
	}

}
