package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.TutorialManagementUI;
import dao.Initializer;
import entity.Tutorial;
import entity.Student;
import entity.ProgrammeClass;
import utility.MessageUI;
import java.util.Scanner;
import main.mainMenuUI;
import utility.ClearScreen;

/**
 *
 * @author Vanness Chaw Jun Kit
 */

public class TutorialManagement {

  private ListInterface<Tutorial> tutorialList = new ArrayList<>();
  private ListInterface<Student> studentList = new ArrayList<>();
  private ListInterface<ProgrammeClass> programList = new ArrayList<>();
  private TutorialManagementUI tutorialUI = new TutorialManagementUI();
  private Initializer initial = new Initializer();
  Scanner scanner = new Scanner(System.in);
  private ClearScreen clearScreen = new ClearScreen();
  
   public void runTutorialManagement() throws InterruptedException {
    int choice =  0;
    
    do {
      choice = tutorialUI.getMenuChoice();
      switch(choice) {
        case 0:
            clearScreen.clrscreen();
            MessageUI.displayExitMessage();
            break;
        case 1:
            clearScreen.clrscreen();
            tutorialUI.studentListBanner();
            tutorialUI.listAllStud(getAllStudents());
            addNewStudent();
            break;
        case 2:
            clearScreen.clrscreen();
            removeStudent();
            break;
        case 3:
            clearScreen.clrscreen();
            chageTutGroupForStudent();
            break;
        case 4:
            clearScreen.clrscreen();
            findStudent();
            break;
        case 5:
            clearScreen.clrscreen();
            listStudentInGroup();
            break;
        case 6:
            clearScreen.clrscreen();
            filterTutGroup();
            break;
        case 7:
            clearScreen.clrscreen();
            mergeTutGroup();
            break;
        case 8:
            clearScreen.clrscreen();
            report();
            break;
        default:
            clearScreen.clrscreen();
            MessageUI.displayInvalidChoiceMessage();
      } 
    } while (choice != 0);
  }
   
   
   public void report() throws InterruptedException{
       tutorialUI.reportSubMenu();
       int choice;
        System.out.print("Enter choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        
        if(choice == 1){
            clearScreen.clrscreen();
            generateSummaryReportByTutGroup();
        }else if(choice == 2){
            clearScreen.clrscreen();
            generateSummaryReportByProgramme();
        }else{
            clearScreen.clrscreen();
            runTutorialManagement();
        }
        
        
        System.out.print("\nDo you want to continue ? [Y/N] : ");
        String yorN = scanner.nextLine();
        if(yorN.equals("Y") || yorN.equals("y")){
            clearScreen.clrscreen();
            report();
        }else{
            clearScreen.clrscreen();
            runTutorialManagement();
        }
   }
   
   
  public void generateSummaryReportByTutGroup() throws InterruptedException {
    tutorialUI.ReportBanner();

    // Print column headers with format
    System.out.printf("\n%-45s%-10s%15s%15s", "Tutorial Group", "Total Students", "Male", "Female");
    System.out.println("\n=========================================================================================");

    for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
        Tutorial currentTutorialGroup = tutorialList.getEntry(i);

        int totalStudents = currentTutorialGroup.getStudentList().getNumberOfEntries();
        int maleCount = 0;
        int femaleCount = 0;

        // Count male and female students
        for (int j = 1; j <= totalStudents; j++) {
            if (currentTutorialGroup.getStudentList().getEntry(j).getGender().equalsIgnoreCase("Male")) {
                maleCount++;
            } else {
                femaleCount++;
            }
        }

        // Print tutorial group summary
        System.out.printf("%-45s%-10d%15d%15d\n", currentTutorialGroup.getTutorialGroupID(),
                totalStudents, maleCount, femaleCount);
    }

    System.out.println("=========================================================================================");
}
  
  public void generateSummaryReportByProgramme() throws InterruptedException {
    tutorialUI.ReportBanner2();

    // Print column headers with format
    System.out.printf("\n%-45s%-10s%15s%15s%20s%20s", "Tutorial Group", "Total Students", "Male", "Female", "Male Percentage", "Female Percentage");
    System.out.println("\n=================================================================================================================================");

    String[] selectedTutorialGroups = {"RAC", "RDS", "RIT", "RPY", "RSD"};

    for (String selectedGroup : selectedTutorialGroups) {
        int totalStudents = 0;
        int maleCount = 0;
        int femaleCount = 0;

        // Iterate through tutorial groups and find the selected one
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            Tutorial currentTutorialGroup = tutorialList.getEntry(i);
            if (currentTutorialGroup.getTutorialGroupID().startsWith(selectedGroup)) {
                totalStudents += currentTutorialGroup.getStudentList().getNumberOfEntries();

                // Count male and female students in the selected tutorial group
                for (int j = 1; j <= currentTutorialGroup.getStudentList().getNumberOfEntries(); j++) {
                    if (currentTutorialGroup.getStudentList().getEntry(j).getGender().equalsIgnoreCase("Male")) {
                        maleCount++;
                    } else {
                        femaleCount++;
                    }
                }
            }
        }

        // Calculate percentages, handling zero division
        double malePercentage = totalStudents > 0 ? (double) maleCount / totalStudents * 100 : 0;
        double femalePercentage = totalStudents > 0 ? (double) femaleCount / totalStudents * 100 : 0;

        // Print summary for the selected tutorial group
        System.out.printf("%-45s%-10d%15d%15d%20.2f%%%20.2f%%\n", selectedGroup, totalStudents, maleCount, femaleCount, malePercentage, femalePercentage);
    }

    System.out.println("=================================================================================================================================");
}



   
  
    public void filterTutGroup() throws InterruptedException{
        int filterChoice = tutorialUI.FilterMenuChoice();
        if(filterChoice == 1){
        
            tutorialUI.programmeList();

            String prevPrefix = ""; // Variable to store the first three characters of the previous tutorial group
            String[] allTutorialGRoupList = new String[tutorialList.getNumberOfEntries()];
            int j = 1;
            for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {

            String currentGroupID = tutorialList.getEntry(i).getTutorialGroupID();
            String currentPrefix = currentGroupID.substring(0, 3);

            // Check if the first three characters are the same as the previous one
            if (!currentPrefix.equals(prevPrefix)) {
                allTutorialGRoupList[j-1] = currentPrefix;
                prevPrefix = currentPrefix; // Update the previous prefix
                j++;
            }
            
            }

            String[] tutorialGroupWithoutNumberOfGroup = new String[j-1];
            for(int g=0; g < (j-1); g++){
                tutorialGroupWithoutNumberOfGroup[g] = allTutorialGRoupList[g];
            }

            for(int k=0; k < tutorialGroupWithoutNumberOfGroup.length; k++){
                System.out.println((k+1) + ". " + tutorialGroupWithoutNumberOfGroup[k]);
            }

            int i;
            int choice = tutorialUI.selectChoice();
            String currentTut = ""; 

            String tutGroup = tutorialGroupWithoutNumberOfGroup[choice-1];
            String [] currentTutGroupArr = new String[tutorialGroupWithoutNumberOfGroup.length];
            int g=0;
            for(int h=1; h <= tutorialList.getNumberOfEntries(); h++){
                currentTut = tutorialList.getEntry(h).getTutorialGroupID();
                if(currentTut.contains(tutGroup)){
                    g++;

                    currentTutGroupArr[g-1] = currentTut;

                }
            }
            String [] tutorialGroupUnderAProgramme = new String[g];

            for(int y=0; y < tutorialGroupUnderAProgramme.length; y++){
                tutorialGroupUnderAProgramme[y] = currentTutGroupArr[y];
            }
            int t;
            for(t=0; t <tutorialGroupUnderAProgramme.length; t++){
                System.out.println( tutorialGroupUnderAProgramme[t]);

            }
            System.out.println("\nTotal Tutorial Group In This Programme is " + (t));

            System.out.print("\nDo you want to continue ? [Y/N] : ");
            String yorN = scanner.nextLine();
            if(yorN.equals("Y") || yorN.equals("y")){
                clearScreen.clrscreen();
                filterTutGroup();
            }else{
                clearScreen.clrscreen();
                runTutorialManagement();
            }
            //}else if(choice == 2){
            //System.out.println("2");
           
        }else if(filterChoice == 0){
            clearScreen.clrscreen();
            runTutorialManagement();
           
        }else{
            clearScreen.clrscreen();
            System.out.print("\nPlease enter following the list\n");
            filterTutGroup();
        }
        
        
    }

public void addNewStudent() throws InterruptedException{

    boolean validStudent = false;
    String newstudentID;
    Student student = new Student();
    do{
        newstudentID = tutorialUI.addStudentToGroup();
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            if(!newstudentID.toUpperCase().equals(studentList.getEntry(i).getStudentID())){
                validStudent = false;
            }else{
                validStudent = true;
                student = studentList.getEntry(i);
                break;
            }
        }   
        if (validStudent){
            break;
        }
    }while(validStudent = false);

    if(!validStudent){
        clearScreen.clrscreen();
        System.out.println("\nNo Student found\n");
        addNewStudent();
    }else{
        clearScreen.clrscreen();
        System.out.println("\nStudent Select Successful !!!");
    }

    int choice;
    String prevPrefix = ""; // Variable to store the first three characters of the previous tutorial group
    String[] allTutorialGRoupList = new String[tutorialList.getNumberOfEntries()];
    int j = 1;
    for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
        String currentGroupID = tutorialList.getEntry(i).getTutorialGroupID();
        String currentPrefix = currentGroupID.substring(0, 3);
        if (!currentPrefix.equals(prevPrefix)) {
            allTutorialGRoupList[j-1] = currentPrefix;
            prevPrefix = currentPrefix; // Update the previous prefix
            j++;
        }
    }

    tutorialUI.tutProgrammeBanner();
    String[] tutorialGroupWithoutNumberOfGroup = new String[j-1];
    for(int g=0; g < (j-1); g++){
        tutorialGroupWithoutNumberOfGroup[g] = allTutorialGRoupList[g];
    }

    for(int k=0; k < tutorialGroupWithoutNumberOfGroup.length; k++){
        System.out.println((k+1) + ". " + tutorialGroupWithoutNumberOfGroup[k]);
    }

    int i;
    choice = tutorialUI.selectChoice();
    String currentTut = ""; 
    String tutGroup = tutorialGroupWithoutNumberOfGroup[choice-1];
    String [] currentTutGroupArr = new String[tutorialList.getNumberOfEntries()];
    
    tutorialUI.tutorialGroupBanner();
    int g=0;
    for(int h=1; h <= tutorialList.getNumberOfEntries(); h++){
        currentTut = tutorialList.getEntry(h).getTutorialGroupID();
        if(currentTut.contains(tutGroup)){
            g++;
            currentTutGroupArr[g-1] = currentTut;

        }
    }
    String [] tutorialGroupUnderAProgramme = new String[g];

    for(int y=0; y < tutorialGroupUnderAProgramme.length; y++){
        tutorialGroupUnderAProgramme[y] = currentTutGroupArr[y];
    }

    for(int t=0; t <tutorialGroupUnderAProgramme.length; t++){
        System.out.println((t+1) + ". " + tutorialGroupUnderAProgramme[t]);
    }
    int tutChoice = tutorialUI.selectChoice();
    String selectedTutorialGroupID = tutorialGroupUnderAProgramme[tutChoice-1];

    int f=0;
    for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
        f++;
        if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupID)){
            break;
        }
    }
    clearScreen.clrscreen();
    tutorialList.getEntry(f).getStudentList().add(student);   
    Tutorial selectedTutGrp = tutorialList.getEntry(f);
    System.out.println(selectedTutGrp);
}

  
  public void removeStudent() throws InterruptedException{
    int choice;
    String removeID;
    tutorialUI.removeBanner();
    tutorialUI.programmeList();

    String prevPrefix = ""; // Variable to store the first three characters of the previous tutorial group
    String[] allTutorialGRoupList = new String[tutorialList.getNumberOfEntries()];
    int j = 1;
    for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
        String currentGroupID = tutorialList.getEntry(i).getTutorialGroupID();
        String currentPrefix = currentGroupID.substring(0, 3);

        if (!currentPrefix.equals(prevPrefix)) {
            allTutorialGRoupList[j-1] = currentPrefix;
            prevPrefix = currentPrefix; // Update the previous prefix
            j++;
        }
    }

    String[] tutorialGroupWithoutNumberOfGroup = new String[j-1];
    for(int g=0; g < (j-1); g++){
        tutorialGroupWithoutNumberOfGroup[g] = allTutorialGRoupList[g];
    }

    for(int k=0; k < tutorialGroupWithoutNumberOfGroup.length; k++){
        System.out.println((k+1) + ". " + tutorialGroupWithoutNumberOfGroup[k]);
    }

    int i;
    choice = tutorialUI.selectChoice();
    String currentTut = ""; 

    String tutGroup = tutorialGroupWithoutNumberOfGroup[choice-1];
    String [] currentTutGroupArr = new String[tutorialGroupWithoutNumberOfGroup.length];
    int g=0;
    tutorialUI.tutorialGroupBanner();
    for(int h=1; h <= tutorialList.getNumberOfEntries(); h++){
        currentTut = tutorialList.getEntry(h).getTutorialGroupID();
        if(currentTut.contains(tutGroup)){
            g++;

            currentTutGroupArr[g-1] = currentTut;

        }
    }
    String [] tutorialGroupUnderAProgramme = new String[g];
     
    

    for(int y=0; y < tutorialGroupUnderAProgramme.length; y++){
        tutorialGroupUnderAProgramme[y] = currentTutGroupArr[y];
    }

    for(int t=0; t <tutorialGroupUnderAProgramme.length; t++){
        System.out.println((t+1) + ". " + tutorialGroupUnderAProgramme[t]);
    }

    
    
    int tutChoice = tutorialUI.selectChoice();
    String selectedTutorialGroupID = tutorialGroupUnderAProgramme[tutChoice-1];
clearScreen.clrscreen();
    int f=0;
    for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
        f++;
        if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupID)){
            break;
        }
    }

    Tutorial selectedTutGrp = tutorialList.getEntry(f);
    System.out.println(selectedTutGrp);
    tutorialUI.RemoveStudentbanner();
    removeID = scanner.nextLine();
    clearScreen.clrscreen();
    boolean matchedStudent = false;
    int matchedStudentNumber = 1;
    for(int m=1; m <= selectedTutGrp.getStudentList().getNumberOfEntries();m++){
        matchedStudent = removeID.toUpperCase().equals(selectedTutGrp.getStudentList().getEntry(m).getStudentID().toUpperCase());
        
        if(matchedStudent){
            break;
        }
        matchedStudentNumber++; 
    }
    Student selectedStudent = selectedTutGrp.getStudentList().getEntry(matchedStudentNumber);
    selectedTutGrp.getStudentList().remove(matchedStudentNumber);
    System.out.println("Remove Successful.");  
    System.out.println(selectedTutGrp);
      
  }
  
  public String getAllStudents() throws InterruptedException{
    String outputStr = "";
    for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
        outputStr += studentList.getEntry(i).toStringTut()+ "\n";
    }
    return outputStr;
  }
  
  
  public void listStudentInGroup() throws InterruptedException{
        int choice;
        tutorialUI.listStudentBanner();
        tutorialUI.programmeList();
        
        String prevPrefix = ""; // Variable to store the first three characters of the previous tutorial group
        String[] allTutorialGRoupList = new String[tutorialList.getNumberOfEntries()];
        int j = 1;
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {            
            String currentGroupID = tutorialList.getEntry(i).getTutorialGroupID();
            String currentPrefix = currentGroupID.substring(0, 3);
            if (!currentPrefix.equals(prevPrefix)) {
                allTutorialGRoupList[j-1] = currentPrefix;
                prevPrefix = currentPrefix; // Update the previous prefix
                j++;
            }       
        }
        
        String[] tutorialGroupWithoutNumberOfGroup = new String[j-1];
        for(int g=0; g < (j-1); g++){
            tutorialGroupWithoutNumberOfGroup[g] = allTutorialGRoupList[g];
        }
        
        for(int k=0; k < tutorialGroupWithoutNumberOfGroup.length; k++){
            System.out.println((k+1) + ". " + tutorialGroupWithoutNumberOfGroup[k]);
        }
        
        int i;
        choice = tutorialUI.selectChoice();
        String currentTut = ""; 
        
        String tutGroup = tutorialGroupWithoutNumberOfGroup[choice-1];
        String [] currentTutGroupArr = new String[tutorialGroupWithoutNumberOfGroup.length];
        int g=0;
        for(int h=1; h <= tutorialList.getNumberOfEntries(); h++){
            currentTut = tutorialList.getEntry(h).getTutorialGroupID();
            if(currentTut.contains(tutGroup)){
                g++;
                currentTutGroupArr[g-1] = currentTut;
                
            }
        }
        String [] tutorialGroupUnderAProgramme = new String[g];
        tutorialUI.tutorialGroupBanner();
        for(int y=0; y < tutorialGroupUnderAProgramme.length; y++){
            tutorialGroupUnderAProgramme[y] = currentTutGroupArr[y];
        }
            
        for(int t=0; t <tutorialGroupUnderAProgramme.length; t++){
            System.out.println((t+1) + ". " + tutorialGroupUnderAProgramme[t]);
        }
        
        int tutChoice = tutorialUI.selectChoice();       
        String selectedTutorialGroupID = tutorialGroupUnderAProgramme[tutChoice-1];       
        int f=0;
        clearScreen.clrscreen();
        for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
            f++;
            if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupID)){
                break;
            }
        }        
        Tutorial selectedTutGrp = tutorialList.getEntry(f);       
        System.out.println(selectedTutGrp);  
        System.out.print("\nDo you want to continue ? [Y/N] : ");
        String yorN = scanner.nextLine();
        if(yorN.equals("Y") || yorN.equals("y")){
            clearScreen.clrscreen();
            listStudentInGroup();
        }else{
            clearScreen.clrscreen();
            runTutorialManagement();
        }
        
  }
  
  public void findStudent() throws InterruptedException{
    int choice;
    tutorialUI.findStudentBanner();
    tutorialUI.programmeList();

    String prevPrefix = ""; // Variable to store the first three characters of the previous tutorial group
    String[] allTutorialGRoupList = new String[tutorialList.getNumberOfEntries()];
    int j = 1;
    for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {

        String currentGroupID = tutorialList.getEntry(i).getTutorialGroupID();
        String currentPrefix = currentGroupID.substring(0, 3);

        // Check if the first three characters are the same as the previous one
        if (!currentPrefix.equals(prevPrefix)) {
            allTutorialGRoupList[j-1] = currentPrefix;
            prevPrefix = currentPrefix; // Update the previous prefix
            j++;
        }

    }
    

    String[] tutorialGroupWithoutNumberOfGroup = new String[j-1];
    for(int g=0; g < (j-1); g++){
        tutorialGroupWithoutNumberOfGroup[g] = allTutorialGRoupList[g];
    }

    for(int k=0; k < tutorialGroupWithoutNumberOfGroup.length; k++){
        System.out.println((k+1) + ". " + tutorialGroupWithoutNumberOfGroup[k]);
    }

    choice = tutorialUI.selectChoice();
    String currentTut = ""; 

    tutorialUI.tutorialGroupBanner();
    String tutGroup = tutorialGroupWithoutNumberOfGroup[choice-1];
    String [] currentTutGroupArr = new String[tutorialGroupWithoutNumberOfGroup.length];
    int g=0;
    for(int h=1; h <= tutorialList.getNumberOfEntries(); h++){
        currentTut = tutorialList.getEntry(h).getTutorialGroupID();
        if(currentTut.contains(tutGroup)){
            g++;
            currentTutGroupArr[g-1] = currentTut;

        }
    }
    String [] tutorialGroupUnderAProgramme = new String[g];

    for(int y=0; y < tutorialGroupUnderAProgramme.length; y++){
        tutorialGroupUnderAProgramme[y] = currentTutGroupArr[y];
    }

    for(int t=0; t <tutorialGroupUnderAProgramme.length; t++){
        System.out.println((t+1) + ". " + tutorialGroupUnderAProgramme[t]);
    }

    int tutChoice = tutorialUI.selectChoice(); 
    String selectedTutorialGroupID = tutorialGroupUnderAProgramme[tutChoice-1];

    int f=0;
    for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
        f++;
        if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupID)){
            break;
        }
    }

    Tutorial selectedTutGrp = tutorialList.getEntry(f);


    tutorialUI.FindStudent();
    String studName;
    studName = scanner.nextLine();


    boolean matchedStudent = false;
    int matchedStudentNumber = 1;
    for(int m=1; m <= selectedTutGrp.getStudentList().getNumberOfEntries();m++){
        matchedStudent = studName.toUpperCase().equals(selectedTutGrp.getStudentList().getEntry(m).getName().toUpperCase());
        if(matchedStudent){
            break;
        }
        matchedStudentNumber++; 
    }
    System.out.println("\n");
    Student sameStudentName = selectedTutGrp.getStudentList().getEntry(matchedStudentNumber);
    System.out.println(sameStudentName);
    
    System.out.print("\nDo you want to continue ? [Y/N] : ");
    String yorN = scanner.nextLine();
    if(yorN.equals("Y") || yorN.equals("y")){
        clearScreen.clrscreen();
        findStudent();
    }else{
        clearScreen.clrscreen();
        runTutorialManagement();
    }
        
  }
  
  public void chageTutGroupForStudent() throws InterruptedException{
        tutorialUI.changeTutorialGroup();     
        int choice;
        tutorialUI.programmeList();
        
        String prevPrefix = ""; // Variable to store the first three characters of the previous tutorial group
        String[] allTutorialGRoupList = new String[tutorialList.getNumberOfEntries()];
        int j = 1;
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {
            
            String currentGroupID = tutorialList.getEntry(i).getTutorialGroupID();
            String currentPrefix = currentGroupID.substring(0, 3);

            // Check if the first three characters are the same as the previous one
            if (!currentPrefix.equals(prevPrefix)) {
                allTutorialGRoupList[j-1] = currentPrefix;
                prevPrefix = currentPrefix; // Update the previous prefix
                j++;
            }
            
        }
        
        String[] tutorialGroupWithoutNumberOfGroup = new String[j-1];
        for(int g=0; g < (j-1); g++){
            tutorialGroupWithoutNumberOfGroup[g] = allTutorialGRoupList[g];
        }
        
        for(int k=0; k < tutorialGroupWithoutNumberOfGroup.length; k++){
            System.out.println((k+1) + ". " + tutorialGroupWithoutNumberOfGroup[k]);
        }
        
        choice = tutorialUI.selectChoice();
        
        String currentTut = ""; 
        
        String tutGroup = tutorialGroupWithoutNumberOfGroup[choice-1];
        String [] currentTutGroupArr = new String[tutorialGroupWithoutNumberOfGroup.length];
        int g=0;
        clearScreen.clrscreen();
        tutorialUI.tutorialGroupBanner();
        for(int h=1; h <= tutorialList.getNumberOfEntries(); h++){
            currentTut = tutorialList.getEntry(h).getTutorialGroupID();
            if(currentTut.contains(tutGroup)){
                g++;
                currentTutGroupArr[g-1] = currentTut;
                
            }
        }
        String [] tutorialGroupUnderAProgramme = new String[g];
        
        for(int y=0; y < tutorialGroupUnderAProgramme.length; y++){
            tutorialGroupUnderAProgramme[y] = currentTutGroupArr[y];
        }
            
        for(int t=0; t <tutorialGroupUnderAProgramme.length; t++){
            System.out.println((t+1) + ". " + tutorialGroupUnderAProgramme[t]);
        }
        
        int fromTutotial = tutorialUI.changeFromGroup();

        String selectedTutorialGroupIDFrom = tutorialGroupUnderAProgramme[fromTutotial-1];
        
        int f=0;
        for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
            f++;
            if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupIDFrom)){
                break;
            }
        }
        
        int toTutotial = tutorialUI.changeToGroup();
        String selectedTutorialGroupIDTo = tutorialGroupUnderAProgramme[toTutotial-1];
        int h=0;
        for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
            h++;
            if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupIDTo)){
                break;
            }
        }
                
        clearScreen.clrscreen();
        Tutorial selectedTutGrpFrom = tutorialList.getEntry(f);
        Tutorial selectedTutGrpTo = tutorialList.getEntry(h);        
      //List student In first Tut
      System.out.println(selectedTutGrpFrom);
              
        tutorialUI.SelectStudent();
        String studID;
        studID = scanner.nextLine();
         
        boolean matchedStudent = false;
        int matchedStudentNumber = 1;
        for(int m=1; m <= selectedTutGrpFrom.getStudentList().getNumberOfEntries();m++){
            matchedStudent = studID.toUpperCase().equals(selectedTutGrpFrom.getStudentList().getEntry(m).getStudentID().toUpperCase());           
            if(matchedStudent){
                System.out.println("Student ID match\n");
                Student sameStudentID = selectedTutGrpFrom.getStudentList().getEntry(m);
                break;
            }            
            matchedStudentNumber++;            
        }
        Student removedStudent = selectedTutGrpFrom.getStudentList().remove(matchedStudentNumber);
        selectedTutGrpTo.getStudentList().add(removedStudent);
        System.out.println(selectedTutGrpTo);
        
        System.out.print("\nDo you want to continue ? [Y/N] : ");
        String yorN = scanner.nextLine();
        if(yorN.equals("Y") || yorN.equals("y")){
            clearScreen.clrscreen();
            chageTutGroupForStudent();
        }else{
            clearScreen.clrscreen();
            runTutorialManagement();
        }
    }
    
    public void mergeTutGroup() throws InterruptedException{
        int choice;
        tutorialUI.mergeTutGroupBanner();        
        tutorialUI.programmeList();
        
        String prevPrefix = ""; // Variable to store the first three characters of the previous tutorial group
        String[] allTutorialGRoupList = new String[tutorialList.getNumberOfEntries()];
        int j = 1;
        for (int i = 1; i <= tutorialList.getNumberOfEntries(); i++) {           
            String currentGroupID = tutorialList.getEntry(i).getTutorialGroupID();
            String currentPrefix = currentGroupID.substring(0, 3);
            // Check if the first three characters are the same as the previous one
            if (!currentPrefix.equals(prevPrefix)) {
                allTutorialGRoupList[j-1] = currentPrefix;
                prevPrefix = currentPrefix; // Update the previous prefix
                j++;
            }
        }
        
        String[] tutorialGroupWithoutNumberOfGroup = new String[j-1];
        for(int g=0; g < (j-1); g++){
            tutorialGroupWithoutNumberOfGroup[g] = allTutorialGRoupList[g];
        }
        
        for(int k=0; k < tutorialGroupWithoutNumberOfGroup.length; k++){
            System.out.println((k+1) + ". " + tutorialGroupWithoutNumberOfGroup[k]);
        }
        
        choice = tutorialUI.selectChoice();
        String currentTut = ""; 
        clearScreen.clrscreen();
        tutorialUI.tutorialGroupBanner();
        String tutGroup = tutorialGroupWithoutNumberOfGroup[choice-1];
        String [] currentTutGroupArr = new String[tutorialGroupWithoutNumberOfGroup.length];
        int g=0;
        for(int h=1; h <= tutorialList.getNumberOfEntries(); h++){
            currentTut = tutorialList.getEntry(h).getTutorialGroupID();
            if(currentTut.contains(tutGroup)){
                g++;
                currentTutGroupArr[g-1] = currentTut;
                
            }
        }
        String [] tutorialGroupUnderAProgramme = new String[g];
        
        for(int y=0; y < tutorialGroupUnderAProgramme.length; y++){
            tutorialGroupUnderAProgramme[y] = currentTutGroupArr[y];
        }
            
        for(int t=0; t <tutorialGroupUnderAProgramme.length; t++){
            System.out.println((t+1) + ". " + tutorialGroupUnderAProgramme[t]);
        }
        
        int fromTutotial = tutorialUI.mergeFromGroup();

        String selectedTutorialGroupIDFrom = tutorialGroupUnderAProgramme[fromTutotial-1];
        
        int f=0;
        for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
            f++;
            if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupIDFrom)){
                break;
            }
        }
        
        int toTutotial = tutorialUI.mergeToGroup();
        String selectedTutorialGroupIDTo = tutorialGroupUnderAProgramme[toTutotial-1];
        int h=0;
        for(int z=1; z <= tutorialList.getNumberOfEntries(); z++){
            h++;
            if(tutorialList.getEntry(z).getTutorialGroupID().toUpperCase().equals(selectedTutorialGroupIDTo)){
                break;
            }
        }
        clearScreen.clrscreen();
        Tutorial selectedTutGrpFrom = tutorialList.getEntry(f);
        Tutorial selectedTutGrpTo = tutorialList.getEntry(h);
        
        for(int l = 1; l <= tutorialList.getNumberOfEntries(); l++){
            Student removedStudent = selectedTutGrpFrom.getStudentList().remove(fromTutotial);
            if(removedStudent != null){
              selectedTutGrpTo.getStudentList().add(removedStudent);  
            }   
        }
        System.out.println(selectedTutGrpTo);
        System.out.print("\nDo you want to continue ? [Y/N] : ");
        String yorN = scanner.nextLine();
        if(yorN.equals("Y") || yorN.equals("y")){
            clearScreen.clrscreen();
            mergeTutGroup();
        }else{
            clearScreen.clrscreen();
            runTutorialManagement();
        }
        
    }

    public void initializeSystemData(Object[] systemDataArray){
        tutorialList = (ListInterface<Tutorial>) systemDataArray[4];
        studentList = (ListInterface<Student>) systemDataArray[3];              
        tutorialList.arrangeArrayByOrder();
        studentList.arrangeArrayByOrder();
    }
    
    public void updateSystem(Object[] systemDataArray){
        systemDataArray[4] = tutorialList;
    }
    
    public static void main(Object[] systemDataArray) throws InterruptedException{
        TutorialManagement tutorialManagement = new TutorialManagement();
        tutorialManagement.initializeSystemData(systemDataArray);
        tutorialManagement.runTutorialManagement();
        tutorialManagement.updateSystem(systemDataArray);
        
    }
}