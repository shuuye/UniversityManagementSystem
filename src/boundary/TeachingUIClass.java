
package boundary;

import entity.*;
import adt.*;
import utility.*;
import adt.ListInterface;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import utility.TextColor;
/**
 *
 * @author wong chai yee
 */
public class TeachingUIClass {  
     
    TextColor color = new TextColor();
    Scanner input = new Scanner(System.in);
    
public int getTeachingMenu() throws InterruptedException {
    ClearScreen.clrscreen();
    System.out.println("-----------------------------------------------------------------------");
    System.out.println("|                        Teaching Menu                                |");
    System.out.println("|---------------------------------------------------------------------|");
    System.out.printf("| %-2s | %-30s  %-30s |\n", "1.", "Assign tutor to course", " ");
    System.out.printf("| %-2s | %-30s  %-29s |\n", "2.", "Assign tutorial groups to tutor", " ");
    System.out.printf("| %-2s | %-30s  %-26s |\n", "3.", "Assign a tutor to a tutorial group", " ");
    System.out.printf("| %-2s | %-30s  %-30s |\n", "4.", "Search courses under a tutor", " ");
    System.out.printf("| %-2s | %-30s  %-30s |\n", "5.", "Search tutors for a course", " ");
    System.out.printf("| %-2s | %-30s  %-30s |\n", "6.", "List all tutors for a course", " ");
    System.out.printf("| %-2s | %-30s  %-30s |\n", "7.", "List courses under a tutor", " ");
    System.out.printf("| %-2s | %-30s  %-29s |\n", "8.", "Filter tutors based on criteria", " ");
    System.out.printf("| %-2s | %-30s  %-30s |\n", "9.", "Generate summary reports", " ");
    System.out.printf("| %-2s | %-30s  %-30s |\n", "0.", "Quit", " ");
    System.out.println("-----------------------------------------------------------------------");

    int choice = 0;
    boolean validInput = false;

    while (!validInput) {
        try {
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine();  
            validInput = true; 
        } catch (InputMismatchException e) {
            color.setRedTextColor();
            System.out.println("***********************************************************************************************************************************************");
            color.setRedTextColor();
            System.out.println("                                                    Invalid input. Please Enter a Valid Integer ");
            color.setRedTextColor();
            System.out.println("***********************************************************************************************************************************************");
            input.nextLine();
            input.nextLine();
        }
    }

    System.out.println();
    return choice;
}
    
    public void assignTutortoCourse() throws InterruptedException {
        ClearScreen.clrscreen();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                 Assign Tutor to Course                                     ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println(" *Belows are the tutors which can be assign \n Please Select the tutor you would like to assign.\n");
        
        
    }
    
    public void assignTutorToTutorial() throws InterruptedException{
        ClearScreen.clrscreen();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                    Assign Tutor to Tutorial Group               ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
      public void assignTutorialToTutor() throws InterruptedException{
        ClearScreen.clrscreen();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                      Assign Tutorial Group to Tutor                                ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
  
public void listTutor(ListInterface<TutorEntity> tutorList) throws InterruptedException {
    System.out.println("=================================================================================================================================================");
    System.out.println("                                         List of Tutors                                            ");
    System.out.println("=================================================================================================================================================");
    System.out.printf("%-4s | %-10s | %-30s | %-15s |%-15s%n", "No.", "Tutor ID", "Tutor Name", "Tutor Type","Gender");
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

    for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
        TutorEntity tutor = tutorList.getEntry(i);
        System.out.printf("%-4d | %-10d | %-30s | %-15s |%-15s%n", i, tutor.getTutorId(), tutor.getTutorName(), tutor.getTutorType(), tutor.getGender());
    }
}


public void SummarylistTutor(ListInterface<TutorEntity> tutorList) throws InterruptedException {
    System.out.println("\n");
    System.out.println("                                All tutor List                                        ");
    System.out.println("                              ------------------                                      ");
    System.out.printf("%-4s  %-10s  %-30s  %-15s %-15s%n", "No.", "Tutor ID", "Tutor Name", "Tutor Type","Gender");
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

    for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
        TutorEntity tutor = tutorList.getEntry(i);
        System.out.printf("%-4d  %-10d  %-30s  %-15s %-15s%n", i , tutor.getTutorId(), tutor.getTutorName(), tutor.getTutorType(), tutor.getGender());
    }
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
}


public void listCourse(ListInterface<Course> courseList) throws InterruptedException {
    System.out.println("=================================================================================================================================================");
    System.out.println("                                          List Of Courses                                            ");
    System.out.println("=================================================================================================================================================");
    System.out.printf("   %-30s %-20s%n", "Course ID", "Course Name");

    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
        Course course = courseList.getEntry(i);
        System.out.printf("%d.  %-30s %-20s %n", i, course.getCourseCode(), course.getCourseName());
    }
}

public void listProgram(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                                    Program List under The Course                                  ");
    System.out.println("=================================================================================================================================================");
}

public void tutorialgrpList(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                                    Tutorial Group List under The Program                          ");
    System.out.println("=================================================================================================================================================");
                            
}

public void searchCourse() throws InterruptedException{
    ClearScreen.clrscreen();
    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("                                    Search Courses under a Tutor Name                                ");
    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

}

public void displaySearchCourseInfo(){
    System.out.println("");
    color.setGreenTextColor();
    System.out.println("* Please Enter the CourseID you would like to search. ");
    color.resetTextAttributes();

}

public void tutorNotInCourse(){
    color.setRedTextColor();
    System.out.println("***********************************************************************************************************************************************");
    color.setRedTextColor();
    System.out.println("                                        Tutor are not exist in this Course !                       ");
    color.setRedTextColor();
    System.out.println("***********************************************************************************************************************************************");
}

public void tutorNotFound(){
    color.setRedTextColor();
    System.out.println("***********************************************************************************************************************************************");
    color.setRedTextColor();
    System.out.println("                                         Tutor not found !!!                              ");
    color.setRedTextColor();
    System.out.println("***********************************************************************************************************************************************");
}


public void tutorInCourse(){
    color.setGreenTextColor();
    System.out.println("***********************************************************************************************************************************************");
    color.setGreenTextColor();
    System.out.println("                                        Tutor exist in this Course !                       ");
    color.setGreenTextColor();
    System.out.println("***********************************************************************************************************************************************");
}

public void tutorialgrp(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                                        Tutorial Group List                                        ");
    System.out.println("=================================================================================================================================================");
}

public void courseNotFound(){
    color.setRedTextColor();
    System.out.println("***********************************************************************************************************************************************");
    color.setRedTextColor();
    System.out.println("                                        The Selected Course Have no Tutor Yet !!");
    color.setRedTextColor();
    System.out.println("***********************************************************************************************************************************************");
    
    
}


public void listgroup(ListInterface<Tutorial> tutorialGrpList) {
    System.out.println( " ");
    System.out.println( "           List of Tutorial Groups             ");
    for (int i = 1; i <= tutorialGrpList.getNumberOfEntries(); i++) {
        Tutorial tutorialGrp = tutorialGrpList.getEntry(i);
        System.out.printf("%d.  %-10d %n", i, tutorialGrp.toString());
    }
    
}
 

    public int getTutorId(){
    System.out.print("Enter TutorID: ");
    int tutorId = input.nextInt();
    return tutorId;
    }
        
    
    public String getTutorName(){
        System.out.print("Enter Tutor Name: ");
        String tutorName = input.nextLine();
        return tutorName;
    }
    
    
    public String tutorType(){
        System.out.print("Enter Tutor Type (Part-time/Full-time): ");
        String tutorType = input.nextLine();
        return tutorType;
    
    }

    public String getCourseID(){
     
        System.out.print("Enter Course ID to Search :");
        String courseId = input.nextLine();
        return courseId;
    }
    
     
public int getListNumber(String outputStr) {
    int choice = 0;
    boolean validInput = false;

    while (!validInput) {
        try {
            System.out.print(outputStr);
            choice = input.nextInt();
            input.nextLine();  
            validInput = true;   
        } catch (InputMismatchException e) {
             color.setRedTextColor();
            System.out.println("***********************************************************************************************************************************************");
            color.setRedTextColor();
            System.out.println("                                                 Invalid input. Please Enter a Valid Integer ");
            color.setRedTextColor();
            System.out.println("***********************************************************************************************************************************************");
            input.nextLine();
            input.nextLine();
        }
    }

    return choice;
}
     
public void filtertutorManu(){
    color.setGreenTextColor();
    System.out.println("===================================================================================================");
    color.setGreenTextColor();
    System.out.println("                                    Select category to filter tutor");
    color.setGreenTextColor();
    System.out.println("===================================================================================================");
    System.out.println("Please select one of the filter Category :");
    System.out.println("");
    System.out.println("1. Filter Tutor by Gender");
    System.out.println("2. Filter Tutor by Tutor Type");
    System.out.println("0. Cancel");
    System.out.print("Enter your choice: ");

}

public void filterByGender() throws InterruptedException{
    ClearScreen.clrscreen();
    System.out.println("===================================================================================================");
    System.out.println("                                Select on category to view all the tutor : ");
    System.out.println("===================================================================================================");
    System.out.printf("%-4s | %-10s%n", "No.", "Category");
    System.out.printf("%-4s | %-10s%n", "1.", "All Male Tutor");
    System.out.printf("%-4s | %-10s%n","2.","All Female Tutor");
    System.out.println("0. Cancel");
    System.out.print("Enter your choice: ");


}

public void filterByType() throws InterruptedException{
    ClearScreen.clrscreen();
    color.setGreenTextColor();
    System.out.println("===================================================================================================");
    color.setGreenTextColor();
    System.out.println("                                Seelct Filter by Tutor Type Options  ");
    color.setGreenTextColor();
    System.out.println("===================================================================================================");
    System.out.printf("%-4s | %-10s |%-10s%n","No.","1.","All Tutor as a Full-Timer");
    System.out.printf("%-4s | %-10s |%-10s%n","No.","2.","All Tutor as a Part-Timer");
    System.out.println("0. Cancel");
    System.out.print("Enter your choice: ");
}

public void SummaryReportMenu(){
    color.setGreenTextColor();
    System.out.println("=================================================================================================================================================");
    color.setGreenTextColor();
    System.out.println("                                                        Teaching Summary Menu");
    color.setGreenTextColor();
    System.out.println("=================================================================================================================================================");
    System.out.println("1. Summary Report for Courses");
    System.out.println("2. Summary Report for Tutors");
    System.out.println("0. Exit");

    System.out.print("Enter your choice: ");
}

public void SummaryForCourse(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                                                 Summary Report for Courses");
    System.out.println("=================================================================================================================================================");

}

public void SummaryForTutor(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                                                 Summary Report for Tutor");
    System.out.println("=================================================================================================================================================");

}

public void SummaryforGender(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                           Below are The Total Count of Tutor By Gender ");
    System.out.println("=================================================================================================================================================");

}

public void Summaryfortype(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                            Below are The Total Count of Tutor by Type.");
    System.out.println("=================================================================================================================================================");

}

public void tutorStatistic(){
    System.out.println("=================================================================================================================================================");
    System.out.println("                            Below Are The Percentage of The Tutor.");
    System.out.println("=================================================================================================================================================");
}

public void printLine(){
    System.out.println("---------------------------------------------------------------------------------------------------");
}


public void printLine2(){
System.out.println("=================================================================================================================================================");

}





public void reportHeader() {
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("                                                UNIVERSITY MANAGEMENT SYSTEM    ");                                                             
        System.out.println("                                             PROGRAMME MANAGEMENT SUMMARY REPORT");                                                        
        System.out.println("");     
        System.out.println("                                             Report Date :" + dateFormat.format(date));
        System.out.println("                                             Generated By: Administrator");

}

    public boolean continueProcess() {
     char continueProcess;
     do {
         System.out.print("Do you want to continue ? (Y/N): ");
         continueProcess = input.next().charAt(0);
     } while (Character.toUpperCase(continueProcess) != 'Y' && Character.toUpperCase(continueProcess) != 'N');
     input.nextLine();
     return Character.toUpperCase(continueProcess) == 'Y';
 }
    
    
}

