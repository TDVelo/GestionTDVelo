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

	String query = "SELECT * FROM epreuve order by discipline,dossard";
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
			result = select.executeQuery(query);

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
			LOG.error("Erreur SQL : " + query, e);
		} finally {
			if (select != null) {
				try {
					select.close();
				} catch (SQLException e) {
					LOG.error("Erreur SQL : " + query, e);
				}
			}
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					LOG.error("Erreur SQL : " + query, e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOG.error("Erreur SQL : " + query, e);
				}
			}
		}
		return lstEpreuve;
	}

}