package ducle.user.customer;

import ducle.item.Item;
import ducle.user.User;

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

    public boolean rent(Item item){
        boolean result = true;

        if(item.getStock() > 0){
            result = false;
        }

        return result;
    }
}
