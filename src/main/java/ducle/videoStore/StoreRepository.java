package ducle.videoStore;

import ducle.item.DVD;
import ducle.item.Game;
import ducle.item.Record;
import ducle.user.customer.*;
import ducle.item.ItemManager;

import java.util.Scanner;

public class StoreRepository {
    private static ItemManager itemManager;
    private static CustomerManager customerManager;

    public StoreRepository(){
        itemManager = new ItemManager();
        customerManager = new CustomerManager();

        initItems();
        initCustomers();
    }

    private void initItems(){
        Scanner scanner = new Scanner("items.txt");
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

    private void initCustomers(){
        Scanner scanner = new Scanner("customers.txt");
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            if (line.startsWith("C")){
                String[] customerArr = line.split(",");
                Customer customer = new Guest();

                switch (customerArr[5]){
                    case "Guest":
                        customer = new Guest(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        customerManager.addGuest((Guest) customer);
                        break;
                    case "Regular":
                        customer = new Regular(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
                        customerManager.addRegular((Regular) customer);
                        break;
                    case "VIP":
                        customer = new VIP(customerArr[0], customerArr[1], customerArr[2], customerArr[3], customerArr[6], customerArr[7]);
//                        customerManager.addVip((VIP) customer);
                        customerManager.addCustomer(customer);
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

    public static CustomerManager getCustomerManager() {
        return customerManager;
    }
}
