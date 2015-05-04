package rezervacije;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import dao.MizaDAO;
import beani.Miza;

@ManagedBean(name = "rezervacijaMize")
@SessionScoped
public class RezervacijaMize {

	@Resource(lookup="java:jboss/datasources/bhhg")
	DataSource ds;
	private Part file;
	private Miza miza = new Miza();
	private ArrayList<Miza> najdeneMize = new ArrayList<Miza>();
	private ArrayList<Miza> vse = new ArrayList<Miza>();
	ArrayList<Miza> proste = new ArrayList<Miza>();
	private ArrayList<Miza> zasedene = new ArrayList<Miza>();
	
	public void kopiraj(Miza m){
		miza = new Miza(m);
		System.out.println(miza.getStMize());
	}
	
	
	public void shrani() throws IOException, SQLException{
		MizaDAO dao = new MizaDAO(ds);
		file.write("C:/Users/Jure/Documents/GitHub/BHHG-hotel/RIS_Projekt/WebContent/Rezervacije/Slike/"+getFilename(file));
		String potSlike = "../Rezervacije/Slike/"+getFilename(file);
		miza.setSlika(potSlike);
		dao.shrani(miza);
	}
	
	public void pocisti(Miza m) throws SQLException{
		MizaDAO dao = new MizaDAO(ds);
		dao.sprazniMizo(m);
		System.out.println(m.getStGostov());
	}
	

	public ArrayList<Miza> vrniVse() throws Exception{
		MizaDAO dao = new MizaDAO(ds);
		vse.clear();
		vse = (ArrayList<Miza>) dao.vrniVse();
		return vse;
	}
	
	public ArrayList<Miza> vrniProste() throws Exception{
		MizaDAO dao = new MizaDAO(ds);
		vse = (ArrayList<Miza>) dao.vrniVse();
		proste.clear();
		for(Miza m: vse){
			if(m.getStGostov()==0){
				proste.add(m);
			}
			
		}return proste;
	}
	
	public ArrayList<Miza> vrniZasedene() throws Exception{
		MizaDAO dao = new MizaDAO(ds);
		vse = (ArrayList<Miza>) dao.vrniVse();
		zasedene.clear();
		for(Miza m: vse){
			if(m.getStGostov()>0){
				zasedene.add(m);
			}
			
		}return zasedene;
	}
	
	
	/*public boolean zasedenost(Miza m){
		if(miza.getStGostov()>0){
			return true;
		}
		return false;
	}*/
	
	public void rezerviraj(){
		MizaDAO dao = new MizaDAO(ds);
		
		dao.rezerviraj(miza);
	}
	
	
	private static String getFilename(Part part){
		for(String cd : part.getHeader("content-disposition").split(";")){
			if(cd.trim().startsWith("filename")){
				String filename = cd.substring(cd.indexOf('=')+1).trim().replace("\"","");
				return filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
				
			}
		}
		return null;
	}
	
	
	
	

	public Miza getMiza() {
		return miza;
	}

	public void setMiza(Miza miza) {
		this.miza = miza;
	}

	public ArrayList<Miza> getNajdeneMize() {
		return najdeneMize;
	}

	public void setNajdeneMize(ArrayList<Miza> najdeneMize) {
		this.najdeneMize = najdeneMize;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public ArrayList<Miza> getVse() {
		return vse;
	}

	public void setVse(ArrayList<Miza> vse) {
		this.vse = vse;
	}


	public ArrayList<Miza> getProste() {
		return proste;
	}


	public void setProste(ArrayList<Miza> proste) {
		this.proste = proste;
	}


	public ArrayList<Miza> getZasedene() {
		return zasedene;
	}


	public void setZasedene(ArrayList<Miza> zasedene) {
		this.zasedene = zasedene;
	}

	
	
	
	
}
