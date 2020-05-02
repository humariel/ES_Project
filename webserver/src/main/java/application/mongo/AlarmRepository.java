package application.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.data.Alarm;

public interface AlarmRepository extends MongoRepository<Alarm, String> {

}