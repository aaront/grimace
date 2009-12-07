/**
 * SideBar.java
 *
 * @author Aaron Jankun
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

/**
 * SideBar is an element that is displayed in ChatPanel, and itself displays
 * QuickOptionsBox and the Conversation-specific ContactList.
 */
public class SideBar extends javax.swing.JPanel {
    ContactList cList;
    int conId;

    /** Creates new form SideBar */
    public SideBar(ClientConversation conv) {
        initComponents();
        listBox.updateModel(conv.getList());
        conId = conv.getConId();
    }

    public ContactListBox getContactListBox() {
        return listBox;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listBox = new grimace.client.ContactListBox();
        quickOptionsBox1 = new grimace.client.QuickOptionsBox();
        btnAddContactToChat = new javax.swing.JButton();
        btnNewChatWithContacts = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(150, 32767));
        setMinimumSize(new java.awt.Dimension(100, 0));
        setPreferredSize(new java.awt.Dimension(150, 227));

        listBox.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listBox);

        btnAddContactToChat.setText("+");
        btnAddContactToChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddContactToChatActionPerformed(evt);
            }
        });

        btnNewChatWithContacts.setText("New Chat");
        btnNewChatWithContacts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewChatWithContactsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(quickOptionsBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnAddContactToChat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNewChatWithContacts)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddContactToChat)
                    .addComponent(btnNewChatWithContacts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quickOptionsBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddContactToChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddContactToChatActionPerformed
        ContactSelectDialog dialog = new ContactSelectDialog(conId);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnAddContactToChatActionPerformed

    private void btnNewChatWithContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewChatWithContactsActionPerformed
        Object[] objects = listBox.getSelectedValues();
        Contact[] contacts = new Contact[objects.length];
        for (int i = 0; i < objects.length; i++) {
            contacts[i] = (Contact) objects[i];
        }
        ProgramController.sendConvoRequest(contacts);
    }//GEN-LAST:event_btnNewChatWithContactsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddContactToChat;
    private javax.swing.JButton btnNewChatWithContacts;
    private javax.swing.JScrollPane jScrollPane1;
    private grimace.client.ContactListBox listBox;
    private grimace.client.QuickOptionsBox quickOptionsBox1;
    // End of variables declaration//GEN-END:variables

}
