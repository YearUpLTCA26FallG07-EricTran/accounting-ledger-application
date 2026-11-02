
package com.pluralsight;

import java.util.Scanner;


public class Console {

    private static final Scanner scanner = new Scanner(System.in);


    public static char promptForChar(String prompt, String validOptions) {
        while(true) {
            System.out.print(prompt);
            char character = 0;
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 ){
                character = input.charAt(0);
            }

            if (validOptions.indexOf(character) >= 0){
                return character;
            }
        }
    }

    public static double promptForDouble(String prompt){
        while(true) {
            System.out.print(prompt);

            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    public static String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int promptForInt(String prompt){

        int result;

        while(true){
            try{

                System.out.print(prompt);
                result = scanner.nextInt();
                scanner.nextLine();
                return result;
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number.");
                scanner.nextLine();
            }
        }
    }
}