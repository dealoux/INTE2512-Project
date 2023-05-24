package ducle.user.customer;

import ducle.item.Item;
import ducle.user.User;
import ducle.videoStore.StoreRepository;

import java.util.HashMap;
import java.util.Map;

public abstract class Customer extends User {
    protected Map<String, Item> rentalList;
    public Customer(String type){
        super();
        this.type = type;
        rentalList = new HashMap<>();
    }

    public Customer(String id, String name, String address, String phone, String type, String username, String password) {
        super(id, name, address, phone, type, username, password);
        rentalList = new HashMap<>();
    }

    public Customer(String id, String name, String address, String phone, String type) {
        super(id, name, address, phone, type);
        rentalList = new HashMap<>();
    }

    public Map<String, Item> getRentalList() {
        return rentalList;
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
            item.setStock(item.getStock()-1);
            rentalList.put(item.getId(), item);
            result = "Rented a copy of " + item.getTitle();
        }
        else{
            result = item.getTitle() + " is out of stock";
        }

        return result;
    }

    public String toString(){
        String result = super.toString();
        result += "\nRental items";

        for(Item item : rentalList.values()){
            result += item.toString();
        }

        return result;
    }
}
