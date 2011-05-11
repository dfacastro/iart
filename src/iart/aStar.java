package iart;

import java.util.Vector;

public class aStar {
	
	private Node lowerHeuristic(Vector<Node> openSet){
		//TODO: funcao para calcular nó com menor heuristica
		return new Node(null, null, null);
	}
	
	
	public void aStarAlgorithm(Node start, Node goal){
		Vector<Node> closedSet = new Vector<Node>();
		Vector<Node> openSet = new Vector<Node>();
		openSet.add(start);
		
		
		
		while(openSet.size()!=0){
			//calcular nó no  openset com menor heuristica
			Node node = lowerHeuristic(openSet); //TODO: 
			
			if(node.equals(goal)){ //TODO: uso equals?
				return; //TODO: a definir retorno
			}
			
			openSet.remove(node);
			closedSet.add(node);
			
			Vector<Node> neighbors = node.expand();
			for(int i = 0; i<neighbors.size(); i++){
				Node son = neighbors.get(i);
				//se o nó já existe no closedSet (se já foi visto)
				if(closedSet.contains(son)){
					//TODO: definir o que fazer
					continue;
				}
				
			}/*
			 tentative_g_score := g_score[x] + dist_between(x,y)
			 
             if y not in openset
                 add y to openset
                 tentative_is_better := true
             else if tentative_g_score < g_score[y]
                 tentative_is_better := true
             else
                 tentative_is_better := false
 
             if tentative_is_better = true
                 came_from[y] := x
                 g_score[y] := tentative_g_score
                 h_score[y] := heuristic_cost_estimate(y, goal)
                 f_score[y] := g_score[y] + h_score[y]
			*/
		}
		
		
	}
}
