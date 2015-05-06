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

	@Resource(lookup = "java:jboss/datasources/bhhg")
	DataSource ds;

	List<OHotelu> najdena = new ArrayList<OHotelu>();

	public OHoteluDAO(DataSource ds) {
		this.ds = ds;
		kreirajTabelo();
	}

	public void kreirajTabelo() {
		Connection con = null;

		try {
			con = ds.getConnection();
			con.createStatement()
					.execute(
							"create table IF NOT EXISTS hotel(idHotela INT PRIMARY KEY,opis VARCHAR(512),slika VARCHAR(100))");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void shrani(OHotelu h) throws SQLException {
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into hotel(idHotela,opis) values (?,?)");
			ps.setInt(1, h.getIdHotela());
			ps.setString(2, h.getOpis());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public OHotelu vrni() throws Exception {
		OHotelu h = new OHotelu();
		Connection conn = null;
		try {
			conn = ds.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from hotel");
			while (rs.next()) {
								h.setIdHotela(rs.getInt("idHotela"));
				h.setOpis(rs.getString("opis"));
				
			}
			rs.close();
		} finally {
			conn.close();
		}
		return h;
	}

	public void posodobiOpis(OHotelu h) throws Exception {
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();

			PreparedStatement ps = con
					.prepareStatement("update hotel set opis=? where idHotela=1");
			ps.setString(1, h.getOpis());
			ps.executeUpdate();
		} finally {
			con.close();
		}
	}
}
