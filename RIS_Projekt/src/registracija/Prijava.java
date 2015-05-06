
package registracija;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.LoginDAO;
import beani.Login;
import beani.RegistriranUporabnik;

@ManagedBean(name = "login")
@SessionScoped

public class Prijava {
	@Resource(lookup = "java:jboss/datasources/bhhg")
	DataSource ds;

	
	private Login user = new Login();
	private RegistriranUporabnik uporabnik = new RegistriranUporabnik();
	
	
	
	public String validiramo() throws Exception {
		LoginDAO dao = new LoginDAO(ds);
		
        boolean valid = dao.validacija(uporabnik, user.getUsername(),user.getPassword());
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user.getUsername());
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Napačno uporabniško ime in geslo",
                            "Prosim vnesite pravilno uporabniško ime in geslo"));
            return "login";
        }
    }
 
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }

    
    
    
	public Login getUser() {
		return user;
	}

	public void setUser(Login user) {
		this.user = user;
	}
	
}
