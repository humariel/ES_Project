package application;

import org.springframework.data.mongodb.repository.MongoRepository;

import application.Value;

public interface ValueRepository extends MongoRepository<Value, String>, CustomValueRepository  {

}