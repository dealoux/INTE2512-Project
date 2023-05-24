package ducle.videoStore;

import ducle.item.DVD;
import ducle.item.Game;
import ducle.item.Record;
import ducle.user.Admin;
import ducle.item.ItemManager;
import ducle.user.customer.*;
import ducle.user.UserManager;

import java.io.File;
import java.io.FileNotFoundException;
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
        }

//        System.out.println(itemManager.toString());
//        System.out.println(userManager.toString());
    }

    private String dataSourcePath(String fileName){
        return "src/main/resources/ducle/videoStore/" + fileName;
    }

    private void initItems() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dataSourcePath("items.txt")));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if(line.startsWith("I")){
                String[] itemArr = line.split(",");

                switch (itemArr[2]){
                    case "DVD":
                        itemManager.addDvd(new DVD(itemArr[0], itemArr[1], itemArr[3], Integer.parseInt(itemArr[4]), itemArr[5], itemArr[6]));
                        break;
                    case "Record":
                        itemManager.addRecord(new Record(itemArr[0], itemArr[1], itemArr[3], Integer.parseInt(itemArr[4]), itemArr[5], itemArr[6]));
                        break;
                    case "Game":
                        itemManager.addGame(new Game(itemArr[0], itemArr[1], itemArr[3], Integer.parseInt(itemArr[4]), itemArr[5]));
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
                    customer.rent(scanner.nextLine());
                }
            }
        }
    }

    public static ItemManager getItemManager() {
        return itemManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }
}
