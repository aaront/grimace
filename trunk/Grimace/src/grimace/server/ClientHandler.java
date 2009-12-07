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
import grimace.client.ContactList;
import grimace.client.Contact;
import grimace.client.FileData;

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
                System.out.println("Command from user " + name + ": " + fromClient.toString());
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
                if (fromClient.getCommandName().equals(Command.START_CONVERSATION)) {
                    ServerController.createConversation(fromClient.getCommandArgList());
                }
                if (fromClient.getCommandName().equals(Command.SEND_MESSAGE)) {
                    int conId = Integer.valueOf(fromClient.getCommandArg(2)).intValue();
                    ServerController.sendMessage(conId,
                                                fromClient.getCommandArg(1),
                                                fromClient.getCommandArg(0));
                }
                if (fromClient.getCommandName().equals(Command.UPDATE_DISPLAY_NAME)) {
                    String userName = fromClient.getCommandArg(0);
                    String displayName = fromClient.getCommandArg(1);
                    String[] args = fromClient.getCommandArgList();
                    int[] ids = new int[args.length - 2];
                    for (int i=2; i<args.length; i++) {
                        ids[i] = Integer.valueOf(args[i]).intValue();
                    }
                    ServerController.updateDisplayName(userName, displayName, ids);
                }
                if (fromClient.getCommandName().equals(Command.UPDATE_STATUS)) {
                    String userName = fromClient.getCommandArg(0);
                    String status = fromClient.getCommandArg(1);
                    String[] args = fromClient.getCommandArgList();
                    int[] ids = new int[args.length - 2];
                    for (int i=2; i<args.length; i++) {
                        ids[i] = Integer.valueOf(args[i]).intValue();
                    }
                    ServerController.updateStatus(userName, status, ids);
                }
                if (fromClient.getCommandName().equals(Command.UPDATE_FONT)) {
                    ServerController.updateFont(fromClient.getCommandArg(0),
                            fromClient.getCommandArg(1),
                            fromClient.getCommandArg(2),
                            fromClient.getCommandArg(3),
                            Boolean.valueOf(fromClient.getCommandArg(4)).booleanValue(),
                            Boolean.valueOf(fromClient.getCommandArg(5)).booleanValue());
                }
                if (fromClient.getCommandName().equals(Command.REMOVE_FROM_CONVERSATION)) {
                    int conId = Integer.valueOf(fromClient.getCommandArg(1)).intValue();
                    ServerController.removeFromConversation(fromClient.getCommandArg(0), conId);
                }
                if (fromClient.getCommandName().equals(Command.ADD_TO_CONVERSATION)) {
                    int conId = Integer.valueOf(fromClient.getCommandArg(1)).intValue();
                    ServerController.addToConversation(fromClient.getCommandArg(0),
                                                        conId,
                                                        fromClient.getCommandArg(2));
                }
                if (fromClient.getCommandName().equals(Command.FILE_TRANSFER_REQUEST)) {
                    String userName = fromClient.getCommandArg(0);
                    String fileName = fromClient.getCommandArg(1);
                    String conName = fromClient.getCommandArg(2);
                    ServerController.placeFileTransferRequest(userName, conName, fileName);
                }
                if (fromClient.getCommandName().equals(Command.FILE_TRANSFER_RESPONSE)) {
                    String userName = fromClient.getCommandArg(0);
                    String fileName = fromClient.getCommandArg(1);
                    String conName = fromClient.getCommandArg(2);
                    boolean confirm = fromClient.getCommandArg(3).equals(Command.ACCEPT);
                    ServerController.confirmFileTransferRequest(userName, conName, fileName, confirm);
                }
                if (fromClient.getCommandName().equals(Command.TRANSFER_FILE)) {
                    FileData fileData = (FileData)in.readObject();
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
                    if (toClient.getCommandName().equals(Command.START_CONVERSATION)) {
                        int conId = Integer.valueOf(toClient.getCommandArg(0)).intValue();
                        ContactList conList = new ContactList();
                        String[] users = ServerController.getServerConversation(conId).getUsers();
                        for (String s : users) {
                            conList.addContact(new Contact(s,
                                                    DataHandler.getDisplayName(s),
                                                    DataHandler.getDisplayStatus(s)));
                        }
                        out.writeObject(conList);
                    }
                    if (toClient.getCommandName().equals(Command.UPDATE_CONTACT)
                        || toClient.getCommandName().equals(Command.UPDATE_CONVO_CONTACT)) {
                        String user = toClient.getCommandArg(0);
                        Contact con = new Contact(user,
                                                DataHandler.getDisplayName(user),
                                                DataHandler.getDisplayStatus(user));
                        out.writeObject(con);
                    }
                    if (toClient.getCommandName().equals(Command.ADD_TO_CONVERSATION)) {
                        String user = toClient.getCommandArg(0);
                        Contact con = new Contact(user,
                                                DataHandler.getDisplayName(user),
                                                DataHandler.getDisplayStatus(user));
                        out.writeObject(con);
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
