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

import java.util.prefs.*;

public class ProgramSettings {
    Preferences prefs;

    public ProgramSettings() throws BackingStoreException {
        loadSettings(Preferences.systemRoot().nodeExists(this.getClass().getName()));
    }

    public void loadSettings(Boolean exists) {
        prefs = Preferences.systemRoot().node(this.getClass().getName());
        if (!exists) {
            setServerAddress("127.0.0.1");
            setServerPort(4422);
            setShowInTray(true);
            setShowEmoticons(true);
            setRecordChatLogs(false);
        }
    }

    public void setServerAddress(String address) {
        prefs.put("ServerAddress", address);
    }

    public String getServerAddress() {
        return prefs.get("ServerAddress", "127.0.0.1");
    }

    public void setServerPort(int port) {
        prefs.putInt("ServerPort", port);
    }

    public int getServerPort() {
        return prefs.getInt("ServerPort", 4422);
    }

    public void setShowInTray(Boolean enabled) {
        prefs.putBoolean("ShowInTray", enabled);
    }

    public Boolean getShowInTray() {
        return prefs.getBoolean("ShowInTray", true);
    }

    public void setShowEmoticons(Boolean enabled) {
        prefs.putBoolean("ShowEmoticons", enabled);
    }

    public Boolean getShowEmoticons() {
        return prefs.getBoolean("ShowEmoticons", true);
    }

    public void setRecordChatLogs(Boolean enabled) {
        prefs.putBoolean("RecordChatLogs", enabled);
    }

    public Boolean getRecordChatLogs() {
        return prefs.getBoolean("RecordChatLogs", false);
    }
}