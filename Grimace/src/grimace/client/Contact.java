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

/**
 * Contact is a reference to an Account that is formatted for diasplay in a
 * ContactList.
 */
public class Contact implements java.io.Serializable {

    // @TODO: Interact with the database somehow. I think. Maybe not.

    private String userName;
    private String displayName;
    private String status;

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
     * Creates an instance of Contact with the user name and display name of
     * an existing account.
     * @param userName  The user name associated with this Contact instance.
     * @param dispName  The display name associated with this Contact instance.
     */
    public Contact(String userName, String dispName, String status) {
        this(userName, dispName);
        this.status = status;
    }

    /**
     * Accessor of the username of the contact
     * @return the username of the contact
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Accessor of the display name of the contact
     * @return the display name of the contact
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the displayName for this contact
     * @param str The new display name for this contact
     */
    public void setDisplayName(String str) {
        displayName = str;
    }

    /**
     * Accessor of the display name of the contact
     * @return the display name of the contact
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status for this contact
     * @param str The new status for this contact
     */
    public void setStatus(String str) {
        status = str;
    }

    /**
     * Returns the display name of the contact as a string
     * @return the display name
     */
    @Override
    public String toString() {
        return displayName + ": " + status;
    }
}
