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
public class CourseMenu {

    ArrayList<Courses> c;
    Scanner input1 = new Scanner(System.in);
    Scanner input2 = new Scanner(System.in);
    Scanner input3 = new Scanner(System.in);

    public CourseMenu() {
        c = new ArrayList();
    }

    public void retrieve() throws IOException {
        System.out.println("\n\t\t\tSchedules");
        c = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\2ndyrGroupC\\Documents\\JaneRepollo\\jyn\\JavaThrowExperiment\\Courses.txt"))) {
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
        System.out.print("Title: ");
        title = input1.nextLine();
        System.out.print("Units : ");
        unit = input2.nextLine();
        while (Check.isString(unit)) {
            System.out.println("Invalid Format!");
            create();
        }
        System.out.print("Schedule : ");
        schedule = input3.nextLine();
        if (c.isEmpty()) {
            c.add(new Courses(1, account_id, title, unit, schedule));
        }
    }
    
    public void create(int id) throws IOException {
        String title, unit, schedule;
        System.out.print("Title: ");
        title = input1.nextLine();
        System.out.print("Units : ");
        unit = input2.nextLine();
        while (Check.isString(unit)) {
            System.out.println("Invalid Format!");
            create();
        }
        System.out.print("Schedule : ");
        schedule = input3.nextLine();
        if (c.isEmpty()) {
            c.add(new Courses(1, id, title, unit, schedule));
        } else {
            c.add(new Courses(c.get(c.size() - 1).getId() + 1, id, title, unit, schedule));
        }
    }
    
    
    
    

    public void update(int acc_id) throws IOException {
        String title = null, unit = null, schedule = null;
        boolean in = false;
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getAccount_id() == acc_id) {
                System.out.println("\t\t\tSchedules");
                System.out.println(c.get(i).getId() + "\t" + c.get(i).getAccount_id() + "\t" + c.get(i).getSubjectName() + "\t" + c.get(i).getUnit() + "\t" + c.get(i).getSchedule());
                System.out.print("\nTitle : ");
                title = input1.nextLine();
                System.out.print("Units : ");
                unit = input2.nextLine();
                while (Check.isString(unit)) {
                    System.out.println("Invalid Format!");
                    update(acc_id);
                }
                System.out.print("Schedule : ");
                schedule = input3.nextLine();
                c.get(i).setSubjectName(title);
                c.get(i).setUnit(unit);
                c.get(i).setSchedule(schedule);
                in = true;
            }
        }
        
        if(!in) {
            create(acc_id);
        }
    }

    public void save() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("C:\\Users\\2ndyrGroupC\\Documents\\JaneRepollo\\jyn\\JavaThrowExperiment\\Courses.txt"))) {
            String str;
            writer.flush();
            for (Courses c1 : c) {
                str = c1.getId() + "\t\t" + c1.getAccount_id() + "\t\t" + c1.getSubjectName() + "\t\t" + c1.getUnit() + "\t\t" + c1.getSchedule();
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
                System.out.println("Account ID " + acc_id + " in schedule deleted succesfully!");
            }
        }
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getId() != i) {
                c.get(i).setId(i + 1);
            }
            if (i == c.size() - 1) {
                break;
            }
        }
    }

    public void search(int acc_id) {
        c.stream().filter((c1) -> (c1.getAccount_id() == acc_id)).forEach((c1) -> {
            System.out.println(c1.getId() + "\t" + c1.getAccount_id() + "\t" + c1.getSubjectName() + "\t" + c1.getUnit() + "\t" + c1.getSchedule());
        });
    }
}
