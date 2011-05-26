/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

/**
 * Implementa grafo dirigido pesado
 * @author Diogo
 */
public class MyGraph extends JFrame {
    ListenableDirectedWeightedGraph<String,String> g = new ListenableDirectedWeightedGraph ( DefaultWeightedEdge.class);
    
    private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    //
    private JGraphModelAdapter m_jgAdapter;
    private JGraph jgraph;

    /**
     * Inicializa o grafo
     */
    public void init() {

        // cria a visualizacao usando JGraph, atraves de um adaptador
        m_jgAdapter = new JGraphModelAdapter( g );

        jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings( jgraph );
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );
        
        jgraph.setMoveable(false);
        
    }

    /**
     * Adiciona um conjunto de vertices ao grafo e posiciona-os
     * @param vertexes
     */
    public void addVertexes(HashMap<String, Integer[]> vertexes) {
        Object[] keys = vertexes.keySet().toArray();

        for(int i = 0; i < keys.length; i++) {
            g.addVertex((String) keys[i]);
            positionVertexAt((String) keys[i] , vertexes.get((String) keys[i])[0], vertexes.get((String) keys[i])[1] );
        }
    }

    /**
     * Adiciona ao grafo uma aresta direccionada entre os vertices 'a' e 'b'.
     * @param a: vertice origem
     * @param b: vertice destino
     */
    public void addEdge(String a, String b, String name) {
        g.addEdge(a,b, name);
        Object[] x = g.edgeSet().toArray();

      //  for(int i = 0; i < x.length; i++)
       //     System.out.println(x[i]);

        //System.out.println(g.getEdgeWeight());
        /*
        String e = g.getEdge(a, b);
        g.setEdgeWeight(g.getEdge(a, b), 29.0);
        System.out.println(e);

        s = s + ".";
        System.out.println(s);*/
    }

    /**
     * Adiciona o vertice V ao grafo na posição x,y
     * @param v: vertixe a adicionar
     * @param x: posição x
     * @param y: posição y
     */
    public void addVertex(String v, int x, int y) {
        g.addVertex(v);
        positionVertexAt(v, x, y);
    }

    public void resetEdges() {
        Object[] edges = g.edgeSet().toArray();

        for(int i = 0; i < edges.length; i++)
            g.removeEdge((String) edges[i]);
    }

    /**
     * Torna o grafo visivel/invisivel
     * @param b
     */
    public void show(boolean b) {

        jgraph.setVisible(b);

        JFrame frame = new JFrame();

        frame.getContentPane().add(new JScrollPane(jgraph));
        frame.pack();
        frame.setVisible(b);
    }
    
    public JGraph getJGraph() {
        return jgraph;
    }

    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

/*        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}
*/
        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }
    
    /**
     * Posiciona um dado vertice
     * @param vertex
     * @param x
     * @param y
     */
    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
                new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        m_jgAdapter.edit(cellAttr, null, null, null);
    }
}
