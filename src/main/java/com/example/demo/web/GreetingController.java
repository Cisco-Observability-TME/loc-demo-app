package com.example.demo.web;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

    GreetingController() {
        log.info("GreetingController initialized");
        this.counter.set(0);
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("Hello endpoint called");
        return "Hello, World!";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        log.info("Goodbye endpoint called");
        return "Goodbye, World!";
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "age", defaultValue = "18") int age) {
        log.info("Greeting endpoint called with name: {}, age: {}", name, age);
		return new Greeting(counter.incrementAndGet(), String.format(template, name), age);
	}
}
