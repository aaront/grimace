/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.server;

import java.sql.*;
import grimace.client.Account;
import grimace.client.ContactList;

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

    public void initDatabase() throws SQLException {
        
    }

	private static void createTable(String tableName, boolean replace, String... cols) throws SQLException {
        Statement statement = connection.createStatement();
        if (cols.length < 1) { return; }
        String sql = "CREATE TABLE " + ((replace) ? "" : " IF NOT EXISTS " ) + tableName + " (";
        for (int i=0; i<cols.length; i++) {
            sql = cols[i] + ((i == cols.length-1) ? "" : ",");
        }
        
	}

	public static void saveAccount(Account acc) {

	}

	public static Account loadAccount(String username) {
        return null;
	}

	public static void deleteAccount(String username) {

	}

	public static void saveContactList(ContactList listt) {

	}

	public static ContactList loadContactList(String username) {
        return null;
	}
}
