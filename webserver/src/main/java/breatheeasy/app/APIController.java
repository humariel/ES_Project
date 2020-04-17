package breatheeasy.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import breatheeasy.app.Repository.BreathRepo;
import breatheeasy.app.Requests.DarkSkyNowRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class APIController {

    @Autowired
    private BreathRepo db;

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private static final DateFormat saveFormat = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/current")
    public String getNowWeather(@RequestParam(name="latitude", required=false, defaultValue="40.628883") String latitude,
								@RequestParam(name="longitude", required=false, defaultValue="-8.6590908") String longitude,
								Model model) {
		DarkSkyNowRequest req = restTemplate.getForObject(
				"https://api.darksky.net/forecast/438151d66be4ce981bc94398c2428874/"+latitude+","+longitude+"?exclude=minutely,hourly,daily,alerts,flags&units=si",
				DarkSkyNowRequest.class
		);
		return req.toString();
	}
}
