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

package ducle.user.customer;

import ducle.item.Item;
import java.util.Map;
import ducle.videoStore.StoreRepository;

public class Guest extends Customer {
    public Guest(){
        super("Guest");
    }

    public Guest(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "Guest", username, password);
    }

    public Guest(String id, String name, String address, String phone, String username, String password, Map<String, Item> rentalList) {
        super(id, name, address, phone, "Guest", username, password, rentalList);
    }

    public Guest(String id, String name, String address, String phone) {
        super(id, name, address, phone, "Guest");
    }

    // limit the rent
    @Override
    public String rent(String itemId) {
        String result;
        Item item = StoreRepository.getItemManager().searchItem(itemId);

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

        if(item.getLoanType().equals("2-day")){
            result = ("A guest customer can not borrow 2-day items, please choose a one-week item, thank you!");
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
}
