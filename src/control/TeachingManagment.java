// Wong Chai Yee
// Student Id 22WMR13766
// RSD2S1 G3

package control;

import adt.*;
import boundary.TeachingUIClass;
import dao.*;
import entity.*;
import java.util.Scanner;
import utility.BeforeContinue;
import utility.MessageUI;
import utility.MessageUI;
import utility.ClearScreen;
import utility.CheckInputRange;
import utility.TextColor;

public class TeachingManagment {

    BeforeContinue beforeContinue = new BeforeContinue();
    TextColor color = new TextColor();

    ListInterface<TeachingEntity> teaching = new ArrayList<>();
    ListInterface<Course> course = new ArrayList<>();
    ListInterface<ProgrammeClass> programme = new ArrayList<>(); // used for tutorial grp
    ListInterface<TutorEntity> tutor = new ArrayList<>();
    private TeachingUIClass teachingUIClass = new TeachingUIClass();

    public void initializeSystemData(Object[] systemDataArray) {
        programme = (ListInterface<ProgrammeClass>) systemDataArray[0];
        course = (ListInterface<Course>) systemDataArray[1];
        teaching = (ListInterface<TeachingEntity>) systemDataArray[2];
        Initializer i = new Initializer();
        tutor = i.initializeTutor();

    }

    public void updateSystem(Object[] systemDataArray) {
        systemDataArray[2] = teaching;
    }

    public static void main(Object[] systemDataArray) throws InterruptedException {

        TeachingManagment teaching = new TeachingManagment();

        teaching.initializeSystemData(systemDataArray);

        teaching.OperateTeaching();
        teaching.updateSystem(systemDataArray);
    }

    public void OperateTeaching() throws InterruptedException {
        int select = 0;

        do {

            select = teachingUIClass.getTeachingMenu();

            switch (select) {
                case 0:
                    MessageUI.displayExitMessage();
                    ClearScreen.clrscreen();
                    break;

                case 1:

                    do {
                        ClearScreen.clrscreen();
                        teachingUIClass.assignTutortoCourse();
                        assignTutortoCourse(); 
                    } while (teachingUIClass.continueProcess());
                    break;

                case 2:

                    do {
                        ClearScreen.clrscreen();
                        teachingUIClass.assignTutorialToTutor();
                        assignTutorialGroupsToTutor(); 
                    } while (teachingUIClass.continueProcess());

                    break;

                case 3:

                    do {
                        ClearScreen.clrscreen();
                        teachingUIClass.assignTutorToTutorial();
                        assignTutorToTutorialGroup();  
                    } while (teachingUIClass.continueProcess());

                    break;

                case 4:

                    do {
                        ClearScreen.clrscreen();
                        teachingUIClass.searchCourse(); 
                        searchCoursesUnderTutor(); 
                    } while (teachingUIClass.continueProcess());

                    break;

                case 5:

                    do {
                        ClearScreen.clrscreen();
                        searchTutorForCourse(); 
                    } while (teachingUIClass.continueProcess());
                    break;

                case 6:

                    do {
                        ClearScreen.clrscreen();
                        ListAllTutorForACourse(); 
                    } while (teachingUIClass.continueProcess());
                    break;

                case 7:
                    do {
                        ClearScreen.clrscreen();
                        ListCoursesUnderTutor(); 
                    } while (teachingUIClass.continueProcess());
                    break;

                case 8:

                    do {
                        ClearScreen.clrscreen();
                        filterTutor();
                    } while (teachingUIClass.continueProcess());
                    break;

                case 9:
                    do {
                        ClearScreen.clrscreen();
                        teachingSummary();
                    } while (teachingUIClass.continueProcess());
                    break;

            }
        } while (select != 0);
    }

    // Case 1
    
    public ListInterface<TutorEntity> getAllTutor() { // get all tutor function for case 1

        ListInterface<TutorEntity> tutorRecords = new ArrayList<>();
        for (int i = 1; i <= teaching.getNumberOfEntries(); i++) {
            TutorEntity foundTutor = teaching.getEntry(i).getTutor();
            tutorRecords.add(foundTutor);

        }

        return tutorRecords;
    }

    private ListInterface<Course> getAllCourses() { // get all courses function for case 1

        for (int i = 1; i <= teaching.getNumberOfEntries(); i++) {
            ListInterface<Course> courseList = teaching.getEntry(i).getCourseList();

            if (courseList != null) {
                for (int j = 1; j <= courseList.getNumberOfEntries(); j++) {
                    Course course = courseList.getEntry(j);
                }
            }
        }

        return course;
    }

    public void assignTutortoCourse() throws InterruptedException { // start the main operation of case 1
        ListInterface<TutorEntity> allTutors = getAllTutor();
        teachingUIClass.listTutor(allTutors);
        int tutorChoice = teachingUIClass.getListNumber("Select a tutor to assign to a course (0 to cancel): ");

        if (tutorChoice == 0) {
            System.out.println("Canceling the assignment.");
            return;
        } else if (tutorChoice < 1 || tutorChoice > allTutors.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {

            TutorEntity selectedTutor = allTutors.getEntry(tutorChoice);
            String selectedTutorID = Integer.toString(selectedTutor.getTutorId());
            TeachingEntity foundRecordInTeachingList = (teaching.search(selectedTutorID).getEntry(1));

            if (!(foundRecordInTeachingList == null)) {
                int recordIndex = 0;
                for (int i = 1; i <= teaching.getNumberOfEntries(); i++) {
                    if (foundRecordInTeachingList.equals(teaching.getEntry(i))) {
                        recordIndex = i;
                        break;
                    }
                }

                ClearScreen.clrscreen();
                ListInterface<Course> allCourses = getAllCourses();
                teachingUIClass.listCourse(allCourses);
                int courseChoice = teachingUIClass.getListNumber("Select a course to assign the tutor to (0 to cancel): ");

                if (courseChoice == 0) {
                    System.out.println("Canceling the assignment.");
                    return;
                } else if (courseChoice < 1 || courseChoice > allCourses.getNumberOfEntries()) {
                    MessageUI.displayInvalidChoiceMessage();
                } else {

                    Course selectedCourse = allCourses.getEntry(courseChoice); 
                    TeachingEntity selectedTeachingEntity = teaching.getEntry(recordIndex);
                    if (selectedTeachingEntity != null) {
                        
                        ListInterface<Course> foundCourse = selectedTeachingEntity.getCourseList().search(selectedCourse.getCourseCode());

                        if (foundCourse.getNumberOfEntries() == 0) {
                            selectedTeachingEntity.assignTutorToCourse(selectedCourse);
                            System.out.println("");
                            System.out.println("Tutor " + selectedTutor.getTutorName() + " Successfully Assigned To Course " + selectedCourse.getCourseCode() + ".\n");
                        } else {
                            System.out.println("");
                            color.setRedTextColor();
                            System.out.println("**Tutor Cannot Assign to this course!");
                            System.out.println("Tutor " + selectedTutor.getTutorName() + " Have Already Exist In The Course " + selectedCourse.getCourseCode() + ".\n");
                        }
                    }
                }
            } else {

                ListInterface<Course> allCourses = getAllCourses();
                teachingUIClass.listCourse(allCourses);
                int courseChoice = teachingUIClass.getListNumber("Select a course to assign the tutor to (0 to cancel): ");

                if (courseChoice == 0) {
                    System.out.println("Canceling the assignment.");
                    return;
                }

                Course selectedCourse = allCourses.getEntry(courseChoice); 
                ListInterface<Course> newCourseList = new ArrayList<>(); 

                newCourseList.add(selectedCourse);

                teaching.add(new TeachingEntity(newCourseList, selectedTutor));
            }
        }
    }
    // end case 1
    
   //  Start case 2
    public void assignTutorialGroupsToTutor() throws InterruptedException {

        ListInterface<TutorEntity> allTutors = getAllTutor();
        teachingUIClass.listTutor(allTutors);
        int tutorChoice = teachingUIClass.getListNumber("Select a tutor assign to a tutorial group (0 to cancel): ");

        if (tutorChoice == 0) {
            System.out.println("Canceling the assignment.");
            return;
        } else if (tutorChoice < 1 || tutorChoice > allTutors.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {

            TutorEntity selectedTutor = allTutors.getEntry(tutorChoice);
            String selectedTutorID = Integer.toString(selectedTutor.getTutorId());

            TeachingEntity foundRecordInTeachingList = (teaching.search(selectedTutorID).getEntry(1));

            if (!(foundRecordInTeachingList == null)) { 

                int recordIndex = 1;
                for (int i = 0; i < teaching.getNumberOfEntries(); i++) {
                    if (foundRecordInTeachingList.equals(teaching.getEntry(i))) {
                        recordIndex = i;
                        break;
                    }
                }
                
                ClearScreen.clrscreen();
                teachingUIClass.assignTutorialToTutor();
                ListInterface<Course> Course = teaching.getEntry(recordIndex).getCourseList();

                teachingUIClass.listCourse(Course);
                int courseChoice = teachingUIClass.getListNumber("Select a course continue assign tutorial group (0 to cancel): ");

                if (courseChoice == 0) {
                    System.out.println("Canceling the assignment.");
                    return;
                } else if (courseChoice < 1 || courseChoice > Course.getNumberOfEntries()) {
                    MessageUI.displayInvalidChoiceMessage();
                } else {
                    String selectedCourse = Course.getEntry(courseChoice).getCourseCode();
                    ClearScreen.clrscreen();
                    teachingUIClass.assignTutorialToTutor();
                    teachingUIClass.listProgram();
                    ListInterface<ProgrammeClass> programGetFromTeaching = Course.getEntry(courseChoice).getProgramAssociated();
                    String selectedProgram;

                    if (!(programGetFromTeaching.getEntry(1) == null)) {

                        for (int j = 1; j <= programGetFromTeaching.getNumberOfEntries(); j++) {

                            System.out.println(j + "." + programGetFromTeaching.getEntry(j).toString());
                        }

                        int programChoice = teachingUIClass.getListNumber("Select a program to assign to a course (0 to cancel): ");
                        if (programChoice == 0) {
                            System.out.println("Canceling the assignment.");
                            return;
                        } else if (programChoice < 1 || programChoice > programGetFromTeaching.getNumberOfEntries()) {
                            MessageUI.displayInvalidChoiceMessage();
                        } else {
                            ListInterface<Course> getFromCourse = course.search(selectedCourse);
                            selectedProgram = programGetFromTeaching.getEntry(programChoice).getCode();
                            ListInterface<Course> getFromLatestCourse = getFromCourse.search(selectedProgram);
                            
                            ClearScreen.clrscreen();
                            teachingUIClass.assignTutorialToTutor();
                            
                            ListInterface<Tutorial> tutorial = getFromLatestCourse.getEntry(1).getProgramAssociated().getEntry(programChoice).getTGroup();
                            teachingUIClass.tutorialgrp();
                            if (tutorial != null) {

                                for (int i = 1; i <= tutorial.getNumberOfEntries(); i++) {
                                    System.out.println(i + ". " + tutorial.getEntry(i).getTutorialGroupID());
                                }
                                int tutorialgrpChoice = teachingUIClass.getListNumber("Select a tutorial group (0 to cancel): ");
                                if (tutorialgrpChoice == 0) {
                                    System.out.println("Canceling the assignment.");
                                    return;
                                }

                                Tutorial selectedTG = tutorial.getEntry(tutorialgrpChoice);

                                ListInterface<Tutorial> gotTutorialGrp = teaching.getEntry(recordIndex).gettGroup();

                                if (gotTutorialGrp == null) {
                                    ListInterface<Tutorial> tutorialList = new ArrayList<>();
                                    tutorialList.add(tutorial.getEntry(tutorialgrpChoice));

                                    teaching.getEntry(recordIndex).settGroup(tutorialList);
                                    ClearScreen.clrscreen();
                                    teachingUIClass.printLine2();
                                    System.out.println("                                                Success Assign Details");
                                    teachingUIClass.printLine2();
                                    System.out.println("Successfully add tutorial to a tutor.");
                                    System.out.println("Assign Tutor Name : "+ tutor.getEntry(recordIndex).getTutorName());
                                    System.out.println("Assign Tutor ID : "+ tutor.getEntry(recordIndex).getTutorId());
                                    System.out.println("");
                                    System.out.println("Tutorial group");
                                    
                                    for (int a = 1; a <= tutorialList.getNumberOfEntries(); a++) {
                                        System.out.println(a+".  "+ teaching.getEntry(recordIndex).gettGroup().getEntry(a).getTutorialGroupID());
                                    }
                                    
                                } else {
                                    ListInterface<Tutorial> foudSameTutorialGrp = teaching.getEntry(recordIndex).gettGroup().search(selectedTG.getTutorialGroupID());
                                    System.out.println(selectedTG.getTutorialGroupID());
                                    if (foudSameTutorialGrp == null) {
                                        teaching.getEntry(recordIndex).tutorAdd(tutorial.getEntry(tutorialgrpChoice));
                                       ClearScreen.clrscreen();
                                        teachingUIClass.printLine2();
                                        System.out.println("                                                Success Assign Details");
                                        teachingUIClass.printLine2();
                                        System.out.println("Successfully add tutorial to a tutor.");
                                        System.out.println("Assign Tutor Name : "+ tutor.getEntry(recordIndex).getTutorName());
                                        System.out.println("Assign Tutor ID : "+ tutor.getEntry(recordIndex).getTutorId());
                                        System.out.println("");
                                        System.out.println("Tutorial group");
                                        for (int b = 1; b <= teaching.getEntry(recordIndex).gettGroup().getNumberOfEntries(); b++) {
                                            System.out.println(teaching.getEntry(recordIndex).gettGroup().getEntry(b).getTutorialGroupID());
                                        }
                                    } else {
                                        System.out.println("Tutor Already assigned in group :");
                                         System.out.println(selectedTG.getTutorialGroupID());
                                    }
                                   
                                }
                            } else {
                                System.out.println("No tutorial group exist in this program !");
                            }

                        }
                    } else {
                        System.out.println("Cannot assign tutorial group. No program exist for this course.");
                    }

                }
            }
        }
    }
// end case 2
    
    // Start Case 3
    public void assignTutorToTutorialGroup() throws InterruptedException {
        teachingUIClass.listCourse(course);
        int courseChoice = teachingUIClass.getListNumber("Select a course continue assign tutorial group (0 to cancel): ");

        if (courseChoice == 0) {
            System.out.println("Canceling the assignment.");
            return;
        } else if (courseChoice < 1 || courseChoice > course.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {
            
            ClearScreen.clrscreen();
            teachingUIClass.listProgram();
            ListInterface<ProgrammeClass> program = course.getEntry(courseChoice).getProgramAssociated();
            ProgrammeClass selectedProgram;

            if (!(program.getEntry(1) == null)) {

                for (int j = 1; j <= program.getNumberOfEntries(); j++) {

                    System.out.println(j + "." + program.getEntry(j).toString());
                }

                int programChoice = teachingUIClass.getListNumber("Select a program to assign to a course (0 to cancel): ");
                if (programChoice == 0) {
                    System.out.println("Canceling the assignment.");
                    return;
                } else if (programChoice < 1 || programChoice > program.getNumberOfEntries()) {
                    MessageUI.displayInvalidChoiceMessage();
                } else {

                    selectedProgram = program.getEntry(programChoice);
                    ClearScreen.clrscreen();
                    teachingUIClass.assignTutorToTutorial();

                    ListInterface<Tutorial> tutorialGetFromCourse = selectedProgram.getTGroup();
                    if (tutorialGetFromCourse != null) {

                        for (int i = 1; i < tutorialGetFromCourse.getNumberOfEntries(); i++) {
                            System.out.println(i + ". " + tutorialGetFromCourse.getEntry(i).getTutorialGroupID());

                        }

                        int tutorialgrpChoice = teachingUIClass.getListNumber("Select a tutorial group (0 to cancel): ");
                        if (tutorialgrpChoice == 0) {
                            System.out.println("Canceling the assignment.");
                            return;
                        }

                        Tutorial selectedTG = tutorialGetFromCourse.getEntry(tutorialgrpChoice);

                        Course selectedCourse = course.getEntry(courseChoice);
                        String selectedCourseCode = selectedCourse.getCourseCode();
                        
                        ClearScreen.clrscreen();
                        teachingUIClass.assignTutorToTutorial();
                        ListInterface<TeachingEntity> foundRecordInTeachingList = (teaching.search(selectedCourseCode));
                        if (foundRecordInTeachingList.getEntry(1) != null) {
                            for (int i = 1; i <= foundRecordInTeachingList.getNumberOfEntries(); i++) {
                                System.out.println(i + ". " + foundRecordInTeachingList.getEntry(i).getTutor().getTutorName());
                            }
                            int tutorChoice = teachingUIClass.getListNumber("Select a tutor assign to a tutorial group (0 to cancel): ");

                            if (tutorChoice == 0) {
                                System.out.println("Canceling the assignment.");
                                return;
                            } else if (tutorChoice < 1 || tutorChoice > foundRecordInTeachingList.getNumberOfEntries()) {
                                MessageUI.displayInvalidChoiceMessage();
                            } else {
                                TeachingEntity selectedTutorRecord = foundRecordInTeachingList.getEntry(tutorChoice);
                                int selectedTutor = selectedTutorRecord.getTutor().getTutorId();
                                ListInterface<Tutorial> getTutorGroupInTeaching = teaching.search(Integer.toString(selectedTutor)).getEntry(1).gettGroup();

                                int recordIndex = 0;
                                for (int i = 0; i < teaching.getNumberOfEntries(); i++) {
                                    if (selectedTutorRecord.equals(teaching.getEntry(i))) {
                                        recordIndex = i;
                                        break;
                                    }
                                }

                                if (getTutorGroupInTeaching == null) {
                                    ListInterface<Tutorial> tutorialList = new ArrayList<>();
                                    tutorialList.add(tutorialGetFromCourse.getEntry(tutorialgrpChoice));
                                    teaching.getEntry(recordIndex).settGroup(tutorialList);
                                    System.out.println("Successfully add tutor to a tutorial group.");
                                    
                                    
                                } else {
                                    ListInterface<Tutorial> foudSameTutorialGrp = teaching.getEntry(recordIndex).gettGroup().search(selectedTG.getTutorialGroupID());
                                    if (foudSameTutorialGrp == null) {
                                        teaching.getEntry(recordIndex).gettGroup().add(tutorialGetFromCourse.getEntry(tutorialgrpChoice));
                                        System.out.println("Successfully add tutor to a tutorial group.");
                                    } else {
                                        System.out.println("Tutor Already assigned in group.");

                                    }
                                }

                            }
                        } else {
                            System.out.println("Cannot assign tutorial group. No tutor exist for this course.");

                        }
                    } else {
                        System.out.println("Cannot assign tutorial group. No program exist for this course.");
                    }

                }
            } else {
                System.out.println("No Program exist in this Course.");
            }
        }
    }// end case 3
    
    // Start Case 4
    public void searchCoursesUnderTutor() throws InterruptedException {

        teachingUIClass.listTutor(getAllTutor()); // already have course tutor,in teaching
        int tutorChoice = teachingUIClass.getListNumber("Enter the number of the tutor to search for courses (0 to cancel): ");

        if (tutorChoice == 0) {
            System.out.println("Canceling the search.");
            return;
        } else if (tutorChoice < 1 || tutorChoice > teaching.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {
            teachingUIClass.displaySearchCourseInfo();
            TeachingEntity selectedTutorRecord = teaching.getEntry(tutorChoice);
            String courseId = teachingUIClass.getCourseID();
            if (!(courseId == "")) {
                ListInterface<Course> foundCourse = selectedTutorRecord.getCourseList().search(courseId);
                if (foundCourse.getNumberOfEntries() == 0) {
                    teachingUIClass.tutorNotInCourse();
                } else {
                    teachingUIClass.tutorInCourse();
                    System.out.println("Course Code :" + foundCourse.getEntry(1).getCourseCode() + "\nCourse Name :" + foundCourse.getEntry(1).getCourseName());

                }
            }else{
                teachingUIClass.tutorNotInCourse();
            }

        }
    }
    //end case 4
    
    // Start Case 5
    private ListInterface<Course> getAllCoursesForTeaching() { // get all course funcition for case 5

        ListInterface<Course> firstEntry = teaching.getEntry(1).getCourseList();

        for (int i = 2; i <= teaching.getNumberOfEntries(); i++) {
            ListInterface<Course> courseList = teaching.getEntry(i).getCourseList();
            firstEntry = firstEntry.removeDuplicate(courseList);
        }

        return firstEntry;
    }

    public void searchTutorForCourse() throws InterruptedException { 

        teachingUIClass.listCourse(getAllCoursesForTeaching());
        int courseChoice = teachingUIClass.getListNumber("Enter the number of the Course to search for tutor (0 to cancel): ");

        if (courseChoice == 0) {
            System.out.println("Canceling the search.");
            return;
        } else if (courseChoice < 1 || courseChoice > teaching.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {

            Course selectedCourse = getAllCoursesForTeaching().getEntry(courseChoice);

            ListInterface<TeachingEntity> courseRecordInTeaching = teaching.search(selectedCourse.getCourseCode());

            int tutorId = teachingUIClass.getTutorId(); 
            String selectedTutorID = Integer.toString(tutorId); 
            ListInterface<TeachingEntity> tutor = courseRecordInTeaching.search(selectedTutorID); 

            if (!tutor.isEmpty()) {
                teachingUIClass.tutorInCourse();
                System.out.printf("%-10s | %-30s | %-25s |%-15s%n", "Tutor ID", "Tutor Name", "Tutor Type", "Gender");
                System.out.printf("%-10d | %-30s | %-25s |%-15s%n", tutor.getEntry(1).getTutor().getTutorId(), tutor.getEntry(1).getTutor().getTutorName(), tutor.getEntry(1).getTutor().getTutorType(), tutor.getEntry(1).getTutor().getGender());

            } else {
                teachingUIClass.tutorNotInCourse();
            }
        }
    }

    // end case 5
    
    // Start Case 6
    
    private ListInterface<Course> getAllCoursesForCase6() {

        ListInterface<Course> returnList = new ArrayList<>();
        
        for (int i = 1; i <= teaching.getNumberOfEntries(); i++) {
            ListInterface<Course> courseList = teaching.getEntry(i).getCourseList();

            if (courseList != null) {
                    returnList = returnList.removeDuplicate(courseList);
                
            }
        }
        return returnList;
    }
    
  // case 6
    public void ListAllTutorForACourse() throws InterruptedException {

        ListInterface<Course> allCourseInTeaching = getAllCoursesForCase6();
        teachingUIClass.listCourse(allCourseInTeaching);
        int courseChoice = teachingUIClass.getListNumber("Select the course number to list out the tutor (0 to cancel): ");

        if (courseChoice == 0) {
            System.out.println("Canceling the search.");
            return;
        }
        
        String selectedCourseCode = allCourseInTeaching.getEntry(courseChoice).getCourseCode();

        ListInterface<TeachingEntity> selectedCourseRecord = teaching.search(selectedCourseCode);

        if (selectedCourseRecord != null) { // check if the course exists
            color.setGreenTextColor();
            System.out.println("* Belows are all the tutor in the course " + allCourseInTeaching.getEntry(courseChoice).getCourseName());
            System.out.println("");
            System.out.printf("%-4s | %-10s | %-30s | %-15s |%-15s%n", "No.", "Tutor ID", "Tutor Name", "Tutor Type", "Gender");
            System.out.println("---------------------------------------------------------------------------------------------------");
            for (int i = 1; i <= selectedCourseRecord.getNumberOfEntries(); i++) {
                TutorEntity tutor1 = selectedCourseRecord.getEntry(i).getTutor();
                System.out.printf("%-4d | %-10d | %-30s | %-15s |%-15s%n", i, tutor1.getTutorId(), tutor1.getTutorName(), tutor1.getTutorType(), tutor1.getGender());
            }

        } else {
            teachingUIClass.courseNotFound();
        }
    }
// end case 6

    // Start Case 7
    public void ListCoursesUnderTutor() throws InterruptedException {
        teachingUIClass.listTutor(getAllTutor());
        int tutorChoice = teachingUIClass.getListNumber("Enter the number of the tutor to search for courses (0 to cancel): ");

        if (tutorChoice == 0) {
            System.out.println("Canceling the search.");
            return;
        }

        beforeContinue.pressEnterKeyBeforeContinue();
        ClearScreen.clrscreen();
        TeachingEntity selectedTutorRecord = teaching.getEntry(tutorChoice);

        if (selectedTutorRecord != null) {
            ListInterface<Course> coursesUnderTutor = selectedTutorRecord.getCourseList();

            System.out.println("Courses handled by " + selectedTutorRecord.getTutor().getTutorName() + ":");
            teachingUIClass.listCourse(coursesUnderTutor);

            if (coursesUnderTutor.isEmpty()) {
                System.out.println("Tutor " + selectedTutorRecord.getTutor().getTutorName() + " has no courses yet.");
            }
        } else {
            teachingUIClass.tutorNotFound();
        }
    }
    // end case 7

    // Start Case 8
    public void filterTutor() throws InterruptedException {
        ClearScreen.clrscreen();
        teachingUIClass.filtertutorManu();
        int filterChoice = teachingUIClass.getListNumber("");
        beforeContinue.pressEnterKeyBeforeContinue();

        switch (filterChoice) {
            case 0:
                System.out.println("Canceling the filter.");
                return;
            case 1:
                filterByGender();
                break;
            case 2:
                filterByTutorType();
                break;
            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void filterByGender() throws InterruptedException { // filter tutor by gender for case 8
        teachingUIClass.filterByGender();
        int genderChoice = teachingUIClass.getListNumber("");

        if (genderChoice == 0) {
            System.out.println("Canceling the gender filter.");
            return;
        }

        String selectedGender = "";

        switch (genderChoice) {
            case 1:
                selectedGender = "Male";
                break;
            case 2:
                selectedGender = "Female";
                break;
            default:
                MessageUI.displayInvalidChoiceMessage();
                return;
        }

        ListInterface<TutorEntity> filteredTutors = new ArrayList<>();

        for (int i = 1; i <= getAllTutor().getNumberOfEntries(); i++) {
            TutorEntity tutor = getAllTutor().getEntry(i);

            if (tutor.getGender().equalsIgnoreCase(selectedGender)) {
                filteredTutors.add(tutor);
            }
        }

        if (!filteredTutors.isEmpty()) {
            System.out.println("All Tutors filtered by Gender (" + selectedGender + ") are:");
            teachingUIClass.listTutor(filteredTutors);
        } else {
            System.out.println("No tutors found with the selected gender.");
        }
    }

    public void filterByTutorType() throws InterruptedException {  // filter tutor by type for case 8
        teachingUIClass.filterByType();
        int tutorTypeChoice = teachingUIClass.getListNumber("");

        if (tutorTypeChoice == 0) {
            System.out.println("Canceling the tutor type filter.");
            return;
        }

        String selectedTutorType = "";

        switch (tutorTypeChoice) {
            case 1:
                selectedTutorType = "Full-Time";
                break;
            case 2:
                selectedTutorType = "Part-Time";
                break;
            default:
                MessageUI.displayInvalidChoiceMessage();
                return;
        }

        ListInterface<TutorEntity> filteredTutors = new ArrayList<>();

        for (int i = 1; i <= getAllTutor().getNumberOfEntries(); i++) {
            TutorEntity tutor = getAllTutor().getEntry(i);

            if (tutor.getTutorType().equalsIgnoreCase(selectedTutorType)) {
                filteredTutors.add(tutor);
            }
        }
        if (!filteredTutors.isEmpty()) {
            System.out.println("Tutors filtered by Tutor Type (" + selectedTutorType + "):");
            teachingUIClass.listTutor(filteredTutors);
        } else {
            System.out.println("No tutors found with the selected tutor type.");
        }
    }
    // end case 8
    
    // Start Case 9
    public void teachingSummary() throws InterruptedException {
        teachingUIClass.SummaryReportMenu();
        int summaryChoice = teachingUIClass.getListNumber("");

        switch (summaryChoice) {
            case 1:
                summaryReportCourse();
                break;
            case 2:
                summeryReportTutor();
                break;
            case 0:
                System.out.println("Exiting Teaching Summary Menu.");
                break;
            default:
                ClearScreen.clrscreen();
                MessageUI.displayInvalidChoiceMessage();
        }

    }

    public void summaryReportCourse() throws InterruptedException { // summary report function for course

        Course courseWithMaxTutors = null;
        int maxTutorCount = 0;
        ClearScreen.clrscreen();
        teachingUIClass.SummaryForCourse();
        teachingUIClass.reportHeader();
        
        for (int i = 1; i <= course.getNumberOfEntries(); i++) {
            Course currentCourse = course.getEntry(i);
            System.out.println("No." + i );
            System.out.println("Course Code:" + currentCourse.getCourseCode());
            System.out.println("Course Name:" + currentCourse.getCourseName());
            System.out.println("");
            // Retrieve tutors for the current course
            ListInterface<TutorEntity> tutors = getTutorsForCourse(currentCourse);
            int tutorCount = tutors.getNumberOfEntries();

            
            if (tutorCount > maxTutorCount) {
                maxTutorCount = tutorCount;
                courseWithMaxTutors = currentCourse;
            }

            teachingUIClass.SummarylistTutor(tutors);
            System.out.printf("%-55s","Total Tutors:"+ tutorCount);
            System.out.println("");
            teachingUIClass.printLine2();
        }

        if (courseWithMaxTutors != null) {
            System.out.println("                                                   **The Maximum Course ");
            System.out.println("                                                   Course: " + courseWithMaxTutors.getCourseName());
            System.out.println("                                                   Total Tutors: " + maxTutorCount);
            System.out.println("");

        } else {
            System.out.println("No courses found.");
        }
    }

    public ListInterface<TutorEntity> getTutorsForCourse(Course course) { // get a tutor for course for case 9
        ListInterface<TutorEntity> tutors = new ArrayList<>();

        for (int i = 1; i <= teaching.getNumberOfEntries(); i++) {
            TeachingEntity teachingEntity = teaching.getEntry(i);

            if (teachingEntity.getCourseList().contains(course)) {
                tutors.add(teachingEntity.getTutor());
            }
        }
        return tutors;
    }

    public void summeryReportTutor() throws InterruptedException { //summary report function for tutor for case 9
        int totalMaleTutors = 0;
        int totalFemaleTutors = 0;
        int totalFullTimeTutors = 0;
        int totalPartTimeTutors = 0;
        ClearScreen.clrscreen();
        teachingUIClass.reportHeader();

        for (int i = 1; i <= tutor.getNumberOfEntries(); i++) {
            TutorEntity currentTutor = tutor.getEntry(i);

            if (currentTutor.getGender().equalsIgnoreCase("Male")) {
                totalMaleTutors++;
            } else if (currentTutor.getGender().equalsIgnoreCase("Female")) {
                totalFemaleTutors++;
            }

            if (currentTutor.getTutorType().equalsIgnoreCase("Full-Time")) {
                totalFullTimeTutors++;
            } else if (currentTutor.getTutorType().equalsIgnoreCase("Part-Time")) {
                totalPartTimeTutors++;
            }
        }

        int totalTutors = tutor.getNumberOfEntries();
        teachingUIClass.SummaryforGender();
        System.out.println("Total Male Tutors: " + totalMaleTutors);
        System.out.println("Total Female Tutors: " + totalFemaleTutors);
        teachingUIClass.Summaryfortype();
        System.out.println("Total Full-Time Tutors: " + totalFullTimeTutors);
        System.out.println("Total Part-Time Tutors: " + totalPartTimeTutors);
        teachingUIClass.tutorStatistic();
        System.out.println("The Total Tutors in the school have: " + totalTutors);

        // Calculate and display percentages
        double percentageMale = (double) totalMaleTutors / totalTutors * 100;
        double percentageFemale = (double) totalFemaleTutors / totalTutors * 100;
        double percentageFullTime = (double) totalFullTimeTutors / totalTutors * 100;
        double percentagePartTime = (double) totalPartTimeTutors / totalTutors * 100;

        System.out.println("Percentage Male Tutors: " + String.format("%.2f%%", percentageMale));
        System.out.println("Percentage Female Tutors: " + String.format("%.2f%%", percentageFemale));
        System.out.println("Percentage Full-Time Tutors: " + String.format("%.2f%%", percentageFullTime));
        System.out.println("Percentage Part-Time Tutors: " + String.format("%.2f%%", percentagePartTime));
    }

    // end case 9

}
