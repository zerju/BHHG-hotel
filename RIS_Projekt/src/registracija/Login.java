
package registracija;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import dao.LoginDAO;
import dao.RegistriranUporabnikDAO;
import beani.RegistriranUporabnik;

@ManagedBean(name = "login")
@SessionScoped

public class Login {
	@Resource(lookup = "java:jboss/datasources/bhhg")
	DataSource ds;

	private Login user = new Login();
	private ArrayList<RegistriranUporabnik> najdeniUporabniki = new ArrayList<RegistriranUporabnik>();

	public void shrani() throws IOException, SQLException {
		LoginDAO dao = new LoginDAO(ds);

		//dao.shrani(user);
	}

}

	


