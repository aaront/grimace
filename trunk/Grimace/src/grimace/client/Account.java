/**
 * Account.java
 *
 * @author Aaron Jankun
 *
 * Copyright (C) 2009 Justin Cole, Aaron Jankun, David Marczak Vineet Sharma,
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

public class Account {

    private ContactList cList;
    private UserSettings settings;
    private String userName;
    private String displayName;


    /**
     * Creates a Account instance with a selected user name
     * @param iUserName     Desired User name for the account.
     */
    public Account(String iUserName){

        cList = new ContactList();
        settings = new UserSettings();
        userName = iUserName;
        displayName = userName;        
    }

     /**
     * Creates a Account instance with a selected user name
     * @param iUserName     Desired User named for the account.
     * @param iDisplayName     Desired Display name for the account
     */
    public Account (String iUserName, String iDisplayName){
        cList = new ContactList();
        settings = new UserSettings();
        userName = iUserName;
        displayName = iDisplayName;
    }

     /**
     * Gives the user name for an account.
     * @return Returns the userName string for an account.
     */
     public String getUserName(){
         return userName;         
     }

     /**
     * Gives the display name for an account.
     * @return Returns the displayName string for an account.
     */
     public String getDisplayName(){
         return displayName;
     }

     /**
     * Gives the contact list for an account.
     * @return Returns the ContactList for an account.
     */
     public ContactList getContactList(){
         return cList;
     }

     /**
     * Gives the settings for an account.
     * @return Returns the Settings for an account.
     */
     public UserSettings getSettings(){
         return settings;
     }

     /**
     * Changes the displayName to name.
     * @param String to replace old displayName.
     */
     public void setDisplayName(String name){
         displayName = name;
     }





}


