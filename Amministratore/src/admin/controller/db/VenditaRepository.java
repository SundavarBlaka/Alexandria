package admin.controller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.model.Vendita;

public class VenditaRepository {
	private String ric = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA";
	private String ricAcq = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA where acquirente = ?";
	private String ricVend = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA where venditore = ?";
	private String ricRis = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA where ris_venduta = ?";
	private String ricAcqVend = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA where acquirente = ? and venditore = ?";
	private String ricAcqRis = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA where acquirente = ? and ris_venduta = ?";
	private String ricVendRis = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA where venditore = ? and ris_venduta = ?";
	private String ricAcqVendRis = "select VENDITA_ID, ACQUIRENTE, VENDITORE, RIS_VENDUTA, DATA from VENDITA where acquirente = ? and venditore = ? and ris_venduta = ?";

	private DataSource ds;

	public VenditaRepository(DataSource ds) {
		this.ds = ds;
	}

	public List<Vendita> getVendite(String acq, String vend, String ris) {
		// capisci quale query vada eseguita sulla base dei valori vuoti
		List<Vendita> res = new ArrayList<Vendita>();

		int a = ((acq.isEmpty()) ? 0 : 1);
		int v = ((vend.isEmpty()) ? 0 : 2);
		int r = ((ris.isEmpty()) ? 0 : 4);

		int result = a | v | r;

		Connection c = null;

		try {
			c = ds.getConnection();
			PreparedStatement st = null;
			switch (result) {
			case 0: {
				st = c.prepareStatement(ric);
				break;
			}
			case 1: {
				st = c.prepareStatement(ricAcq);
				st.setString(1, acq);
				break;
			}
			case 2: {
				st = c.prepareStatement(ricVend);
				st.setString(1, vend);
				break;
			}
			case 3: {
				st = c.prepareStatement(ricAcqVend);
				st.setString(1, acq);
				st.setString(2, vend);
				break;
			}
			case 4: {

				st = c.prepareStatement(ricRis);
				st.setInt(1, Integer.parseInt(ris));
				break;
			}
			case 5: {
				st = c.prepareStatement(ricAcqRis);
				st.setString(1, acq);
				st.setInt(2, Integer.parseInt(ris));
				break;
			}
			case 6: {
				st = c.prepareStatement(ricVendRis);
				st.setString(1, vend);
				st.setInt(2, Integer.parseInt(ris));
				break;
			}
			case 7: {
				st = c.prepareStatement(ricAcqVendRis);
				st.setString(1, acq);
				st.setString(2, vend);
				st.setInt(3, Integer.parseInt(ris));
				break;
			}
			}

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Vendita toAdd = new Vendita();

				toAdd.setAcquirente(rs.getString("ACQUIRENTE"));
				toAdd.setVenditore(rs.getString("VENDITORE"));
				toAdd.setId(rs.getInt("VENDITA_ID"));
				toAdd.setIdRisorsaVenduta(rs.getInt("RIS_VENDUTA"));
				toAdd.setData(rs.getDate("DATA"));

				res.add(toAdd);
			}
		} catch (PersistenceException | SQLException e) {
			System.err.println("Errore durante l'esecuzione della query");
		} catch (NumberFormatException e) {
			System.err.println("Errore: l'identificativo di una risorsa deve essere numerico");
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

}
