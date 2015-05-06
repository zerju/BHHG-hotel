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
	private Kontakt kontakt1 = new Kontakt();
	
	public Kontakt getKontakt1() {
		return kontakt1;
	}

	public void setKontakt1(Kontakt kontakt1) {
		this.kontakt1 = kontakt1;
	}

	public void shrani() throws SQLException{
		KontaktDAO dao = new KontaktDAO(ds);
		dao.shrani(kontakt1);
	}
	
	public Kontakt vrni() throws Exception{
		KontaktDAO dao = new KontaktDAO(ds);
		kontakt1 = dao.vrni();
		return kontakt1;
	}
	
	public void posodobiTelefon() throws Exception{
		KontaktDAO dao = new KontaktDAO(ds);
		dao.posodobiTelefon(kontakt1);
	}
	public void posodobiEmail() throws Exception{
		KontaktDAO dao = new KontaktDAO(ds);
		dao.posodobiEmail(kontakt1);
	}
	

	
}
