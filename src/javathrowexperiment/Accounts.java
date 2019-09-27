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

/**
 *
 * @author morrejo_sd2023
 */
public class Accounts {

    Scanner input = new Scanner(System.in);
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    private String username;
    private String password;
    private String confirm;
    static int account_id = 1;
    private int acc_id;
    ArrayList<Accounts> a;

    Accounts() throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\morrejo_sd2023\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts.txt"))) {
            while (reader.readLine() != null) {
                account_id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        a = new ArrayList();
    }

    public Accounts(int id, String username, String password) {
        this.acc_id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    void addUsername() throws IOException {
        System.out.print("Enter username : ");
        username = input.next();
        while (!Check.isString(username)) {
            System.out.println("Username containing all number.");
            addUsername();
        }
    }

    void addPassword() throws IOException, PasswordException {
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
    }

    @Override
    public String toString() {
        return "Accounts{"+"accountID="+ account_id + ", username=" + username + ", password=" + password + '}';
    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("C:\\Users\\morrejo_sd2023\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts.txt", true))){
            String str = Integer.toString(account_id) + "\t" + username + "\t" + hash(password.toCharArray());
            writer.write(str);
            writer.newLine();
            ++account_id;
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void run() throws IOException, PasswordException {
        System.out.println("--- Step 1 ---");
        addUsername();
        addPassword();
        save();
    }

    

}
