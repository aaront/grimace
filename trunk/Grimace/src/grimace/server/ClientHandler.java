/**
 * ClientHandler.java
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

/**
 * ClientHandler runs threads that manages a connection to a client.
 *
 * @author Vineet Sharma
 */
public class ClientHandler {
    private String name;
    private ArrayList<Command> commandQueue;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Thread listen;
    private Thread send;
    private Command fromClient;
    private Command toClient;
    private boolean run;

    public ClientHandler(Socket socket, String name, ObjectInputStream in, ObjectOutputStream out) {
        this.name = name;
        commandQueue = new ArrayList<Command>();
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.run = true;
        listen = new Thread(new Runnable() {
                            public void run() {
                                listenSocket();
                            }
                        });
        send = new Thread(new Runnable() {
                            public void run() {
                                sendSocket();
                            }
                        });
        listen.start();
        send.start();
    }

    public String getName() {
        return name;
    }

    public void closeSocket() {
        try {
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void placeCommand(Command cmd) {
        commandQueue.add(cmd);
    }
    
    private void listenSocket() {
        while (run) {
            try {
                Thread.sleep(100);
                fromClient = (Command)in.readObject();
                if (fromClient.getCommandName().equals(Command.CONTACT_REQUEST)) {
                    ServerController.placeContactRequest(fromClient.getCommandArg(0),
                                                            fromClient.getCommandArg(1));
                }
                if (fromClient.getCommandName().equals(Command.CONTACT_REQUEST_RESPONSE)) {
                    ServerController.confirmContactRequest(
                            fromClient.getCommandArg(0),
                            fromClient.getCommandArg(1),
                            fromClient.getCommandArg(2).equals(Command.ACCEPT));
                }
                if (fromClient.getCommandName().equals(Command.LOGOUT)) {
                    placeCommand(new Command(Command.TEST_CONNECTION));
                }
                if (fromClient.getCommandName().equals(Command.DELETE_CONTACT)) {
                    ServerController.deleteContact(fromClient.getCommandArg(0),
                                                    fromClient.getCommandArg(1));
                }
            }
            catch (EOFException e) {}
            catch (Exception e) {}
        }
        try {
            in.close();
        }
        catch (Exception e) {}
        ServerController.closeConnection(name);
    }

    private void sendSocket() {
        while (run) {
            try {
                Thread.sleep(100);
                if (!commandQueue.isEmpty()) {
                    toClient = commandQueue.get(0);
                    out.writeObject(toClient);
                    if (toClient.getCommandName().equals(Command.UPDATE_CONTACT_LIST)) {
                        out.writeObject(DataHandler.loadContactList(name));
                    }
                    commandQueue.remove(0);
                }
            }
            catch (IOException e) {
                System.out.println("Connection lost.");
                run = false;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            out.close();
        }
        catch (Exception e) {}
    }
}
