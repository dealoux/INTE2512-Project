/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This Singleton class acts as the database hub, read and write into databases (.txt files)
*/

package ducle.videoStore;

import ducle.videoStore.item.*;
import ducle.videoStore.item.Record;
import ducle.videoStore.user.Admin;
import ducle.videoStore.user.customer.*;
import ducle.videoStore.user.UserManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StoreRepository {
    private static ItemManager itemManager;
    private static UserManager userManager;

    public StoreRepository(){
        itemManager = new ItemManager();
        userManager = new UserManager();

        try {
            initItems();
            initUsers();
        } catch (FileNotFoundException e){
            System.out.println("Database files not found");
            e.printStackTrace();
        }
    }

    private static String dataSourcePath(String fileName){
        return "src/main/resources/ducle/videoStore/" + fileName;
    }

    private static String dataOutputPath(String fileName){
        return "src/main/resources/ducle/videoStore/output/" + fileName;
    }

    private void initItems() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dataSourcePath("items.txt")));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if(line.startsWith("I")){
                String[] itemArr = line.split(",");

                switch (itemArr[2]){
                    case "DVD":
                        itemManager.addDvd(new DVD(itemArr[0], itemArr[1], itemArr[3], itemArr[4], itemArr[5], itemArr[6]));
                        break;
                    case "Record":
                        itemManager.addRecord(new Record(itemArr[0], itemArr[1], itemArr[3], itemArr[4], itemArr[5], itemArr[6]));
                        break;
                    case "Game":
                        itemManager.addGame(new Game(itemArr[0], itemArr[1], itemArr[3], itemArr[4], itemArr[5]));
                        break;
                }
            }
        }
    }

    private void initUsers() throws FileNotFoundException{
        initAdmins();
        initCustomers();
    }

    private void initAdmins() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(dataSourcePath("admins.txt")));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if (line.startsWith("A")) {
                String[] adminArr = line.split(",");
                userManager.addAdmin(new Admin(adminArr[0], adminArr[1], adminArr[2], adminArr[3], adminArr[4], adminArr[5]));
            }
        }
    }

    private void initCustomers() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(dataSourcePath("customers.txt")));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if (line.startsWith("C")){
                String[] customerArr = line.split(",");
                Customer customer = new Guest();

                switch (customerArr[5]){
                    case "Guest":
                        customer = new Guest(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        userManager.addGuest((Guest) customer);
                        break;
                    case "Regular":
                        customer = new Regular(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        userManager.addRegular((Regular) customer);
                        break;
                    case "VIP":
                        customer = new VIP(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        userManager.addVip((VIP) customer);
                        break;
                }

                for(int i=0; i < Integer.parseInt(customerArr[4]); i++){
                    String[] itemArr = scanner.nextLine().split(",");

                    Item toBeRented = itemManager.searchItem(itemArr[0]);

                    if(toBeRented != null){
                        switch (itemArr.length){
                            // 1st time input
                            case 1:
                                customer.rent(toBeRented);
                                break;
                            // database
                            case 3:
                                Item rented = toBeRented.createCopy();
                                rented.setStock(itemArr[1]);
                                rented.setFee(itemArr[2]);
                                customer.getRentalMap().put(rented.getId(), rented);
                                break;
                        }
                    }
                }
            }
        }
    }

    public static void saveData(){
        saveItems();
        saveUser();
    }

    public static void saveItems(){
        try{
            File output = new File(dataOutputPath("items.txt"));
            output.createNewFile();
            FileWriter writer = new FileWriter(output.getPath());
            String data = itemManager.toString();
            writer.write(data);
            writer.close();
            System.out.println("Saved items data to " + output.getPath());
        } catch (IOException e){
            System.out.println("An error has occurred when trying save items");
            e.printStackTrace();
        }
    }

    public static void saveUser(){
        // customers
        try{
            File output = new File(dataOutputPath("customers.txt"));
            output.createNewFile();
            FileWriter writer = new FileWriter(output.getPath());
            String data = userManager.printCustomers();
            writer.write(data);
            writer.close();
            System.out.println("Saved customers data to " + output.getPath());
        } catch (IOException e){
            System.out.println("An error has occurred when trying save customers");
            e.printStackTrace();
        }

        // admins
        try{
            File output = new File(dataOutputPath("admins.txt"));
            output.createNewFile();
            FileWriter writer = new FileWriter(output.getPath());
            String data = userManager.printAdmins();
            writer.write(data);
            writer.close();
            System.out.println("Saved admins data to " + output.getPath());
        } catch (IOException e){
            System.out.println("An error has occurred when trying save admins");
            e.printStackTrace();
        }
    }

    public static ItemManager getItemManager() {
        return itemManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }
}
