package beani;

public class Mnenje {
	
	private Oseba oseba;
	private int sumOcena;
	private int ocenaStoritev;
	private int ocenaLokacije;
	private String naslov;
	private String vsebina;
	private String vrstaIzleta;
	private String letniCas;
	
	
	public Oseba getOseba() {
		return oseba;
	}
	public void setOseba(Oseba oseba) {
		this.oseba = oseba;
	}
	public int getSumOcena() {
		return sumOcena;
	}
	public void setSumOcena(int sumOcena) {
		this.sumOcena = sumOcena;
	}
	public int getOcenaStoritev() {
		return ocenaStoritev;
	}
	public void setOcenaStoritev(int ocenaStoritev) {
		this.ocenaStoritev = ocenaStoritev;
	}
	public int getOcenaLokacije() {
		return ocenaLokacije;
	}
	public void setOcenaLokacije(int ocenaLokacije) {
		this.ocenaLokacije = ocenaLokacije;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getVsebina() {
		return vsebina;
	}
	public void setVsebina(String vsebina) {
		this.vsebina = vsebina;
	}
	public String getVrstaIzleta() {
		return vrstaIzleta;
	}
	public void setVrstaIzleta(String vrstaIzleta) {
		this.vrstaIzleta = vrstaIzleta;
	}
	public String getLetniCas() {
		return letniCas;
	}
	public void setLetniCas(String letniCas) {
		this.letniCas = letniCas;
	}
}