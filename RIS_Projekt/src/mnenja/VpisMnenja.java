package mnenja;

import java.io.IOException; 
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

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
		
		dao.shrani(mnenje);
	}

	public Mnenje getMnenje() {
		return mnenje;
	}

	public void setMnenje(Mnenje mnenje) {
		this.mnenje = mnenje;
	}
	
}