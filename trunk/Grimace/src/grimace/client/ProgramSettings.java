/**
 * ProgramSettings.java
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

public class ProgramSettings implements Serializable {
    // @TODO: Store preferences in working directory
    //Preferences prefs;
    String SETTINGS_FILE = System.getProperty("user.dir") + "ProgramSettings.ser";
    String serverAddress;
    int serverPort;
    Boolean showInTray;
    Boolean showEmoticons;
    Boolean recordChatLogs;
    Boolean showEquationEditor;

    public ProgramSettings() {
        loadSettings();
    }

    public void loadSettings() {
        //prefs = Preferences.systemRoot().node(this.getClass().getName());
        File file = new File(SETTINGS_FILE);
        if (!file.exists()) {
            setServerAddress("127.0.0.1");
            setServerPort(4422);
            setShowInTray(true);
            setShowEmoticons(true);
            setRecordChatLogs(false);
            setShowEquationEditor(false);
        } else {
            ProgramSettings saved = (ProgramSettings) FileSystem.loadFile(SETTINGS_FILE);
            setServerAddress(saved.getServerAddress());
            setServerPort(saved.getServerPort());
            setShowInTray(saved.getShowInTray());
            setShowEmoticons(saved.getShowEmoticons());
            setRecordChatLogs(saved.getRecordChatLogs());
            setShowEquationEditor(saved.getShowEquationEditor());
        }
    }

    public void saveSettings() {
        FileSystem.saveFile(this, SETTINGS_FILE);
    }

    public void setServerAddress(String address) {
        serverAddress = address;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerPort(int port) {
        serverPort = port;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setShowInTray(Boolean enabled) {
        showInTray = enabled;
    }

    public Boolean getShowInTray() {
        return showInTray;
    }

    public void setShowEmoticons(Boolean enabled) {
        showEmoticons = enabled;
    }

    public Boolean getShowEmoticons() {
        return showEmoticons;
    }

    public void setRecordChatLogs(Boolean enabled) {
        recordChatLogs = enabled;
    }

    public Boolean getRecordChatLogs() {
        return recordChatLogs;
    }

    public void setShowEquationEditor(Boolean enabled) {
        showEquationEditor = enabled;
    }

    public Boolean getShowEquationEditor() {
        return showEquationEditor;
    }
}