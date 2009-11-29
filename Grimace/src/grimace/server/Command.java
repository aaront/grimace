/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.server;

/**
 * Command represents a task communication between a client and server.
 *
 * @author Vineet Sharma
 */
public class Command {
    /** The name indicating the task to be completed. */
    private String cmdName;
    /** A list of arguments, parameters, or data. */
	private String[] args;

    /**
     * Creates a Command instance with the given command name and argument list.
     *
     * @param cmdName   The name indicating the
     * @param args      A list of arguments, parameters, or data.
     */
	public Command(String cmdName, String... args) {
		this.cmdName = cmdName;
        this.args = args;
    }

	public String getCommandName() {
		return cmdName;
	}

    public int getArgumentNumber() {
        return args.length;
    }

	public String getCommandArg(int n) {
		return args[n];
	}

	public String[] getCommandArgList() {
		return args;
	}

	public String toString() {
		String temp = cmdName;
        for (String s : args) {
            temp = temp + "," + s;
        }
        return temp;
	}
}
