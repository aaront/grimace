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
 *
 * @author Vineet Sharma
 */
public final class ServerHandler {
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

	private static Command sendCommand(Command cmd) throws Exception {
        out.writeObject(cmd);
        return (Command) in.readObject();
	}
    
	public static void sendLoginRequest(String userName, String passWord) throws Exception {
        sendCommand(new Command("login", userName, passWord));
	}

	public static void sendAddContactRequest(String userName, String contactName) throws Exception {
        sendCommand(new Command("contactRequest", userName, contactName));
	}

	public static void sendDeleteContactRequest(String userName, String contactName) throws Exception {
        sendCommand(new Command("delContact", userName, contactName));
	}

	public static void sendConversationRequest(String userName, String[] contactNames) throws Exception {
        //sendCommand(new Command("startConversation", userName, ));
	}

	public static void sendMessagePostRequest(String userName, String message, int conId) throws Exception {
        sendCommand(new Command("sendMessage", userName, message, String.valueOf(conId)));
	}

	public static void sendFileTransferRequest(String username, String filename, String[] contactNames) throws Exception {
        //sendCommand(new Command("fileTransferRequest", userName, filename, ));
	}

    public static void sendQuitConversationNotification(String userName, int conId) throws Exception {
        sendCommand(new Command("quitConversation", userName, String.valueOf(conId)));
    }

    public static void sendFileTransferResponse(String userName, String contactName, boolean response) throws Exception {
        sendCommand(new Command("fileTransferResponse", userName, contactName, String.valueOf(response)));
    }

    public static void sendContactRequestResponse(String userName, String contactName, boolean response) throws Exception {
        sendCommand(new Command("contactRequestResponse", userName, contactName, String.valueOf(response)));
    }

    public static void sendAccountUpdateRequest(String userName, Account account) throws Exception {
        //sendCommand(new Command("updateAccount", userName, ));
    }
}
