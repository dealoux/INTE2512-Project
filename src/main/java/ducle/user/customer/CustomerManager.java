package ducle.user.customer;

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
}
