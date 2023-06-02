/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 202301
  Assessment: Project
  Author: Le Minh Duc
  ID: s4000577
  Created  date: 29/05/2023
  Acknowledgement: I have acknowledged that all the resources here are the course materials as well as my own experiences
  Purpose: This class is the base structure for entities in the program (User, Item)
*/

package ducle.videoStore;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Entity implements Comparable<Entity>{
    protected StringProperty id = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }
    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String print(){
        return "[" + getId() + "]";
    }

    // sorted based on id
    @Override
    public int compareTo(Entity entity) {
        return getId().compareTo(entity.getId());
    }
}
