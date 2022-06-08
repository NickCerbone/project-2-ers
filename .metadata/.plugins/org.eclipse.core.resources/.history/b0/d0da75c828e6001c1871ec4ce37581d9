package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	static Connection conn = null;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection makeConnection() throws SQLException {

		if (conn == null) {
			//conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ers", "postgres", "Pcqy?262");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ers", "postgres", "309758944Kk");
		}
		return conn;
	}

	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
