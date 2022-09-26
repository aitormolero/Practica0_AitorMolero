package Clases;

public class Resultado {
	
	//Propiedades
	private String torneo = "";
	private int anio = 0;
	private String ganador = "";
	private String subcampeon = "";
	private int rankingGanador = 0;
	private int rankingSubcampeon = 0;
	private String resultado = "";
	private String nacionalidadGanador = "";
	private String nacionalidadSubcampeon = "";
	
	//Constructor
	public Resultado(String torneo, int anio, String ganador, String subcampeon, int rankingGanador,
			int rankingSubcampeon, String resultado, String nacionalidadGanador, String nacionalidadSubcampeon) {
		this.torneo = torneo;
		this.anio = anio;
		this.ganador = ganador;
		this.subcampeon = subcampeon;
		this.rankingGanador = rankingGanador;
		this.rankingSubcampeon = rankingSubcampeon;
		this.resultado = resultado;
		this.nacionalidadGanador = nacionalidadGanador;
		this.nacionalidadSubcampeon = nacionalidadSubcampeon;
	}
	
	//Getters y setters

	public String getTorneo() {
		return torneo;
	}
	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}

	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getGanador() {
		return ganador;
	}
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public String getSubcampeon() {
		return subcampeon;
	}
	public void setSubcampeon(String subcampeon) {
		this.subcampeon = subcampeon;
	}

	public int getRankingGanador() {
		return rankingGanador;
	}
	public void setRankingGanador(int rankingGanador) {
		this.rankingGanador = rankingGanador;
	}

	public int getRankingSubcampeon() {
		return rankingSubcampeon;
	}
	public void setRankingSubcampeon(int rankingSubcampeon) {
		this.rankingSubcampeon = rankingSubcampeon;
	}

	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String getNacionalidadGanador() {
		return nacionalidadGanador;
	}
	public void setNacionalidadGanador(String nacionalidadGanador) {
		this.nacionalidadGanador = nacionalidadGanador;
	}

	public String getNacionalidadSubcampeon() {
		return nacionalidadSubcampeon;
	}
	public void setNacionalidadSubcampeon(String nacionalidadSubcampeon) {
		this.nacionalidadSubcampeon = nacionalidadSubcampeon;
	}

	//Otros metodos
	public String toString() {
		return "Resultado [torneo=" + torneo + ", anio=" + anio + ", ganador=" + ganador + ", subcampeon=" + subcampeon
				+ ", rankingGanador=" + rankingGanador + ", rankingSubcampeon=" + rankingSubcampeon + ", resultado="
				+ resultado + "]";
	}
	
	
}
