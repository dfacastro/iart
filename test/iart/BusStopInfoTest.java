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
public class BusStopInfoTest {

    public BusStopInfoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }


    /**
     * Test of departure method, of class BusStopInfo.
     */
    @Test
    public void testDeparture() {
        
        BusStopInfo bsi = new BusStopInfo();
        bsi.addSch(5, 30);
        bsi.addSch(4, 50);
        bsi.addSch(7, 40);
        bsi.sortSch();
        
        assertEquals(4, bsi.departure(new StopSchedule(4,30)).hour);
        assertEquals(50, bsi.departure(new StopSchedule(4,30)).minutes);
        
        assertEquals(5, bsi.departure(new StopSchedule(5,30)).hour);
        assertEquals(30, bsi.departure(new StopSchedule(5,30)).minutes);

        assertEquals(7, bsi.departure(new StopSchedule(6,30)).hour);
        assertEquals(40, bsi.departure(new StopSchedule(6,30)).minutes);
        
        assertEquals(4, bsi.departure(new StopSchedule(8,30)).hour);
        assertEquals(50, bsi.departure(new StopSchedule(8,30)).minutes);


    }

}