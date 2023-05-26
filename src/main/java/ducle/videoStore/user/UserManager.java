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

    public String addAdmin(Admin admin){
        adminMap.put(admin.getId(), admin);
        return "Added admin " + admin.getId();
    }

    public String addRegular(Regular customer){
        regularMap.put(customer.getId(), customer);
        return "Added regular customer " + customer.getId();
    }

    public String addVip(VIP customer){
        vipMap.put(customer.getId(), customer);
        return "Added VIP customer " + customer.getId();
    }

    public String addGuest(Guest customer){
        guestMap.put(customer.getId(), customer);
        return "Added Guest customer " + customer.getId();
    }

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

    public Admin searchAdmin(String id){
        return adminMap.get(id);
    }

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

    public User searchUser(String id){
        User result = searchAdmin(id);

        if(result == null){
            result = searchCustomer(id);
        }

        return result;
    }

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

    public User searchUserByUsername(String id){
        User result = searchAdminByUsername(id);

        if(result == null){
            result = searchCustomerByUsername(id);
        }

        return result;
    }

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

    public String removeRegular(String id){
        String result;
        Regular customer = regularMap.remove(id);

        if(customer != null){
            result = "Removed Regular " + customer.getId();
            customer.returnAllItem();
        }
        else{
            result = "Could not find any Regular with id " + id;
        }

        return result;
    }

    public String removeVip(String id){
        String result;
        VIP customer = vipMap.remove(id);

        if(customer != null){
            result = "Removed VIP " + customer.getId();
            customer.returnAllItem();
        }
        else{
            result = "Could not find any VIP with id " + id;
        }

        return result;
    }

    public String removeGuest(String id){
        String result;
        Guest customer = guestMap.remove(id);

        if(customer != null){
            result = "Removed Guest " + customer.getId();
            customer.returnAllItem();
        }
        else{
            result = "Could not find any Guest with id " + id;
        }

        return result;
    }

    public String removeCustomer(Customer customer){
        String result = "";

        if(customer instanceof Regular){
            result = removeRegular(customer.getId());
        }
        else if(customer instanceof VIP){
            result = removeVip(customer.getId());
        }
        else if(customer instanceof Guest){
            result = removeGuest(customer.getId());
        }

        return result;
    }

    public String removeCustomer(String id){
        String result;
        Customer customer = searchCustomer(id);

        if(customer != null){
            result = removeCustomer(customer);
        }
        else{
            result = "Could not find customer with id " + id;
        }

        return result;
    }

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

    public String printAdmins(){
        String result = "";

        List<Admin> admins = getAdminList();
        for(Admin admin: admins){
            result += admin.toString() + "\n";
        }

        return result;
    }

    public String printCustomers(){
        String result = "";

        List<Customer> customers = getCustomerList();
        for(Customer customer : customers){
            result += customer.toString() + "\n";
        }

        return result;
    }

    public String toString(){
        String result = "";

        result += printCustomers();
        result += printAdmins();

        return result;
    }
}
