/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: this class is the base structure for all managers
*/

package ducle.videoStore.managers;

import ducle.videoStore.Entity;

import java.util.*;

public class Manager<T extends Entity> {
    protected Map<String, T> map;

    public Manager(){
        map = new HashMap<>();
    }
    
    public Map<String, T> getMap() {
        return map;
    }

    /**
     * This function returns a list of all T instances
     * */
    public List<T> getList(){
        List<T> result = new ArrayList<>(map.values());
        Collections.sort(result);
        return result;
    }

    /**
     * This function searches all maps to try and find the entity with the given id.
     * Returns the Entity instance if found, otherwise returns null
     * @param id id for searching
     * */
    public T search(String id){
        return map.get(id);
    }

    /**
     * This function adds the given Entity instance to the map.
     * Returns a string indicating the result of the operation
     * @param entity reference to the Entity instance
     * */
    public String add(T entity){
        map.put(entity.getId(), entity);
        return "Added " + entity.getClass().toString() + " " + entity.getId();
    }

    /**
     * This function tries to remove the entity with the given id from the map if found.
     * Returns a string indicating the result of the operation
     * @param id id of the entity to be removed
     * */
    public String remove(String id){
        String result;
        Entity entity = map.remove(id);

        if(entity != null){
            result = "Removed " + entity.getClass().toString() + " " + entity.print();
        }
        else{
            result = "Could not find any " + entity.getClass().toString() + " with id " + id;
        }

        return result;
    }

    /**
     * This function tries to remove the given Entity instance from the map.
     * Returns a string indicating the result of the operation
     * @param entity reference of the Entity to be removed
     * */
    public String remove(T entity){
        return remove(entity.getId());
    }

    public String toString(){
        String result = "";

        List<T> entities = getList();
        for(T entity : entities){
            result +=  entity.toString() + "\n";
        }

        return result;
    }
}
