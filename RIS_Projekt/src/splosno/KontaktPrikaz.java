package splosno;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import beani.Kontakt;
import beani.OHotelu;
import dao.KontaktDAO;
import dao.OHoteluDAO;





@ManagedBean(name = "kontakt")
@SessionScoped
public class KontaktPrikaz {

	@Resource(lookup="java:jboss/datasources/bhhg")
	DataSource ds;
	private Kontakt kontakt = new Kontakt();
	
	public Kontakt getKontakt() {
		return kontakt;
	}

	public void setKontakt(Kontakt kontakt) {
		this.kontakt = kontakt;
	}

	public void shrani() throws SQLException{
		KontaktDAO dao = new KontaktDAO(ds);
		dao.shrani(kontakt);
	}
	
	public void vrni() throws Exception{
		KontaktDAO dao = new KontaktDAO(ds);
		kontakt = dao.vrni();
	}
	
	public void posodobiTelefon(Kontakt kontakt) throws Exception{
		KontaktDAO dao = new KontaktDAO(ds);
		dao.posodobiTelefon(kontakt);
	}
	public void posodobiEmail(Kontakt kontakt) throws Exception{
		KontaktDAO dao = new KontaktDAO(ds);
		dao.posodobiEmail(kontakt);
	}
	

	
}
