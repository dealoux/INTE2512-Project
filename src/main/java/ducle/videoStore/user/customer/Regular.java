/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the blueprint for all regular customers
*/

package ducle.videoStore.user.customer;

import ducle.videoStore.item.Item;
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

    @Override
    public Regular createCopy(){
        return new Regular(getId(), getName(), getAddress(), getPhone(), getUsername(), getPassword(), getRentalMap());
    }
}
