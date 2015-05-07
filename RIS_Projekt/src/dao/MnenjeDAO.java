package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import beani.Mnenje;

public class MnenjeDAO {

	DataSource ds;
	
	List<Mnenje> najdena = new ArrayList<Mnenje>();
	
	public MnenjeDAO(DataSource ds) {
		this.ds = ds;
		kreirajTabelo();
	}
	
	public void kreirajTabelo(){
		Connection con = null;
		
		try {
			con = ds.getConnection();
			con.createStatement().execute("create table IF NOT EXISTS mnenje(idMnenja INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL,sumOcena VARCHAR(15),uporabnik VARCHAR(25),ocenaStoritev VARCHAR(15),ocenaSobe VARCHAR(15),ocenaLokacije VARCHAR(15),naslov VARCHAR(30),vsebina VARCHAR(500),vrstaIzleta VARCHAR(30),letniCas VARCHAR(20))");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public void shrani(Mnenje m) throws SQLException{
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into mnenje(idMnenja,uporabnik,sumOcena,ocenaStoritev,ocenaSobe,ocenaLokacije,naslov,vsebina,vrstaIzleta,letniCas) values (?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, m.getIdMnenja());
				ps.setString(2, m.getUporabnik());
				ps.setString(3, m.getSumOcena());
				ps.setString(4, m.getOcenaStoritev());
				ps.setString(5, m.getOcenaSobe());
				ps.setString(6,m.getOcenaLokacije());
				ps.setString(7, m.getNaslov());
				ps.setString(8,m.getVsebina());
				ps.setString(9, m.getVrstaIzleta());
				ps.setString(10,m.getLetniCas());
				ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			con.close();
		}
	}
	
	public List<Mnenje> vrniVse() throws Exception {
		List<Mnenje> ret=new ArrayList<Mnenje>();
		Connection conn=null;
		try {
			conn=ds.getConnection();
			ResultSet rs=conn.createStatement().executeQuery("select * from mnenje");
			while (rs.next()) {
				Mnenje m =new Mnenje();
				m.setIdMnenja(rs.getInt("idMnenja"));
				m.setUporabnik(rs.getString("uporabnik"));
				m.setSumOcena(rs.getString("sumOcena"));
				m.setOcenaStoritev(rs.getString("ocenaStoritev"));
				m.setOcenaStoritev(rs.getString("ocenaSobe"));
				m.setOcenaLokacije(rs.getString("ocenaLokacije"));
				m.setNaslov(rs.getString("naslov"));
				m.setVsebina(rs.getString("vsebina"));
				m.setVrstaIzleta(rs.getString("vrstaIzleta"));
				m.setLetniCas(rs.getString("letniCas"));
				ret.add(m);
			}
			rs.close();
		} finally {
			conn.close();
		}
		return ret;
	}
	
	
	
	
	
	
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
				l.setUporabnik(rs.getString("uporabnik"));
				l.setSumOcena(rs.getString("sumOcena"));
				l.setOcenaStoritev(rs.getString("ocenaStoritev"));
				l.setOcenaStoritev(rs.getString("ocenaSobe"));
				l.setOcenaLokacije(rs.getString("ocenaLokacije"));
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
	}
}
