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

public class SobaDAO {

	
List<Soba> najdene = new ArrayList<Soba>();
	
	
	
	DataSource ds;
	
	public SobaDAO(DataSource ds){
		this.ds = ds;
		kreirajTabelo();
	}
	
	public void kreirajTabelo(){
		Connection con = null;
		
		try {
			con = ds.getConnection();
			con.createStatement().execute("create table IF NOT EXISTS soba(idSobe INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL,stSobe INT,kapaciteta INT,tip VARCHAR(20),zasedena BOOLEAN, opis VARCHAR(100),cena INT,slika1 VARCHAR(256),slika2 VARCHAR(256), slika3 VARCHAR(256), slika4 VARCHAR(256),oseba_id INT,datumPrihoda VARCHAR(20), datumOdhoda VARCHAR(20),FOREIGN KEY (oseba_id) REFERENCES oseba(idOsebe) ON DELETE CASCADE)");
			
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
				PreparedStatement ps=con.prepareStatement("insert into soba(idSobe,stSobe,kapaciteta,tip,zasedena, opis,cena,slika1,slika2,slika3,slika4,datumPrihoda,datumOdhoda ) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, s.getIdSobe());
				ps.setInt(2, s.getStSobe());				
				ps.setInt(3, s.getKapaciteta());
				ps.setString(4, s.getTip());
				ps.setBoolean(5, s.isZasedena());
				ps.setString(6, s.getOpis());
				ps.setInt(7, s.getCena());
				ps.setString(8, s.getSlika1());
				ps.setString(9, s.getSlika2());
				ps.setString(10, s.getSlika3());
				ps.setString(11, s.getSlika4());
				ps.setString(12, s.getDatumPrihoda());
				ps.setString(13,s.getDatumOdhoda());
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
				s.setOpis(rs.getString("opis"));
				s.setCena(rs.getInt("cena"));
				s.setSlika1(rs.getString("slika1"));
				s.setSlika2(rs.getString("slika2"));
				s.setSlika3(rs.getString("slika3"));
				s.setSlika4(rs.getString("slika4"));
				s.setDatumPrihoda(rs.getString("datumPrihoda"));
				s.setDatumOdhoda(rs.getString("datumOdhoda"));
				ret.add(s);
			}
			rs.close();
		} finally {
			conn.close();
		}
		return ret;
	}
	
	
	public void sprazniSobo(Soba s) throws SQLException{
		Connection con = ds.getConnection();
		
		PreparedStatement ps = con.prepareStatement("UPDATE soba SET oseba_id = ?, datumPrihoda = ?, datumOdhoda = ? WHERE idSobe = ?");
		ps.setInt(1,0);
		ps.setString(2,null);
		ps.setString(2,null);
		ps.setInt(4, s.getIdSobe());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public void rezerviraj(Soba s,Oseba o) throws SQLException{
		Connection con = ds.getConnection();
		
		PreparedStatement ps = con.prepareStatement("UPDATE soba SET oseba_id = ?, datumPrihoda = ?, datumOdhoda = ? WHERE idSobe = ?");
		ps.setInt(1,o.getIdOsebe());
		ps.setString(2,s.getDatumPrihoda());
		ps.setString(3,s.getDatumOdhoda());
		ps.setInt(4, s.getIdSobe());
		System.out.println(s.getDatumOdhoda()+s.getDatumPrihoda()+s.getIdSobe()+o.getIdOsebe());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public int najdiID(Soba s) throws SQLException{
		
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement("select idSobe from soba where stSobe=?");
		ps.setInt(1,s.getStSobe());
		ResultSet rs = ps.executeQuery();
		int raz = 0;
		while(rs.next()){
			raz = rs.getInt("idSobe");
		}
		return raz;
		
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
				l.setOpis(rs.getString("opis"));
				l.setCena(rs.getInt("cena"));
				l.setSlika1(rs.getString("slika1"));
				l.setSlika2(rs.getString("slika2"));
				l.setSlika3(rs.getString("slika3"));
				l.setSlika4(rs.getString("slika4"));
				l.setDatumPrihoda(rs.getString("datumPrihoda"));
				l.setDatumOdhoda(rs.getString("datumOdhoda"));
				najdene.add(l);
			}
		} finally {
			con.close();
		}
		return najdene;
	}
}
