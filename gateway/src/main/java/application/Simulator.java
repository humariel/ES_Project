package application;

public class Simulator {



    public static Entity simulate(String id, double lat, double lon) {
        Entity entity = new Entity();

        entity.setId(id);
        entity.setLatitude(lat);
        entity.setLongitude(lon);

        entity.setTemperature(30);
        entity.setHumidity(10);
        entity.setPressure(3);
        entity.setPrecipIntensity(15);

        entity.setO3(20.2);
        entity.setCo(1.13);
        entity.setNo2(3.2);
        entity.setPm10(10);
        entity.setPm25(25);
        entity.setSo2(2.22);

        return entity;
    }

}
