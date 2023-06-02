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
