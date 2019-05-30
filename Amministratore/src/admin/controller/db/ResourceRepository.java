package admin.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.model.Risorsa;

public class ResourceRepository {
	DataSource ds;
	private static String resByUser = "select res_id, titolo, res_owner from RISORSA where res_owner = ?";
	private static String resByTitle = "select res_id, titolo, res_owner from RISORSA where titolo = ?";
	private static String resById = "select res_id, titolo, res_owner from RISORSA where res_id = ?";
	private static String resRemove = "delete from RISORSA where res_id = ?";

	public ResourceRepository(DataSource ds) {
		this.ds = ds;
	}

	public List<Risorsa> getRisorsaByUser(String username) {
		List<Risorsa> res = new ArrayList<Risorsa>();
		Connection c = null;
		try {
			c = ds.getConnection();

			PreparedStatement st = c.prepareStatement(resByUser);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Risorsa r = new Risorsa();

				r.setId(rs.getInt("res_id"));
				r.setTitolo(rs.getString("titolo"));
				r.setIdProprietario(rs.getString("res_owner"));

				res.add(r);
			}

			rs.close();
			st.close();
		} catch (PersistenceException | SQLException e) {
			System.out.print(e.getMessage());
		} finally {

			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.err.println("Impossibile chiudere la risorsa connessione");
				}
			}
		}

		return res;
	}

	public List<Risorsa> getRisorsaByTitolo(String key) {
		List<Risorsa> res = new ArrayList<Risorsa>();
		Connection c = null;
		try {
			c = ds.getConnection();

			PreparedStatement st = c.prepareStatement(resByTitle);
			st.setString(1, key);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Risorsa r = new Risorsa();

				r.setId(rs.getInt("res_id"));
				r.setTitolo(rs.getString("titolo"));
				r.setIdProprietario(rs.getString("res_owner"));

				res.add(r);
			}

			rs.close();
			st.close();
		} catch (PersistenceException | SQLException e) {
			System.out.print(e.getMessage());
		} finally {

			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.err.println("Impossibile chiudere la risorsa connessione");
				}
			}
		}

		return res;
	}

	public List<Risorsa> getRisorsaById(int key) {
		List<Risorsa> res = new ArrayList<Risorsa>();
		Connection c = null;
		try {
			c = ds.getConnection();

			PreparedStatement st = c.prepareStatement(resById);
			st.setInt(1, key);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Risorsa r = new Risorsa();

				r.setId(rs.getInt("res_id"));
				r.setTitolo(rs.getString("titolo"));
				r.setIdProprietario(rs.getString("res_owner"));

				res.add(r);
			}

			rs.close();
			st.close();
		} catch (PersistenceException | SQLException e) {
			System.out.print(e.getMessage());
		} finally {

			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.err.println("Impossibile chiudere la risorsa connessione");
				}
			}
		}

		return res;
	}

	public boolean removeRisorsaByID(int id) {
		boolean res = false;

		Connection c = null;

		try {
			c = ds.getConnection();

			PreparedStatement st = c.prepareStatement(resRemove);
			st.setInt(1, id);

			if (st.executeUpdate() != 1) {
				res = false;
			}
			res = true;
			st.close();
		} catch (PersistenceException | SQLException e) {
			System.out.print(e.getMessage());
			res = false;
		} finally {

			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.err.println("Impossibile chiudere la risorsa connessione");
				}
			}
		}
		return res;
	}

}
