/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class represents the blueprint for all Admin users
*/

package ducle.videoStore.user;

public class Admin extends User {
    public Admin(){
        super();
        setType("Admin");
    }

    public Admin(String id, String name, String address, String phone, String username, String password) {
        super(id, name, address, phone, "Admin", username, password);
    }
}