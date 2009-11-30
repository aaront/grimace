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