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
        char choice;
        do{
            choice = Console.promptForChar(homeScreenText, "DMLX");
            switch (choice) {
                case 'D' -> addDeposit();
                case 'M' -> makePayment();
                case 'L' -> displayLedger();
                default -> System.out.println("Invalid Input, try again..");

            }
        }while (choice != 'X');


    }

    private static void addDeposit() {
        //turn Date info into LocalDate
        String dateInfo = Console.promptForString("Enter date format (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(dateInfo);

        //turns time info to LocalTime
        String timeInfo = Console.promptForString("Enter time format (hh:mm:ss): ");
        LocalTime time = LocalTime.parse(timeInfo);

        //Get description
        String product = Console.promptForString("Enter product: ");

        //vendor
        String vendor = Console.promptForString("Enter vendor name: ");

        //amount
        double amount = Console.promptForDouble("Enter amount: ");

    }

    private static void makePayment() {

    }

    private static void displayLedger() {

    }



}
