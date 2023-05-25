package ducle.user.customer;

import ducle.item.Item;
import ducle.user.User;
import ducle.videoStore.StoreRepository;

import java.util.*;

public class Customer extends User {
    protected List<String> rentalList;
    protected static List<String> customerTypeList = new ArrayList<>(
            Arrays.asList("Guest", "Regular", "VIP")
    );

    public Customer(){
        super();
        setType("");
        rentalList = new ArrayList<>();
    }

    public Customer(String type){
        super();
        setType(type);
        rentalList = new ArrayList<>();
    }

    public Customer(String id, String name, String address, String phone, String type, String username, String password) {
        super(id, name, address, phone, type, username, password);
        rentalList = new ArrayList<>();
    }

    public Customer(String id, String name, String address, String phone, String type, String username, String password, List<String> rentalList) {
        super(id, name, address, phone, type, username, password);
        this.rentalList = rentalList;
    }

    public Customer(String id, String name, String address, String phone, String type) {
        super(id, name, address, phone, type);
        rentalList = new ArrayList<>();
    }

    public List<String> getRentalList() {
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
            item.decreaseStock();
            rentalList.add(item.getId());
            result = "Rented a copy of " + item.getTitle();
        }
        else{
            result = item.getTitle() + " is out of stock";
        }

        return result;
    }

    public String returnItem(String itemId){
        String result;

        if(rentalList.contains(itemId)){
            result = returnItem(StoreRepository.getItemManager().searchItem(itemId));
        }
        else{
            result = "Could not find any item id " + itemId + " in the rental list";
        }

        return result;
    }

    public String returnItem(Item item){
        item.increaseStock();
        rentalList.remove(item.getId());
        return "Returned item " + item.getId();
    }

    public String returnAllItem(){
        for(String itemId: rentalList){
            Item item = StoreRepository.getItemManager().searchItem(itemId);
            item.increaseStock();
        }

        rentalList.clear();
        return "Returned all item";
    }

    @Override
    public String toString(){
        String result = super.toString();

        for(String itemId : rentalList){
            result += "\n" + itemId;
        }

        return result;
    }
}
