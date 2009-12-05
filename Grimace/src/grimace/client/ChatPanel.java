/**
 * ChatPanel.java
 *
 * @author David Marczak
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

import javax.swing.text.*;
import javax.swing.text.html.*;

/**
 * ChatPanel incorporates the ChatBox, as well as a contact list for the current
 * conversation, and some quick options that the user can set.
 */
public class ChatPanel extends javax.swing.JPanel {
    ClientConversation convo;
    HTMLEditorKit htmlKit;
    HTMLDocument htmlDoc;

    /** Creates new form ChatPanel */
    public ChatPanel() {
        initComponents();
    }

    /** Creates new form ChatPanel */
    public ChatPanel(ClientConversation conversation) {
        this();
        convo = conversation;
        chatBox1.setConId(convo.getConId());
        htmlKit = new HTMLEditorKit();
        htmlDoc = (HTMLDocument)htmlKit.createDefaultDocument();
        chatBox1.getChatDisplayBox().setDocument(htmlDoc);
        try {
			htmlKit.insertHTML(htmlDoc, 0, "<p id=\"WernickeChat\"></p>", 0, 0, HTML.Tag.P);
		}
		catch (Exception e) {}
    }

    public String getTitle() {
        return convo.getTitle();
    }

    public int getID() {
        return convo.getConId();
    }

    public void postMessage(String message, String userName) {
        System.out.println("Received: " + userName + ": " + message);
        String dName = userName;
        Contact user = convo.getList().getContact(userName);
        if (user != null) {
            dName = user.getDisplayName();
        }
        String messageText = "<p><strong>" + dName + "</strong>: " + message + "</p>";
        try {
			htmlKit.insertHTML(htmlDoc, 0, messageText, 0, 0, HTML.Tag.P);
            htmlKit.insertHTML(htmlDoc, 0, "<br>", 0, 0, HTML.Tag.BR);
		}
		catch (Exception e) {}
        convo.storeRecievedMessage(messageText);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatBox1 = new grimace.client.ChatBox();

        setMinimumSize(new java.awt.Dimension(400, 420));

        chatBox1.initChatBox();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chatBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private grimace.client.ChatBox chatBox1;
    // End of variables declaration//GEN-END:variables

}
