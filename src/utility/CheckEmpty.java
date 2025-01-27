/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * Chew Wei Seng 22WMR14168
 */

public class CheckEmpty {
    Scanner scan = new Scanner(System.in);
    private UI_messages uiMsg = new UI_messages();
    
    public String checkEmptyInputStr(String label, String input,String emptyErrorMsg){
        
        do{
            System.out.print(label);
            input = scan.nextLine();
            if(input.isEmpty()){
                uiMsg.displayErrorMessage(emptyErrorMsg);
            }
            else{
                break;
            }
        }while (input.isEmpty());
        
        return input;
    }
    
    public double checkEmptyInputDouble(String label, double input, String emptyErrorMsg) {
        do {
            System.out.print(label);
            String inputStr = scan.nextLine();
            if (inputStr.isEmpty()) {
                uiMsg.displayErrorMessage(emptyErrorMsg);
            } else {
                try {
                    input = Integer.parseInt(inputStr);
                    if (input == 0) {
                        // 0 amount will be also consider as empty (for currency)
                        uiMsg.displayErrorMessage(emptyErrorMsg);
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {    
                    uiMsg.displayErrorMessage(emptyErrorMsg);
                }
            }
        } while (true);

        return input;
    }
    
    public int checkEmptyInputInt(String label, int input, String emptyErrorMsg) {
        do {
            System.out.print(label);
            String inputStr = scan.nextLine();
            if (inputStr.isEmpty()) {
                uiMsg.displayErrorMessage(emptyErrorMsg);
            } else {
                try {
                    input = Integer.parseInt(inputStr);
                    if (input == 0) {
                        // 0 amount will be also consider as empty (for currency)
                        uiMsg.displayErrorMessage(emptyErrorMsg);
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {     
                    uiMsg.displayErrorMessage(emptyErrorMsg);
                }
            }
        } while (true);

        return input;
    }
    
    public char checkEmptyInputChar(String label, char input, String emptyErrorMsg) {
        do {
            System.out.print(label);
            String inputStr = scan.nextLine();
            if (inputStr.isEmpty()) {
                uiMsg.displayErrorMessage(emptyErrorMsg);
            } else {
                // Check if the inputStr contains only one character (char)
                if (inputStr.length() == 1) {
                    input = inputStr.charAt(0);
                    break;
                } else {
                    uiMsg.displayErrorMessage(emptyErrorMsg);
                }
            }
        } while (true);
        return input;
    }
}
