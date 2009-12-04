/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.client;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author vs
 */
public class ChatBoxTest extends JFrame implements Runnable {
    public void run() {
        setContentPane(new ChatBox());
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ChatBoxTest());
    }
}
