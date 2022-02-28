package com.web.scraper.example.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.web.scraper.example.test.model.ResponseModel;

//Repository to persist data into in memory DB.

@Repository
public interface ProjectRespository extends CrudRepository<ResponseModel, Long> {
	
ResponseModel findByTitle(String title);
long count();

}
