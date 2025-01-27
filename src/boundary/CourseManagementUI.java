/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package boundary;

import adt.ListInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import utility.CheckDataType;
import utility.CheckEmpty;
import utility.CheckInputRange;
import utility.UI_messages;

/**
 *
 * Author :  Chew Wei Seng 22WMR14168
 */

public class CourseManagementUI {
    private CheckEmpty checkEmpty = new CheckEmpty();
    private CheckDataType checkDataType = new CheckDataType();
    private UI_messages uiMsg = new UI_messages();   
    private CheckInputRange checkRange = new CheckInputRange();
    
    public void displayMenu(){
        System.out.println("\n===================================\n    COURSE MANAGEMENT MAIN MENU\n===================================\n");
                                
        System.out.println("1. Add a new course");
        System.out.println("2. Remove a course");
        System.out.println("3. Find course");
        System.out.println("4. Amend course details");
        System.out.println("5. List courses taken by different faculties");
        System.out.println("6. List all courses");
        System.out.println("7. Add a programme to a course");
        System.out.println("8. Remove a programme from a course");
        System.out.println("9. Generate reports");
        System.out.println("0. Quit\n");
    }
    
    public void courseRegistrationBanner(){
        System.out.println("\n ADD THE NEW COURSE HERE");
        System.out.println("=========================\n");
    }
    
    public void removeCourseBanner(String header){
        System.out.println("\n  Remove a " + header + " here");
        System.out.println("===================================================\n");
        
    }
    
    public void allCoursesListingBanner(){
        System.out.println("\n      COURSES LIST");
        System.out.println("=========================\n");
        
    }
    
    public void courseSearchingBanner(){
        System.out.println("\n     COURSE SEARCHING");
        System.out.println("=========================\n");
        
    }
    
    public void coursesUnderAFacultyBanner(){
        System.out.println("\n     SELECT A FACULTY");
        System.out.println("===========================\n");
                 
    }
    
    public void amendCourseDetailsBanner(){
        System.out.println("\n AMEND A COURSE DETAILS");
        System.out.println("=========================\n");
    }
    
    public void addProgrammeToACourseBanner(){
        System.out.println("\n CHOOSE A COURSE TO ADD PROGRAMME");
        System.out.println("==================================\n");
        
    }
    
    public void removeProgrammeFromACourseBanner(){
        System.out.println("\n CHOOSE A COURSE TO REMOVE PROGRAMME");
        System.out.println("=====================================\n");
        
    }
    
    public void summaryReportMenu(){
        System.out.println("\n   SUMMARY REPORT");
        System.out.println("====================\n");
        System.out.println("1. Course General Summary Report");
        System.out.println("2. Filter Summary Report By the Faculty");
        System.out.println("0. Quit\n");
        
    }
    
    private void printReportGeneratedDateTime(){
        // Get the current date and time
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        // Print the formatted date and time
        System.out.println("Report generated on: " + formattedDate + "\n");
    }
        
    public void printSummaryReportTitle(){
        System.out.println("\n                       COURSE MANAGEMENT SUMMARY REPORT\n");
        printReportGeneratedDateTime();
    }
    
    public void printSummaryReportFilteredByFacultyTitle(){
        System.out.println("\n                 FACULTY POPULARITY SUMMARY FOR EACH COURSE REPORT\n");
        printReportGeneratedDateTime();
    }
    
    public void amendOption(){
        System.out.println("\n Choose a criteria to modify");
        System.out.println("==============================");
        System.out.println("\n 1)Course Leader");
        System.out.println(" 2)Pre-requisite/ co-requisite");
        System.out.println(" 3)Programme Associate");
        System.out.println(" 4)Quit");
        System.out.println(" 0)Cancel All Modification & Quit\n");
        
    }
    
    public void amendListOption(String msg){
        System.out.println("\n Choose a option");
        System.out.println("==============================");
        System.out.println(("\n 1)Add new " + msg + " for a course"));
        System.out.println((" 2)Remove a " + msg + " for a course"));
        System.out.println(" 0)Cancel\n");
    }
    
    public void searchingOption(){
        System.out.println("1) Search by course code");
        System.out.println("2) Search by typing\n");
    }
    
    
    public boolean inputIsInRange(int choiceInt,int min, int max){
        if(!checkRange.checkIntRange(min, max, choiceInt)){
            if(choiceInt == min){
                uiMsg.displayErrorMessage("Please only input " + min + ".");
            }else{
                uiMsg.displayErrorMessage("Please only input between " + min + " and " + max + ".");
            }
            return false;
        }else{
            return true;
        }
    }
    
    public boolean inputIsInt (String choice){
        return checkDataType.isInteger(choice);
    }
    
    public int checkOptionInput(int choiceInt,int min, int max){
        boolean correctMenuInput;
        do{
            correctMenuInput=true;
            
            String choice = "";
            choice=checkEmpty.checkEmptyInputStr("Enter your option number here :", choice, "Please at least select one of the options");
                  
            correctMenuInput = inputIsInt(choice);
            
            if (correctMenuInput) {
                choiceInt = Integer.parseInt(choice);
                // Now you can safely use quantityInt as an integer.
                
                correctMenuInput = inputIsInRange(choiceInt,min,max);
            }else{
                uiMsg.displayErrorMessage("Please only input number from the options.");
            }
                        
        }while (correctMenuInput == false);
        
        return choiceInt;
    }
    
    public int getChoice(int min, int max) {
        
        int choiceInt = 0;
        //only can input between min and max inputed
        choiceInt = checkOptionInput(choiceInt,min,max);
        
        return choiceInt;
    }
    
    public int chooseOptionFrom1(int numberOfEntries){
        int choice = 0;
        //only can input between min inputed and the numberOfEntries
        choice = checkOptionInput(choice,1,numberOfEntries);
        
        return choice;
    }
    
    public String strReadNCheckEmpty(String label){
        String input="";
        input = checkEmpty.checkEmptyInputStr((label + " :"), input, ("Please fill in the " + label));
        
        return input;
    }

    public void listStrFromNonEmptyList(String outputStr, String emptyStringMsg){
        if(!"".equals(outputStr)){
            System.out.println(outputStr);
        }else{
            uiMsg.displayErrorMessage(emptyStringMsg);
        }
    }
    
    public <T> void printListValue1By1WithTypeInOrderList(String type, ListInterface<T> list){
        for(int i=1; i <= list.getNumberOfEntries(); i++){
            System.out.println(type + " " + i + "\n-------------------\n" + list.getEntry(i));
        } 
    }
    
    public <T> void printListValueInNumberOrderWithHeader(String header, ListInterface<T> list){
        System.out.println(header);
        for(int i=1; i <= list.getNumberOfEntries(); i++){
            System.out.println(i + " ." + list.getEntry(i));
        } 
        System.out.println("");
    }
    
    public <T> void printAnEntryWithTitle(String title, T entry){
        System.out.println("\n" + title + "\n---------------------------");
        System.out.println(entry + "\n");
    }
  
    private String setPercentageBar(int divider, int remainder){
        String percentageBar = "";
        
        for(int i=1; i<=divider; i++){
            percentageBar += "      ";
        }
        if(remainder >= 5){
            percentageBar += "   ";
            remainder -= 5;
        }
        for(int j=1; j<=remainder; j++){
            percentageBar += " ";
        }
        
        return percentageBar;
    }
    
    private void printGraph(int[] enrolledStudentCountForEachCourseArray, String[][] courseCodeArray, int totalEnrolledStudent){
        //percentage of enrolled student count for each course
        System.out.println("\nPercentages Of The Course Popularity\n----------------------------------------");
        String summaryCountStr="";
        for(int n=0; n < enrolledStudentCountForEachCourseArray.length; n++){

            double percentageOfCount = Math.round(((double)enrolledStudentCountForEachCourseArray[n] / (double)(totalEnrolledStudent+1))/*//here dont plus 1*/ * 100);
            
            int divider = (int)percentageOfCount / 10;
            int remainder = (int)percentageOfCount % 10;
            
            String percentageBar = setPercentageBar(divider,remainder);
 
            summaryCountStr += String.format("%s = [%s] (%2.0f%s) \n",courseCodeArray[n][0],percentageBar,percentageOfCount,"%"); 
            
        }
        
        System.out.println(summaryCountStr);
    }
    
    private static int findLongestStringLength(String[] array) {
        int maxLength = 0;

        for (String s : array) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }

        return maxLength;
    }
    
    private void printCoursePopularityHeader(int longestString){        
        int num = (longestString/2)+1;
        String format = String.format("| %-" + num + "s Course %-" + num + "s | %-10s | %-12s |", "", "","Popularity","Revenue (RM)");
        String line="";
        for(int i=0; i<format.length();i++){
             line += "=";
        }
        System.out.println(line + "\n" + format + "\n" + line);
    }
    
    private double calculateRevenue(char courseCredit, int popularity){
        double price = 0;
        
        switch(courseCredit){
            case '2' -> price = 518 * popularity;
            case '3' -> price = 777 * popularity;
            case '4' -> price = 1036 * popularity;
        }
        
        return price;
    }
    
    private void printPopularityAndRevenueOfCourse(int longestString, String[] codeAndName,int[] enrolledStudentCountForEachCourseArray){        
        int num = longestString + 3;
        String format = "";
        int[] popularity = enrolledStudentCountForEachCourseArray;
        double revenue = 0;
        int totalPopularity = 0;
        double totalRevenue = 0;
        
        int y=0;
        for(int i=0; i < codeAndName.length; i++){
            revenue = calculateRevenue(codeAndName[i].charAt(7),popularity[i]);
            format += String.format("| %d. | %-" + num + "s  | %-10d | %-12.2f |\n",(i+1), codeAndName[i],popularity[i],revenue);
            totalPopularity += popularity[i];
            totalRevenue += revenue;
            y++;
        }
        
        System.out.println(format);
        
        String line="";
        for(int i=0; i<(format.length()/y);i++){
             line += "=";
        }
        
        format = String.format("|      %-" + num + "s  | %-10d | %-12.2f |","Total :",totalPopularity,totalRevenue);
        
        System.out.println(line + "\n" + format + "\n" + line + "\n");
    
    }
    
    public void displaySummaryPercentageGraph(int[] enrolledStudentCountForEachCourseArray, String[][] courseCodeArray){
        
        int totalEnrolledStudent = 0;
        for(int l=0; l < enrolledStudentCountForEachCourseArray.length; l++){
            totalEnrolledStudent += enrolledStudentCountForEachCourseArray[l];
        }
        
        String[] codeAndName = new String [enrolledStudentCountForEachCourseArray.length];
        for(int j=0; j < enrolledStudentCountForEachCourseArray.length; j++){
            codeAndName[j] = (courseCodeArray[j][0] + " - " + courseCodeArray[j][1]);
        }
        
        // Find the index of the longest string
        int longestString = findLongestStringLength(codeAndName);
        
        printSummaryReportTitle();
        
        printCoursePopularityHeader(longestString);
        
        printPopularityAndRevenueOfCourse(longestString, codeAndName, enrolledStudentCountForEachCourseArray);
        
        printGraph(enrolledStudentCountForEachCourseArray, courseCodeArray, totalEnrolledStudent);
    }
    
    public void displaySummaryReportFilteredByFaculty(String[][] courseCodeArray, String[][] programmeCodeAndNameArray, int[][] studentInEachProgrammeCount, String selectedFaculty){
        
        String[] codeAndName = new String [courseCodeArray.length];
        for(int j=0; j < courseCodeArray.length; j++){
            codeAndName[j] = (courseCodeArray[j][0] + " - " + courseCodeArray[j][1]);
        }
        
        String equalLine="";
        String dashLine="";
        for(int i=0; i <= 92; i++){
            equalLine += "=";
            dashLine += "-";
        }
        
        printSummaryReportFilteredByFacultyTitle();
        
        System.out.println("The List Of Course That Offered to " + selectedFaculty + " :\n" + equalLine + "\n");
        
        int subtotal = 0;
        
        for(int i=0; i < codeAndName.length; i++){
            System.out.println(codeAndName[i] + "\n" + dashLine);
            String outputStr ="";
            int total = 0;
            for(int j=0; j < programmeCodeAndNameArray.length; j++){
                if(programmeCodeAndNameArray[i][j] != null){
                   outputStr += String.format("%-85s | %-3d |\n", programmeCodeAndNameArray[i][j], studentInEachProgrammeCount[i][j]);
                   total += studentInEachProgrammeCount[i][j];
                   subtotal += studentInEachProgrammeCount[i][j];
                }
                
            }
            
            System.out.println(outputStr + dashLine);
            System.out.printf("%-85s | %-3d |\n",("Total Student In " + codeAndName[i] + " For " + selectedFaculty + " : "),total);
            System.out.println(equalLine + "\n");
        }
        
        System.out.println("\nSubtotal of the popularity in " + selectedFaculty + " = " + subtotal + " students \n");
    }
}
