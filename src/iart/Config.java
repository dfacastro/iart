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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo
 */
public class Config {

    public void load() {

        loadBusStops();
        loadConfig();
        
    }

    /**
     * Carrega os horarios e percursos dos autocarros do ficheiro "config.txt"
     */
    private void loadConfig() {
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
                    String[] line1 = new String[0];
                    String[] line2 = new String[0];
                    Boolean isFirstLine = true;
                    Boolean isFirstBusStop = true;
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
                        if (isFirstLine) {
                            isFirstLine = false;
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
                            
                            if (isFirstBusStop) {
                                isFirstBusStop = false;
                                bsi.setFirst();
                            }
                            //para cada hora, adicionar ao horário
                            for (int i = 1; i < line1.length; i++) {

                                int hours = Integer.parseInt(line1[i].split(":")[0]);
                                int minutes = Integer.parseInt(line1[i].split(":")[1]);
                                bsi.addSch(hours, minutes);
                            }

                            bsi.sortSch();
                            Display.addBusStopInfo(line1[0], busID, bsi);
                        }
                        line = bufRead.readLine();
                    }
                    Display.fixBusName(busID, line1[0]);
                    Display.buses.add(busID + "-" + line1[0]);
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
     * Carrega a informação das paragens do ficheiro "busStops.txt"
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

                Integer[] coords = {Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])};

                //busStops.put(tokens[0], ints);
                BusStop b = new BusStop(tokens[0], coords[0], coords[1]);
                Display.busStops.add(b);

                line = bufRead.readLine();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
