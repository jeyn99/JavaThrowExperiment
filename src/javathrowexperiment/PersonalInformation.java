/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathrowexperiment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author morrejo_sd2023
 */
public class PersonalInformation {

    Scanner input = new Scanner(System.in);
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    private String firstname;
    private String lastname;
    private String age;
    private int id = 1;
    private int account_id = Accounts.account_id - 1;
    ArrayList<PersonalInformation> personal_informations;

    PersonalInformation() throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\morrejo_sd2023\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Accounts_Personal_Information.txt"))) {
            while (reader.readLine() != null) {
                id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        personal_informations = new ArrayList();
    }
    
    public PersonalInformation(int id, int ac, String fn, String ln, String age) {
        this.account_id = ac;
        this.id = id;
        this.firstname = fn;
        this.lastname = ln;
        this.age = age;
        
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLasName() {
        return lastname;
    }

    public void setLastName(String lname) {
        this.lastname = lname;
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
    
}
