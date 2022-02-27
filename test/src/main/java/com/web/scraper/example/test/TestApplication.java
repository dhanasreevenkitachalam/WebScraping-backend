package com.web.scraper.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.scraper.example.test.service.ScraperRepositoryService;
import com.web.scraper.example.test.service.ScraperService;
import com.web.scraper.example.test.service.ScraperServiceForComputerWorld;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		
	
	}

}
