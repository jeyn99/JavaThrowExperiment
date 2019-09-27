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
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author morrejo_sd2023
 */
public class PersonalInformation {

    Scanner input = new Scanner(System.in);
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    private String fname;
    private String lname;
    private String age;
    private int id = 1;
    private int account_id = Accounts.account_id - 1;
    ArrayList<PersonalInformation> pi;

    PersonalInformation() throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\morrejo_sd2023\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt"))) {
            while (reader.readLine() != null) {
                id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        pi = new ArrayList();
    }
    
    public PersonalInformation(int id, int ac, String fn, String ln, String age) {
        this.account_id = ac;
        this.id = id;
        this.fname = fn;
        this.lname = ln;
        this.age = age;
        
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    void addFName() throws IOException {

        System.out.print("Enter first name : ");
        fname = input.nextLine();
        while (!Check.isString(fname)) {
            System.out.println("First name does not contain any number.");
            addFName();
        }
    }

    void addLName() throws IOException {
        System.out.print("Enter last name : ");
        lname = input1.nextLine();
        while (!Check.isString(lname)) {
            System.out.println("Last name does not contain any number.");
            addLName();
        }
    }

    void addAge() throws IOException {
        System.out.print("Enter age : ");
        age = input2.next();
        while (Check.isString(age)) {
            System.out.println("Age is not a string.");
            addAge();
        }
    }

    public void save()
            throws IOException {
        
        try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("C:\\Users\\morrejo_sd2023\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt", true))){
            String str = Integer.toString(id) + "\t" + Integer.toString(account_id) + "\t" + fname + "\t" + lname + "\t" + age;
            writer.write(str);
            writer.newLine();
            ++id;
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void run() throws IOException {
        System.out.println("--- Step 2 ---");
        addFName();
        addLName();
        addAge();
        save();
    }
    
    
}
