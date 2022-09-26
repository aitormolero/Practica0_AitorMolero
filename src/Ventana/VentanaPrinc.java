package Ventana;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Clases.HistoriaGrandSlams;
import Paneles.*;

public class VentanaPrinc extends JFrame {
	
	PanelResultados panelResultados;
	PanelTenistas panelTenistas;
	PanelTorneos panelTorneos;
	JPanel panelSur;
	JButton btnEvolucion;
	
	JTabbedPane pestañas;
	
	
	public VentanaPrinc() {
		
		panelSur = new JPanel();
		btnEvolucion = new JButton("Evolucion Ranking");
		
		pestañas = new JTabbedPane();
		
		panelResultados = new PanelResultados();   // Tres paneles diferentes que se añaden a las distintas pestañas
		panelTenistas = new PanelTenistas();
		panelTorneos = new PanelTorneos();
		
		pestañas.add("Resultados", panelResultados);
		pestañas.add("Tenistas", panelTenistas);
		pestañas.add("Torneos", panelTorneos);
		panelSur.add(btnEvolucion);
		
		this.add(pestañas);
		this.add(panelSur, BorderLayout.SOUTH);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		btnEvolucion.addActionListener(new ActionListener() {   // el escuchador para iniciar el hilo con la evolucion historica
			
			public void actionPerformed(ActionEvent e) {
				String inicio = JOptionPane.showInputDialog("Año de inicio:");
				String fin = JOptionPane.showInputDialog("Año final:");
				int mayorVic = panelTenistas.getaTenistas()[0].getVictoriasGrandSlams();
				VentanaEvolucion ve = new VentanaEvolucion(Integer.parseInt(inicio), Integer.parseInt(fin), mayorVic);
			}
		});
		
	}
	

	
	// getter de pestañas
	public JTabbedPane getPestañas() {
		return pestañas;
	}
	
	

}
