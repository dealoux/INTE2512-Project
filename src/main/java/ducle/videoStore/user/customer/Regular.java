/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the blueprint for all regular customers
*/

package ducle.videoStore.user.customer;

import ducle.videoStore.StoreRepository;
import ducle.videoStore.item.Item;
import java.util.Map;

public class Regular extends Customer {
    private static final int UNTIL_PROMOTION = 5;
    public Regular(){
        super("Regular");
    }

    public Regular(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "Regular", username, password);
    }

    public Regular(String id, String name, String address, String phone, String username, String password, Map<String, Item> rentalList, RentalStats stats) {
        super(id, name, address, phone, "Regular", username, password, rentalList, stats);
    }

    public Regular(String id, String name, String address, String phone) {
        super(id, name, address, phone, "Regular");
    }

    @Override
    public Regular createCopy(){
        return new Regular(getId(), getName(), getAddress(), getPhone(), getUsername(), getPassword(), getRentalMap(), getStats());
    }

    private String promotionCheck(){
        String result = ". Rent and return " + (UNTIL_PROMOTION - stats.getReturnCount()) + " more items to be promoted to VIP.";
        if(stats.getReturnCount() > UNTIL_PROMOTION){
            StoreRepository.Instance().getCustomerManager().add(new VIP(getId(), getName(), getAddress(), getPhone(), getUsername(), getPassword(), getRentalMap(), getStats()));
            result = ". Congrats you have been promoted to VIP, please enjoy our rewards program!";
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
}
