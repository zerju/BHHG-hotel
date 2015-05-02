package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import beani.Soba;

public class SobaDAO {

	
List<Soba> najdene = new ArrayList<Soba>();
	
	
	@Resource(lookup="java:jboss/datasources/bhhg")
	DataSource ds;
	
	public SobaDAO(){
		kreirajTabelo();
	}
	
	public void kreirajTabelo(){
		Connection con = null;
		
		try {
			con = ds.getConnection();
			con.createStatement().execute("create table IF NOT EXISTS soba(idSobe INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL,stMize INT,kapaciteta INT,tip VARCHAR(20),zasedena BOOLEAN)");
			
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
	
	public void shrani(Soba s) throws SQLException{
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();
				PreparedStatement ps=con.prepareStatement("insert into soba(idSobe,stSobe,kapaciteta,tip,zasedena ) values (?,?,?,?,?)");
				ps.setInt(1, s.getIdSobe());
				ps.setInt(2, s.getStSobe());				
				ps.setInt(3, s.getKapaciteta());
				ps.setString(4, s.getTip());
				ps.setBoolean(5, s.isZasedena());
				ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			con.close();
		}
	}
	
	public List<Soba> vrniVse() throws Exception {
		List<Soba> ret=new ArrayList<Soba>();
		Connection conn=null;
		try {
			conn=ds.getConnection();
			ResultSet rs=conn.createStatement().executeQuery("select * from soba");
			while (rs.next()) {
				Soba s =new Soba();
				s.setIdSobe(rs.getInt("idSobe"));
				s.setStSobe(rs.getInt("stSobe"));
				s.setKapaciteta(rs.getInt("kapaciteta"));
				s.setTip(rs.getString("tip"));
				s.setZasedena(rs.getBoolean("zasedena"));
				ret.add(s);
			}
			rs.close();
		} finally {
			conn.close();
		}
		return ret;
	}
	
	
	
	
	
	
	public List<Soba> najdi(Soba m, int kapaciteta) throws Exception {
		
		Connection con=ds.getConnection();
		
		try {
			con=ds.getConnection();
			
			PreparedStatement ps = con.prepareStatement("select * from soba where kapaciteta=?");
			ps.setInt(1, m.getKapaciteta());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Soba l = new Soba();
				l.setIdSobe(rs.getInt("idSobe"));
				l.setStSobe(rs.getInt("stSobe"));
				l.setKapaciteta(rs.getInt("kapaciteta"));
				l.setTip(rs.getString("tip"));
				l.setZasedena(rs.getBoolean("zasedena"));
				najdene.add(l);
			}
		} finally {
			con.close();
		}
		return najdene;
	}
}
