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
    private Thread active;
    private Command fromClient;
    private Command toClient;
    private boolean run;

    public ClientHandler(Socket socket, String name, ObjectInputStream in, ObjectOutputStream out) {
        this.name = name;
        commandQueue = new ArrayList<Command>();
        this.socket = socket;
        this.in = in;
        this.out = out;
        listen = new Thread(new Runnable() {
                            public void run() {
                                listenSocket();
                            }
                        });
        active = new Thread(new Runnable() {
                            public void run() {
                                activeSocket();
                            }
                        });
        run = true;
        listen.start();
        active.start();
    }

    private void setName(String str) {
        name = str;
    }

    public String getName() {
        return name;
    }

    private void listenSocket() {
        while (run) {
            try {
                fromClient = (Command)in.readObject();
                handleCommand(fromClient);
            }
            catch (java.io.EOFException e) { }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void activeSocket() {
        while (true) {
            if (!run) { break; }
        }
    }

    private void handleCommand(Command cmd) {
        
    }
}
