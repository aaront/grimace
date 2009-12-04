/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChatBox.java
 *
 * Created on Dec 3, 2009, 8:40:04 PM
 */

package grimace.client;

import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class ChatBox extends javax.swing.JPanel {

    private Color currentFontColour;
    private Font currentFont;

    /** Creates new form ChatBox */
    public ChatBox() {
        currentFont = new Font(Account.DEFAULT_FONT, Font.BOLD+Font.ITALIC, Account.DEFAULT_FONT_SIZE);

        initComponents();

        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] availableFonts = e.getAvailableFontFamilyNames();
        Integer[] fontSizes = {8, 9, 10, 11, 12, 13, 14, 16, 18, 24, 36, 48, 72, 96};

        fontSelector.setModel(new javax.swing.DefaultComboBoxModel(availableFonts));
        sizeSelector.setModel(new javax.swing.DefaultComboBoxModel(fontSizes));

        // @TODO: Fill in with proper stuff when ProgramController is all hooked up and shit
        fontSelector.setSelectedItem(Account.DEFAULT_FONT);
        sizeSelector.setSelectedItem(Account.DEFAULT_FONT_SIZE);
        btnColour.setForeground(Account.DEFAULT_FONT_COLOUR);

        // @TODO: Will be eventually replaced by getting the font from the account.
        loadFontProperties(currentFont);

        // Disable using the enter key to do a line break. Enter will be used to
        // send the message.
        KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
        messageBox.getInputMap().put(enter, "none");
    }

    private void loadFontProperties(Font font) {
        if (font.isBold()) {
            bolden.setSelected(true);
        }
        if (font.isItalic()) {
            italicize.setSelected(true);
        }

        messageBox.setFont(font);
    }

    /**
     * Gets the current font being used in the messageBox
     * @return the font of the messageBox
     */
    public Font getCurrentFont() {
        return currentFont;
    }

    /**
     * Gets the current font color being used in the messageBox
     * @return
     */
    public Color getCurrentFontColour() {
        return currentFontColour;
    }

    /**
     * Gets the instance of the chat display box
     * @return the chat display box (where the conversation takes place)
     */
    public JTextPane getChatDisplayBox() {
        return chatDisplayBox;
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
        chatDisplayBox = new javax.swing.JTextPane();
        toolbar = new javax.swing.JToolBar();
        bolden = new javax.swing.JToggleButton();
        italicize = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        fontSelector = new javax.swing.JComboBox();
        sizeSelector = new javax.swing.JComboBox();
        btnColour = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnAddEquation = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageBox = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(400, 450));
        setPreferredSize(new java.awt.Dimension(600, 170));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        chatDisplayBox.setEditable(false);
        chatDisplayBox.setFocusable(false);
        jScrollPane2.setViewportView(chatDisplayBox);

        add(jScrollPane2);

        toolbar.setBorder(null);
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setBorderPainted(false);
        toolbar.setMaximumSize(new java.awt.Dimension(1000, 36));
        toolbar.setMinimumSize(new java.awt.Dimension(500, 36));
        toolbar.setPreferredSize(new java.awt.Dimension(500, 36));

        bolden.setFont(new java.awt.Font("Lucida Grande", 0, 14));
        bolden.setText("<html><strong>B</strong>");
        bolden.setFocusable(false);
        bolden.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bolden.setMaximumSize(new java.awt.Dimension(30, 28));
        bolden.setMinimumSize(new java.awt.Dimension(30, 28));
        bolden.setPreferredSize(new java.awt.Dimension(30, 28));
        bolden.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bolden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldenActionPerformed(evt);
            }
        });
        toolbar.add(bolden);

        italicize.setFont(new java.awt.Font("Lucida Grande", 0, 14));
        italicize.setText("<html><i>i</i>");
        italicize.setFocusable(false);
        italicize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        italicize.setMaximumSize(new java.awt.Dimension(30, 28));
        italicize.setMinimumSize(new java.awt.Dimension(30, 28));
        italicize.setPreferredSize(new java.awt.Dimension(30, 28));
        italicize.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        italicize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                italicizeActionPerformed(evt);
            }
        });
        toolbar.add(italicize);
        toolbar.add(jSeparator2);

        fontSelector.setMaximumSize(new java.awt.Dimension(170, 27));
        fontSelector.setMinimumSize(new java.awt.Dimension(170, 27));
        fontSelector.setPreferredSize(new java.awt.Dimension(170, 27));
        fontSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fontSelectorActionPerformed(evt);
            }
        });
        toolbar.add(fontSelector);

        sizeSelector.setMaximumSize(new java.awt.Dimension(70, 27));
        sizeSelector.setMinimumSize(new java.awt.Dimension(70, 27));
        sizeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeSelectorActionPerformed(evt);
            }
        });
        toolbar.add(sizeSelector);

        btnColour.setText("Color");
        btnColour.setFocusable(false);
        btnColour.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnColour.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColourActionPerformed(evt);
            }
        });
        toolbar.add(btnColour);
        toolbar.add(jSeparator1);

        btnAddEquation.setText("Add Equation");
        btnAddEquation.setFocusable(false);
        btnAddEquation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddEquation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(btnAddEquation);

        add(toolbar);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(32767, 50));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 50));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(244, 50));

        messageBox.setColumns(20);
        messageBox.setLineWrap(true);
        messageBox.setMinimumSize(new java.awt.Dimension(400, 16));
        messageBox.setPreferredSize(new java.awt.Dimension(400, 16));
        messageBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageBoxKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(messageBox);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnColourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColourActionPerformed
        // TODO add your handling code here:
        currentFontColour = JColorChooser.showDialog(null, "Choose a new font color:", btnColour.getForeground());
        btnColour.setForeground(currentFontColour);
        messageBox.setForeground(currentFontColour);
}//GEN-LAST:event_btnColourActionPerformed

    private void boldenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldenActionPerformed
        if (bolden.isSelected() == true) {
            currentFont = currentFont.deriveFont(currentFont.getStyle() | Font.BOLD);
        } else {
            currentFont = currentFont.deriveFont(currentFont.getStyle() ^ Font.BOLD);
        }
        messageBox.setFont(currentFont);
}//GEN-LAST:event_boldenActionPerformed

    private void italicizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_italicizeActionPerformed
        if (italicize.isSelected() == true) {
            currentFont = currentFont.deriveFont(currentFont.getStyle() | Font.ITALIC);
        } else {
            currentFont = currentFont.deriveFont(currentFont.getStyle() ^ Font.ITALIC);
        }
        messageBox.setFont(currentFont);
    }//GEN-LAST:event_italicizeActionPerformed

    private void sizeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeSelectorActionPerformed
        JComboBox sel = (JComboBox)evt.getSource();
        Integer fs = (Integer)sel.getSelectedItem();
        Float fsfl = fs.floatValue();
        currentFont = currentFont.deriveFont(fsfl);
        messageBox.setFont(currentFont);
    }//GEN-LAST:event_sizeSelectorActionPerformed

    private void fontSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontSelectorActionPerformed
        JComboBox sel = (JComboBox)evt.getSource();
        String fs = (String)sel.getSelectedItem();
        currentFont = new Font(fs, currentFont.getStyle(), currentFont.getSize());
        messageBox.setFont(currentFont);
    }//GEN-LAST:event_fontSelectorActionPerformed

    private void messageBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageBoxKeyPressed
        // Checks and sees if the key pressed is "enter", if it is, sends away
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            // @TODO actually send the contents of the messagebox away.
            System.out.println(messageBox.getText());
            messageBox.setText("");
        }
    }//GEN-LAST:event_messageBoxKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bolden;
    private javax.swing.JButton btnAddEquation;
    private javax.swing.JButton btnColour;
    private javax.swing.JTextPane chatDisplayBox;
    private javax.swing.JComboBox fontSelector;
    private javax.swing.JToggleButton italicize;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTextArea messageBox;
    private javax.swing.JComboBox sizeSelector;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

}
