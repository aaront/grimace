/**
 * ServerController.java
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

import java.net.*;
import java.io.*;
import java.sql.SQLException;

/**
 *
 * @author Vineet Sharma
 */
public class ServerController {
    private static final int LISTENING_PORT = 1234;
	private static ServerSocket[] connections;
    private static Socket socket;

	public static void setupServer() {
        try {
            DataHandler.connect();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Database driver not found.");
            System.exit(1);
        }
        catch (SQLException ex) {
            System.out.println("Unable to connect to database.");
            System.exit(1);
        }
	}

	public static void listen() {

	}

	public static void initServerSocket() {

	}

	public static void setupConnection() {

	}

	public static void decodeCommand(Command cmd) {

	}

	public static void checkAccountLoginStatus(String username) {

	}

	public static void verifyLoginRequest(String username, String password) {

	}

	public static void placeContactRequest(String username, String contactname) {

	}

	public static void confirmContactRequest(String username, String contactName) {

	}
}
