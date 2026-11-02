package com.pluralsight;

import javax.swing.text.html.HTMLDocument;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        displayHomeScreen();

    }


    public static void displayHomeScreen() {


        //Created homescreen with String inputs
        // D) Adds deposits
        // P) makes payments (debit)
        // L) ledger home screen
        // X) exits the app

        String homeScreenText = """
                
                === HOME SCREEN ===
                
                D) Add Deposit 
                P) Make Payment(Debit)
                L) Ledger
                X) Exit
                Pick an option:""";
        String choice;
        do{
            choice = Console.promptForString(homeScreenText);
            switch (choice) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> displayLedger();
                default -> System.out.println("Invalid Input, try again..");

            }
        }while (!choice.equals("X"));


    }

    private static void addDeposit() {
        //turn Date info into LocalDate
        String dateInfo = Console.promptForString("Enter date format (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(dateInfo);

        //turns time info to LocalTime
        String timeInfo = Console.promptForString("Enter time format (hh:mm:ss");
        LocalTime time = LocalTime.parse(timeInfo);

        //Get description

    }

    private static void makePayment() {

    }

    private static void displayLedger() {

    }



}
