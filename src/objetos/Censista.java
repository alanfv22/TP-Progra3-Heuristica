package objetos;

public class Censista {
	
	private int ID;
	private String nombre;
	
	
	/**
	 * Constructor de Censista
	 * @param ID
	 * @param nombre
	 */
	public Censista(int ID, String nombre) {
		this.ID = ID;
		this.nombre = nombre;
	}

	
	//Getters
	public int getID() {
		return ID;
	}


	public String getNombre() {
		return nombre;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "nombre:" + nombre + " ID:" + ID;
	}


}
