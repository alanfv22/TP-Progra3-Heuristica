package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import objetos.AsignacionHeuristica;
import objetos.RadioCensal;

public class HeuristicaTest {
	RadioCensal radio;
	AsignacionHeuristica heuristica;
	HashMap <Integer,ArrayList<Integer>> conjuntoDeManzanas;
	
	@Test
	public void cantConjuntosManzanasRadioRamdomTest() {
		radioRamdon();
		assertEquals(conjuntoDeManzanas.size(),7);
		
	}
	
	@Test
	public void cantConjuntosManzanasRadioRealistaTest() {
		radioRealista();
		assertEquals(conjuntoDeManzanas.size(),5);
		
	}
	
	@Test
	public void primerConjuntoRadioRamdonTest() {
		radioRamdon();
		Assert.iguales(new int[] {0,6,7}, Set.copyOf(conjuntoDeManzanas.get(0)));
		
	}
	
	@Test
	public void primerConjuntoRadioRealistaTest() {
		radioRealista();
		Assert.iguales(new int[] {0,1,3}, Set.copyOf(conjuntoDeManzanas.get(0)));
		
	}
	
	@Test
	public void cantManzanasAsigndasRadioRamdon() {
		radioRamdon();
		assertEquals(cantManzanasAsignadas(),this.radio.getCantManzanas());
	}
	
	@Test
	public void cantManzanasAsigndasRadioRealista() {
		radioRealista();
		assertEquals(cantManzanasAsignadas(),this.radio.getCantManzanas());
	}
	

	public void radioRamdon() {
		
		/* 
		 *  Un grafo aleatorio donde el mejor caso seria 5 censistas en caso de fuerza bruta y 15  en el peor caso.
		 *  Con la heuristica implementada nos da un valor de 7 censistas el cual consideramos un valor optimo
		 */
		
		this.radio = new RadioCensal(15);  
		
		radio.agregarArco(1,5);
		radio.agregarArco(0,7); 
		radio.agregarArco(0,6); 
		radio.agregarArco(2, 10);
		radio.agregarArco(3, 14);
		radio.agregarArco(4, 8);
		radio.agregarArco(5, 2);
		radio.agregarArco(6, 13);
		radio.agregarArco(0,14);
		radio.agregarArco(0,10);
		radio.agregarArco(7, 2);
		radio.agregarArco(9, 11);
		radio.agregarArco(12, 4);
		radio.agregarArco(12, 14);
		radio.agregarArco(3,9);
	
		
		heuristica= new AsignacionHeuristica(radio);
		conjuntoDeManzanas= heuristica.obtenerConjuntoManzanas();
		}
		
	public void radioRealista() {
		
		/* dibujo del grafo 
		 * 
		 *  1 -- 2 -- 3
		 *  |    |    | 
		 *  4 -- 5 -- 6
		 *  |    |    |
		 *  7 -- 8 -- 9 
		 *  
		 *  se plantea un caso "realista" donde la solucion optima deberia ser 3 censistas  en caso de fuerza bruta,
		 *  y 9 en peor caso. Con heuristica nos da un total de 5 censistas el cual consideramos un resultado optimo
		 *  
		 *  
		 */
			
			this.radio = new RadioCensal(9);   

			radio.agregarArco(0, 1); 
			radio.agregarArco(0, 3);
			
			radio.agregarArco(1, 2);
			radio.agregarArco(1, 4);
			radio.agregarArco(1, 0);
			
			radio.agregarArco(2, 5);
			radio.agregarArco(2, 1);
			
			radio.agregarArco(3, 4);
			radio.agregarArco(3, 6);
			radio.agregarArco(3, 0);

			radio.agregarArco(4, 1); 
			radio.agregarArco(4, 3);
			radio.agregarArco(4, 5);
			radio.agregarArco(4, 7);
			
			radio.agregarArco(5, 2);
			radio.agregarArco(5, 4);
			radio.agregarArco(5, 8);
			
			radio.agregarArco(6, 3);
			radio.agregarArco(6, 7);
			
			radio.agregarArco(7, 4);
			radio.agregarArco(7, 8);
			radio.agregarArco(7, 6);
			
			radio.agregarArco(8, 5);
			radio.agregarArco(8, 7);

			heuristica= new AsignacionHeuristica(radio);
			conjuntoDeManzanas= heuristica.obtenerConjuntoManzanas();
	
		}

	public int cantManzanasAsignadas() {
	
		int ret=0;
		
		for(ArrayList<Integer> i : conjuntoDeManzanas.values())
			ret=ret+ i.size();
		
		return ret;
	}
	
}
