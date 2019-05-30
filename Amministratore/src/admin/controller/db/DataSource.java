package admin.controller.db;

import java.sql.*;

public class DataSource {
	// nome del database
	private String dbName = "hibernate";

	@SuppressWarnings("deprecation")
	public Connection getConnection() throws PersistenceException {
		String driver;
		String dbUri;
		String userName = "";
		String password = "";

		driver = "com.mysql.cj.jdbc.Driver";
		dbUri = "jdbc:mysql://localhost:3306/" + dbName;
		userName = "root";
		password = "root";

		Connection connection = null;
		try {
			// System.out.println("DataSource.getConnection() driver = " + driver);
			Class.forName(driver).newInstance();
			// System.out.println("DataSource.getConnection() dbUri = " + dbUri);

			connection = DriverManager.getConnection(dbUri, userName, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} catch (InstantiationException e) {
			throw new PersistenceException(e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
