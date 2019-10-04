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
import java.util.Scanner;

/**
 *
 * @author repolloja_sd2023
 */
public class Courses {

    Scanner input = new Scanner(System.in);
    private String subjectName;
    private String unit;
    private String schedule;
    private int id = 1;
    private int account_id = Accounts.account_id - 1;

    Courses() throws FileNotFoundException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\repolloja_sd2023\\Desktop\\JavaThrowExperiment\\Courses.txt"))) {
            while (reader.readLine() != null) {
                id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
    
    public Courses(int id, int acc, String title, String unit, String sc) {
        this.id = id;
        this.account_id = acc;
        this.subjectName = title;
        this.schedule = sc;
        this.unit = unit;
    }
    

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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
