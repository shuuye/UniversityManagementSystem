// Wong Chai Yee
// Student Id 22WMR13766
// RSD2S1 G3

package entity;

import adt.ListInterface;

public class TutorEntity {  
    
    private int tutorId;
    private String tutorName;
    private String tutorType;
    private String gender;

    public TutorEntity() {
    }

    public TutorEntity(int tutorId, String tutorName, String tutorType, String gender) {
        this.tutorId = tutorId;
        this.tutorName = tutorName;
        this.tutorType = tutorType;
        this.gender = gender;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorType() {
        return tutorType;
    }

    public void setTutorType(String tutorType) {
        this.tutorType = tutorType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    
    
  

    @Override
    public String toString() {
        String outputStr = String.format("%-10s %-30s %-15s Tutor Details%n", "Tutor ID"+tutorId, " Tutor Name"+tutorName, "Tutor Type"+tutorType+ "Gender"+ gender+ "\n");
        return outputStr;
    }

}
