package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import archivos.LecturaJson;
import objetos.AsignacionHeuristica;
import objetos.Censista;
import objetos.RadioCensal;
import objetos.Solver;

public class Controlador {

	Solver solver;
	RadioCensal radio;
	AsignacionHeuristica heuristica;


	public Controlador() {
		this.radio = LecturaJson.nuevoRadioCensal();
		heuristica = new AsignacionHeuristica(radio);


	}

	public void resolverPorHeuristica() {
		solver = new Solver(radio.getCencistas(), heuristica.obtenerConjuntoManzanas());
	}

	public String getNombreCensistasTotales() {
		StringBuilder nombres = new StringBuilder();
		for(Censista censista : radio.getCencistas()) {
			nombres.append("Censista: "+censista.getNombre()).append(" ").append("\n").append("\n");
		}
		return nombres.toString();
	}
	

    public String getConjuntoManzanasTotales() {
		
		StringBuilder conjuntoManzanas = new StringBuilder();
		int i=0;
		for(Set<Integer> vecinos : radio.getVecinos()) {
			conjuntoManzanas.append("Vecinos de la manzana " + i++ +": "   + vecinos).append("\n").append("\n");
			
		}
		return conjuntoManzanas.toString();
	}
	
	@Override
	public String toString() {
		return solver.toString();
	}

	public void reiniciarAsignacion() {
		this.resolverPorHeuristica();
	}
	
	public Solver getSolver() {
		return solver;
	}
	
	
}
