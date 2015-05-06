package beani;

public class OHotelu {

	
	private int idHotela = 1;
	private String opis;
	private String opis2;
	
	public OHotelu(OHotelu hotel) {
		
		this.idHotela = hotel.getIdHotela();
		this.opis = hotel.getOpis();
		this.opis2 = hotel.getOpis2();
	}
	
	public OHotelu() {}
	
	public int getIdHotela() {
		return idHotela;
	}
	public void setIdHotela(int idHotela) {
		this.idHotela = idHotela;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getOpis2() {
		return opis2;
	}

	public void setOpis2(String opis2) {
		this.opis2 = opis2;
	}
	
}
