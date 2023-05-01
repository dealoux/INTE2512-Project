package ducle.customer;
import ducle.item.*;
import java.util.HashMap;

public abstract class Customer {
    protected String id;
    protected String name;
    protected String address;
    protected String phone;
    protected HashMap<String, Item> rentalList;
    protected String username;
    protected String password;

    public Customer(){
        id = "";
        name = "";
        address = "";
        phone = "";
        rentalList = new HashMap<>();
        username = "";
        password = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public HashMap<String, Item> getRentalList() {
        return rentalList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean rent(Item item){
        boolean result = true;

        if(item.getStock() > 0){
            result = false;
        }

        return result;
    }
}
