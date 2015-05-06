package beani;

public class OHotelu {

	
	private int idHotela = 1;
	private String opis;
	
	public OHotelu(OHotelu hotel) {
		
		this.idHotela = hotel.getIdHotela();
		this.opis = hotel.getOpis();
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
	
}
