package breatheasy.alerts.alerts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Parish {

    @Id
    private String id;
    private String name;

    @GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
    private Geometry geometry;

    public Parish() {
    }

    public Parish(String id, String name, Geometry geometry) {
        this.id = id;
        this.name = name;
        this.geometry = geometry;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Geometry getGeometry() {
        return this.geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", geometry='" + getGeometry() + "'" +
            "}";
    }


}