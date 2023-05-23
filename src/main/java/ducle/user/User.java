package ducle.user;

public abstract class User {
    protected String id;
    protected String name;
    protected String address;
    protected String phone;
    protected String type;
    protected String username;
    protected String password;

    public User(){
        id = "";
        name = "";
        address = "";
        phone = "";
        username = "";
        password = "";
        type = "";
    }

    public User(String id, String name, String address, String phone, String type, String username, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public User(String id, String name, String address, String phone, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
