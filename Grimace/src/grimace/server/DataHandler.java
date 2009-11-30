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
        DataHandler.createTable("Accounts", false, "userName varchar(30)", "passWord varchar(40)", "connection int");
        DataHandler.createTable("UserSettings", false, "userName varchar(30)", "displayName varchar(100)", "displayPic varchar(100)");
        DataHandler.createTable("FontProperties", false, "userName varchar(30)", "fontName varchar(100)", "fontSize int", "fontColour char(7)", "fontStyle - varchar(50)");
        DataHandler.createTable("Contacts", false, "userName varchar(30)", "contactName varchar(30)", "contactNick varchar(100)");
    }

	private static void createTable(String tableName, boolean replace, String... cols) throws SQLException {
        Statement statement = connection.createStatement();
        if (cols.length < 1) { return; }
        String sql = "CREATE TABLE " + ((replace) ? "" : " IF NOT EXISTS " ) + tableName + " (";
        for (int i=0; i<cols.length; i++) {
            sql = sql + cols[i] + ((i == cols.length-1) ? "" : ",");
        }
        sql = sql + ")";
        statement.execute(sql);
        statement.close();
	}

    public static void createAccount(String userName, String passWord) {
        
    }

	public static void saveAccount(Account acc) {

	}

	public static Account loadAccount(String username) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM Accounts WHERE userName=" + username;
        ResultSet result = statement.executeQuery(sql);
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
