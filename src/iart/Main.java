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
import java.util.HashMap;
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

    public static HashMap<String, Integer[]> busStops;
    public static Vector<Bus> buses;
    public double ticket_price;

    public static void main(String[] args) {

        //JGraphAdapterDemo d = new JGraphAdapterDemo();
        //d.init();

        Config c = new Config();
        c.load();
        buses = c.getBuses();
        busStops = c.getBusStops();

        Vector<String> r = buses.get(0).route;
        for(int i = 0; i < r.size(); i++)
            System.out.println("ROUTE: " + r.get(i));

        MyGraph mg = new MyGraph();
        mg.init();
        mg.addVertexes(busStops);
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
}

