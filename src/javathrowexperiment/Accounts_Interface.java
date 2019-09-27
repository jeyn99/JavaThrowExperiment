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
import static java.util.Objects.hash;
import java.util.Scanner;
import static javathrowexperiment.Accounts.account_id;

/**
 *
 * @author morrejo_sd2023
 */
public class Accounts_Interface {

    ArrayList<Accounts> a;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public Accounts_Interface() {
        a = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("--- RETRIEVE ---");
        System.out.println("\n\t*** Accounts ***");
        a = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t");
                a.add(new Accounts(Integer.parseInt(partsA[0]), partsA[1], partsA[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void create() throws IOException, PasswordException {
        String username, password, confirm;
        System.out.print("\n--- CREATE ---\nEnter username : ");
        username = input1.next();
        while (!Check.isString(username)) {
            System.out.println("Username containing all number.");
            create();
        }
        while (true) {
            try {
                System.out.print("Enter password : ");
                password = input1.next();
                if (password.length() >= 8) {
                    break;
                } else {
                    throw new PasswordException("Password too short.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        while (true) {
            try {
                System.out.print("Re-enter password : ");
                confirm = input2.next();
                if (confirm == null ? password == null : confirm.equals(password)) {
                    break;
                } else {
                    throw new PasswordException("Password mismatch.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        a.add(new Accounts(account_id, username, password));
        ++account_id;
    }

    public void update() {
        String username, password, confirm;
        System.out.print("\n--- UPDATE ---\nID number : ");
        int updateID = input1.nextInt();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getAcc_id() == updateID) {
                System.out.print("Enter username : ");
                username = input2.next();
                while (!Check.isString(username)) {
                    System.out.println("Username containing all number.");
                    update();
                }
                a.get(i).setUsername(username);
                while (true) {
                    try {
                        System.out.print("Enter password : ");
                        password = input3.next();
                        if (password.length() >= 8) {
                            break;
                        } else {
                            throw new PasswordException("Password too short.");
                        }
                    } catch (PasswordException ex) {
                        System.out.println(ex);
                    }
                }
                while (true) {
                    try {
                        System.out.print("Re-enter password : ");
                        confirm = input3.next();
                        if (confirm == null ? password == null : confirm.equals(password)) {
                            break;
                        } else {
                            throw new PasswordException("Password mismatch.");
                        }
                    } catch (PasswordException ex) {
                        System.out.println(ex);
                    }
                }
                a.get(i).setPassword(password);
            }
        }

    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts.txt"))) {
            String str;
            writer.flush();
            for (int i = 0; i < a.size(); i++) {
                str = a.get(i).getAcc_id() + "\t" + a.get(i).getUsername() + "\t" + hash(a.get(i).getPassword().toCharArray());
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void delete(int acc_id) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getAcc_id() == acc_id) {
                a.remove(i);
            }
        }
    }

    public void search(int acc_id) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getAcc_id() == acc_id) {
                System.out.println(a.get(i).getAcc_id() + "\t" + a.get(i).getUsername() + "\t" + a.get(i).getPassword());
            }
        }
    }

}
