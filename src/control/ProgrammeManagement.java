package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.ProgrammeMaintenanceUI;
import dao.Initializer;
import entity.ProgrammeClass;
import entity.Course;
import entity.TeachingEntity;
import entity.Student;
import entity.Tutorial;

import utility.GetInput;
import utility.CheckDataType;
import utility.BeforeContinue;
import utility.ClearScreen;

/**
 *
 * @Author: Lim Shuye
 */
public class ProgrammeManagement {

    //ARRAY LIST DECLARATION
    ListInterface<ProgrammeClass> programmeList = new ArrayList<>();
    ListInterface<Course> courseList = new ArrayList<>();

    //UI Declaration
    private final ProgrammeMaintenanceUI programmeUI = new ProgrammeMaintenanceUI();

    //UTILITY DECLARATION
    GetInput getInput = new GetInput();
    CheckDataType checkDataType = new CheckDataType();
    BeforeContinue AskContinue = new BeforeContinue();

    //SYSTEM INTEGRATION FUNCTIONS
    public void initializeSystemData(Object[] systemDataArray) {
        programmeList = (ListInterface<ProgrammeClass>) systemDataArray[0];
        courseList = (ListInterface<Course>) systemDataArray[1];
    }

    //SYSTEM INTEGRATION FUNCTIONS
    public void updateSystem(Object[] systemDataArray) {
        systemDataArray[0] = programmeList;
    }

    public static void main(Object[] systemDataArray) throws InterruptedException {

        ProgrammeManagement programmeManagement = new ProgrammeManagement();

        programmeManagement.initializeSystemData(systemDataArray);

        programmeManagement.runProgrammeMaintenance();

        programmeManagement.updateSystem(systemDataArray);

    }

    public ProgrammeManagement() {
        Initializer programmeInitializer = new Initializer();
        programmeList = programmeInitializer.initializeProgramme();
    }

    public void runProgrammeMaintenance() throws InterruptedException {
        int choice = 0;
        do {
            ClearScreen.clrscreen();
            choice = getInput.getIntInput(programmeUI.MenuChoice());
            switch (choice) {
                case 0 ->
                    programmeUI.displayExitMessage();
                case 1 -> {//add
                    addNewProgramme();
                }
                case 2 -> //remove
                    removeProgramme();
                case 3 -> //Search
                    searchProgramme();
                case 4 -> {
                    //Amend
                    boolean quit = false;
                    do {
                        ClearScreen.clrscreen();
                        programmeUI.printTitle("Amend Programme Details");
                        System.out.println("Enter the no. for the programme to be amend: - ");
                        int selectedProgramme = programmeUI.getListNumber(getAllProgramme());
                        if (selectedProgramme == 0) {
                            programmeUI.displayGoingBack();
                            AskContinue.pressEnterKeyBeforeContinue();
                            quit = true;

                        } else if (selectedProgramme < 1 || selectedProgramme > programmeList.getNumberOfEntries()) {
                            programmeUI.displayInvalidChoiceMessage();
                            AskContinue.pressEnterKeyBeforeContinue();
                        } else {
                            amendProgrammeDetails(selectedProgramme);
                            quit = true;
                            ClearScreen.clrscreen();
                        }
                    } while (quit == false);
                }
                case 5 -> {//List
                    ClearScreen.clrscreen();
                    programmeUI.printTitle("List All Programme");
                    programmeUI.listAllProgramme(getAllProgramme());
                    AskContinue.pressEnterKeyBeforeContinue();
                }
                case 6 -> //Add tutorial
                    addGroup();
                case 7 -> //Remove tutorial
                    removeGroup();
                case 8 -> //list tutorial group
                    listGroup();
                case 9 -> {//summary
                    generateSummaryReport();
                }
                default -> {
                    programmeUI.displayInvalidChoiceMessage();
                    AskContinue.pressEnterKeyBeforeContinue();
                }
            }
        } while (choice != 0);
    }

    //****************************************************** 1. ADD PROGRAMME **************************************************************
    public void addNewProgramme() throws InterruptedException {
        boolean duplicated = false;
        ProgrammeClass newProgramme;

        do {
            ClearScreen.clrscreen();
            newProgramme = inputProgrammeDetails();
            duplicated = duplicateProgramme(newProgramme);
            if (duplicated) {
                programmeUI.displayDuplicateError();

                programmeUI.askToQuit();
                duplicated = AskContinue.confirmationBeforeContinue();

            } else {
                programmeList.add(newProgramme);
                ClearScreen.clrscreen();
                programmeUI.printTitle("Add New Programme");
                programmeUI.SuccessAddProgramme();
                System.out.println("The following is the new programme you just added: -");
                programmeUI.printProgrammeDetails(newProgramme);
                AskContinue.pressEnterKeyBeforeContinue();
            }

        } while (duplicated == true);

    }
    
    public ProgrammeClass inputProgrammeDetails() throws InterruptedException {
        boolean validCode = false;
        String programmeCode;

        do {
            programmeUI.printTitle("Add New Programme");
            System.out.println("Please input the programme details:- ");
            programmeCode = programmeUI.inputProgrammeCode();
            if (isEmpty(programmeCode)) {
                validCode = validateCodeFormat(programmeCode);
                programmeCode = programmeCode.toUpperCase();

            }
            ClearScreen.clrscreen();
        } while (validCode == false);

        programmeUI.displayvalidDataInput();
        AskContinue.pressEnterKeyBeforeContinue();
        ClearScreen.clrscreen();

        boolean validName = false;
        String programmeName;

        do {
            programmeUI.printTitle("Add New Programme");
            System.out.println("Please input the programme details:- ");
            programmeName = programmeUI.inputProgrammeName();

            if (isEmpty(programmeName)) {
                validName = validateName(programmeName);
            }
            ClearScreen.clrscreen();
        } while (validName == false);

        programmeUI.displayvalidDataInput();
        AskContinue.pressEnterKeyBeforeContinue();
        ClearScreen.clrscreen();

        boolean validFaculty = false;
        String faculty;

        do {
            programmeUI.printTitle("Add New Programme");
            System.out.println("Please input the programme details:- ");
            faculty = programmeUI.inputFaculty();

            if (isEmpty(faculty)) {
                validFaculty = validateFacultyFormat(faculty);
            }
            ClearScreen.clrscreen();
        } while (validFaculty == false);

        programmeUI.displayvalidDataInput();
        AskContinue.pressEnterKeyBeforeContinue();
        ClearScreen.clrscreen();

        boolean validYear = false;
        String year;
        int convertyear = 0;

        do {
            programmeUI.printTitle("Add New Programme");
            System.out.println("Please input the programme details:- ");
            year = programmeUI.inputYear();

            if (isEmpty(year)) {
                if (checkDataType.isInteger(year)) {
                    convertyear = Integer.parseInt(year);
                    validYear = validateYear(convertyear);
                } else {
                    programmeUI.displayInvalidYear();
                    programmeUI.enterToTryAgain();
                }

            }
            ClearScreen.clrscreen();
        } while (validYear == false);

        programmeUI.displayvalidDataInput();
        AskContinue.pressEnterKeyBeforeContinue();

        System.out.println();
        return new ProgrammeClass(programmeCode, programmeName, faculty, convertyear);
    }

    //****************************************************** 2. REMOVE PROGRAMME **************************************************************
    //adt -> get number of entries, get entry, remove
    public void removeProgramme() throws InterruptedException {

        boolean quit = false;
        do {
            ClearScreen.clrscreen();
            programmeUI.printTitle("Remove Programme");
            System.out.println("Enter the no. for the programme to be removed: - ");
            int choice = programmeUI.getListNumber(getAllProgramme());
            if (choice == 0) {
                programmeUI.displayGoingBack();
                AskContinue.pressEnterKeyBeforeContinue();
                quit = true;
            } else if (choice < 1 || choice > programmeList.getNumberOfEntries()) {
                programmeUI.displayInvalidChoiceMessage();
                AskContinue.pressEnterKeyBeforeContinue();
            } else {
                ClearScreen.clrscreen();
                programmeUI.printTitle("Remove Programme");
                System.out.println("\nAre you sure to remove the following programme ? ");
                programmeUI.printProgrammeDetails(programmeList.getEntry(choice));
                System.out.print("\n Enter (Y) for yes or (N) for no ->  ");
                boolean confirm = AskContinue.confirmationBeforeContinue();

                if (confirm) {
                    ClearScreen.clrscreen();
                    programmeUI.printTitle("Remove Programme");
                    programmeList.remove(choice);
                    programmeUI.displaySuccessRemove();
                    System.out.println("The following is the new list after removed: - ");
                    displayProgramme();
                } else {
                    ClearScreen.clrscreen();
                    programmeUI.printTitle("Remove Programme");
                    programmeUI.cancelAction();
                }
                AskContinue.pressEnterKeyBeforeContinue();
                quit = true;
            }
        } while (quit == false);

    }

//****************************************************** 3. SEARCH PROGRAMME **************************************************************
    //adt -> get entry, search
    public void searchProgramme() throws InterruptedException {
        boolean validCode = false;
        String programmeCode;

        do {
            ClearScreen.clrscreen();
            programmeUI.printTitle("Search Programme");
            System.out.println("Enter the the programme code to search: - ");
            programmeCode = programmeUI.inputProgrammeCode();
            if (isEmpty(programmeCode)) {
                validCode = validateCodeFormat(programmeCode);
            }

        } while (validCode == false);
        ListInterface<ProgrammeClass> result = programmeList.search(programmeCode);

        System.out.println("\nResult : -");

        if (result.getEntry(1) == null) {
            programmeUI.displayProgrammeNotFound();
            AskContinue.pressEnterKeyBeforeContinue();
        } else {
            programmeUI.printProgrammeDetails(result.getEntry(1));
            AskContinue.pressEnterKeyBeforeContinue();
        }

    }

    //****************************************************** 4. AMEND PROGRAMME DETAILS **************************************************************
    //adt -> getEntry, replace
    public void amendProgrammeDetails(int selectedProgramme) throws InterruptedException {
        int amendChoice = 1;
        ProgrammeClass program = programmeList.getEntry(selectedProgramme);
        do {
            ClearScreen.clrscreen();
            amendChoice = getInput.getIntInput(programmeUI.AmendChoiceMenu());
            ClearScreen.clrscreen();
            programmeUI.printTitle("Amend Programme Details");
            System.out.println("You are amending the details for: - ");
            programmeUI.printProgrammeDetails(program);
            System.out.println("\nPlease key in the new details : - ");

            switch (amendChoice) {
                case 0 -> {
                    programmeUI.cancelAction();
                    AskContinue.pressEnterKeyBeforeContinue();
                }
                case 1 -> {
                    boolean duplicate = true;
                    boolean quitCode = false;
                    do {
                        String programmeCode = programmeUI.inputProgrammeCode();
                        ProgrammeClass programmeChosen = ProgrammeClass.copy(program);
                        programmeChosen.setCode(programmeCode);
                        duplicate = duplicateProgramme(programmeChosen);
                        if (isEmpty(programmeCode)) {
                            if (validateCodeFormat(programmeCode)) {
                                if (duplicate) {
                                    programmeUI.displayDuplicateError();
                                    programmeUI.askToQuit();
                                    quitCode = !AskContinue.confirmationBeforeContinue();
                                    if (quitCode == false) {
                                        break;
                                    }
                                } else {

                                    programmeList.replace(selectedProgramme, programmeChosen);
                                    amendChoice = 0;
                                    programmeUI.displaySuccessAmend();
                                    AskContinue.pressEnterKeyBeforeContinue();
                                }
                            }
                        }

                    } while (duplicate == true);
                }
                case 2 -> {
                    boolean quitName = false;
                    boolean quitCode;
                    do {
                        String programmeName = programmeUI.inputProgrammeName();
                        if (isEmpty(programmeName)) {
                            if (validateName(programmeName)) {

                                program.setProgramName(programmeName);
                                programmeUI.displaySuccessAmend();
                                AskContinue.pressEnterKeyBeforeContinue();
                                amendChoice = 0;
                                quitName = true;
                            } else {
                                programmeUI.askToQuit();
                                quitCode = !AskContinue.confirmationBeforeContinue();
                                if (quitCode == false) {
                                    break;
                                }
                            }

                        } else {
                            quitName = true;
                        }
                    } while (quitName == false);
                }
                case 3 -> {
                    boolean quitFaculty = false;
                    boolean quitCode;
                    do {
                        String faculty = programmeUI.inputFaculty();
                        if (isEmpty(faculty)) {
                            if (validateFacultyFormat(faculty)) {

                                program.setFaculty(faculty);
                                programmeUI.displaySuccessAmend();
                                AskContinue.pressEnterKeyBeforeContinue();
                                amendChoice = 0;
                                quitFaculty = true;
                            } else {
                                programmeUI.askToQuit();
                                quitCode = !AskContinue.confirmationBeforeContinue();
                                if (quitCode == false) {
                                    break;
                                }
                            }

                        } else {
                            quitFaculty = true;
                        }
                    } while (quitFaculty == false);
                }
                case 4 -> {
                    boolean quitYear = false;
                    do {
                        String year = programmeUI.inputYear();
                        if (isEmpty(year)) {
                            if (checkDataType.isInteger(year)) {
                                if (validateYear(Integer.parseInt(year))) {
                                    program.setYear(Integer.parseInt(year));
                                    programmeUI.displaySuccessAmend();
                                    AskContinue.pressEnterKeyBeforeContinue();
                                    amendChoice = 0;
                                    quitYear = true;

                                }
                            } else {
                                programmeUI.displayInvalidInteger();
                            }
                        } else {
                            quitYear = true;
                        }

                    } while (quitYear == false);
                }
                default ->
                    programmeUI.displayInvalidChoiceMessage();

            }
        } while (amendChoice != 0);
    }

    //****************************************************** 6. ADD TUTORIAL GROPU **************************************************************
    //adt -> get number of entries, get entry, add
    public void addGroup() throws InterruptedException {
        boolean quit = false;
        do {
            ClearScreen.clrscreen();
            programmeUI.printTitle("Add a Tutorial Group to Programme");
            System.out.println("Enter the no. for the programme to add a new tutorial group: - ");
            int chosen = programmeUI.getListNumber(getAllProgrammeAndTutorial());
            if (chosen == 0) {
                programmeUI.displayGoingBack();
                quit = true;
                AskContinue.pressEnterKeyBeforeContinue();
            } else if (chosen < 1 || chosen > programmeList.getNumberOfEntries()) {
                programmeUI.displayInvalidChoiceMessage();
            } else {
                ClearScreen.clrscreen();
                programmeUI.printTitle("Add a Tutorial Group to Programme");
                ProgrammeClass chosenProgramme = programmeList.getEntry(chosen);
                //programmeUI.printProgrammeDetails(chosenProgramme);
                String programmeCode = chosenProgramme.getCode();

                ListInterface<Tutorial> programGroup = programmeList.getEntry(chosen).getTGroup();
                boolean add = false;
                if (programGroup == null) {
                    ListInterface<Tutorial> temp = new ArrayList<>();
                    add = temp.add(new Tutorial(programmeCode + "1"));
                    programmeList.getEntry(chosen).setTGroup(temp);
                } else if (programGroup.getNumberOfEntries() >= 5) {
                    programmeUI.fullGroup();
                    AskContinue.pressEnterKeyBeforeContinue();
                } else {
                    add = programmeList.getEntry(chosen).addTGroup(new Tutorial(programmeCode + programGroup.getNumberOfEntries()));
                }

                if (add == false) {
                    programmeUI.failAddTGroup();
                } else {
                    programmeUI.SuccessAddTGroup();
                }
                programmeUI.displayCurrentListing();
                programmeUI.listAllProgramme(getAllProgrammeAndTutorial());
                AskContinue.pressEnterKeyBeforeContinue();
                quit = true;

            }
        } while (quit == false);
    }

    //****************************************************** 7. REMVOVE TUTORIAL GROUP **************************************************************
    //adt -> get entry and get number of entry, remove
    public void removeGroup() throws InterruptedException {
        boolean quit = false;
        do {
            ClearScreen.clrscreen();
            programmeUI.printTitle("Remove Tutorial Group from Programme");
            System.out.println("Enter the no. for the programme to remove a tutorial group: - ");
            int choice = programmeUI.getListNumber(getAllProgrammeAndTutorial());
            if (choice == 0) {
                programmeUI.cancelAction();
                quit = true;
                AskContinue.pressEnterKeyBeforeContinue();
            } else if (choice < 1 || choice > programmeList.getNumberOfEntries()) {
                programmeUI.displayInvalidChoiceMessage();
                AskContinue.pressEnterKeyBeforeContinue();
            } else {
                ClearScreen.clrscreen();
                programmeUI.printTitle("Remove Tutorial Group from Programme");
                ProgrammeClass chosenProgramme = programmeList.getEntry(choice);
                ListInterface<Tutorial> currentGroupList = programmeList.getEntry(choice).getTGroup();

                programmeUI.printProgrammeDetails(chosenProgramme);

                if (currentGroupList == null) {
                    programmeUI.displayNoTutorialGroup();
                } else {
                    System.out.println("Enter the no. for the tutorial group to be removed: - ");
                    int chosenGroup = programmeUI.getListNumber(getAllTutorialGroup(choice));
                    if (chosenGroup == 0) {
                        programmeUI.cancelAction();
                        quit = true;
                    } else if (chosenGroup < 1 || chosenGroup > currentGroupList.getNumberOfEntries()) {
                        programmeUI.displayInvalidChoiceMessage();
                    } else {
                        programmeList.getEntry(choice).removeTGroup(chosenGroup);
                        programmeUI.displaySuccessRemove();
                    }

                }

                AskContinue.pressEnterKeyBeforeContinue();
                quit = true;
            }
        } while (quit == false);

    }

    //****************************************************** 8. LIST GROUP BY PROGRAMME **************************************************************
    public void listGroup() throws InterruptedException {
        boolean quit = false;
        do {
            ClearScreen.clrscreen();
            programmeUI.printTitle("List Tutorial Group for Programme");
            System.out.println("Select the programme to list it's tutorial group: - ");
            int chosen = programmeUI.getListNumber(getAllProgrammeAndTutorial());
            if (chosen == 0) {
                programmeUI.displayGoingBack();
                quit = true;
            } else if (chosen < 1 || chosen > programmeList.getNumberOfEntries()) {
                programmeUI.displayInvalidChoiceMessage();
                AskContinue.pressEnterKeyBeforeContinue();
            } else {
                ClearScreen.clrscreen();
                programmeUI.printTitle("List Tutorial Group for Programme");
                ProgrammeClass chosenProgramme = programmeList.getEntry(chosen);
                programmeUI.printProgrammeDetails(chosenProgramme);
                ListInterface<Tutorial> list = chosenProgramme.getTGroup();
                if (!(list == null)) {
                    programmeUI.listAllTutorailGroup(getAllTutorialGroup(chosen));
                } else {
                    programmeUI.displayNoTutorialGroup();
                }
                quit = true;

            }
            AskContinue.pressEnterKeyBeforeContinue();
        } while (quit == false);

    }

    //****************************************************** 9. SUMMARY REPORT  **************************************************************
    //adt -> sortedProgramme, create copy of list, get entry, get number of entries, remove duplicate, search
    public String getAllProgrammeStudents(ListInterface<ProgrammeClass> chosenList) {
        String line = """
                           ====================================================================================================================================
                           """;
        String outputStr = line + String.format("    %-10s %-80s %-10s %-10sNo. of Students\n", "Code", "Programme Name", "Duration", "Faculty")
                + line;
        ListInterface<Student> totalList = new ArrayList<>();
        ListInterface<ProgrammeClass> sortedProgramme = chosenList.createCopyOfList();
        sortedProgramme.arrangeArrayByOrder();
        for (int i = 1; i <= sortedProgramme.getNumberOfEntries(); i++) {
            ListInterface<Tutorial> tutorialGroup = sortedProgramme.getEntry(i).getTGroup();
            ListInterface<Student> returnList = new ArrayList<>();

            if (tutorialGroup == null) {
                outputStr = outputStr + i + ".  " + sortedProgramme.getEntry(i) + "0\n";
            } else {
                int firstIndex = 1;
                boolean quit = false;

                do {

                    if (tutorialGroup.getEntry(firstIndex).getStudentList() == null) {

                        if (firstIndex >= tutorialGroup.getNumberOfEntries()) {
                            quit = true;
                        }
                        firstIndex++;
                    } else {

                        returnList = tutorialGroup.getEntry(firstIndex).getStudentList().createCopyOfList();

                        quit = true;
                    }

                } while (quit == false);

                for (int j = firstIndex; j <= sortedProgramme.getEntry(i).getTGroup().getNumberOfEntries(); j++) {
                    if (tutorialGroup.getEntry(j).getStudentList() != null) {
                        returnList = returnList.removeDuplicate(tutorialGroup.getEntry(j).getStudentList());
                    }

                }
                if (returnList != null) {
                    outputStr = outputStr + i + ".  " + sortedProgramme.getEntry(i) + returnList.getNumberOfEntries() + "\n";
                } else {
                    outputStr = outputStr + i + ".  " + sortedProgramme.getEntry(i) + "0\n";
                }

            }
            totalList = totalList.removeDuplicate(returnList);
        }

        outputStr += programmeUI.totalDivider() + "\n";
        outputStr += String.format(" %37s %-50s %30d \n", "TOTAL", " ", totalList.getNumberOfEntries());
        outputStr += line;
        return outputStr;
    }

    //adt -> sortedProgramme, create copy of list, get entry, get number of entries
    public void generateSummaryReport() throws InterruptedException {
        ClearScreen.clrscreen();
        boolean validCode = false;
        String programmeFaculty;

        do {
            ClearScreen.clrscreen();
            programmeUI.printTitle("Summary Report for faculty");
            System.out.print("Enter the faculty to generate the summary report : ");
            programmeFaculty = programmeUI.inputFaculty();
            if (isEmpty(programmeFaculty)) {
                validCode = validateFacultyFormat(programmeFaculty);
            }

        } while (validCode == false);
        ListInterface<ProgrammeClass> result = programmeList.search(programmeFaculty);

        ClearScreen.clrscreen();

        if (result != null) {
            System.out.println(programmeUI.reportHeader());

            System.out.format("\n%64s", "Faculty : " + programmeFaculty);

            System.out.println("\n\n" + programmeUI.reportDivider());
            System.out.format("%80s\n", "Programme Student Enrollment ");
            System.out.println(getAllProgrammeStudents(result));

            System.out.println("\n\n" + programmeUI.reportDivider());
            System.out.format("%90s\n", "Total Tutorial Groups and Courses by Programme");
            System.out.println(programmeUI.reportDivider());
            System.out.format(" %46s %-30s %30s %20s\n", "Programme", " ", "Tutorial Group", "No. of Course");
            System.out.println(programmeUI.reportDivider());

            ListInterface<Tutorial> totalGroup = new ArrayList<>();
            for (int i = 1; i <= result.getNumberOfEntries(); i++) {
                ListInterface<ProgrammeClass> sortedProgramme = result.createCopyOfList();
                sortedProgramme.arrangeArrayByOrder();
                ProgrammeClass programme = sortedProgramme.getEntry(i);

                ListInterface<Course> course = courseList.search(programme.getCode());
                int courseNum = courseList.count(programme.getCode());

                ListInterface<Tutorial> tGList = programme.getTGroup();
                if (tGList != null) {
                    if (course != null) {
                        System.out.format(" %-7s %-85s |  %-10s|  %10s\n", i + ".", programme.getCode() + "  "
                                + programme.getProgramName(), tGList.getEntry(1).getTutorialGroupID(), courseNum);
                    } else {
                        System.out.format(" %-7s %-85s |  %-10s|  %10s\n", i + ".", programme.getCode() + "  "
                                + programme.getProgramName(), tGList.getEntry(1).getTutorialGroupID(), "0");
                    }

                    for (int row = 2; row <= tGList.getNumberOfEntries(); row++) {
                        System.out.format(" %-7s %-85s |  %-10s|\n", "", "", tGList.getEntry(row).getTutorialGroupID());
                    }
                    System.out.println(programmeUI.subTotalDivider());
                    System.out.format(" %-7s %85s |  %-10s|\n", "", "Subtotal", tGList.getNumberOfEntries());

                    totalGroup = totalGroup.removeDuplicate(tGList);
                } else {
                    System.out.format(" %-7s %-85s |  %-10s|  %10s\n", i + ".", programme.getCode() + "  "
                            + programme.getProgramName(), "none", "0");
                }

                System.out.println(programmeUI.reportDivider());

            }
            System.out.println(programmeUI.totalDivider());
            System.out.format(" %-7s %85s |  %-10s|  %10s\n", "", "TOTAL", totalGroup.getNumberOfEntries(), "N/A");
            System.out.println(programmeUI.reportDivider());
            System.out.println("\n" + programmeUI.reportFooter());
        }else{
            programmeUI.displaFacultyNotFound();
        }

        AskContinue.pressEnterKeyBeforeContinue();

    }

//****************************************************** GETTING STRINGS/DATA **************************************************************
    //adt -> get number of entries
    public String getAllProgramme() {
        String line = """
                           ===================================================================================================================
                           """;
        String outputStr = line + String.format("    %-10s %-80s %-10s %-10s\n", "Code", "Programme Name", "Duration", "Faculty")
                + line;

        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            outputStr = outputStr + i + ".  " + programmeList.getEntry(i) + "\n";
        }

        outputStr += line;
        return outputStr;
    }

    //adt -> get number of entries and get entry
    public String getAllProgrammeAndTutorial() {
        String line = """
                           ====================================================================================================================================
                           """;
        String outputStr = line + String.format("    %-10s %-80s %-10s %-10sTutorial Group\n", "Code", "Programme Name", "Duration", "Faculty")
                + line;
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            ListInterface<Tutorial> tutorialGroup = programmeList.getEntry(i).getTGroup();
            if (tutorialGroup == null) {
                outputStr = outputStr + i + ".  " + programmeList.getEntry(i) + "0  \n";
            } else {
                outputStr = outputStr + i + ".  " + programmeList.getEntry(i) + programmeList.getEntry(i).getTGroup().getNumberOfEntries() + "\n";
            }

        }

        outputStr += line;
        return outputStr;
    }

    //adt -> get number of entries and get entry
    public String getAllTutorialGroup(int programme) {
        String line = """
                           ==============================================
                           """;
        String outputStr = line + String.format("    %-25s %-10s \n", "Tutorial Group ID", "Capacity") + line;

        ListInterface<Tutorial> currentGroupList = programmeList.getEntry(programme).getTGroup();
        for (int j = 1; j <= currentGroupList.getNumberOfEntries(); j++) {
            Tutorial currentGroup = currentGroupList.getEntry(j);

            outputStr += String.format(" %5s   %-25s %-10d \n", j + ".", currentGroup.getTutorialGroupID(), currentGroup.getCapacity());

        }

        outputStr += line;
        return outputStr;
    }

    public void displayProgramme() {
        programmeUI.listAllProgramme(getAllProgramme());
    }

    //******************************************************VALIDATION**************************************************************
    //adt -> get entry
    public boolean duplicateProgramme(ProgrammeClass newEntry) {
        boolean duplicated = false;
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            duplicated = programmeList.getEntry(i).equals(newEntry);
            if (duplicated == true) {
                return duplicated;
            }
        }

        return duplicated;
    }

    private boolean validateCodeFormat(String code) {
        for (int i = 0; i < code.length(); i++) {
            char eachCh = code.charAt(i);

            if (!(Character.isLetter(eachCh) && code.length() == 3)) {
                programmeUI.displayInvalidCode();
                programmeUI.enterToTryAgain();
                return false;
            }
        }
        return true;
    }

    public boolean validateName(String input) {
        for (int i = 0; i < input.length(); i++) {

            char eachCh = input.charAt(i);
            /*Check for every character in string is a letter or space */
            if (Character.isLetter(eachCh) || Character.isSpaceChar(eachCh) || eachCh == '(' || eachCh == ')') {

            } else {
                programmeUI.displayInvalidName();
                programmeUI.enterToTryAgain();
                return false;
            }

        }

        return true;
    }

    private boolean validateFacultyFormat(String inputStr) {
        for (int i = 0; i < inputStr.length(); i++) {
            char eachCh = inputStr.charAt(i);

            if (!(Character.isLetter(eachCh) && inputStr.length() == 4)) {
                programmeUI.displayInvalidFaculty();
                programmeUI.enterToTryAgain();
                return false;
            }
        }
        return true;
    }

    public boolean validateYear(int input) {
        /*Check for the readAge must between 0 to 100*/
        if ((input < 0 || input > 4)) {
            programmeUI.displayInvalidYear();
            programmeUI.enterToTryAgain();
            return false;
        }

        return true;

    }

    public boolean isEmpty(String input) {
        if ("".equals(input)) {

            programmeUI.displayBlankError();
            programmeUI.enterToTryAgain();
            return false;
        } else {
            return true;
        }
    }

}
