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
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public Courses_Interface() {
        c = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("\n\t\t\t *** Schedules ***");
        c = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Courses.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t\t");
                c.add(new Courses(Integer.parseInt(partsA[0]), Integer.parseInt(partsA[1]), partsA[2], partsA[3], partsA[4]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void create() throws IOException {
        String title, unit, schedule;
        System.out.print("Enter title/subject : ");
        title = input1.nextLine();
        System.out.print("Enter units : ");
        unit = input2.nextLine();
        while (Check.isString(unit)) {
            System.out.println("Units is not a string.");
            create();
        }
        System.out.print("Enter schedule : ");
        schedule = input3.nextLine();
        if (c.isEmpty()) {
            c.add(new Courses(1, account_id - 1, title, unit, schedule));
        } else {
            c.add(new Courses(c.get(c.size() - 1).getId() + 1, account_id, title, unit, schedule));
        }
    }

    public void update(int acc_id) throws IOException {
        String title = null, unit = null, schedule = null;
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getAccount_id() == acc_id) {
                System.out.println("\t\t\t*** Schedules ***");
                System.out.println(c.get(i).getId() + "\t" + c.get(i).getAccount_id() + "\t" + c.get(i).getTitle() + "\t" + c.get(i).getUnit() + "\t" + c.get(i).getSchedule());
                System.out.print("\nEnter new title/subject : ");
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
            } else if (c.get(i).getAccount_id() != acc_id && i == c.size() - 1 && title == null && unit == null && schedule == null) {
                create();
                c.get(i).setAccount_id(acc_id);
                break;
            }
        }

    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Courses.txt"))) {
            String str;
            writer.flush();
            for (Courses c1 : c) {
                str = c1.getId() + "\t\t" + c1.getAccount_id() + "\t\t" + c1.getTitle() + "\t\t" + c1.getUnit() + "\t\t" + c1.getSchedule();
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
    }

    public void delete(int acc_id) {
        boolean con = false;
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getAccount_id() == acc_id && con == false) {
                c.remove(i);
                System.out.println("Account ID " + acc_id + " in Schedules has been deleted!");
                con = true;
            } if (con == true && i < c.size()-1) {
                c.get(i).setId(c.get(i).getId()-1);
            }if (i == c.size()-2 && con == true) {
                break;
            }
        }
    }

    public void search(int acc_id) {
        c.stream().filter((c1) -> (c1.getAccount_id() == acc_id)).forEach((c1) -> {
            System.out.println(c1.getId() + "\t" + c1.getAccount_id() + "\t" + c1.getTitle() + "\t" + c1.getUnit() + "\t" + c1.getSchedule());
        });
    }
}
