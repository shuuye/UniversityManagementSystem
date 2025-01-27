/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import dao.Initializer;
import control.TeachingManagment;
import control.ProgrammeManagement;
import control.StudentRegistrationManagement;
import control.TutorialManagement;
import control.CourseManagement;
import utility.UI_messages;
import java.util.Scanner;
import utility.CheckDataType;
import utility.CheckEmpty;
import utility.CheckInputRange;

/**
 *
 * @author Lim Shuye,Chew Wei Seng, Wong Chai Yee, Wong Weng Sam, Vanness Chaw Jun Kit
 */
public class mainMenuUI {
    
    private static UI_messages uiMsg = new UI_messages();
    
    public static void main(String[] args) throws InterruptedException {
        Initializer initializer = new Initializer();
        Object[] systemInitializeArray = initializer.initializeAllData();
    
        Object[] systemDataArray = systemInitializeArray;
    
        Scanner scan = new Scanner(System.in);
        UI_messages uiMsg = new UI_messages();
        
        boolean exitSystem = false;

        do{
            System.out.println("\n UNIVERSITY MANAGEMENT SYSTEM MAIN MENU\n----------------------------------------");
            System.out.println("1. Programme Management");
            System.out.println("2. Course Management");
            System.out.println("3. Tutorial Group Management");
            System.out.println("4. Teaching Management");
            System.out.println("5. Student Management");
            System.out.println("0. Exit"); 
        
            int option = getChoice(0,5);
            
            switch(option){
                case 1 -> ProgrammeManagement.main(systemDataArray);
                case 2 -> CourseManagement.main(systemDataArray);
                case 3 -> TutorialManagement.main(systemDataArray);
                case 4 -> TeachingManagment.main(systemDataArray);
                case 5 -> StudentRegistrationManagement.main(systemDataArray);
                case 0 -> {System.out.println("");
                            uiMsg.displaySuccessMessage("******************************");
                            uiMsg.displaySuccessMessage("    Thank you for using.");
                            uiMsg.displaySuccessMessage("    Existing System...");
                            uiMsg.displaySuccessMessage("******************************");
                            exitSystem = true;}
            }
            
        }while(!exitSystem);
        
    }
    
    public static boolean inputIsInRange(int choiceInt,int min, int max){
        CheckInputRange checkRange = new CheckInputRange();
        if(!checkRange.checkIntRange(min, max, choiceInt)){
            if(choiceInt == min){
                uiMsg.displayErrorMessage("Please only input " + min + ".");
            }else{
                uiMsg.displayErrorMessage("Please only input between " + min + " and " + max + ".");
            }
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean inputIsInt (String choice){
        CheckDataType checkDataType = new CheckDataType();
        return checkDataType.isInteger(choice);
    }
    
    public static int checkOptionInput(int choiceInt,int min, int max){
        CheckEmpty checkEmpty = new CheckEmpty();
        
        boolean correctMenuInput;
        do{
            correctMenuInput=true;
            
            String choice = "";
            choice=checkEmpty.checkEmptyInputStr("Enter your option number here :", choice, "Please at least select one of the options");
                  
            correctMenuInput = inputIsInt(choice);
            
            if (correctMenuInput) {
                choiceInt = Integer.parseInt(choice);
                // Now you can safely use quantityInt as an integer.
                
                correctMenuInput = inputIsInRange(choiceInt,min,max);
            }else{
                uiMsg.displayErrorMessage("Please only input number from the options.");
            }
                        
        }while (correctMenuInput == false);
        
        return choiceInt;
    }
    
    public static int getChoice(int min, int max) {
        
        int choiceInt = 0;
        //only can input between min and max inputed
        choiceInt = checkOptionInput(choiceInt,min,max);
        
        return choiceInt;
    }
    
    
}