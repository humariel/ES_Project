package gateway.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/quote")
    public Quote getQuote(){
        return ScheduledTasks.getQuote();
    }
}
