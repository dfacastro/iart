/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diogo
 */
public class StopScheduleTest {

    public StopScheduleTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of compareTo method, of class StopSchedule.
     */
    @Test
    public void testCompareTo() {

        StopSchedule ss1 = new StopSchedule(5, 10);
        StopSchedule ss2 = new StopSchedule(5, 30);
        assertEquals(true, ss1.compareTo(ss2) < 0);
        
        ss1 = new StopSchedule(5, 50);
        ss2 = new StopSchedule(5, 30);
        assertEquals(true, ss1.compareTo(ss2) > 0);

        ss1 = new StopSchedule(5, 30);
        ss2 = new StopSchedule(5, 30);
        assertEquals(true, ss1.compareTo(ss2) == 0);
        
        ss1 = new StopSchedule(5, 30);
        ss2 = new StopSchedule(6, 30);
        assertEquals(true, ss1.compareTo(ss2) < 0);

        ss1 = new StopSchedule(6, 30);
        ss2 = new StopSchedule(5, 30);
        assertEquals(true, ss1.compareTo(ss2) > 0);
    }

    /**
     * Test of diff method, of class StopSchedule.
     */
    @Test
    public void testDiff() {
        
        StopSchedule ss1 = new StopSchedule(5, 10);
        StopSchedule ss2 = new StopSchedule(5, 30);
        assertEquals(20, StopSchedule.diff(ss1, ss2));
        
        ss1 = new StopSchedule(5, 30);
        ss2 = new StopSchedule(5, 10);
        assertEquals(20, StopSchedule.diff(ss1, ss2));

        ss1 = new StopSchedule(5, 30);
        ss2 = new StopSchedule(6, 10);
        assertEquals(40, StopSchedule.diff(ss1, ss2));
        
    }

}