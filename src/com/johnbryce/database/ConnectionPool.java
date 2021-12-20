package com.johnbryce.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ConnectionPool {

	private static Set<Connection> connections;
	private static ConnectionPool instance;
	private static final int entryPoints = 3; // is this right?

	private ConnectionPool() {
		connections = new HashSet<Connection>();
	}

	public static ConnectionPool getInstance() {

		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();

					for (int i = 0; i < entryPoints; i++) {
						try {
							connections.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/CouponSystem",
									"root", "rakovic11"));
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		return instance;
	}

	public static Connection getConnection() {

		if (connections.isEmpty()) {
			try {
				connections.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Connection con = null;

		synchronized (connections) {

			Connection[] cons = (Connection[]) connections.toArray();
			con = cons[0];
			connections.remove(con);

		}

		return con;

	}

	public static void restoreConnection(Connection connection) {

		synchronized (connections) {

			if (connection != null) {

				System.out.println("Conneciton returned");
				connections.add(connection);

				connection.notify();
			}
		}

	}

	public static void closeAllConnections() {
		connections.clear();
	}

}

