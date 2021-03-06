package com.web.scraper.example.test.controller;

import com.web.scraper.example.test.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.scraper.example.test.model.ResponseModel;


import com.web.scraper.example.test.service.ScraperServiceForComputerWorld;



import com.web.scraper.example.test.service.ScraperRepositoryService;

import java.util.Set;



@RestController
@CrossOrigin
@RequestMapping(path = "/")
public class ScraperController {
	
    @Autowired
	private ScraperService scraperService;
	
	@Autowired
	private ScraperServiceForComputerWorld scraperServiceForComputerWorld;
	
	
	@Autowired
	private ScraperRepositoryService scraperRepositoryService;
	
	//http://localhost:8080/  starts webscraping and loads into h2 database
	@GetMapping(path="/")
	public void fetchDetails() {
		
		Set<ResponseModel>  set=scraperServiceForComputerWorld.getEventDetails();
		
		scraperRepositoryService.saveRepo(set);
       Set<ResponseModel>  set1=scraperService.getEventDetails();
        
      
       scraperRepositoryService.saveRepo(set1);
		
	}
	
	//queries the table , all  get all events from table.
	
	@GetMapping("/getEvents")
	public Iterable<ResponseModel> getEvents(){
		long count=scraperRepositoryService.countRecords();
		
		if(count==0) {
			System.out.println("inside ddd");
			Set<ResponseModel>  set=scraperServiceForComputerWorld.getEventDetails();
			
			scraperRepositoryService.saveRepo(set);
	       Set<ResponseModel>  set1=scraperService.getEventDetails();
	        
	      
	       scraperRepositoryService.saveRepo(set1);
	      
			
			}
		 return scraperRepositoryService.getAllEvents();
		}


	 

	 

}
