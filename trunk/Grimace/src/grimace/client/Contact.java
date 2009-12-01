/**
 * Contact.java
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

public class Contact implements java.io.Serializable {

    // @TODO: Interact with the database somehow. I think. Maybe not.

    private String userName;
    private String displayName;

    /**
     * Creates an instance of Contact with an existing account
     * @param account  The account associated with this Contact instance
     */
    public Contact(Account account) {
        this.userName = account.getUserName();
        this.displayName = account.getDisplayName();
    }

    /**
     * Creates an instance of Contact with the user name and display name of
     * an existing account.
     * @param userName  The user name associated with this Contact instance.
     * @param dispName  The display name associated with this Contact instance.
     */
    public Contact(String userName, String dispName) {
        this.userName = userName;
        this.displayName = dispName;
    }

    /**
     * Accessor of the username of the contact
     * @return the username of the contact
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Accessor of the display name of the contact
     * @return the display name of the contact
     */
    public String getDisplayName() {
        return this.displayName;
    }

}
