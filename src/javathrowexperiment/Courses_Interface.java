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
public class Courses_Interface {

    ArrayList<Courses> c;
    private int id = 1;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public Courses_Interface() {
        c = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("\n\t *** Courses ***");
        c = new ArrayList();
        id =0;
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Courses.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t");
                c.add(new Courses(Integer.parseInt(partsA[0]), Integer.parseInt(partsA[1]), partsA[2], partsA[3], partsA[4]));
                id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void create() throws IOException, PasswordException {
        String title, unit, schedule;
        System.out.print("Enter title/subject : ");
        title = input1.nextLine();
        System.out.print("Enter units : ");
        unit = input2.nextLine();
        while (Check.isString(unit)) {
            System.out.println("Units is not s string.");
            create();
        }
        System.out.print("Enter schedule : ");
        schedule = input3.nextLine();
        c.add(new Courses(id, account_id-1, title, unit, schedule));
        ++id;
    }

    public void update(int acc_id) {
        String title, unit, schedule;
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getAccount_id() == acc_id) {
                System.out.print("Enter new title/subject : ");
                title = input1.nextLine();
                System.out.print("Enter new units : ");
                unit = input2.nextLine();
                while (Check.isString(unit)) {
                    System.out.println("Units is not s string.");
                    update(acc_id);
                }
                System.out.print("Enter new schedule : ");
                schedule = input3.nextLine();
                c.get(i).setTitle(title);
                c.get(i).setUnit(unit);
                c.get(i).setSchedule(schedule);
            }
        }

    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Courses.txt"))) {
            String str;
            writer.flush();
            for (int i = 0; i < c.size(); i++) {
                str = c.get(i).getId() + "\t" + c.get(i).getAccount_id() + "\t" + c.get(i).getTitle() + "\t" + c.get(i).getUnit() + "\t" + c.get(i).getSchedule();
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void delete(int acc_id) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getAccount_id() == acc_id) {
                c.remove(i);
            }
        }
    }

    public void search(int acc_id) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getAccount_id() == acc_id) {
                System.out.println(c.get(i).getId() + "\t" + c.get(i).getAccount_id() + "\t" + c.get(i).getTitle() + "\t" + c.get(i).getUnit() + "\t" + c.get(i).getSchedule());
            }
        }
    }
}
