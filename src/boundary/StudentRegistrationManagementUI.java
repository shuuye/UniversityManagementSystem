
package boundary;
import java.util.Scanner;
import utility.CheckDataType;
import utility.UI_messages;

/** @author Wong Weng Sam
 */
public class StudentRegistrationManagementUI {
    private UI_messages MessageUI = new UI_messages();
    Scanner scanner = new Scanner(System.in);
    private CheckDataType checkDataType = new CheckDataType();
    

  public int getMenuChoice() {
    System.out.println(" ---------------------------------");
    System.out.println(" |Student Registration Management|");
    System.out.println(" ---------------------------------"); 
    System.out.println("1. Add new student");
    System.out.println("2. Remove new student");
    System.out.println("3. Amend student details");
    System.out.println("4. Search students for registered courses");
    System.out.println("5. Add students to courses");
    System.out.println("6. Remove students from courses");
    System.out.println("7. List students for courses");
    System.out.println("8. Generate summary report");
    System.out.println("0. Quit");
    
    boolean correctInput = false;
    int choiceInt=0;

    do{
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        if(!checkDataType.isInteger(choice)){
            MessageUI.displayErrorMessage("Please enter integer only!");

        }else{
            choiceInt = Integer.parseInt(choice);
            break;
        }
    }while(!correctInput);
    
    
    return choiceInt;
  }
  
 public String inputStudentID() {
        String studentID;
        boolean isValid;
        
        do {
            System.out.print("Enter StudentID (TARUMT FORMAT eg: 22WMR13770): ");
            studentID = scanner.nextLine().trim().toUpperCase();

            // Validate alphanumeric input for student id
            isValid = studentID.matches("^\\d{2}[A-Za-z]{3}\\d{5}$");
            
            if (!isValid) {
                MessageUI.displayErrorMessage("Invalid input. Please use alphanumeric characters only.");
               
            }

        } while (!isValid);

        return studentID;
    }
 
 
 

public String inputStudentName() {
    String studentName;
        boolean isValid;

        do {
            System.out.print("Enter Student Name: ");
            studentName = scanner.nextLine().trim().toUpperCase();

            // Validate alphabet input for student name
            isValid = studentName.matches("^[a-zA-Z\\s]+$");
  
            if (!isValid) {
                MessageUI.displayErrorMessage("Invalid input. Please use alphabet characters only.");
            }

        } while (!isValid);

        return studentName;
    }
  

public String inputGender() {
    String gender;
        boolean isValid;

        do {
            System.out.print("Enter Gender: ");
            gender= scanner.nextLine().trim().toUpperCase();

            // Validate gender input for gender
            isValid = gender.equals("MALE") || gender.equals("FEMALE") ;
  
            if (!isValid) {
                 MessageUI.displayErrorMessage("Invalid input. Please enter 'Male' or 'FEMALE' only.");   
            }

        } while (!isValid);

        return gender;
  }



public String inputProgrammeName() {
    String programmeName;
        boolean isValid;

        do {
            System.out.print("Enter programme name (eg: RSD): ");
            programmeName= scanner.nextLine().trim().toUpperCase();

            // Validate alphabet input for programme name
            isValid = programmeName.matches("^[a-zA-Z]{3}$") ;
  
            if (!isValid) {
                MessageUI.displayErrorMessage("Invalid input. Please enter the correct programme name.");   
            }

        } while (!isValid);

        return programmeName;
  }

public String inputStudentEmail() {
    String studentEmail;
    boolean isValid;

    do {
        System.out.print("Enter Student Email (eg: ***@gmail.com): ");
        studentEmail = scanner.nextLine().trim();

        // Validate alphanumeric input for email with any symbol in front and ending with @gmail.com
        isValid = studentEmail.matches("^[\\w\\d\\W]+@gmail\\.com$");

        if (!isValid) {
             MessageUI.displayErrorMessage("Invalid input. Please use a valid email format that end with @gmail.com.");
        }

    } while (!isValid);

    return studentEmail;
}


public String inputStudentPhoneNo() {
    String phoneNo;
    boolean isValid;
    
    do {
        System.out.print("Enter student phone number (eg: 011********/01********): ");
        phoneNo = scanner.nextLine().trim();

        // Validate phone number format
        isValid = phoneNo.matches("^(011\\d{8}|012\\d{7}|013\\d{7}|014\\d{7}|015\\d{7}|016\\d{7}|017\\d{7}|018\\d{7}|019\\d{7}|010\\d{7})$");
        
        if (!isValid) {
             MessageUI.displayErrorMessage("Invalid input. Please follow correct format.");         
        }  

    } while (!isValid);

    return phoneNo;
}





public void addstudentBanner(){
    System.out.println(" --------------------------");
    System.out.println(" |New Student Registrarion|");
    System.out.println(" --------------------------");  
}

public void liststudentBanner(){
    System.out.println("-----------------------------------------------------------------------------");
    System.out.println("|                          List Students For Courses                        |");
    System.out.println("-----------------------------------------------------------------------------");  
}

public void courseinfoBanner(){
    System.out.println(" ----------------------------");
    System.out.println(" |       Course Info        |");
    System.out.println(" ----------------------------");  
}






public void coursesBill(){
    System.out.println("=============================================================================");
    System.out.println("|                                Student Bill                               |");
}

public void studentIDConfirmationBanner(){
    System.out.println(" -------------------------");
    System.out.println(" |Student ID confirmation|");
    System.out.println(" -------------------------");  
}

public void amendmentBanner(){
    System.out.println(" -------------------------");
    System.out.println(" |   Amendment Options   |");
    System.out.println(" -------------------------");  
}


public void removestudentBanner(){
    System.out.println(" ------------------------------");
    System.out.println(" |Remove student by student ID|");
    System.out.println(" ------------------------------");  
}

public int getCoursesChoices() {
        System.out.println(" -----------------------------------------------------");
        System.out.println(" |Please select one courses from options above to add|");
        System.out.println(" -----------------------------------------------------");
        
    boolean correctInput = false;
    int choiceInt=0;

    do{
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        if(!checkDataType.isInteger(choice)){
             MessageUI.displayErrorMessage("Please enter integer only!");
        }else{
            choiceInt = Integer.parseInt(choice);
            break;
        }
    }while(!correctInput);  
    
    return choiceInt;
} 


public int getCoursesSummaryChoices() {
        System.out.println(" ---------------------------------------------------------------------");
        System.out.println(" |Please select one courses from options above to make summary report|");
        System.out.println(" ---------------------------------------------------------------------");
        
    boolean correctInput = false;
    int choiceInt=0;

    do{
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        if(!checkDataType.isInteger(choice)){
             MessageUI.displayErrorMessage("Please enter integer only!");
        }else{
            choiceInt = Integer.parseInt(choice);
            break;
        }
    }while(!correctInput);  
    
    return choiceInt;
} 



public int searchStudentForCoursesChoices() {
        System.out.println(" ---------------------------------------");
        System.out.println(" |Search student for registered courses|");
        System.out.println(" ---------------------------------------");   
        System.out.println("1. Search course by studentID");
        System.out.println("2. Search course by student name");
        
        boolean correctInput = false;
        int choiceInt=0;

    do{
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        if(!checkDataType.isInteger(choice)){
             MessageUI.displayErrorMessage("Please enter integer only!");
        }else{
            choiceInt = Integer.parseInt(choice);
            break;
        }
    }while(!correctInput);  
    
    return choiceInt;
      
    } 

public int removeCoursesChoices() {
        System.out.println(" ----------------------------------------------");
        System.out.println(" |Please remove one courses from options above|");
        System.out.println(" ----------------------------------------------");
        
        boolean correctInput = false;
        int choiceInt=0;

    do{
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        if(!checkDataType.isInteger(choice)){
             MessageUI.displayErrorMessage("Please enter integer only!");
        }else{
            choiceInt = Integer.parseInt(choice);
            break;
        }
    }while(!correctInput);  
    
    return choiceInt;
    } 



public int getAmendmentChoice() {
        System.out.println("Choose an option to amend: ");
        System.out.println("1. Student Name");
        System.out.println("2. Student Gender");
        System.out.println("3. Student Email");
        System.out.println("4. Student Phone number");
        
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        int choiceInt=0;
        
        if(!checkDataType.isInteger(choice)){
            System.out.println("Only integer value.");
        }else{
            choiceInt = Integer.parseInt(choice);
        }
        
        return choiceInt;
    }

public int getcourseStatus() {
        System.out.println("Choose course status ");
        System.out.println("1. Main");
        System.out.println("2. Repeat");
        System.out.println("3. Resit");
        System.out.println("4. Elective");
        
        boolean correctInput = false;
        int choiceInt=0;

    do{
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        if(!checkDataType.isInteger(choice)){
             MessageUI.displayErrorMessage("Please enter integer only!");
        }else{
            choiceInt = Integer.parseInt(choice);
            break;
        }
    }while(!correctInput);  
    
    return choiceInt;
    }

public void summaryBanner1() {
     System.out.println("==========================================================================================");
     System.out.println("|                                Summary Report By Gender                                |");
     System.out.println("==========================================================================================");
}

public void summaryBanner2() {
     System.out.println("==============================================================================================");
     System.out.println("|                                Summary Report By Course                                    |");
     System.out.println("==============================================================================================\n");
}


public int summaryreportChoice() {
    //clearScreen.clrscreen();
    System.out.println(" ---------------------------------");
    System.out.println(" |          Summary report       |");
    System.out.println(" ---------------------------------"); 
    System.out.println("1. Summary report by gender");
    System.out.println("2. Summary report by course");

    boolean correctInput = false;
    int choiceInt=0;

    do{
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        if(!checkDataType.isInteger(choice)){
            MessageUI.displayErrorMessage("Please enter integer only!");

        }else{
            choiceInt = Integer.parseInt(choice);
            break;
        }
    }while(!correctInput);
    
    
    return choiceInt;
  }

    public void listAllStudents(String outputStr) {
    System.out.println("\nList of Students :\n" + outputStr);
  }
}
