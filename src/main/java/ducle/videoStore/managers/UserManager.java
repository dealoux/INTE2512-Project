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

package ducle.videoStore.managers;

import ducle.videoStore.user.User;

public class UserManager<T extends User> extends Manager<T> {
    /**
     * This function searches all maps to try and find the user with the given id.
     * Returns the User instance if found, otherwise returns null
     * @param username username for searching
     * */
    public T searchUserByUsername(String username){
        T result = null;

        for(User user : map.values()){
            if(user.getUsername().equals(username)){
                result = (T) user;
                break;
            }
        }

        return result;
    }
}
