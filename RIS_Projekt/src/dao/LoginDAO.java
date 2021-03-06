package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import beani.RegistriranUporabnik;;

public class LoginDAO {

	DataSource ds;

	public LoginDAO(DataSource ds) {
		this.ds = ds;
	}

	public boolean validacija(RegistriranUporabnik r, String uporabniskoIme, String geslo) throws Exception {

		Connection con = ds.getConnection();

		try {
			con = ds.getConnection();

			PreparedStatement ps = con.prepareStatement("select uporabniskoIme,geslo from registriranUporabnik where uporabniskoIme=? AND geslo=?");
			ps.setString(1, uporabniskoIme);
			ps.setString(2, geslo);
			ResultSet rs = ps.executeQuery();
			
			/*
			 * RegistriranUporabnik l = new RegistriranUporabnik();
			 * l.setUporabniskoIme(rs.getString("uporabniskoIme"));
			 * l.setGeslo(rs.getString("geslo"));
			 */

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error-->" + ex.getMessage());
			return false;
		} finally {
			con.close();
		}
		return false;
	}
}