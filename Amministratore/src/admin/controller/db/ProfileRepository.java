package admin.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.model.Profilo;

public class ProfileRepository {
	private DataSource ds;

	private static String profByUsername = "select p.username, p.nome, p.cognome, p.data, count(r.titolo) as numero from PROFILO p, RISORSA r where p.username = ? and r.res_owner = ? group by p.username, p.nome, p.cognome";
	private static String profRemove = "delete from PROFILO where username = ?";
	private static String profRemoveResourceByUsername = "delete from RISORSA where res_owner = ?";
	private static String profRemoveCommentByUsername = "delete from COMMENTO where mittente_commento= ?";

	public ProfileRepository(DataSource ds) {
		super();
		this.ds = ds;
	}

	public Profilo getProfiloByUsername(String username) {
		Connection c = null;
		Profilo res = null;

		try {
			c = ds.getConnection();
			PreparedStatement st = c.prepareStatement(profByUsername);

			st.setString(1, username);
			st.setString(2, username);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				res = new Profilo();
				res.setUsername(username);
				res.setNome(rs.getString("nome"));
				res.setCognome(rs.getString("nome"));
				res.setData(rs.getDate("data"));
				res.setNumeroRisorse(rs.getInt("numero"));
			}
			rs.close();
			st.close();
		} catch (PersistenceException | SQLException e) {
			System.err.println("Errore durante la ricerca");

		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.err.println("Errore durante la chiusura della connessione");
				}
			}
		}

		return res;
	}

	public boolean removeProfiloByUsername(String username) {
		boolean res = false;

		Connection c = null;

		try {
			c = ds.getConnection();
			c.setAutoCommit(false);

			PreparedStatement st = c.prepareStatement(profRemove);
			st.setString(1, username);

			if (st.executeUpdate() != 1) {
				res = false;
			} else {
				res = true;
			}

			if (res) {
				res = false;

				// rimuovi tutte le risorse associate
				st.close();
				st = c.prepareStatement(profRemoveResourceByUsername);
				st.setString(1, username);
				st.executeUpdate();

				//rimuovi tutti i commenti associati
				st.close();
				st = c.prepareStatement(profRemoveCommentByUsername);
				st.setString(1, username);
				st.executeUpdate();
				st.close();
				res = true;
			}
			if (res)
				c.commit();
			else
				c.rollback();
		} catch (PersistenceException | SQLException e) {
			System.out.print("Errore durante l'esecuzione della ricerca");
			if (c != null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					System.err.println("Impossibile chiudere correttamente la sessione");
				}
			}
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
