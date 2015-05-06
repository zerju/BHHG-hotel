package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import beani.Mnenje;
import beani.Kontakt;
import beani.OHotelu;

public class KontaktDAO {

	@Resource(lookup = "java:jboss/datasources/bhhg")
	DataSource ds;

	List<Kontakt> najdena = new ArrayList<Kontakt>();

	public KontaktDAO(DataSource ds) {
		this.ds = ds;
		kreirajTabelo();
	}

	public void kreirajTabelo() {
		Connection con = null;

		try {
			con = ds.getConnection();
			con.createStatement()
					.execute(
							"create table IF NOT EXISTS kontakt(idKontakta INT PRIMARY KEY AUTO_INCREMENT DEFAULT NULL,telefon VARCHAR(11),email VARCHAR(50))");

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

	public void shrani(Kontakt k) throws SQLException {
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into kontakt(idKontakta,telefon,email) values (?,?,?)");
			ps.setInt(1, k.getIdKontakta());
			ps.setString(2, k.getTelefon());
			ps.setString(3, k.getEmail());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	public Kontakt vrni() throws Exception {
		Kontakt h = new Kontakt();
		Connection conn = null;
		try {
			conn = ds.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from hotel");
			while (rs.next()) {
				h.setIdKontakta(rs.getInt("idKontakta"));
				h.setTelefon(rs.getString("telefon"));
				h.setEmail(rs.getString("email"));
			}
			rs.close();
		} finally {
			conn.close();
		}
		return h;
	}

	public void posodobiTelefon(Kontakt k) throws Exception {
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();

			PreparedStatement ps = con
					.prepareStatement("update kontakt set telefon=? where idKontakta=1");
			ps.setString(1, k.getTelefon());
			ps.executeUpdate();
		} finally {
			con.close();
		}
	}

	public void posodobiEmail(Kontakt k) throws Exception {
		Connection con = ds.getConnection();
		try {
			con = ds.getConnection();

			PreparedStatement ps = con
					.prepareStatement("update kontakt set email=? where idKontakta=1");
			ps.setString(1, k.getEmail());
			ps.executeUpdate();
		} finally {
			con.close();
		}
	}

}
