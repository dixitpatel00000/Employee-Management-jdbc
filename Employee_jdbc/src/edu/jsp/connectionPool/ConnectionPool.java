package edu.jsp.connectionPool;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class ConnectionPool {
	private static final int POOLSIZE = 5;

	private static ArrayList<Connection> list = new ArrayList<Connection>();
	private static String user = "postgres";
	private static String pass = "admin@123";
	private static String path = "jdbc:postgresql://localhost:5432/postgres";

	static {
		try {
//			FileInputStream Stream = new FileInputStream("src/main/resources/config.properties");
//			Properties properties = new Properties();
//			properties.load(Stream);
//			Class.forName("org.postgresql.Driver");
			for (int i = 0; i < POOLSIZE; i++) {
				list.add(createConnection());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection createConnection() {
		try {

			return DriverManager.getConnection(path, user, pass);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public static Connection getConnection() {
		try {
			if (!list.isEmpty()) {
				return list.remove(0);
			} else {
				return createConnection();
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;

	}

	public static void recieveConnection(Connection con) {
		try {
			if (list.size() < POOLSIZE) {
				list.add(con);

			} else {
				con.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
