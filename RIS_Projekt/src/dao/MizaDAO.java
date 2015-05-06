package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import beani.Miza;

public class MizaDAO {
	
	List<Miza> najdene = new ArrayList<Miza>();
	
	
	
	DataSource ds;
	
	public MizaDAO(DataSource ds){
		this.ds = ds;
		kreirajTabelo();
	}
	
	public void kreirajTabelo(){
		Connection con = null;
		
		try {
			con = ds.getConnection();
			con.createStatement().execute("create table IF NOT EXISTS miza(idMize INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL, ime VARCHAR(20), stMize INT,kapaciteta INT,posebneZahteveM VARCHAR(60),vrstaObroka VARCHAR(30),stGostov INT,uraPrihoda VARCHAR(5),zasedena BOOLEAN, slika VARCHAR(512),opis VARCHAR(50),telefon INT)");
			
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
	
	public void shrani(Miza m) throws SQLException{
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into miza(ime,stMize,kapaciteta,posebneZahteveM,vrstaObroka,stGostov,uraPrihoda,zasedena,slika,opis,telefon ) values (?,?,?,?,?,?,?,?,?,?,?)");
				//ps.setInt(1, m.getId());
				ps.setString(1, m.getIme());
				ps.setInt(2, m.getStMize());
				ps.setInt(3, m.getKapaciteta());
				ps.setString(4, m.getPosebneZahteveM());
				ps.setString(5, m.getVrstaObroka());
				ps.setDouble(6, m.getStGostov());
				ps.setString(7, m.getUraPrihoda());
				ps.setBoolean(8, m.isZasedena());
				ps.setString(9,m.getSlika());
				ps.setString(10, m.getOpis());
				ps.setInt(11,m.getTelefon());
				ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			con.close();
		}
	}
	
	//vnesejo se podatki osebe ki rezervira
	public void rezerviraj(Miza m){
		try {
			Connection con = ds.getConnection();
			
			PreparedStatement ps = con.prepareStatement("UPDATE miza SET ime = ? , telefon = ? , stGostov = ? , posebneZahteveM = ? , uraPrihoda = ? WHERE idMize = ?");
			ps.setString(1, m.getIme());
			ps.setInt(2, m.getTelefon());
			ps.setInt(3, m.getStGostov());
			ps.setString(4, m.getPosebneZahteveM());
			ps.setString(5, m.getUraPrihoda());
			ps.setInt(6, m.getId());
			//ps.executeUpdate();
			System.out.println(ps.executeUpdate());
			System.out.println(m.getStMize());
			//ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//vrne vse mize ki so v bazi
	public List<Miza> vrniVse() throws Exception {
		List<Miza> ret=new ArrayList<Miza>();
		Connection conn=null;
		try {
			conn=ds.getConnection();
			ResultSet rs=conn.createStatement().executeQuery("select * from miza");
			while (rs.next()) {
				Miza m=new Miza();
				m.setId(rs.getInt("idMize"));
				m.setIme(rs.getString("ime"));
				m.setStMize(rs.getInt("stMize"));
				m.setKapaciteta(rs.getInt("kapaciteta"));
				m.setPosebneZahteveM(rs.getString("posebneZahteveM"));
				m.setVrstaObroka(rs.getString("vrstaObroka"));
				m.setStGostov(rs.getInt("stGostov"));
				m.setUraPrihoda(rs.getString("uraPrihoda"));
				m.setZasedena(rs.getBoolean("zasedena"));
				m.setSlika(rs.getString("slika"));
				m.setOpis(rs.getString("opis"));
				m.setTelefon(rs.getInt("telefon"));
				ret.add(m);
			}
			rs.close();
		} finally {
			conn.close();
		}
		return ret;
	}
	
	
	//ko gostje odidejo se miza sprazni
	public void sprazniMizo(Miza m) throws SQLException{
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement("UPDATE miza SET ime = ? , telefon = ? , stGostov = ? , posebneZahteveM = ? , uraPrihoda = ? WHERE idMize = ?");
		ps.setString(1, null);
		ps.setInt(2, 0);
		ps.setInt(3, 0);
		ps.setString(4, null);
		ps.setString(5, null);
		ps.setInt(6, m.getId());
		ps.executeUpdate();
		System.out.println("miza"+m.getId());
	}
	
	//če išče po kapaciteti
	public List<Miza> najdi(Miza m, int kapaciteta) throws Exception {
		
		Connection con=ds.getConnection();
		
		try {
			con=ds.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select * from miza where kapaciteta=?");
			ps.setInt(1, m.getKapaciteta());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Miza l = new Miza();
				l.setIme(rs.getString("ime"));
				l.setStMize(rs.getInt("stMize"));
				l.setKapaciteta(rs.getInt("kapaciteta"));
				l.setPosebneZahteveM(rs.getString("posebneZahteveM"));
				l.setVrstaObroka(rs.getString("vrstaObroka"));
				l.setStGostov(rs.getInt("stGostov"));
				l.setUraPrihoda(rs.getString("uraPrihoda"));
				l.setZasedena(rs.getBoolean("zasedena"));
				l.setId(rs.getInt("idMize"));
				l.setSlika(rs.getString("slika"));
				l.setOpis(rs.getString("opis"));
				m.setTelefon(rs.getInt("telefon"));
				najdene.add(l);
			}
		} finally {
			con.close();
		}
		return najdene;
	}
	
	
	
	
}
