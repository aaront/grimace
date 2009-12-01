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
    private boolean isLoggedIn;
    private ArrayList<Command> commandQueue;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Thread listen;
    private Thread active;
    private Command fromClient;
    private Command toClient;
    private boolean run;

    public ClientHandler(Socket socket) {
        this.name = "";
        isLoggedIn = false;
        run = false;
        commandQueue = new ArrayList<Command>();
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            run = true;
            listen = new Thread(new Runnable() {
                                public void run() {
                                    listenSocket();
                                }
                            });
            listen.start();
            active = new Thread(new Runnable() {
                                public void run() {
                                    activeSocket();
                                }
                            });
            active.start();
        }
        catch (Exception e) {
            System.out.println("Socket IO Problem:");
            e.printStackTrace();
            run = false;
        }
    }

    private void setName(String str) {
        name = str;
    }

    public String getName() {
        return name;
    }

    private void listenSocket() {
        while (true) {
            if (!run) { break; }
            try {
                fromClient = (Command)in.readObject();
                handleCommand(fromClient);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
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
