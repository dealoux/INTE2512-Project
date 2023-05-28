/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class has the utilities functions to handle all the users data gained from the database (.txt files)
*/

package ducle.videoStore.user;

import ducle.videoStore.user.customer.Customer;
import ducle.videoStore.user.customer.Guest;
import ducle.videoStore.user.customer.Regular;
import ducle.videoStore.user.customer.VIP;

import java.util.*;

public class UserManager {
    private Map<String, Admin> adminMap;
    private Map<String, Regular> regularMap;
    private Map<String, VIP> vipMap;
    private Map<String, Guest> guestMap;

    public UserManager(){
        adminMap = new HashMap<>();
        regularMap = new HashMap<>();
        vipMap = new HashMap<>();
        guestMap = new HashMap<>();
    }

    public Map<String, Admin> getAdminMap() {
        return adminMap;
    }

    public Map<String, Regular> getRegularMap() {
        return regularMap;
    }

    public Map<String, VIP> getVipMap() {
        return vipMap;
    }

    public Map<String, Guest> getGuestMap() {
        return guestMap;
    }

    public List<Admin> getAdminList(){
        List<Admin> result = new ArrayList<>(adminMap.values());
        Collections.sort(result);
        return result;
    }

    public List<Regular> getRegularList(){
        List<Regular> result = new ArrayList<>(regularMap.values());
        Collections.sort(result);
        return result;
    }

    public List<VIP> getVipList(){
        List<VIP> result = new ArrayList<>(vipMap.values());
        Collections.sort(result);
        return result;
    }

    public List<Guest> getGuestList(){
        List<Guest> result = new ArrayList<>(guestMap.values());
        Collections.sort(result);
        return result;
    }

    public List<Customer> getCustomerList(){
        List<Customer> result = new ArrayList<>();

        result.addAll(this.getRegularList());
        result.addAll(this.getVipList());
        result.addAll(this.getGuestList());

        Collections.sort(result);
        return result;
    }

    /**
     * This function searches the map of admins to try and find the admin with the given id.
     * Returns the Admin instance if found, otherwise returns null
     * @param id id for searching
     * */
    public Admin searchAdmin(String id){
        return adminMap.get(id);
    }

    /**
     * This function searches all customers maps to try and find the customer with the given id.
     * Returns the Customer instance if found, otherwise returns null
     * @param id id for searching
     * */
    public Customer searchCustomer(String id){
        Customer result = regularMap.get(id);

        if(result == null){
            result = vipMap.get(id);
        }

        if(result == null){
            result = guestMap.get(id);
        }

        return result;
    }

    /**
     * This function searches all maps to try and find the user with the given id.
     * Returns the User instance if found, otherwise returns null
     * @param id id for searching
     * */
    public User searchUser(String id){
        User result = searchAdmin(id);

        if(result == null){
            result = searchCustomer(id);
        }

        return result;
    }

    /**
     * This function searches the map of admins to try and find the admin with the given username.
     * Returns the Admin instance if found, otherwise returns null
     * @param username username for searching
     * */
    public Admin searchAdminByUsername(String username){
        Admin result = null;

        for(Admin admin : adminMap.values()){
            if(admin.getUsername().equals(username)){
                result = admin;
                break;
            }
        }

        return result;
    }

    /**
     * This function searches the map of regulars to try and find the regular with the given username.
     * Returns the Regular instance if found, otherwise returns null
     * @param username username for searching
     * */
    public Regular searchRegularByUsername(String username){
        Regular result = null;

        for(Regular customer : regularMap.values()){
            if(customer.getUsername().equals(username)){
                result = customer;
                break;
            }
        }

        return result;
    }

    /**
     * This function searches the map of VIPs to try and find the VIP with the given username.
     * Returns the VIP instance if found, otherwise returns null
     * @param username username for searching
     * */
    public VIP searchVipByUsername(String username){
        VIP result = null;

        for(VIP customer : vipMap.values()){
            if(customer.getUsername().equals(username)){
                result = customer;
                break;
            }
        }

        return result;
    }

    /**
     * This function searches the map of guests to try and find the guest with the given username.
     * Returns the Guest instance if found, otherwise returns null
     * @param username username for searching
     * */
    public Guest searchGuestByUsername(String username){
        Guest result = null;

        for(Guest customer : guestMap.values()){
            if(customer.getUsername().equals(username)){
                result = customer;
                break;
            }
        }

        return result;
    }

    /**
     * This function searches all customers maps to try and find the customer with the given username.
     * Returns the Customer instance if found, otherwise returns null
     * @param username username for searching
     * */
    public Customer searchCustomerByUsername(String username){
        Customer result = searchRegularByUsername(username);

        if(result == null){
            result = searchVipByUsername(username);
        }

        if(result == null){
            result = searchGuestByUsername(username);
        }

        return result;
    }

    /**
     * This function searches all maps to try and find the user with the given id.
     * Returns the User instance if found, otherwise returns null
     * @param username username for searching
     * */
    public User searchUserByUsername(String username){
        User result = searchAdminByUsername(username);

        if(result == null){
            result = searchCustomerByUsername(username);
        }

        return result;
    }

    /**
     * This function adds the given Admin instance to the map of admins.
     * Returns a string indicating the result of the operation
     * @param admin reference to the Admin instance
     * */
    public String addAdmin(Admin admin){
        adminMap.put(admin.getId(), admin);
        return "Added admin " + admin.getId();
    }

    /**
     * This function adds the given Regular instance to the map of regulars.
     * Returns a string indicating the result of the operation
     * @param customer reference to the Regular instance
     * */
    public String addRegular(Regular customer){
        regularMap.put(customer.getId(), customer);
        return "Added regular customer " + customer.getId();
    }

    /**
     * This function adds the given VIP instance to the map of VIPs.
     * Returns a string indicating the result of the operation
     * @param customer reference to the VIP instance
     * */
    public String addVip(VIP customer){
        vipMap.put(customer.getId(), customer);
        return "Added VIP customer " + customer.getId();
    }

    /**
     * This function adds the given Guest instance to the map of guests.
     * Returns a string indicating the result of the operation
     * @param customer reference to the Regular instance
     * */
    public String addGuest(Guest customer){
        guestMap.put(customer.getId(), customer);
        return "Added Guest customer " + customer.getId();
    }

    /**
     * This function adds the given Customer instance to its respective map.
     * Returns a string indicating the result of the operation
     * @param customer reference to the customer instance
     * */
    public String addCustomer(Customer customer){
        String result = "";

        switch (customer.getType()){
            case "Regular":
                result = addRegular(new Regular(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getUsername(), customer.getPassword(), customer.getRentalMap()));
                break;
            case "VIP":
                result = addVip(new VIP(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getUsername(), customer.getPassword(), customer.getRentalMap()));
                break;
            case "Guest":
                result = addGuest(new Guest(customer.getId(), customer.getName(), customer.getAddress(), customer.getPhone(), customer.getUsername(), customer.getPassword(), customer.getRentalMap()));
                break;
        }

        return result;
    }

    /**
     * This function tries to remove the admin with the given id from the map of admins if found.
     * Returns a string indicating the result of the operation
     * @param id id of the admin to be removed
     * */
    public String removeAdmin(String id){
        String result;

        Admin admin = adminMap.remove(id);

        if(admin != null){
            result = "Removed Admin " + admin.getId();
        }
        else{
            result = "Could not find any Admin with id " + id;
        }

        return result;
    }

    /**
     * This function tries to remove the regular with the given id from the map of regulars if found.
     * Returns a string indicating the result of the operation
     * @param id id of the regular to be removed
     * */
    public String removeRegular(String id, boolean keepIventory){
        String result;
        Regular customer = regularMap.remove(id);

        if(customer != null){
            result = "Removed Regular " + customer.getId();

            if(!keepIventory)
                customer.returnAllItem();
        }
        else{
            result = "Could not find any Regular with id " + id;
        }

        return result;
    }

    /**
     * This function tries to remove the VIP with the given id from the map of VIPs if found.
     * Returns a string indicating the result of the operation
     * @param id id of the VIP to be removed
     * */
    public String removeVip(String id, boolean keepIventory){
        String result;
        VIP customer = vipMap.remove(id);

        if(customer != null){
            result = "Removed VIP " + customer.getId();

            if(!keepIventory)
                customer.returnAllItem();
        }
        else{
            result = "Could not find any VIP with id " + id;
        }

        return result;
    }

    /**
     * This function tries to remove the guest with the given id from the map of guests if found.
     * Returns a string indicating the result of the operation
     * @param id id of the guest to be removed
     * */
    public String removeGuest(String id, boolean keepIventory){
        String result;
        Guest customer = guestMap.remove(id);

        if(customer != null){
            result = "Removed Guest " + customer.getId();

            if(!keepIventory)
                customer.returnAllItem();
        }
        else{
            result = "Could not find any Guest with id " + id;
        }

        return result;
    }

    /**
     * This function tries to remove the given customer instance from its respective map if found
     * Returns a string indicating the result of the operation
     * @param customer reference to of the customer to be removed
     * @param keepIventory keep the customer inventory if set to true
     * */
    public String removeCustomer(Customer customer, boolean keepIventory){
        String result = "";

        if(customer instanceof Regular){
            result = removeRegular(customer.getId(), keepIventory);
        }
        else if(customer instanceof VIP){
            result = removeVip(customer.getId(), keepIventory);
        }
        else if(customer instanceof Guest){
            result = removeGuest(customer.getId(), keepIventory);
        }

        return result;
    }

    /**
     * This function tries to remove the given customer instance from its respective map if found
     * Returns a string indicating the result of the operation
     * @param customer reference to of the customer to be removed
     * */
    public String removeCustomer(Customer customer){
        return removeCustomer(customer, false);
    }

    /**
     * This function tries to remove the customer with the given id from its respective map if found.
     * Returns a string indicating the result of the operation
     * @param id id of the customer to be removed
     * @param keepInventory keep the customer inventory if set to true
     * */
    public String removeCustomer(String id, boolean keepInventory){
        String result;
        Customer customer = searchCustomer(id);

        if(customer != null){
            result = removeCustomer(customer, keepInventory);
        }
        else{
            result = "Could not find customer with id " + id;
        }

        return result;
    }

    /**
     * This function tries to remove the customer with the given id from its respective map if found.
     * Returns a string indicating the result of the operation
     * @param id id of the customer to be removed
     * */
    public String removeCustomer(String id){
        return removeCustomer(id, false);
    }

    /**
     * This function tries to remove the user with the given id from its respective map if found.
     * Returns a string indicating the result of the operation
     * @param id id of the user to be removed
     * */
    public String removeUser(String id){
        String result = removeAdmin(id);

        if(!result.startsWith("Removed")){
            result = removeCustomer(id);

            if(!result.startsWith("Removed")){
                result = "Could not find user with id " + id;
            }
        }

        return result;
    }

    /**
     * This function returns a string with all admins data
     * */
    public String printAdmins(){
        String result = "";

        List<Admin> admins = getAdminList();
        for(Admin admin: admins){
            result += admin.toString() + "\n";
        }

        return result;
    }

    /**
     * This function returns a string with all customers data
     * */
    public String printCustomers(){
        String result = "";

        List<Customer> customers = getCustomerList();
        for(Customer customer : customers){
            result += customer.toString() + "\n";
        }

        return result;
    }

    /**
     * This function returns a string with all customers and admins data
     * */
    public String toString(){
        String result = "";

        result += printCustomers();
        result += printAdmins();

        return result;
    }
}
