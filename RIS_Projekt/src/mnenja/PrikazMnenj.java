package mnenja;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;

import dao.MnenjeDAO;
import beani.Mnenje;

@ManagedBean(name = "prikazMnenja")
@SessionScoped
public class PrikazMnenj {

	@Resource(lookup="java:jboss/datasources/bhhg")
	DataSource ds;
	
	private Mnenje mnenje = new Mnenje();
	private ArrayList<Mnenje> vse = new ArrayList<Mnenje>();
	
	
	public ArrayList<Mnenje> vrniVse() throws Exception{
		MnenjeDAO dao = new MnenjeDAO(ds);
		vse.clear();
		vse = (ArrayList<Mnenje>) dao.vrniVse();
		System.out.println(vse.get(0).getNaslov());
		return vse;
	}

	public Mnenje getMnenje() {
		return mnenje;
	}

	public void setMnenje(Mnenje mnenje) {
		this.mnenje = mnenje;
	}

	public ArrayList<Mnenje> getVse() {
		return vse;
	}

	public void setVse(ArrayList<Mnenje> vse) {
		this.vse = vse;
	}
	
}