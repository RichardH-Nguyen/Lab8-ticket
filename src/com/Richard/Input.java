package com.Richard;

import java.util.Scanner;

/**
 * Created by NinjaHunter on 3/20/17.
 */
public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int getPositiveIntInput(String question){
        if(question!= null){
            System.out.println(question);
        }
        while (true){
            try{
                String stringInput = scanner.nextLine();
                int intInput = Integer.parseInt(stringInput);
                if(intInput >= 0){
                    return intInput;
                }else {
                    System.out.println("Please enter a positive number");
                }
            }catch (NumberFormatException fne){
                System.out.println("Please type a positive whole number");
            }
        }
    }

    public static String getStringInput(String question){
        if(question != null){
            System.out.println(question);
        }
        String entry = scanner.nextLine();
        return entry;
    }
}
