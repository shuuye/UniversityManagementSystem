/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lim Shuye
 */
public class GetInput {

    Scanner scanner = new Scanner(System.in);

    /*Get integer input function
        how to use:
        - prompt is the message you want to tell user (exp. Enter your name: )
        - scanner is the input scanner
        - when the input is correct, this function returns the integer value that user input
     */
    public int getIntInput(String prompt) {
        int input = 0;
        boolean validInput = false;
        /*Catch if any invalid input is enter*/
        do {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("\u001b[31m**********************************************************\u001b[0m");
                System.out.println("\u001b[31m Incorrect Input: Please enter integer only. \u001b[0m");
                System.out.println("\u001b[31m**********************************************************\u001b[0m");
                System.out.println("");
                scanner.nextLine(); // Clear the invalid input from the scanner buffer
            }
        } while (!validInput);
        scanner.nextLine();
        return input;
    }

    /*
    Display customize menu and get input of choice
    Description: 
            1.  Prompt is the header of the menu
            2.  you can determine the number of choices given by inputing a sring call option (ex. new String[]{"No Preference", "Vegetarian"}))
            3.  The loop will count the number of option by using array.length to do validation 
            4.  If input is valid, this program returns the integer that user input
     */
    public static int getInputChoice(String prompt, String[] options) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validInput = false;
        /*Check if the choice input is valid and within the given selection*/
        while (!validInput) {
            /*catch any input error*/
            try {
                System.out.println(prompt);
                for (int i = 0; i < options.length; i++) {
                    System.out.println("\t" + (i + 1) + ". " + options[i]);
                }
                System.out.println("\t 0"  + ". " + "Quit");
                System.out.print("\n ==> Select your choice: ");
                choice = scanner.nextInt();
                /*Check if the input is within given selection*/
                if (choice >= 1 && choice <= options.length) {
                    validInput = true;
                } else {
                    System.out.println("");
                    System.out.println("\u001b[31m**********************************************************\u001b[0m");
                    System.out.println("\u001b[31m Invalid Choice : Please select a valid option. \u001b[0m");
                    System.out.println("\u001b[31m**********************************************************\u001b[0m");
                    System.out.println("");
                }
            } catch (Exception ex) {
                System.out.println("");
                System.out.println("\u001b[31m**********************************************************\u001b[0m");
                System.out.println("\u001b[31m Incorrect Input: Please enter integer only. \u001b[0m");
                System.out.println("\u001b[31m**********************************************************\u001b[0m");
                System.out.println("");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return choice;
    }


    /*
    Get String input function
      how to use:
        - prompt is the message you want to tell user (exp. Enter your name: )
        - scanner is the input scanner
        - when the input is correct, this function returns the string that user input
     */
    public String getStringInput(String prompt) {
        String input = null;
        boolean validInput = false;
        /*Check if any invalid input is entered*/
        do {
            try {
                System.out.print(prompt);
                input = scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println("\u001b[31m**********************************************************\u001b[0m");
                System.out.println("\u001b[31m Incorrect Input: Input only contain letters and space. \u001b[0m");
                System.out.println("\u001b[31m**********************************************************\u001b[0m");
                System.out.println("");
                scanner.next(); // Clear the invalid input from the scanner buffer
            }
        } while (!validInput);
        return input;
    }

}
