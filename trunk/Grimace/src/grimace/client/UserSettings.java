/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grimace.client;
import java.awt.Font;
/**
 *
 * @author Aaron
 */
public class UserSettings {
    private Font font;
    private boolean isEqnEditor;


     /**
     * Creates a UserSettings instance with default Font settings
     *
     */
    public UserSettings(){
        isEqnEditor = false;
        font = new Font( "Times New Roman", Font.PLAIN, 12);
    }


      /**
     * Changes the font to that of the string type.  It is assumed type will
       * come from the GraphicsEnvironment to avoid bad string types
     * @param type     Type of font that will replace the old one.
     */
    public void changeFont(String type){
        int style = font.getStyle();
        int size = font.getSize();
        font = new Font(type, style, size);
    }

    /**
     * Changes the font size
     * @param size      The size of the next font text
     */
    public void changeSize(int size){
        int style = font.getStyle();
        String fontType = font.getFontName();
        font = new Font(fontType, style, size);
    }

     /**
     * Toggles between having bold on or off, if italics is on then both
      * will be applied
     */
    public void toggleBold(){
        int style = font.getStyle();
        String fontType = font.getFontName();
        int size = font.getSize();
        if(style == font.PLAIN)
        font = new Font(fontType, font.BOLD, size);
        else if (style == font.BOLD)
        font = new Font(fontType, font.PLAIN, size);
        else if (style == font.ITALIC)
        font = new Font(fontType, font.BOLD+font.ITALIC, size);
    }

         /**
     * Toggles between having italics on or off, if bold is on then both
      * will be applied
     */
    public void toggleItalic(){
        int style = font.getStyle();
        String fontType = font.getFontName();
        int size = font.getSize();
        if(style == font.PLAIN)
        font = new Font(fontType, font.ITALIC, size);
        else if (style == font.BOLD)
        font = new Font(fontType, font.BOLD+font.ITALIC, size);
        else if (style == font.ITALIC)
        font = new Font(fontType, font.PLAIN, size);
    }




}
