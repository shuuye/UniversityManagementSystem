/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * Chew Wei Seng 22WMR14168
 */
public class CheckInputRange {
    
    public boolean checkIntRange(int min, int max, int input){
        
        return !(min > input || max < input);
    }
}
