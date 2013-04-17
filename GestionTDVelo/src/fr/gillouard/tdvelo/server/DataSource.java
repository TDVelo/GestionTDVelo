package fr.gillouard.tdvelo.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataSource {

	/** LOGGER. **/
	private static final Log LOG = LogFactory.getLog(DataSource.class);

	private Connection connection;
	private static DataSource dataSource;

	private DataSource() {
		connection = init();
	}

	public static Connection getInstance() {
		if (dataSource == null) {
			dataSource = new DataSource();
		}
		if(dataSource.getConnection() == null) {
			dataSource.init();
		}
		return dataSource.getConnection();
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	private synchronized Connection init() {
		Connection conn = null;

		try {
			Properties prop = new Properties();
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"TDVelo.properties"));

			Class.forName(prop.getProperty("db.driver")).newInstance();
			conn = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"),
				prop.getProperty("db.password"));

		} catch (final Exception e) {
			LOG.error(
					"Erreur lors de la recuperation de la connection a la base !",
					e);
		}
		return conn;

	}
}
