/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the blueprint for all VIP customers
*/

package ducle.videoStore.user.customer;

import ducle.videoStore.item.Item;
import ducle.videoStore.scenes.SceneUtilities;
import javafx.scene.control.ButtonType;

import java.util.Map;
import java.util.Optional;

public class VIP extends Customer {

    public VIP() {
        super("VIP");
    }

    public VIP(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "VIP", username, password);
    }

    public VIP(String id, String name, String address, String phone, String username, String password, Map<String, Item> rentalList, RentalStats stats) {
        super(id, name, address, phone, "VIP", username, password, rentalList, stats);
    }


    public VIP(String id, String name, String address, String phone) {
        super(id, name, address, phone, "VIP");
    }

    @Override
    public String rent(Item item) {
        String result = super.rent(item);

        if(result.startsWith("Rented")){
            if(stats.rewardsAvailable()){
                Optional<ButtonType> buttonHandler = SceneUtilities.confirmationDialog(
                        "Confirm rent",
                        "Would you like exchange 100 points to rent this item free of charge?",
                        "Current points: " + stats.getRewardPoints());

                if(buttonHandler.get() == ButtonType.OK){
                    Item rented = rentalMap.get(item.getId());
                    rented.setFee(rented.getFee()-item.getFee());
                    stats.decreaseRewards();
                    result += ", for free using 100 points";
                }
            }
        }

        return result;
    }

    @Override
    public String returnItem(Item item) {
        String result = super.returnItem(item);
        stats.increaseRewards();
        return result + ", total reward points: " + stats.getRewardPoints();
    }

    @Override
    public String returnItemMultiple(Item itemRented){
        int count = 0;
        Item itemInStore = searchItem(itemRented.getId());

        while(itemRented.getStock() > 0){
            itemRented.setStock(itemRented.getStock()-1);
            itemInStore.increaseStock();
            count++;
            stats.increaseRewards();
        }

        stats.increaseReturnCount(count);
        rentalMap.remove(itemRented.getId());
        return "Returned " + count + (count > 1 ? " copies" : " copy") + " of item " + itemRented.print() + ", total reward points: " + stats.getRewardPoints();
    }

    /**
     * This function handles the logic of returning all items in this customer rental map.
     * Returns a string indicating the result of the operation
     * */
    public String returnAllItem(){
        int count = 0;

        for(Item itemRented: rentalMap.values()){
            Item itemInStore = searchItem(itemRented.getId());

            while(itemRented.getStock() > 0){
                itemRented.setStock(itemRented.getStock()-1);
                itemInStore.increaseStock();
                count++;
                stats.increaseRewards();
            }
        }

        stats.increaseReturnCount(count);
        rentalMap.clear();
        return "Returned all item, total reward points: " + stats.getRewardPoints();
    }

    @Override
    public VIP createCopy(){
        return new VIP(getId(), getName(), getAddress(), getPhone(), getUsername(), getPassword(), getRentalMap(), getStats());
    }
}
