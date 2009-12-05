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

import java.util.*;
import java.io.Serializable;

/**
 * ContactList is a list of contacts that can be added to, remove contacts,
 * and perform various operations on the contacts within it.
 */
public class ContactList implements Serializable {

    private ArrayList<Contact> list;

    /**
     * Creates an instance of a contact list.
     */
    public ContactList() {
        list = new ArrayList<Contact>();
    }

    /**
     * Creates an instance of a contact list with specified contacts.
     */
    public ContactList(Contact[] contacts) {
        list = new ArrayList<Contact>();
        int i;
        for (i = 0; i < contacts.length; i++) {
            list.add(contacts[i]);
        }
    }

    // Assuming that we're using an array data structure
    // @TODO: Definitely get the results from a database. Somehow. Maybe.

    /**
     * Adds a contact to a user's contact list
     * @param toAdd  The contact to be added to the user's contact list
     */
    public void addContact(Contact toAdd) {
        list.add(toAdd);
    }

    /**
     * Removes a contact to a user's contact list
     * @param toRemove  The contact to be removed to the user's contact list
     */
    public void removeContact(Contact toRemove) {
        list.remove(toRemove);
    }

    public Contact getContact(String userName) {
        for (Contact c : list) {
            if (c.getUserName().equals(userName)) {
                return c;
            }
        }
        return null;
    }

    public Contact getContact(int ind) {
        return list.get(ind);
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Public method for accessing the contact list
     * @return the list of contacts
     */
    public ArrayList<Contact> getList() {
        return list;
    }

}
