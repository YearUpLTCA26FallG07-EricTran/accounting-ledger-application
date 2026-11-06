package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Main {

    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        loadAccountInfo();
        displayHomeScreen();
    }

    public static void displayHomeScreen() {
        char choice;
        do {

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
            choice = Console.promptForChar(homeScreenText, "DPLX");

            switch (choice) {
                case 'D' -> addDeposit();
                case 'P' -> makePayment();
                case 'L' -> displayLedger();
                case 'X' -> System.out.println("Closing Program");
            }
        } while (choice != 'X');

    }

    public static void loadAccountInfo() {
        try {
            //reads the csv so any new saved transactions can be read
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));


            String line = reader.readLine();
            //skips the header in order to read the transaction data
            line = reader.readLine();

            //reads transaction.csv until there are no more records
            while (line != null) {
                //splits the csv line into the different fields
                String[] parts = line.split("\\|");

                //converts each variable into the right data type
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                //new Transaction object using data read from csv file
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                //stores the transaction  into the arraylistto use throughout the program
                transactions.add(transaction);

                //reads the next line of csv file
                line = reader.readLine();
            }
            //closes when finished reading csv file
            reader.close();
        } catch (IOException e) {
            //this runs if there is a problem with file || handles in case CSV file can't be accessed
            System.out.println("You have No Balance");
        }
    }

    public static double getBalance() {
        double balance = 0;
        //Loops through each transaction and adds it to balance
        //Deposits are both positive/negative
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;

    }


    public static void addDeposit() {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        //Get description
        String description = Console.promptForString("Enter description: ");
        //vendor
        String vendor = Console.promptForString("Enter vendor name: ");

        //ensures amount entered is positive
        double amount = Math.abs(Console.promptForDouble("Enter amount: "));

        //new Transaction object using data read from csv file
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);
        saveTransaction(transaction);
        System.out.println("Deposit added successfully!");


    }


    private static void makePayment() {

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String description = Console.promptForString("Enter description: ");
        String vendor = Console.promptForString("Enter vendor name: ");
        //ensures amount entered is - because of payment
        double amount = -Math.abs(Console.promptForDouble("Enter amount: "));

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);
        saveTransaction(transaction);


    }


    private static void saveTransaction(Transaction transaction) {
        String line = transaction.getDate() + "|"
                + transaction.getTime() + "|"
                + transaction.getdescription() + "|"
                + transaction.getVendor() + "|"
                + transaction.getAmount();

        try (FileWriter writer = new FileWriter("transactions.csv", true)) {
            //writes the string into the CSV
            writer.write(line + "\n");
        } catch (Exception e) {
            System.out.println("Error saving transaction");
        }


    }

    private static void displayLedger() {
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
                
                H) Return to Main Menu
                
                --------------------------------
                Select an option:  """;

        char choice;
        do {
            choice = Console.promptForChar(ledgerMenuScreen, "ADPRH");
            switch (choice) {
                case 'A' -> displayAllEntriesLedgerMenu();
                case 'D' -> depositsLedgerMenu();
                case 'P' -> paymentsLedgerMenu();
                case 'R' -> reportsLedgerMenu();
                case 'H' -> {
                }
            }
        } while (choice != 'H');


    }

    private static void displayAllEntriesLedgerMenu() {
        //Passes the transaction list into a reusable display method
        displayTransactions(transactions, "ALL TRANSACTIONS");
    }

    private static void depositsLedgerMenu() {
        //Creates a separate list to store only deposits
        ArrayList<Transaction> deposits = new ArrayList<>();

        //loops through the original transaction list
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            //checks if the transaction amount is positive
            if (transaction.getAmount() > 0) {
                //saves the deposits that pass through the if statement
                deposits.add(transaction);
            }
        }
        //Sends the filtered list to the reusable display method
        displayTransactions(deposits, "DEPOSITS");

    }

    private static void paymentsLedgerMenu() {

        ArrayList<Transaction> payments = new ArrayList<>();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (transaction.getAmount() < 0) {
                payments.add(transaction);

            }
        }
        displayTransactions(payments, "PAYMENTS");

    }

    private static void reportsLedgerMenu() {

        String ledgerMenuScreen = """
                === Reports ===
                
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor 
                0) Back 
                
                --------------------------------
                Select an option:  """;
        int choice;
        do {
            choice = Console.promptForInt(ledgerMenuScreen);
            switch (choice) {
                case 1 -> monthToDate();
                case 2 -> previousMonth();
                case 3 -> yearToDate();
                case 4 -> previousYear();
                case 5 -> searchByVendor();
                case 0 -> {
                }

                default -> {
                    if (choice != 0) {
                        System.out.println("Invalid Input. Enter A Valid Number.");
                    }
                }
            }

        } while (choice != 0);
    }

    private static void monthToDate() {
        //Get today's date and checks for the first day of current month
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        //Create new list to store the transaction of current month
        ArrayList<Transaction> monthTransactions = new ArrayList<>();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            LocalDate date = transaction.getDate();

            if (date.isAfter(startOfMonth) || date.equals(startOfMonth)) {
                monthTransactions.add(transaction);
            }
        }
        displayTransactions(monthTransactions, "THIS MONTH");

    }

    private static void previousMonth() {
        //This determines the range for the previous month
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);
        LocalDate startOfPreviousMonth = previousMonth.withDayOfMonth(1);
        LocalDate startOfCurrentMonth = today.withDayOfMonth(1);
        //New list to store transaction of previous month
        ArrayList<Transaction> previousMonthTransaction = new ArrayList<>();


        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            LocalDate date = transaction.getDate();

            if (!date.isBefore(startOfPreviousMonth) && date.isBefore(startOfCurrentMonth)) {
                previousMonthTransaction.add(transaction);
            }
        }
        displayTransactions(previousMonthTransaction, "PREVIOUS MONTH");
    }

    private static void yearToDate() {
        // Determines the first day of the current year
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);
        ArrayList<Transaction> yearToDateTransaction = new ArrayList<>();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            LocalDate date = transaction.getDate();

            if (!date.isBefore(startOfYear)) {
                yearToDateTransaction.add(transaction);

            }
        }
        displayTransactions(yearToDateTransaction, "YEAR TO DATE");
    }

    private static void previousYear() {
        //Determines the beginning of the previous year and current year
        //Dates establish the range of previous year reports
        LocalDate today = LocalDate.now();
        LocalDate startOfPreviousYear = today.minusYears(1).withDayOfYear(1);
        LocalDate startOfCurrentYear = today.withDayOfYear(1);
        ArrayList<Transaction> previousYearTransaction = new ArrayList<>();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            LocalDate date = transaction.getDate();

            if (!date.isBefore(startOfPreviousYear) && date.isBefore(startOfCurrentYear)) {
                previousYearTransaction.add(transaction);

            }
        }
        displayTransactions(previousYearTransaction, "PREVIOUS YEAR");
    }


    private static void searchByVendor() {
        //Prompts the user to enter Vendor they're searching for
        String searchVendorName = Console.promptForString("Enter Vendor Name: ");
        //List to store the Vendor Name that was searched
        ArrayList<Transaction> vendorTransaction = new ArrayList<>();
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            LocalDate date = transaction.getDate();

            if (transaction.getVendor().equalsIgnoreCase(searchVendorName)) {
                vendorTransaction.add(transaction);
            }

        }
        displayTransactions(vendorTransaction, "VENDOR");
    }

    private static void displayTransactions(
            ArrayList<Transaction> transactions, String title) {
        //Display the title and headers for the table
        System.out.println("=====================================================================================");
        System.out.printf("%45s", title);
        System.out.println("\n=====================================================================================");

        //Formats the headings and columns using spacing
        System.out.printf("%-12s %-10s %-25s %-20s %10s%n",
                "DATE", "TIME", "DESCRIPTION", "VENDOR", "AMOUNT");
        //Loops through the list backwards in order to get newest transactions first
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            //Converts the time into a 12-hour format
            String formattedTime = transaction.getTime()
                    .format(java.time.format.DateTimeFormatter.ofPattern("hh:mm a"));

            //Displays the data using aligned columns and amount by two decimal points
            System.out.printf("%-12s %-10s %-25s %-20s %10.2f%n",
                    transaction.getDate(),
                    formattedTime,
                    transaction.getdescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }
    }

}