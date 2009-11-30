/**
 * ContactList.java
 *
 * @author David Marczak
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
import java.util.ArrayList;

/**
 *The class with all the quick options in it.
 * @author David
 */
public class QuickOptions {

    private static ArrayList<Boolean> optionsToggled;

    public QuickOptions(){
      optionsToggled = new ArrayList<Boolean>();
    }

    public static ArrayList<Boolean> toggledList(){
    return optionsToggled;
}

     public void optionChange0(){
        optionsToggled.set(0, Boolean.TRUE);
    }

     public void optionChange1(){
        optionsToggled.set(1, Boolean.TRUE);
    }

     public void optionChange2(){
        optionsToggled.set(2, Boolean.TRUE);
    }
}


