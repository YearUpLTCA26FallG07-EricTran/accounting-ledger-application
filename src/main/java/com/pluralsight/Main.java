package com.pluralsight;

public class Main {

    public static void main(String[] args) {

        displayHomeScreen();

    }


    public static void displayHomeScreen() {

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


    }

    private static void makePayment() {

    }

    private static void displayLedger() {

    }



}
