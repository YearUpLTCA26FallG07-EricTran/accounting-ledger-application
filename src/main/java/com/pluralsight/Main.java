package com.pluralsight;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        loadAccountInfo();
        displayHomeScreen();



    }


    public static void displayHomeScreen() throws IOException {




        char choice;
        do{
            //Created homescreen with String inputs
            // D) Adds deposits
            // P) makes payments (debit)
            // L) ledger home screen
            // X) exits the app

            String homeScreenText = """
                
                ================================
                          HOME SCREEN 
                ================================
                
                """ + String.format("Current Balance: $%.2f%n", getBalance()) + """
                
                D) Add Deposit
                P) Make Payment(Debit)
                L) Ledger
                X) Exit
                

                --------------------------------
                Select an option: """;
            displayBalance();


            choice = Console.promptForChar(homeScreenText, "DPLX");
            switch (choice) {
                case 'D' -> addDeposit();
                case 'P' -> makePayment();
                case 'L' -> displayLedger();
                case 'X' -> System.out.println("Closing Program");
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

            //while there is still data in the csv read it but into separate parts
            while(line != null) {
                //splits the csv line into the 5 pieces
                String[] parts = line.split("\\|");

                //converts each variable into the right data type
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                //new Transaction object using data read from csv file
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

                //reads the next line of csv file
                line = reader.readLine();
            }
            //closes when finished reading csv file
            reader.close();
        }catch (IOException e){
            //this runs if there is a problem with file
            System.out.println("You have No Balance");
        }
    }

    public static double getBalance() {
        double balance = 0;

        for(Transaction transaction : transactions) {
            balance += transaction.getAmount();

        }
        return balance;

    }

    public static void displayBalance () {
        double balance = getBalance();
        System.out.printf("Current Balance: $%.2f%n", balance);
    }


    public static void addDeposit() throws IOException {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        //Get description
        String description = Console.promptForString("Enter description: ");

        //vendor
        String vendor = Console.promptForString("Enter vendor name: ");

        //amount
        double amount = Math.abs(Console.promptForDouble("Enter amount: "));

        //new Transaction object using data read from csv file
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //test
        System.out.println("Deposit added successfully!");

        //save deposit
        saveTransaction(transaction);

    }



    private static void makePayment() throws IOException {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        //Get description
        String description = Console.promptForString("Enter description: ");

        //vendor
        String vendor = Console.promptForString("Enter vendor name: ");

        //amount
        double amount = -Math.abs(Console.promptForDouble("Enter amount: "));

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //test
        System.out.println("Payment successful!");

        //saves transaction to the csv file
        saveTransaction(transaction);
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

        //writes the string into the CSV
        writer.write(line + "\n");
        //closes the writer
        writer.close();

        



    }

    private static void displayLedger() throws IOException {
        //Create Ledger Menu
        //All entries should show the newest entries first
        //A) All - Display all entries
        //D) Deposits - Display only the entries that are deposits into the account
        //P) Payments - Display only the negative entries (or payments)
        //R) Reports - A new screen that allows the user to run pre-defined reports or
        //to run a custom search


        String ledgerMenuScreen = """
                === Ledger ===
                A) Display all entries
                D) Deposits
                P) Payments
                R) Reports
                
                X) Return to Main Menu
                """;

        char choice;
        do{
            choice = Console.promptForChar(ledgerMenuScreen, "ADPRX");
            switch (choice) {
                case 'A' -> displayAllEntriesLedgerMenu();
                case 'D' -> depositsLedgerMenu();
                case 'P' -> paymentsLedgerMenu();
                case 'R' -> reportsLedgerMenu();
                case 'X' -> {}
            }
        } while(choice != 'X');




    }

    private static void displayAllEntriesLedgerMenu () {

    }

    private static void depositsLedgerMenu () {

    }

    private static void paymentsLedgerMenu() {

    }

    private static void reportsLedgerMenu() {

    }

}
