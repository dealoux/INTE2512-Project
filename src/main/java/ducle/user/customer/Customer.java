package ducle.user.customer;

import ducle.item.Item;
import ducle.user.User;
import ducle.videoStore.StoreRepository;

import java.util.HashMap;

public abstract class Customer extends User {
    protected HashMap<String, Item> rentalList;
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

    public HashMap<String, Item> getRentalList() {
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
}
