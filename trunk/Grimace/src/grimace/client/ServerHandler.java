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
