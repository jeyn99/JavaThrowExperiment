/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileExprement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static FileExprement.Accounts.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class PersonalInfo {

    ArrayList<PersonalInformation> pi;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);
    

    public PersonalInfo() {
        pi = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("\n\t\tPersonal Information");
        pi = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\JaneRepollo\\jyn\\JavaThrowExperiment\\PersonalInfo.txt"))) {
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
    

    public void create() throws IOException {
        String fname, lname, age;
        System.out.print("Firstname: ");
        fname = input1.nextLine();
        while (!Check.isString(fname)) {
            System.out.println("Invalid format.");
            create();
        }
        System.out.print("Lastname : ");
        lname = input2.nextLine();
        while (!Check.isString(lname)) {
            System.out.println("Invalid format.");
            create();
        }
        System.out.print("Age : ");
        age = input3.nextLine();
        while (Check.isString(age)) {
            System.out.println("Age is not a string.");
            create();
        }
        if (pi.isEmpty()) {
            pi.add(new PersonalInformation(1, account_id, fname, lname, age));
        } else {
            pi.add(new PersonalInformation(pi.get(pi.size() - 1).getId() + 1, account_id, fname, lname, age));
        }
    }
    
    public void create(int id) throws IOException {
        String fname, lname, age;
        System.out.print("Firstname: ");
        fname = input1.nextLine();
        while (!Check.isString(fname)) {
            System.out.println("Invalid format.");
            create();
        }
        System.out.print("Lastname : ");
        lname = input2.nextLine();
        while (!Check.isString(lname)) {
            System.out.println("Invalid format.");
            create();
        }
        System.out.print("Age : ");
        age = input3.nextLine();
        while (Check.isString(age)) {
            System.out.println("Age is not a string.");
            create();
        }
        if (pi.isEmpty()) {
            pi.add(new PersonalInformation(1, account_id, fname, lname, age));
        } else {
            pi.add(new PersonalInformation(pi.get(pi.size() - 1).getId() + 1, id, fname, lname, age));
        }
    }
    

    public void update(int acc_id) throws IOException {
        String fname = null, lname = null, age = null;
        boolean in = false;
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getAccount_id() == acc_id) {
                System.out.println("\t\tPersonal Information");
                System.out.println(pi.get(i).getId() + "\t" + pi.get(i).getAccount_id() + "\t" + pi.get(i).getFirstname() + "\t" + pi.get(i).getLasName() + "\t" + pi.get(i).getAge());
                System.out.print("\nFirstname : ");
                fname = input1.next();
                while (!Check.isString(fname)) {
                    System.out.println("Invalid Format.");
                    update(acc_id);
                }
                System.out.print("Lastname : ");
                lname = input2.nextLine();
                while (!Check.isString(lname)) {
                    System.out.println("Invalid Format");
                    update(acc_id);
                }
                System.out.print("Age : ");
                age = input2.next();
                while (Check.isString(age)) {
                    System.out.println("Invalid format!");
                    update(acc_id);
                }
                pi.get(i).setFirstname(fname);
                pi.get(i).setLastName(lname);
                pi.get(i).setAge(age);
                in = true;
            } 
        }
        if(!in) {          
            create(acc_id);
        }
    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\JaneRepollo\\jyn\\JavaThrowExperiment\\PersonalInfo.txt"))) {
            String str;
            writer.flush();
            for (PersonalInformation pi1 : pi) {
                str = pi1.getId() + "\t\t" + pi1.getAccount_id() + "\t\t" + pi1.getFirstname() + "\t\t" + pi1.getLasName() + "\t\t" + pi1.getAge();
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
                System.out.println("Account ID " + acc_id + " in personal info succesfully deleted!");
            }
        }
        for (int i = 0; i < pi.size(); i++) {
            if (pi.get(i).getId() != i) {
                pi.get(i).setId(i + 1);
            }
            if (i == pi.size() - 1) {
                break;
            }
        }
    }

    public void search(int acc_id) {
        pi.stream().filter((pi1) -> (pi1.getAccount_id() == acc_id)).forEach((pi1) -> {
            System.out.println(pi1.getId() + "\t" + pi1.getAccount_id() + "\t" + pi1.getFirstname() + "\t" + pi1.getLasName() + "\t" + pi1.getAge());
        });
    }
}
