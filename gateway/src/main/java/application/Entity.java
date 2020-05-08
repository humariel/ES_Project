package application;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Entity {
    
    @Id
    private String id;
    private Location location;

    public Entity() {
    }

    public Entity(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }

}