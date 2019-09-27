/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author morrejo_sd2023
 */
public class JavaThrowExperiment {

    public static void main(String args[]) throws IOException, PasswordException {
        Accounts_Interface ai = new Accounts_Interface();
        Personal_Information_Interface pi = new Personal_Information_Interface();
        Courses_Interface ci = new Courses_Interface();
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        boolean exit = false;

        while (exit == false) {
            System.out.println("\n***** CHOICES *****");
            System.out.println("c -- create\nr -- retrieve\nu -- update\nd -- delete\ns -- save\nf -- search\ne -- exit");
            System.out.print("Choice : ");
            String choice1 = input1.next();
            switch (choice1) {
                case "c":
                    ai.create();
                    System.out.print("Do you want to add personal information (y/n) ? ");
                    String choice4 = input2.next();
                    switch (choice4) {
                        case "y":
                            pi.create();
                            while (true) {
                                System.out.print("Do you want to add courses (y/n) ? ");
                                String choice5 = input3.next();
                                if ("y".equals(choice5)) {
                                    ci.create();
                                } else {
                                    break;
                                }
                            }
                        case "n":
                            break;
                    }
                    break;
                case "r":
                    ai.retrieve();
                    pi.retrieve();
                    ci.retrieve();
                    break;
                case "u":
                    System.out.println("\n--- UPDATE ---");
                    System.out.print("Account ID :");
                    int choice6 = input2.nextInt();
                    pi.update(choice6);
                    ci.update(choice6);
                    break;
                case "d":
                    System.out.println("\n--- DELETE ---");
                    System.out.print("Account ID :");
                    int choice2 = input2.nextInt();
                    ai.delete(choice2);
                    pi.delete(choice2);
                    ci.delete(choice2);
                    break;
                case "s":
                    System.out.println("\n--- SAVE ---");
                    ai.save();
                    pi.save();
                    ci.save();
                    break;
                case "f":
                    System.out.println("\n--- SEARCH ---");
                    System.out.print("Account ID :");
                    int choice3 = input3.nextInt();
                    ai.search(choice3);
                    pi.search(choice3);
                    ci.search(choice3);
                    break;
                case "e":
                    exit = true;
                    break;
            }
        }
    }

}
