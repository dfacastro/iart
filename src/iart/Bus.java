/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Diogo
 */
public class Bus {
   //horário para cada paragem
   public HashMap<String, Vector<StopSchedule>> stops = new HashMap<String, Vector<StopSchedule>>();

   //percurso
   public Vector<String> route = new Vector<String>();

   //numero do autocarro
   int busID = -1;

   Bus () {
        
   }
   
   Bus(HashMap<String, Vector<StopSchedule>> stopsT, int busIDT, Vector<String> route) {
       stops = stopsT;
       busID = busIDT;
   }
}

