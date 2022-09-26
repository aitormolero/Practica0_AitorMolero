package Paneles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.HistoriaGrandSlams;
import Clases.Resultado;
import Clases.Tenista;
import Ventana.VentanaEvolucion;
import Ventana.VentanaPrinc;

public class PanelResultados extends JPanel {

	private static JTable tablaResultados = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel model = new DefaultTableModel();
	private static HashMap<String, Tenista> victoriasJugadores = new HashMap<>();
	private JButton btnAniadir;
	private JButton btnGuardar;
	private JPanel panelSur;
	
	
	public PanelResultados() {
		
		btnAniadir = new JButton("Añadir");
		btnGuardar = new JButton("Guardar");
		panelSur = new JPanel();
		panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		tablaResultados.setModel(model);
		
		model.addColumn("Year");
		model.addColumn("Mayor#");
		model.addColumn("Champion");
		model.addColumn("Seed_Champion");
		model.addColumn("Ctry_Champion");
		model.addColumn("Runner-up");
		model.addColumn("Seed_Runner-up");
		model.addColumn("Ctry_Runner-up");
		model.addColumn("Score in the Final");
		
		leerCSV();		//leemos csv para completar la tabla
		
		scrollPane.setViewportView(tablaResultados);
		System.out.println(victoriasJugadores);
		
		panelSur.add(btnGuardar);
		panelSur.add(btnAniadir);
		this.setLayout(new BorderLayout());
		this.add(panelSur, BorderLayout.SOUTH);
		this.add(scrollPane);
		
		
		
		btnGuardar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				escribirCSV();
			}
		});
		
		
		btnAniadir.addActionListener(new ActionListener() {		//escuchador del boton añadir para crear un nuevo resultado
			
			public void actionPerformed(ActionEvent e) {
				
				String anio = JOptionPane.showInputDialog("Año del torneo:");
				
				Object[] torneos = {"Open Australia", "Roland Garros", "Wimbledon", "Open USA"};
				String torneo = (String)JOptionPane.showInputDialog(null, "Torneo:", "Torneo", JOptionPane.QUESTION_MESSAGE, null, torneos, torneos[0]);
				String major;
				if (torneo == "Open USA") {major = "4";
				} else if (torneo == "Wimbledon") {major = "3";
				} else if (torneo == "Roland Garros") {major = "2";
				} else {major = "1";}
				String ganador = JOptionPane.showInputDialog("Tenista ganador/a:");
				String paisGanador = JOptionPane.showInputDialog("Pais de tenista ganador/a:");
				String rankingGanador = JOptionPane.showInputDialog("Ranking de ganador/a:");
				String finalista = JOptionPane.showInputDialog("Tenista finalista:");
				String paisFinalista = JOptionPane.showInputDialog("Pais de tenista finalista:");
				String rankingFinalista = JOptionPane.showInputDialog("Ranking de finalista:");
				String resultadoPartido = JOptionPane.showInputDialog("Resultado del partido final:");
				
				String[] nuevaFila = new String[9];		//Creamos una nueva linea parametro por parametro, los cuales hemos pedido al usuario
				nuevaFila[0] = anio;
				nuevaFila[1] = major;
				nuevaFila[2] = ganador;
				nuevaFila[3] = rankingGanador;
				nuevaFila[4] = paisGanador;
				nuevaFila[5] = finalista;
				nuevaFila[6] = rankingFinalista;
				nuevaFila[7] = paisFinalista;
				nuevaFila[8] = resultadoPartido;
				sumarVictoria(nuevaFila);	//actualizamos el hashmap de victorias de los jugadores
				model.addRow(nuevaFila);	//añadimos nueva fila a la tabla
				
				PanelTenistas.completarTabla();
				Resultado r = new Resultado(major, Integer.parseInt(anio), ganador, finalista, Integer.parseInt(rankingGanador), Integer.parseInt(rankingFinalista), resultadoPartido, paisGanador, paisFinalista);
				HistoriaGrandSlams.getResultadosHistoricos().add(r);
			}
		});
		
				
		tablaResultados.addMouseListener(new MouseAdapter() {		//escuchador para devolver informacion del tenistas seleccionado en la tabla, solo el ganador
			
			public void mouseClicked(MouseEvent e) {
				if (tablaResultados.getSelectedColumn() == 2) {
					System.out.println(tablaResultados.getValueAt(tablaResultados.getSelectedRow(), tablaResultados.getSelectedColumn()));
					JOptionPane.showMessageDialog(null, tablaResultados.getValueAt(tablaResultados.getSelectedRow(), tablaResultados.getSelectedColumn()) + 
							"\nCabeza de serie en este torneo: " + tablaResultados.getValueAt(tablaResultados.getSelectedRow(), 3));
				}
			}
			
		});
		
	}
				
	public void escribirCSV () {
	
		String archivo = "GrandSlams.csv";
		FileWriter fw;
		
		try {
			
			fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String[] cabeceras = new String[9];
			cabeceras[0] = "Year";
			cabeceras[1] = "Mayor#";
			cabeceras[2] = "Champion";
			cabeceras[3] = "Seed_Champion";
			cabeceras[4] = "Ctry_Champion";
			cabeceras[5] = "Runner-up";
			cabeceras[6] = "Seed_Runner-up";
			cabeceras[7] = "Ctry_Runner-up";
			cabeceras[8] = "Score in the final";
			
			for (int i=0; i<=cabeceras.length-2; i++) {
				 String s = ('"' + cabeceras[i] + '"' + ",");
				 bw.write(s);
			}
			bw.write('"' + cabeceras[cabeceras.length-1] + '"' + ",");
			bw.write("\n");
			
			System.out.println(HistoriaGrandSlams.getResultadosHistoricos());
			
			for (Resultado r : HistoriaGrandSlams.getResultadosHistoricos()) {
				bw.write(r.getAnio() + ",");
				bw.write(r.getTorneo() + ",");
				bw.write('"' + r.getGanador() + '"' + ",");
				bw.write(r.getRankingGanador() + ",");
				bw.write('"' + r.getNacionalidadGanador() + '"' + ",");
				bw.write('"' + r.getSubcampeon() + '"' + ",");
				bw.write(r.getRankingSubcampeon() + ",");
				bw.write('"' + r.getNacionalidadSubcampeon() + '"' + ",");
				bw.write('"' + r.getResultado() + '"' + ",");
				bw.write("\n");
			}
			
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void leerCSV () {		//lectura de csv
		
		String archivo = "GrandSlams.csv";
		BufferedReader br = null;
		String linea = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo),"UTF8"));
			linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String[] dato = new String[9];
				dato = dividePorComas(linea);
				model.addRow(dato);
				sumarVictoria(dato);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	private void sumarVictoria(String[] dato) {			//actualizamos el hasmap añadiendo una victoria mas a un tenista ya existente o creamos un nuevo tenista
		if (victoriasJugadores.containsKey(dato[2])) {
			int victoriaAnt = victoriasJugadores.get(dato[2]).getVictoriasGrandSlams();
			victoriaAnt += 1;
			victoriasJugadores.replace(dato[2], new Tenista(dato[2], dato[4], victoriaAnt));
		} else {
			Tenista tenista = new Tenista(dato[2], dato[4], 1);
			victoriasJugadores.put(dato[2], tenista); 
		}
		if (!victoriasJugadores.containsKey(dato[5])) {		//añadimos tambien el finalista en caso de que no exista
			Tenista tenista = new Tenista(dato[5], dato[7], 0);
			victoriasJugadores.put(dato[5], tenista); 
		}
	}



	private String[] dividePorComas(String linea) {		//metodo para dividir por comas el csv
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

	
	//un par de getters
	public static HashMap<String, Tenista> getVictoriasJugadores() {
		return victoriasJugadores;
	}

	public static JTable getTablaResultados() {
		return tablaResultados;
	}
	
	
	
}


