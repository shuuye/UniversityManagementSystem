// Wong Chai Yee
// Student Id 22WMR13766
// RSD2S1 G3
package entity;

import adt.*;

public class TeachingEntity {
    private TutorEntity tutor;
    private ListInterface<Course> courseList;
    private ListInterface<Tutorial> tGroup;

    public TeachingEntity(ListInterface<Course> courseList, ListInterface<Tutorial> tGroup, TutorEntity tutor) {
        this.courseList = courseList;
        this.tutor = tutor;
        this.tGroup = tGroup;
        
    }

    public TeachingEntity(ListInterface<Course> courseList, TutorEntity tutor) {
        this.courseList = courseList;
        this.tutor = tutor;
        
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ListInterface<Course> courseList) {
        this.courseList = courseList;
    }

    public TutorEntity getTutor() {
        return tutor;
    }

    public void setTutor(TutorEntity tutor) {
        this.tutor = tutor;
    }

    public ListInterface<Tutorial> gettGroup() {
        return tGroup;
    }

    public void settGroup(ListInterface<Tutorial> tGroup) {
        this.tGroup = tGroup;
    }
    
    public boolean assignTutorToCourse(Course course){
    return this.getCourseList().add(course);
    }
    
    public boolean tutorAdd(Tutorial tutorial){
    return this.gettGroup().add(tutorial);
    }
 
    @Override
    public String toString() {

        String output = String.format("Record for tutor: \n%s\n The course taken by this tutor: \n%s\n The tutorial group under tutor:\n%s\n ...........................NEXT RECORD.........................\n", tutor,courseList, tGroup);
        return output;

    }

}
