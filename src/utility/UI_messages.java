/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * Chew Wei Seng 22WMR14168
 */
public class UI_messages {
    private TextColor textColor = new TextColor();
    
    public void displayErrorMessage(String msg){
        textColor.setRedTextColor();
        System.out.println(msg);
        textColor.resetTextAttributes();
        System.out.print("\n");
    }
    
    public void displaySuccessMessage(String msg){
        textColor.setGreenTextColor();
        System.out.println(msg);
        textColor.resetTextAttributes();
        System.out.print("\n");
    }
    
    public void displayConfirmationMessageAre(String msg){
        System.out.print("Are you sure you want to " + msg + " ? (Y/N) : ");
    }
    
    public void displayConfirmationMessageDo(String msg){
        System.out.print("Do you want to " + msg + " ? (Y/N) : ");
    }
    
    public String isNotUniqueMessage(String msg){
        return ("The " + msg + " already exists. Please enter a unique " + msg + " .");
    }
}
