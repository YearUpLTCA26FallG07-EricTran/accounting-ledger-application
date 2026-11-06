# Command-Line Banking Ledger

## Project Overview

This project is a Java-based command-line banking ledger application that allows users to manage deposits, payments, and view their account balance.

The application stores transaction information in a CSV file so that transaction history is saved and can be accessed again when the program is restarted.

## Features

- **View Account Balance:** Displays the current account balance by calculating all deposits and payments.
- **Add Deposits:** Allows users to add money to their account.
- **Make Payments:** Records payments as negative transactions.
- **View All Transactions:** Displays all transactions, with the newest transactions appearing first.
- **Filter Deposits:** Displays only positive transactions.
- **Filter Payments:** Displays only negative transactions.
- **Month-to-Date Report:** Displays transactions from the current month.
- **Previous Month Report:** Displays transactions from the previous month.
- **Year-to-Date Report:** Displays transactions from the current year.
- **Previous Year Report:** Displays transactions from the previous year.
- **Search by Vendor:** Allows users to search for transactions using a vendor name.

## Technologies Used

- Java
- ArrayList
- File I/O (BufferedReader and FileWriter)
- CSV file storage
- LocalDate and LocalTime
- Object-Oriented Programming (OOP)

## How It Works

### Transaction Management

Each transaction contains five pieces of information:

- Date
- Time
- Description
- Vendor
- Amount

Transactions are created as Transaction objects and stored in an ArrayList.

### Account Balance

The account balance is calculated by adding together all transaction amounts.

- Deposits are stored as positive values.
- Payments are stored as negative values.

### Data Storage

Transactions are saved in `transactions.csv` using a pipe (`|`).

When the application starts, it reads the CSV file and loads the existing transactions into the ArrayList.

### Ledger and Reports

The ledger allows users to view all transactions or filter them based on their type.

Reports use Java's LocalDate class to determine date ranges and filter transactions accordingly.

A reusable `displayTransactions()` method formats and displays transaction information in aligned columns.

## How to Run

1. Clone or download the project.
2. Open the project in IntelliJ IDEA or another Java IDE.
3. Make sure the `transactions.csv` file is available in the project's working directory.
4. Run the `Main.java` file.
5. Follow the command-line prompts to navigate the application.

## Project Structure

- `Main.java` – Contains the main application logic, menus, transaction management, reports, and file handling.
- `Transaction.java` – Represents individual transactions and stores transaction information.
- `Console.java` – Handles user input.
- `transactions.csv` – Stores transaction history.

## Learning Objectives

This project demonstrates the use of:

- Java methods and method reuse.
- ArrayLists for storing and filtering objects.
- Classes and objects.
- Loops and conditional statements.
- File reading and writing.
- Date and time handling.
- Formatting console output.
- Breaking a larger program into smaller, reusable methods.