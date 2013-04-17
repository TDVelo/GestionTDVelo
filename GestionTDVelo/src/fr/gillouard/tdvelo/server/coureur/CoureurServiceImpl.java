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

		try {
			Connection conn = DataSource.getInstance().getConnection();
			Statement select = conn.createStatement();
			ResultSet result = select.executeQuery(query);
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
			select.close();
			result.close();
			conn.close();
		} catch (SQLException e) {
			LOG.error("Erreur SQL : " + query, e);
		}
		return lstCoureur;
	}

}
