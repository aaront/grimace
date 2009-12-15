
/**
 * ContactList.java
 *
 * @author David Marczak
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
import java.util.ArrayList;
import java.io.*;
import java.io.DataInputStream;



/**
 * ClientConversation is a local representation of a chat conversation.
 *
 * optionsToggled is an arraylist that will keep track
 * of the options on (true) or off (false). The index
 * numbers and options that correspond are yet to be
 * completely determind
 *
 */
public class ClientConversation {
    int conId;
    ArrayList<String> conversationText = new ArrayList<String>();
    ContactList convoPeople;
    
    /**
     * ClientConversation, creates an instance of a client conversation
     * @param conId   The conversation identification integer
     * @param cList   The list of Contacts in the conversation
     */
    public ClientConversation(int conId, ContactList cList){
        this.conId = conId;
        convoPeople = cList;
}
    public ContactList getList(){
        return convoPeople;
    }

    /**
     * Contacts the server and retrieves the requested
     * @return String containing the names of the participants
     */
    public String getTitle(){
        int numPeople = convoPeople.getSize();
        int i;
        String people = "";

        for (i = 0; i < numPeople; i++){
            if (i > 0) {
                people = people.concat(", ");
            }
            people = people.concat(convoPeople.getContact(i).getDisplayName());
        }

        return people;
    }
    
    /**
     * Logs the conversationText
     * @param filename  The name of the log file (to be specified?)
     * @param date  The date gets thrown in at the end so as to be read
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void logConversation(File log) {
        if (log.exists()){ return; }
        try {
            FileWriter outFile = new FileWriter(log.getAbsoluteFile());
            PrintWriter out = new PrintWriter(outFile);

            for (String s : conversationText) {
                out.println(s);
            }
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * New message recieved and added to conversationText
     * @param newMessage    The new recieved message
     */
    public void storeRecievedMessage(String newMessage){
        if (newMessage != null){
            conversationText.add(newMessage);
        }
    }

    /**
     * Gets the conversation ID number
     * @return the conversation ID number
     */
    public int getConId(){
        return this.conId;
    }

    /**
     * Adds a contact to the current conversation's contact list
     * @param contact the contact to add
     */
    public void addToList(Contact contact) {
        convoPeople.addContact(contact);
    }

    public void removeFromList(String userName) {
        Contact contact = convoPeople.getContact(userName);
        if (contact == null) { return; }
        convoPeople.removeContact(contact);
    }

}

