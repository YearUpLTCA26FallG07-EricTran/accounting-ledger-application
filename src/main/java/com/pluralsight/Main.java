package com.pluralsight;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        loadAccountInfo();
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
                Pick an option: """;
        char choice;
        do{
            choice = Console.promptForChar(homeScreenText, "DPLX");
            switch (choice) {
                case 'D' -> addDeposit();
                case 'P' -> makePayment();
                case 'L' -> displayLedger();
                case 'X' -> {}
            }
        }while (choice != 'X');


    }

    public static void loadAccountInfo() {
        try {
            //reads the csv file
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            //first line of the csv stored as a string
            String line = reader.readLine();
            System.out.println(line);
        }catch (IOException e){
            //this runs if there is a problem with file
            System.out.println("+ You have No Balance");
        }
    }


    private static void addDeposit() {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        //Get description
        String description = Console.promptForString("Enter description: ");

        //vendor
        String vendor = Console.promptForString("Enter vendor name: ");

        //amount
        double amount = Console.promptForDouble("Enter amount: ");
        
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

    }

    private static void makePayment() {

    }

    private static void displayLedger() {

    }



}
