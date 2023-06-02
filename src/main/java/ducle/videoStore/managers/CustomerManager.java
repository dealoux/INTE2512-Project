/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class has the utilities functions to handle all the customers data gained from the database
*/

package ducle.videoStore.managers;

import ducle.videoStore.user.User;
import ducle.videoStore.user.customer.Customer;
import ducle.videoStore.user.customer.Guest;
import ducle.videoStore.user.customer.Regular;
import ducle.videoStore.user.customer.VIP;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager extends UserManager<Customer>{
    /**
     * This function returns a list of all Regular instances in the map
     */
    public List<Regular> getRegularList(){
        List<Regular> result = new ArrayList<>();

        for(User user : map.values()){
            if(user instanceof Regular){
                result.add((Regular) user);
            }
        }

        return result;
    }

    /**
     * This function returns a list of VIP instances in the map
     */
    public List<VIP> getVIPList(){
        List<VIP> result = new ArrayList<>();

        for(User user : map.values()){
            if(user instanceof VIP){
                result.add((VIP) user);
            }
        }

        return result;
    }

    /**
     * This function returns a list of Guest instances in the map
     */
    public List<Guest> getGuestList(){
        List<Guest> result = new ArrayList<>();

        for(User user : map.values()){
            if(user instanceof Regular){
                result.add((Guest) user);
            }
        }

        return result;
    }

    public String remove(String id, boolean keepIventory){
        String result;
        Customer customer = (Customer) map.remove(id);

        if(customer != null){
            result = "Removed " +customer.getType() + " " + customer.getId();

            if(!keepIventory)
                customer.returnAllItem();
        }
        else{
            result = "Could not find any " + customer.getType() + " with id " + id;
        }

        return result;
    }

    public String remove(Customer customer, boolean keepIventory){
        return remove(customer.getId(), keepIventory);
    }

    @Override
    public String remove(String id){
        return remove(id, false);
    }

    @Override
    public String remove(Customer user){
        return remove(user, false);
    }
}
