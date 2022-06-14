package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import objetos.BFS;
import objetos.RadioCensal;

public class BFSTest {

	@Test (expected=IllegalArgumentException.class)
	public void RadioCensalNuloTest() {
		BFS.esConexo(null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void RadioCensalVacioTest() {
		RadioCensal g = new RadioCensal(0);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void RadioCensalNoConexoTest() {
		RadioCensal g = inicializarRadioCensal();
		assertFalse(BFS.esConexo(g));
	}
	
	@Test
	public void RadioCensalConexoTest() {
		RadioCensal g = inicializarRadioCensal();
		g.agregarArco(3, 4);
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesTest() {
		RadioCensal g = inicializarRadioCensal();
		Set<Integer> alcanzables = BFS.alcanzables(g,0);
		Assert.iguales(new int[] {0,1,2,3}, alcanzables);
		
	}

	private RadioCensal inicializarRadioCensal() {
		RadioCensal RadioCensal = new RadioCensal(5);
		RadioCensal.agregarArco(0, 1);
		RadioCensal.agregarArco(0, 2);
		RadioCensal.agregarArco(2, 3);
		return RadioCensal;
	}

}
