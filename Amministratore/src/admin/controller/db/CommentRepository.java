package admin.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.model.Commento;

public class CommentRepository {
	private DataSource ds;

	private String cByRes = "select comment_id, risorsa_associata, mittente_commento, data from COMMENTO where risorsa_associata = ?";
	private String cByUser = "select comment_id, risorsa_associata, mittente_commento, data from COMMENTO where mittente_commento = ?";
	private String remove = "delete from COMMENTO where comment_id = ?";

	public CommentRepository(DataSource ds) {
		this.ds = ds;
	}

	public List<Commento> getCommentoByRisorsa(int id) {
		List<Commento> res = new ArrayList<Commento>();
		Connection c = null;
		try {
			c = ds.getConnection();

			PreparedStatement st = c.prepareStatement(cByRes);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Commento r = new Commento();

				r.setId(rs.getLong("comment_id"));
				r.setData(rs.getDate("data"));
				r.setIdRisorsaConnessa(rs.getInt("risorsa_associata"));
				r.setMittente(rs.getString("mittente_commento"));

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

	public List<Commento> getCommentoByUsername(String username) {
		List<Commento> res = new ArrayList<Commento>();
		Connection c = null;
		try {
			c = ds.getConnection();

			PreparedStatement st = c.prepareStatement(cByUser);
			st.setString(1, username);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Commento r = new Commento();

				r.setId(rs.getLong("comment_id"));
				r.setData(rs.getDate("data"));
				r.setIdRisorsaConnessa(rs.getInt("risorsa_associata"));
				r.setMittente(rs.getString("mittente_commento"));

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

	public boolean removeCommentoById(int identificativo) {
		boolean res = false;

		Connection c = null;

		try {
			c = ds.getConnection();

			PreparedStatement st = c.prepareStatement(remove);
			st.setInt(1, identificativo);

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
