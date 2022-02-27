package com.web.scraper.example.test.service;

import java.io.IOException;

import java.util.HashSet;
import java.util.LinkedList;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import com.web.scraper.example.test.model.ResponseModel;



@Service
public class ScraperServiceImplForComputerWorld implements ScraperServiceForComputerWorld {
	

	@Override
	public Set<ResponseModel> getEventDetails() {
		
		
		Set<ResponseModel> responseModels=new HashSet<>();
		
	    		   extractDataFromComputerWorld(responseModels);
	    	   
		return responseModels;
	}
	private void extractDataFromComputerWorld(Set<ResponseModel> responseModels) {
		try {
			
			 Document document = Jsoup.connect("https://www.computerworld.com/article/3313417/tech-event-calendar-shows-conferences-and-it-expos-updated.html").get();
			  
			 
			Elements arefs=document.getElementsByTag("a");
			
			LinkedList<String> titles=new LinkedList<String>();
			
			LinkedList<String> refs=new LinkedList<String>();
			String s;
			for(Element aref:arefs) {
			     if (!StringUtils.isEmpty(aref.attr("title")) ) {
	                    titles.add(aref.attr("title"));
	                    refs.add(aref.attr("href"));
	                }
			}
			
		//Removing unwanted title	
		titles.pollFirst();
		//removing refs	
        refs.pollFirst();
			
		//	 ResponseModel responseModel = new ResponseModel();
			 Elements tds=document.getElementsByTag("td");
			 int i=0;
				ResponseModel  responseModel=new ResponseModel();
			for(Element el:tds) {
		
				i++;
				
				if(i==1) {
			//	responseModel.setDescription(el.text());
				}
				
				else if(i==2) {
					responseModel.setStart_date(el.text());
				}
				
				else if(i==3) {
					responseModel.setEnd_date(el.text());
	
				}
				else if(i==4)  {
					responseModel.setLocation(el.text());
					i=0;
					String title=titles.pollFirst();
					String titleToUpperCase=title.substring(0,1).toUpperCase() + title.substring(1).toLowerCase();
				
				   responseModel.setTitle(titleToUpperCase);
				    responseModel.setUrl(refs.pollFirst());
					responseModels.add(responseModel);
				     responseModel=new ResponseModel();
						
					
				}
				
		
				
				
			}
			
		}
		
		
		catch(IOException ex) {
			
			 ex.printStackTrace();
		}
			
		
	}
	

}
