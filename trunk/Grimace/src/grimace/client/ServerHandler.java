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
import java.util.ArrayList;
import grimace.server.Command;
import java.security.MessageDigest;

/**
 * ServerHandler facilitates communication with the Wernicke server.
 * 
 * @author Vineet Sharma
 */
public final class ServerHandler {
    private static final String SERVER_HOSTNAME = "localhost";
	private static final int SERVER_PORT = 1234;
	private static Thread listen;
    private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Command fromServer;
    private static boolean run = false;

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
     * Sends a command to the server.
     *
     * @param cmd   The command to send.
     * @throws java.lang.Exception
     */
	private static void sendCommand(Command cmd) throws Exception {
        if (!socket.isConnected()) { throw new Exception("Not connected"); }
        out.writeObject(cmd);
	}

    /**
     * Creates and sends a command to the server.
     *
     * @param cmdName   The name of the command to send.
     * @param args      The arguments for the command.
     * @throws java.lang.Exception
     */
	private static void sendCommand(String cmdName, String... args) throws Exception {
        if (!socket.isConnected()) { throw new Exception("Not connected"); }
        try {
            out.writeObject(new Command(cmdName, args));
        }
        catch (IOException e) {
            e.printStackTrace();
            ProgramController.showMessage("The connection to the server was closed. You will be logged out.");
            logout();
        }
	}

    /**
     * Merges a set of arguments and appends an array of arguments
     * to the resulting array.
     *
     * @param append    Arguments to append to the merged array.
     * @param args      Arguments to merge.
     * @return          A String array containing all arguments.
     */
    public static String[] mergeStrings(String[] append, String... args) {
        String[] merged = new String[append.length + args.length];
        for (int i=0; i<args.length; i++) {
            merged[i] = args[i];
        }
        for (int i=args.length; i<args.length+append.length; i++) {
            merged[i] = append[i-args.length];
        }
        return merged;
    }

    public static String[] getStringArrayFromList(ArrayList<String> strings) {
        Object[] oArr = strings.toArray();
        String[] sArr = new String[oArr.length];
        for (int i=0; i<oArr.length; i++) {
            sArr[i] = (String)oArr[i];
        }
        return sArr;
    }

    /**
     * Returns a hash of the given password string using the SHA-1 algorithm.
     *
     * @param password  The string to hash.
     * @return  A string of hex digits representing the hashed value.
     */
    public static synchronized String getPasswordHash(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch (Exception e) {
            return "";
        }

        StringBuffer hash;
        try {
            byte[] hashBytes = md.digest(password.getBytes("UTF-8"));
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
     * Sends a request to register a new user.
     *
     * @param userName  The userName to register with.
     * @param passWord  The password to register with.
     * @throws java.lang.Exception
     */
    public static Command sendRegisterRequest(String userName, String password,
                                                String displayName) {
        try {
            connect();
            sendCommand(Command.REGISTER,
                        userName.toLowerCase(),
                        getPasswordHash(password),
                        displayName);
            Command response = (Command)in.readObject();
            out.close();
            in.close();
            socket.close();
            return response;
        }
        catch (Exception e) {
            return new Command(Command.REGISTER_FAILURE, Command.SERVER_CONNECT_FAILURE);
        }
    }

    /**
     * Sends a request to login a user.
     *
     * @param userName The name of the user to login as.
     * @param passWord  The password for the user's account.
     * @return  True if the login was successful, false otherwise.
     * @throws java.lang.Exception
     */
	public static boolean sendLoginRequest(String userName, String password) {
        try {
            connect();
            sendCommand(Command.LOGIN,
                    userName.toLowerCase(),
                    getPasswordHash(password));
            Command response = (Command)in.readObject();
            if (!response.getCommandName().equals(Command.LOGIN_SUCCESS)) {
                out.close();
                in.close();
                socket.close();
                return false;
            }
            else {
                Account acc = (Account)in.readObject();
                ProgramController.setAccount(acc);
                listen = new Thread(new Runnable() {
                                        public void run() {
                                            listen();
                                        }
                                    });
                run = true;
                listen.start();
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
	}

    /**
     * Waits for commands from the server and executes them.
     */
    private static void listen() {
        while (run) {
            try {
                Thread.sleep(100);
                fromServer = (Command)in.readObject();
                if (fromServer.getCommandName().equals(Command.DISPLAY_NOTIFICATION)) {
                    ProgramController.showMessage(fromServer.getCommandArg(0));
                }
                if (fromServer.getCommandName().equals(Command.CONTACT_REQUEST)) {
                    int resp = ProgramController.showRequestDialog("You have a contact request from "
                            + fromServer.getCommandArg(0) + ". What would you like to do?");
                    switch (resp) {
                        case 0:
                            sendContactRequestResponse(fromServer.getCommandArg(0),
                                    ProgramController.getAccount().getUserName(),
                                    Command.ACCEPT);
                            /*sendCommand(Command.CONTACT_REQUEST_RESPONSE, fromServer.getCommandArg(0),
                                    ProgramController.getAccount().getUserName(), Command.ACCEPT);*/
                            break;
                        case 1:
                            sendContactRequestResponse(fromServer.getCommandArg(0),
                                    ProgramController.getAccount().getUserName(),
                                    Command.REJECT);
                            /*sendCommand(Command.CONTACT_REQUEST_RESPONSE, fromServer.getCommandArg(0),
                                    ProgramController.getAccount().getUserName(), Command.REJECT);*/
                            break;
                        case 2:
                            ProgramController.showMessage("Your contact requests will be displayed on your next login.");
                            break;
                    }
                }
                if (fromServer.getCommandName().equals(Command.UPDATE_CONTACT_LIST)) {
                    ContactList cList = (ContactList)in.readObject();
                    ProgramController.getAccount().setContactList(cList);
                    ProgramController.getContactListBox().updateContactListView();
                }
                if (fromServer.getCommandName().equals(Command.START_CONVERSATION)) {
                    int conId = Integer.valueOf(fromServer.getCommandArg(0)).intValue();
                    ContactList cList = (ContactList)in.readObject();
                    ProgramController.openNewConvo(conId, cList);
                }
                if (fromServer.getCommandName().equals(Command.SEND_MESSAGE)) {
                    int conId = Integer.valueOf(fromServer.getCommandArg(2)).intValue();
                    String message = fromServer.getCommandArg(1);
                    String sender = fromServer.getCommandArg(1);
                    
                }
            }
            catch (EOFException e) {}
            catch (SocketException e) {}
            catch (Exception e) { e.printStackTrace(); }
        }
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
        return null;
    }

    /**
     * Sends a request to the server to add a contact to an account.
     *
     * @param userName  The username of the account.
     * @param contactName The name of the contact being requested.
     * @throws java.lang.Exception
     */
	public static void sendAddContactRequest(String contactName) throws Exception {
        if (contactName.equals(ProgramController.getAccount().getUserName())) {
            ProgramController.showMessage("You can't add yourself as a contact. That's cheating!");
            return;
        }
        sendCommand(Command.CONTACT_REQUEST,
                ProgramController.getAccount().getUserName(),
                contactName.toLowerCase());
	}

    /**
     * Sends a response to a contact request given by the server.
     *
     * @param userName The name of the user initiating the request.
     * @param contactName The name of the user responding.
     * @param response Whether or not the request is accepted.
     * @throws java.lang.Exception
     */
    public static void sendContactRequestResponse(String userName,
                                                    String contactName,
                                                    String response)
                                                    throws Exception {
        sendCommand(Command.CONTACT_REQUEST_RESPONSE,
                    userName, contactName, response);
    }

    /**
     * Sends a request to the server to delete a contact from an account.
     *
     * @param userName The userName of the account.
     * @param contactName   The name of the contact to delete.
     * @throws java.lang.Exception
     */
	public static void sendDeleteContactRequest(String userName,
                                                String contactName)
                                                throws Exception {
        sendCommand(Command.DELETE_CONTACT, userName, contactName);
	}

    /**
     * Sends a request to start a conversation with one or more contacts.
     *
     * @param userName      The user initiating the conversation.
     * @param contactNames  The names of the contacts requested.
     * @throws java.lang.Exception
     */
	public static void sendConversationRequest(String userName,
                                                Contact[] contacts)
                                                throws Exception {
        String[] names = new String[contacts.length];
        for (int i=0; i<contacts.length; i++) {
            names[i] = contacts[i].getUserName();
        }
        String[] args = mergeStrings(names, userName);
        sendCommand(Command.START_CONVERSATION, args);
	}

    /**
     * Sends a request to the server to post a message to a conversation.
     *
     * @param userName The name of the user sending the message.
     * @param message   The message being sent.
     * @param conId     An integer identifying a target conversation.
     * @throws java.lang.Exception
     */
	public static void sendMessagePostRequest(String message,
                                                int conId) throws Exception {
        sendCommand(Command.SEND_MESSAGE,
                ProgramController.getAccount().getUserName(), message, String.valueOf(conId));
	}

    /**
     * Sends a notification to the server that a contact has left a
     * conversation.
     *
     * @param userName  The name of the user leaving the conversation.
     * @param conId An integer identifying the conversation.
     * @throws java.lang.Exception
     */
    public static void sendQuitConversationNotification(int conId)
                                                        throws Exception {
        sendCommand(Command.QUIT_CONVERSATION,
                ProgramController.getAccount().getUserName(), String.valueOf(conId));
    }

    /**
     * Sends a request to the server to transfer a file to one or more contacts.
     *
     * @param username The name of the user initiating the transfer.
     * @param filename The name of the file to be sent.
     * @param contactNames The names of contacts receiving the file.
     * @throws java.lang.Exception
     */
	public static void sendFileTransferRequest(String fileName,
                                                String... contactNames)
                                                throws Exception {
        String[] args = mergeStrings(contactNames,
                ProgramController.getAccount().getUserName(), fileName);
        sendCommand(Command.FILE_TRANSFER_REQUEST, args);
	}

    /**
     * Sends a response to a file transfer request given by the server.
     *
     * @param userName  The name of the user initiating the request.
     * @param contactName The name of the user responding.
     * @param response  Whether or not the request is accepted.
     * @throws java.lang.Exception
     */
    public static void sendFileTransferResponse(String userName,
                                                String contactName,
                                                boolean response)
                                                throws Exception {
        sendCommand(Command.FILE_TRANSFER_RESPONSE,
                    userName,
                    contactName,
                    String.valueOf(response));
    }

    /**
     * Sends a request to the server to update an account.
     *
     * @param account The account to update.
     * @throws java.lang.Exception
     */
    public static void sendAccountUpdateRequest(Account account)
                                                throws Exception {
        sendCommand(Command.UPDATE_ACCOUNT, account.getUserName());
        out.writeObject(account);
    }

    public static void sendLogoutRequest() {
        try {
            sendCommand(Command.LOGOUT);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        logout();
    }

    public static void logout() {
        try {
            run = false;
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ProgramController.showLoginForm();
    }
}
