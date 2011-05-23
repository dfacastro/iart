package iart;

import java.util.Vector;

public class aStar {
	private static Vector<Node> closedSet = new Vector<Node>();
	private static Vector<Node> openSet = new Vector<Node>();
	
	private static Node lowerF(Vector<Node> openSet){
		//TODO: funcao para calcular nó com menor heuristica
		if(openSet.size() == 0) return null;
		Node n = openSet.get(0);
		for(int i = 1; i<openSet.size(); i++){
			if((n.heuristic + n.cost) > (openSet.get(i).heuristic+ openSet.get(i).cost))
				n = openSet.get(i);
		}
		return n;
	}
	
	
	public static Vector<Node> aStarAlgorithm(Node start, BusStop goal){

		start.heuristica(goal);
		start.eval_cost();
		openSet.add(start);
		
		while(openSet.size()!=0){
			
			for(int i = 0; i < openSet.size(); i++)
				System.out.println("COST: " + openSet.get(i).cost + "  "+ openSet.get(i).heuristic + "  bus: " + openSet.get(i).getBusStop().getName());
			//calcular nó no  openset com menor heuristica
			Node node = lowerF(openSet);
			
			openSet.remove(node);
			System.out.println("Estou no "+node.getBusStop().getName());
			//se estamos no destino, retornar caminho.
			if(node.getBusStop() == goal){
				System.out.println("Encontrei o destino ");
				Vector<Node> path = new Vector<Node>();
				
				Node val = node;
				while(val!=null){
					path.add(val);
					val = val.getParent();
				}
				return path;
				

			}
			closedSet.add(node);
			
			Vector<Node> neighbors = node.expand();
			for(int i = 0; i<neighbors.size(); i++){
				Node son = neighbors.get(i);
				System.out.println("Count: "+i);
				//verificar se já passou naquela paragem
				Node val = node;
				boolean cont = false;
				while(val!=null){
					//se a paragem já existir naquele caminho, discarta-la!
					if(val.getBusStop() == son.getBusStop()){
						cont = true;
						break;
					}
					val = val.getParent();
				}
				if(cont)
					continue;
				
				//adicionar o filho ao openset e calcular a heuristica e custo

				node.addChild(son);
				son.heuristica(goal);
				son.eval_cost();
				
				openSet.add(son);
			}
		}
			return null;
	}
}
