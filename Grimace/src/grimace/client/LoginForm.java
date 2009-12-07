/**
 * LoginForm.java
 *
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

import java.io.File;
import javax.swing.ImageIcon;

/**
 * LoginForm displays the login form, which is the default panel when running
 * the client program.
 */
public class LoginForm extends javax.swing.JPanel {

    /** Creates new form LoginForm */
    public LoginForm() {
        initComponents();

        // Loads the logo into the login screen
        // @TODO: Works for Mac, not so much for Windows
        try {
            ImageIcon logo = new ImageIcon(
                    this.getClass().getClassLoader()
                    .getResource("grimace/client/resources/logo_small.png"));
            logoLabel.setIcon(logo);
        }
        catch (Exception e) {
            System.out.println("Logo is only show when running via the JAR file");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        statusBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        serverAddressField = new javax.swing.JTextField();
        portField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(300, 450));
        setMinimumSize(new java.awt.Dimension(300, 450));

        statusBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Away", "Busy" }));

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel1.setText("USERNAME");

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel2.setText("PASSWORD");

        jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel3.setText("STATUS");

        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerButton.setText("REGISTER");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel4.setText("ADDRESS");

        serverAddressField.setText("127.0.0.1");

        portField.setText("6373");

        jLabel5.setFont(jLabel5.getFont().deriveFont(jLabel5.getFont().getStyle() | java.awt.Font.BOLD, 10));
        jLabel5.setText("PORT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(statusBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 116, Short.MAX_VALUE)
                            .addComponent(passField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(userField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(loginButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(registerButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(serverAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(portField, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                        .addGap(84, 84, 84)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registerButton)
                .addGap(72, 72, 72))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String user = userField.getText();
        String pass = new String(passField.getPassword());
        String status = (String) statusBox.getSelectedItem();
        // @TODO: Pass in status

        ProgramController.setServerAddress(serverAddressField.getText());
        try {
            ProgramController.setServerPort(Integer.parseInt(portField.getText()));
        }
        catch (java.lang.NumberFormatException e) {
            ProgramController.showMessage("Please enter a valid port number.");
        }

        if (user.isEmpty()) {
            ProgramController.showMessage("Please enter a username.");
        } else if (pass.isEmpty()) {
            ProgramController.showMessage("Please enter a password.");
        } else {
            try {
                if(ServerHandler.sendLoginRequest(user,pass,status)) {
                    System.out.println("LoginSuccess");
                    ProgramController.setContactListBox(new ContactPanel());
                } else {
                    System.out.println("LoginFailure");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        ProgramController.setRightPane(new RegistrationForm());
    }//GEN-LAST:event_registerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPasswordField passField;
    private javax.swing.JTextField portField;
    private javax.swing.JButton registerButton;
    private javax.swing.JTextField serverAddressField;
    private javax.swing.JComboBox statusBox;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
