/**
 * ServerConversation.java
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

import java.util.ArrayList;

/**
 * ServerConversation tracks the users involved in a single chat conversation.
 * 
 * @author Vineet Sharma
 */
public class ServerConversation {
    private int conId;
    private ArrayList<String> users;

    /**
     * Creates an empty ServerConversation.
     *
     * @param conId An integer identifying this ServerConversation instance.
     */
    public ServerConversation(int conId) {
        this.conId = conId;
        users = new ArrayList<String>();
    }

    /**
     * Creates an empty ServerConversation.
     *
     * @param conId An integer identifying this ServerConversation instance.
     * @param users The users in this conversation.
     */
    public ServerConversation(int conId, ArrayList<String> users) {
        this.conId = conId;
        this.users = users;
    }

    /**
     * Adds a user to this ServerConversation.
     *
     * @param userName  The name of a user to add to the ServerConversation.
     */
    public void addUser(String userName) {
        users.add(userName);
    }

    /**
     * Removes a user from this ServerConversation.
     *
     * @param userName Name of the user to remove from the ServerConversation.
     */
    public void removeUser(String userName) {
        users.remove(userName);
    }

    /**
     * Returns the number of users in this ServerConversation
     *
     * @return The number of users in this ServerConversation.
     */
    public String[] getUsers() {
        return (String[]) users.toArray();
    }
}