/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileExprement;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author repolloja_sd2023
 */
public class FileExperimentMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, PasswordException {
        AccountMenu account = new AccountMenu();
        PersonalInfo info = new PersonalInfo();
        CourseMenu course = new CourseMenu();
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (exit == false) {

            System.out.println("Welcome! ");
            System.out.println("\n[1] Create\n[2] Retrieve\n[3] Update\n[4] Delete\n[5] Save\n[6] Search\n[7] Exit\n");
            System.out.print("Choice : ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    account.create();
                    System.out.print("ADD PERSONAL INFORMATION? y/n: ");
                    String ans = input.next();
                    switch (ans) {
                        case "y":
                        case "Y":
                            info.create();
                            while (true) {
                                System.out.print("ADD COURSES? (y/n): ");
                                String choice5 = input.next();
                                if ("y".equals(choice5)) {
                                    course.create();
                                } else {
                                    break;
                                }
                            }
                        case "n":
                        case "N":
                            break;
                    }
                    break;
                case 2:
                    account.retrieve();
                    info.retrieve();
                    course.retrieve();
                    break;
                case 3:
                    try {
                        System.out.println("\nUPDATE");
                        System.out.print("Account ID :");
                        int id = input.nextInt();
                        while (account.checkIDin(id)) {
                            System.out.print("ADD Personal info? (y/n): ");
                            String answ = input.next();
                            if ("y".equals(answ)) {
                                if(info.pi.isEmpty()) {
                                    info.create();
                                } else {
                                    info.update(id);
                                }
                            } else {                
                                break;
                            }
                        }
                        while (account.checkIDin(id)) {
                            System.out.print("ADD COURSES? (y/n): ");
                            String answ = input.next();
                            if ("y".equals(answ)) {
                                if (course.c.isEmpty()) {
                                    course.create();
                                } else {
                                    course.update(id);

                                }
                            } else {                               
                                break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input Mismatch");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nDELETE");
                        System.out.print("Account ID :");
                        int choice2 = input.nextInt();
                        info.delete(choice2);
                        course.delete(choice2);
                    } catch (InputMismatchException e) {
                        System.out.println("Input Mismatch!");
                    }
                    break;
                case 5:
                    System.out.println("SAVE");
                    account.save();
                    info.save();
                    course.save();
                    break;
                case 6:
                    try {
                        System.out.println("SEARCH");
                        System.out.print("Account ID :");
                        int choice3 = input.nextInt();
                        account.search(choice3);
                        info.search(choice3);
                        course.search(choice3);
                    } catch (InputMismatchException e) {
                        System.out.println("Mismatch input.");
                    }
                    break;
                case 7:
                    exit = true;
                    break;
            }

        }
    }

}
