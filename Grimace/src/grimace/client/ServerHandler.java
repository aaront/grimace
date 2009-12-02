/**
 * ServerHandler.java
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

package grimace.client;

import java.net.*;
import java.io.*;
import grimace.server.Command;

/**
 * ServerHandler facilitates communication with the Wernicke server.
 * 
 * @author Vineet Sharma
 */
public final class ServerHandler {
    private static final String SERVER_HOSTNAME = "host_ip";
	private static final int SERVER_PORT = 1234;
	private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    /**
     * Connects to the server on the SERVER_PORT.
     *
     * @throws java.net.UnknownHostException
     * @throws java.io.IOException
     */
	private static void connect() throws UnknownHostException, IOException {
		socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
	}

    /**
     * Sends a command to the server and returns the server's response.
     *
     * @param cmd   The command to send.
     * @return  The response from the server.
     * @throws java.lang.Exception
     */
	private static Command sendCommand(Command cmd) throws Exception {
        if (!socket.isConnected()) { throw new Exception("Not connected"); }
        out.writeObject(cmd);
        return (Command) in.readObject();
	}

    /**
     * Sends a request to register a new user.
     *
     * @param userName  The userName to register with.
     * @param passWord  The password to register with.
     * @throws java.lang.Exception
     */
    public static Command sendRegisterRequest(String userName, String passWord) throws Exception {
        connect();
        Command response = null;
        response = sendCommand(new Command("register", userName, passWord));
        socket.close();
        return response;
    }

    /**
     * Sends a request to login a user.
     *
     * @param userName The name of the user to login as.
     * @param passWord  The password for the user's account.
     * @return  True if the login was successful, false otherwise.
     * @throws java.lang.Exception
     */
	public static boolean sendLoginRequest(String userName, String passWord) throws Exception {
        connect();
        Command response = null;
        response = sendCommand(new Command("login", userName, passWord));
        if (!response.getCommandName().equals("loginSuccess")) {
            socket.close();
            return false;
        }
        else {
            Account acc = (Account)in.readObject();
            ProgramController.setAccount(acc);
        }
        return true;
	}

    /**
     * Requests an Account from the server. This can only be done if the user
     * is logged in.
     *
     * @param userName The userName for the Account to retreive.
     * @return  The Account corresponding to the username, or null if it does
     *          not exist.
     */
    public static Account getAccount(String userName) {
        Command response = null;
        try {
            response = sendCommand(new Command("getAccount", userName));
        }
        catch (Exception e) {
            return null;
        }
        if (!response.getCommandName().equals("readUser")) {

        }
        return null;
    }

    /**
     * Sends a request to the server to add a contact to an account.
     *
     * @param userName  The username of the account.
     * @param contactName The name of the contact being requested.
     * @throws java.lang.Exception
     */
	public static void sendAddContactRequest(String userName, String contactName) throws Exception {
        sendCommand(new Command("contactRequest", userName, contactName));
	}

    /**
     * Sends a request to the server to delete a contact from an account.
     *
     * @param userName The userName of the account.
     * @param contactName   The name of the contact to delete.
     * @throws java.lang.Exception
     */
	public static void sendDeleteContactRequest(String userName, String contactName) throws Exception {
        sendCommand(new Command("delContact", userName, contactName));
	}

    /**
     * Sends a request to start a conversation with one or more contacts.
     *
     * @param userName  The user initiating the conversation.
     * @param contactNames  The names of the contacts to request.
     * @throws java.lang.Exception
     */
	public static void sendConversationRequest(String userName, String[] contactNames) throws Exception {
        //sendCommand(new Command("startConversation", userName, ));
	}

    /**
     * Sends a request to the server to post a message to a conversation.
     *
     * @param userName The name of the user sending the message.
     * @param message   The message being sent.
     * @param conId     An integer identifying a target conversation.
     * @throws java.lang.Exception
     */
	public static void sendMessagePostRequest(String userName, String message, int conId) throws Exception {
        sendCommand(new Command("sendMessage", userName, message, String.valueOf(conId)));
	}

    /**
     * Sends a request to the server to transfer a file to one or more contacts.
     *
     * @param username The name of the user initiating the transfer.
     * @param filename The name of the file to be sent.
     * @param contactNames The names of contacts receiving the file.
     * @throws java.lang.Exception
     */
	public static void sendFileTransferRequest(String username, String filename, String[] contactNames) throws Exception {
        //sendCommand(new Command("fileTransferRequest", userName, filename, ));
	}

    /**
     * Sends a notification to the server that a contact has left a
     * conversation.
     *
     * @param userName  The name of the user leaving the conversation.
     * @param conId An integer identifying the conversation.
     * @throws java.lang.Exception
     */
    public static void sendQuitConversationNotification(String userName, int conId) throws Exception {
        sendCommand(new Command("quitConversation", userName, String.valueOf(conId)));
    }

    /**
     * Sends a response to a file transfer request given by the server.
     *
     * @param userName  The name of the user initiating the request.
     * @param contactName The name of the user responding.
     * @param response  Whether or not the request is accepted.
     * @throws java.lang.Exception
     */
    public static void sendFileTransferResponse(String userName, String contactName, boolean response) throws Exception {
        sendCommand(new Command("fileTransferResponse", userName, contactName, String.valueOf(response)));
    }

    /**
     * Sends a response to a contact request given by the server.
     *
     * @param userName The name of the user initiating the request.
     * @param contactName The name of the user responding.
     * @param response Whether or not the request is accepted.
     * @throws java.lang.Exception
     */
    public static void sendContactRequestResponse(String userName, String contactName, boolean response) throws Exception {
        sendCommand(new Command("contactRequestResponse", userName, contactName, String.valueOf(response)));
    }

    /**
     * Sends a request to the server to update an account.
     *
     * @param account The account to update.
     * @throws java.lang.Exception
     */
    public static void sendAccountUpdateRequest(Account account) throws Exception {
        //sendCommand(new Command("updateAccount", userName, ));
    }
}
