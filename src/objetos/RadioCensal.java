package objetos;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class RadioCensal  {

	private ArrayList<Set<Integer>> vecinos;
	private int cantManzanas;
	private ArrayList<Censista> censistas;
	
	// Constructor de RadioCensal
	public RadioCensal(int cantManzanas)  {
		if (cantManzanas>0) {
			vecinos = new ArrayList<Set<Integer>>(cantManzanas);
			this.cantManzanas = cantManzanas;
			for (int i = 0; i < cantManzanas; i++) 
			vecinos.add(new TreeSet<Integer>());
		}
		else 
			throw new IllegalArgumentException("No se puede crear un radio censal sin manzanas");
	
	}

	// Getters y setters

	@SuppressWarnings("unchecked")
	public ArrayList<Set<Integer>> getVecinos() {
		return (ArrayList<Set<Integer>>) vecinos.clone();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Censista> getCencistas(){
		return (ArrayList<Censista>) censistas.clone();
	}

	public Set<Integer> vecinos(int s) throws IndexOutOfBoundsException {
		try {
			return vecinos.get(s);
		}
		catch (IndexOutOfBoundsException e) {
			throw new  IndexOutOfBoundsException("Debe ingresar una manzana existente en el radio censal");
		}
	}

	public int getCantManzanas() {
		return this.cantManzanas;
	}
	
	public void agregarCencista(ArrayList<Censista> listaCensistas) {
		censistas = listaCensistas;
	}

	public void agregarArco(int s, int t) throws IllegalArgumentException  {
			verificarAgregarArco(s,t);
			vecinos.get(s).add(t);
			vecinos.get(t).add(s);
	}

	private void verificarAgregarArco(int s,int t)  {
		
		if(s < 0 || s>this.cantManzanas || t < 0 || t>this.cantManzanas)
			 throw new  IllegalArgumentException ("Las manzanas deben pertenecer al radio censal ");
	}

	public boolean existeArco(int s, int t) {
		return vecinos.get(s).contains(t);
	}
	
	@Override
	public String toString() {
		return "RadioCensal [vecinos=" + vecinos + ", censistas=" + censistas + "]";
	}


	
}
