/**
 * Command.java
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

import java.io.Serializable;

/**
 * Command represents a task communication between a client and server.
 *
 * @author Vineet Sharma
 */
public class Command implements Serializable {

    public static final String LOGIN = "login";
    public static final String REGISTER = "register";
    public static final String LOGIN_SUCCESS = "loginSuccess";
    public static final String LOGIN_FAILURE = "loginFailure";
    public static final String REGISTER_SUCCESS = "registerSuccess";
    public static final String REGISTER_FAILURE = "registerFailure";
    public static final String SERVER_CONNECT_FAILURE = "serverConnectFailure";
    public static final String CONTACT_REQUEST = "contactRequest";
    public static final String DELETE_CONTACT = "deleteContact";
    public static final String START_CONVERSATION = "startConversation";
    public static final String SEND_MESSAGE = "sendMessage";
    public static final String QUIT_CONVERSATION = "quitConversation";
    public static final String FILE_TRANSFER_REQUEST = "fileTransferRequest";
    public static final String FILE_TRANSFER_RESPONSE = "fileTransferResponse";
    public static final String CONTACT_REQUEST_RESPONSE = "contactRequestResponse";
    public static final String UPDATE_ACCOUNT = "updateAccount";
    public static final String USERNAME_EXISTS = "userNameExists";
    public static final String ACCOUNT_CREATION_ERROR = "accountCreationError";
    public static final String INVALID_COMMAND = "invalidCommand";
    public static final String DISPLAY_NOTIFICATION = "displayNotification";
    public static final String ACCEPT = "accept";
    public static final String REJECT = "reject";

    /** The name indicating the task to be completed. */
    private String cmdName;
    /** A list of arguments, parameters, or data. */
	private String[] args;

    /**
     * Creates a Command instance with the given command name and argument list.
     *
     * @param cmdName   The name indicating the task to complete.
     * @param args      A list of arguments, parameters, or data.
     */
	public Command(String cmdName, String... args) {
        this.cmdName = cmdName;
        this.args = args;
    }

    /**
     * Returns the name of the command represented by this object.
     * @return  The name indicating the task to complete.
     */
	public String getCommandName() {
		return cmdName;
	}

    /**
     * Returns the number of arguments held by this command.
     * @return  The number of arguments held by the command.
     */
    public int getArgumentNumber() {
        return args.length;
    }

    /**
     * Returns the (0-indexed) nth argument held by this command.
     * @param n The index of the argument to retrieve.
     * @return  The nth argument held by this command.
     */
	public String getCommandArg(int n) {
		return args[n];
	}

    /**
     * Returns a list of the arguments for this command.
     * @return A array of strings reperesenting the arguments of this command.
     */
	public String[] getCommandArgList() {
		return args;
	}

    /**
     * Returns a string representation of this command object.
     * @return A comma-separated string with the command name and arguments.
     */
	public String toString() {
		StringBuffer temp = new StringBuffer(cmdName);
        for (String s : args) {
            temp.append("," + s);
        }
        return temp.toString();
	}
}
