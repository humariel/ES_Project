package application;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.Alarm;

public interface AlarmRepository extends MongoRepository<Alarm, String> {

}