/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Diogo
 */
public class BusStop {
    private String name;
    private int x, y;

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

    public int getXCoord() {
        return x;
    }

    public int getYCoord() {
        return y;
    }

    public void addBusStopInfo(String busID, BusStopInfo bsi) {
        schedules.put(busID, bsi);
    }

    /**
     * retorna os autocarros que passam nesta paragem
     * @return
     */
    public String[] getBuses() {
        String[] a = new String[0];
        return schedules.keySet().toArray(a);
    }

    /**
     * retorna a proxima paragem do autocarro 'bus'
     * @param bus
     * @return
     */
    public BusStop getBusNextStop(String bus) {

        if(!schedules.containsKey(bus))
            return null;
        else
            return schedules.get(bus).nextStop();
        
    }

    /**
     * verifica se esta e' a primeira paragem do autocarro indicado
     * @param busID
     */
    public Boolean isBusFirstStop(String busID) {
        if (!schedules.containsKey(busID))
            return false;
        if (!schedules.get(busID).isFirst())
            return false;
        return true;
    }

    /**
     * Retorna o restante percurso do autocarro a partir deste ponto
     * @param busID
     * @return
     */
    public void getBusRoute(String busID, Vector<String> route) {

        if(!schedules.containsKey(busID))
            return;

        route.add(name);
        BusStop next = schedules.get(busID).nextStop();
        if (next != null) {
            next.getBusRoute(busID, route);
        }

    }

    /**
     * Actualiza o nome do autocarro busID, substituindo-o por newBusID
     * 804 -> 804-S.João
     * @param BusID nome do autcarro a actualizar
     * @param newBusID novo nome do autocarro
     */
    public void fixBusName(String BusID, String newBusID) {

        if (schedules.containsKey(BusID)) {
            BusStopInfo bsi = schedules.get(BusID);
            schedules.put(newBusID, bsi);
            schedules.remove(BusID);
        }
    }

    /**
     * Retorna um vector com o nome de todas as paragens
     * para as quais é possível ir a partir desta paragem.
     * @return
     */
    public Vector<String> getNeighbors() {
        Vector<String> neighbors = new Vector<String>();
        Object[] keys = schedules.keySet().toArray();

        for(int i = 0; i < keys.length; i++)
            if (schedules.get((String) keys[i]).nextStop() != null)
                neighbors.add(schedules.get((String) keys[i]).nextStop().getName());
        return neighbors;
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
