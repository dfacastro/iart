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
    String bus = new String("");  //autocarro usado para viajar até ao nó actual
    StopSchedule arrival = null;
    Double cost = -1.0;

    Vector<Node> children = new Vector<Node>();
    Node parent = null;

    Node(BusStop bs, String busName, StopSchedule arr) {
        bstop = bs;
        bus = busName;
        arrival = arr;
    }

    Node (BusStop bs, StopSchedule now) {
        bstop = bs;
        arrival = now;
    }

    public StopSchedule getArrivalTime() {
        return arrival;
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

    public String getBus() {
        return bus;
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

            if (next == null)
                continue;

            //visita o ramo no sentido ascendente e verifica se a paragem ja foi visitada
            while(n != null) {
                if (n.getBusStop().getName().equals(next.getName())) {
                    alreadyVisited = true;
                    break;
                }
                n = n.getParent();
            }

            //tempo de chegada do autocarro bus a' paragem actual
            StopSchedule departure = bstop.departure(buses[i], arrival);
            //tempo de chegada do autocarro bus ao destino
            StopSchedule next_arrival = next.departure(buses[i], departure);
            if(!alreadyVisited)
                nodes.add(new Node( next, buses[i], next_arrival));

        }
        
        return nodes;
    }

    /**
     * Avalia o custo do no'
     */
    public void eval_cost() {

        Node n = parent;
        while(n != null) {
            
        }
    }

}
