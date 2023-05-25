package ducle.user.customer;

import ducle.item.Item;
import java.util.Map;

public class Regular extends Customer {
    public Regular(){
        super("Regular");
    }

    public Regular(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "Regular", username, password);
    }

    public Regular(String id, String name, String address, String phone, String username, String password, Map<String, Item> rentalList) {
        super(id, name, address, phone, "Regular", username, password, rentalList);
    }

    public Regular(String id, String name, String address, String phone) {
        super(id, name, address, phone, "Regular");
    }
}
