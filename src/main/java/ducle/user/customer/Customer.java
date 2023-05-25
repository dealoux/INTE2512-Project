/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the base blueprint for all customers
*/

package ducle.user.customer;

import ducle.item.Item;
import ducle.user.User;
import ducle.videoStore.StoreRepository;

import java.util.*;

public class Customer extends User {
    protected Map<String, Item> rentalMap;
    protected static List<String> customerTypeList = new ArrayList<>(
            Arrays.asList("Guest", "Regular", "VIP")
    );

    public Customer(){
        super();
        setType("");
        rentalMap = new HashMap<>();
    }

    public Customer(String type){
        super();
        setType(type);
        rentalMap = new HashMap<>();
    }

    public Customer(String id, String name, String address, String phone, String type, String username, String password) {
        super(id, name, address, phone, type, username, password);
        rentalMap = new HashMap<>();
    }

    public Customer(String id, String name, String address, String phone, String type, String username, String password, Map<String, Item> rentalList) {
        super(id, name, address, phone, type, username, password);
        this.rentalMap = rentalList;
    }

    public Customer(String id, String name, String address, String phone, String type) {
        super(id, name, address, phone, type);
        rentalMap = new HashMap<>();
    }

    public Map<String, Item> getRentalMap() {
        return rentalMap;
    }

    public List<Item> getRentalList() {
        return new ArrayList<>(rentalMap.values());
    }

    public static List<String> getCustomerTypeList() {
        return customerTypeList;
    }

    public String rent(String itemId){
        String result;
        Item item = StoreRepository.getItemManager().searchItem(itemId);

        if(item != null){
            result = rent(item);
        }
        else{
            result = "Could not find any item with id " + itemId;
        }

        return result;
    }

    public String rent(Item item){
        String result;

        if(item.getStock() > 0){
            Item toBeRented = rentalMap.get(item.getId());

            if(toBeRented == null){
                toBeRented = item.createCopy();
                toBeRented.setStock(0);
                rentalMap.put(toBeRented.getId(), toBeRented);
            }

            toBeRented.setStock(toBeRented.getStock()+1);
            item.decreaseStock();

            result = "Rented a copy of item " + item.print();
        }
        else{
            result = "Item " + item.print() + " is out of stock";
        }

        return result;
    }

    public String returnItem(String itemId){
        String result;
        Item item = rentalMap.get(itemId);

        if(item != null){
            result = returnItem(item);
        }
        else{
            result = "Could not find any item with id " + itemId + " in the rental list";
        }

        return result;
    }

    public String returnItem(Item itemRented){
        Item itemInStore = StoreRepository.getItemManager().searchItem(itemRented.getId());

        itemRented.setStock(itemRented.getStock()-1);
        itemInStore.increaseStock();

        if(itemRented.getStock() == 0){
            rentalMap.remove(itemRented.getId());
        }

        return "Returned a copy of item " + itemRented.print();
    }

    public String returnItemMultiple(String itemId){
        String result;
        Item item = rentalMap.get(itemId);

        if(item != null){
            result = returnItemMultiple(item);
        }
        else{
            result = "Could not find any item id " + itemId + " in the rental list";
        }

        return result;
    }

    public String returnItemMultiple(Item itemRented){
        int count = 0;
        Item itemInStore = StoreRepository.getItemManager().searchItem(itemRented.getId());

        while(itemRented.getStock() > 0){
            itemRented.setStock(itemRented.getStock()-1);
            itemInStore.increaseStock();
            count++;
        }

        rentalMap.remove(itemRented.getId());
        return "Returned " + count + (count > 1 ? " copies" : " copy") + " of item " + itemRented.print();
    }

    public String returnAllItem(){
        for(Item itemRented: rentalMap.values()){
            Item itemInStore = StoreRepository.getItemManager().searchItem(itemRented.getId());

            while(itemRented.getStock() > 0){
                itemRented.setStock(itemRented.getStock()-1);
                itemInStore.increaseStock();
            }
        }

        rentalMap.clear();
        return "Returned all item";
    }

    @Override
    public String toString(){
        String result = super.toString();

        for(String itemId : rentalMap.keySet()){
            result += "\n" + itemId;
        }

        return result;
    }
}
