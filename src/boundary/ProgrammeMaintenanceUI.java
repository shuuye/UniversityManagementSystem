/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.ProgrammeClass;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import utility.CheckDataType;
import utility.UI_messages;


/**
 *
 * @author Lim Shuye
 */
public class ProgrammeMaintenanceUI {
    
    //DELCARARION FOR UTILITY
    Scanner scanner = new Scanner(System.in);
    CheckDataType check = new CheckDataType();
    UI_messages message = new UI_messages();

    //****************************************************** MENU DISPLAYING FUNCTIONS **************************************************************
    
    public String MenuChoice() {
        String prompt = """
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        PROGRAMME MANAGEMENT MAIN MENU
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        1. Add new programme
                        2. Remove a programme
                        3. Search programme
                        4. Amend programme details
                        5. List all programme
                        6. Add tutorial group to programme
                        7. Remove tutorial group from programme
                        8. List all tutorial groups for programme
                        9. Generate summary report
                        0. Quit
                        
                        Enter choice: \t """;

        return prompt;
    }

    public String AmendChoiceMenu() {

        String prompt = """
                        ~~~~~~~~~~~~~~~~~~~~~~~
                        Amend programme details
                        ~~~~~~~~~~~~~~~~~~~~~~~
                        Select the details you want to amend:
                        1. Programme Code
                        2. Programme Name
                        3. Faculty
                        4. Programme Duration 
                        0. Quit
                        
                        Enter choice: \t """;

        return prompt;
    }
    
    //****************************************************** DISPLAY LISTING FUNSTIONS **************************************************************

    public void listAllProgramme(String outputStr) {
        
        System.out.println("""
                           
                           List of Programme:
                           """ + outputStr);

    }

    public void listAllTutorailGroup(String outputStr) {
        System.out.println("\nList of Tutorial Group:\n" + outputStr);

    }
    public void printProgrammeDetails(ProgrammeClass programme) {
        System.out.println("\n-----------------------------------------------------------------------------------------------");
        System.out.println("Programme Details");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Programme Code :  " + programme.getCode());
        System.out.println("Programme Name :  " + programme.getProgramName());
        System.out.println("Faculty        :  " + programme.getFaculty());
        System.out.println("Duration (year):  " + programme.getYear());
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    //****************************************************** INPUT DISPLAY FUNCTION **************************************************************
    public String inputProgrammeCode() {

        System.out.print("\nEnter Programme code: ");
        String code = scanner.nextLine();

        return code.toUpperCase();
    }

    public String inputProgrammeName() {
        System.out.print("\nEnter programme name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String inputFaculty() {
        System.out.print("\nEnter Faculty: ");
        String faculty = scanner.nextLine();
        return faculty.toUpperCase();
    }

    public String inputYear() {
        System.out.print("\nEnter year: ");
        String year = scanner.nextLine();
        return year;
    }

    public int getListNumber(String outputStr) {
        listAllProgramme(outputStr);
        String strChoice;
       
        do {
            System.out.print("Enter your choice (0 to cancel): ");
             strChoice = scanner.nextLine();
             if(!check.isInteger(strChoice)){
                 displayInvalidInteger();
             }             
            
        } while (!check.isInteger(strChoice));
        int choice =  Integer.parseInt(strChoice);
        System.out.println();
        return choice;
    }

    public void printTitle(String prompt) {
        String result = "\n" + prompt + "\n";
        String temp = "";
        for (int i = 0; i < prompt.length(); i++) {

            temp += "~";
        }

        System.out.println(temp + result + temp);
    }

     public void enterToTryAgain(){
        System.out.print("Press Enter Key To Try Again ...");
        scanner.nextLine();
    }
     //****************************************************** REPORT FORMATTING FUNCTIONS **************************************************************
     public String reportHeader() {
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String line = "\n====================================================================================================================================\n";
        String title = """
                       
                                                                        UNIVERSITY MANAGEMENT SYSTEM
                                                                    PROGRAMME MANAGEMENT SUMMARY REPORT
                       """;
        String header = line + title + line +
                      "                                             Report Date  : " + dateFormat.format(date) +
                      "\n                                             Generated By : Administrator" ;

        return header;
    }
     
     public String reportDivider(){
                          
         String line = "====================================================================================================================================";
         return line;
     }
     
     public String totalDivider(){
                          
         String line = "-----------------------------------------------------------------------------------------------------------------------------------";
         return line;
     }
     
     public String subTotalDivider(){
                       
         String line = String.format(" %-7s %-85s   %-10s", "","","----------");
         return line;
     }
   public String reportFooter() {
        
        String line = "\n========================================================== END OF REPORT ===========================================================\n";
        
        String footer = line;

        return footer;
    }

   //****************************************************** PROGRAMME MESSAGES DISPLAYING FUNCTIONS **************************************************************
   
    public void failAddTGroup() {
        System.out.println("");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("\u001b[31m Invalid action : Failed to add tutorial group. \u001b[0m");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("");
    }

    public void displayCurrentListing() {
        System.out.println("Current Listing: - ");
    }

    public void displayBlankError() {
        System.out.println("");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("\u001b[31m Incorrect Input: Please do not leave it blank. \u001b[0m");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("");
    }

    public void displayInvalidCode() {
        System.out.println("");
        System.out.println("\u001b[31m***************************************************************\u001b[0m");
        System.out.println("\u001b[31m Invalid Input : Please enter three letters only for the code. \u001b[0m");
        System.out.println("\u001b[31m***************************************************************\u001b[0m");
        System.out.println("");
    }

    public void SuccessAddTGroup() {
        System.out.println("");
        message.displaySuccessMessage("**********************************************************");
        message.displaySuccessMessage(" Success : A new group successfully added.");
        message.displaySuccessMessage("**********************************************************");
        System.out.println("");
    }

    public void displayInvalidInteger() {
        System.out.println("");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("\u001b[31m Invalid input: Please only enter integer. \u001b[0m");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("");
    }

    public void displayNoTutorialGroup() {
        System.out.println("");
        message.displayErrorMessage("*******************************************************************************");
        message.displayErrorMessage(" No Record Found : There is no tutorial group assigned to the programme yet");
        message.displayErrorMessage("                   Please add a tutorial group before proceeding. ");
        message.displayErrorMessage("*******************************************************************************");
        System.out.println("");
    }

    public void displaySuccessAmend() {
        System.out.println("");
        message.displaySuccessMessage("**********************************************************");
        message.displaySuccessMessage(" Success : Successfully amended !");
        message.displaySuccessMessage("**********************************************************");
        System.out.println("");
    }

    public void displayInvalidFaculty() {
        System.out.println("");
        System.out.println("\u001b[31m*****************************************************************\u001b[0m");
        System.out.println("\u001b[31m Invalid Input : Please enter four letters only for the faculty. \u001b[0m");
        System.out.println("\u001b[31m*****************************************************************\u001b[0m");
        System.out.println("");
    }

    public void displayvalidDataInput() {
        message.displaySuccessMessage("Valid input. Press enter to proceed.");
    }

    public void displayInvalidYear() {
        System.out.println("");
        System.out.println("\u001b[31m*************************************************************\u001b[0m");
        System.out.println("\u001b[31m Invalid Input : Please enter the year between range 1 to 4. \u001b[0m");
        System.out.println("\u001b[31m*************************************************************\u001b[0m");
        System.out.println("");
    }

    public void displaySuccessRemove() {
        System.out.println("");
        message.displaySuccessMessage("**********************************************************");
        message.displaySuccessMessage(" Susscess : Successfully removed !");
        message.displaySuccessMessage("**********************************************************");
        System.out.println("");
    }

    public void cancelAction() {
        System.out.println("");
        message.displayErrorMessage("Canceled : You are not proceeding with the action. The system is reverting back ...  ");
    }

    public void askToQuit() {
        System.out.println("Do you want to try again (Y/N) ? ");
    }

    public void displayInvalidName() {
        System.out.println("");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("\u001b[31m Invalid Input: Name only contain letters and space. \u001b[0m");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("");
    }

    public void displayDuplicateError() {
        System.out.println("");
        System.out.println("\u001b[31m**************************************************************\u001b[0m");
        System.out.println("\u001b[31m Invalid Action : You can not add an existing programme code. \u001b[0m");
        System.out.println("\u001b[31m**************************************************************\u001b[0m");
        System.out.println("");
    }

    public void displayExitMessage() {
        System.out.println("");
        message.displaySuccessMessage("**********************************************************");
        message.displaySuccessMessage(" Exiting to Main Menu.....");
        message.displaySuccessMessage("**********************************************************");
        System.out.println("");
    }

    public void displayProgrammeNotFound() {
        System.out.println("");
        message.displayErrorMessage("***********************************************************");
        message.displayErrorMessage(" Failed to search : Programme not found, please try again.");
        message.displayErrorMessage("***********************************************************");
        System.out.println("");
    }
    
    public void displaFacultyNotFound() {
        System.out.println("");
        message.displayErrorMessage("***********************************************************");
        message.displayErrorMessage(" Failed to display : Faculty not found, please try again.");
        message.displayErrorMessage("***********************************************************");
        System.out.println("");
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("\u001b[31m Choice out of range : Please select a valid option. \u001b[0m");
        System.out.println("\u001b[31m**********************************************************\u001b[0m");
        System.out.println("");
    }

    public void fullGroup() {
        System.out.println("");
        System.out.println("\u001b[31m********************************************************************\u001b[0m");
        System.out.println("\u001b[31m Failed to add : Reach the maximum amount of 5 groups in programme. \u001b[0m");
        System.out.println("\u001b[31m********************************************************************\u001b[0m");
        System.out.println("");
    }

    public void SuccessAddProgramme() {
        System.out.println("");
        message.displaySuccessMessage("**********************************************************");
        message.displaySuccessMessage(" Success : A new programme successfully added.");
        message.displaySuccessMessage("**********************************************************");
        System.out.println("");
    }

    public void displayGoingBack() {
        System.out.println("");
        message.displaySuccessMessage("**********************************************************");
        message.displaySuccessMessage(" Reverting to Previous Page.....");
        message.displaySuccessMessage("**********************************************************");
        System.out.println("");
    }
}
