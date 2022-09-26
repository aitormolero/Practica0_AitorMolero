package Clases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HistoriaGrandSlams {
	
	private static ArrayList<Resultado> resultadosHistoricos = new ArrayList<>();
	private HashMap<String, Torneo> hmTorneos = new HashMap<>();
	private static HashMap<String, Tenista> hmTenistas = new HashMap<>();
	
	public HistoriaGrandSlams() {
		
		leerCSVResultados();
		leerCSVTorneos();
		
	}
	
	
	public void leerCSVResultados () {   // Leo el csv "GrandSlams" para guardar todos los resultados historicos y completar el hashmap de los tenistas
		
		String archivo = "GrandSlams.csv";
		BufferedReader br = null;
		String linea = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo),"UTF8"));
			linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String[] dato = new String[9];
				dato = dividePorComas(linea);
				Resultado r = new Resultado(dato[1], Integer.parseInt(dato[0]), dato[2], dato[5], Integer.parseInt(dato[3]), Integer.parseInt(dato[6]), dato[8], dato[4], dato[7]);
				resultadosHistoricos.add(r);  // añado un nuevo resultado
				sumarVictoria(dato);  // añado un nuevo tenista al hashmap o le actualizo las victorias si ya existe
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void sumarVictoria(String[] dato) {   // metodo para comprobar si ya existe un jugador con esa clave y se actualizan las victorias de Grand Slams
		if (hmTenistas.containsKey(dato[2])) {
			int victoriaAnt = hmTenistas.get(dato[2]).getVictoriasGrandSlams();
			victoriaAnt += 1;
			hmTenistas.replace(dato[2], new Tenista(dato[2], dato[4], victoriaAnt));
		} else {
			Tenista tenista = new Tenista(dato[2], dato[4], 1);
			hmTenistas.put(dato[2], tenista); 
		}
		if (!hmTenistas.containsKey(dato[5])) {   // Si no existe creo un nuevo tenista
			Tenista tenista = new Tenista(dato[5], dato[7], 0);
			hmTenistas.put(dato[5], tenista); 
		}
	}



	private String[] dividePorComas(String linea) {   // metodo para dividir por comas las lineas del csv
		ArrayList<String> listaTokens = new ArrayList<String>();
		boolean entreComillas = false;
		StringBuilder b = new StringBuilder();
		for (char c : linea.toCharArray()) {
			if (c==';') {
				if (entreComillas) {
					b.append(c);
				} else {
					listaTokens.add( b.toString() );
					b = new StringBuilder();
				}
			} else if (c=='\"') {
				entreComillas = !entreComillas;
			} else {
				b.append(c);
			}
		}
		listaTokens.add( b.toString() );
		return listaTokens.toArray( new String[0] );
		
	}

	
	private void leerCSVTorneos() {
		
		String archivo = "Torneos.csv";
		BufferedReader br = null;
		String linea = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo),"UTF8"));
			linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String[] dato = new String[3];
				dato = dividePorComas(linea);
				hmTorneos.put(dato[0], new Torneo(dato[0], dato[1], dato[2]));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	
	public static ArrayList<Resultado> getResultadosHistoricos() {
		return resultadosHistoricos;
	}

	public HashMap<String, Torneo> getHmTorneos() {
		return hmTorneos;
	}

	public static HashMap<String, Tenista> getHmTenistas() {
		return hmTenistas;
	}

	
	

}
