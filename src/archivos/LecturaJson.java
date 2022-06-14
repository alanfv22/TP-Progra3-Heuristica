package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import objetos.RadioCensal;

public class LecturaJson {
	
	/**
	 * Lee un archivo Json
	 * @param archivo
	 * @return
	 */
	public static RadioCensal leerFixture(String archivo) {
		Gson gson = new Gson();
		RadioCensal aux = null;
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(archivo));
			aux = gson.fromJson(buffer, RadioCensal.class);
		} catch(FileNotFoundException ex) {
	        System.out.println("El " + archivo + " no se encuentra");                
	    }
	    catch(@SuppressWarnings("hiding") IOException ex) {
	        System.out.println("Error leyendo el " + archivo);                  
	    }
		return aux;
	}
	
	
	/**
	 * @return Devuelve el radioCensal cargado para poder utilizarlo en el programa
	 */
	public static RadioCensal nuevoRadioCensal() {
		return LecturaJson.leerFixture("./src/archivos/radioCensal.json");
	}


}
