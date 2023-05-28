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

package ducle.videoStore.user.customer;

import ducle.videoStore.item.Item;
import ducle.videoStore.user.User;
import ducle.videoStore.StoreRepository;

import java.util.*;

public class Customer extends User {
    protected Map<String, Item> rentalMap; // as customer has a map to keep track of their rental inventory

    // treat as constants/enums
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

    public Customer(String id, String name, String address, String phone, String type, String username, String password, Map<String, Item> rentalMap) {
        super(id, name, address, phone, type, username, password);
        this.rentalMap = rentalMap;
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

    /**
     * ["Guest", "Regular", "VIP"]
     * */
    public static List<String> getCustomerTypeList() {
        return customerTypeList;
    }

    public boolean validId(String str){
        return str.matches("C\\d{3}");
    }

    /**
     * Shorthand function call to StoreRepository instance for searching items
     * @param itemId item id for searching
     * */
    protected Item searchItem(String itemId){
        return StoreRepository.Instance().getItemManager().searchItem(itemId);
    }

    /**
     * This function handles the logic of renting the item with the given id.
     * Returns a string indicating the result of the operation
     * @param itemId id of the item to be rented
     * */
    public String rent(String itemId){
        String result;
        Item item = searchItem(itemId);

        if(item != null){
            result = rent(item);
        }
        else{
            result = "Could not find any item with id " + itemId;
        }

        return result;
    }

    /**
     * This function handles the logic of renting the Item instance.
     * Returns a string indicating the result of the operation
     * @param item reference of the item to be rented
     * */
    public String rent(Item item){
        String result;

        if(item.getStock() > 0){
            Item toBeRented = rentalMap.get(item.getId());

            if(toBeRented == null){
                toBeRented = item.createCopy();
                toBeRented.setStock(0);
                toBeRented.setFee(0);
                rentalMap.put(toBeRented.getId(), toBeRented);
            }

            toBeRented.setStock(toBeRented.getStock()+1);
            toBeRented.setFee(toBeRented.getFee()+item.getFee());
            item.decreaseStock();

            result = "Rented a copy of item " + item.print();
        }
        else{
            result = "Item " + item.print() + " is out of stock";
        }

        return result;
    }

    /**
     * This function handles the logic of returning a copy the item with the given id.
     * Returns a string indicating the result of the operation
     * @param itemId id of the item to be returned
     * */
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

    /**
     * This function handles the logic of returning a copy the Item instance.
     * Returns a string indicating the result of the operation
     * @param itemRented reference of the item to be returned
     * */
    public String returnItem(Item itemRented){
        Item itemInStore = searchItem(itemRented.getId());

        itemRented.setStock(itemRented.getStock()-1);
        itemRented.setFee(itemRented.getFee() - itemInStore.getFee());
        itemInStore.increaseStock();

        if(itemRented.getStock() == 0){
            rentalMap.remove(itemRented.getId());
        }

        return "Returned a copy of item " + itemRented.print();
    }

    /**
     * This function handles the logic of returning all copies the item with the given id.
     * Returns a string indicating the result of the operation
     * @param itemId id of the item to be returned
     * */
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

    /**
     * This function handles the logic of returning all copies the Item instance.
     * Returns a string indicating the result of the operation
     * @param itemRented reference of the item to be returned
     * */
    public String returnItemMultiple(Item itemRented){
        int count = 0;
        Item itemInStore = searchItem(itemRented.getId());

        while(itemRented.getStock() > 0){
            itemRented.setStock(itemRented.getStock()-1);
            itemInStore.increaseStock();
            count++;
        }

        rentalMap.remove(itemRented.getId());
        return "Returned " + count + (count > 1 ? " copies" : " copy") + " of item " + itemRented.print();
    }

    /**
     * This function handles the logic of returning all items in this customer rental map.
     * Returns a string indicating the result of the operation
     * */
    public String returnAllItem(){
        for(Item itemRented: rentalMap.values()){
            Item itemInStore = searchItem(itemRented.getId());

            while(itemRented.getStock() > 0){
                itemRented.setStock(itemRented.getStock()-1);
                itemInStore.increaseStock();
            }
        }

        rentalMap.clear();
        return "Returned all item";
    }

    /**
     * This function returns a StringProperty of every item id in the rental map, each on a single line
     * */
    public String printRentalList(){
        String result = "";

        for(String itemId : rentalMap.keySet()){
            result += itemId + "\n";
        }

        return result;
    }

    /**
     * This function creates and returns a shallow copy of this customer
     * */
    public Customer createCopy(){
        return new Customer(getId(), getName(), getAddress(), getPhone(), getType(), getUsername(), getPassword(), getRentalMap());
    }

    @Override
    public String toString(){
        String result = getId() + "," + getName() + "," + getAddress() + "," + getPhone() + "," + rentalMap.size() + "," + getType() + ", " + getUsername() + ", " + getPassword();

        for(Item item : rentalMap.values()){
            result += "\n" + item.getId() + "," + item.getStock() + "," + item.getFee();
        }

        return result;
    }
}
