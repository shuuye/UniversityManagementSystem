package boundary;

import adt.ArrayList;
import entity.Student;
import entity.Tutorial;
import java.util.Scanner;
import adt.ListInterface;

/**
 *
 * @author Vanness Chaw Jun Kit
 */

public class TutorialManagementUI {

    private ListInterface<Student> studentList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private ListInterface<Tutorial> tutorialList = new ArrayList<>();

    public int getMenuChoice() {
        System.out.println("\n==================================");
        System.out.println("|  Tutorial Group Management Menu  |");
        System.out.println("==================================\n");
        System.out.println("1. Add student to tutorial group");  
        System.out.println("2. Remove student from tutorial group");  
        System.out.println("3. Change tutorial group for student"); 
        System.out.println("4. Find student from tutorial group"); 
        System.out.println("5. List all student from tutorial group"); 
        System.out.println("6. Filter tutorial groups based on criteria");
        System.out.println("7. Merge tutorial groups based on constraint"); 
        System.out.println("8. Generate summary reports"); 
        System.out.println("0. Quit\n"); 
        
        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (choice < 0 || choice > 8) {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice < 0 || choice > 8);

        System.out.println();
        return choice;
    
    }

  public void reportSubMenu() {
    System.out.println("\n==================================");
    System.out.println("|      Summary Report SubMenu     |");
    System.out.println("==================================\n");
    System.out.println("1. Generate Summary Report based on Tutorial Group");   
    System.out.println("2. Generate Summary Report based on Programme");  

    System.out.println("0. Quit\n");
    
    }
  
  public int FilterMenuChoice() {
    System.out.println("\n==================================");
    System.out.println("|  Filter Tutorial Group SubMenu  |");
    System.out.println("==================================\n");
    System.out.println("1. Filter tutorial group based on programme");   
    //System.out.println("2. Filter tutorial group based on ");  

    System.out.println("0. Quit\n");

    int choice;
    do {
        System.out.print("Enter choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (choice < 0 || choice > 8) {
            System.out.println("Invalid choice. Please enter a valid option.");
        }
    } while (choice < 0 || choice > 8);

    System.out.println();
    return choice;
    
    }

    public void programmeList() {
        System.out.println("\n==================================");
        System.out.println("|         Programmes List         |");
        System.out.println("==================================\n");
    }  
    
    public void ReportBanner() {
        System.out.println("\n=========================================================================================");
        System.out.println("|                                        Summary  Report                                 |");
        System.out.println("=========================================================================================\n");
    }
    
    public void ReportBanner2() {
        System.out.println("\n=================================================================================================================================");
        System.out.println("|                                                          Summary  Report                                                       |");
        System.out.println("=================================================================================================================================\n");
    }
    
    public void listStudentBanner() {
        System.out.println("\n==================================");
        System.out.println("|    List All Student In Group    |");
        System.out.println("==================================\n");
    }  
    
    public void findStudentBanner() {
        System.out.println("\n==================================");
        System.out.println("|    Find Student From Group    |");
        System.out.println("==================================\n");
    }  
    
    public void listAllStud(String outputStr) {
        System.out.println("\n" + outputStr);
        
    }  
    
    public String getStudentIDToFind() {
        System.out.print("Enter the Student ID to find: ");
        
        return scanner.nextLine().trim();
    }
    
    public String getStudentIDToRemove() {
        System.out.print("Enter the Student ID to remove: ");
        
        return scanner.nextLine().trim();
    }
  
    public String addStudentToGroup() {//add
        System.out.println("\n==================================");
        System.out.println("|             Add Student         |");
        System.out.println("==================================\n");
        System.out.print("\nEnter Student ID: ");
        String studID = scanner.nextLine();
        
        return studID;
    }
    
    public void removeBanner(){
        System.out.println("==================================");
        System.out.println("|         Remove Student          |");
        System.out.println("==================================\n");
        
    }
    
    public void tutProgrammeBanner(){
        System.out.println("\n\n==================================");
        System.out.println("|         To Which Programme      |");
        System.out.println("==================================\n");
        
    }
    
    public void tutGroupBanner(){
        System.out.println("\n\n==================================");
        System.out.println("|      To Which Tutorial Group    |");
        System.out.println("==================================\n");
        
    }
    
    public void mergeTutGroupBanner(){
        System.out.println("==================================");
        System.out.println("|      Merge Tutorial Group       |");
        System.out.println("==================================\n");
        
    }
    
    public void programmelist(){
        System.out.println("\n\n==================================");
        System.out.println("|           Programme List       |");
        System.out.println("==================================\n");
        
    }

    
    public void tutorialGroupBanner(){
        System.out.println("\n\n==================================");
        System.out.println("|         Tutorial Group         |");
        System.out.println("==================================\n");
        
    }
    
    public int selectChoice(){ //which tut group
            System.out.print("\nEnter choice : ");
            int tutGroup = scanner.nextInt();
            System.out.println();
            return tutGroup;
    }
    
    public int changeFromGroup(){
        System.out.print("\nFrom Which Tutorial Group : ");
        int tutGroup = scanner.nextInt();
        System.out.println();
        return tutGroup;
    }
    
    public int changeToGroup(){
        System.out.print("To Which Tutorial Group : ");
        int tutGroup = scanner.nextInt();
        System.out.println();
        return tutGroup;
    }
    
    public int mergeFromGroup(){
        System.out.print("\nMerge From Which Tutorial Group : ");
        int tutGroup = scanner.nextInt();
        System.out.println();
        return tutGroup;
    }
    
    public int mergeToGroup(){
        System.out.print("Merge To Which Tutorial Group : ");
        int tutGroup = scanner.nextInt();
        System.out.println();
        return tutGroup;
    }
    
    public void studentListBanner(){
        System.out.println("\n\n==================================");
        System.out.println("|             Student List        |    ");
        System.out.println("==================================\n");
        
    }
    
    
    
    public void RemoveStudentbanner() { //remove
        System.out.println("==================================");
        System.out.println("|         Remove Student          |");
        System.out.println("==================================\n");
        System.out.print("Enter the StudentID of Student : ");
        
    }
    
    public void FindStudent() { 
        System.out.println("==================================");
        System.out.println("|          Find Student           |");
        System.out.println("==================================\n");
        System.out.print("Enter the Student Name : ");
        
    }
    
    public void SelectStudent() { 
        System.out.println("==================================");
        System.out.println("|          Select Student         |");
        System.out.println("==================================\n");
        System.out.print("Enter the Student ID : ");
        
    }
    
    
    public void changeTutorialGroup(){
        System.out.println("==================================");
        System.out.println("|     Change tutorial group       |");
        System.out.println("==================================");
    }
}
