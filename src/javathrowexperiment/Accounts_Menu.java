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
public class Accounts_Menu {

    ArrayList<Accounts> a;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public Accounts_Menu() {
        a = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("Retrieve : ");
        System.out.println("\n\t\t\t*** Your Account ***");
        a = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t\t");
                a.add(new Accounts(Integer.parseInt(partsA[0]), partsA[1], partsA[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file has found!!!");
        }
    }

    public void create() throws IOException, PasswordException {
        String username, password, confirm;
        System.out.print("\n CREATE \nUSERNAME : ");
        username = input1.next();
        while (!Check.isString(username)) {
            System.out.println("ERROR! your username is all numbers!");
            create();
        }
        while (true) {
            try {
                System.out.print("PASSWORD : ");
                password = input1.next();
                if (password.length() >= 8) {
                    break;
                } else {
                    throw new PasswordException("Password is not enough... your password is too short...");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        while (true) {
            try {
                System.out.print("Verify password : ");
                confirm = input2.next();
                if (confirm == null ? password == null : confirm.equals(password)) {
                    break;
                } else {
                    throw new PasswordException("Password is not the same.");
                }
            } catch (PasswordException ex) {
                System.out.println(ex);
            }
        }
        if (a.isEmpty()) {
            a.add(new Accounts(1, username, password));
        }
        else {
            a.add(new Accounts(a.get(a.size()-1).getAcc_id()+1, username, password));
            account_id = a.get(a.size()-1).getAcc_id();
        }
    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts.txt"))) {
            String str;
            writer.flush();
            for (Accounts a1 : a) {
                str = a1.getAcc_id() + "\t\t" + a1.getUsername() + "\t\t" + a1.getPassword();
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("No file has found!!!");
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
