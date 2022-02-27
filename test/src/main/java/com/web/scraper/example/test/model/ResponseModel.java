
  // IntelliJ API Decompiler stub source generated from a class file
  // Implementation of methods is not available

package com.web.scraper.example.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ResponseModel {
	
	
	@Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
    String title;
    String url;
    
 //   @Column(length=400)
 //   String description;
   String start_date;
String end_date;
  String location;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
/*public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}*/
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

    
}