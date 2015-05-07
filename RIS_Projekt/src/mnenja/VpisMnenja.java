package mnenja;

import java.io.IOException; 
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import registracija.SessionBean;
import dao.MnenjeDAO;
import beani.Mnenje;

@ManagedBean(name = "vpisMnenja")
@SessionScoped
public class VpisMnenja {

	@Resource(lookup="java:jboss/datasources/bhhg")
	DataSource ds;
	
	private Mnenje mnenje = new Mnenje();
	
	public void shrani() throws IOException, SQLException{
		MnenjeDAO dao = new MnenjeDAO(ds);
		HttpSession session = SessionBean.getSession();
		mnenje.setUporabnik(session.getAttribute("username").toString());
		dao.shrani(mnenje);
	}

	public Mnenje getMnenje() {
		return mnenje;
	}

	public void setMnenje(Mnenje mnenje) {
		this.mnenje = mnenje;
	}
	
}