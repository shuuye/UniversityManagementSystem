/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Author : Chew Wei Seng 22WMR14168
 */
public class Course{
    private String courseCode;
    private String courseName;
    private String courseLeader;
    private ListInterface<String> preRequisite;
    private ListInterface<ProgrammeClass> programAssociated;

    public Course() {
        this.preRequisite = new ArrayList<>();
        this.programAssociated = new ArrayList<>();
    }
    
    public Course(String courseCode, String courseName, String courseLeader) {
        this();
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseLeader = courseLeader;
    }

    public Course(String courseCode, String courseName, String courseLeader, ListInterface<String> preRequisite, ListInterface<ProgrammeClass> programAssociated) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseLeader = courseLeader;
        this.preRequisite = preRequisite;
        this.programAssociated = programAssociated;
    }
    public Course(String courseCode, String courseName, String courseLeader, ListInterface<ProgrammeClass> programAssociated) {
        this();
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseLeader = courseLeader;
        this.preRequisite = new ArrayList<>();
        this.programAssociated = programAssociated;
    }

    public Course(String courseCode, String courseName) {
        this();
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseLeader = "";
        
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLeader() {
        return courseLeader;
    }

    public void setCourseLeader(String courseLeader) {
        this.courseLeader = courseLeader;
    }

    public ListInterface<String> getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(ListInterface<String> preRequisite) {
        this.preRequisite = preRequisite;
    }

    public ListInterface<ProgrammeClass> getProgramAssociated() {
        return programAssociated;
    }

    public void setProgramAssociated(ListInterface<ProgrammeClass> programAssociated) {
        this.programAssociated = programAssociated;
    }
    
    public boolean validCourseCode(String courseCode){
        // Define the regular expression for the format (4 alphabet characters followed by 4 numeric characters)
        String regex = "^[A-Za-z]{4}\\d{4}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(courseCode);

        // Check if the courseCode matches the pattern
        return matcher.matches();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course other = (Course) obj;
        return Objects.equals(courseCode.trim(), other.courseCode.trim()) &&
               Objects.equals(courseName.trim(), other.courseName.trim()) &&
               Objects.equals(courseLeader.trim(), other.courseLeader.trim()) &&
               Objects.equals(preRequisite.toString().trim(), other.preRequisite.toString().trim()) &&
               Objects.equals(programAssociated.toString().trim(), other.programAssociated.toString().trim());
    }
    
    public boolean addProgramme(ProgrammeClass newProgramme){
        return programAssociated.add(newProgramme);
    }
    
    public ProgrammeClass removeProgramme(int entryPosition){
        return programAssociated.remove(entryPosition);
    }
    
    public boolean addPreRequisite(String newPreRequisite){
        return preRequisite.add(newPreRequisite);
    }
    
    public String removePreRequisite(int entryPosition){
        return preRequisite.remove(entryPosition);
    }

    @Override
    public String toString() {
        String preRequisiteString;
        String programAssociatedString;
        
        if(preRequisite.isEmpty()){
            preRequisiteString = "NIL";
        }else{
            preRequisiteString = preRequisite.toString().trim();
        }
        
        if(programAssociated.isEmpty()){
            programAssociatedString = "NIL";
        }else{
            programAssociatedString = programAssociated.toString().trim();
        }
        
        return "Course Code : " + courseCode 
                + "\nCourse Name : " + courseName 
                + "\nCourse Leader : " + courseLeader 
                + "\nPre-requisite/ co-requisite (if any): \n" + preRequisiteString
                + "\n\nPrograms Associated with This Course :\n" + programAssociatedString 
                + "\n\n================================================================================================================\n";
    }

}
