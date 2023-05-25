package ducle.user.customer;

import ducle.item.Item;
import ducle.user.User;
import ducle.videoStore.StoreRepository;

import java.util.*;

public class Customer extends User {
    protected Map<String, Item> rentalList;
    protected static List<String> customerTypeList = new ArrayList<>(
            Arrays.asList("Guest", "Regular", "VIP")
    );

    public Customer(){
        super();
        setType("");
        rentalList = new HashMap<>();
    }

    public Customer(String type){
        super();
        setType(type);
        rentalList = new HashMap<>();
    }

    public Customer(String id, String name, String address, String phone, String type, String username, String password) {
        super(id, name, address, phone, type, username, password);
        rentalList = new HashMap<>();
    }

    public Customer(String id, String name, String address, String phone, String type, String username, String password, Map<String, Item> rentalList) {
        super(id, name, address, phone, type, username, password);
        this.rentalList = rentalList;
    }

    public Customer(String id, String name, String address, String phone, String type) {
        super(id, name, address, phone, type);
        rentalList = new HashMap<>();
    }

    public Map<String, Item> getRentalList() {
        return rentalList;
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
            Item toBeRented = rentalList.get(item.getId());

            if(toBeRented == null){
                toBeRented = item.createCopy();
                toBeRented.setStock(0);
                rentalList.put(toBeRented.getId(), toBeRented);
            }

            toBeRented.setStock(toBeRented.getStock()+1);
            item.decreaseStock();

            result = "Rented a copy of " + item.getTitle();
        }
        else{
            result = "Item " + item.getId() + " is out of stock";
        }

        return result;
    }

    public String returnItem(String itemId){
        String result;
        Item item = rentalList.get(itemId);

        if(item != null){
            result = returnItem(item);
        }
        else{
            result = "Could not find any item id " + itemId + " in the rental list";
        }

        return result;
    }

    public String returnItem(Item itemRented){
        int count = 0;
        Item itemInStore = StoreRepository.getItemManager().searchItem(itemRented.getId());

        while(itemRented.getStock() > 0){
            itemRented.setStock(itemRented.getStock()-1);
            itemInStore.increaseStock();
            count++;
        }

        rentalList.remove(itemRented.getId());
        return "Returned " + count + (count > 1 ? "copies" : "copy") + " of item " + itemRented.getId();
    }

    public String returnAllItem(){
        for(Item itemRented: rentalList.values()){
            Item itemInStore = StoreRepository.getItemManager().searchItem(itemRented.getId());

            while(itemRented.getStock() > 0){
                itemRented.setStock(itemRented.getStock()-1);
                itemInStore.increaseStock();
            }
        }

        rentalList.clear();
        return "Returned all item";
    }

    @Override
    public String toString(){
        String result = super.toString();

        for(String itemId : rentalList.keySet()){
            result += "\n" + itemId;
        }

        return result;
    }
}
