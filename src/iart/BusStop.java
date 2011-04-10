/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

import java.util.HashMap;

/**
 *
 * @author Diogo
 */
public class BusStop {
    private String name;
    public int x, y;

    private HashMap<String, BusStopInfo> schedules = new HashMap<String, BusStopInfo>();

    BusStop(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void print() {
        System.out.println("BUS STOP " + name + "  x: " + x + "  y: " + y);
    }

    public String getName() {
        return name;
    }

    public void addBusStopInfo(String busID, BusStopInfo bsi) {
        schedules.put(busID, bsi);
    }

    public void printFrame() {
        System.out.println("Frame BusStop");
        System.out.println("Nome: " + name);
        System.out.println("Coords: [" + x + " , "+ y + "]");
        System.out.println();

        Object[] keys = schedules.keySet().toArray();

        for(int i = 0; i < keys.length; i++) {
            System.out.println("\tFrame BusSchedule");
            System.out.println("\tBus: " + keys[i]);
            schedules.get(keys[i]).printFrame();
            System.out.println();
        }
    }

}
