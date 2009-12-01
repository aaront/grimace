/**
 * Account.java
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
import java.awt.Color;


public class Account {

    public static final String DEFAULT_FONT = "Times New Roman";
    public static final int DEFAULT_FONT_SIZE = 12;
    public static final Color DEFAULT_FONT_COLOUR = Color.BLACK;

    private Font font;
    private Color fontColour;
    private ContactList cList;
    private String userName;
    private String displayName;



    /**
     * Creates a Account instance with a selected user name
     * @param iUserName     Desired User name for the account.
     */
    public Account(String iUserName){

        cList = new ContactList();
        userName = iUserName;
        displayName = userName;
        font = new Font( DEFAULT_FONT, Font.PLAIN, DEFAULT_FONT_SIZE);
        fontColour = DEFAULT_FONT_COLOUR;
    }

     /**
     * Creates a Account instance with a selected user name
     * @param iUserName     Desired User named for the account.
     * @param iDisplayName     Desired Display name for the account
     */
    public Account (String iUserName, String iDisplayName){
        cList = new ContactList();
        userName = iUserName;
        displayName = iDisplayName;
        font = new Font( DEFAULT_FONT, Font.PLAIN, DEFAULT_FONT_SIZE);
        fontColour = DEFAULT_FONT_COLOUR;
    }

     /**
     * Gives the user name for an account.
     * @return Returns the userName string for an account.
     */
     public String getUserName(){
         return userName;         
     }

     /**
     * Gives the display name for an account.
     * @return Returns the displayName string for an account.
     */
     public String getDisplayName(){
         return displayName;
     }

     /**
     * Gives the contact list for an account.
     * @return Returns the ContactList for an account.
     */
     public ContactList getContactList(){
         return cList;
     }

     /**
     * Sets the contact list for an account.
     * @param list to replace old cList.
     */
     public void setContactList(ContactList list){
         cList = list;
     }

     /**
     * Changes the displayName to name.
     * @param String to replace old displayName.
     */
     public void setDisplayName(String name){
         displayName = name;
     }



    /**
     * Returns the current Font Colour for the settings
     * @return the font colour that the user is currently using
     */
    public Color getFontColour(){
        return fontColour;
    }
    
    /**
     * Changes the fontColour to colour.
     * @param colour to replace old fontColour.
     */
     public void setFontColour(Color colour){
         fontColour = colour;
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


