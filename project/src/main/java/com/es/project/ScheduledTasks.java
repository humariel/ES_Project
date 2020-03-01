package com.es.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {

    @Autowired
    private RestTemplate restTemplate;

    private static ResponseEntity<Object> recentResponse;
    private final String baseURL = "https://api.openaq.org/v1/latest";

    @Scheduled(fixedRate = 15000)
    public void reportCurrentTime() {
       /* double i = Math.random();
        String url;
        if(i > 0.5){
            url = baseURL + "?country=PT&city=Aveiro";
        }else{
            url = "https://api.openaq.org/v1/countries?limit=10000";
        }*/

        String url = baseURL + "?country=PT&city=Aveiro";
        System.out.println(url);
        recentResponse = restTemplate.getForEntity(url, Object.class);
    }

    public static ResponseEntity<Object> getRecentResponse() {
        return recentResponse;
    }
}