package rezervacije;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

import beani.Soba;

@ManagedBean(name = "rezervacijaSobe")
@SessionScoped
public class RezervacijeSobe {

	private Soba soba = new Soba();
	private ArrayList<Soba> najdeneSobe = new ArrayList<Soba>();
	
	@PersistenceContext
	private EntityManager em;
	
	
	public void shrani(Soba s){
		//em.persist(s);
	}
	
	public ArrayList<Soba> najdi(Soba s){
		//najdeneSobe = (ArrayList<Soba>) em.createQuery("select s from Soba s").getResultList();
		return najdeneSobe;
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
	
}
