package breatheasy.alerts.alerts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TriggerRepository extends MongoRepository<Trigger, String>{

}