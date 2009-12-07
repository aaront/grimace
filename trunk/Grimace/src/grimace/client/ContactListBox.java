/**
 * ContactListBox.java
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

package grimace.client;

import grimace.common.ContactList;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;

/**
 * ContactListBox displays a ContactList.
 */
public class ContactListBox extends JList {
    private ContactList cList;

    /**
     * Constructor for a new ContactListBox
     */
    public ContactListBox() {
        DefaultListSelectionModel lsm = new DefaultListSelectionModel();
        lsm.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setSelectionModel(lsm);
    }

    /**
     * Updates the contents of ContactListBox
     */
    public void updateModel() {
        DefaultListModel m = new DefaultListModel();
        int n = cList.getSize();
        for (int i = 0; i < n; i++) {
            m.addElement(cList.getContact(i));
        }
        setModel(m);
    }

    /**
     * Updates the contents of ContactListBox with a new list
     * @param list a new list of contents
     */
    public void updateModel(ContactList list) {
        cList = list;
        updateModel();
    }

    public ContactList getList() {
        return cList;
    }
}
