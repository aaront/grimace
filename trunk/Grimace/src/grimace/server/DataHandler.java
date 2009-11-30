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
import java.security.MessageDigest;
import grimace.client.Account;
import grimace.client.ContactList;

/**
 *
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
                                "contactName varchar(30)",
                                "contactNick varchar(100)");
    }

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

    public static void createAccount(String userName,
                                    String passWord) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO Accounts (userName, passWord) VALUES(\'"
                       + userName + "\',\'"
                       + getPasswordHash(passWord) +"\')";
        statement.execute(sql);
        statement.close();
    }

    private static String getPasswordHash(String passWord) {
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

	public static void saveAccount(Account acc) {
        
	}

	public static Account loadAccount(String username) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM Accounts WHERE userName=\'" + username +"\'";
        ResultSet result = statement.executeQuery(sql);
        return null;
	}

	public static void deleteAccount(String username) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM Accounts WHERE userName="
                        + username + " LIMIT 1";
        statement.executeUpdate(sql);
	}

	public static void saveContactList(ContactList listt) {
        
	}

	public static ContactList loadContactList(String username) {
        return null;
	}
}
