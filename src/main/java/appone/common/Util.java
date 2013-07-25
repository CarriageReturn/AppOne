/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appone.common;

/**
 *
 * @author Xander
 */
public class Util {

    public static String formatAsTimeString(int value) {        
        int hours = value / 60;
        int minutes = value % 60;
        return String.format("%d:%02d", hours, minutes);
    }
}
