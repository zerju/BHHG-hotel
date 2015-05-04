package registracija;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import dao.RegistriranUporabnikDAO;
import beani.RegistriranUporabnik;

@ManagedBean(name = "registracija")
@SessionScoped
public class Registracija {

	@Resource(lookup="java:jboss/datasources/bhhg")
	DataSource ds;
	
	private RegistriranUporabnik registriranUporabnik = new RegistriranUporabnik();
	private ArrayList<RegistriranUporabnik> najdeniUporabniki = new ArrayList<RegistriranUporabnik>();
	private ArrayList<RegistriranUporabnik> vse = new ArrayList<RegistriranUporabnik>();
	ArrayList<RegistriranUporabnik> proste = new ArrayList<RegistriranUporabnik>();
	
	
	public void shrani() throws IOException, SQLException{
		RegistriranUporabnikDAO dao = new RegistriranUporabnikDAO(ds);
		
		dao.shrani(registriranUporabnik);
	}
	
	public void pocisti(RegistriranUporabnik registriranUporabnik) throws SQLException{
		RegistriranUporabnikDAO dao = new RegistriranUporabnikDAO(ds);
	
	}
	
	public ArrayList<RegistriranUporabnik> vrniVse() throws Exception{
		RegistriranUporabnikDAO dao = new RegistriranUporabnikDAO(ds);
		vse.clear();
		vse = (ArrayList<RegistriranUporabnik>) dao.vrniVse();
		return vse;
	}

	
	
	
	
	
	public RegistriranUporabnik getRegistriranUporabnik() {
		return registriranUporabnik;
	}

	public void setRegistriranUporabnik(RegistriranUporabnik registriranUporabnik) {
		this.registriranUporabnik = registriranUporabnik;
	}

	public ArrayList<RegistriranUporabnik> getNajdeniUporabniki() {
		return najdeniUporabniki;
	}

	public void setNajdeniUporabniki(ArrayList<RegistriranUporabnik> najdeniUporabniki) {
		this.najdeniUporabniki = najdeniUporabniki;
	}

	public ArrayList<RegistriranUporabnik> getVse() {
		return vse;
	}

	public void setVse(ArrayList<RegistriranUporabnik> vse) {
		this.vse = vse;
	}
	
}
