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
 * QuickOptions facilitates quick setting of ProgramSettings.
 * @author David
 */
public class QuickOptions {


      boolean eqn;
      boolean log;
      boolean opt;

    public QuickOptions(){
    eqn = false;
    log = false;
    opt = false;
    }

   
     public void optionLog(boolean log){
        if (log == true){
           log = false;
        }
        else{
        log = true;
        }
    }

     public void optionEqn(boolean eqn){
        if (eqn == true){
            eqn = false;
        }
        else{
            eqn = true;
        }

     }

     public void optionChange(boolean opt){
        if (opt == true){
           opt = false;
        }
        else{
        opt = true;
        }
    }

     public boolean eqnChecked (){return eqn;}

     public boolean logChecked (){return log;}

     public boolean optChecked (){return opt;}

}


