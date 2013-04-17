package fr.gillouard.tdvelo.server.coureur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.gillouard.tdvelo.client.coureur.service.CoureurService;
import fr.gillouard.tdvelo.server.DataSource;
import fr.gillouard.tdvelo.shared.Coureur;

public class CoureurServiceImpl extends RemoteServiceServlet implements
		CoureurService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** LOGGER. **/
	private static final Log LOG = LogFactory.getLog(CoureurServiceImpl.class);

	String query = "SELECT * FROM coureur order by categorie,dossard";

	@Override
	public List<Coureur> getListeCoureur() throws IllegalArgumentException {

		final List<Coureur> lstCoureur = new ArrayList<Coureur>();
		Connection conn = null;
		Statement select = null;
		ResultSet result = null;

		try {
			conn = DataSource.getInstance().getConnection();
			select = conn.createStatement();
			result = select.executeQuery(query);

			while (result.next()) {
				final Coureur coureur = new Coureur();
				coureur.setDossard(result.getInt("dossard"));
				coureur.setNom(result.getString("nom"));
				coureur.setPrenom(result.getString("prenom"));
				if ("M".equals(result.getString("sexe"))) {
					coureur.setSexe(true);
				} else {
					coureur.setSexe(false);
				}
				coureur.setCategorie(result.getString("categorie"));
				coureur.setClub(result.getString("club"));
				coureur.setEquipe(result.getString("equipe"));
				lstCoureur.add(coureur);
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
		return lstCoureur;
	}

}
