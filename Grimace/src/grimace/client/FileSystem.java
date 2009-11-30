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

public class FileSystem {
    // @TODO: Which methods will need to use FileSystem?

    public void saveFile(Serializable data, String path) throws Exception {
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

    public Object loadFile(String path) throws Exception {
        try {
            FileInputStream inFile = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(inFile);

            Object obj = in.readObject();
            in.close();
            inFile.close();

            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
}