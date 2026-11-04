package com.pluralsight;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

            //skips the header and reads line of data
            line = reader.readLine();

            //while there is still data in the csv read it but into seperate parts
            while(line != null) {
                //splits the csv line into the 5 pieces
                String[] parts = line.split("\\|");

                //converts each variable into the right data type
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

                line = reader.readLine();
            }
            reader.close();
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
        double amount = Math.abs(Console.promptForDouble("Enter amount: "));

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //test
        System.out.println("Deposit added successfully!");


    }

    private static void makePayment() {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        //Get description
        String description = Console.promptForString("Enter description: ");

        //vendor
        String vendor = Console.promptForString("Enter vendor name: ");

        //amount
        double amount = Math.abs(Console.promptForDouble("Enter amount: "));

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //test
        System.out.println("Payment successful!");

    }

    private static void saveTransaction(Transaction transaction) throws IOException {
        FileWriter writer = new FileWriter("transactions.csv", true);
        transaction.getDate();
        transaction.getTime();
        transaction.getdescription();
        transaction.getVendor();
        transaction.getAmount();
        String line = transaction.getDate() + "|"
                +  transaction.getTime() + "|"
                + transaction.getdescription() + "|"
                + transaction.getVendor() + "|"
                + transaction.getAmount();

        



    }

    private static void displayLedger() {

    }



}
