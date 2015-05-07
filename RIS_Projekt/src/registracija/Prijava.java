
package registracija;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.*;
import beani.Login;
import beani.RegistriranUporabnik;

@ManagedBean(name = "login")
@SessionScoped

public class Prijava {
	@Resource(lookup = "java:jboss/datasources/bhhg")
	DataSource ds;
	int counter= 0;
	
	private Login user = new Login();
	private RegistriranUporabnik uporabnik = new RegistriranUporabnik();
	
	
	
	public String validiramo() throws Exception {
		LoginDAO dao = new LoginDAO(ds);
		
        boolean valid = dao.validacija(uporabnik, user.getUsername(),user.getPassword());
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user.getUsername());
            counter = 1;
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Napačno uporabniško ime in geslo",
                            "Prosim vnesite pravilno uporabniško ime in geslo"));
            return "login";
        }
    }
	public void setSession(){
		if(counter==0){
		counter +=1;}
	}
	
	
	public boolean isAdmin(){
		HttpSession session = SessionBean.getSession();
		if(counter != 0){
			if(session.getAttribute("username").equals("admin")){
			return true;
		}}
			return false;
		
	}
	
	public boolean isNekaj() throws Exception{
		RegistriranUporabnikDAO dao = new RegistriranUporabnikDAO(ds);
		ArrayList<RegistriranUporabnik> vsi = (ArrayList<RegistriranUporabnik>) dao.vrniVse();
		HttpSession session = SessionBean.getSession();
		for(RegistriranUporabnik ru: vsi){
			if(counter != 0){
				if(session.getAttribute("username").equals(ru.getUporabniskoIme())){
				return true;
			}}
		}
		
		return false;
		
	}
	
	public boolean isNekaj12() throws Exception{
		RegistriranUporabnikDAO dao = new RegistriranUporabnikDAO(ds);
		ArrayList<RegistriranUporabnik> vsi = (ArrayList<RegistriranUporabnik>) dao.vrniVse();
		HttpSession session = SessionBean.getSession();
		for(RegistriranUporabnik ru: vsi){
			if(counter != 0){
				if(session.getAttribute("username").equals(ru.getUporabniskoIme())){
				return false;
			}}
		}
		
		return true;
		
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
	public RegistriranUporabnik getUporabnik() {
		return uporabnik;
	}
	public void setUporabnik(RegistriranUporabnik uporabnik) {
		this.uporabnik = uporabnik;
	}

    
    
    

	
}
