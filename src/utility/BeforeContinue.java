/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.*;

/**
 *
 * Chew Wei Seng 22WMR14168
 */
public class BeforeContinue {
    Scanner scanner = new Scanner(System.in);
    
    public void pressEnterKeyBeforeContinue(){
        System.out.print("Press Enter Key To Continue ...");
        scanner.nextLine();
    }
    
    public boolean confirmationBeforeContinue(){

        String option;
        boolean confirm = true;
        boolean check = true;

        do {
            option = scanner.nextLine().trim();  // Read the input and trim any leading/trailing spaces
            option = option.toUpperCase();

            switch (option) {
                case "Y" -> {
                    confirm = true;
                    check = true;
                }
                case "N" -> {
                    confirm = false;
                    check = true;
                }
                default -> {
                    UI_messages uimsg = new UI_messages();
                    uimsg.displayErrorMessage("Please only select between Y or N.");
                    System.out.print("Please Re-Type the option (Y/N):");
                    check = false;
                }
            }

        } while (!check);

        return confirm;
    }
    
}
