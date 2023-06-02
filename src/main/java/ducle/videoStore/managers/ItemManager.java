/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class has the utilities functions to handle all the items data gained from the database (.txt files)
*/

package ducle.videoStore.managers;

import ducle.videoStore.item.Item;

import java.util.*;

public class ItemManager extends Manager<Item> {
    /**
     * This function returns a list of all out-of-stock items
     * */
    public List<Item> getOOSItemList(){
        List<Item> result = new ArrayList<>();

        for(Item item : map.values()){
            if(item.getStock() == 0){
                result.add(item);
            }
        }

        Collections.sort(result);
        return result;
    }

    /**
     * This function returns a list of all in-stock items
     * */
    public List<Item> getISItemList(){
        List<Item> result = new ArrayList<>();

        for(Item item : map.values()){
            if(item.getStock() > 0){
                result.add(item);
            }
        }

        Collections.sort(result);
        return result;
    }
}
