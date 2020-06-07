package alarm.breatheasy;

import org.springframework.data.mongodb.repository.MongoRepository;

import alarm.breatheasy.Alarm;

public interface AlarmRepository extends MongoRepository<Alarm, String> {

}