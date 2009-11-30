/**
 * UserSettings.java
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
     * Returns the current Font for the settings
     * @return the font that the user is currently using
     */
    public Font getFont(){
        return font;
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
