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
    
    public double heuristica (BusStop goal) { //metodo de calculo das heuristicas
    	double h=0;
    	// distancia = raiz((x^2-x1^2)+(y^2-y1^2))
    	double distancia = java.lang.Math.sqrt((goal.getXCoord()*goal.getXCoord() - bstop.getXCoord()*bstop.getXCoord()) 
    			+ (goal.getYCoord()*goal.getYCoord() - bstop.getYCoord()*bstop.getYCoord()));
		
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
