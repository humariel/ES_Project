package application;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.Trigger;

public interface TriggerRepository extends MongoRepository<Trigger, String>{

}