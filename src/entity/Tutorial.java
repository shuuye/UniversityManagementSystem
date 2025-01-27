package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.io.Serializable;

/**
 *
 * @author Vanness Chaw Jun Kit
 */

public class Tutorial implements Serializable {
    private static final int MAX_CAPACITY = 20;
   
    private int tutorialGroup;
    private String tutorialGroupID;
    private int capacity;
    private ListInterface<Student> studentList;
    ListInterface<String> NameAndStudID = new ArrayList<>();
    

    public Tutorial() {
    }

    public Tutorial(String tutorialGroupID) {
        this.tutorialGroupID = tutorialGroupID;
        this.capacity = 0;
        this.studentList = new ArrayList<>();
    }

    public Tutorial(String tutorialGroupID, int capacity, ListInterface<Student> studentList) {
        this.tutorialGroupID = tutorialGroupID;
        this.capacity = capacity;
        this.studentList = studentList;
    }

    public Tutorial(String tutorialGroupID, int capacity) {
        this.tutorialGroupID = tutorialGroupID;
        this.capacity = capacity;
        this.studentList = new ArrayList<>();
    }

    //for just get the Name and ID
    public Tutorial(int tutorialGroup, String tutorialGroupID, int capacity, ListInterface<String> NameAndStudID) {
        this.tutorialGroup = tutorialGroup;
        this.tutorialGroupID = tutorialGroupID;
        this.capacity = capacity;
        this.NameAndStudID = NameAndStudID;
    }

    public ListInterface<String> getNameAndStudID() {
        return NameAndStudID;
    }

    public void setNameAndStudID(ListInterface<String> NameAndStudID) {
        this.NameAndStudID = NameAndStudID;
    }
    
    

    public int getTutorialGroup() {
        return tutorialGroup;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }


    public void setTutorialGroup(int tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    
    public String getTutorialGroupID() {
        return tutorialGroupID;
    }

    public void setTutorialGroupID(String tutorialGroupID) {
        this.tutorialGroupID = tutorialGroupID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "\nTutorial Group ID : " + tutorialGroupID + 
                
                "\nStudent Details : \n" + studentList ;
                //"Student List(Changed) : \n" + NameAndStudID  ;
                //"Student List : \n" + studentList.getEntry().toStringTut()  ;
    }
    
    

}
