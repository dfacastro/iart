/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iart;

/**
 * Classe que implementa o horario de uma paragem.
 */
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
    
    public String toString() {
        return hour + ":" + minutes;
    }

    public static int diff(StopSchedule ss1, StopSchedule ss2) {
        int diffMins = ss1.minutes - ss2.minutes;
        int diffHours = ss1.hour - ss2.hour;

        return Math.abs(diffMins + 60 * diffHours);
    }
}