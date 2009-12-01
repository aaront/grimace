/**
 * ProgramController.java
 *
 * @author Justin Cole, Aaron Jankun, David Marczak, Vineet Sharma, Aaron Toth
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
import java.util.ArrayList;
import java.io.File;

/**
 * The big kahuna, cheese, mega overlord, grand ruler of Wernickeland
 */
public class ProgramController {

    String username;
    String displayname;

    Account accnt = new Account(username, displayname);
    ContactList list = accnt.getContactList();
    ClientConversation convo;


    public ProgramController() {
        // @TODO: Create a new session with an account
    }

    /**
     * Sends a new registration form to the ServerHandler
     * @param newaccount the account to be registered with the server
     */
    public void makeRegistration(Account newaccount) {

    }

    /**
     * Retrieves a chat log
     * @param conversation the conversation that occurred
     * @return the chat log of the conversation
     */
    public void getChatLog(ClientConversation conversation) {
        
    }

    /**
     * Adds a contact by username to the current account's contact list
     * @param userName the contact to be added
     */
    public void addContact(Contact userName) {
        accnt.getContactList().addContact(userName);
    }

    /**
     * Removes a contact by username from the current account's contact list
     * @param userName the contact to be removed
     */
    public void removeContact(Contact userName) {
        accnt.getContactList().removeContact(userName);
    }

    /**
     * Retrieves a contact list from the server associated with the current
     * account.
     */
    public ContactList getContactList() {
    return accnt.getContactList();
    }

    /**
     * Updates the contact list for the current account.
     */
    public void updateContactList() {

    }

    /**
     * Creates a new conversation with usernames
     * @param userName an array of usernames to initiate a conversation with
     * @return an instance of the conversation
     */
    public ClientConversation makeConversation(ContactList list) {
       ClientConversation convo = new ClientConversation(list);
        return convo;
    }

    /**
     * Adds a contact to an existing conversation
     * @param userName the contact to add to the conversation
     * @param conversation the conversation that receives the contact
     */
    public void addContactToConversation(Contact userName, ClientConversation conversation) {


    }

    /**
     * Receive message from a conversation
     * @param conversation the conversation from where the message originates
     * @return the message from the conversation
     */
    public void receiveMessage(String messageIn) {
        convo.displayRecievedMessage(messageIn);

    }

    /**
     * Send message to a conversation
     * @param conversation the conversation to send the message to
     * @param message the sent message
     * 
     */
    public void sendMessage(String message) {
        convo.prepareMessageForSending(message);
    }

    /**
     * Creates a new equation to send
     * @return the string-formatted equation
     */
    public void createEquation(String eqn) {

    }

    /**
     * Receive an equation from a conversation
     * @return the string-formatted equation
     * @TODO: determine the correct output
     */
    public String receiveEquation() {
        return "";
    }

    /**
     * Sends an equation to a conversation
     * @param equation the equation to be sent
     * @return the string-formatted equation that was sent
     */
    public void sendEquation(String eqn) {
        convo.prepareEquationForSending(eqn);
    }

    // @TODO: Are we going to be leaving out "setFontProperties?????"

    /**
     * Get's the current account's program settings
     * @return the program settings
     */
    public ProgramSettings getProgramSettings() {
        return null;
    }

    /**
     * Logs into the system
     * @param userName the username
     * @param password the password
     */
    public void login(String userName, String password) {

    }

    /**
     * Logout the current account
     */
    public void logout() {

    }

    /**
     * Close a chat panel
     * @param cp the chat panel to be closed
     */
    public void closeTab(/*ChatPanel cp*/) {
        
    }

    /**
     * Sends a file in a conversation
     * @param fileName the name/location of the file to be sent
     * @param conversation the conversation to send the file to
     */
    public void sendFile(String fileName, ClientConversation conversation) {

    }

    /**
     * Receives a file
     * @param conversation the conversation from which the file originates
     * @return the file
     * @TODO: ???? lots....
     */
    public File receiveFile(ClientConversation conversation) {
        return null;
    }

    /**
     * Displays a prompt asking if the current account would like to download the file
     * @param fileName the filename of the file
     * @param conversation the conversation that was the origin of the file
     * @return a string asking if the account would like to download the file
     */
    public String displayFileInvitiation(String fileName, ClientConversation conversation) {
        return "";
    }

}
