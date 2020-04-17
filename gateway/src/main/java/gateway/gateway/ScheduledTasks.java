package gateway.gateway;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gateway.DarkskyRequest;


@Component
public class ScheduledTasks {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Quote cQuote = null;
	private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper toJsonParser = new ObjectMapper();
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        KProducer producer = new KProducer();
        DarkskyRequest req = restTemplate.getForObject(
			"https://api.darksky.net/forecast/438151d66be4ce981bc94398c2428874/40.628883,-8.6590908?exclude=hourly,minutely,daily,alerts,flags",
                DarkskyRequest.class);
                
        try {
			//producer.send("/topic",toJsonParser.writeValueAsString(req));
			logger.info("got new DAILY data");
		} catch (Exception e) {
			logger.warn("Failed to get DAILY data for %s", dateFormat.format(new Date()));
		}
        
        // Quote quote = restTemplate.getForObject(
        //         "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        // cQuote = quote;

        // log.info(quote.toString());
        // log.info("The time is now {}", dateFormat.format(new Date()));

    }

    public static Quote getQuote(){
        return cQuote;
    }
}