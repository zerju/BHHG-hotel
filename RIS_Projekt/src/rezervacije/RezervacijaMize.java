package rezervacije;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.persistence.*;

import beani.Miza;

@ManagedBean(name = "rezervacijaMize")
@SessionScoped
public class RezervacijaMize {

	private Miza miza = new Miza();
	private ArrayList<Miza> najdeneMize = new ArrayList<Miza>();
	
	
	/*@PersistenceContext
	private EntityManager em;*/
	
	public void shrani(Miza m){
		//em.persist(m);
	}
	
	public ArrayList<Miza> najdi(Miza m){
		//najdeneMize = (ArrayList<Miza>) em.createQuery("select m from Miza m").getResultList();
		return najdeneMize;
	}
	
	public boolean zasedenost(Miza m){
		return m.isZasedena();
	}
	
	
	
	

	public Miza getMiza() {
		return miza;
	}

	public void setMiza(Miza miza) {
		this.miza = miza;
	}

	public ArrayList<Miza> getNajdeneMize() {
		return najdeneMize;
	}

	public void setNajdeneMize(ArrayList<Miza> najdeneMize) {
		this.najdeneMize = najdeneMize;
	}

	
	
	
	
}
