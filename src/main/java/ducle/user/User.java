/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the base blueprint for all users
*/

package ducle.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class User implements Comparable<User> {
    protected StringProperty id = new SimpleStringProperty();
    protected StringProperty name = new SimpleStringProperty();
    protected StringProperty address = new SimpleStringProperty();
    protected StringProperty phone = new SimpleStringProperty();
    protected StringProperty type = new SimpleStringProperty();
    protected StringProperty username = new SimpleStringProperty();
    protected StringProperty password = new SimpleStringProperty();

    protected static List<String> userTypeList = new ArrayList<>(
            Arrays.asList("Admin")
    );

    public User(){
        id.set("");
        name.set("");
        address.set("");
        phone.set("");
        setUsername("");
        setPassword("");
        type.set("");
    }

    public User(String id, String name, String address, String phone, String type, String username, String password) {
        this.id.set(id);
        this.name.set(name);
        this.address.set(address);
        this.phone.set(phone);
        this.type.set(type);
        setUsername(username);
        setPassword(password);
    }

    public User(String id, String name, String address, String phone, String type) {
        this.id.set(id);
        this.name.set(name);
        this.address.set(address);
        this.phone.set(phone);
        this.type.set(type);
    }

    public String getId() {
        return id.get();
    }
    public StringProperty idProperty() {
        return id;
    }
    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }
    public StringProperty addressProperty() {
        return address;
    }
    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        return phone.get();
    }
    public StringProperty phoneProperty() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getType() {
        return type.get();
    }
    public StringProperty typeProperty() {
        return type;
    }
    public void setType(String type) {
        this.type.set(type);
    }

    public String getUsername() {
        return username.get();
    }
    public StringProperty usernameProperty() {
        return username;
    }
    public void setUsername(String username) {
        this.username.set(username.trim());
    }

    public String getPassword() {
        return password.get();
    }
    public StringProperty passwordProperty() {
        return password;
    }
    public void setPassword(String password) {
        this.password.set(password.trim());
    }

    public static List<String> getUserTypeList() {
        return userTypeList;
    }

    // users are sorted based on their IDs
    @Override
    public int compareTo(User user) {
        return getId().compareTo(user.getId());
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getAddress() + "," + getPhone() + "," + getType() + ", " + getUsername() + ", " + getPassword();
    }
}
