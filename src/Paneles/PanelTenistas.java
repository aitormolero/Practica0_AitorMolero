package Paneles;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Tenista;

public class PanelTenistas extends JPanel{

	private static JTable tablaTenistas = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private static DefaultTableModel model = new DefaultTableModel();
	private static Tenista[] aTenistas;

	
	
	
	public PanelTenistas() {
				
		tablaTenistas.setModel(model);
		
		model.addColumn("Nombre");
		model.addColumn("Pais");
		model.addColumn("Victorias");
		
		recorrerHashMap(PanelResultados.getVictoriasJugadores());	//recorremos el mapa para añadir los tenistas a una array
		ordenarTenistas();		//ordenamos los tenistas por victorias de grand slams
		aniadirTabla();			//añadimos los tenistas ya ordenados
		
		scrollPane.setViewportView(tablaTenistas);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPane);
	}



	public static void completarTabla() {	//funcion que se llama al añadir un nuevo resultado en el panel resultados
		model.setRowCount(0);	//se vacia la tabla para que no este dupilcada
		recorrerHashMap(PanelResultados.getVictoriasJugadores());	//se siguen los mismos pasos que antes
		ordenarTenistas();
		aniadirTabla();
	}
	
	
	private static void aniadirTabla() {	//metodo para completar la tabla tenista por tenista
		
		for (Tenista t : aTenistas) {
			String[] dato = new String[3];
			dato[0] = t.getNombre();
			dato[1] = t.getNacionalidad();
			dato[2] = t.getVictoriasGrandSlams() + "";
			
			model.addRow(dato);
		}
		
	}



	private static void ordenarTenistas() {		//metodo para ordenar el array de tenistas
		Arrays.sort(aTenistas);
	}



	private static void recorrerHashMap(HashMap<String, Tenista> victoriasJugadores) {
		int i = 0;
		aTenistas = new Tenista[PanelResultados.getVictoriasJugadores().size()];
		for (String tenistas : victoriasJugadores.keySet()) {
			
			aTenistas[i] = victoriasJugadores.get(tenistas);	//añadimos tenista al array
			i += 1;
		}		
		
	}



	public static Tenista[] getaTenistas() {	//getter de array de tenistas
		return aTenistas;
	}
	
	
		
	
}
