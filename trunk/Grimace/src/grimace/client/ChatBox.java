/**
 * ChatBox.java
 *
 * @author Aaron Toth
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
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * ChatBox is the panel with the message display box, the toolbar, and the
 * text entry field.
 */
public class ChatBox extends javax.swing.JPanel {

    private Color currentFontColour;
    private Font currentFont;
    private int conId;

    /** Creates new form ChatBox */
    public ChatBox() {
        initComponents();
    }

    public void initChatBox() {
        currentFont = ProgramController.getAccount().getFont();
        currentFontColour = ProgramController.getAccount().getFontColour();
        
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] availableFonts = e.getAvailableFontFamilyNames();
        Integer[] fontSizes = {8, 9, 10, 11, 12, 13, 14, 16, 18, 24, 36, 48};

        fontSelector.setModel(new javax.swing.DefaultComboBoxModel(availableFonts));
        sizeSelector.setModel(new javax.swing.DefaultComboBoxModel(fontSizes));

        // @TODO: Will be eventually replaced by getting the font from the account.
        loadFontProperties(currentFont, currentFontColour);

        // Disable using the enter key to do a line break. Enter will be used to
        // send the message.
        KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
        messageBox.getInputMap().put(enter, "none");
    }

    /**
     * Sets the conversation ID
     * @param id conversation ID
     */
    public void setConId(int id) {
        conId = id;
    }

    /**
     * Gets the conversation ID
     * @return the conversation ID
     */
    public int getConId() {
        return conId;
    }

    /**
     * Loads font style settings into various elements on the ChatBox
     * @param font the font to use
     * @param colour the colour of the font
     */
    public void loadFontProperties(Font font, Color colour) {
        // Sets up the toolbar
        fontSelector.setSelectedItem(font.getFamily());
        sizeSelector.setSelectedItem(font.getSize());
        btnColour.setForeground(colour);

        // Sets the state of the toolbar toggle buttons
        if (font.isBold()) {
            bolden.setSelected(true);
        }
        if (font.isItalic()) {
            italicize.setSelected(true);
        }

        // Sets the style of the messagebox
        messageBox.setFont(font);
        messageBox.setForeground(colour);
    }

    /**
     * Gets the current font being used in the messageBox
     * @return the font of the messageBox
     */
    public Font getCurrentFont() {
        return currentFont;
    }

    /**
     * Gets the current font colour being used in the messageBox
     * @return the current font colour of the message box
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

    /**
     * Gets the instance of the chat display box scollpane
     * @return the chat display box pane
     */
    public javax.swing.JScrollPane getChatDisplayBoxScrollPane() {
        return chatDisplayBoxScroll;
    }

    /**
     * Gets the instance of the message box
     * @return the message box
     */
    public JTextArea getMessageBox() {
        return messageBox;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatDisplayBoxScroll = new javax.swing.JScrollPane();
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
        btnAddFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageBox = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(465, 420));
        setPreferredSize(new java.awt.Dimension(600, 170));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        chatDisplayBoxScroll.setAutoscrolls(true);

        chatDisplayBox.setContentType("text/html");
        chatDisplayBox.setEditable(false);
        chatDisplayBox.setText("");
        chatDisplayBoxScroll.setViewportView(chatDisplayBox);

        add(chatDisplayBoxScroll);

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
        btnColour.setMaximumSize(new java.awt.Dimension(100, 27));
        btnColour.setMinimumSize(new java.awt.Dimension(0, 27));
        btnColour.setPreferredSize(new java.awt.Dimension(50, 27));
        btnColour.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnColour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColourActionPerformed(evt);
            }
        });
        toolbar.add(btnColour);
        toolbar.add(jSeparator1);

        btnAddEquation.setText("Equation");
        btnAddEquation.setFocusable(false);
        btnAddEquation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddEquation.setMaximumSize(new java.awt.Dimension(100, 27));
        btnAddEquation.setMinimumSize(new java.awt.Dimension(0, 27));
        btnAddEquation.setPreferredSize(new java.awt.Dimension(70, 27));
        btnAddEquation.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddEquation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEquationActionPerformed(evt);
            }
        });
        toolbar.add(btnAddEquation);

        btnAddFile.setText("File");
        btnAddFile.setFocusable(false);
        btnAddFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddFile.setMaximumSize(new java.awt.Dimension(100, 27));
        btnAddFile.setMinimumSize(new java.awt.Dimension(0, 27));
        btnAddFile.setPreferredSize(new java.awt.Dimension(40, 27));
        btnAddFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFileActionPerformed(evt);
            }
        });
        toolbar.add(btnAddFile);

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
        ProgramController.getAccount().setFontColour(currentFontColour);

        // Update fonts
        try {
            ServerHandler.sendFontUpdateRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_btnColourActionPerformed

    private void boldenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldenActionPerformed
        if (bolden.isSelected() == true) {
            currentFont = currentFont.deriveFont(currentFont.getStyle() | Font.BOLD);
        } else {
            currentFont = currentFont.deriveFont(currentFont.getStyle() ^ Font.BOLD);
        }
        messageBox.setFont(currentFont);
        ProgramController.getAccount().setFont(currentFont);

        // Update fonts
        try {
            ServerHandler.sendFontUpdateRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_boldenActionPerformed

    private void italicizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_italicizeActionPerformed
        if (italicize.isSelected() == true) {
            currentFont = currentFont.deriveFont(currentFont.getStyle() | Font.ITALIC);
        } else {
            currentFont = currentFont.deriveFont(currentFont.getStyle() ^ Font.ITALIC);
        }
        messageBox.setFont(currentFont);
        ProgramController.getAccount().setFont(currentFont);

        // Update fonts
        try {
            ServerHandler.sendFontUpdateRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_italicizeActionPerformed

    private void sizeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeSelectorActionPerformed
        JComboBox sel = (JComboBox)evt.getSource();
        Integer fs = (Integer)sel.getSelectedItem();
        Float fsfl = fs.floatValue();
        currentFont = currentFont.deriveFont(fsfl);
        messageBox.setFont(currentFont);
        ProgramController.getAccount().setFont(currentFont);

        // Update fonts
        try {
            ServerHandler.sendFontUpdateRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_sizeSelectorActionPerformed

    private void fontSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fontSelectorActionPerformed
        JComboBox sel = (JComboBox)evt.getSource();
        String fs = (String)sel.getSelectedItem();
        currentFont = new Font(fs, currentFont.getStyle(), currentFont.getSize());
        messageBox.setFont(currentFont);
        ProgramController.getAccount().setFont(currentFont);

        // Update fonts
        try {
            ServerHandler.sendFontUpdateRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_fontSelectorActionPerformed

    private void messageBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageBoxKeyPressed
        // Checks and sees if the key pressed is "enter", if it is, sends away
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            int n = ProgramController.getChatPanel(conId).getClientConversation().getList().getSize();
            if (n == 0) {
                ProgramController.postNotification(conId, "There are no other users in this conversation.");
            }

            // Sets the default font weight and style for the message
            String fontWeight = "normal";
            String fontStyle = "normal";

            if (currentFont.isBold() == true) {
                fontWeight = "bold";
            }

            if (currentFont.isItalic() == true) {
                fontStyle = "italic";
            }

            String hexColour = String.format("%02X%02X%02X",
                    currentFontColour.getRed(),
                    currentFontColour.getGreen(),
                    currentFontColour.getBlue());
            
            String sendString = messageBox.getText().replaceAll("\\<.*?\\>", "");

            String message = String.format("<span style='color: #%s; font: %dpx %s, sans-serif; font-weight: %s; font-style: %s;'>%s</span>",
                    hexColour, currentFont.getSize(),
                    '"' + currentFont.getFamily() + '"', fontWeight, fontStyle,
                    sendString);

            ProgramController.sendMessage(message, conId);
            System.out.println(message);

            messageBox.setText("");
        }
    }//GEN-LAST:event_messageBoxKeyPressed

    private void btnAddEquationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEquationActionPerformed
        EquationEditor ee = new EquationEditor(this, false);
        ee.setVisible(true);
    }//GEN-LAST:event_btnAddEquationActionPerformed

    private void btnAddFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFileActionPerformed
       // @TODO: Finish file transfer.
       JFileChooser chooseFileToAdd = new JFileChooser();

       int ret = chooseFileToAdd.showOpenDialog(this);

       if (ret == JFileChooser.APPROVE_OPTION) {
           File file = chooseFileToAdd.getSelectedFile();

           ArrayList<Contact> cList = ProgramController.getChatPanel(conId).getContactListBox().getList().getList();

           for (Contact con : cList) {
               try {
                    ServerHandler.sendFileTransferRequest(file.getAbsolutePath(), con.getUserName());
               }
               catch (Exception e) {
                   e.printStackTrace();
               }
            }
       }
    }//GEN-LAST:event_btnAddFileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bolden;
    private javax.swing.JButton btnAddEquation;
    private javax.swing.JButton btnAddFile;
    private javax.swing.JButton btnColour;
    private javax.swing.JTextPane chatDisplayBox;
    private javax.swing.JScrollPane chatDisplayBoxScroll;
    private javax.swing.JComboBox fontSelector;
    private javax.swing.JToggleButton italicize;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTextArea messageBox;
    private javax.swing.JComboBox sizeSelector;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

}
