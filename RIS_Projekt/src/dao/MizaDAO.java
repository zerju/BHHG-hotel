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
			con.createStatement().execute("create table IF NOT EXISTS miza(idMize INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL, ime VARCHAR(20), stMize INT,kapaciteta INT,posebneZahteveM VARCHAR(60),vrstaObroka VARCHAR(30),stGostov INT,uraPrihoda VARCHAR(5),zasedena BOOLEAN, slika VARCHAR(50))");
			
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
				PreparedStatement ps=con.prepareStatement("insert into miza(idMize,ime,stMize,kapaciteta,posebneZahteveM,vrstaObroka,stGostov,uraPrihoda,zasedena,slika ) values (?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, m.getId());
				ps.setString(2, m.getIme());
				ps.setInt(3, m.getStMize());
				ps.setInt(4, m.getKapaciteta());
				ps.setString(5, m.getPosebneZahteveM());
				ps.setString(6, m.getVrstaObroka());
				ps.setDouble(7, m.getStGostov());
				ps.setString(8, m.getUraPrihoda());
				ps.setBoolean(9, m.isZasedena());
				ps.setString(10,m.getSlika());
				ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			con.close();
		}
	}
	
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
				ret.add(m);
			}
			rs.close();
		} finally {
			conn.close();
		}
		return ret;
	}
	
	
	
	
	
	
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
				najdene.add(l);
			}
		} finally {
			con.close();
		}
		return najdene;
	}
	
	
	
	
}