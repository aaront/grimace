
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
import java.util.ArrayList;
import java.io.*;
import java.io.DataInputStream;



/**
 *
 * @author David
 * optionsToggled is an arraylist that will keep track
 * of the options on (true) or off (false). The index
 * numbers and options that correspond are yet to be
 * completely determind
 *
 */
public class ClientConversation {
    int conId;
    ArrayList<String> conversationText = new ArrayList<String>();
    QuickOptions convoQOptions;
    ContactList convoPeople;
    
    /**
     * ClientConversation, creates an instance of a client conversation
     * @param conId   The conversation identification integer
     * @param cList   The list of Contacts in the conversation
     */
    public ClientConversation(int conId, ContactList cList){
        this.conId = conId;
        convoQOptions = new QuickOptions();
        convoPeople = cList;
}
    public ArrayList<Contact> getList(){
        return convoPeople.getList();
    }

    /**
     * Contacts the server and retrieves the requested
     * @return String containing the names of the participants
     */
    public String getTitle(){
        int numPeople = convoPeople.getList().size();
        int i;
        String people = "";

        for (i = 0; i < numPeople; i++){
            if (i > 0) {
                people = people.concat(", ");
            }
            people = people.concat(convoPeople.getList().get(i).getDisplayName());
        }

        return people;
    }
    
    /**
     * Logs the conversationText (where is it from?)
     * @param filename  The name of the log file (to be specified?)
     * @param date  The date gets thrown in at the end so as to be read
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void logConversation(String filename, String date) throws FileNotFoundException, IOException{
        File log = new File(filename);
        ArrayList<String> logText;
        
        if (log.exists()){
            logText = conversationText;
            logText.add(date);
            
            FileOutputStream fout = new FileOutputStream(log);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(conversationText);
            fout.close();

        }
                 
    }
/**
 * I believe this should take a filename and print it out with file.io
 * @param filename
 * @throws FileNotFoundException
 * @throws IOException
 */
    public void openLog(String filename) throws FileNotFoundException, IOException{

        FileInputStream log = new FileInputStream(filename);

        /* Java 6 API says do this:
         * new BufferedReader(new InputStreamReader(log)).readLine();
         */
        System.out.println(new BufferedReader(new InputStreamReader(log)).readLine());

        log.close();

    }
    /**
     * New message recieved and added to conversationText
     * @param newMessage    The new recieved message
     */
    public void displayRecievedMessage(String newMessage){

        if (newMessage != null){
            conversationText.add(newMessage);
         }
    }

    /**
     * Adds the new message to conversationText
     * and sends it to the server in some way
     * @param newSentMessage    the new message to be sent
     */
    public void prepareMessageForSending(String newSentMessage){

        if (newSentMessage != null){
            conversationText.add(newSentMessage);
            //and server sending stuff
        }
    }

    public void prepareEquationForSending(String equation){

        if (equation != null){
            conversationText.add(equation);
            //and server sending stuff
        }
    }

    public void prepareFileForSending(File filename){
        //and server sending stuff
    }

    public QuickOptions getOptions(){

        return convoQOptions;
    }

    public int getConId(){
        return this.conId;
    }

    public void addToList(Contact userName) {
        convoPeople.getList().add(userName);
    }


}

