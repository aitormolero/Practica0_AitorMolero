package Ventana;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;

import Clases.Tenista;
import Paneles.PanelResultados;

public class VentanaEvolucion extends JFrame {

	private boolean bandera = false;
	private Tenista[] arTenistas;
	private HashMap<String, Tenista> hmJugadores = new HashMap<>();
	private int mayorVictorias;
	
	
	
	
	public VentanaEvolucion(int anioInicio, int anioFinal, int mayor) {
		
		mayorVictorias = mayor;  //mayor numero de victorias jjamas logradas, se pasan como parametro
		calcularMedidas(anioInicio, anioFinal);
		
		this.setLayout(null);
		this.setSize(575, 580);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	private void calcularMedidas(int anioInicio, int anioFinal) {   // Metodo principal que contiene el hilo
		int total = PanelResultados.getTablaResultados().getRowCount();
		JTable tabla = PanelResultados.getTablaResultados();
		
		Thread t = new Thread() { // new Runnable() {
			public void run() {
				
				int lineaInicio = 0;		
				for (int i = anioInicio; i <= anioFinal; i++) {  //este for se ejecuta tantas veces como años se hayan escogido. ejemplo: 2022-1968 = 54veces
					
					for (int j = lineaInicio; j < total; j++) {  // este bucle recorre las lineas de la tabla fijandose en el año
						
						if (Integer.parseInt((String)tabla.getValueAt(j, 0)) == i) {	//cada año va actualizando un hashmap de los tenistas ganadores
							System.out.println(tabla.getValueAt(j, 2));					//añade un nuevo tenista o le suma una victoria a uno ya existente
							sumarVictoria((String)tabla.getValueAt(j, 2), (String)tabla.getValueAt(j, 4));   //llamada al metodo que se introduecen como parametros
																											// el nombre y la nacionalidad del tenista
						}
					}
					
					arTenistas = new Tenista[hmJugadores.size()];	//creamos un nuevo array para crearlo recorriendo el hashmap
					recorrerHashMap(hmJugadores);					//llamada a la funcion para completar el array
					Arrays.sort(arTenistas);						//ordenamos el array por numero de victorias
					System.out.println(Arrays.toString(arTenistas));
					bandera = true;
					repaint();	//pintamos la ventana
										
					System.out.println();
					try {
						Thread.sleep( 2000 );	//esperamos 2 segundos
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}; t.start();
		
	}
	
	
	
	private void recorrerHashMap(HashMap<String, Tenista> hmTenistas) {
		int i = 0;
		
		for (String tenistas : hmTenistas.keySet()) {
			arTenistas[i] = hmTenistas.get(tenistas);	//añadimos tenista al array
			i += 1;
		}
		
	}
	
	
	
	private void sumarVictoria(String nombre, String nacionalidad) {	//metodo para añadir tenistas al hashmap
		if (hmJugadores.containsKey(nombre)) {
			int victoriaAnt = hmJugadores.get(nombre).getVictoriasGrandSlams();
			victoriaAnt += 1;
			hmJugadores.replace(nombre, new Tenista(nombre, nacionalidad, victoriaAnt));
		} else {
			Tenista tenista = new Tenista(nombre, nacionalidad, 1);
			hmJugadores.put(nombre, tenista); 
		}
	}
	
	

	public void paint(Graphics g) {
		super.paint(g);
		if (bandera ==true ) {
			
			int largo1;		//creamos 10 diferentes longitudes
			int largo2;
			int largo3;
			int largo4;
			int largo5;
			int largo6;
			int largo7;
			int largo8;
			int largo9;
			int largo10;
			
			if (arTenistas.length <= 1) {		//para que no nos de ningun tipo de error cuando el array no este completo hacemos unos ifs anidados
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;	//definimos la longitud que queremos que tenga la barra (pixeles)
				
				g.setColor(new Color(0, 0, 0));		//creamos color (negro)
				g.fillRect(150, 50, largo1, 40);	//creamos rectangulo: 150 pixeles a la derecha, 50 hacia abajo, la longitud que hemos definido antes, y 40 pixeles de grosor
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);	//Escribimos el nombre del tenista
				
			} else if (arTenistas.length <= 2) {	// esto se repite pero añaiendo mas tenistas usando la misma logica y añadiendo pixeles de distancia entre una barra y otra
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
			} else if (arTenistas.length <= 3) {
				
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
			} else if (arTenistas.length <= 4) {
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo4 = arTenistas[3].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
				g.fillRect(150, 200, largo4, 40);
				g.drawString(arTenistas[3].getNombre() + " (" + arTenistas[3].getVictoriasGrandSlams() + ")", 10, 225);
				
			} else if (arTenistas.length <= 5) {
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo4 = arTenistas[3].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo5 = arTenistas[4].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
				g.fillRect(150, 200, largo4, 40);
				g.drawString(arTenistas[3].getNombre() + " (" + arTenistas[3].getVictoriasGrandSlams() + ")", 10, 225);
				
				g.fillRect(150, 250, largo5, 40);
				g.drawString(arTenistas[4].getNombre() + " (" + arTenistas[4].getVictoriasGrandSlams() + ")", 10, 275);
				
			} else if (arTenistas.length <= 6) {
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo4 = arTenistas[3].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo5 = arTenistas[4].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo6 = arTenistas[5].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
				g.fillRect(150, 200, largo4, 40);
				g.drawString(arTenistas[3].getNombre() + " (" + arTenistas[3].getVictoriasGrandSlams() + ")", 10, 225);
				
				g.fillRect(150, 250, largo5, 40);
				g.drawString(arTenistas[4].getNombre() + " (" + arTenistas[4].getVictoriasGrandSlams() + ")", 10, 275);
				
				g.fillRect(150, 300, largo6, 40);
				g.drawString(arTenistas[5].getNombre() + " (" + arTenistas[5].getVictoriasGrandSlams() + ")", 10, 325);
				
			} else if (arTenistas.length <= 7) {
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo4 = arTenistas[3].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo5 = arTenistas[4].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo6 = arTenistas[5].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo7 = arTenistas[6].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
				g.fillRect(150, 200, largo4, 40);
				g.drawString(arTenistas[3].getNombre() + " (" + arTenistas[3].getVictoriasGrandSlams() + ")", 10, 225);
				
				g.fillRect(150, 250, largo5, 40);
				g.drawString(arTenistas[4].getNombre() + " (" + arTenistas[4].getVictoriasGrandSlams() + ")", 10, 275);
				
				g.fillRect(150, 300, largo6, 40);
				g.drawString(arTenistas[5].getNombre() + " (" + arTenistas[5].getVictoriasGrandSlams() + ")", 10, 325);
				
				g.fillRect(150, 350, largo7, 40);
				g.drawString(arTenistas[6].getNombre() + " (" + arTenistas[6].getVictoriasGrandSlams() + ")", 10, 375);
				
			} else if (arTenistas.length <= 8) {
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo4 = arTenistas[3].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo5 = arTenistas[4].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo6 = arTenistas[5].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo7 = arTenistas[6].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo8 = arTenistas[7].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
				g.fillRect(150, 200, largo4, 40);
				g.drawString(arTenistas[3].getNombre() + " (" + arTenistas[3].getVictoriasGrandSlams() + ")", 10, 225);
				
				g.fillRect(150, 250, largo5, 40);
				g.drawString(arTenistas[4].getNombre() + " (" + arTenistas[4].getVictoriasGrandSlams() + ")", 10, 275);
				
				g.fillRect(150, 300, largo6, 40);
				g.drawString(arTenistas[5].getNombre() + " (" + arTenistas[5].getVictoriasGrandSlams() + ")", 10, 325);
				
				g.fillRect(150, 350, largo7, 40);
				g.drawString(arTenistas[6].getNombre() + " (" + arTenistas[6].getVictoriasGrandSlams() + ")", 10, 375);
				
				g.fillRect(150, 400, largo8, 40);
				g.drawString(arTenistas[7].getNombre() + " (" + arTenistas[7].getVictoriasGrandSlams() + ")", 10, 425);
				
			} else if (arTenistas.length <= 9) {
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo4 = arTenistas[3].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo5 = arTenistas[4].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo6 = arTenistas[5].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo7 = arTenistas[6].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo8 = arTenistas[7].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo9 = arTenistas[8].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
				g.fillRect(150, 200, largo4, 40);
				g.drawString(arTenistas[3].getNombre() + " (" + arTenistas[3].getVictoriasGrandSlams() + ")", 10, 225);
				
				g.fillRect(150, 250, largo5, 40);
				g.drawString(arTenistas[4].getNombre() + " (" + arTenistas[4].getVictoriasGrandSlams() + ")", 10, 275);
				
				g.fillRect(150, 300, largo6, 40);
				g.drawString(arTenistas[5].getNombre() + " (" + arTenistas[5].getVictoriasGrandSlams() + ")", 10, 325);
				
				g.fillRect(150, 350, largo7, 40);
				g.drawString(arTenistas[6].getNombre() + " (" + arTenistas[6].getVictoriasGrandSlams() + ")", 10, 375);
				
				g.fillRect(150, 400, largo8, 40);
				g.drawString(arTenistas[7].getNombre() + " (" + arTenistas[7].getVictoriasGrandSlams() + ")", 10, 425);
				
				g.fillRect(150, 450, largo9, 40);
				g.drawString(arTenistas[8].getNombre() + " (" + arTenistas[8].getVictoriasGrandSlams() + ")", 10, 475);
				
			} else {
				largo1 = arTenistas[0].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo2 = arTenistas[1].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo3 = arTenistas[2].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo4 = arTenistas[3].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo5 = arTenistas[4].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo6 = arTenistas[5].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo7 = arTenistas[6].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo8 = arTenistas[7].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo9 = arTenistas[8].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				largo10 = arTenistas[9].getVictoriasGrandSlams() * 400 / this.mayorVictorias;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(150, 50, largo1, 40);
				g.drawString(arTenistas[0].getNombre() + " (" + arTenistas[0].getVictoriasGrandSlams() + ")", 10, 75);
				
				g.fillRect(150, 100, largo2, 40);
				g.drawString(arTenistas[1].getNombre() + " (" + arTenistas[1].getVictoriasGrandSlams() + ")", 10, 125);
				
				g.fillRect(150, 150, largo3, 40);
				g.drawString(arTenistas[2].getNombre() + " (" + arTenistas[2].getVictoriasGrandSlams() + ")", 10, 175);
				
				g.fillRect(150, 200, largo4, 40);
				g.drawString(arTenistas[3].getNombre() + " (" + arTenistas[3].getVictoriasGrandSlams() + ")", 10, 225);
				
				g.fillRect(150, 250, largo5, 40);
				g.drawString(arTenistas[4].getNombre() + " (" + arTenistas[4].getVictoriasGrandSlams() + ")", 10, 275);
				
				g.fillRect(150, 300, largo6, 40);
				g.drawString(arTenistas[5].getNombre() + " (" + arTenistas[5].getVictoriasGrandSlams() + ")", 10, 325);
				
				g.fillRect(150, 350, largo7, 40);
				g.drawString(arTenistas[6].getNombre() + " (" + arTenistas[6].getVictoriasGrandSlams() + ")", 10, 375);
				
				g.fillRect(150, 400, largo8, 40);
				g.drawString(arTenistas[7].getNombre() + " (" + arTenistas[7].getVictoriasGrandSlams() + ")", 10, 425);
				
				g.fillRect(150, 450, largo9, 40);
				g.drawString(arTenistas[8].getNombre() + " (" + arTenistas[8].getVictoriasGrandSlams() + ")", 10, 475);
				
				g.fillRect(150, 500, largo10, 40);
				g.drawString(arTenistas[9].getNombre() + " (" + arTenistas[9].getVictoriasGrandSlams() + ")", 10, 525);
				
			}
			
						
		}
		
	}
	
}
