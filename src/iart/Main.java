package iart;
/*
import java.io.File;
import org.jgraph.JGraph;

public class Main {


 // Usage: java SVGGraph gxl-file svg-file
  public static void main(String[] args) {
   
    // Construct the graph to hold the ettributes
    JGraph graph = new JGraph(new DefaultGraphModel());
    // Read the GXL file into the model
    read(new File(args[0]), graph.getModel());
    // Apply Layout
    layout(graph);
    // Resize (Otherwise not Visible)
    graph.setSize(graph.getPreferredSize());
   
    // Write the SVG file
    write(graph, out);
    
  }
}

*/

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

public class Main {

    //public static HashMap<String, Integer[]> busStops;
    //public static Vector<Bus> buses;
    public double ticket_price;


    //
    public static Vector<BusStop> busStops = new Vector<BusStop>();

    public static void main(String[] args) {

        //JGraphAdapterDemo d = new JGraphAdapterDemo();
        //d.init();

        Config c = new Config();
        c.load();

        printFrames();

 /*       ArrayList<Integer> a = new ArrayList<Integer>();
        //LinkedList<Integer> a = new LinkedList<Integer>();
        a.add(2); a.add(1); a.add(9); a.add(5);
        
        System.out.println("TESTE");
        for(int i = 0; i < a.size(); i++)
            System.out.println(a.get(i));

        Integer[] ai = new Integer[1];
        ai = a.toArray(ai);

        Arrays.sort(ai);
        a = new ArrayList<Integer>( Arrays.asList(ai));
*/

        /*for(int i = 0; i < busStops.size(); i++)
            busStops.get(i).print();*/

        //buses = c.getBuses();
        //busStops = c.getBusStops();

/*        Vector<String> r = buses.get(0).route;
        for(int i = 0; i < r.size(); i++)
            System.out.println("ROUTE: " + r.get(i));*/

        MyGraph mg = new MyGraph();
        mg.init();
        //mg.addVertexes(busStops);
        mg.show(true);

      /*  GraphModel model = new DefaultGraphModel();

        GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
        JGraph graph = new JGraph(model, view);
        DefaultGraphCell[] cells = new  DefaultGraphCell[3];
        cells[0] = new DefaultGraphCell(new String("Hello"));
        GraphConstants.setBounds(cells[0].getAttributes(), new Rectangle2D.Double(20, 20, 40, 20));
        GraphConstants.setGradientColor(cells[0].getAttributes(), Color.orange);
        GraphConstants.setOpaque(cells[0].getAttributes(), true);
        GraphConstants.setSizeable(cells[0].getAttributes(), false);

        DefaultPort port0 = new DefaultPort();
        cells[0].add(port0);

        cells[1] = new DefaultGraphCell(new String("World"));


        GraphConstants.setBounds(cells[1].getAttributes(), new Rectangle2D.Double(140, 140, 40, 20));
        GraphConstants.setGradientColor(
                cells[1].getAttributes(),
                Color.red);
        GraphConstants.setOpaque(cells[1].getAttributes(), true);
        DefaultPort port1 = new DefaultPort();
        cells[1].add(port1);
        DefaultEdge edge = new DefaultEdge();
        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[1].getChildAt(0));
        cells[2] = edge;

        int arrow = GraphConstants.ARROW_CLASSIC;
        GraphConstants.setLineEnd(edge.getAttributes(), arrow);
        GraphConstants.setEndFill(edge.getAttributes(), true);
        graph.getGraphLayoutCache().insert(cells);



        JFrame frame = new JFrame();

        frame.getContentPane().add(new JScrollPane(graph));
        frame.pack();
        frame.setVisible(true);*/
    }


    /**
     * Actualiza a informação do horário de uma paragem já existente
     * @param busStopID: id da paragem a actualizar
     * @param busID: id do autocarro cujo horário se pretende actualizar
     * @param bsi: informação do horário
     */
    public static void addBusStopInfo(String busStopID, String busID, BusStopInfo bsi) {
        
        for(int i = 0; i < busStops.size(); i++) {
            if(busStops.get(i).getName().equals(busStopID)) {
                
                busStops.get(i).addBusStopInfo(busID, bsi);
                return;
            }
        }


        System.out.println("ERRO: Paragem '" + busStopID + "' não encontrada.");
        System.exit(1);
    }


    /**
     * Imprime as frames
     */
    public static void printFrames() {
        for(int i = 0; i < busStops.size(); i++)
            busStops.get(i).printFrame();
    }
}

