/**
 * FileSystem.java
 *
 * @author Justin Cole
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

import java.io.*;

/**
 * FileSystem interacts with the filesystem of the host machine, and facilitates
 * the loading and saving of files sent over the chat.
 */
public class FileSystem {

    /**
     * Saves a file to the specified path on the user's hard drive
     * @param data the file to save
     * @param path the path to save the file to on the user's hard drive
     */
    public static void saveFile(Serializable data, String path) {
        try {
            FileOutputStream outFile = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(outFile);
            
            out.writeObject(data);
            out.close();
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a file from the specified path
     * @param path the path of the file on the user's hard drive
     * @return the file loaded from the user's hard drive
     */
    public static Serializable loadFile(String path) {
        try {
            FileInputStream inFile = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(inFile);

            Serializable obj = (Serializable) in.readObject();
            in.close();
            inFile.close();

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }

    /**
     * Deletes all files in the given directory
     *
     * @param dir   The representation of the folder to delete.
     */
    public static void deleteInDirectory(File dir) {
        if(dir.exists()) {
            File[] files = dir.listFiles();
            for(File f : files) {
                if(f.isDirectory()) {
                    deleteInDirectory(f);
                    f.delete();
                }
                else {
                    f.delete();
                }
            }
        }
    }
    
}