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

import grimace.common.Contact;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.Box;

import java.awt.Point;
import java.io.File;

/**
 * ChatPanel incorporates the ChatBox, as well as a contact list for the current
 * conversation, and some quick options that the user can set.
 */
public class ChatPanel extends javax.swing.JPanel {
    ClientConversation convo;
    HTMLEditorKit htmlKit;
    HTMLDocument htmlDoc;
    Element convoElement;
    SideBar chatSideBar;

    /** Creates new form ChatPanel */
    public ChatPanel() {
        initComponents();
    }

    /**
     * Creates new form ChatPanel with a conversation ID
     * @param conversation the conversation associated with the panel
     */
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
        convoElement = htmlDoc.getElement("WernickeChat");

        //Load the sidebar
        chatSideBar = new SideBar(conversation);
        jSplitPane1.setRightComponent(chatSideBar);
    }

    /**
     * Gets the conversation title of the current conversation
     * @return the title of the conversation
     */
    public String getTitle() {
        return convo.getTitle();
    }

    /**
     * Gets the conversation ID of the current conversation
     * @return the ID of the conversation
     */
    public int getID() {
        return convo.getConId();
    }

    public ClientConversation getClientConversation() {
        return convo;
    }

    public ContactListBox getContactListBox() {
        return ((SideBar)jSplitPane1.getRightComponent()).getContactListBox();
    }

    /**
     * Posts a message to the chatDisplayBox from the server
     * @param message the message from the server
     * @param userName the username of the current account
     */
    public void postMessage(String message, String userName) {
        System.out.println("Received: " + userName + ": " + message);
        JTextPane textPane = chatBox1.getChatDisplayBox();
        String dName = userName;
        if (userName.equals(ProgramController.getUserName())) {
            dName = ProgramController.getDisplayName();
        }
        else {
            Contact user = convo.getList().getContact(userName);
            if (user != null) {
                dName = user.getDisplayName();
            }
        }
        String messageText = "<p><strong>" + dName + "</strong>: " + message + "</p>";
        ArrayList<String> equations = ProgramController.parseEquation(message);
        if (equations.size() > 0) {
            File equFile;
            equFile = EquationEditor.saveEquationImage(equations.get(0));
            if (equFile != null) {
                String eqntag = "<p><strong>" + dName + "</strong>: "
                                + "<img src=\"file://"+ equFile.getAbsolutePath() +"\"></p>";
                try {
                    htmlDoc.insertBeforeEnd(convoElement, eqntag);
                    htmlDoc.insertBeforeEnd(convoElement, "<br>");
                }
                catch (Exception e) {}
            }
            else {
                String msg = "<p><strong>" + dName + "</strong> has somehow entered an invalid formula.</p>";
                try {
                    htmlDoc.insertBeforeEnd(convoElement, msg);
                    htmlDoc.insertBeforeEnd(convoElement, "<br>");
                }
                catch (Exception e) {}
            }
        }
        else {
            try {
                htmlDoc.insertBeforeEnd(convoElement, messageText);
                htmlDoc.insertBeforeEnd(convoElement, "<br>");
//              textPane.scrollRectToVisible(new Rectangle(0,textPane
//                .getHeight()*11,1,1));
                chatBox1.getChatDisplayBoxScrollPane().getViewport()
                        .setViewPosition(new Point(0, 20*textPane.getDocument()
                        .getLength()));

            }
            catch (Exception e) {}
        }
        convo.storeRecievedMessage(messageText);
    }

    /**
     * Posts a message to the chatDisplayBox from the server
     * @param message the message from the server
     * @param userName the username of the current account
     */
    public void postNotification(String message) {
        System.out.println("Received: " + message);
        JTextPane textPane = chatBox1.getChatDisplayBox();
        String messageText = "<p><span style=\"color:#ff0000\">[" + message + "]</span></p>";
        try {
            htmlDoc.insertBeforeEnd(convoElement, messageText);
            htmlDoc.insertBeforeEnd(convoElement, "<br>");
//              textPane.scrollRectToVisible(new Rectangle(0,textPane
//                .getHeight()*11,1,1));
            chatBox1.getChatDisplayBoxScrollPane().getViewport()
                    .setViewPosition(new Point(0, 20*textPane.getDocument()
                    .getLength()));

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

        jSplitPane1 = new javax.swing.JSplitPane();
        chatBox1 = new grimace.client.ChatBox();
        chatPanelToolbar = new javax.swing.JToolBar();
        chatPanelToolbar.add(Box.createHorizontalGlue());
        btnCloseChatTab = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(400, 420));
        setLayout(new java.awt.BorderLayout());

        chatBox1.initChatBox();
        jSplitPane1.setLeftComponent(chatBox1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);

        chatPanelToolbar.setFloatable(false);
        chatPanelToolbar.setRollover(true);

        btnCloseChatTab.setText("x");
        btnCloseChatTab.setFocusable(false);
        btnCloseChatTab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCloseChatTab.setMaximumSize(new java.awt.Dimension(25, 22));
        btnCloseChatTab.setMinimumSize(new java.awt.Dimension(25, 22));
        btnCloseChatTab.setPreferredSize(new java.awt.Dimension(25, 22));
        btnCloseChatTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseChatTabActionPerformed(evt);
            }
        });
        chatPanelToolbar.add(btnCloseChatTab);

        add(chatPanelToolbar, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseChatTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseChatTabActionPerformed
        ProgramController.closeConvo(this.getID());
    }//GEN-LAST:event_btnCloseChatTabActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseChatTab;
    private grimace.client.ChatBox chatBox1;
    private javax.swing.JToolBar chatPanelToolbar;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables

}
