
package com.pluralsight;

import java.util.Scanner;


public class Console {

    private static final Scanner scanner = new Scanner(System.in);



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