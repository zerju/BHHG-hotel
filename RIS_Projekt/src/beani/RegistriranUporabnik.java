package beani;


public class RegistriranUporabnik {
	
	private int idUporabnika;
	private String uporabniskoIme;
	private String geslo;
	private Oseba oseba;
	private String osebaString;
	
	
	public int getIdUporabnika() {
		return idUporabnika;
	}
	public void setIdUporabnika(int idUporabnika) {
		this.idUporabnika = idUporabnika;
	}
	public String getUporabniskoIme() {
		return uporabniskoIme;
	}
	public void setUporabniskoIme(String uporabniskoIme) {
		this.uporabniskoIme = uporabniskoIme;
	}
	public String getGeslo() {
		return geslo;
	}
	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}
	public Oseba getOseba() {
		return oseba;
	}
	public void setOsebaString(String oseba){
		this.osebaString = oseba;
	}
	public String getOsebaString(){
		return osebaString;
	}
	public void setOseba(Oseba oseba) {
		this.oseba = oseba;
	}
}
