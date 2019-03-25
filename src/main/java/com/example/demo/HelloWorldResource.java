package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloWorldResource {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldResource.class);
    private RestTemplate restTemplate = new RestTemplate();
    private URI weatherService;

    public HelloWorldResource(@Value("${weatherUrl}") URI weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Map<String, Object> helloWorld() {
        return hello("world");
    }

    @GetMapping(path = "/{name}")
    public Map<String, Object> hello(@PathVariable String name) {
        Map weather = restTemplate.getForObject(weatherService, Map.class);
        LOG.info("Weather: {}", weather);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello " + name + "!");
        response.put("weather", weather);
        return response;
    }
}
