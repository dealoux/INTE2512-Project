/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the blueprint for all guest customers
*/

package ducle.videoStore.user.customer;

import ducle.videoStore.StoreRepository;
import ducle.videoStore.item.Item;
import java.util.Map;

public class Guest extends Customer {
    public Guest(){
        super("Guest");
    }
    private static final int UNTIL_PROMOTION = 3;

    public Guest(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "Guest", username, password);
    }

    public Guest(String id, String name, String address, String phone, String username, String password, Map<String, Item> rentalList, RentalStats stats) {
        super(id, name, address, phone, "Guest", username, password, rentalList, stats);
    }

    public Guest(String id, String name, String address, String phone) {
        super(id, name, address, phone, "Guest");
    }

    // limit the rent
    @Override
    public String rent(String itemId) {
        String result;
        Item item = searchItem(itemId);

        if(item != null){
            result = rent(item);
        }
        else{
            result = "Could not find any item with id " + itemId;
        }

        return  result;
    }

    @Override
    public String rent(Item item) {
        String result;

        if(item.getLoanType().equals(Item.getLoanTypeList().get(0))){
            result = ("A guest customer can not borrow " + Item.getLoanTypeList().get(0) + " items, please choose a " + Item.getLoanTypeList().get(1) + " item, thank you!");
        }
        else{
            if(rentalMap.size() < 2){
                result = super.rent(item);
            }
            else{
                result = ("You have reached your rental limit!");
            }
        }

        return  result;
    }

    private String promotionCheck(){
        String result = ". Rent and return " + (UNTIL_PROMOTION - stats.getReturnCount()) + " more items to be promoted to Regular.";
        if(stats.getReturnCount() >= UNTIL_PROMOTION){
            StoreRepository.Instance().getCustomerManager().add(new Regular(getId(), getName(), getAddress(), getPhone(), getUsername(), getPassword(), getRentalMap(), getStats()));
            result = ". Congrats you have been promoted to Regular!";
        }
        return result;
    }

    @Override
    public String returnItem(Item item) {
        String result = super.returnItem(item);
        return result + promotionCheck();
    }

    @Override
    public String returnItemMultiple(Item itemRented){
        String result = super.returnItemMultiple(itemRented);
        return result + promotionCheck();
    }

    @Override
    public String returnAllItem(){
        String result = super.returnAllItem();
        return result + promotionCheck();
    }

    @Override
    public Guest createCopy(){
        return new Guest(getId(), getName(), getAddress(), getPhone(), getUsername(), getPassword(), getRentalMap(), getStats());
    }
}
