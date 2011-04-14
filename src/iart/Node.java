/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

/**
 *
 * @author Diogo
 */
public class Node {
    BusStop bstop = null;
    String bus = new String();  //autocarro usado para viajar até ao nó actual

    Node(BusStop bs, String busName) {
        bstop = bs;
        bus = busName;
    }
    
    public double heuristica (BusStop goal) {
    	double h=0;
    	switch (Display.mode){
    	case COST:
    		break;
    	case DISTANCE:
    		break;
    	case TIME:
    		break;
    	}
    	
    	
    	return h; 
    }

}
