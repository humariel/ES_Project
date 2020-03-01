package com.es.project;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController {

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        return ScheduledTasks.getRecentResponse();
    }
}
