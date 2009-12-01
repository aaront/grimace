/**
 * ContactList.java
 *
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

import java.util.ArrayList;

public class ContactList implements java.io.Serializable {

    private ArrayList<Contact> list;

    /**
     * Creates an instance of a contact list.
     */
    public ContactList() {
        this.list = new ArrayList();
    }

    // Assuming that we're using an array data structure
    // @TODO: Definitely get the results from a database. Somehow. Maybe.

    /**
     * Adds a contact to a user's contact list
     * @param toAdd  The contact to be added to the user's contact list
     */
    public void addContact(Contact toAdd) {
        this.list.add(toAdd);
    }

    /**
     * Removes a contact to a user's contact list
     * @param toRemove  The contact to be removed to the user's contact list
     */
    public void removeContact(Contact toRemove) {
        this.list.remove(toRemove);
    }

    /**
     * Public method for accessing the contact list
     * @return the list of contacts
     */
    public ArrayList<Contact> getList() {
        return this.list;
    }

}