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
import java.awt.Color;
import grimace.client.Account;
import grimace.client.ContactList;
import grimace.client.Contact;

/**
 * DataHandler provides methods for interfacing with Wernicke's server database.
 * 
 * @author Vineet Sharma
 */
public class DataHandler {
    private static final String DB_DRIVER = "org.sqlite.JDBC";
                                //"org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DB_NAME = "WernickeData/WernickeData.db";
	private static final String CONNECTION_URL =
                                "jdbc:sqlite:" + DB_NAME;
	private static Connection connection;


    public static Connection getConnection() {
        return connection;
    }

	public static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
        connection = DriverManager.getConnection(CONNECTION_URL);
	}

    /**
     * Creates all necessary tables if the do not exist.
     *
     * @throws java.sql.SQLException
     */
    public static void initDatabase() {
        try {
            DataHandler.createTable("Accounts", false,
                                "userName varchar(30) PRIMARY KEY",
                                "password varchar(40)",
                                "displayName varchar(100)",
                                "displayStatus varchar(20)",
                                "fontName varchar(100)",
                                "fontSize int",
                                "fontColour int",
                                "fontItalic int",
                                "fontBold int");
            DataHandler.createTable("Contacts", false,
                                "userName varchar(30)",
                                "contactName varchar(30)");
            DataHandler.createTable("ContactRequests", false,
                                    "contactName varchar(30)",
                                    "senderName varchar(30)");
            DataHandler.createTable("FileTransferRequests", false,
                                    "contactName varchar(30)",
                                    "senderName varchar(30)",
                                    "fileName text");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
        StringBuffer sql = new StringBuffer("CREATE TABLE"
                                        + ((replace) ? " " : " IF NOT EXISTS ")
                                        + tableName + " (");
        for (int i=0; i<cols.length; i++) {
            sql.append(cols[i] + ((i == cols.length-1) ? "" : ","));
        }
        sql.append(")");
        statement.execute(sql.toString());
        statement.close();
	}

    public static void printContactRequests() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM ContactRequests");
            System.out.println("Requests:");
            while (result.next()) {
                System.out.println(result.getString(1) + ", " + result.getString(2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printContacts() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Contacts");
            System.out.println("Contacts:");
            while (result.next()) {
                System.out.println(result.getString(1) + ", " + result.getString(2));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printAccounts() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Accounts");
            System.out.println("Accounts:");
            while (result.next()) {
                System.out.println(result.getString(1) + ", " + result.getString(2) + ", " + result.getString(3));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an Account with the given userName and password.
     *
     * @param userName  The userName for the new account.
     * @param password  The password for the new account, which will be stored
     *                  as a hexadecimal representation of the SHA-1 hash of the
     *                  string provided.
     * @throws Exception
     */
    public static synchronized boolean createAccount(String userName,
                                                    String passHash,
                                                    String displayName)
                                                    throws Exception {
        if (accountExists(userName)) { return false; }
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO Accounts VALUES(\'"
                       + userName + "\',\'"
                       + passHash + "\',\'"
                       + displayName + "\',\'"
                       + Account.STATUS_INVISIBLE + "\',\'"
                       + Account.DEFAULT_FONT + "\',"
                       + String.valueOf(Account.DEFAULT_FONT_SIZE) + ","
                       + String.valueOf(Account.DEFAULT_FONT_COLOUR.getRGB())
                       + ",0,0)";
        statement.executeUpdate(sql);
        statement.close();
        return true;
    }

    /**
     * Updates the database with the information in the given account.
     *
     * @param acc   The account to save.
     */
	public static synchronized boolean saveAccount(Account acc) {
        if (!accountExists(acc.getUserName())) { return false; }
        try {
            Statement statement = connection.createStatement();
            StringBuffer sql = new StringBuffer("UPDATE Accounts SET ");
            sql.append("displayName=\'" + acc.getDisplayName() + "\',");
            sql.append("fontName=\'" + acc.getFont().getFontName() + "\',");
            sql.append("fontSize="
                    + String.valueOf(acc.getFont().getSize()) + ",");
            sql.append("fontColour="
                    + String.valueOf(acc.getFontColour().getRGB()) + ",");
            sql.append("fontItalic="
                    + (acc.getFont().isItalic() ? "1" : "0") + ",");
            sql.append("fontBold=" + (acc.getFont().isBold() ? "1" : "0"));
            sql.append(" WHERE username=\'" + acc.getUserName() + "\'");
            statement.executeUpdate(sql.toString());
            statement.close();
            saveContactList(acc.getUserName(), acc.getContactList());
        }
        catch (Exception e) { return false; }
        return true;
	}

    /**
     * Returns an Account with the given userName and the settings associated
     * with that userName.
     *
     * @param userName  The userName of the Account to load.
     * @return  An Account containing the given userName and settings associated
     *          with that userName.
     */
	public static synchronized Account loadAccount(String userName) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Accounts WHERE userName=\'"
                        + userName +"\'";
            ResultSet result = statement.executeQuery(sql);
            if (!result.next()) {
                result.close();
                return null;
            }
            Account acc = new Account(userName);
            acc.setDisplayName(result.getString("displayName"));
            acc.changeFont(result.getString("fontName"));
            acc.changeSize(result.getInt("fontSize"));
            acc.setFontColour(new Color(result.getInt("fontColour")));
            if (result.getInt("fontItalic") == 1) { acc.toggleItalic(); }
            if (result.getInt("fontBold") == 1) { acc.toggleBold(); }
            ContactList cList = loadContactList(userName);
            acc.setContactList(cList);
            return acc;
        }
        catch (Exception e) { return null; }
	}

    /**
     * Deletes an account with the given userName.
     *
     * @param userName  The userName of the account to delete.
     * @throws java.sql.SQLException
     */
	public static synchronized void deleteAccount(String userName)
                                                    throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM Accounts WHERE userName=\'"
                        + userName + "\'";
        statement.executeUpdate(sql);
        statement.close();
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
            if (!result.next()) {
                result.close();
                return false;
            }
            result.close();
            statement.close();
        }
        catch (Exception e) { return false; }
        return true;
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
    public static synchronized String getDisplayName(String userName) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT displayName FROM Accounts WHERE userName=\'"
                        + userName +"\'";
            ResultSet result = statement.executeQuery(sql);
            if (!result.next()) {
                result.close();
                return "";
            }
            String dname = result.getString("displayName");
            result.close();
            statement.close();
            return dname;
        }
        catch (Exception e) { return ""; }
    }

    /**
     * Returns the hash of the password associated with an Account.
     *
     * @param userName  The userName of the account whose password hash is
     *                  required.
     * @return  The password hash of the Account with the given userName. An
     *          Exception is thrown if the Account does not exist.
     * @throws Exception
     */
    public static synchronized String getAccountPasswordHash(String userName)
                                                        throws Exception {
        Statement statement = connection.createStatement();
        String sql = "SELECT password FROM Accounts WHERE userName=\'"
                    + userName +"\'";
        ResultSet result = statement.executeQuery(sql);
        if (!result.next()) {
            result.close();
            return "";
        }
        String hash = result.getString("password");
        result.close();
        statement.close();
        return hash;
    }

    public static synchronized boolean contactRequestExists(String userName,
                                                        String contactName) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM ContactRequests WHERE contactName=\'"
                            + contactName +"\' AND senderName=\'"
                            + userName +"\'";
            ResultSet result = statement.executeQuery(sql);
            if (!result.next()) {
                result.close();
                return false;
            }
            result.close();
            statement.close();
        }
        catch (Exception e) { return false; }
        return true;
    }

    /**
     * Places a contact request.
     *
     * @param userName  The user requesting the contact.
     * @param contactName  The name of the contact.
     * @throws java.sql.SQLException
     */
    public static synchronized void placeContactRequest(String userName,
                                    String contactName) throws SQLException {
        if (contactExists(userName, contactName)) { return; }
        if (contactRequestExists(userName, contactName)) { return; }
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO ContactRequests VALUES(\'"
                       + contactName + "\',\'"
                       + userName +"\')";
        statement.executeUpdate(sql);
        statement.close();
    }

    /**
     * Removes a contact request
     *
     * @param userName  The user requesting the contact
     * @param contactName The name of the contact.
     * @throws java.sql.SQLException
     */
	public static synchronized void clearContactRequest(String userName,
                                                    String contactName)
                                                    throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM ContactRequests WHERE contactName=\'"
                        + contactName + "\' AND senderName=\'"
                        + userName + "\'";
        statement.executeUpdate(sql);
        statement.close();
	}

    public static synchronized Object[] getContactRequestCommands(String userName) {
        ArrayList<Command> cmdList = new ArrayList<Command>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM ContactRequests "
                                                        + "WHERE contactName=\'"
                                                        + userName + "\'");
            while (result.next()) {
                cmdList.add(new Command(Command.CONTACT_REQUEST,result.getString("senderName")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return cmdList.toArray();
    }

    /**
     * Adds a contact to an Account with the given userName.
     *
     * @param userName  The userName of the account.
     * @param contactName  The name of the contact to add to the account.
     * @throws java.sql.SQLException
     */
    public static synchronized void addContact(String userName,
                                    String contactName) throws SQLException {
        if (contactExists(userName, contactName)) { return; }
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO Contacts VALUES(\'"
                       + userName + "\',\'"
                       + contactName +"\')";
        statement.executeUpdate(sql);
        statement.close();
    }

    /**
     * Deletes a contact from the Account with the given userName.
     *
     * @param userName  The userName of the account.
     * @param contactName The name of the contact to delete.
     * @throws java.sql.SQLException
     */
	public static synchronized void deleteContact(String userName,
                                                    String contactName)
                                                    throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM Contacts WHERE userName=\'"
                        + userName + "\' AND contactName=\'"
                        + contactName + "\'";
        statement.executeUpdate(sql);
        statement.close();
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
            if (!result.next()) {
                result.close();
                return false;
            }
            result.close();
            statement.close();
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
            try {
                addContact(userName, c.getUserName());
            }
            catch (Exception e) {}
        }
        ArrayList<Contact> compList = loadContactList(userName).getList();
        for (Contact c : compList) {
            try {
                if (!contacts.contains(c)) {
                    deleteContact(userName,c.getUserName());
                }
            }
            catch (Exception e) {}
        }
	}

    /**
     * Returns the ContactList for an account.
     *
     * @param userName  The userName of the account.
     * @return  A ContactList with all contacts associated with the given
     *          userName, or null if the Account does not exist.
     */
	public static synchronized ContactList loadContactList(String userName) {
        try {
            ContactList cList = new ContactList();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Contacts WHERE userName=\'"
                        + userName +"\'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String cName = result.getString("contactName");
                Contact c = new Contact(cName, getDisplayName(cName));
                cList.addContact(c);
            }
            result.close();
            statement.close();
            return cList;
        }
        catch (Exception e) { return new ContactList(); }
	}
}