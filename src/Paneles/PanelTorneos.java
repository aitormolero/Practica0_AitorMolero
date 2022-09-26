package Paneles;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelTorneos extends JPanel {
	
	private JTable tablaTorneos = new JTable();
	private DefaultTableModel model = new DefaultTableModel();

	
	public PanelTorneos() {
		
		tablaTorneos.setModel(model);
		
		model.addColumn("Codigo");
		model.addColumn("Nombre");
		model.addColumn("Ciudad");
		
		leerCSV();
		
		this.setLayout(new BorderLayout());
		this.add(tablaTorneos);
		
	}


	private void leerCSV() {	//leemos csv "Torneos" para crear la tabla
		
		String archivo = "Torneos.csv";
		BufferedReader br = null;
		String linea = "";
		
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo),"UTF8"));
			linea = br.readLine();
			while ((linea = br.readLine()) != null) {
				String[] dato = new String[9];
				dato = dividePorComas(linea);
				model.addRow(dato);		//añadimos nueva fila a la tabla
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
}
