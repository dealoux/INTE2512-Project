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
import java.util.Map;

public class VIP extends Customer {
    protected int rewardPoints;

    public VIP() {
        super("VIP");
        rewardPoints = 0;
    }

    public VIP(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "VIP", username, password);
        rewardPoints = 0;
    }

    public VIP(String id, String name, String address, String phone, String username, String password, Map<String, Item> rentalList) {
        super(id, name, address, phone, "VIP", username, password, rentalList);
        rewardPoints = 0;
    }

    public VIP(String id, String name, String address, String phone) {
        super(id, name, address, phone, "VIP");
        rewardPoints = 0;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @Override
    public String rent(Item item) {
        String result = super.rent(item);

        if(result.startsWith("Rented")){
            if(rewardPoints >= 100){
                Item rented = rentalMap.get(item.getId());
                rented.setFee(rented.getFee()-item.getFee());
                rewardPoints -= 100;
                result += ", for free using 100 points";
            }
            else {
                rewardPoints += 10;
            }

            result += ", total reward points: " + rewardPoints;
        }

        return result;
    }
}
