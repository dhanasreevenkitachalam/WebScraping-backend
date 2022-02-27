package com.web.scraper.example.test.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.scraper.example.test.model.ResponseModel;
import com.web.scraper.example.test.repository.ProjectRespository;

@Service
public class ScraperRepositoryService {
	
	
	@Autowired
	private ProjectRespository projectRepository;
	
//Method to save the data obtained from Web Scraping into Database	
	public void saveRepo(Set<ResponseModel> set) {
		for(ResponseModel s:set) {
			projectRepository.save(s);
		}
	}
	
//Method to get All Events from Database	
	public Iterable<ResponseModel> getAllEvents() {
	return	projectRepository.findAll();
	}
	
	

}
