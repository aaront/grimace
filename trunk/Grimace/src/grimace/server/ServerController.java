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
import java.util.ArrayList;
import java.sql.SQLException;
import grimace.client.Account;

/**
 *
 *
 * @author Vineet Sharma
 */
public class ServerController {
    private static final int LISTENING_PORT = 1234;
	private static ArrayList<ClientHandler> connections;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static boolean run = false;

    /**
     * Runs the Wernicke server.
     *
     * @param args  Command line arguments.
     */
    public static void main(String[] args) {
        ServerController.setupServer();
    }

    /**
     * Connects to the database and begins listening for client connections
     * on a the LISTENING_PORT.
     */
	public static void setupServer() {
        initDataHandler();
        initServerSocket();
        //if we made it this far, thats dandy
        System.out.println("Server initialization successful.");
        connections = new ArrayList<ClientHandler>();
        run = true;
        listen();
	}

    /**
     * Initializes the ServerSocket and binds it to the LISTENING_PORT.
     */
	public static void initDataHandler() {
        try {
            DataHandler.connect();
            //DataHandler.initDatabase();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Database driver not found.");
            System.out.println("Server will exit.");
            System.exit(1);
        }
        catch (SQLException ex) {
            System.out.println("Unable to connect to database.");
            System.out.println("Server will exit.");
            System.exit(1);
        }
	}

    /**
     * Initializes the ServerSocket and binds it to the LISTENING_PORT.
     */
	public static void initServerSocket() {
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
        }
        catch (Exception e) {
            System.out.println("Could not listen on port "
                                + String.valueOf(LISTENING_PORT));
            System.out.println("Server will exit.");
            System.exit(1);
        }
	}

    /**
     * Waits for a command from a client and passes it on to be decoded.
     */
	public static void listen() {
        while (run) {
            try {
                socket = serverSocket.accept();
                connections.add(new ClientHandler(socket));
            }
            catch (Exception ex) {
                System.out.println("Failed to accept socket.");
            }
        }
	}


    /**
     * Determines what action to perform for a given command.
     *
     * @param cmd   The command to decode.
     */
	public static void decodeCommand(Command cmd) {

	}

    /**
     * Returns whether or not a user with the given userName is logged in.
     * 
     * @param username The name of the user to check.
     * @return  True if the user is logged in, false otherwise.
     */
	public static boolean checkAccountLoginStatus(String userName) {
        return false;
	}

    /**
     * Checks whether a given userName/password combination are correct.
     *
     * @param username  The name of the user to verify.
     * @param password  The password to verify.
     */
	public static boolean verifyLoginRequest(String userName, String password) {
        return false;
	}

    /**
     * Places a request for a contact to be added.
     *
     * @param username  The name of the user requesting the addition.
     * @param contactname   The name of the contact being requested.
     */
	public static void placeContactRequest(String username,
                                            String contactname) {

	}

    /**
     * Completes the addition of a contact if confirmed by the contact.
     * 
     * @param username  The name of the user who requested the addition.
     * @param contactName The name of the contact being requested.
     * @param confirm   Whether or not to complete the addition.
     */
	public static void confirmContactRequest(String username,
                                                String contactName,
                                                boolean confirm) {

	}

    /**
     * Deletes a contact from the Account with the given userName.
     *
     * @param userName The Account from which to delete the contact.
     * @param contactName The name of the contact to delete.
     */
    public static void deleteContact(String userName, String contactName) {

    }

    /**
     * Creates a conversation and adds all requested users who are online.
     *
     * @param userNames The users requested for the conversation.
     * @return The integer identifying the conversation.
     */
    public static int createConversation(String userNames) {
        return -1;
    }

    /**
     * Sends a message to all users in the conversation with the given id.
     *
     * @param conId An integer identifying a target conversation.
     * @param message The message to send.
     */
    public static void sendMessage(int conId, String message) {

    }

    /**
     * Places a request for users to accept a file transfer.
     *
     * @param userName  The user initiating the transfer.
     * @param contactNames  The contacts to receive the file.
     * @param file  The name of the file.
     */
    public static void placeFileTransferRequest(String userName,
                                                String[] contactNames,
                                                String file) {
    }

    /**
     * Removes a user from a conversation.
     *
     * @param userName  The user to remove.
     * @param conId An integer identifying the target conversation.
     */
    public static void removeFromConversation(String userName, int conId) {

    }

    /**
     * 
     * @param userNames
     * @param conId
     */
    public static void addToConversation(String[] userNames, int conId) {

    }

    /**
     * Updates the information in an account.
     *
     * @param acc  The Account to update.
     */
    public static void updateAccount(Account acc) {

    }

    /**
     * Closes a conversation.
     *
     * @param conId An integer identifying the conversation to close.
     */
    public static void closeConversation(int conId) {

    }
}