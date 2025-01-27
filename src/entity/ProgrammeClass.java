/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.util.Objects;

/**
 *
 * @author Lim Shuye
 */
public class ProgrammeClass {

    private String code;
    private String programName;
    private ListInterface<Tutorial> TGroup;
    private String faculty;
    private int year;

    public ProgrammeClass() {

    }

    public ProgrammeClass(String code, String programName, String faculty, int year) {
        this.code = code;
        this.programName = programName;
        this.TGroup = null;
        this.faculty = faculty;
        this.year = year;
    }

    public ProgrammeClass(String code, String programName, ListInterface<Tutorial> TGroup, String faculty, int year) {
        this.code = code;
        this.programName = programName;
        TGroup = (ArrayList<Tutorial>) TGroup;
        this.TGroup = TGroup;
        this.faculty = faculty;
        this.year = year;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProgramName() {
        return this.programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ListInterface<Tutorial> getTGroup() {
        return this.TGroup;
    }

    public void setTGroup(ListInterface<Tutorial> TGroup) {
        this.TGroup = TGroup;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public boolean addTGroup(Tutorial newEntry){
        return this.TGroup.add(newEntry);
    }

     public Tutorial removeTGroup(int givenPosition){
        return this.TGroup.remove(givenPosition);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgrammeClass other = (ProgrammeClass) obj;
        return Objects.equals(this.code, other.code);
    }
    
    //function: used to create a duplicate of the programme 
    public static ProgrammeClass copy(ProgrammeClass original){
        ProgrammeClass newProgramme = new ProgrammeClass();
        newProgramme.code = original.code;
        newProgramme.programName = original.programName;
        newProgramme.faculty = original.faculty;
        newProgramme.year = original.year;
        newProgramme.TGroup = original.TGroup;
        
        return newProgramme;
    }

    @Override
    public String toString() {

        String result = String.format("%-10s %-80s %-10d %-10s ", code, programName, year, faculty);

        return result;

    }

}
