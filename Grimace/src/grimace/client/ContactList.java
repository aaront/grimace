/**
 * ContactList.java
 *
 * @author Aaron Toth
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

import java.util.ArrayList;

public class ContactList {

    private ArrayList<Contact> list;

    public ContactList() {
        list = new ArrayList();
    }

    // Assuming that we're using an array data structure
    // @TODO: Definitely get the results from a database. Somehow. Maybe.
    
    public static void addContact(Contact toAdd) {
        
    }
    
    public static void removeContact(Contact toRemove) {
        
    }
    
    public static ArrayList<Contact> getList() {
        return list;
    }

}
