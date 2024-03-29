/**
 * ProgramController.java
 *
 * @author Justin Cole
 * @author Aaron Jankun
 * @author David Marczak
 * @author Vineet Sharma
 * @author Aaron Toth
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
import grimace.common.ContactList;
import grimace.common.Contact;
import grimace.common.Account;
import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

/**
 * ProgramController handles most of the client functionality of the program.
 */
public class ProgramController {
    public static final String DATA_FOLDER = System.getProperty("user.dir") + "/" + "WernickeData";
    public static final String TEMP_FOLDER = DATA_FOLDER + "/" + "temp";
    public static final String RECEIVED_FOLDER = DATA_FOLDER + "/" + "received";
    public static final String LOG_FOLDER = DATA_FOLDER + "/" + "chatlogs";

    private static Account accnt;
    private static ArrayList<ChatPanel> chatTabs;
    private static ProgramWindow window;
    private static ContactPanel contactListBox;
    private static String serverAddress = ServerHandler.DEF_SERVER_HOSTNAME;
    private static int serverPort = ServerHandler.DEF_SERVER_PORT;

    /**
     * Constructor for ProgramController. One is created every time the program
     * starts.
     */
    public ProgramController() {
        File dir = new File(DATA_FOLDER);
        if (!dir.exists()) {
            try { dir.mkdir(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        dir = new File(TEMP_FOLDER);
        if (!dir.exists()) {
            try { dir.mkdir(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        dir = new File(RECEIVED_FOLDER);
        if (!dir.exists()) {
            try { dir.mkdir(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        dir = new File(LOG_FOLDER);
        if (!dir.exists()) {
            try { dir.mkdir(); }
            catch (Exception e) { e.printStackTrace(); }
        }

        // Uses Nimbus as default theme. Much better than Metal. Search "Java nimbus"
        // on Google to find out more
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) { }

        chatTabs = new ArrayList<ChatPanel>();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                window = new ProgramWindow();
                window.setVisible(true);

                window.addWindowListener(new WindowListener() {
                    public void windowClosed(WindowEvent e) {
                        try {
                            FileSystem.deleteInDirectory(new File(TEMP_FOLDER));
                        }
                        catch (Exception ex) { ex.printStackTrace(); }
                        System.out.println("Wernicke is closing....");
                        if (accnt != null) {
                            logout();
                        }
                        System.exit(0);
                    }
                    public void windowClosing(WindowEvent e) {}
                    public void windowDeactivated(WindowEvent e) {}
                    public void windowDeiconified(WindowEvent e) {}
                    public void windowIconified(WindowEvent e) {}
                    public void windowOpened(WindowEvent e) {}
                    public void windowActivated(WindowEvent e) {}
                });
            }
        });

    }

    /**
     * Sets the content in the left pane of the Program Window
     * @param comp the component to display
     */
    public static void setLeftPane(Component comp) {
        window.setLeftPane(comp);
    }

    /**
     * Sets the content in the right pane of the Program Window
     * @param comp the component to display
     */
    public static void setRightPane(Component comp) {
        window.setRightPane(comp);
    }

    /**
     * Displays an message prompt
     * @param message The message to display.
     */
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(window, message);
    }

    /**
     * A request dialog
     * @param message the message to display in the dialog
     * @return the result of the request
     */
    public static int showRequestDialog(String message) {
        //Custom button text
        Object[] options = {"Accept",
                            "Reject",
                            "Ignore"};
        int n = JOptionPane.showOptionDialog(window,
            message,
            "Response Required",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[2]);
        return n;
    }

    public static String getServerAddress() {
        return serverAddress;
    }
    
    public static void setServerAddress(String str) {
        serverAddress = str;
    }

    public static int getServerPort () {
        return serverPort;
    }

    public static void setServerPort (int val) {
        serverPort = val;
    }

    /**
     * Gets the ContactListBox
     * @return the contact list box
     */
    public static ContactPanel getContactListBox() {
        return contactListBox;
    }

    /**
     * Retrieves a contact list from the server associated with the current
     * account.
     */
    public static ContactList getContactList() {
        return accnt.getContactList();
    }

    public static ChatPanel getChatPanel(int conId) {
        for (ChatPanel c : chatTabs) {
            if (c.getID() == conId) {
                return c;
            }
        }
        return null;
    }

    public static int[] getConIdList() {
        int len = chatTabs.size();
        int[] conIdList = new int[len];
        for (int i=0; i<len; i++) {
            conIdList[i] = chatTabs.get(i).getID();
        }
        return conIdList;
    }

    public static void setContactListBox(ContactPanel clb) {
        contactListBox = clb;
        setLeftPane(clb);
        ProgramWindow.updateChatTabs(chatTabs);
    }

    public static void sendConvoRequest(Contact[] contacts) {
        try {
            ServerHandler.sendConversationRequest(accnt.getUserName(), contacts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendAddContactRequest(String contactName) {
        try {
            ServerHandler.sendAddContactRequest(contactName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message, int conId) {
        try {
            ServerHandler.sendMessagePostRequest(message, conId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void postMessage(int conId, String message, String sender) {
        getChatPanel(conId).postMessage(message, sender);
    }

    public static void postNotification(int conId, String message) {
        getChatPanel(conId).postNotification(message);
    }

    public static void removeFromConversation(String userName, int conId) {
        ChatPanel panel = getChatPanel(conId);
        if (panel == null) { return; }
        panel.getClientConversation().removeFromList(userName);
        panel.postNotification(userName + " has left the conversation.");
        panel.getContactListBox().updateModel();
    }

    public static void addToConversation(Contact contact, int conId) {
        ChatPanel panel = getChatPanel(conId);
        if (panel == null) { return; }
        panel.getClientConversation().addToList(contact);
        panel.postNotification(contact.getUserName() + " has been added to the conversation.");
        panel.getContactListBox().updateModel();
    }

    public static void sendDeleteContactRequest(String contactName) {
        try {
            ServerHandler.sendDeleteContactRequest(contactName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDisplayName(String displayName) {
        accnt.setDisplayName(displayName);
        try {
            ServerHandler.sendDisplayNameUpdateRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAccountStatus(String status) {
        accnt.setStatus(status);
        try {
            ServerHandler.sendStatusUpdateRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logout() {
        if (chatTabs.size() > 0) {
            int[] conIdList = getConIdList();
            for (int id : conIdList) {
                closeConvo(id);
            }
        }
        setAccountStatus(Account.STATUS_OFFLINE);
        ServerHandler.sendLogoutRequest();
        accnt = null;
    }

    public static void openNewConvo(int conId, ContactList contacts) {
        ClientConversation convo = new ClientConversation(conId, contacts);
        ChatPanel panel = new ChatPanel(convo);
        chatTabs.add(panel);
        ProgramWindow.addChatTab(panel);
    }

    public static void closeConvo(int conId) {
        ChatPanel panel = getChatPanel(conId);
        if (panel == null) { return; }
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        String date = sdf.format(cal.getTime());
        File logFile = new File(LOG_FOLDER + "/" + date + ".html");
        panel.getClientConversation().logConversation(logFile);

        ProgramWindow.closeTab(panel);
        chatTabs.remove(panel);
        try {
            ServerHandler.sendQuitConversationNotification(conId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the contact list for the current account.
     */
    public static void updateContactList() {
        contactListBox.updateContactListView();
    }

    /**
     * Changes the currently active user
     * @param newAccount The new active Account
     */
    public static void setAccount(Account newAccount) {
        accnt = newAccount;
    }

    /**
     * Returns the currently active Account
     */
    public static Account getAccount() {
        return accnt;
    }

    public static String getUserName() {
        return accnt.getUserName();
    }

    public static String getDisplayName() {
        return accnt.getDisplayName();
    }

    public static String getAccountStatus() {
        return accnt.getStatus();
    }


    /**
     * Returns the program to the login form
     */
    public static void showLoginForm() {
        setRightPane(null);
        setLeftPane(new LoginForm());
    }

    public static Component getLoginForm() {
        Component comp = window.getLeftPane();
        if (comp instanceof LoginForm) {
            return comp;
        }
        return null;
    }

    /**
     * Returns a reference to the ProgramWindow frame
     * @return The currently active ProgramWindow frame
     */
    public static java.awt.Frame getWindow() {
        return window;
    }

    /**
     * Parses an equation from our message syntax
     * @param message the message containing an equation
     * @return an ArrayList of equations contained in a message
     */
    public static ArrayList<String> parseEquation(String message) {
        ArrayList<String> equations = new ArrayList<String>();

        try {
            // Wow, this sure is a lot of boilerplate code
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource s = new InputSource(new StringReader(message));
            Document equation = db.parse(s);

            // Filters out equations by the <eqn> tag
            NodeList listOfEquations = equation.getElementsByTagName("eqn");

            // Iterates through the equations and adds them to an ArrayList
            for (int i = 0; i < listOfEquations.getLength(); i++ ) {
                Element eqn = (Element) listOfEquations.item(i);
                equations.add(getStr(eqn));
            }
        }
        catch (Exception e) {
            System.out.println("Not a valid equation string.");
        }

        return equations;
    }

    /**
     * Gets the string representation of an Element in the NodeList
     * @param e an element in a NodeList
     */
    public static String getStr(Element e) {
       Node child = e.getFirstChild();
       if (child instanceof CharacterData) {
           CharacterData cd = (CharacterData)child;
           return cd.getData();
       }
       return "nil";
    }

    public static void main(String[] args) {
        new ProgramController();
    }
}