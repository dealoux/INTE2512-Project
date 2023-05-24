package ducle.user.customer;

import ducle.item.Game;

import java.util.HashMap;

public class CustomerManager {
    private HashMap<String, Regular> regularList;
    private HashMap<String, VIP> vipList;
    private HashMap<String, Guest> guestList;

    public CustomerManager(){
        regularList = new HashMap<>();
        vipList = new HashMap<>();
        guestList = new HashMap<>();
    }

    public HashMap<String, Regular> getRegularList() {
        return regularList;
    }

    public HashMap<String, VIP> getVipList() {
        return vipList;
    }

    public HashMap<String, Guest> getGuestList() {
        return guestList;
    }

    public String addRegular(Regular customer){
        regularList.put(customer.getId(), customer);
        return "Added regular customer " + customer.getId();
    }

    public String addVip(VIP customer){
        vipList.put(customer.getId(), customer);
        return "Added VIP customer " + customer.getId();
    }

    public String addGuest(Guest customer){
        guestList.put(customer.getId(), customer);
        return "Added Guest customer " + customer.getId();
    }

    public String addCustomer(Customer customer){
        String result = "";

        if(customer instanceof Regular){
            result = addRegular((Regular) customer);
        }
        else if(customer instanceof VIP){
            result = addVip((VIP) customer);
        }
        else if(customer instanceof Guest){
            result = addGuest((Guest) customer);
        }

        return result;
    }

    public Customer searchCustomer(String id){
        Customer result = regularList.get(id);

        if(result == null){
            result = vipList.get(id);
        }

        if(result == null){
            result = guestList.get(id);
        }

        return result;
    }

    public String removeRegular(String id){
        String result;
        Regular customer = regularList.remove(id);

        if(customer != null){
            result = "Removed Regular " + customer.getId();
        }
        else{
            result = "Could not find any Regular with id " + id;
        }

        return result;
    }

    public String removeVip(String id){
        String result;
        VIP customer = vipList.remove(id);

        if(customer != null){
            result = "Removed VIP " + customer.getId();
        }
        else{
            result = "Could not find any VIP with id " + id;
        }

        return result;
    }

    public String removeGuest(String id){
        String result;
        Guest customer = guestList.remove(id);

        if(customer != null){
            result = "Removed Guest " + customer.getId();
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
}
