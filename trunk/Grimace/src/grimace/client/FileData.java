/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.client;

import java.io.*;

/**
 *
 * @author vs
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
