package beani;


public class Soba {
	
	private int idSobe;
	private int stSobe;
	private int kapaciteta;
	private String opis;
	private String tip;
	private int cena;
	private String datumPrihoda;
	private String datumOdhoda;
	private boolean zasedena;
	private String slika1;
	private String slika2;
	private String slika3;
	private String slika4;
	private int idOsebe;
	
	public Soba(){}
	
	public Soba(Soba s){
		this.idSobe = s.getIdOsebe();
		this.idOsebe = s.getIdOsebe();
		this.stSobe = s.getStSobe();
		this.kapaciteta = s.getKapaciteta();
		this.opis = s.getOpis();
		this.tip = s.getTip();
		this.cena = s.getCena();
		this.datumOdhoda = s.getDatumOdhoda();
		this.datumPrihoda = s.getDatumPrihoda();
		this.zasedena = s.isZasedena();
		this.slika1 = s.getSlika1();
		this.slika2 = s.getSlika2();
		this.slika3 = s.getSlika3();
		this.slika4 = s.getSlika4();
	}
	
	

	public int getIdSobe() {
		return idSobe;
	}
	public void setIdSobe(int idSobe) {
		this.idSobe = idSobe;
	}
	public int getStSobe() {
		return stSobe;
	}
	public void setStSobe(int stSobe) {
		this.stSobe = stSobe;
	}
	public int getKapaciteta() {
		return kapaciteta;
	}
	public void setKapaciteta(int kapaciteta) {
		this.kapaciteta = kapaciteta;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public boolean isZasedena() {
		return zasedena;
	}
	public void setZasedena(boolean zasedena) {
		this.zasedena = zasedena;
	}
	public String getSlika1() {
		return slika1;
	}
	public void setSlika1(String slika1) {
		this.slika1 = slika1;
	}
	public String getSlika2() {
		return slika2;
	}
	public void setSlika2(String slika2) {
		this.slika2 = slika2;
	}
	public String getSlika3() {
		return slika3;
	}
	public void setSlika3(String slika3) {
		this.slika3 = slika3;
	}
	public String getDatumPrihoda() {
		return datumPrihoda;
	}

	public void setDatumPrihoda(String datumPrihoda) {
		this.datumPrihoda = datumPrihoda;
	}

	public String getDatumOdhoda() {
		return datumOdhoda;
	}

	public void setDatumOdhoda(String datumOdhoda) {
		this.datumOdhoda = datumOdhoda;
	}

	public String getSlika4() {
		return slika4;
	}
	public void setSlika4(String slika4) {
		this.slika4 = slika4;
	}
	public int getIdOsebe() {
		return idOsebe;
	}
	public void setIdOsebe(int idOsebe) {
		this.idOsebe = idOsebe;
	}
	
}
