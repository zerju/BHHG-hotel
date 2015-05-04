package rezervacije;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import dao.OsebaDAO;
import dao.SobaDAO;
import beani.Oseba;
import beani.Soba;

@ManagedBean(name = "rezervacijaSobe")
@SessionScoped
public class RezervacijeSobe {

	
	@Resource(lookup="java:jboss/datasources/bhhg")
	DataSource ds;
	
	private Oseba oseba = new Oseba();
	private Soba soba = new Soba();
	private ArrayList<Soba> najdeneSobe = new ArrayList<Soba>();
	private Part file1;
	private Part file2;
	private Part file3;
	private Part file4;
	private ArrayList<Soba> vse = new ArrayList<Soba>();
	private ArrayList<Soba> proste = new ArrayList<Soba>();
	private ArrayList<Soba> zasedene = new ArrayList<Soba>();
	private List<String> images;
	
	
	
	public ArrayList<Soba> najdi(Soba s){
		
		return najdeneSobe;
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
	
	public ArrayList<Soba> vrniVse() throws Exception{
		SobaDAO dao = new SobaDAO(ds);
		vse.clear();
		vse = (ArrayList<Soba>) dao.vrniVse();
		return vse;
	}
	
	public ArrayList<Soba> vrniProste() throws Exception{
		SobaDAO dao = new SobaDAO(ds);
		vse = (ArrayList<Soba>) dao.vrniVse();
		proste.clear();
		for(Soba s: vse){
			if(s.getIdOsebe()==0){
				proste.add(s);
			}
			
		}return proste;
	}
	
	public ArrayList<Soba> vrniZasedene() throws Exception{
		SobaDAO dao = new SobaDAO(ds);
		vse = (ArrayList<Soba>) dao.vrniVse();
		zasedene.clear();
		for(Soba s: vse){
			if(s.getIdOsebe()>0){
				System.out.println(s.getIdOsebe()+"hjehje");
				zasedene.add(s);
			}
			
		}return zasedene;
	}
	
	public Oseba sobaOseba(Soba s) throws SQLException{
		SobaDAO sdao = new SobaDAO(ds);
		this.oseba = sdao.pridobiOsebo(s);
		return oseba;
	}
	
	
	public void kopiraj(Soba s){
		soba = new Soba(s);
		System.out.println(soba.getIdSobe());
	}
	
	
	public void shrani() throws IOException, SQLException{
		OsebaDAO oseba = new OsebaDAO(ds);
		SobaDAO dao = new SobaDAO(ds);
		file1.write("C:/Users/Jure/Documents/GitHub/BHHG-hotel/RIS_Projekt/WebContent/Rezervacije/Slike/"+getFilename(file1));
		file2.write("C:/Users/Jure/Documents/GitHub/BHHG-hotel/RIS_Projekt/WebContent/Rezervacije/Slike/"+getFilename(file1));
		file3.write("C:/Users/Jure/Documents/GitHub/BHHG-hotel/RIS_Projekt/WebContent/Rezervacije/Slike/"+getFilename(file1));
		file4.write("C:/Users/Jure/Documents/GitHub/BHHG-hotel/RIS_Projekt/WebContent/Rezervacije/Slike/"+getFilename(file1));
		
		String potSlike1 = "../Rezervacije/Slike/"+getFilename(file1);
		String potSlike2 = "../Rezervacije/Slike/"+getFilename(file2);
		String potSlike3 = "../Rezervacije/Slike/"+getFilename(file3);
		String potSlike4 = "../Rezervacije/Slike/"+getFilename(file4);
		
		soba.setSlika1(potSlike1);
		soba.setSlika2(potSlike2);
		soba.setSlika3(potSlike3);
		soba.setSlika4(potSlike4);
		dao.shrani(soba);
	}
	
	
	public void pocisti(Soba s) throws SQLException{
		SobaDAO dao = new SobaDAO(ds);
		dao.sprazniSobo(s);
		//System.out.println(m.getStGostov());
	}
	
	public void rezerviraj() throws Exception{
		OsebaDAO odao = new OsebaDAO(ds);
		SobaDAO sdao = new SobaDAO(ds);
		odao.shrani(oseba);
		soba.setIdSobe(sdao.najdiID(soba));
		oseba.setIdOsebe(odao.najdiID(oseba));
		sdao.rezerviraj(soba,oseba);
		//System.out.println("----"+soba.getIdSobe()+oseba.getIdOsebe()+"-------");
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Oseba getOseba() {
		return oseba;
	}

	public void setOseba(Oseba oseba) {
		this.oseba = oseba;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}

	public ArrayList<Soba> getNajdeneSobe() {
		return najdeneSobe;
	}

	public void setNajdeneSobe(ArrayList<Soba> najdeneSobe) {
		this.najdeneSobe = najdeneSobe;
	}

	public Part getFile1() {
		return file1;
	}

	public void setFile1(Part file1) {
		this.file1 = file1;
	}

	public Part getFile2() {
		return file2;
	}

	public void setFile2(Part file2) {
		this.file2 = file2;
	}

	public Part getFile3() {
		return file3;
	}

	public void setFile3(Part file3) {
		this.file3 = file3;
	}

	public Part getFile4() {
		return file4;
	}

	public void setFile4(Part file4) {
		this.file4 = file4;
	}

	public ArrayList<Soba> getVse() {
		return vse;
	}

	public void setVse(ArrayList<Soba> vse) {
		this.vse = vse;
	}

	public ArrayList<Soba> getProste() {
		return proste;
	}

	public void setProste(ArrayList<Soba> proste) {
		this.proste = proste;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}


	public ArrayList<Soba> getZasedene() {
		return zasedene;
	}


	public void setZasedene(ArrayList<Soba> zasedene) {
		this.zasedene = zasedene;
	}
	
}
