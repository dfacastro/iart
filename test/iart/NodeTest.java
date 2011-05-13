/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo
 */
public class NodeTest {

    public NodeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

 
    /**
     * Test of eval_cost method, of class Node.
     */
    @Test
    public void testEval_cost_modeCost() {
        Display.mode = heuristicMode.COST;

        Node n1 = new Node( new BusStop("A", 0, 0), "BUS1", new StopSchedule(10,10));
        Node n2 = new Node( new BusStop("B", 0, 0), new StopSchedule(10,10));
        n2.addChild(n1);
        n1.eval_cost();
        assertEquals(1.0, n1.getCost(), 0);

        /**
         * Àrvore: n3 (root) -> n2 -> n1 (leaf)
         */
        Node n3 = new Node( new BusStop("B", 0, 0), new StopSchedule(10,10));
        n2 = new Node( new BusStop("B", 0, 0), "BUS2", new StopSchedule(10,10));
        n1 = new Node( new BusStop("B", 0, 0), "BUS3", new StopSchedule(10,10));

        n3.eval_cost();

        n3.addChild(n2);
        n2.eval_cost();

        n2.addChild(n1);
        n1.eval_cost();

        assertEquals(1.0, n2.getCost(), 0.0);
        assertEquals(2.0, n1.getCost(), 0.0);

    }
    
    
    /**
     * Test of eval_cost method, of class Node.
     */
    @Test
    public void testEval_cost_modeTime() {
        Display.mode = heuristicMode.TIME;
        
        Node n1 = new Node( new BusStop("A", 0, 0), "BUS1", new StopSchedule(11,16));   //FOLHA
        Node n2 = new Node( new BusStop("A", 0, 0), "BUS1", new StopSchedule(10,50));
        Node n3 = new Node( new BusStop("B", 0, 0), new StopSchedule(10,20));           //RAIZ

        n3.eval_cost();

        n3.addChild(n2);
        n2.eval_cost();

        n2.addChild(n1);
        n1.eval_cost();

        assertEquals(30.0, n2.getCost(), 0.0);
        assertEquals(56.0, n1.getCost(), 0.0);
     
    }

    /**
     * Test of eval_cost method, of class Node.
     */
    @Test
    public void testEval_cost_modeDistance() {
        Display.mode = heuristicMode.DISTANCE;

        /**
         * TODO:
         */
        
        Node n1 = new Node( new BusStop("A", 20, 30), "BUS1", new StopSchedule(11,16));   //FOLHA
        Node n2 = new Node( new BusStop("A", 20, 0), "BUS1", new StopSchedule(10,50));
        Node n3 = new Node( new BusStop("B", 0, 0), new StopSchedule(10,20));           //RAIZ
        
        n3.eval_cost();

        n3.addChild(n2);
        n2.eval_cost();

        n2.addChild(n1);
        n1.eval_cost();

        assertEquals(20.0, n2.getCost(), 0.0);
        assertEquals(50.0, n1.getCost(), 0.0);        
    }
    
    
    /**
     * Test of heuristica method, DISTANCE part, of class Node.
     */
    @Test
    public void testHeuristica_modeDistance() {
        Display.mode = heuristicMode.DISTANCE;

        /**
         * TODO:
         */
        
        Node n1 = new Node( new BusStop("A", 5, 4), "BUS1", new StopSchedule(11,16));   //FOLHA
        Node n2 = new Node( new BusStop("B", 5, 0), "BUS1", new StopSchedule(10,50));
        Node n3 = new Node( new BusStop("C", 0, 0), new StopSchedule(10,20));           //RAIZ
        
        n3.heuristica(n1);

        n3.addChild(n2);
        n2.heuristica(n1);

        n2.addChild(n1);
        
        assertEquals(6.4, n3.getHeuristic(), 0.1);
        assertEquals(4.0, n2.getHeuristic(), 0.0);        
    }
    
    /**
     * Test of heuristica method, TIME part, of class Node.
     */
    @Test
    public void testHeuristica_modeTime() {
        Display.mode = heuristicMode.TIME;
        
        Node n1 = new Node( new BusStop("A", 5, 4), "BUS1", new StopSchedule(11,16));   //FOLHA
        Node n2 = new Node( new BusStop("B", 5, 0), "BUS1", new StopSchedule(10,50));
        Node n3 = new Node( new BusStop("C", 0, 0), new StopSchedule(10,20));           //RAIZ

        n3.heuristica(n1);

        n3.addChild(n2);
        n2.heuristica(n1);

        n2.addChild(n1);

        assertEquals(6.4*60.0/50.0, n3.getHeuristic(), 0.1);
        assertEquals(4.0*60.0/50.0, n2.getHeuristic(), 0.0);
     
    }
    
    /**
     * Test of heuristic method, COST part, of class Node.
     */
 /*   @Test
    public void testHeuristica_modeCost() {
        Display.mode = heuristicMode.COST;

        Node n1 = new Node( new BusStop("A", 0, 0), "BUS1", new StopSchedule(10,10));
        Node n2 = new Node( new BusStop("B", 0, 0), new StopSchedule(10,10));
        Node n3 = new Node( new BusStop("C", 0, 0), new StopSchedule(10,10));
        
        n3.eval_cost();

        n3.addChild(n2);
        n2.eval_cost();

        n2.addChild(n1);
        n1.eval_cost();

        assertEquals(1.0, n2.getCost(), 0.0);
        assertEquals(2.0, n1.getCost(), 0.0);

    }
   */ 
    
}