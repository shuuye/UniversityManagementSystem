/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * Chew Wei Seng 22WMR14168
 */
public class TextColor {
    // ANSI escape code for resetting text attributes to default
    private static final String RESET_ATTRIBUTES = "\u001B[0m";

    // ANSI escape codes for various text colors
    private static final String TXT_COLOR_RED = "\u001B[31m";
    private static final String TXT_COLOR_GREEN = "\u001B[32m";

    public void resetTextAttributes() {
        System.out.print(RESET_ATTRIBUTES);
    }
    
    public void setRedTextColor() {
        System.out.print(TXT_COLOR_RED);
    }
    
    public void setGreenTextColor() {
        System.out.print(TXT_COLOR_GREEN);
    }
    
    
    
}
