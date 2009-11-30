/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.client;

import java.net.*;
import java.io.*;
import grimace.server.Command;

/**
 *
 * @author vs
 */
public class ServerHandler {
    private static final String SERVER_HOSTNAME = "host_ip";
	private static final int SERVER_PORT = 1234;
	private static Socket socket;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

	public static void connect() throws UnknownHostException, IOException {
		socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
	}

	private static void sendCommand(Command cmd) {

	}
    
	public static void sendLoginRequest(String userName, String passWord) {

	}

	public static void sendAddContactRequest(String userName, String contactName) {

	}

	public static void sendDeleteContactRequest(String userName, String contactName) {

	}

	public static void sendConversationRequest(String userName, String[] contactNames) {

	}

	public static void sendMessagePostRequest(String userName, String message, String[] contactNames) {

	}

	public static void sendFileTransferRequest(String username, String filename) {

	}
}
