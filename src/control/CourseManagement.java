/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;
import boundary.CourseManagementUI;
import entity.Course;
import entity.ProgrammeClass;
import entity.Student;
import utility.BeforeContinue;
import utility.UI_messages;
import adt.ArrayList;
import adt.ListInterface;
import dao.Initializer;
import entity.Tutorial;
import utility.ClearScreen;
/**
 *
 * Author : Chew Wei Seng 22WMR14168
 */
public class CourseManagement {

    private CourseManagementUI courseUI = new CourseManagementUI();
    private UI_messages uiMsg = new UI_messages(); 
    private ListInterface<Course> courseList = new ArrayList<>();
    private BeforeContinue b4Continue = new BeforeContinue();
    private ListInterface<ProgrammeClass> programmeList = new ArrayList<>();  
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<String> facultyList = new ArrayList<>();
    
    public String getAllCourseCodeAndName(){
        String outputStr = "";
        // convert all courseList index to a string 1 by 1
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) { //called adt method
          outputStr += i + ". " + courseList.getEntry(i).getCourseCode() + " - " + courseList.getEntry(i).getCourseName() + "\n"; //called adt method
        }
        
        return outputStr;
    }
    
    public void setAllFaculty(){
        for(int i=1; i<=programmeList.getNumberOfEntries();i++){ //called adt method
            String currentFaculty = programmeList.getEntry(i).getFaculty(); //called adt method
            
            if(!facultyList.contains(currentFaculty)){ //called adt method
                //add the new faculty only if it is not existed
                //means no duplicate faculty will be added
                facultyList.add(currentFaculty); //called adt method
            }//end if 
            
        }
    }
   
    public boolean isUniqueCourse(String entry) {
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) { //called adt method
            Course course = courseList.getEntry(i); //called adt method
            //compare the course code and name with all  course in the courseList
            if (entry.toUpperCase().equals(course.getCourseCode().toUpperCase()) || entry.toUpperCase().trim().equals(course.getCourseName().toUpperCase().trim())) {
                return false; // CourseName / CourseCode already exists
            }//end if
            
        }
        return true; // CourseName / CourseCode is unique
    }
    
    public Course readNewCourse(Course newCourse){
        String courseCode = "";
        String courseName = "";
        
        do {
            courseCode = courseUI.strReadNCheckEmpty("Course Code"); // Get the new course
            if (!isUniqueCourse(courseCode)) {
                uiMsg.displayErrorMessage(uiMsg.isNotUniqueMessage("Course Code"));
            }
            if(!newCourse.validCourseCode(courseCode)){
                uiMsg.displayErrorMessage("Invalid Course Code Format. Please retype the Course Code(eg.BAIT2004)");
            }
            //end 2 if
            
        } while (!isUniqueCourse(courseCode) || !newCourse.validCourseCode(courseCode)); // Repeat until a unique course is entered
        
        if(newCourse.validCourseCode(courseCode)){
            courseCode = courseCode.toUpperCase();
        }
        
        do {
            courseName = courseUI.strReadNCheckEmpty("Course Name"); // Get the new course
            if (!isUniqueCourse(courseName)) {
                uiMsg.displayErrorMessage(uiMsg.isNotUniqueMessage("Course Name"));
            }//end if
            
        } while (!isUniqueCourse(courseName)); // Repeat until a unique course is entered
        
        newCourse = new Course(courseCode,courseName);
        
        return newCourse;
    }
    
    public <T> void addNewEntryToList(ListInterface<T> list, T newEntry, String msg, String addWhat){
        if(list.add(newEntry)){// add new entry to the list //called adt method
            uiMsg.displaySuccessMessage((msg + " Successfully! "));
            System.out.println("\n" + addWhat + "\n-------------------\n" + newEntry + "\n");
            list.arrangeArrayByOrder(); //called adt method
        }else{
            uiMsg.displayErrorMessage((msg + " Failed"));
        }
    }
    
    public void addNewCourse(){
        Course newCourse = new Course();
        
        courseUI.courseRegistrationBanner();
        
        newCourse = readNewCourse(newCourse);
        
        addNewEntryToList(courseList,newCourse, "Course Registered","New Course");
        
        b4Continue.pressEnterKeyBeforeContinue();
    }
    
    public <T> boolean removalConfirmation(T selectedEntry, String removeWhat,String confirmMsg){
        System.out.println("\nSelected " + removeWhat +" : \n" + selectedEntry);
        System.out.println("");
        uiMsg.displayConfirmationMessageAre(confirmMsg); // make sure user confirm to do data removal
        boolean confirmation = b4Continue.confirmationBeforeContinue();
        
        return confirmation;
    }
    
    public <T> void removeFromList(boolean confirmation, int choice, ListInterface<T> list, String removeWhat){
        if (confirmation){
            
            list.remove(choice); // remove the index user choose from the array //called adt method
            list.arrangeArrayByOrder(); //called adt method
            uiMsg.displaySuccessMessage(("Selected " + removeWhat + " Removed Successfully!"));
        }else{
            uiMsg.displayErrorMessage(("Selected " + removeWhat + " Removal Cancelled."));

        }
    }
    
    public <T> boolean chooseOptionFromListToRemove(ListInterface<T> listForAmend, String listStr ,String listHeader, T selectionFromList, String removeWhat, String confirmMsg) throws InterruptedException{
        boolean listIsNotEmpty = true;
        
        if(!listForAmend.isEmpty()){ //called adt method
            ClearScreen.clrscreen();
            courseUI.removeCourseBanner(removeWhat);
            
            if("".equals(listStr)){
                courseUI.printListValueInNumberOrderWithHeader(listHeader,listForAmend);
            }else{
                System.out.println(listStr);
            }//end inner if else
            
            int choice = courseUI.chooseOptionFrom1(listForAmend.getNumberOfEntries()); //called adt method
            
            selectionFromList = (T) listForAmend.getEntry(choice); //called adt method
            
            boolean confirmation = removalConfirmation(selectionFromList,removeWhat,confirmMsg);
            
            removeFromList(confirmation,choice,listForAmend,removeWhat);
            
        }else{
            ClearScreen.clrscreen();
            listIsNotEmpty = false;
        }
        
        return listIsNotEmpty;
    }
    
    public void removeCourse() throws InterruptedException{
               
        Course selectedCourseToRemove = new Course();
        boolean listIsNotEmpty = chooseOptionFromListToRemove(courseList, getAllCourseCodeAndName(), "Registered Course", selectedCourseToRemove, "Course", "REMOVE THIS COURSE");
        if(!listIsNotEmpty){ 
            uiMsg.displayErrorMessage("No Course are registered.");
        }
                
        b4Continue.pressEnterKeyBeforeContinue();

    }
    
    public int selectedCourseDetails() throws InterruptedException{
        int choice = courseUI.chooseOptionFrom1(courseList.getNumberOfEntries());  //called adt method
        ClearScreen.clrscreen();
        System.out.println("\n================");
        uiMsg.displaySuccessMessage("Searched Result\n================");
        System.out.println(courseList.getEntry(choice)); //called adt method
        
        return choice;
    }
    
    public void searchFromList() throws InterruptedException{
        //list all the course in brief and only show the details user choose
        courseUI.courseSearchingBanner();
        
        courseUI.listStrFromNonEmptyList(getAllCourseCodeAndName(),"No Course are registered.");
        
        if(!"".equals(getAllCourseCodeAndName())){
            
            selectedCourseDetails();
        }
        
        b4Continue.pressEnterKeyBeforeContinue();
    }
    
    public void searchByWordTyping() throws InterruptedException{
        courseUI.courseSearchingBanner();
        
        //let the user search the course by typing word in, and find the match one
        String searchFor = courseUI.strReadNCheckEmpty("Searching Bar");
        
        ListInterface<Course> searchResult = courseList.search(searchFor); //called adt method
        if(!searchResult.isEmpty()){ //called adt method
            ClearScreen.clrscreen();
            System.out.println("\n================");
            uiMsg.displaySuccessMessage("Searched Result\n================");
            courseUI.printListValue1By1WithTypeInOrderList("Course", searchResult);
        }else{
            System.out.println("");
            uiMsg.displayErrorMessage(("No result is found for " + searchFor));
        }
        
        b4Continue.pressEnterKeyBeforeContinue();        
    }
    
    public void courseSearching() throws InterruptedException{
        courseUI.courseSearchingBanner();
        courseUI.searchingOption();
        
        int choice = courseUI.getChoice(1, 2);
        
        switch(choice){
            case 1 -> searchFromList();
            case 2 -> searchByWordTyping();
        }
    }
    
    public boolean amendConfirmation(){
        uiMsg.displayConfirmationMessageDo("Amend this Course Details");
        boolean confirmation = b4Continue.confirmationBeforeContinue();
        
        return confirmation;
    }
    
    public <T> void amendDetailsConfirmation(ListInterface<T> list, int selection, T amendedCourse){
        courseUI.printAnEntryWithTitle("New Course Details", amendedCourse);
        uiMsg.displayConfirmationMessageAre("Update the New Course Details");

        boolean confirmation = b4Continue.confirmationBeforeContinue();

        if(confirmation){
            if(list.replace(selection, amendedCourse)){ //called adt method
                // replace the new amended course to the current choosed course that user want to amend
                uiMsg.displaySuccessMessage("New Course Details Update Successfully");
            }else{
                uiMsg.displayErrorMessage("Failed to Update New Course Details");
            }//end if else
            
        }else{
            uiMsg.displayErrorMessage("Course Details Modification Cancelled");
        }
    }
    
    public void getProgrammeFromCourse(ListInterface<ProgrammeClass> existedProgrammeForACourse, ListInterface<ProgrammeClass> availableProgramme, int type) {
        // type == 1 means get programme that are associated in the course
        // type == 2 means get programme that are not associated in the course
        
        if (!existedProgrammeForACourse.isEmpty()) { //called adt method
            // go through all Programmes in the programmeList
            for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) { //called adt method
                // Get the current Programme as a String
                String currentProgramme = programmeList.getEntry(i).toString(); //called adt method
                boolean found = false;

                // Go through all Programmes in existedProgrammeForACourse
                for (int j = 1; j <= existedProgrammeForACourse.getNumberOfEntries(); j++) { //called adt method
                    // Get the current Existed Programme as a String
                    String currentExistedProgramme = existedProgrammeForACourse.getEntry(j).toString(); //called adt method

                    // Comparison of the current Programme and the current Existed Programme
                    if (currentProgramme.equals(currentExistedProgramme)) {
                        if(type == 2){ //programme that are not associated
                            found = true;
                            break; // Exit the inner loop if a match is found
                        }else if(type == 1){ //programme that are associated
                            found = false;
                            break; // Exit the inner loop if a match is found
                        }  
                    } 
                } //end inner for
                
                if (!found) {
                    availableProgramme.add(programmeList.getEntry(i)); //called adt method
                }//end if
                
            }//end for
            
        } else {
            // If existedProgrammeForACourse is empty, set availableProgramme to the entire programmeList
            for(int i=1; i <= programmeList.getNumberOfEntries();i++){ //called adt method
                availableProgramme.add(programmeList.getEntry(i)); //called adt method
            }//end for
            
        }

    }

    
    public ProgrammeClass chooseProgrammeToAmmend(ListInterface<ProgrammeClass> availableProgramme, Course selectedCourse) throws InterruptedException{

        ProgrammeClass selectedProgramme = new ProgrammeClass();
        
        ClearScreen.clrscreen();
        
        System.out.println("\nProgramme Available To Add For " + selectedCourse.getCourseCode() + " :");

        if(availableProgramme.isEmpty()){ //called adt method
            System.out.println("");
            uiMsg.displayErrorMessage("No Programme available to associate");
            selectedProgramme = null;
            
        }else{ 
            //allow user to choose which programme that are available to ammend
            courseUI.printListValueInNumberOrderWithHeader("", availableProgramme);
            
            int choice = courseUI.chooseOptionFrom1(availableProgramme.getNumberOfEntries()); //called adt method

            selectedProgramme = availableProgramme.getEntry(choice); //called adt method

        }

        return selectedProgramme;
    }
    
    public <T> void checkListContainsEntry(ListInterface<T> listForAmend, T newEntry,String msg){
        // check the entry is inside the list or not b4 add to avoid duplicate data added
        if(!listForAmend.contains(newEntry)){ //called adt method
            addNewEntryToList(listForAmend,newEntry,(msg + " For A Course Registered"),msg);

        }else{
            uiMsg.displayErrorMessage(("Failed To Add The " + msg + ", Because it Already Existed"));
        }
    }
    
    public <T> void appendPreRequisiteList(T newPreRequisite,ListInterface<T> listForAmend, Course selectedCourse){
        System.out.println("");
        
        if(!listForAmend.isEmpty()){ //called adt method
            
            courseUI.printListValueInNumberOrderWithHeader("Registered Pre Requisite For " + selectedCourse.getCourseCode() + " : ", listForAmend);//called adt method
        }
        
        newPreRequisite = (T) courseUI.strReadNCheckEmpty("New Pre Requisite").trim();
        checkListContainsEntry(listForAmend,newPreRequisite,"New Pre Requisite");
    }
    
    public <T> ListInterface<ProgrammeClass> appendProgrammeFromList(T newProgrammeAssociated, ListInterface<T> listForAmend, Course selectedCourse) throws InterruptedException{
        ListInterface<ProgrammeClass> availableProgramme = new ArrayList<>();
        //find the programme that are available 
        //newProgrammeAssociated = programclass
        //listForAmend = programmeList under a course (selectedCourse)
        // 2 means not associated programme in the course
        getProgrammeFromCourse((ListInterface<ProgrammeClass>)listForAmend, availableProgramme, 2);
        newProgrammeAssociated = (T) chooseProgrammeToAmmend(availableProgramme, selectedCourse);
        
        if(newProgrammeAssociated != null){
            checkListContainsEntry(listForAmend,newProgrammeAssociated,"New Programme Associated");
        }

        //availableProgramme.clear();
        return availableProgramme;
    }

    public <T> void removePreRequisiteFromList(ListInterface<T> listForAmend, Course selectedCourse) throws InterruptedException{
        System.out.println("");
        
        String selectedPreRequisiteToRemove = new String();
        
        // allow user to choose which pre Requisite they want to remove if the pre Requisite list is not empty
        boolean listIsNotEmpty = chooseOptionFromListToRemove(listForAmend, "","Registered Pre Requisite For " + selectedCourse.getCourseCode() + " :  ", (T) selectedPreRequisiteToRemove, "Pre-Requisite", "REMOVE THIS PRE-REQUISITE");
        if(!listIsNotEmpty){
            uiMsg.displayErrorMessage("No Pre-Requisite Are Registered To Remove.");
        }
        
    }
    
    public <T> boolean removeProgramAssociatedFromList(ListInterface<T> listForAmend, Course selectedCourse) throws InterruptedException{
        System.out.println("");
        ProgrammeClass selectedProgramAssociatedToRemove = new ProgrammeClass();
        
        // allow user to choose which Program Associated they want to remove if the Program Associated list is not empty
        boolean listIsNotEmpty = chooseOptionFromListToRemove(listForAmend, "","\nRegistered Associated Programme For " + selectedCourse.getCourseCode() + " : ", (T) selectedProgramAssociatedToRemove, "Associated Programme", "REMOVE THIS ASSOCIATED PROGRAM");
        if(!listIsNotEmpty){
            System.out.println("\nRegistered Associated Programme For " + selectedCourse.getCourseCode() + " :  ");
            uiMsg.displayErrorMessage("No Associated Program Are Registered To Remove.");
        }
        
        return listIsNotEmpty;
    }

    public <T> void amendFromList(ListInterface<T> listForAmend, int choice, Course selectedCourse) throws InterruptedException{

        String whichList="";
        switch(choice){
            case 2 -> {whichList = "Pre Requisite";}
            case 3 -> {whichList = "Associated Programme";}
        }
        
        courseUI.amendListOption(whichList);
        
        int addOrRemove = courseUI.getChoice(0,2);
        T newPreRequisite = null;
        T newProgrammeAssociated = null;
        
        switch(addOrRemove){
            case 1 -> {if(choice == 2){ // add thing to Pre Requisite list
                            appendPreRequisiteList(newPreRequisite,listForAmend,selectedCourse);
                            
                        }else if(choice == 3){// add thing to Programme Associate list
                            appendProgrammeFromList(newProgrammeAssociated,listForAmend,selectedCourse);
                            
                        }}
            case 2 -> {if(choice == 2){ //remove thing from Pre Requisite list
                            removePreRequisiteFromList(listForAmend, selectedCourse);
                        }else if(choice == 3){//remove thing to Programme Associate list
                            removeProgramAssociatedFromList(listForAmend,selectedCourse);
                        }}
            case 0 -> System.out.println("");
        }
        
        listForAmend.arrangeArrayByOrder(); //called adt method
    }
    
    public void storeExistedDataToArray(Course selectedCourse, ListInterface<String> newPreRequisite, ListInterface<ProgrammeClass> newProgramAssociated){
        ListInterface<String> existedPreRequisite = selectedCourse.getPreRequisite();
        
        for (int i = 1;!existedPreRequisite.isEmpty() && i <= existedPreRequisite.getNumberOfEntries(); i++) { //called adt method
            newPreRequisite.add(existedPreRequisite.getEntry(i)); //called adt method
        }
        
        ListInterface<ProgrammeClass> existedProgramAssociated = selectedCourse.getProgramAssociated();
        
        for (int i = 1;!existedProgramAssociated.isEmpty() &&  i <= existedProgramAssociated.getNumberOfEntries(); i++) { //called adt method
            newProgramAssociated.add(existedProgramAssociated.getEntry(i)); //called adt method
        }
    }
    
    public Object[] amendMenu(String newCourseLeader, ListInterface<String> newPreRequisiteList, ListInterface<ProgrammeClass> newProgramAssociatedList, Course selectedCourse) throws InterruptedException {
        boolean doneAmend = false;
        boolean cancelAmend = false;

        do {
            ClearScreen.clrscreen();
            courseUI.amendOption();

            int choice = courseUI.getChoice(0, 4);

            switch (choice) {
                case 1 -> { // modify the new course leader here
                    System.out.println("");
                    if (!newCourseLeader.isBlank()) {
                        System.out.println("Current Course Leader: " + newCourseLeader + "\n");
                    }
                    String userInput = courseUI.strReadNCheckEmpty("New Course Leader");
                    newCourseLeader = userInput;
                    uiMsg.displaySuccessMessage("New Course Leader Registered Successfully");
                }
                case 2 -> amendFromList(newPreRequisiteList, choice, selectedCourse); // to amend the newPreRequisite
                case 3 -> amendFromList(newProgramAssociatedList, choice, selectedCourse); // to amend the newProgramAssociated
                case 4 -> {
                    doneAmend = true;
                }
                case 0 -> {
                    //will cancel all the amendment from teh user
                    cancelAmend = true;
                    doneAmend = true;
                }
            }//end switch

            if (!doneAmend) {
                uiMsg.displayConfirmationMessageDo("Amend Other Details");
                doneAmend = !b4Continue.confirmationBeforeContinue();
            }//end if

        } while (!doneAmend);

        return new Object[]{cancelAmend, newCourseLeader};
    }
    
    public Object[] chooseCourseAndStoreValue(int choice){
        Course selectedCourse = courseList.getEntry(choice); //called adt method
        //let the new course become the current one b4 change
        String newCourseLeader=selectedCourse.getCourseLeader();
        ListInterface<String> newPreRequisiteList = new ArrayList<>();
        ListInterface<ProgrammeClass> newProgramAssociatedList = new ArrayList<>();

        //store the existed data to the arraylist for the selected course
        storeExistedDataToArray(selectedCourse,newPreRequisiteList,newProgramAssociatedList);
        
        return new Object[]{selectedCourse, newCourseLeader, newPreRequisiteList, newProgramAssociatedList};
    }
    

    public void amendDetailsValue(int selection) throws InterruptedException{
        
        Object[] courseResultArray = chooseCourseAndStoreValue(selection);
        Course selectedCourse = (Course) courseResultArray[0];
        String newCourseLeader = (String) courseResultArray[1];
        ListInterface<String> newPreRequisiteList = (ListInterface<String>) courseResultArray[2];
        ListInterface<ProgrammeClass> newProgramAssociatedList = (ListInterface<ProgrammeClass>) courseResultArray[3];

        Object[] resultArray = amendMenu(newCourseLeader, newPreRequisiteList, newProgramAssociatedList, selectedCourse);
        boolean cancelAmend = (boolean) resultArray[0];
        newCourseLeader = (String) resultArray[1];
        
        if(cancelAmend){
            System.out.println("");
            uiMsg.displayErrorMessage("Course Details Modification Cancelled");
            
        }else{
            ClearScreen.clrscreen();
            Course amendedCourse = new Course(selectedCourse.getCourseCode(),selectedCourse.getCourseName(),newCourseLeader,newPreRequisiteList,newProgramAssociatedList);
            //check the amended course details is contained (matched with any index) in the array of courseList
            //when the course details is never exist in the courseList, means this is the new amended course
            if(!courseList.contains(amendedCourse)){ //called adt method
                amendDetailsConfirmation(courseList, selection, amendedCourse);
            }else{
                System.out.println("");
                uiMsg.displayErrorMessage("Nothing From The Course Details is Amended. (Because no NEW data was inputed)");
            }//end inner if else
            
            b4Continue.pressEnterKeyBeforeContinue();
        }
        
    }
    
    public void amendCourseDetails() throws InterruptedException{
        courseUI.amendCourseDetailsBanner();
        
        courseUI.listStrFromNonEmptyList(getAllCourseCodeAndName(), "No Course are registered.");
        int selection = 0;
        
        if(!"".equals(getAllCourseCodeAndName())){
            selection = selectedCourseDetails(); // choose which they want to remove
            
            if(amendConfirmation()){ // if they sure to remove the course they selected
                amendDetailsValue(selection);
            }else{
                System.out.println("");
                uiMsg.displayErrorMessage("Course Details Modification Cancelled");
                
                b4Continue.pressEnterKeyBeforeContinue();
            }//end inner if else
            
        }else{
            b4Continue.pressEnterKeyBeforeContinue();
        }//end if else
    
    }
    
    public ListInterface<Course> listAllCourseUnderSelectedFaculty(String selectedFaculty) throws InterruptedException{
        ClearScreen.clrscreen();
        
        ListInterface<Course> coursesUnderAFaculty = new ArrayList<>();
        for(int i=1; i <= courseList.getNumberOfEntries();i++){ //called adt method
            //check for every course
            Course currentCourse = courseList.getEntry(i); //called adt method
            
            ListInterface<ProgrammeClass> programmeListAssociatedWithACourse = currentCourse.getProgramAssociated();
            for(int j=1; j <= programmeListAssociatedWithACourse.getNumberOfEntries();j++){ //called adt method
                //if the faculty matched with the faculty programme in the current course
                if(programmeListAssociatedWithACourse.getEntry(j).getFaculty().contains(selectedFaculty)){ //called adt method
                    coursesUnderAFaculty.add(currentCourse);//called adt method
                    break;
                }//end if
                
            }//end inner for
            
        }//end for
        
        return coursesUnderAFaculty;        
    }
    
    public Object[] findAllCourseUnderAFaculty() throws InterruptedException{
        courseUI.coursesUnderAFacultyBanner();
        
        if(!facultyList.isEmpty()){ //called adt method
            courseUI.printListValueInNumberOrderWithHeader("\n    AVAILABLE FACULTIES\n----------------------------",facultyList);
            
            int selection = courseUI.chooseOptionFrom1(facultyList.getNumberOfEntries()); //called adt method
        
            String selectedFaculty = facultyList.getEntry(selection);//called adt method

            return new Object[]{listAllCourseUnderSelectedFaculty(selectedFaculty),selectedFaculty};
        }else{
            uiMsg.displayErrorMessage("No Faculty Available. (No Course Are Taken By Any Faculty)");
            
            return new Object[]{null,null};
        }
        
    }
    
    public void displayAllCourseUnderAFaculty() throws InterruptedException{
        ListInterface<Course> coursesUnderAFaculty = new ArrayList<>();
        
        coursesUnderAFaculty = (ListInterface<Course>)findAllCourseUnderAFaculty()[0];
        
        if(coursesUnderAFaculty != null){
            System.out.println("\n================");
            uiMsg.displaySuccessMessage("Searched Result\n================");
            courseUI.printListValue1By1WithTypeInOrderList("Course", coursesUnderAFaculty);
        }
        
        b4Continue.pressEnterKeyBeforeContinue();
    }
    
    public void listAllCourses(){
        courseUI.allCoursesListingBanner();
        courseUI.printListValue1By1WithTypeInOrderList("Course", courseList);
        b4Continue.pressEnterKeyBeforeContinue();
    }

    public void updateProgrammeAssociatedListAndPrintOut(Course selectedCourse, ListInterface<ProgrammeClass> newProgramAssociatedList) throws InterruptedException{
        newProgramAssociatedList.arrangeArrayByOrder();//called adt method
        Course amendedCourse = new Course(selectedCourse.getCourseCode(),selectedCourse.getCourseName(),selectedCourse.getCourseLeader(),selectedCourse.getPreRequisite(),newProgramAssociatedList);
        //check the amended course details is contained (matched with any index) in the array of courseList
        //when the course details is never exist in the courseList, means this is the new amended course
        if(!courseList.contains(amendedCourse)){ //called adt method
            if(newProgramAssociatedList.getNumberOfEntries() > selectedCourse.getProgramAssociated().getNumberOfEntries()){ //means new programme added //called adt method
               for(int i=1; i <= newProgramAssociatedList.getNumberOfEntries(); i++){//called adt method
                    if(!selectedCourse.getProgramAssociated().contains(newProgramAssociatedList.getEntry(i))){//called adt method
                        selectedCourse.addProgramme(newProgramAssociatedList.getEntry(i)); //called entity adt method
                    }

                } 
            }else if(newProgramAssociatedList.getNumberOfEntries() < selectedCourse.getProgramAssociated().getNumberOfEntries()){ //means programme removed //called adt method
                for(int i=1; i <= selectedCourse.getProgramAssociated().getNumberOfEntries(); i++){//called adt method
                    if(!newProgramAssociatedList.contains(selectedCourse.getProgramAssociated().getEntry(i))){//called adt method
                        selectedCourse.removeProgramme(i); //called entity adt method
                    }

                }
            }
            
            selectedCourse.getProgramAssociated().arrangeArrayByOrder();//called adt method
            ClearScreen.clrscreen();
            //display updated course
            System.out.println("\nModified Course\n-------------------\n" + selectedCourse);
        }else{
            System.out.println("");
            uiMsg.displayErrorMessage("Nothing changed... (no new data inputed)\n");
            
        }
    }
    
    public boolean addNewProgrammeToAClass(ListInterface<ProgrammeClass> availableProgrammeLeft, ListInterface<ProgrammeClass> newProgramAssociatedList, boolean failedToContinue,Course selectedCourse) throws InterruptedException{
        ProgrammeClass newProgrammeAssociated = null;

        availableProgrammeLeft = appendProgrammeFromList(newProgrammeAssociated, newProgramAssociatedList,selectedCourse);

        if(availableProgrammeLeft.isEmpty()){//called adt method
            b4Continue.pressEnterKeyBeforeContinue();
            System.out.println("");
            //stop to let the user add another programme bc all programme in the selectedCourse is existed (no available to add ald)
            failedToContinue = true;
        }
        
        return failedToContinue;
    }
    
    public boolean removeProgrammeToACourse(ListInterface<ProgrammeClass> newProgramAssociatedList, boolean failedToContinue, Course selectedCourse) throws InterruptedException{

        boolean listIsNotEmpty = removeProgramAssociatedFromList(newProgramAssociatedList, selectedCourse);

        if(!listIsNotEmpty){
            b4Continue.pressEnterKeyBeforeContinue();
            System.out.println("");
            //stop to let the user remove another programme bc all programme in the selectedCourse is empty
            failedToContinue = true;
        }
        
        return failedToContinue;
    }
    
    public String removeOrAddProgrammeForAClassHeader(int menuSelection, String msg){
        // 7 = add new Programme
        // 8 = remove programme
        
        switch(menuSelection){
            case 7 -> {courseUI.addProgrammeToACourseBanner();
                        msg = "Add";}
            case 8 -> {courseUI.removeProgrammeFromACourseBanner();
                        msg = "Remove";}
        } 
        
        courseUI.listStrFromNonEmptyList(getAllCourseCodeAndName(), "No Course are registered.");
        return msg;
    }
    
    public void removeOrAddAssociatedProgrammeForAClass(int menuSelection) throws InterruptedException{
        String msg="";
        msg = removeOrAddProgrammeForAClassHeader(menuSelection,msg);
         
        int choice = courseUI.chooseOptionFrom1(courseList.getNumberOfEntries()); //called adt method
       
        //initial all list data for the selectedCourse
        Object[] resultArray = chooseCourseAndStoreValue(choice);
        ListInterface<ProgrammeClass> availableProgrammeLeft = new ArrayList<>();
        Course selectedCourse = (Course) resultArray[0];
        ListInterface<ProgrammeClass> newProgramAssociatedList = (ListInterface<ProgrammeClass>) resultArray[3];
       
        boolean stop = false;
        boolean failedToContinue = false;
        do{ 
            if(!courseList.isEmpty()){ //called adt method
                
                switch(menuSelection){
                    case 7 -> failedToContinue = addNewProgrammeToAClass(availableProgrammeLeft, newProgramAssociatedList, failedToContinue,selectedCourse);
                    case 8 -> failedToContinue = removeProgrammeToACourse(newProgramAssociatedList, failedToContinue,selectedCourse);
                }
                
                if(failedToContinue == true){
                    //stop the looping means stop to ask user whether tp continue add or remove programme
                    break;
                }
                
                uiMsg.displayConfirmationMessageDo((msg + " Other Programme Associated to A Course"));
                stop = !b4Continue.confirmationBeforeContinue();
                
            }//end if
        }while(!stop);
        
        updateProgrammeAssociatedListAndPrintOut(selectedCourse, newProgramAssociatedList);

        //if(!failedToContinue){
            b4Continue.pressEnterKeyBeforeContinue();
        //}
    }
    
    public void countTheStudentsEnrollForEachCourse(int[] enrolledStudentCountForEachCourseArray){
        for(int i = 1; i <= courseList.getNumberOfEntries(); i++){ //called adt method
            Course currentCourse = courseList.getEntry(i); //called adt method
            //convert to how the studentList store the course (it just store the code and name)
            String currentCourseCodeAndName = currentCourse.getCourseCode() + " - " + currentCourse.getCourseName();
            
            int count = 0;
            
            for(int j = 1; j <= studentList.getNumberOfEntries(); j++){ //called adt method
                Student currentStudent = new Student();
                currentStudent = studentList.getEntry(j); //called adt method
                
                
                //check for each course that student enrolled
                for(int k = 1; k <= currentStudent.getCoursesNamesAndCodes().getNumberOfEntries(); k++){ //called adt method
                    
                    //if the courseCode and courseName is matched in the studentList
                    if(currentStudent.getCoursesNamesAndCodes().getEntry(k).contains(currentCourseCodeAndName)){ //called adt method
                        count++; // count + 1 if the student enrolled the course
                        enrolledStudentCountForEachCourseArray[i-1] = count;
                        
                    }//end if
                    
                }// end inner inner for
                
            }// end inner for
            
        }//end for
        
    }
    
    public String[][] courseCodeAndNameArrayInitializer(ListInterface<Course> list){
        String[][] courseCodeAndNameArray = new String[list.getNumberOfEntries()][2]; //called adt method
        
        for(int i=1; i <= list.getNumberOfEntries();i++){ //called adt method
            courseCodeAndNameArray[i-1][0] = (list.getEntry(i).getCourseCode()); //called adt method
            courseCodeAndNameArray[i-1][1] = (list.getEntry(i).getCourseName()); //called adt method
        }
        
        return courseCodeAndNameArray;
    }
    
    public String[][] programmeCodeAndNameArrayInitializer(ListInterface<Course> list){
        String[][] programmeCodeAndNameArray = new String[list.getNumberOfEntries()][programmeList.getNumberOfEntries()]; //called adt method
        
        for(int i=1; i <= list.getNumberOfEntries();i++){ //called adt method
            
            for(int j=1; j <= list.getEntry(i).getProgramAssociated().getNumberOfEntries(); j++){//called adt method
                ProgrammeClass currentprogramme = list.getEntry(i).getProgramAssociated().getEntry(j); //called adt method
                
                programmeCodeAndNameArray[i-1][j-1] = (currentprogramme.getCode() + " " + currentprogramme.getProgramName());
            }
            
        }
        
        return programmeCodeAndNameArray;
    }
    
    public void generateCourseSummaryReport(){
        if(!courseList.isEmpty()){ //called adt method
            //popularity enrollment (Total number of students enrolled in each course.)
            //set the array to store enrolled student count for every course 
            int[] enrolledStudentCountForEachCourseArray = new int[courseList.getNumberOfEntries()]; //called adt method

            countTheStudentsEnrollForEachCourse(enrolledStudentCountForEachCourseArray);

            String[][] courseCodeArray = courseCodeAndNameArrayInitializer(courseList);

            courseUI.displaySummaryPercentageGraph(enrolledStudentCountForEachCourseArray,courseCodeArray);
        }else{
            System.out.println("");
            uiMsg.displayErrorMessage("No Summary Report Generated, Because There Is No Course Register.");
        }
        b4Continue.pressEnterKeyBeforeContinue();        
    }
    
    public ListInterface<Course> getProgrammeThatMatchedSelectedFaculty(ListInterface<Course> courseUnderTakenBySelectedFaculty, String selectedFaculty){
        ListInterface<Course> newCourseListWithTheProgrammeUnderTheFacultyOnly = new ArrayList();
        
        for(int i=1; i <= courseUnderTakenBySelectedFaculty.getNumberOfEntries(); i++){ //called adt method
            Course currentCourse = courseUnderTakenBySelectedFaculty.getEntry(i); //called adt method
            
            Course newCourse = new Course(currentCourse.getCourseCode(),currentCourse.getCourseName());
            
            for(int j=1; j <= currentCourse.getProgramAssociated().getNumberOfEntries(); j++){ //called adt method
                ProgrammeClass currentProgramme = currentCourse.getProgramAssociated().getEntry(j); //called adt method
                if(currentProgramme.getFaculty().contains(selectedFaculty)){ //called adt method
                    
                    newCourse.addProgramme(currentProgramme); //called entity adt method
                    
                }//end if
                
            }//end inner for
            newCourseListWithTheProgrammeUnderTheFacultyOnly.add(newCourse); //called adt method
            
        }//end for
        
        return newCourseListWithTheProgrammeUnderTheFacultyOnly;
    }
    
    public int[][] getStudentCountInProgrammeForEachCourse(int [][] studentInEachProgrammeCount, ListInterface<Course> newCourseListWithTheProgrammeUnderTheFacultyOnly ){
        for(int i=1; i <= newCourseListWithTheProgrammeUnderTheFacultyOnly.getNumberOfEntries(); i++){ //called adt method
            Course currentCourse = newCourseListWithTheProgrammeUnderTheFacultyOnly.getEntry(i); //called adt method
            
            for(int j=1; j <= currentCourse.getProgramAssociated().getNumberOfEntries(); j++){ //called adt method
                ProgrammeClass currentProgrammeAssociated = currentCourse.getProgramAssociated().getEntry(j); //called adt method
                
                if(currentProgrammeAssociated.getTGroup() != null){
                    
                    for(int k=1; k <= currentProgrammeAssociated.getTGroup().getNumberOfEntries(); k++){ //called adt method
                        Tutorial currentTutorialGroup = currentProgrammeAssociated.getTGroup().getEntry(k); //called adt method

                        if(!currentTutorialGroup.getStudentList().isEmpty()){ //called adt method
                            studentInEachProgrammeCount [i-1][j-1] += currentTutorialGroup.getStudentList().getNumberOfEntries(); //called adt method
                        }


                    }//end inner inner for
                    
                }else{
                    studentInEachProgrammeCount [i-1][j-1] = 0;
                }//end if else
                
            }//end inner for
            
        }//end for
        
        return studentInEachProgrammeCount;
    }
    
    public void generateFacultyFilteredReport() throws InterruptedException{
        ListInterface<Course> courseUnderTakenBySelectedFaculty = new ArrayList<>();
        Object [] courseFaculty = findAllCourseUnderAFaculty();
        courseUnderTakenBySelectedFaculty = (ListInterface<Course>)courseFaculty[0];
        String selectedFaculty = (String)courseFaculty[1];
        
        ListInterface<Course> newCourseListWithTheProgrammeUnderTheFacultyOnly = getProgrammeThatMatchedSelectedFaculty(courseUnderTakenBySelectedFaculty, selectedFaculty);
       
        int[][] studentInEachProgrammeCount = new int [newCourseListWithTheProgrammeUnderTheFacultyOnly.getNumberOfEntries()][programmeList.getNumberOfEntries()]; //called adt method
         
        String[][] courseCodeArray = courseCodeAndNameArrayInitializer(newCourseListWithTheProgrammeUnderTheFacultyOnly);
        
        String[][] programmeCodeAndNameArray =  programmeCodeAndNameArrayInitializer(newCourseListWithTheProgrammeUnderTheFacultyOnly);
        
        studentInEachProgrammeCount = getStudentCountInProgrammeForEachCourse(studentInEachProgrammeCount, newCourseListWithTheProgrammeUnderTheFacultyOnly);
            
        courseUI.displaySummaryReportFilteredByFaculty(courseCodeArray, programmeCodeAndNameArray, studentInEachProgrammeCount,selectedFaculty);
        
        b4Continue.pressEnterKeyBeforeContinue();  
    }
    
    public void selectSummaryReport() throws InterruptedException{
        courseUI.summaryReportMenu();
        
        int choice = courseUI.getChoice(0, 2);
        
        ClearScreen.clrscreen();
        
        switch(choice){
            case 1 -> generateCourseSummaryReport();
            case 2 -> generateFacultyFilteredReport();
        }
    }

    public void runCourseManagement() throws InterruptedException{        
        int choice = 0;
        do {
            ClearScreen.clrscreen();
            courseUI.displayMenu();
            choice = courseUI.getChoice(0,9);
            ClearScreen.clrscreen();
            switch (choice) {
                case 0 -> choice = 0;
                case 1 -> addNewCourse();
                case 2 -> removeCourse();
                case 3 -> courseSearching();
                case 4 -> amendCourseDetails();
                case 5 -> displayAllCourseUnderAFaculty();
                case 6 -> listAllCourses();
                case 7 -> removeOrAddAssociatedProgrammeForAClass(choice);
                case 8 -> removeOrAddAssociatedProgrammeForAClass(choice);
                case 9 -> selectSummaryReport(); 
                default -> {
                    uiMsg.displayErrorMessage("Please Only type 0 until 9");
                }
            }
        } while (choice != 0);
    }
    
    public void initializeSystemData(Object[] systemDataArray){
        programmeList = (ListInterface<ProgrammeClass>) systemDataArray[0];
        courseList = (ListInterface<Course>) systemDataArray[1];
        studentList = (ListInterface<Student>) systemDataArray[3];
        
        programmeList.arrangeArrayByOrder(); //called adt method
        courseList.arrangeArrayByOrder(); //called adt method
        studentList.arrangeArrayByOrder(); //called adt method

        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            ListInterface<ProgrammeClass> programmeInCourse = courseList.getEntry(i).getProgramAssociated(); //called adt method
            programmeInCourse.arrangeArrayByOrder(); //called adt method
            courseList.getEntry(i).setProgramAssociated(programmeInCourse); //called adt method
        }
        
        setAllFaculty();
    }
    
    public void updateSystem(Object[] systemDataArray){
        systemDataArray[1] = courseList;
    }
    
    public static void main(Object[] systemDataArray) throws InterruptedException {
        
        CourseManagement courseManagement = new CourseManagement();
        
        courseManagement.initializeSystemData(systemDataArray);

        courseManagement.runCourseManagement();
  
        courseManagement.updateSystem(systemDataArray);
        
    }
    
}
