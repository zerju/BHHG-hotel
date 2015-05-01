package beani;

import javax.persistence.*;

@Entity
public class Oseba {

	private int idOsebe;
	private String ime;
	private String priimek;
	private String email;
	private int telefon;
	private String tipOsebe;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdOsebe() {
		return idOsebe;
	}
	public void setIdOsebe(int idOsebe) {
		this.idOsebe = idOsebe;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPriimek() {
		return priimek;
	}
	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefon() {
		return telefon;
	}
	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}
	public String getTipOsebe() {
		return tipOsebe;
	}
	public void setTipOsebe(String tipOsebe) {
		this.tipOsebe = tipOsebe;
	}
	
}
