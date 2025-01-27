package control;

import adt.ListInterface;
import adt.ArrayList;
import boundary.StudentRegistrationManagementUI;
import entity.Student;
import dao.Initializer;
import entity.Course;
import utility.BeforeContinue;
import utility.ClearScreen;
import utility.UI_messages;

/** @author Wong Weng Sam
 */
public class StudentRegistrationManagement {
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<Course> coursesRegistered = new ArrayList<>();
    private ListInterface<String> allCoursesNamesAndCodes = new ArrayList<>();
    ListInterface<String> coursesNamesAndCodes = new ArrayList<>();
    private Initializer initializer = new Initializer();
    private StudentRegistrationManagementUI studentRegistration = new StudentRegistrationManagementUI();
    private UI_messages MessageUI = new UI_messages();
    private BeforeContinue beforeContinue = new BeforeContinue();
    private ClearScreen clearScreen = new ClearScreen();


   //*finished*
   public void runStudentRegistrationMenu() throws InterruptedException{  
        int choice;
        boolean valid = true;
        do {
             if(valid){
                clearScreen.clrscreen();
            } else{
                 MessageUI.displayErrorMessage("Please select the correct range (0-8)");
                 beforeContinue.pressEnterKeyBeforeContinue();
                 clearScreen.clrscreen();
             }
            choice = studentRegistration.getMenuChoice();  
            valid = true;
            switch (choice) {
                case 0:
                    MessageUI.displaySuccessMessage("Exiting the program......");
                    beforeContinue.pressEnterKeyBeforeContinue();
                    clearScreen.clrscreen();
                    
                    break;
                case 1:
                    clearScreen.clrscreen();
                    addNewStudent();
                    break;
                case 2:
                    clearScreen.clrscreen();
                    removeStudent();
                    break;
                case 3:
                    clearScreen.clrscreen();
                    ammendstudentDetails();
                     // Ammend student details;
                    break;
                case 4:
                    clearScreen.clrscreen();
                     searchStudentByCourse();
                    // search students for registered courses;
                    break;
                case 5:
                    clearScreen.clrscreen();
                     addStudentsToCourses();
                    // Add students to a course(main, resit, repeat);
                    break;
                case 6:
                    clearScreen.clrscreen();
                    removeStudentsFromCourses();
                    // Remove students from a course(main, resit, elective);
                    break;
                case 7:
                    clearScreen.clrscreen();
                     getAllStudents();
                    // List students for courses
                     break;
                case 8:
                    clearScreen.clrscreen();
                    generateSummaryReport();
                    // Generate summary report
                    break;

                default:                   
                    valid = false;
                    break;
            }
            
        } while (choice != 0);
        
    }
    
//*finished*
public void generateSummaryReportByGender() throws InterruptedException {
    ListInterface<String> uniqueCourses = getAllUniqueCourses();

    studentRegistration.summaryBanner1() ;

    // Print column headers with format
    System.out.printf("\n%-45s%-10s%-10s%-15s%-15s%n", "Course List", "Male", "Female", "Male(%)", "Female(%)");
     System.out.println("=========================================================================================");

    int numRows = uniqueCourses.getNumberOfEntries();
    int numCols = 5; // Number of data columns (Male, Female, Male %, Female %)

    // Create arrays to store data and totals
    int[][] data = new int[numRows][2]; // Male and Female counts
    double[] rowPercentages = new double[numRows]; // Row percentages
    int[] colTotals = new int[numCols - 2]; // Column totals (excluding percentages)

    // Populate data and calculate row totals
    for (int i = 0; i < numRows; i++) {
        String course = uniqueCourses.getEntry(i + 1);
        int[] genderCounts = getGenderCountsForCourse(course);
        double malePercentage = calculatePercentage(genderCounts[0], genderCounts[0] + genderCounts[1]);
        double femalePercentage = calculatePercentage(genderCounts[1], genderCounts[0] + genderCounts[1]);

        // Store data in arrays
        data[i][0] = genderCounts[0];
        data[i][1] = genderCounts[1];
        rowPercentages[i] = malePercentage;

        // Update column totals
        colTotals[0] += genderCounts[0];
        colTotals[1] += genderCounts[1];
    }

    // Print data and row percentages with format
    for (int i = 0; i < numRows; i++) {
        System.out.printf("%-45s%-10d%-10d%-15.2f%-15.2f%n",
                uniqueCourses.getEntry(i + 1),
                data[i][0], data[i][1], rowPercentages[i], 100 - rowPercentages[i]);
    }

    // Print column totals with format
    System.out.println("=========================================================================================");
    beforeContinue.pressEnterKeyBeforeContinue();
    
}


// *finished*
public void generateSummaryReportByCourse() throws InterruptedException {
    boolean isValidChoice = false;

    while (!isValidChoice) {
        studentRegistration.courseinfoBanner();
        displayallCourses();
        int courseChoice = studentRegistration.getCoursesSummaryChoices();

        if (courseChoice < 1 || courseChoice > allCoursesNamesAndCodes.getNumberOfEntries()) {
            MessageUI.displayErrorMessage("Invalid course choice. Please choose a valid course.");
            beforeContinue.pressEnterKeyBeforeContinue();
            clearScreen.clrscreen();
        } else {
            clearScreen.clrscreen();
            String studentChoice = allCoursesNamesAndCodes.getEntry(courseChoice);
            String course = "";
            int studentCount = 0;

            for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                Student student = studentList.getEntry(i);

                for (int l = 1; l <= student.getCoursesNamesAndCodes().getNumberOfEntries(); l++) {
                    course = student.getCoursesNamesAndCodes().getEntry(l);
                    String courseInfo = course;
                    int lastIndex = courseInfo.lastIndexOf('(');

                    // Check if "(" is found
                    if (lastIndex != -1) {
                        // Extract the substring from the beginning to the last occurrence of "("
                        course = courseInfo.substring(0, lastIndex).trim();
                    }

                    if (course.equals(studentChoice)) {
                        studentCount++;
                        break;
                    }
                }
            }

            double price = 0;
            switch (studentChoice.charAt(7)) {
                case '2' -> price = 518;
                case '3' -> price = 777;
                case '4' -> price = 1036;
            }

            double totalProfit = studentCount * price;

            // Display the output in table form
            studentRegistration.summaryBanner2();
            System.out.println("=".repeat(94));
            System.out.printf("%-45s  %-10s  %-15s  %-15s%n", "Course Selected", "Price (RM)", "Student Count", "Total Profit (RM)");
            System.out.println("=".repeat(94));
            System.out.printf("%-45s  %-10.2f  %-15d  %-15.2f%n \n", studentChoice, price, studentCount, totalProfit);
            System.out.println("=".repeat(94));
            beforeContinue.pressEnterKeyBeforeContinue();
            clearScreen.clrscreen();

            isValidChoice = true; // Set to true to exit the loop
        }
    }
}




//*finished*
public void generateSummaryReport() throws InterruptedException {
    int summaryreportChoice;

    do {
        summaryreportChoice = studentRegistration.summaryreportChoice();

        switch (summaryreportChoice) {
            case 1:
                clearScreen.clrscreen();
                generateSummaryReportByGender();
                break;
            case 2:
                clearScreen.clrscreen();
                generateSummaryReportByCourse();
                break;

            default:
                MessageUI.displayErrorMessage("Please select the correct range (1 or 2)");
                beforeContinue.pressEnterKeyBeforeContinue();
                clearScreen.clrscreen();
        }
    } while (summaryreportChoice != 1 && summaryreportChoice != 2);
}

private int[] getGenderCountsForCourse(String course) {
    // use an array to store the count of male and female students (Index 0 for male, 1 for female)
    int[] genderCounts = new int[2];

    // Iterate through each student in the studentList
    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        // Get the current student
        Student student = studentList.getEntry(i);

        // Iterate through each course in the student's list of course names and codes
        for (int j = 1; j <= student.getCoursesNamesAndCodes().getNumberOfEntries(); j++) {
            // Get the course information for the current student and course
            String courseInfo = student.getCoursesNamesAndCodes().getEntry(j);

            // Find the index of the last occurrence of "("
            int lastIndex = courseInfo.lastIndexOf('(');

            // Check if "(" is found
            if (lastIndex != -1) {
                // Extract the substring from the beginning to the last occurrence of "("
                String courseCodeAndName = courseInfo.substring(0, lastIndex).trim();

                // Check if the current course matches the specified course code and name
                if (course.equals(courseCodeAndName)) {
                    // Check the gender of the student and update the count in the array
                    if ("MALE".equalsIgnoreCase(student.getGender())) {
                        genderCounts[0]++; // Increment of male count
                    } else if ("FEMALE".equalsIgnoreCase(student.getGender())) {
                        genderCounts[1]++; // Increment of female count
                    }
                }
            }
        }
    }

    // Return the array containing the count of male and female students
    return genderCounts;
}

private double calculatePercentage(int count, int total) {
    // Calculate the percentage and return it as a double
    if (total == 0) {
        return 0.0; // To avoid division by zero
    } else {
        return ((double) count / total) * 100;
    }
}




private ListInterface<String> getAllUniqueCourses() {
    ListInterface<String> uniqueCourses = new ArrayList<>();

    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        Student student = studentList.getEntry(i);

        for (int j = 1; j <= student.getCoursesNamesAndCodes().getNumberOfEntries(); j++) {
            String courseInfo = student.getCoursesNamesAndCodes().getEntry(j);

            // Find the index of the last occurrence of "("
            int lastIndex = courseInfo.lastIndexOf('(');

            // Check if "(" is found
            if (lastIndex != -1) {
                // Extract the substring from the beginning to the last occurrence of "("
                String courseCodeAndName = courseInfo.substring(0, lastIndex).trim();

                // Now courseCodeAndName contains the part before the last "("

                // Check if the extracted course code and name is not already in the list
                if (!uniqueCourses.contains(courseCodeAndName)) {
                    uniqueCourses.add(courseCodeAndName);
                }
            }
        }
    }

    return uniqueCourses;
}


    
    
    // *finished*
   public void searchStudentByCourse() throws InterruptedException{

    int searchChoice = studentRegistration.searchStudentForCoursesChoices();

    switch (searchChoice) {
        case 1:
            String studentId = studentRegistration.inputStudentID();
            boolean studentFound = false;

            for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                Student student = studentList.getEntry(i);

                if (studentId.equals(student.getStudentID())) {
                    if (!student.getCoursesNamesAndCodes().isEmpty()) {
                        clearScreen.clrscreen();
                        System.out.println("=========================================================");
                        System.out.println("The student with ID " + "(" + studentId + ")" + " has taken below courses:");
                        System.out.println("=========================================================");
                        System.out.println(student.getCoursesNamesAndCodes() + "\n");
                    } else {
                        MessageUI.displayErrorMessage("The student with ID " + "(" + studentId + ")" + " has not registered for any courses yet.");
                    }
                     studentFound = true;
                     beforeContinue.pressEnterKeyBeforeContinue();
                     break;
                }
            }

            if (!studentFound) {
                MessageUI.displayErrorMessage("Student ID does not exist");
                beforeContinue.pressEnterKeyBeforeContinue();   
            }
            break;

        case 2:
            String studentName = studentRegistration.inputStudentName();
            boolean studentNameFound = false;

            for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                Student student = studentList.getEntry(i);

                if (studentName.equals(student.getName())) {
                    if (!student.getCoursesNamesAndCodes().isEmpty()) {
                        clearScreen.clrscreen();
                        System.out.println("==========================================================");
                        System.out.println("The student " + "(" + studentName + ")" + " has taken below courses:");
                        System.out.println("==========================================================");
                        System.out.println(student.getCoursesNamesAndCodes());
                    } else {
                        MessageUI.displayErrorMessage("The student " + "(" + studentName + ")" + " has not registered for any courses yet.");     
                    }
                    studentNameFound = true;
                    beforeContinue.pressEnterKeyBeforeContinue();
                    break; 
                }
            }

            if (!studentNameFound) {
                MessageUI.displayErrorMessage("Student name does not exist");
                beforeContinue.pressEnterKeyBeforeContinue();
                
            }
            break;

        default:
            MessageUI.displayErrorMessage("Please select the correct range (1 or 2) only ");
            beforeContinue.pressEnterKeyBeforeContinue();
            clearScreen.clrscreen();
            studentRegistration.searchStudentForCoursesChoices();
    }
}

    
    
    
    
    public void getallCourses() throws InterruptedException{
       for(int i=1; i <= coursesRegistered.getNumberOfEntries(); i++){
            String str = (coursesRegistered.getEntry(i).getCourseCode() + " - " + coursesRegistered.getEntry(i).getCourseName());
             allCoursesNamesAndCodes.add(str) ;
    }     
       
       
    }

    public void displayallCourses() throws InterruptedException{
       for(int i=1; i <= allCoursesNamesAndCodes.getNumberOfEntries(); i++){
            System.out.print(i + " ." + allCoursesNamesAndCodes.getEntry(i) + "\n");
    }
       
       
    }
    
    // finished
    
   public void addStudentsToCourses() throws InterruptedException {
    boolean success = false;
    studentRegistration.studentIDConfirmationBanner();
    String studentId = studentRegistration.inputStudentID();
    
    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        Student student = studentList.getEntry(i);
        if (studentId.equals(student.getStudentID())) {
            success = true;
            clearScreen.clrscreen();
            do {
               clearScreen.clrscreen();
                studentRegistration.courseinfoBanner();
                displayallCourses();
                
                
                int courseChoice = studentRegistration.getCoursesChoices();
                
                 if (courseChoice < 1 || courseChoice > allCoursesNamesAndCodes.getNumberOfEntries()) {
                    MessageUI.displayErrorMessage("Invalid course choice. Please choose a valid course.");
                    beforeContinue.pressEnterKeyBeforeContinue();
                    continue;
                }
                 
                 String studentChoice = allCoursesNamesAndCodes.getEntry(courseChoice);
                 String course="";
                 boolean existed = false;
                 for(int l=1; l <= student.getCoursesNamesAndCodes().getNumberOfEntries(); l++){
                    course = student.getCoursesNamesAndCodes().getEntry(l);
                    String courseStr="";
                    
                    for(int k = 0; k < studentChoice.length(); k++){
                        courseStr += student.getCoursesNamesAndCodes().getEntry(l).charAt(k);
                        if(k == (course.length()-1)){
                            break;
                        }
                    }
                     if(studentChoice.equals(courseStr)){
                         existed = true;
                         break;
                     }else{
                         existed = false;
                     }
                 }
                 if(existed){
                       MessageUI.displayErrorMessage("Course has been in the list, please add another course!");   
                       beforeContinue.pressEnterKeyBeforeContinue();
                       continue;    
                 }else{
                    int status = studentRegistration.getcourseStatus();
                    switch (status) {
                        case 1:
                            student.addcoursesNamesAndCodes(allCoursesNamesAndCodes.getEntry(courseChoice) + " (MAIN) ");
                            break;
                        case 2:
                             student.addcoursesNamesAndCodes(allCoursesNamesAndCodes.getEntry(courseChoice) + " (REPEAT) ");
                            break;
                        case 3:
                             student.addcoursesNamesAndCodes(allCoursesNamesAndCodes.getEntry(courseChoice) + " (RESIT) ");
                            break;
                        case 4:
                             student.addcoursesNamesAndCodes(allCoursesNamesAndCodes.getEntry(courseChoice) + " (ELECTIVE) ");
                            break;
                    }
                    
                    MessageUI.displaySuccessMessage("Course added successfully!");
                    System.out.print("Do you want to add another course: ");
                    boolean continueOption = beforeContinue.confirmationBeforeContinue();
                    clearScreen.clrscreen();
                    if(!continueOption) {
                        studentRegistration.coursesBill();
                        System.out.println(student);
                        int price=0;
                        String outputStr = "";
                         for(int j=1; j <= student.getCoursesNamesAndCodes().getNumberOfEntries(); j++){
                        String str = student.getCoursesNamesAndCodes().getEntry(j);
                        //check the price and sum up as total
                        switch(str.charAt(7)){
                            case '2' -> price += 518;
                            case '3' -> price += 777;
                            case '4' -> price += 1036;
                        }  
                    }
                        outputStr += ("Total course prices: RM " + price + ".00" + "\n" + "=============================================================================\n" );
                        System.out.println(outputStr);
                        beforeContinue.pressEnterKeyBeforeContinue();
                    break;
                    }
                }
                } while (true);
            
            
        }
    }

    if (!success) {
        MessageUI.displayErrorMessage("Courses added failed. No student ID matches.");
        beforeContinue.pressEnterKeyBeforeContinue();
        
    }
}
   
   
   // *finished*
 public void removeStudentsFromCourses() throws InterruptedException {
    boolean success = false;
    studentRegistration.studentIDConfirmationBanner();
    String studentId = studentRegistration.inputStudentID();

    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        Student student = studentList.getEntry(i);
        if (studentId.equals(student.getStudentID())) {
            success = true;

            // Check if the courses list is empty
            if (student.getCoursesNamesAndCodes().isEmpty()) {
                MessageUI.displayErrorMessage("No courses in the list to be removed.");
                beforeContinue.pressEnterKeyBeforeContinue();
                
                return; 
            }
            
            clearScreen.clrscreen();
            do {
                
                studentRegistration.courseinfoBanner();

                for (int h = 1; h <= student.getCoursesNamesAndCodes().getNumberOfEntries(); h++) {
                    System.out.println(h + ". " + student.getCoursesNamesAndCodes().getEntry(h));
                }

                int courseChoice = studentRegistration.removeCoursesChoices();
                student.removecoursesNamesAndCodes(courseChoice);
                MessageUI.displaySuccessMessage("Course removed successfully!");
                // Check if the courses list is empty after removal
                if (student.getCoursesNamesAndCodes().isEmpty()) {
                    MessageUI.displayErrorMessage("The course list is empty. Returning to the menu.");
                    beforeContinue.pressEnterKeyBeforeContinue();
                    
                    return; // exit the method
                }

                System.out.print("Do you want to remove another course: ");
                boolean continueOption = beforeContinue.confirmationBeforeContinue();
                clearScreen.clrscreen();

                if (!continueOption) {
                    
                    break;
                }

            } while (true);
        }
    }

    if (!success) {
        MessageUI.displayErrorMessage("Courses removal failed. No student ID matches.");
        beforeContinue.pressEnterKeyBeforeContinue();
    }
}


  // *finished*
  public void getAllStudents() throws InterruptedException{
    if (studentList.isEmpty()) {
         MessageUI.displayErrorMessage("No records have been added to the student list!");
        studentRegistration.getMenuChoice();
    } else {
        studentRegistration.liststudentBanner();
        String outputStr = "";
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {      
            outputStr += studentList.getEntry(i) + "\n";
            int price=0;
            for(int j=1; j <= studentList.getEntry(i).getCoursesNamesAndCodes().getNumberOfEntries(); j++){
                String str = studentList.getEntry(i).getCoursesNamesAndCodes().getEntry(j);
                //check the price and sum up as total
                switch(str.charAt(7)){
                    case '2' -> price += 518;
                    case '3' -> price += 777;
                    case '4' -> price += 1036;
                }
            }
            outputStr += ("Total course prices: RM " + price + ".00" + "\n" + "=============================================================================\n" );
        }
        System.out.println(outputStr);
        beforeContinue.pressEnterKeyBeforeContinue();
        
    }
}
  
// *finished*
    public void addNewStudent() throws InterruptedException {
        boolean validstudentID;
        String newstudentID = "";
        studentRegistration.addstudentBanner();
        do{
             newstudentID = studentRegistration.inputStudentID();
            if(isStudentIdExists(newstudentID)){
                MessageUI.displayErrorMessage("Student ID has been registered");  
                beforeContinue.pressEnterKeyBeforeContinue();
                return;
                //need to add something
                
            }else{
                 validstudentID = true;       
            }
        }while(!validstudentID);
        String newstudentName = studentRegistration.inputStudentName();
        String newstudentGender = studentRegistration.inputGender();
        String newstudentEmail = studentRegistration.inputStudentEmail();
        String newstudentphoneNumber = studentRegistration.inputStudentPhoneNo();
        Student newStudent = new Student(newstudentID, newstudentName, newstudentGender, newstudentEmail,newstudentphoneNumber);      
        studentList.add(newStudent);
        studentList.arrangeArrayByOrder();
        MessageUI.displaySuccessMessage(" A New Student has been added into the list !");   
                System.out.print("Do you want to add another student: ");
                boolean continueOption = beforeContinue.confirmationBeforeContinue();
                

                if (continueOption) {
                    clearScreen.clrscreen();
                    addNewStudent();
                }
                }
    
    private boolean isStudentIdExists(String studentId) {
    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        // Assuming getStudentID is the method to retrieve the student ID
        if (studentId.equals(studentList.getEntry(i).getStudentID())) {
            return true; // ID already exists
        }
    }
    return false; // ID does not exist
}
    
    
    // *finished*
    public void removeStudent() throws InterruptedException {
    studentRegistration.removestudentBanner();

    if (studentList.isEmpty()) {
        MessageUI.displaySuccessMessage("No students in the list. Returning to the menu.");
        beforeContinue.pressEnterKeyBeforeContinue(); 
        return;
    }

    boolean success = false;
    String studentId = studentRegistration.inputStudentID();

    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        Student student = studentList.getEntry(i);
        if (studentId.equals(student.getStudentID())) {
            success = true;
            clearScreen.clrscreen();
            MessageUI.displaySuccessMessage("Removing in progress......");
            System.out.println(student.toString());
            studentList.remove(i);
            System.out.println("=============================================================================");
            MessageUI.displaySuccessMessage("Student removed successfully.\n");


            if (studentList.isEmpty()) {
                MessageUI.displaySuccessMessage("No more students in the list. Returning to the menu.");
                beforeContinue.pressEnterKeyBeforeContinue();
                return;
            }

            System.out.print("Do you want to remove another student: ");
            boolean continueOption = beforeContinue.confirmationBeforeContinue();
            clearScreen.clrscreen();
            if (continueOption) {
                removeStudent();
            }
        }
    }

    if (!success) {
        MessageUI.displayErrorMessage("No student found with the specified Student ID.");
        beforeContinue.pressEnterKeyBeforeContinue();
        
    }
}


 // * finished *
 public void ammendstudentDetails() throws InterruptedException {
    boolean success = false;
    studentRegistration.studentIDConfirmationBanner();
    String studentId = studentRegistration.inputStudentID();

    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        Student student = studentList.getEntry(i);
        if (studentId.equals(student.getStudentID())) {
            success = true;
            clearScreen.clrscreen();
            while (true) {
                studentRegistration.amendmentBanner();
                int amendmentChoice = studentRegistration.getAmendmentChoice();

                switch (amendmentChoice) {
                    case 1:
                        // Amend student name
                        String newStudentName = studentRegistration.inputStudentName();
                        student.setName(newStudentName);
                        MessageUI.displaySuccessMessage("Student name amended successfully.");
                        beforeContinue.pressEnterKeyBeforeContinue();
                        clearScreen.clrscreen();
                        break;
                    case 2:
                        // Amend student gender
                        String newStudentGender = studentRegistration.inputGender();
                        student.setGender(newStudentGender);
                        MessageUI.displaySuccessMessage("Student gender amended successfully.");
                        beforeContinue.pressEnterKeyBeforeContinue();
                        clearScreen.clrscreen();
                        break;                       
                    case 3:
                        // Amend student email
                        String newStudentEmail = studentRegistration.inputStudentEmail();
                        student.setStudentemail(newStudentEmail);
                        MessageUI.displaySuccessMessage("Student email amended successfully.");
                        beforeContinue.pressEnterKeyBeforeContinue();
                        clearScreen.clrscreen();
                        break;
                    case 4:
                        // Amend student phone number
                        String newPhoneNumber = studentRegistration.inputStudentPhoneNo();
                        student.setPhoneNo(newPhoneNumber);
                        MessageUI.displaySuccessMessage("Student phone number amended successfully.");
                        beforeContinue.pressEnterKeyBeforeContinue();
                        clearScreen.clrscreen();
                        break;
                    default:
                        MessageUI.displayErrorMessage("Please select the correct range (1-4)");
                        continue;  
                }
                break;  // Exit the loop once the student is found and amended.
            }
            break;  // Exit the outer loop once the student is found.
        }
    }

    if (!success) {
        MessageUI.displayErrorMessage("Amendment failed. No student ID matches.");
        beforeContinue.pressEnterKeyBeforeContinue();
        
    }
}


 public void initialize(Object[] systemDataArray) throws InterruptedException{  
        coursesRegistered =  (ListInterface<Course>) systemDataArray[1];
        studentList =  (ListInterface<Student>) systemDataArray[3];
        getallCourses();
 
 }
public void updateSystem(Object[] systemDataArray){
    systemDataArray[3] = studentList;
}
 
  public static void main(Object[] systemDataArray) throws InterruptedException {
   StudentRegistrationManagement studentRegistrationManagement = new StudentRegistrationManagement();
   studentRegistrationManagement.initialize(systemDataArray);
   studentRegistrationManagement.runStudentRegistrationMenu();
   studentRegistrationManagement.updateSystem(systemDataArray);
   }
}
