package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import beani.Oseba;
import beani.Soba;

public class OsebaDAO {
	
	DataSource ds;
	
	List<Oseba> najdene = new ArrayList<Oseba>();
	
	public OsebaDAO(DataSource ds) {
		this.ds = ds;
		kreirajTabelo();
	}
	
	public void kreirajTabelo(){
		Connection con = null;
		
		try {
			con = ds.getConnection();
			con.createStatement().execute("create table IF NOT EXISTS oseba(idOsebe INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL,ime VARCHAR(20),priimek VARCHAR(20),email VARCHAR(40),telefon INT, tipOsebe VARCHAR(15),kartica VARCHAR(40))");
			
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
	
	public void shrani(Oseba o) throws SQLException{
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into oseba(idOsebe,ime,priimek,email,telefon,tipOsebe,kartica ) values (?,?,?,?,?,?,?)");
				ps.setInt(1, o.getIdOsebe());
				ps.setString(2, o.getIme());				
				ps.setString(3, o.getPriimek());
				ps.setString(4, o.getEmail());
				ps.setInt(5, o.getTelefon());
				ps.setString(6, o.getTipOsebe());
				ps.setString(7,o.getKartica());
				ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			con.close();
		}
	}
	
	public List<Oseba> vrniVse() throws Exception {
		List<Oseba> ret=new ArrayList<Oseba>();
		Connection conn=null;
		try {
			conn=ds.getConnection();
			ResultSet rs=conn.createStatement().executeQuery("select * from oseba");
			while (rs.next()) {
				Oseba o =new Oseba();
				o.setIdOsebe(rs.getInt("idOsebe"));
				o.setIme(rs.getString("ime"));
				o.setPriimek(rs.getString("priimek"));
				o.setEmail(rs.getString("email"));
				o.setTelefon(rs.getInt("telefon"));
				o.setTipOsebe(rs.getString("tipOsebe"));
				o.setKartica(rs.getString("kartica"));
				ret.add(o);
			}
			rs.close();
		} finally {
			conn.close();
		}
		return ret;
	}
	
public int najdiID(Oseba o) throws SQLException{
		
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement("select idOsebe from oseba where kartica=?");
		ps.setString(1,o.getKartica());
		ResultSet rs = ps.executeQuery();
		int raz = 0;
		while(rs.next()){
			raz = rs.getInt("idOsebe");
		}
		return raz;
		
	}
	
	
	
	
	public List<Oseba> najdi(Oseba o, String priimek) throws Exception {
		
		Connection con=ds.getConnection();
		
		try {
			con=ds.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select * from oseba where priimek=?");
			ps.setString(1, o.getPriimek());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Oseba l = new Oseba();
				l.setIdOsebe(rs.getInt("idOsebe"));
				l.setIme(rs.getString("ime"));
				l.setPriimek(rs.getString("priimek"));
				l.setEmail(rs.getString("email"));
				l.setTelefon(rs.getInt("telefon"));
				l.setTipOsebe(rs.getString("tipOsebe"));
				l.setKartica(rs.getString("kartica"));
				najdene.add(l);
			}
		} finally {
			con.close();
		}
		return najdene;
	}
}
