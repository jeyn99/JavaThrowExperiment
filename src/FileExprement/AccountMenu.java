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
 * @author repolloja_sd2023
 */
public class AccountMenu {

    ArrayList<Accounts> a;
    Scanner input = new Scanner(System.in);

    public AccountMenu() {
        a = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("Retrieve : ");
        System.out.println("\n\t\t\tAccounts");
        a = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Desktop\\JavaThrowExperiment\\Accounts.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t\t");
                a.add(new Accounts(Integer.parseInt(partsA[0]), partsA[1], partsA[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public boolean checkIDin(int id) {
        boolean checker = false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getAcc_id() == id) {
                checker = true;
            }
        }
        return checker;
    }

    public void validateName(String name) throws Exception {
        if (name.length() < 1) {
            throw new Exception();
        } else if (name.length() >= 1) {
            System.out.print("");
        }
    }

    public void create() throws IOException, PasswordException {
        String username = null, password = null, confirm;
        boolean flag = true;
        String user;
        while (flag) {
            System.out.print("Username: ");
            user = input.nextLine();
            try {
                validateName(user);
                int a = Integer.valueOf(user);
            } catch (NumberFormatException e) {
                username = (user);
                break;
            } catch (Exception e) {
                System.out.println("Fill up name!");
            }
        }

        flag = true;
        while (flag) {
            System.out.print("Password: ");
            String pass = input.nextLine();
            if (pass.length() < 8) {
                System.out.println("Too Short!\n");
            } else {
                password = (pass);
                while (true) {
                    System.out.print("Confirm Password: ");
                    String pw2 = input.nextLine();
                    if (password.equals(pw2)) {
                        System.out.println("Account Successfully created!");
                        break;
                    } else {
                        System.out.println("Password doesn't match!");
                    }
                }
                flag = false;
            }
        }
        
        if (a.isEmpty()) {
            a.add(new Accounts(1, username, password));
        } else {
            a.add(new Accounts(a.get(a.size() - 1).getAcc_id() + 1, username, password));
            account_id = a.get(a.size() - 1).getAcc_id();
        }
    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Desktop\\JavaThrowExperiment\\Accounts.txt"))) {
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
