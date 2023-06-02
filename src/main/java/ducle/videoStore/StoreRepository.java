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
import ducle.videoStore.managers.CustomerManager;
import ducle.videoStore.managers.ItemManager;
import ducle.videoStore.user.Admin;
import ducle.videoStore.user.User;
import ducle.videoStore.user.customer.*;
import ducle.videoStore.managers.UserManager;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class StoreRepository {
    private static StoreRepository instance;
    private ItemManager itemManager;
    private CustomerManager customerManager;
    private UserManager<Admin> adminManager;
    private Properties configProps;

    private StoreRepository(){
        itemManager = new ItemManager();
        customerManager = new CustomerManager();
        adminManager = new UserManager<>();

        try {
            configProps = new Properties();
            configProps.load(new FileInputStream("config.properties"));
            initItems();
            initAdmins();
            initCustomers();
        } catch (Exception e){
            System.out.println("Database files not found");
            e.printStackTrace();
        }
    }

    /**
     * This function returns the static instance of StoreRepository Singleton class
     * */
    public static StoreRepository Instance(){
        if(instance == null){
            instance = new StoreRepository();
        }

        return instance;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public UserManager<Admin> getAdminManager() {
        return adminManager;
    }


    /**
     * This function reads and loads the items input/database file
     * */
    private void initItems() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(configProps.getProperty("INPUT_PATH")+configProps.getProperty("ITEM_DATA")));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if(line.startsWith("I")){
                String[] itemArr = line.split(",");

                switch (itemArr[2]){
                    case "DVD":
                        itemManager.add(new DVD(itemArr[0], itemArr[1], itemArr[3], itemArr[4], itemArr[5], itemArr[6]));
                        break;
                    case "Record":
                        itemManager.add(new Record(itemArr[0], itemArr[1], itemArr[3], itemArr[4], itemArr[5], itemArr[6]));
                        break;
                    case "Game":
                        itemManager.add(new Game(itemArr[0], itemArr[1], itemArr[3], itemArr[4], itemArr[5]));
                        break;
                }
            }
        }
    }

    /**
     * This function reads and loads the admin input/database file
     * */
    private void initAdmins() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(configProps.getProperty("INPUT_PATH")+configProps.getProperty("ADMIN_DATA")));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if (line.startsWith("A")) {
                String[] adminArr = line.split(",");
                adminManager.add(new Admin(adminArr[0], adminArr[1], adminArr[2], adminArr[3], adminArr[4], adminArr[5]));
            }
        }
    }

    /**
     * This function reads and loads the customers input/database file
     * */
    private void initCustomers() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(configProps.getProperty("INPUT_PATH")+configProps.getProperty("CUSTOMER_DATA")));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if (line.startsWith("C")){
                String[] customerArr = line.split(",");
                Customer customer = new Guest();

                switch (customerArr[5]){
                    case "Guest":
                        customer = new Guest(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        break;
                    case "Regular":
                        customer = new Regular(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        break;
                    case "VIP":
                        customer = new VIP(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        break;
                }

                customerManager.add(customer);

                for(int i=0; i < Integer.parseInt(customerArr[4]); i++){
                    String[] itemArr = scanner.nextLine().split(",");

                    Item toBeRented = itemManager.search(itemArr[0]);

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

    public User searchUserByUsername(String username){
        User result = customerManager.searchUserByUsername(username);

        if(result == null){
            result = adminManager.searchUserByUsername(username);
        }

        return result;
    }

    /**
     * This function writes the memory stored items and users data to the specified output database files
     * */
    public void saveData(){
        saveItems();
        saveUser();
    }

    /**
     * This function writes the memory stored items data to the specified output database files
     * */
    public void saveItems(){
        try{
            File output = new File(configProps.getProperty("OUTPUT_PATH")+configProps.getProperty("ITEM_DATA"));
            output.createNewFile();
            FileWriter writer = new FileWriter(output.getPath());
            String data = "# an item record has the following format\n" +
                    "#ID,Title,Rent type,Loan type,Number of copies,rental fee,[genre] if it is a video record or a DVD\n";
            data += itemManager.toString();
            writer.write(data);
            writer.close();
            System.out.println("Saved items data to " + output.getPath());
        } catch (IOException e){
            System.out.println("An error has occurred when trying save items");
            e.printStackTrace();
        }
    }

    /**
     * This function writes the memory stored users data to the specified output database files
     * */
    public void saveUser(){
        // customers
        try{
            File output = new File(configProps.getProperty("OUTPUT_PATH")+configProps.getProperty("CUSTOMER_DATA"));
            output.createNewFile();
            FileWriter writer = new FileWriter(output.getPath());
            String data = "# a customer record has the following format\n" +
                    "#ID,Name,Address,Phone,Number of rentals,customer type, username, password followed by a list of items with the following format\n" +
                    "#ID,Quantity,Total Fee\n";
            data += customerManager.toString();
            writer.write(data);
            writer.close();
            System.out.println("Saved customers data to " + output.getPath());
        } catch (IOException e){
            System.out.println("An error has occurred when trying save customers");
            e.printStackTrace();
        }

        // admins
        try{
            File output = new File(configProps.getProperty("OUTPUT_PATH")+configProps.getProperty("ADMIN_DATA"));
            output.createNewFile();
            FileWriter writer = new FileWriter(output.getPath());
            String data = "# an admin has the following format\n" +
                    "# ID,Name,Address,Phone, username, password\n" +
                    "# ID starts with A\n" +
                    "# admin accounts can not be added when the program is running so feel free to add more here\n";
            data += adminManager.toString();
            writer.write(data);
            writer.close();
            System.out.println("Saved admins data to " + output.getPath());
        } catch (IOException e){
            System.out.println("An error has occurred when trying save admins");
            e.printStackTrace();
        }
    }
}
