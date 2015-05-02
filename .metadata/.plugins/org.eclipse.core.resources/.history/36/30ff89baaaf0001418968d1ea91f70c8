package beani;

import javax.persistence.*;

@Entity
public class RegistriranUporabnik {
	
	private int idUporabnika;
	private String uporabniskoIme;
	private String geslo;
	private Oseba oseba;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	public void setOseba(Oseba oseba) {
		this.oseba = oseba;
	}
}
