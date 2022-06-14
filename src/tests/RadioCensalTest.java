package tests;

import static org.junit.Assert.*;


import java.util.Set;

import org.junit.Test;

import objetos.RadioCensal;

public class RadioCensalTest {

	@Test
	public void tamanoTest() {
		RadioCensal r = radioCensalEjemplo();
		assertEquals(r.getCantManzanas(),15);
	}
	
	@Test
	public void existeArcoTest() {
		RadioCensal r = radioCensalEjemplo();
		assertTrue(r.existeArco(0,7));
	}
	@Test
	public void noExisteArcoTest() {
		RadioCensal r = radioCensalEjemplo();
		assertFalse(r.existeArco(1,7));
		
	}
	
	@Test
	public void arcosPrimeraManzanaTest() {
		RadioCensal r = radioCensalEjemplo();
		 Set<Integer> arcosDePrimeraManzana =r.vecinos(0);
		Assert.iguales(new int[] {7,14,6,10},arcosDePrimeraManzana);
	}
	
	@Test
	public void arcosUltimaManzanaTest() {
		RadioCensal r = radioCensalEjemplo();
		 Set<Integer> arcosDePrimeraManzana =r.vecinos(r.getCantManzanas()-1);
		Assert.iguales(new int[] {12,0,3},arcosDePrimeraManzana);
	}
	
	
	@Test
	public void agregarArcoTest() {
		RadioCensal r = radioCensalEjemplo();
		r.agregarArco(0,1);
		assertTrue(r.existeArco(0, 1));
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void agregarArcoErroneoTest() {
		RadioCensal r = radioCensalEjemplo();
		r.agregarArco(-2, -2);
		assertTrue(r.existeArco(0, 1));
	}
	

	
	@Test (expected = IllegalArgumentException.class)
	public void agregarArcoNegativoTest() {
		RadioCensal r = radioCensalEjemplo();
		r.agregarArco(-1, 1);
	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void crearRadioCensalSinManzanasTest() {
		new RadioCensal(0);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void arcoManzanaNoexistenteTest() {
		RadioCensal r = radioCensalEjemplo();
		r.vecinos(r.getCantManzanas()); // el radio censal tiene tamano-1 manzanas
	}
	

	
	
	public RadioCensal radioCensalEjemplo() {
		
		RadioCensal r = new RadioCensal(15);   

		r.agregarArco(0, 7); 
		r.agregarArco(1, 5);
		r.agregarArco(2, 10);
		r.agregarArco(3, 14);
		r.agregarArco(4, 8);
		r.agregarArco(5, 2);
		r.agregarArco(6, 13);
		r.agregarArco(7, 2);
		r.agregarArco(9, 11);
		r.agregarArco(12, 4);
		r.agregarArco(12, 14);
		r.agregarArco(3,9);
		r.agregarArco(0,14);
		r.agregarArco(6,0);
		r.agregarArco(0,10);
		
		return r;
	}
	
}
