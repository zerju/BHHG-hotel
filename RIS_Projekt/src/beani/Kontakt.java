package beani;

public class Kontakt {

	private int idKontakta = 1;
	private String telefon;
	private String email;
	
	

	public Kontakt(Kontakt kontakt) {
		
		this.idKontakta = kontakt.getIdKontakta();
		this.email = kontakt.getEmail();
		this.telefon = kontakt.getTelefon();
	}
	
	public Kontakt() {}
	
	public int getIdKontakta() {
		return idKontakta;
	}
	public void setIdKontakta(int idKontakta) {
		this.idKontakta = idKontakta;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
