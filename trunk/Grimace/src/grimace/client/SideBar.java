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

    /** Creates new form SideBar */
    public SideBar(ClientConversation conv) {
        initComponents();
        listBox.updateModel(conv.getList());
    }

    public ContactListBox getContactListBox() {
        return contactListBox1;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        contactListBox1 = new grimace.client.ContactListBox();
        quickOptionsBox1 = new grimace.client.QuickOptionsBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        listBox = new grimace.client.ContactListBox();

        contactListBox1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(contactListBox1);

        setLayout(new java.awt.BorderLayout());

        quickOptionsBox1.setMaximumSize(new java.awt.Dimension(32767, 75));
        quickOptionsBox1.setPreferredSize(new java.awt.Dimension(176, 75));
        add(quickOptionsBox1, java.awt.BorderLayout.CENTER);

        listBox.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listBox.setMinimumSize(new java.awt.Dimension(39, 30000));
        jScrollPane1.setViewportView(listBox);

        add(jScrollPane1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private grimace.client.ContactListBox contactListBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private grimace.client.ContactListBox listBox;
    private grimace.client.QuickOptionsBox quickOptionsBox1;
    // End of variables declaration//GEN-END:variables

}
