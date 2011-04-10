/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo
 */
public class Config {

    //public HashMap<String, Vector<StopSchedule>> stops = new HashMap<String, Vector<StopSchedule>>();
    private Vector<Bus> buses = new Vector<Bus>();
    //private Vector<String> busStops = new Vector<String>();
    private HashMap<String, Integer[]> busStops = new HashMap<String, Integer[]>();

    public void load() {
        //HashMap<String, Vector<StopSchedule>> stops = new HashMap<String, Vector<StopSchedule>>();

        //loadBusStops();
        //loadConfig();
        loadBusStops2();
        loadConfig2();



        /* String[] stopsArray = { "A", "B", "C", "D" };
        int[][][][] schedules = {
        {{{803}}},
        {

        {{0}, {10,10}, {15,5} }, //<-- paragem
        {{2}, {10,20}, {15,8} },
        {{3}, {10,15}, {15,30} }
        },

        {{{801}}},
        {
        {{0}, {20,20}, {20,5} }
        }

        };*/

        /*
         *         int[][][] schedules = {
        {{0}, {10,10}, {15,5} }, //<-- paragem
        {{2}, {10,20}, {15,8} },
        {{3}, {10,15}, {15,30} }


        };
         */

        //Parser
/*
        int busID = 0;
        for(int paragem = 0; paragem < schedules.length; paragem++) {
        int paragemIndex = schedules[paragem][0][0];
        String stop = stopsArray[paragemIndex];
        Vector<StopSchedule> vec = new Vector<StopSchedule>();

        for(int sch = 1; sch < schedules[paragem].length; sch++ ) {
        StopSchedule ss = new StopSchedule();
        ss.hour = schedules[paragem][sch][0];
        ss.minutes = schedules[paragem][sch][1];

        vec.add(ss);
        }

        stops.put(stop, vec);

        }

        Bus b = new Bus(stops, busID);
         *
         *
         */
        /*
        //printer
        Object[] keys = stops.keySet().toArray();

        for(int i = 0; i < keys.length; i++) {

        System.out.println(keys[i]);
        Vector<StopSchedule> v = stops.get(keys[i]);

        for(int j = 0; j < v.size(); j++)
        System.out.println("    Hora: " + v.get(j).hour + " Mins: " + v.get(j).minutes);

        }*/


    }

    private void loadConfig2() {
        try {
            FileReader input = new FileReader("config.txt");
            BufferedReader bufRead = new BufferedReader(input);
            String line = "";
            line = bufRead.readLine();


            //parser
            while (line != null) {
                if (line.equals("")) {
                    line = bufRead.readLine();
                    continue;
                } //tag "bus" encontrada
                else if (line.split("-")[0].equals("BUS")) {
                    String[] tokens = line.split("-");
                    String busID = tokens[1];
                    String[] line1, line2 = null;
                    Boolean isFirst = true;
                    Boolean isLast = false;

                    line = bufRead.readLine();

                    //enquanto não encontrar tag "endbus" ou o ficheiro não acabar..
                    while (line != null && !isLast) {
                        if (line.toLowerCase().equals("endbus")) {
                            isLast = true;
                        }

                        if (line.equals("")) {
                            line = bufRead.readLine();
                            continue;
                        }

                        //se for a primeira linha..
                        if (isFirst) {
                            isFirst = false;
                            //então encontramos o terminal de partida
                            line2 = line.split(" ");
                        }
                        else {
                            
                            line1 = line2;
                            line2 = line.split(" ");

                            if (line1.length != line2.length && !isLast) {
                                System.out.println("Erro: O Autocarro " + busID + " nao passa o mesmo numero de vezes em todas as paragens");
                                System.exit(1);
                            }
                            BusStopInfo bsi;

                            //se for a ultima linha, entao encontramos o terminal: nextStop -> null
                            if(isLast)
                                bsi = new BusStopInfo("");
                            else
                                bsi = new BusStopInfo(line2[0]);
                            
                            //para cada hora, adicionar ao horário
                            for (int i = 1; i < line1.length; i++) {

                                int hours = Integer.parseInt(line1[i].split(":")[0]);
                                int minutes = Integer.parseInt(line1[i].split(":")[1]);
                                bsi.addSch(hours, minutes);
                            }

                            bsi.sortSch();
                            Main.addBusStopInfo(line1[0], busID, bsi);
                        }
                        line = bufRead.readLine();
                    }
                }
                line = bufRead.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Config file not found: config.txt");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Carrega a informação dos autocarros e respectivas rotas e horários (config.txt)
     */
    private void loadConfig() {
        try {
            FileReader input = new FileReader("config.txt");
            BufferedReader bufRead = new BufferedReader(input);
            String line = "";
            line = bufRead.readLine();

            //int busID = 0;

            Bus b = new Bus();


            //PARSER
            while (line != null) {
                //Linha vazia
                if (line.equals("")) {
                    line = bufRead.readLine();
                    continue;
                }

                //BUS
                String[] tokens = line.split("-");
                if (tokens[0].equals("BUS")) {
                    b.busID = Integer.parseInt(tokens[1]);

                    //System.out.println("FOUND BUS " + busID );
                } //ENDBUS
                else if (line.equals("ENDBUS")) {

                    //Bus b = new Bus(stops, busID);
                    buses.add(b);

                    b = new Bus();
                    //busID = 0;
                    //stops = new HashMap<String, Vector<StopSchedule>>();
                } //PARAGEM
                else {
                    String[] tok = line.split(" ");
                    String stopID = tok[0];
                    Vector<StopSchedule> vec = new Vector<StopSchedule>();

                    //if(!busStops.contains(stopID))
                    //    busStops.add(stopID);
                    b.route.add(stopID);

                    for (int i = 1; i < tok.length; i++) {
                        String tempo = tok[i];

                        StopSchedule ss = new StopSchedule();
                        ss.hour = Integer.parseInt(tempo.split(":")[0]);
                        ss.minutes = Integer.parseInt(tempo.split(":")[1]);
                        vec.add(ss);
                    }
                    b.stops.put(stopID, vec);
                }

                line = bufRead.readLine();
            }


        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Config file not found: config.txt");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * 
     */
    private void loadBusStops2() {

        FileReader input;
        try {
            input = new FileReader("busStops.txt");

            BufferedReader bufRead = new BufferedReader(input);
            String line = "";
            line = bufRead.readLine();

            while (line != null) {
                String[] tokens = line.split(" ");

                Integer[] coords = {Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])};

                //busStops.put(tokens[0], ints);
                BusStop b = new BusStop(tokens[0], coords[0], coords[1]);
                Main.busStops.add(b);

                line = bufRead.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Carrega a informação das paragens de autocarros (busStops.txt)
     */
    private void loadBusStops() {

        FileReader input;
        try {
            input = new FileReader("busStops.txt");

            BufferedReader bufRead = new BufferedReader(input);
            String line = "";
            line = bufRead.readLine();

            while (line != null) {
                String[] tokens = line.split(" ");

                Integer[] ints = {Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])};

                busStops.put(tokens[0], ints);

                line = bufRead.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Bus> getBuses() {
        return buses;
    }

    public HashMap<String, Integer[]> getBusStops() {
        return busStops;
    }
}
