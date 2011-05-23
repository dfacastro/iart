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
    double cost = -1.0;
    double heuristic = -1.0;

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
    
    public double heuristica (BusStop goal) { //metodo de calculo das heuristicas
    	double h=0;
    	// distancia = raiz((x^2-x1^2)+(y^2-y1^2))
    	double distancia = java.lang.Math.hypot(
    			java.lang.Math.abs(goal.getXCoord() - bstop.getXCoord()), 
    			java.lang.Math.abs(goal.getYCoord() - bstop.getYCoord()));
		
    	switch (Display.mode){
    	case DISTANCE:
    		h = distancia;
    		break;
    	case COST:
    		if(parent == null){
    			boolean passa2 = false; //se um autocarro da paragem passa na final
    			boolean passa = false; //se o autocarro actual passa na final
    			
    			for(int i=0; i < bstop.getBuses().length; i++)
    				for(int j=0; j < goal.getBuses().length; j++)
    					if(bstop.getBuses()[i].equals(goal.getBuses()[j]))
    						passa2 = true;	
    			
    			for(int j=0; j < goal.getBuses().length; j++)
					if(bus.equals(goal.getBuses()[j]))
						passa = true;
    			
    			if(passa) h = 0; //esta num autocarro e nao apanha mais nenhum
    			else if(passa2 && !passa) h = 1; //um dos autocarros da paragem passa na paragem final
    			else h = 2; //nenhum autocarro passa na paragem final
    		}
    		break;
    	case TIME:
    		h = distancia*60 / 50;
    		break;
    	}
    	
    	heuristic = h;
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

    public double getCost() {
        return cost;
    }
    
    public double getHeuristic() {
        return heuristic;
    }

    /**
     * retorna todos os nós filhos possíveis (excluindo as paragens ja visitadas no ramo)
     * @return
     */
    public Vector<Node> expand() {
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
        if (parent == null) {
            cost = 0.0;
            return;
        }

        double previous_cost = (parent.getCost() < 0.0)? 0.0 : parent.getCost();
        double this_cost = 0.0;

        switch ( Display.mode) {
            case DISTANCE:
                this_cost = Math.hypot(bstop.getXCoord() - parent.getBusStop().getXCoord(),
                        bstop.getYCoord() - parent.getBusStop().getYCoord());
                break;
            case COST:
                // se os autocarros das duas ultimas arestas forem diferentes -> houve transbordo
                if (!parent.getBus().equals(bus))
                    this_cost = 1.0;
                break;
            case TIME:
                this_cost = StopSchedule.diff(arrival, parent.getArrivalTime());
                break;
        }

        cost = previous_cost + this_cost;

    }
    
    
    

}
