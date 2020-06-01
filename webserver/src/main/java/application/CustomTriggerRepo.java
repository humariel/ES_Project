package application;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.Trigger;

public interface CustomTriggerRepo {

	List<Trigger> findAllByAlarm(String alarm);

}