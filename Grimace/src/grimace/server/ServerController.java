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
import java.util.Hashtable;
import java.util.ArrayList;
import java.sql.SQLException;
import grimace.client.Account;
import grimace.client.Contact;
import grimace.client.ContactList;

/**
 * ServerController handles most of the server functionality of the program.
 *
 * @author Vineet Sharma
 */
public class ServerController {
    private static final int LISTENING_PORT = 1234;
    private static Hashtable<String,ClientHandler> connections;
    private static Hashtable<Integer,ServerConversation> conversations;
    private static int conversationCount;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static boolean run = false;

    /**
     * Runs the Wernicke server.
     *
     * @param args  Command line arguments.
     */
    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
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
        try {
            DataHandler.createAccount("vineet", grimace.client.ServerHandler.getPasswordHash("1234"), "ServerNerd");
            DataHandler.createAccount("justin", grimace.client.ServerHandler.getPasswordHash("1234"), "Justin");
            DataHandler.createAccount("aaront", grimace.client.ServerHandler.getPasswordHash("1234"), "Aaron T");
            DataHandler.createAccount("aaronj", grimace.client.ServerHandler.getPasswordHash("1234"), "Aaron J");
            DataHandler.createAccount("david", grimace.client.ServerHandler.getPasswordHash("1234"), "David");
            DataHandler.createAccount("wernicke", grimace.client.ServerHandler.getPasswordHash("1234"), "The Great Wernicke");
            DataHandler.addContact("vineet", "wernicke");
            DataHandler.addContact("vineet", "justin");
            DataHandler.addContact("vineet", "aaront");
            DataHandler.addContact("vineet", "aaronj");
            DataHandler.addContact("vineet", "david");
            DataHandler.addContact("justin", "wernicke");
            DataHandler.addContact("justin", "vineet");
            DataHandler.addContact("justin", "aaront");
            DataHandler.addContact("justin", "aaronj");
            DataHandler.addContact("justin", "david");
            DataHandler.addContact("aaront", "wernicke");
            DataHandler.addContact("aaront", "justin");
            DataHandler.addContact("aaront", "vineet");
            DataHandler.addContact("aaront", "aaronj");
            DataHandler.addContact("aaront", "david");
            DataHandler.addContact("aaronj", "wernicke");
            DataHandler.addContact("aaronj", "justin");
            DataHandler.addContact("aaronj", "aaront");
            DataHandler.addContact("aaronj", "vineet");
            DataHandler.addContact("aaronj", "david");
            DataHandler.addContact("david", "wernicke");
            DataHandler.addContact("david", "justin");
            DataHandler.addContact("david", "aaront");
            DataHandler.addContact("david", "aaronj");
            DataHandler.addContact("david", "vineet");
        }
        catch (Exception e) { e.printStackTrace(); }
        DataHandler.printAccounts();
        conversationCount = 0;
        conversations = new Hashtable<Integer,ServerConversation>();
        connections = new Hashtable<String,ClientHandler>();
        run = true;
        listen();
	}

    /**
     * Initializes the ServerSocket and binds it to the LISTENING_PORT.
     */
	public static void initDataHandler() {
        try {
            DataHandler.connect();
            DataHandler.initDatabase();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Database driver not found.");
            System.out.println("Server will exit.");
            System.exit(1);
        }
        catch (SQLException ex) {
            System.out.println("Unable to connect to database.");
            System.out.println("Server will exit.");
            ex.printStackTrace();
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

    private static void registerConnection(String userName, ClientHandler handler) {
        connections.put(userName, handler);
    }
    
    public static void closeConnection(String userName) {
        System.out.println("Closing connection session for user \'" + userName + "\':");
        getClientHandler(userName).closeSocket();
        System.out.println("Removing connection handler for user \'" + userName + "\'");
        connections.remove(userName);
    }

    /**
     * Waits for a command from a client and passes it on to be decoded.
     */
	public static void listen() {
        while (run) {
            try {
                System.out.println("Waiting for next connection...");
                socket = serverSocket.accept();
                ObjectInputStream in = new ObjectInputStream(
                                            socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(
                                            socket.getOutputStream());
                System.out.println("Received connection from " + socket.getInetAddress().toString());
                Command cmd = (Command)in.readObject();
                System.out.println("Received command: " + cmd.getCommandName());
                Command resp = processConnectionCommand(cmd);
                out.writeObject(resp);
                if (!cmd.getCommandName().equals(Command.LOGIN)) {
                    System.out.println("Done processing command: " + cmd.getCommandName());
                    System.out.println("Closing connection to " + socket.getInetAddress().toString());
                    socket.close();
                }
                else {
                    if (!resp.getCommandName().equals(Command.LOGIN_SUCCESS)) {
                        System.out.println("Done processing command: " + cmd.getCommandName());
                        System.out.println("Closing connection to " + socket.getInetAddress().toString());
                        socket.close();
                    }
                    else {
                        String userName = cmd.getCommandArg(0);
                        System.out.print("Sending Account data for user \'" + userName + "\': ");
                        out.writeObject(DataHandler.loadAccount(userName));
                        System.out.println("done");
                        System.out.print("Setting up handler for connection session with user \'" + userName + "\': ");
                        registerConnection(userName, new ClientHandler(socket, userName, in, out));
                        System.out.println("done");
                        System.out.print("Presenting contact requests for user \'" + userName + "\': ");
                        presentContactRequests(userName);
                        System.out.println("done");
                        System.out.println("Done processing command: " + cmd.getCommandName());
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Error: Failed to accept socket.");
                e.printStackTrace();
            }
        }
	}


    /**
     * Determines what action to perform for a given command.
     *
     * @param cmd   The command to decode.
     */
	public static Command processConnectionCommand(Command cmd) {
        Command resp = null;
        if (cmd.getCommandName().equals(Command.LOGIN)) {
            System.out.println("Processing login request with username: " + cmd.getCommandArg(0));
            if (cmd.getArgumentNumber() < 2) {
                resp = new Command(Command.LOGIN_FAILURE);
                System.out.println("Login failed for username: " + cmd.getCommandArg(0) + ", too few arguments");
            }
            else {
                String userName = cmd.getCommandArg(0);
                String passHash = cmd.getCommandArg(1);
                System.out.print("Verifying login for username " + userName + ": ");
                if (verifyLoginRequest(userName, passHash)) {
                    resp = new Command(Command.LOGIN_SUCCESS);
                    System.out.println("valid");
                    System.out.println("Response: " + Command.LOGIN_SUCCESS);
                }
                else {
                    resp = new Command(Command.LOGIN_FAILURE);
                    System.out.println("invalid");
                    System.out.println("Response: " + Command.LOGIN_FAILURE);
                }
            }
        }
        else if (cmd.getCommandName().equals(Command.REGISTER)) {
            System.out.println("Processing registration request with username: " + cmd.getCommandArg(0));
            if (cmd.getArgumentNumber() < 3) {
                resp = new Command(Command.REGISTER_FAILURE);
                System.out.println("Login failed for username: " + cmd.getCommandArg(0) + ", too few arguments");
            }
            else {
                String userName = cmd.getCommandArg(0);
                String passHash = cmd.getCommandArg(1);
                String display = cmd.getCommandArg(2);
                System.out.print("Creating account with username " + userName + ": ");
                try {
                    if (DataHandler.createAccount(userName, passHash,
                                                            display)) {
                        resp = new Command(Command.REGISTER_SUCCESS);
                        System.out.println("done");
                        System.out.println("Response: " + Command.REGISTER_SUCCESS);
                    }
                    else {
                        resp = new Command(Command.REGISTER_FAILURE,
                                            Command.USERNAME_EXISTS);
                        System.out.println("failed, username exists");
                        System.out.println("Response: " + Command.REGISTER_FAILURE
                                            + ", " + Command.USERNAME_EXISTS);
                    }
                }
                catch (Exception e) {
                    resp = new Command(Command.REGISTER_FAILURE,
                                        Command.ACCOUNT_CREATION_ERROR);
                    System.out.println("failed, error:\n");
                    e.printStackTrace();
                    System.out.println("Response: " + Command.REGISTER_FAILURE
                                            + ", " + Command.ACCOUNT_CREATION_ERROR);
                }
            }
        }
        else {
            System.out.println("Rejected invlid command: " + cmd.getCommandName());
            resp = new Command(Command.INVALID_COMMAND);
            System.out.println("Response: " + Command.INVALID_COMMAND);
        }

        return resp;
	}

    /**
     * Returns whether or not a user with the given userName is logged in.
     * 
     * @param userName The name of the user to check.
     * @return  True if the user is logged in, false otherwise.
     */
	public static boolean checkAccountLoginStatus(String userName) {
        return connections.containsKey(new String(userName));
	}

    /**
     * Checks whether a given userName/password combination are correct.
     *
     * @param userName  The name of the user to verify.
     * @param passHash  The password hash to verify.
     */
	public static boolean verifyLoginRequest(String userName, String passHash) {
        try {
            String realHash = DataHandler.getAccountPasswordHash(userName);
            return realHash.equals(passHash);
        }
        catch (Exception e) {
            return false;
        }
	}

    private static ClientHandler getClientHandler(String userName) {
        return connections.get(userName);
    }

    public static ServerConversation getServerConversation(int conId) {
        return conversations.get(new Integer(conId));
    }

    /**
     * Places a Command on a Command queue to send to a client
     * @param cmd   The command to send.
     */
    public static boolean sendCommand(Command cmd, String userName) {
        if (!checkAccountLoginStatus(userName)) {
            return false;
        }
        getClientHandler(userName).placeCommand(cmd);
        return true;
    }

    /**
     * Places a Command on a Command queue to send to a client
     * @param cmd   The command to send.
     */
    public static boolean sendDisplayNotification(String message, String userName) {
        if (!checkAccountLoginStatus(userName)) {
            return false;
        }
        getClientHandler(userName).placeCommand(new Command(Command.DISPLAY_NOTIFICATION, message));
        return true;
    }

    public static void contactUpdateNotification(String userName, int[] ids) {
        ContactList cList = DataHandler.loadContactList(userName);
        if (cList.getSize() > 0) {
            for (Contact c : cList.getList()) {
                sendCommand(new Command(Command.UPDATE_CONTACT, userName), c.getUserName());
            }
        }
        for (int i : ids) {
            ServerConversation sc = null;
            try { sc = getServerConversation(i); }
            catch (Exception e) { e.printStackTrace(); }
            if (sc == null) { return; }
            String[] users = sc.getUsers();
            for (String s : users) {
                if (!s.equals(userName)) {
                    sendCommand(new Command(Command.UPDATE_CONTACT, userName), s);
                }
            }
        }
    }

    /**
     * Places a request for a contact to be added.
     *
     * @param userName  The name of the user requesting the addition.
     * @param contactName   The name of the contact being requested.
     */
	public static void placeContactRequest(String userName,
                                            String contactName) {
        if (!DataHandler.accountExists(contactName)) {
            sendDisplayNotification("The requested account does not exist.", userName);
            return;
        }
        try {
            DataHandler.placeContactRequest(userName, contactName);
            DataHandler.printContactRequests();
        }
        catch (Exception e) {
            sendDisplayNotification(
                    "An error occurred while placing your contact request for contact: " + contactName,
                    userName);
            return;
        }
        sendCommand(new Command(Command.CONTACT_REQUEST, userName), contactName);
	}

    /**
     * Completes the addition of a contact if confirmed by the contact.
     * 
     * @param userName  The name of the user who requested the addition.
     * @param contactName The name of the contact being requested.
     * @param confirm   Whether or not to complete the addition.
     */
	public static void confirmContactRequest(String userName,
                                                String contactName,
                                                boolean confirm) {
        if (!DataHandler.accountExists(userName)) {
            return;
        }
        if (confirm) {
            try {
                DataHandler.addContact(userName, contactName);
                DataHandler.addContact(contactName, userName);
                sendCommand(new Command(Command.UPDATE_CONTACT_LIST), userName);
                sendCommand(new Command(Command.UPDATE_CONTACT_LIST), contactName);
                DataHandler.printContacts();
                DataHandler.clearContactRequest(userName, contactName);
                DataHandler.printContactRequests();
            }
            catch (Exception e) {
                sendDisplayNotification(
                        "Your request to add " + contactName
                        + " to your contact list was accepted,\n"
                        + "but an error occured while updating your contact list.",
                        userName);
            }
        }
        else {
            try {
                DataHandler.clearContactRequest(userName, contactName);
                DataHandler.printContactRequests();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

    public static void presentContactRequests(String userName) {
        if (!DataHandler.accountExists(userName)) {
            return;
        }
        Object[] rList = DataHandler.getContactRequestCommands(userName);
        for (Object cmd : rList) {
            sendCommand((Command)cmd, userName);
        }
    }

    /**
     * Deletes a contact from the Account with the given userName.
     *
     * @param userName The Account from which to delete the contact.
     * @param contactName The name of the contact to delete.
     */
    public static void deleteContact(String userName, String contactName) {
        try {
            DataHandler.deleteContact(userName, contactName);
            sendCommand(new Command(Command.UPDATE_CONTACT_LIST), userName);
        }
        catch (Exception e) {
            sendDisplayNotification(
                "An error occurred while deleting contact: " + contactName, userName);
        }
    }

    /**
     * Creates a conversation and adds all requested users who are online.
     *
     * @param userNames The users requested for the conversation.
     */
    public static void createConversation(String[] userNames) {
        if (userNames.length < 2) { return; }
        ArrayList<String> online = new ArrayList<String>();
        for (String s : userNames) {
            if (checkAccountLoginStatus(s)) {
                online.add(s);
            }
        }
        if (online.size() == 0) {
            return;
        }
        if (online.size() < 2) {
            sendDisplayNotification(
                        "Unable to start a conversation: not enough users online.", userNames[0]);
            return;
        }
        ServerConversation convo = new ServerConversation(conversationCount++, online);
        conversations.put(new Integer(convo.getConId()), convo);
        for (String s : online) {
            sendCommand(new Command(Command.START_CONVERSATION,
                    String.valueOf(convo.getConId()),
                    userNames[0]), s);
        }
    }

    /**
     * Sends a message to all users in the conversation with the given id.
     *
     * @param conId An integer identifying a target conversation.
     * @param message The message to send.
     */
    public static void sendMessage(int conId, String message, String userName) {
        ServerConversation serverConvo;
        try {
            serverConvo = getServerConversation(conId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (serverConvo == null) {
            sendDisplayNotification("The conversation you are trying to communicate in is closed.\n"
                                    + "Why isn\'t yours?", userName);
            return;
        }
        String[] users = serverConvo.getUsers();
        for (String s : users) {
            System.out.println("Sending message from " + userName + " to " + s);
            sendCommand(new Command(Command.SEND_MESSAGE, userName, message, String.valueOf(conId)), s);
        }
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
        ServerConversation serverConvo;
        try {
            serverConvo = getServerConversation(conId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (serverConvo == null) {
            sendDisplayNotification("The conversation you are trying to communicate in is closed.\n"
                                    + "Why isn\'t yours?", userName);
            return;
        }
        serverConvo.removeUser(userName);
        if (serverConvo.getSize() > 0) {
            String[] users = serverConvo.getUsers();
            for (String s : users) {
                sendCommand(new Command(Command.REMOVE_FROM_CONVERSATION,
                            userName, String.valueOf(conId)), s);
            }
        }
        else {
            closeConversation(conId);
        }
    }

    /**
     * Adds a user to an existing converation.
     *
     * @param userName  The name of the user to add.
     * @param conId     The id of the conversation to add the user to.
     */
    public static void addToConversation(String userName, int conId, String sender) {
        ServerConversation serverConvo;
        try {
            serverConvo = getServerConversation(conId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (serverConvo == null) {
            sendDisplayNotification("The conversation you are trying to communicate in is closed.\n"
                                    + "Why isn\'t yours?", userName);
            return;
        }
        if (!checkAccountLoginStatus(userName)) {
            sendDisplayNotification("The user " + userName + " is not online.", sender);
            return;
        }
        serverConvo.addUser(userName);
        if (serverConvo.getSize() > 0) {
            String[] users = serverConvo.getUsers();
            for (String s : users) {
                sendCommand(new Command(Command.ADD_TO_CONVERSATION,
                            userName, String.valueOf(conId)), s);
            }
        }
        else {
            closeConversation(conId);
        }
    }

    /**
     * Updates the display name for a user.
     *
     * @param userName      The user whose display name to update
     * @param displayName   The new display name
     */
    public static void updateDisplayName(String userName, String displayName, int[] ids) {
        if (!DataHandler.updateDisplayName(userName, displayName)) {
            sendDisplayNotification("An error occurred while updating your display name.", userName);
            return;
        }
        contactUpdateNotification(userName, ids);
    }

    /**
     * Updates the display name for a user.
     *
     * @param userName      The user whose display name to update
     * @param displayName   The new display name
     */
    public static void updateStatus(String userName, String status, int[] ids) {
        if (!DataHandler.updateDisplayStatus(userName, status)) {
            sendDisplayNotification("An error occurred while updating your status.", userName);
            return;
        }
        contactUpdateNotification(userName, ids);
    }

    /**
     * Updates the display name for a user.
     *
     * @param userName      The user whose display name to update
     * @param displayName   The new display name
     */
    public static void updateFont(String userName,
                                String fontName,
                                String fontSize,
                                String fontColour,
                                boolean fontBold,
                                boolean fontItalic) {
        if (!DataHandler.updateFont(userName, fontName, fontSize,
                                    fontColour, fontBold, fontItalic)) {
            sendDisplayNotification("An error occurred while"
                                    + "updating your font.", userName);
        }
    }

    /**
     * Closes a conversation.
     *
     * @param conId An integer identifying the conversation to close.
     */
    public static void closeConversation(int conId) {
        conversations.remove(new Integer(conId));
    }
}