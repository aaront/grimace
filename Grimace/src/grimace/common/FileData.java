/**
 * FileData.java
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

package grimace.common;

import java.io.*;

/**
 * FileData provides a means of loading and storing data from a file in order
 * to send it serially.
 *
 * @author Vineet Sharma
 */
public class FileData implements Serializable {
    private String fileName;
    private byte[] fileData;

    public FileData(File file) {
        fileName = "";
        fileData = null;
        if (file.exists()) {
            fileName = file.getAbsolutePath();
            fileData = new byte[(int)file.length()];
            loadFileData(file);
        }
    }

    private void loadFileData(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(fileData,0,fileData.length);
        }
        catch (Exception e) {
            e.printStackTrace();
            fileData = null;
        }
    }

    public void saveFileData(File file) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(fileData, 0 , fileData.length);
        bos.flush();
    }
}
