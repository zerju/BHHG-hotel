package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import beani.OHotelu;


public class OHoteluDAO {

		DataSource ds;
		
		List<OHotelu> najdena = new ArrayList<OHotelu>();
		
		public OHoteluDAO(DataSource ds) {
			this.ds = ds;
			kreirajTabelo();
		}
		
		public void kreirajTabelo(){
			Connection con = null;
			
			try {
				con = ds.getConnection();
				con.createStatement().execute("create table IF NOT EXISTS hotel(idHotela INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL,opis VARCHAR(512),slika VARCHAR(100))");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(con!=null){
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public void shrani(OHotelu h) throws SQLException{
			Connection con = ds.getConnection();
			try {
				con = ds.getConnection();
					PreparedStatement ps=con.prepareStatement("insert into hotel(idHotela,opis,slika ) values (?,?,?)");
					ps.setInt(1, h.getIdHotela());
					ps.setString(2, h.getOpis());
					ps.setString(3, h.getSlika());
					ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			 finally {
				con.close();
			}
		}
		
		public List<OHotelu> vrni() throws Exception {
			List<OHotelu> ret=new ArrayList<OHotelu>();
			Connection conn=null;
			try {
				conn=ds.getConnection();
				ResultSet rs=conn.createStatement().executeQuery("select * from hotel");
				while (rs.next()) {
					OHotelu h = new OHotelu();
					h.setIdHotela(rs.getInt("idHotela"));
					h.setOpis(rs.getString("opis"));
					h.setSlika(rs.getString("slika"));
					ret.add(h);
				}
				rs.close();
			} finally {
				conn.close();
			}
			return ret;
		}
		
		/*
		public List<Mnenje> najdi(Mnenje m, String vrstaIzleta) throws Exception {
			
			Connection con=ds.getConnection();
			
			try {
				con=ds.getConnection();
				
				PreparedStatement ps = con.prepareStatement("select * from mnenje where vrstaIzleta=?");
				ps.setString(1, m.getVrstaIzleta());
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					Mnenje l = new Mnenje();
					l.setIdMnenja(rs.getInt("idMnenja"));
					l.setOsebaString(rs.getString("oseba"));
					l.setSumOcena(rs.getInt("sumOcena"));
					l.setOcenaStoritev(rs.getInt("ocenaStoritev"));
					l.setOcenaLokacije(rs.getInt("ocenaLokacije"));
					l.setNaslov(rs.getString("naslov"));
					l.setVsebina(rs.getString("vsebina"));
					l.setVrstaIzleta(rs.getString("vrstaIzleta"));
					l.setLetniCas(rs.getString("letniCas"));
					najdena.add(l);
				}
			} finally {
				con.close();
			}
			return najdena;
		}*/
	}

