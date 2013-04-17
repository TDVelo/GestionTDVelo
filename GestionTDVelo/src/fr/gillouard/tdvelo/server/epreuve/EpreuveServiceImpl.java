package fr.gillouard.tdvelo.server.epreuve;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.gillouard.tdvelo.client.epreuve.service.EpreuveService;
import fr.gillouard.tdvelo.server.DataSource;
import fr.gillouard.tdvelo.shared.Epreuve;

public class EpreuveServiceImpl extends RemoteServiceServlet implements
		EpreuveService {

	/** LOGGER. **/
	private static final Log LOG = LogFactory.getLog(EpreuveServiceImpl.class);

	public static String QUERRY_LISTE_EPREUVE = "SELECT * FROM epreuve order by discipline,dossard";
	/**
	 * UID
	 */
	private static final long serialVersionUID = -5217040857543351560L;

	@Override
	public List<Epreuve> getListeEpreuve() throws IllegalArgumentException {
		final List<Epreuve> lstEpreuve = new ArrayList<Epreuve>();
		Connection conn = null;
		Statement select = null;
		ResultSet result = null;

		try {
			conn = DataSource.getInstance().getConnection();
			select = conn.createStatement();
			result = select.executeQuery(QUERRY_LISTE_EPREUVE);

			while (result.next()) {
				final Epreuve epreuve = new Epreuve();
				epreuve.setDossard(result.getInt("dossard"));
				epreuve.setDiscipline(result.getString("discipline"));
				epreuve.setTemps(result.getDouble("temps"));
				epreuve.setPenalite(result.getDouble("penalite"));
				epreuve.setClassement(result.getInt("classement"));
				epreuve.setType(result.getString("type"));
				lstEpreuve.add(epreuve);
			}

		} catch (SQLException e) {
			LOG.error("Erreur SQL : " + QUERRY_LISTE_EPREUVE, e);
		} finally {
			if (select != null) {
				try {
					select.close();
				} catch (SQLException e) {
					LOG.error("Erreur SQL : " + QUERRY_LISTE_EPREUVE, e);
				}
			}
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					LOG.error("Erreur SQL : " + QUERRY_LISTE_EPREUVE, e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOG.error("Erreur SQL : " + QUERRY_LISTE_EPREUVE, e);
				}
			}
		}
		return lstEpreuve;
	}

	public void ajouterEpreuve(final Epreuve epreuve) throws Exception {
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = DataSource.getInstance().getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO epreuve (discipline, dossard, temps, penalite, type, classement) "
					+ "VALUES("
					+ "'"
					+ epreuve.getDiscipline()
					+ "'"
					+ ","
					+ epreuve.getDossard()
					+ ","
					+ epreuve.getTemps()
					+ ","
					+ epreuve.getPenalite()
					+ ","
					+ "'"
					+ epreuve.getType()
					+ "'" + "," + epreuve.getClassement() + ")");

		} catch (SQLException e) {
			LOG.error("Erreur SQL ajout epreuve" + epreuve, e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
