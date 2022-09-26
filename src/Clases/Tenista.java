package Clases;

public class Tenista implements Comparable<Tenista> {
	
	//Propiedades
	private String nombre = "";
	private String nacionalidad = "";
	private int victoriasGrandSlams = 0;
	
	//Constructores
	public Tenista(String nombre, String nacionalidad, int victoriasGrandSlams) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.victoriasGrandSlams = victoriasGrandSlams;
	}

	//Getters y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getVictoriasGrandSlams() {
		return victoriasGrandSlams;
	}
	public void setVictoriasGrandSlams(int victoriasGrandSlams) {
		this.victoriasGrandSlams = victoriasGrandSlams;
	}
	
	
	//Otros metodos
	
	public void sumarVictoriaGrandSlams(int victoriasGrandSlams) {  // metodo para sumar una victoria a las victorias totales de un jugador
		this.victoriasGrandSlams = victoriasGrandSlams + 1;
	}

	@Override
	public String toString() {
		return "Tenista [nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", victoriasGrandSlams="
				+ victoriasGrandSlams + "]";
	}
	
	
	public int compareTo(Tenista t) {   // metodo compareTo que sirve para ordenar los tenistas de mas a menos Grand Slams
        if (this.victoriasGrandSlams != t.getVictoriasGrandSlams()) {
        	return t.getVictoriasGrandSlams() - this.victoriasGrandSlams;
        }
        return this.nombre.compareTo(nombre);
    }

}
