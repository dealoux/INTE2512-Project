package ducle.user;

public class Admin extends User {
    public Admin(){
        super();
        type = "Admin";
    }

    public Admin(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "Admin", username, password);
    }
}