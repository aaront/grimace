/**
 * EquationEditor.java
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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;

import be.ugent.caagt.jmathtex.TeXFormula;
import be.ugent.caagt.jmathtex.TeXConstants;
import be.ugent.caagt.jmathtex.JMathTeXException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * EquationEditor can preview TeX code, and send it back into the messageBox
 * of the current ChatBox.
 */
public class EquationEditor extends javax.swing.JDialog {

    private grimace.client.ChatBox parentFrame;

    /** Creates new form EquationEditor */
    public EquationEditor(grimace.client.ChatBox parent, boolean modal) {
        this.parentFrame = parent;

        initComponents();

        equationInputBox.requestFocusInWindow();

        // Disable using the enter key to do a line break.
        KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
        equationInputBox.getInputMap().put(enter, "none");
    }

    public static File saveEquationImage(String equ) {
        Icon icon;
        try {
            icon = getEquationIcon(equ);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        JLabel jl = new JLabel();
        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        icon.paintIcon(jl, img.getGraphics(), 0, 0);

        int i = 0;
        File imgFile;
        do {
            imgFile = new File(ProgramController.TEMP_FOLDER + "/equ_img" + String.valueOf(i++) + ".png");
        } while (imgFile.exists());
        try {
            ImageIO.write(img, "png", imgFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imgFile;
    }

    public static Icon getEquationIcon(String equ) throws Exception {
        TeXFormula viewer = new TeXFormula(equ);
        Icon viewicon = viewer.createTeXIcon(TeXConstants.STYLE_DISPLAY, 18);
        return viewicon;
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
        equationPreviewBox = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        equationInputBox = new javax.swing.JTextArea();
        btnCancel = new javax.swing.JButton();
        btnPreviewEquation = new javax.swing.JButton();
        btnSendEquation = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        equationPreviewBox.setEditable(false);
        equationPreviewBox.setFocusable(false);
        equationPreviewBox.setMargin(new java.awt.Insets(30, 30, 30, 30));
        jScrollPane1.setViewportView(equationPreviewBox);

        equationInputBox.setColumns(20);
        equationInputBox.setRows(5);
        jScrollPane2.setViewportView(equationInputBox);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnPreviewEquation.setText("Preview Equation");
        btnPreviewEquation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviewEquationActionPerformed(evt);
            }
        });

        btnSendEquation.setText("Send Equation");
        btnSendEquation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendEquationActionPerformed(evt);
            }
        });

        btnHelp.setText("Syntax Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHelp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnPreviewEquation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSendEquation, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSendEquation)
                    .addComponent(btnPreviewEquation)
                    .addComponent(btnCancel)
                    .addComponent(btnHelp))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
}//GEN-LAST:event_btnCancelActionPerformed

    private void btnPreviewEquationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviewEquationActionPerformed
        equationPreviewBox.setText("");
        try {
            TeXFormula viewer = new TeXFormula(equationInputBox.getText());
            Icon viewicon = viewer.createTeXIcon(TeXConstants.STYLE_DISPLAY, 18);
            equationPreviewBox.insertIcon(viewicon);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "That is not a valid " +
                    "equation!\n\nPlease refer to the Syntax Help button for\n"+
                    "help on how to create well-formed math euqations.",
                    "Equation Error",
                    JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_btnPreviewEquationActionPerformed

    private void btnSendEquationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendEquationActionPerformed
        String equation = "<eqn>" + equationInputBox.getText() + "</eqn>";
        
        try {
            TeXFormula viewer = new TeXFormula(equationInputBox.getText());
            ProgramController.sendMessage(equation, parentFrame.getConId());
            this.dispose();
        }
        catch (JMathTeXException e){
            JOptionPane.showMessageDialog(this, "That is not a valid " +
                    "equation!\n\nPlease refer to the Syntax Help button for\n"+
                    "help on how to create well-formed math euqations.",
                    "Equation Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
}//GEN-LAST:event_btnSendEquationActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        JOptionPane.showMessageDialog(this, "Wernicke uses TeX to format " +
                "equations.\n\nTeX has been around a very long time and is " +
                "a standard way of\nrepresenting mathematics with a keyboard." +
                "\n\nA good reference is below (but ignore Mathematics " +
                "Environment,\nwe do that for you already)\n\n" +
                "http://en.wikibooks.org/wiki/LaTeX/Mathematics", "Syntax Help",
                JOptionPane.QUESTION_MESSAGE);
    }//GEN-LAST:event_btnHelpActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EquationEditor dialog = new EquationEditor(new grimace.client.ChatBox(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnPreviewEquation;
    private javax.swing.JButton btnSendEquation;
    private javax.swing.JTextArea equationInputBox;
    private javax.swing.JTextPane equationPreviewBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
