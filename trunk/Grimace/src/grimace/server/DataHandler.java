/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.server;

import java.sql.*;

/**
 *
 * @author Vineet Sharma
 */
public class DataHandler {
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_NAME = "WernickeData";
	private static final String CONNECTION_URL = "jdbc:derby:" + DB_NAME + ";create=true";
	private static Connection connection;

	public static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
        connection = DriverManager.getConnection(CONNECTION_URL);
	}

    public initDatabase() {
        
    }

	private static void createTable(String tableName, String... cols) {

	}

	public static void saveAccount(Account acc) {

	}

	public static Account loadAccount(String username) {

	}

	public static void deleteAccount(String username) {

	}

	public static void saveContactList(ContactList listt) {

	}

	public static ConactList loadContactList(String username) {

	}
}
