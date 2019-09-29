/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static javathrowexperiment.Accounts.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class Personal_Information_Interface {

    ArrayList<PersonalInformation> pi;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public Personal_Information_Interface() {
        pi = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("\n\t\t    *** Personal Information ***");
        pi = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t\t");
                pi.add(new PersonalInformation(Integer.parseInt(partsA[0]), Integer.parseInt(partsA[1]), partsA[2], partsA[3], partsA[4]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void create() throws IOException, PasswordException {
        String fname, lname, age;
        System.out.print("Enter first name : ");
        fname = input1.nextLine();
        while (!Check.isString(fname)) {
            System.out.println("First name does not contain any number.");
            create();
        }
        System.out.print("Enter last name : ");
        lname = input2.nextLine();
        while (!Check.isString(lname)) {
            System.out.println("Last name does not contain any number.");
            create();
        }
        System.out.print("Enter age : ");
        age = input3.nextLine();
        while (Check.isString(age)) {
            System.out.println("Age is not a string.");
            create();
        }
        if (pi.isEmpty()) {
            pi.add(new PersonalInformation(1, account_id - 1, fname, lname, age));
        } else {
            pi.add(new PersonalInformation(pi.get(pi.size() - 1).getId() + 1, account_id, fname, lname, age));
        }
    }

    public void update(int acc_id) {
        String fname, lname, age;
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getAccount_id() == acc_id) {
                System.out.print("Enter new first name : ");
                fname = input1.next();
                while (!Check.isString(fname)) {
                    System.out.println("First name does not contain any number.");
                    update(acc_id);
                }
                System.out.print("Enter new last name : ");
                lname = input2.nextLine();
                while (!Check.isString(lname)) {
                    System.out.println("Last name does not contain any number.");
                    update(acc_id);
                }
                System.out.print("Enter new age : ");
                age = input2.next();
                while (Check.isString(age)) {
                    System.out.println("Age is not a string.");
                    update(acc_id);
                }
                pi.get(i).setFname(fname);
                pi.get(i).setLname(lname);
                pi.get(i).setAge(age);
            }
        }

    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt"))) {
            String str;
            writer.flush();
            for (int i = 0; i < pi.size(); i++) {
                str = pi.get(i).getId() + "\t\t" + pi.get(i).getAccount_id() + "\t\t" + pi.get(i).getFname() + "\t\t" + pi.get(i).getLname() + "\t\t" + pi.get(i).getAge();
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void delete(int acc_id) {
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getAccount_id() == acc_id) {
                pi.remove(i);
                System.out.println("Account ID " + acc_id + " in Personal Information has been deleted!");
            }
        }
    }

    public void search(int acc_id) {
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getAccount_id() == acc_id) {
                System.out.println(pi.get(i).getId() + "\t" + pi.get(i).getAccount_id() + "\t" + pi.get(i).getFname() + "\t" + pi.get(i).getLname() + "\t" + pi.get(i).getAge());
            }
        }
    }
}
