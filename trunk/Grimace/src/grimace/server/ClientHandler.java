/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    private void listenSocket() {
        while (run) {
            try {
                fromClient = (Command)in.readObject();
                
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
                if (!commandQueue.isEmpty()) {
                    toClient = commandQueue.get(0);
                    out.writeObject(toClient);
                    commandQueue.remove(0);
                }
            }
            catch (IOException e) {
                System.out.println("Connection lost.");
                run = false;
            }
            catch (Exception e) {}
        }
        try {
            out.close();
        }
        catch (Exception e) {}
    }
}
