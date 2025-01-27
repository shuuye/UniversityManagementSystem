package entity;

import adt.ListInterface;
import adt.ArrayList;
import java.io.Serializable;
import java.util.Objects;

/** @author Wong Weng Sam
 */
public class Student implements Serializable {
    private String name;
    private String gender;
    private String studentID;
    private String studentEmail;
    private String phoneNo;
    ListInterface<String> coursesNamesAndCodes = new ArrayList<>();
 
    public Student() {
    }

    
    public Student(String studentID,String name, String gender, String studentEmail, String phoneNo,  ListInterface<String> coursesNamesAndCodes) {
        this.studentID = studentID;  
        this.name = name;
        this.gender = gender;
        this.studentEmail= studentEmail;
        this.phoneNo = phoneNo;
        this.coursesNamesAndCodes= coursesNamesAndCodes;
    }


    public Student(String studentID,String name, String gender, String studentEmail, String phoneNo) {
        this.studentID = studentID;  
        this.name = name;
        this.gender = gender;
        this.studentEmail= studentEmail;
        this.phoneNo = phoneNo;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentemail() {
        return studentEmail;
    }

    public void setStudentemail(String studentemail) {
        this.studentEmail = studentemail;
    }
    
    

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    
    public ListInterface<String> getCoursesNamesAndCodes() {
        return coursesNamesAndCodes;
    }

    
    public void setCoursesNamesAndCodes(ListInterface<String> coursesNamesAndCodes) {
        this.coursesNamesAndCodes = coursesNamesAndCodes;
    }
    
    public void addcoursesNamesAndCodes (String newEntry ){
        coursesNamesAndCodes.add(newEntry);
    }
    
    public void removecoursesNamesAndCodes (int position){
        coursesNamesAndCodes.remove(position);
    }

    //  }
//
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return Objects.equals(this.studentID, other.studentID);
    }

    @Override
    public String toString() {
      return  "=============================================================================" + "\n" +
              "\nStudent ID : " + studentID + 
              "\nStudent Name : " + name +
              "\nGender : " + gender +
              "\nStudent Email : " + studentEmail +
              "\nPhone Number : " + phoneNo + 
              "\nCourse Taken:  " + "\n" + coursesNamesAndCodes;
    }

    public String toStringTut() {
      return  
              "Student ID : " + studentID + 
              "\t\t Gender : " + gender +
              "\t\tStudent Name : " + name +"\n";      
    }
}
