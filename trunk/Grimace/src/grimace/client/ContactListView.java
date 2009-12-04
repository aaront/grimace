/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.client;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author vs
 */
public class ContactListView extends JList {
    ContactList cList;

    public ContactListView() {
        DefaultListSelectionModel lsm = new DefaultListSelectionModel();
        lsm.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setSelectionModel(lsm);
    }

    public void updateModel() {
        DefaultListModel m = new DefaultListModel();
        for (int i = 0; i < cList.getList().size(); i++) {
            m.addElement(cList.getList().get(i));
        }
        setModel(m);
    }

    public void updateModel(ContactList cList) {
        this.cList = cList;
        updateModel();
    }
}
