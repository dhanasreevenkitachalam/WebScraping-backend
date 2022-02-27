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
	
	
	public void saveRepo(Set<ResponseModel> set) {
		for(ResponseModel s:set) {
			projectRepository.save(s);
		}
	}
	
	public Iterable<ResponseModel> getAllEvents() {
	return	projectRepository.findAll();
	}
	
	public ResponseModel getEventsByTitle(String title){
		return projectRepository.findByTitle(title);
	}

}
