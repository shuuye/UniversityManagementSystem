
package utility;

import java.util.Scanner;


public class MessageUI {
    
    public static void displayInvalidChoiceMessage() {
    System.out.println("\nInvalid choice");
  }

  public static void displayExitMessage() {
    System.out.println("\nExiting system");
  }
  
  public int getIntegerInput(String message, int upperLimit) {
    Scanner scanner = new Scanner(System.in);
    int choice = 0;
    boolean validInput = false;

    while (!validInput) {
        try {
            System.out.print(message);
            choice = scanner.nextInt();

            if (choice < 0 || choice > upperLimit) {
                MessageUI.displayInvalidChoiceMessage();
            } else {
                validInput = true;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // Consume the invalid input
        }
    }

    return choice;
}
  
}
