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

}
