package Clases;

public class Torneo {
	
	//Propiedades
	private String codigo = "";
	private String nombre = "";
	private String ciudad = "";
	
	//Constructor
	public Torneo(String codigo, String nombre, String ciudad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	//Getter y setters
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	//Otros metodos
	public String toString() {
		return "Torneo [codigo=" + codigo + ", nombre=" + nombre + ", ciudad=" + ciudad + "]";
	}
	
	
	

}
