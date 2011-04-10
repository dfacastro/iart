package iart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

/**
 * Esta classe representa o horário de um autocarro numa determinada paragem.
 * Inclui também uma referência para a próxima paragem do seu percurso.
 * @author Diogo
 */
public class BusStopInfo {

    private BusStop nextStop = null;
    private Boolean isFirst = false;
    //StopSchedule[] schs = new StopSchedule[]; //Arrays.sort(schs);
    private ArrayList<StopSchedule> schs = new ArrayList<StopSchedule>();
    
    BusStopInfo(String ns) {
        if (ns.equals("")) {
            nextStop = null;
            return;
        }

        for(int i = 0; i < Main.busStops.size(); i++)
            if(Main.busStops.get(i).getName().equals(ns)) {
                nextStop = Main.busStops.get(i);
                return;
            }
       System.out.println("ERRO: Paragem '" + ns + "' não encontrada.");
       System.exit(1);
    }

    public void setFirst() {
        isFirst = true;
    }

    public Boolean isFirst() {
        return isFirst;
    }

    public void addSch(int h, int m) {
        StopSchedule ss = new StopSchedule(h, m);
        schs.add(ss);
    }

    public void setNextStop(BusStop next) {
        nextStop = next;
    }

    /**
     * Ordena o horário por ordem crescente
     */
    public void sortSch() {
        
        StopSchedule[] arr = new StopSchedule[0];
        arr = schs.toArray(arr);

        Arrays.sort(arr);
        schs = new ArrayList<StopSchedule>( Arrays.asList(arr));
        
    }

    public BusStop nextStop() {
        return nextStop;
    }

    public void printFrame() {
        if (nextStop != null)
            System.out.println("\tNextStop: " + nextStop.getName());
        else
            System.out.println("\tNextStop: null (terminal)");
        System.out.print("\tSchedule: ");

        for (int i = 0; i < schs.size(); i++)
            schs.get(i).print();

        System.out.println();
    }

}



class StopSchedule implements Comparable {
    public int hour;
    public int minutes;

    StopSchedule() {
        hour = 0;
        minutes = 0;
    }
    StopSchedule(int h, int m) {
        hour = h;
        minutes = m;
    }

    public int compareTo(Object o) {
        if (this.hour < ((StopSchedule) o).hour)
            return -1;
        else if (this.hour > ((StopSchedule) o).hour)
            return 1;
        else if (this.hour == ((StopSchedule) o).hour)
           if (this.minutes < ((StopSchedule) o).minutes)
               return -1;
           else if (this.minutes > ((StopSchedule) o).minutes)
               return 1;
        
        return 0;
    }

    public void print() {
        System.out.print("["+ hour + ":" + minutes + "]");

    }

    public static int diff(StopSchedule ss1, StopSchedule ss2) {
        int diffMins = ss1.minutes - ss2.minutes;
        int diffHours = ss1.hour - ss2.hour;

        return Math.abs(diffMins + 60 * diffHours);
    }
}
