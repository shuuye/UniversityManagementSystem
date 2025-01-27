package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Course;
import entity.ProgrammeClass;
import entity.Student;
import entity.TeachingEntity;
import entity.TutorEntity;
import entity.Tutorial;
import java.util.Random;

/**
 *
 * @author Lim Shuye,Chew Wei Seng, Wong Chai Yee, Wong Weng Sam, Vanness Chaw Jun Kit
 */
public class Initializer {

    //  Method to return a collection of with hard-coded entity values
    public ListInterface<ProgrammeClass> initializeProgramme() {
        // 5 records with tutorial group is null
        ListInterface<ProgrammeClass> ProgrammeList = new ArrayList<>();

        ProgrammeList.add(new ProgrammeClass("RSD", "Bachelor of Information Technology (Honours) in Software Systems Development ", "FOCS", 3));
        ProgrammeList.add(new ProgrammeClass("RSF", "Bachelor of Computer Science (Honours) in Software Engineering ", "FOCS", 3));
        ProgrammeList.add(new ProgrammeClass("RIT", "Bachelor of Information Technology (Honours) in Internet Technology", "FOCS", 3));
        ProgrammeList.add(new ProgrammeClass("RIS", "Bachelor of Information Technology (Honours) in Information Security", "FOCS", 3));
        ProgrammeList.add(new ProgrammeClass("RST", "Bachelor of Computer Science (Honours) in Interactive Software Technology", "FOCS", 3));
        ProgrammeList.add(new ProgrammeClass("RDS", "Bachelor of Computer Science (Honours) in Data Science", "FOCS", 3));

        // 2 records with tutorial group initialized
        ListInterface<Tutorial> RACgroup = new ArrayList<>();
        RACgroup.add(new Tutorial("RAC1"));
        RACgroup.add(new Tutorial("RAC2"));
        RACgroup.add(new Tutorial("RAC3"));
        RACgroup.add(new Tutorial("RAC4"));
        RACgroup.add(new Tutorial("RAC5"));
        ProgrammeList.add(new ProgrammeClass("RAC", "Bachelor of Accounting (Honours)", RACgroup, "FAFB", 4));

        ArrayList<Tutorial> RPYgroup = new ArrayList<>();
        RPYgroup.add(new Tutorial("RPY1"));
        RPYgroup.add(new Tutorial("RPY2"));
        RPYgroup.add(new Tutorial("RPY3"));
        RPYgroup.add(new Tutorial("RPY4"));
        RPYgroup.add(new Tutorial("RPY5"));
        ProgrammeList.add(new ProgrammeClass("RPY", "Bachelor of Social Science (Honours) in Psychology ", RPYgroup, "FSSH", 4));
        return ProgrammeList;
    }

    public ListInterface<Course> initializeCourse() {
        ListInterface<Course> courseList = new ArrayList<>();
        ListInterface<ProgrammeClass> programmeList = initializeProgramme();

        ListInterface<ProgrammeClass> programmeListIT = new ArrayList<>();
        ListInterface<ProgrammeClass> programmeListCS = new ArrayList<>();
        ListInterface<ProgrammeClass> programmeListItCs = new ArrayList<>();

        programmeListIT.add(programmeList.getEntry(1));
        programmeListIT.add(programmeList.getEntry(3));
        programmeListIT.add(programmeList.getEntry(7));

        programmeListCS.add(programmeList.getEntry(2));
        programmeListCS.add(programmeList.getEntry(4));
        programmeListCS.add(programmeList.getEntry(5));

        for (int i = 1; i <= 5; i++) {
            programmeListItCs.add(programmeList.getEntry(i));
        }
        ListInterface<String> preRequisite = new ArrayList<>();
        preRequisite.add("BACS2023 Object-Oriented Programming");

        courseList.add(new Course("FPIT1053", "Data Management", "JOOI HUANG HENG"));
        courseList.add(new Course("BJEL1023", "Academic English", "KAARTEEKA A/P ANBARASAN"));
        courseList.add(new Course("BACS2063", "Data Structures and Algorithms", "YeohKp", preRequisite, programmeListItCs));
        courseList.add(new Course("FPMA1014", "Statistics", "SONG CANG LEE", programmeList));
        courseList.add(new Course("BAIT1173", "IT Fundamentals", "BEE LIAN PUA", programmeListIT));
        courseList.add(new Course("BACS3074", "Artificial Intelligence", "Glaret Shirley A/P Sinnappan", programmeListCS));
        
        return courseList;
    }

    public ListInterface<TutorEntity> initializeTutor() {

        ListInterface<TutorEntity> TutorList = new ArrayList<>();

        TutorList.add(new TutorEntity(1234, "Jack Simpson Loh", "Full-time", "Male"));
        TutorList.add(new TutorEntity(2345, "Jim Tong Yan Zhu", "Part-time", "Male"));
        TutorList.add(new TutorEntity(5678, "Adrian Koh Luh Jian", "Full-time", "Male"));
        TutorList.add(new TutorEntity(2233, "Peter V/Vicent", "Part-time", "Female"));
        TutorList.add(new TutorEntity(1122, "Charlie Puth ", "Part-time", "Female"));

        return TutorList;

    }

    public ListInterface<TeachingEntity> initializeTeaching() {

        ListInterface<TeachingEntity> teaching = new ArrayList<>();

        ListInterface<TutorEntity> TutorList = initializeTutor();
        ListInterface<Course> courseList = initializeCourse();
        ListInterface<ProgrammeClass> programmeList = initializeProgramme();

        // course part
        ListInterface<Course> CourseList1 = new ArrayList();
        CourseList1.add(courseList.getEntry(1));
        CourseList1.add(courseList.getEntry(2));
        CourseList1.add(courseList.getEntry(5));

        ListInterface<Course> CourseList2 = new ArrayList();
        CourseList2.add(courseList.getEntry(2));

        ListInterface<Course> CourseList3 = new ArrayList();
        CourseList3.add(courseList.getEntry(3));
        CourseList3.add(courseList.getEntry(5));

        ListInterface<Course> CourseList4 = new ArrayList();
        CourseList4.add(courseList.getEntry(4));
        CourseList4.add(courseList.getEntry(2));

        ListInterface<Course> CourseList5 = new ArrayList();
        CourseList5.add(courseList.getEntry(5));

        // Final teaching part
        teaching.add(new TeachingEntity(CourseList1, programmeList.getEntry(1).getTGroup(), TutorList.getEntry(1)));
        teaching.add(new TeachingEntity(CourseList2, programmeList.getEntry(2).getTGroup(), TutorList.getEntry(2)));
        teaching.add(new TeachingEntity(CourseList3, programmeList.getEntry(3).getTGroup(), TutorList.getEntry(3)));
        teaching.add(new TeachingEntity(CourseList4, programmeList.getEntry(5).getTGroup(), TutorList.getEntry(4)));
        teaching.add(new TeachingEntity(CourseList5, programmeList.getEntry(4).getTGroup(), TutorList.getEntry(5)));

        return teaching;

    }

    public ListInterface<Student> initializeStudents() {
        ListInterface<Student> studentList = new ArrayList<>();
        ListInterface<Course> courseList = initializeCourse();
        //PUT HERE TO DECLARE HOW MANY STUDENTS THAT YOU WANT 
        int numberOfStudents = 50; // Change this to the desired number of students
        int numberOfCoursesToSelect = 2;
        Random random = new Random();

        for (int studentIndex = 0; studentIndex < numberOfStudents; studentIndex++) {
            ListInterface<String> coursesNamesAndCodes = new ArrayList<>();
            boolean[] selectedIndices = new boolean[courseList.getNumberOfEntries()];

            for (int i = 0; i < numberOfCoursesToSelect; i++) {
                int randomIndex;
                int randomStatusInt;
                String status = " ";

                do {
                    randomIndex = random.nextInt(courseList.getNumberOfEntries());
                } while (selectedIndices[randomIndex]);

                do {
                    randomStatusInt = random.nextInt(4);
                } while (selectedIndices[randomStatusInt]);

                selectedIndices[randomIndex] = true;
                selectedIndices[randomStatusInt] = true;

                switch (randomStatusInt) {
                    case 1 ->
                        status = " (MAIN) ";
                    case 2 ->
                        status = " (REPEAT) ";
                    case 3 ->
                        status = " (RESIT) ";
                    case 4 ->
                        status = " (ELECTIVE) ";
                    default ->
                        status = " (MAIN) ";
                }

                Course course = courseList.getEntry(randomIndex + 1); // Adjust for 1-based indexing
                String courseInfo = course.getCourseCode() + " - " + course.getCourseName() + status;
                coursesNamesAndCodes.add(courseInfo);
            }

            // Create a new Student and add it to the list
            Student student = new Student(/* initialize student fields */);
            student.setCoursesNamesAndCodes(coursesNamesAndCodes);

            // Example of using the same format for adding students
            if (studentIndex == 0) {
                studentList.add(new Student("22WMR53232", "WONG WENG SAM", "MALE", "wongws@gmail.com", "0166637923", coursesNamesAndCodes));
            } else if (studentIndex == 1) {
                studentList.add(new Student("22WMR24321", "JOHN", "MALE", "John123@gmail.com", "0198723123", coursesNamesAndCodes));
            } else if (studentIndex == 2) {
                studentList.add(new Student("22WMR32423", "LEE WEI", "MALE", "Leewei23@gmail.com", "0154322312", coursesNamesAndCodes));
            } else if (studentIndex == 3) {
                studentList.add(new Student("22WMR09222", "LEE HUA", "MALE", "LeeHUA23@gmail.com", "0154321232", coursesNamesAndCodes));
            } else if (studentIndex == 4) {
                studentList.add(new Student("22WMR00921", "LEE XIAN", "FEMALE", "Leexian23@gmail.com", "01109212321", coursesNamesAndCodes));
            } else if (studentIndex == 5) {
                studentList.add(new Student("22WMR12345", "SARAH", "FEMALE", "sarah@gmail.com", "0178965423", coursesNamesAndCodes));
            } else if (studentIndex == 6) {
                studentList.add(new Student("22WMR67890", "JAMES", "MALE", "james@gmail.com", "0187654321", coursesNamesAndCodes));
            } else if (studentIndex == 7) {
                studentList.add(new Student("22WMR11111", "EMMA", "FEMALE", "emma@gmail.com", "0145678901", coursesNamesAndCodes));
            } else if (studentIndex == 8) {
                studentList.add(new Student("22WMR22222", "MICHAEL", "MALE", "michael@gmail.com", "0167890123", coursesNamesAndCodes));
            } else if (studentIndex == 9) {
                studentList.add(new Student("22WMR33333", "OLIVIA", "FEMALE", "olivia@gmail.com", "0190123456", coursesNamesAndCodes));
            } else if (studentIndex == 10) {
                studentList.add(new Student("22WMR44444", "DAVID", "MALE", "david@gmail.com", "0132456789", coursesNamesAndCodes));
            }
        }

        return studentList;
    }

    public Object[] initializeTutorial(ListInterface<ProgrammeClass> p, ListInterface<Tutorial> t) {

        //get tutorial group from programme
        ListInterface<ProgrammeClass> programmeList = initializeProgramme();

        //*******************************RDS GROUP
        //create new tutorail group list
        ListInterface<Tutorial> RDStutorialList = new ArrayList<>();
        RDStutorialList.add(new Tutorial("RDS1", 2));
        RDStutorialList.add(new Tutorial("RDS2", 2));
        RDStutorialList.add(new Tutorial("RDS3"));
        RDStutorialList.add(new Tutorial("RDS4"));
        RDStutorialList.add(new Tutorial("RDS5"));

        //assign to the programme tutorial group list
        programmeList.getEntry(6).setTGroup(RDStutorialList);

        //get student list from students
        ListInterface<Student> StudentList = initializeStudents();

        //get entry from student list
        ListInterface<Student> studentListRDS1 = RDStutorialList.getEntry(1).getStudentList();
        ListInterface<Student> studentListRDS2 = RDStutorialList.getEntry(2).getStudentList();

        //assign entry into student list in the tutorial group
        studentListRDS1.add(StudentList.getEntry(1));
        studentListRDS1.add(StudentList.getEntry(2));
        studentListRDS2.add(StudentList.getEntry(3));
        studentListRDS2.add(StudentList.getEntry(4));

        //*******************************RSD GROUP
        //secend programme tutorial group initialization
        ListInterface<Tutorial> RSDtutoriallist = new ArrayList<>();
        RSDtutoriallist.add(new Tutorial("RSD1", 4));
        RSDtutoriallist.add(new Tutorial("RSD2", 16));
        RSDtutoriallist.add(new Tutorial("RSD3",5));
        RSDtutoriallist.add(new Tutorial("RSD4"));
        RSDtutoriallist.add(new Tutorial("RSD5",18));
        programmeList.getEntry(1).setTGroup(RSDtutoriallist);

        ListInterface<Student> studentListRSD1 = RSDtutoriallist.getEntry(1).getStudentList();
        ListInterface<Student> studentListRSD2 = RSDtutoriallist.getEntry(2).getStudentList();

        //assign entry into student list before set in the tutorial group
        studentListRSD1.add(StudentList.getEntry(5));
        studentListRSD1.add(StudentList.getEntry(6));
        studentListRSD1.add(StudentList.getEntry(7));
        studentListRSD1.add(StudentList.getEntry(8));
        studentListRSD2.add(StudentList.getEntry(9));
        studentListRSD2.add(StudentList.getEntry(10));
        

        //NO STUDENT IN THIS GROUP
        ListInterface<Tutorial> RITgroup = new ArrayList<>();
        RITgroup.add(new Tutorial("RIT1"));
        RITgroup.add(new Tutorial("RIT2", 0));
        RITgroup.add(new Tutorial("RIT3"));
        RITgroup.add(new Tutorial("RIT4", 15));
        RITgroup.add(new Tutorial("RIT5", 8));

        programmeList.getEntry(3).setTGroup(RITgroup);
       
        
        ListInterface<Tutorial> tutorialList = new ArrayList<>();

        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            ProgrammeClass currentProgramme = programmeList.getEntry(i);

            if (currentProgramme.getTGroup() != null) {
                for (int j = 1; j <= currentProgramme.getTGroup().getNumberOfEntries(); j++) {
                    tutorialList.add(currentProgramme.getTGroup().getEntry(j));
                }
            }
        }

        p = programmeList;
        t = tutorialList;

        return new Object[]{p, t};

    }

    public ListInterface<Course> updateTheCourseAgain(ListInterface<Course> courseList, ListInterface<ProgrammeClass> programmeList) {

        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course currentCourse = courseList.getEntry(i);

            for (int j = 1; j <= programmeList.getNumberOfEntries(); j++) {
                ProgrammeClass currentProgramme = programmeList.getEntry(j);

                for (int k = 1; k <= currentCourse.getProgramAssociated().getNumberOfEntries(); k++) {
                    ProgrammeClass currentProgrammeAssociatedWithACourse = currentCourse.getProgramAssociated().getEntry(k);

                    if (currentProgramme.getCode().toUpperCase().equals(currentProgrammeAssociatedWithACourse.getCode().toUpperCase())) {
                        currentProgrammeAssociatedWithACourse.setTGroup(currentProgramme.getTGroup());
                    }

                }

            }

        }
        return courseList;
    }

    public Object[] initializeAllData() {
        // To illustrate how to use the initializeProducts() method
        Initializer initial = new Initializer();
        ListInterface<ProgrammeClass> programmeList = new ArrayList<>();
        ListInterface<Course> courseList = new ArrayList<>();
        ListInterface<TeachingEntity> teaching = new ArrayList<>();
        ListInterface<Student> studentList = new ArrayList<>();
        
        programmeList = initial.initializeProgramme();
        courseList = initial.initializeCourse();
        teaching = initial.initializeTeaching();
        studentList = initial.initializeStudents();
        
        ListInterface<Tutorial> tutorialList = new ArrayList<>();

        Object[] programmeNtutorialGroup = initial.initializeTutorial(programmeList, tutorialList);

        programmeList = (ListInterface<ProgrammeClass>) programmeNtutorialGroup[0];
        tutorialList = (ListInterface<Tutorial>) programmeNtutorialGroup[1];
        courseList = updateTheCourseAgain(courseList, programmeList);

        return new Object[]{programmeList, courseList, teaching, studentList, tutorialList};
    }
}
