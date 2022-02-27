package com.web.scraper.example.test.service;


import java.util.Set;

import org.springframework.stereotype.Service;

import com.web.scraper.example.test.model.ResponseModel;

@Service
public interface ScraperService {
 
   public Set<ResponseModel> getEventDetails();
  
}