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
public class Courses_Menu {

    ArrayList<Courses> courses_lists;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public Courses_Menu() {
        courses_lists = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("Schedules : ");
        courses_lists = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Courses.txt"))) {
            String inside;
            while ((inside = reader.readLine()) != null) {
                System.out.println(inside);
                String[] partsA = inside.split("\t\t");
                courses_lists.add(new Courses(Integer.parseInt(partsA[0]), Integer.parseInt(partsA[1]), partsA[2], partsA[3], partsA[4]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file has found!!!");
        }
    }

    public void create() throws IOException {
        String title, unit, schedule;
        System.out.print("SUBJECTS : ");
        title = input1.nextLine();
        System.out.print("UNITS : ");
        unit = input2.nextLine();
        while (Check.isString(unit)) {
            System.out.println("INVALiD...");
            create();
        }
        System.out.print("SCHEDULE : ");
        schedule = input3.nextLine();
        if (courses_lists.isEmpty()) {
            courses_lists.add(new Courses(1, account_id - 1, title, unit, schedule));
        } else {
            courses_lists.add(new Courses(courses_lists.get(courses_lists.size() - 1).getId() + 1, account_id, title, unit, schedule));
        }
    }

   public void update(int acc_id) throws IOException {
        String title = null, unit = null, schedule = null;
        for (int i = 0; i < courses_lists.size(); i++) {
            if (courses_lists.get(i).getAccount_id() == acc_id) {
                System.out.println("Schedules :");
                System.out.println(courses_lists.get(i).getId() + "\t" + courses_lists.get(i).getAccount_id() + "\t" + courses_lists.get(i).getSubjectName() + "\t" + courses_lists.get(i).getUnit() + "\t" + courses_lists.get(i).getSchedule());
                System.out.print("\nNEW SUBJECT : ");
                title = input1.nextLine();
                System.out.print("NEW UNITS : ");
                unit = input2.nextLine();
                while (Check.isString(unit)) {
                    System.out.println("INVALID");
                    update(acc_id);
                }
                System.out.print("NEW SCHEDULE : ");
                schedule = input3.nextLine();
                courses_lists.get(i).setSubjectName(title);
                courses_lists.get(i).setUnit(unit);
                courses_lists.get(i).setSchedule(schedule);
            } else if (courses_lists.get(i).getAccount_id() != acc_id && i == courses_lists.size() - 1 && title == null && unit == null && schedule == null) {
                System.out.print("SUBJECT : ");
                title = input1.nextLine();
                System.out.print("UNITS : ");
                unit = input2.nextLine();
                while (Check.isString(unit)) {
                    System.out.println("INVALID");
                    update(acc_id);
                }
                System.out.print("SCHEDULE : ");
                schedule = input3.nextLine();
                if (courses_lists.isEmpty()) {
                    courses_lists.add(new Courses(1, acc_id, title, unit, schedule));
                } else {
                    courses_lists.add(new Courses(courses_lists.get(courses_lists.size() - 1).getId() + 1, acc_id, title, unit, schedule));
                }
                break;
            }
        }

    }
    
    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\NetBeansProjects\\JavaThrowExperiment\\Courses.txt"))) {
            String str;
            writer.flush();
            for (Courses c1 : courses_lists) {
                str = c1.getId() + "\t\t" + c1.getAccount_id() + "\t\t" + c1.getSubjectName() + "\t\t" + c1.getUnit() + "\t\t" + c1.getSchedule();
                writer.write(str);
                writer.newLine();
            }
        } catch (Exception ex) {
            System.out.println("No file has found!!!");
        }
    }

    public void delete(int acc_id) {
        for (int i = 0; i < courses_lists.size(); i++) {
            if (courses_lists.get(i).getAccount_id() == acc_id) {
                courses_lists.remove(i);
                System.out.println("Account ID " + acc_id + " is deleted!");
            }
        }
        for (int i = 0; i < courses_lists.size(); i++) {
            if (courses_lists.get(i).getId() != i) {
                courses_lists.get(i).setId(i + 1);
            }
            if (i == courses_lists.size() - 1) {
                break;
            }
        }
    }

    public void search(int acc_id) {
        courses_lists.stream().filter((c1) -> (c1.getAccount_id() == acc_id)).forEach((c1) -> {
            System.out.println(c1.getId() + "\t" + c1.getAccount_id() + "\t" + c1.getSubjectName() + "\t" + c1.getUnit() + "\t" + c1.getSchedule());
        });
    }
}
