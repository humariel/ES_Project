package breatheasy.alerts.alerts;

import org.springframework.data.mongodb.repository.MongoRepository;

import breatheasy.alerts.alerts.Alarm;

public interface AlarmRepository extends MongoRepository<Alarm, String> {

}