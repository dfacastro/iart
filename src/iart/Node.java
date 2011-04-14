/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

import java.util.Vector;

/**
 *
 * @author Diogo
 */
public class Node {
    BusStop bstop = null;
    String bus = new String();  //autocarro usado para viajar até ao nó actual

    Vector<Node> children = new Vector<Node>();
    Node parent = null;

    Node(BusStop bs, String busName) {
        bstop = bs;
        bus = busName;
    }

    /**
     * Adiciona um nó filho.
     * Paralelamente, esta instância passa também a ser o pai do nó indicado.
     * @param child
     */
    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }

    private void setParent(Node n) {
        parent = n;
    }

    public Node getParent() {
        return parent;
    }

    public BusStop getBusStop() {
        return bstop;
    }

    /**
     * retorna todos os nós filhos possíveis (excluindo as paragens ja visitadas no ramo)
     * @return
     */
    Vector<Node> expand() {
        Vector<Node> nodes = new Vector<Node>();

        //autocarros que passam nesta paragem
        String[] buses = bstop.getBuses();

        //possíveis destinos
        for(int i = 0; i < buses.length; i++) {
            BusStop next = bstop.getBusNextStop(buses[i]);
            Boolean alreadyVisited = false;
            Node n = parent;

            //visita o ramo no sentido ascendente e verifica se a paragem ja foi visitada
            while(n != null) {
                if (n.getBusStop().getName().equals(next.getName())) {
                    alreadyVisited = true;
                    break;
                }
                n = n.getParent();
            }

            if(!alreadyVisited)
                nodes.add(new Node( bstop.getBusNextStop(buses[i]), buses[i]));
        }
        
        return nodes;
    }

}
