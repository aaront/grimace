/**
 * DataHandler.java
 *
 * @author Vineet Sharma
 *
 * Copyright (C) 2009 Justin Cole, Aaron Jankun, David Marczak, Vineet Sharma,
 *        and Aaron Toth
 *
 * This file is part of Wernicke.
 *
 * Wernicke is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package grimace.server;

import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import grimace.client.Account;
import grimace.client.ContactList;
import grimace.client.Contact;

/**
 * DataHandler provides methods for interfacing with Wernicke's server database.
 * @author Vineet Sharma
 */
public class DataHandler {
    private static final String DB_DRIVER =
                                "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_NAME = "WernickeData";
	private static final String CONNECTION_URL =
                                "jdbc:derby:" + DB_NAME + ";create=true";
	private static Connection connection;

	public static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
        connection = DriverManager.getConnection(CONNECTION_URL);
	}

    /**
     * Creates all necessary tables if the do not exist.
     *
     * @throws java.sql.SQLException
     */
    public void initDatabase() throws SQLException {
        DataHandler.createTable("Accounts", false,
                                "userName varchar(30)",
                                "passWord varchar(40)",
                                "displayName varchar(100)",
                                "displayPic text",
                                "fontName varchar(100)",
                                "fontSize int",
                                "fontColour char(7)",
                                "fontStyle varchar(50)",
                                "connection int");
        DataHandler.createTable("Contacts", false,
                                "userName varchar(30)",
                                "contactName varchar(30)");
    }

    /**
     * Creates a table with the given table name and columns.
     *
     * @param tableName The name of the table to create
     * @param replace   Whether or not to overwrite an existing table with the
     *                  same name.
     * @param cols      The columns of the table, each followed by its type,
     *                  separated by a space.
     *                  For example, "wernickeCol varchar(100)" represents a
     *                  column named wernickeCol of type varchar(100).
     * @throws java.sql.SQLException
     */
	private static void createTable(String tableName,
                                    boolean replace,
                                    String... cols) throws SQLException {
        Statement statement = connection.createStatement();
        if (cols.length == 0) { return; }
        String sql = "CREATE TABLE " + ((replace) ? "" : " IF NOT EXISTS " )
                        + tableName + " (";
        for (int i=0; i<cols.length; i++) {
            sql = sql + cols[i] + ((i == cols.length-1) ? "" : ",");
        }
        sql = sql + ")";
        statement.execute(sql);
        statement.close();
	}

    /**
     * Creates an Account witht the given userName and password.
     *
     * @param userName  The userName for the new account.
     * @param passWord  The password for the new account, which will be stored
     *                  as a hexadecimal representation of the SHA-1 hash of the
     *                  string provided.
     * @throws java.sql.SQLException
     */
    public static synchronized void createAccount(String userName,
                                    String passWord) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO Accounts (userName, passWord) VALUES(\'"
                       + userName + "\',\'"
                       + getPasswordHash(passWord) +"\')";
        statement.execute(sql);
        statement.close();
    }

    /**
     * Returns a hash of the given password string using the SHA-1 algorithm.
     *
     * @param passWord  The string to hash.
     * @return  A string of hex digits representing the hashed value.
     */
    private static synchronized String getPasswordHash(String passWord) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch (Exception e) {
            return "";
        }

        StringBuffer hash;
        try {
            byte[] hashBytes = md.digest(passWord.getBytes("UTF-8"));
            hash = new StringBuffer(hashBytes.length * 2);
            for (byte b : hashBytes) {
                String hex = String.format("%02X", b); //$NON-NLS-1$
                hash.append(hex);
            }
        }
        catch (Exception e) {
            return "";
        }
        return hash.toString();
    }

    /**
     * Updates the database with the information in the given account.
     *
     * @param acc   The account to save.
     */
	public static void saveAccount(Account acc) {
        
	}

    /**
     * Returns an Account with the given userName and the settings associated
     * with that userName.
     *
     * @param userName  The userName of the Account to load.
     * @return  An Account containing the given userName and settings associated
     *          with that userName.
     * @throws java.sql.SQLException
     */
	public static synchronized Account loadAccount(String userName) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM Accounts WHERE userName=\'" + userName +"\'";
        ResultSet result = statement.executeQuery(sql);
        return null;
	}

    /**
     * Returns the display name associated with the account with the given
     * userName.
     *
     * @param userName  The userName of the account whose displayName is
     *                  required.
     * @return  The displayName of the Account with the given userName, or an
     *          empty string if that Account does not exist.
     */
    public static String getDisplayName(String userName) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT displayName FROM Accounts WHERE userName=\'" + userName +"\'";
            ResultSet result = statement.executeQuery(sql);
            statement.close();
            if (!result.next()) { return ""; }
            String dname = result.getNString("displayName");
            result.close();
            return dname;
        }
        catch (Exception e) { return ""; }
    }

    /**
     * Deletes an account with the given userName.
     *
     * @param userName  The userName of the account to delete.
     * @throws java.sql.SQLException
     */
	public static synchronized void deleteAccount(String userName) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM Accounts WHERE userName="
                        + userName + " LIMIT 1";
        statement.executeUpdate(sql);
	}

    /**
     * Returns whether or not an account with the given userName exists.
     *
     * @param userName  The userName to check.
     * @return  True if an Account with the given userName exists, and false
     *          otherwise.
     */
    public static synchronized boolean accountExists(String userName) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Accounts WHERE userName=\'"
                        + userName +"\'";
            ResultSet result = statement.executeQuery(sql);
            if (!result.next()) { return false; }
        }
        catch (Exception e) { return false; }
        return true;
    }

    /**
     * Returns whether or not an account has a given contact in its contactList.
     *
     * @param userName  The userName to check.
     * @param contactName The contact to check for.
     * @return  True if an Contact with the given contactName exists in the
     *          ContactList of the Account with the given userName and false
     *          otherwise.
     */
    public static synchronized boolean contactExists(String userName,
                                                        String contactName) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Contacts WHERE userName=\'"
                            + userName +"\' AND contactName=\'"
                            + contactName +"\'";
            ResultSet result = statement.executeQuery(sql);
            if (!result.next()) { return false; }
        }
        catch (Exception e) { return false; }
        return true;
    }

    /**
     * Saves a ContactList by inserting any Contacts that do not already exist
     * and deleting any Contacts that are not found in the given list.
     *
     * @param userName The userName of the Account that holds the list.
     * @param list  The ContactList to save.
     */
    public static synchronized void saveContactList(String userName,
                                                    ContactList list) {
        if (!accountExists(userName)) { return; }
        ArrayList<Contact> contacts = list.getList();
        for (Contact c : contacts) {
            if (!contactExists(userName, c.getUserName())) {
                try {
                    Statement statement = connection.createStatement();
                    String sql = "INSERT INTO Contacts VALUES(\'"
                                + userName +"\', \'"
                                + c.getUserName() + "\')";
                    statement.execute(sql);
                }
                catch (Exception e) {}
            }
        }

	}

    /**
     * Returns the ContactList for an account.
     *
     * @param userName  The userName of the account.
     * @return  A ContactList with all contacts associated with the given
     *          userName.
     */
	public static synchronized ContactList loadContactList(String userName) {
        try {
            ContactList cList = new ContactList();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Accounts WHERE userName=\'"
                        + userName +"\'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Contact c = new Contact(result.getString("contactName"),
                                        result.getNString(""));
            }
            return cList;
        }
        catch (Exception e) { return null; }
	}
}