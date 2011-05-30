package iart;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Esta classe representa o horário de um autocarro numa determinada paragem.
 * Inclui também uma referência para a próxima paragem do seu percurso.
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

        for(int i = 0; i < Display.busStops.size(); i++)
            if(Display.busStops.get(i).getName().equals(ns)) {
                nextStop = Display.busStops.get(i);
                return;
            }
       System.out.println("ERRO: Paragem '" + ns + "' não encontrada.");
       System.exit(1);
    }

    //apenas para uso em JUnits
    BusStopInfo() {
        
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

    /**
     * Retorna a hora da proxima partida do autocarro nesta paragem.
     * A hora e' a mais proxima da hora indicada.
     * @param now: hora actual
     * @return proxima partida
     */
    public StopSchedule departure(StopSchedule now) {
        for(int i = 0; i < schs.size(); i++)
            if (schs.get(i).compareTo(now) >= 0)
                return schs.get(i);

        //se o autocarro nao voltar a passar nesta paragem neste dia, retornar o primeiro do dia seguinte
        if (!schs.isEmpty())
            return schs.get(0);
        else return null;
    }

}




