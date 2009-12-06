/**
 * ContactPanel.java
 *
 * @author Aaron Jankun
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

import javax.swing.DefaultListModel;

/**
 * ContactPanel is a container for a ContactListBox, which displays an account's
 * contact list.
 */
public class ContactPanel extends javax.swing.JPanel {

    /** Creates new form ContactListBox */
    public ContactPanel() {
        initComponents();
        ((ContactListBox)listBox).updateModel(ProgramController.getAccount().getContactList());
    }

    /**
     * Updates the ContactList in the contactListBox
     */
    public void updateContactListView() {
        ((ContactListBox)listBox).updateModel(ProgramController.getAccount().getContactList());
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
        addButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(250, 450));

        jScrollPane1.setHorizontalScrollBar(null);

        listBox.setModel(new DefaultListModel());
        listBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listBoxMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listBox);

        addButton.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        addButton.setText("+");
        addButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        addButton.setMaximumSize(new java.awt.Dimension(40, 27));
        addButton.setMinimumSize(new java.awt.Dimension(40, 27));
        addButton.setPreferredSize(new java.awt.Dimension(40, 27));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Lucida Grande", 1, 13));
        deleteButton.setText("-");
        deleteButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        deleteButton.setMaximumSize(new java.awt.Dimension(40, 27));
        deleteButton.setMinimumSize(new java.awt.Dimension(40, 27));
        deleteButton.setPreferredSize(new java.awt.Dimension(40, 27));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        optionsButton.setText("Options");
        optionsButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        optionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                .addComponent(optionsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optionsButton)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        AddContactDialog dlg = new AddContactDialog();
        dlg.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        ProgramController.logout();
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        Object[] conList = listBox.getSelectedValues();
        for (Object con : conList) {
            ProgramController.sendDeleteContactRequest(((Contact)con).getUserName());
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void listBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listBoxMouseClicked
        if (evt.getClickCount() == 2) {
            Object[] objects = listBox.getSelectedValues();
            Contact[] contacts = new Contact[objects.length];
            for (int i = 0; i < objects.length; i++) {
                contacts[i] = (Contact) objects[i];
            }
            ProgramController.sendConvoRequest(contacts);
        }
    }//GEN-LAST:event_listBoxMouseClicked

    private void optionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButtonActionPerformed
        ProgramWindow.showOptionsTab();
    }//GEN-LAST:event_optionsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listBox;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton optionsButton;
    // End of variables declaration//GEN-END:variables

}
