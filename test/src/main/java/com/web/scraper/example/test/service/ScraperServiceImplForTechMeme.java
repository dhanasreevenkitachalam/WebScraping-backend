package com.web.scraper.example.test.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import com.web.scraper.example.test.model.ResponseModel;
/*
 ScraperServiceImplForTechMeme extracts data from techMeme website

*/
@Service
public class ScraperServiceImplForTechMeme implements ScraperService{
	
	
	@Override
	public Set<ResponseModel> getEventDetails() {
		Set<ResponseModel> responseModels=new HashSet<>();
		extractDataFromComputerWorldTech(responseModels);
		return responseModels;
	}
	private void extractDataFromComputerWorldTech(Set<ResponseModel> responseModels) {
		try {
			
			 Document document = Jsoup.connect("https://www.techmeme.com/events").get();
			 Elements rhov= document.getElementsByClass("rhov");
			 for(Element rh :rhov) {
				//Extracting all the <a> tag inside the class with name "rhov"
				 Elements arefs=rh.getElementsByTag("a");
				 ResponseModel responseModel=new ResponseModel();
				
				for(Element aref :arefs) {
					//Extracting the URL from <a> tag and trimming unwanted "/r2/" from the url.
						String urls= aref.attr("href");
						String trimmedUrl=urls.replace("/r2/", "");	
						//Setting url value
						responseModel.setUrl(trimmedUrl);
						
						//Extracting all <div> tag to extract date and location
						Elements divs=aref.getElementsByTag("div");
						int i=0;
						for(Element div:divs) {
							i++;
						 /*In Tech Meme, website date in the format "Mar 17-19" or "Jun 30-Jul 1". To convert single date range into 
						  * start_date and end_date.Extracted month and converted to corresponding numeric value using HashMap.
						  * For date format "Mar 17-19", month remains same for start_date and end_date
						  * For date format "Jun 30-Jul 1", month is different and extracted separately and added to variables
						  * startdate and enddate.
				          *
				          *  */	
							if(i==1) {
							Map<String, String> myMap = new HashMap<String, String>() {{
							    put("Jan", "01");
							    put("Feb", "02");
							    put("Mar", "03");
							    put("Apr", "04");
							    put("May", "05");
							    put("Jun", "06");
							    put("Jul", "07");
							    put("Aug", "08");
							    put("Sep", "09");
							    put("Oct", "10");
							    put("Nov", "11");
							    put("Dec", "12");
							}};
								String s=div.text();
								// Splitting Dates to end start Date and End date
								String[] words=s.split("-");
								String month="";
								String startDate="";
							    String endDate="";
						   
						     for(int j=0;j<words.length;j++) {
							    //Words[0] has start date and month in format "Jan 30"
							    //Splitting them to retrieve month and date separately
							    String[] firstDates=words[0].split("\\s");
							    	 
							     //Retrieving months value as numeric from myMap 
							     month=myMap.get(firstDates[0]);
						    	 startDate="2022-"+month+"-"+firstDates[1];
						    	 if(j==1) {
							    	  //Retrieving end date. End date could be either date only or a combination of month and date
							    	  //Retrieving both month and date
							    	  String[] secondDates=words[j].split("\\s");
							    	  if(secondDates.length==2) {
								    		 endDate="2022-"+myMap.get(secondDates[0])+"-"+secondDates[1];
								    	 }
								      else {
								    		 endDate="2022-"+month+"-"+secondDates[0];
								    	 } 
						    	 }
						    	//If Event is only for a day, then start_date and end_date is same.
						    	 if(endDate.isEmpty())
						    		 {
						    		 endDate=startDate;
						    		 }
						     }
							responseModel.setStart_date(startDate);
							responseModel.setEnd_date(endDate);
						}
						
						else if(i==2) {
							//Extracting title from <div> and converting first letter into upperCase for Sorting function frontend
								String title=div.text();
								String titleToUpperCase=title.substring(0,1).toUpperCase() + title.substring(1).toLowerCase();
								responseModel.setTitle(titleToUpperCase);
						}
						
						else if(i==3) {
								String location=div.text();
							//Extracting location, if its empty setting it to "VIRTUAL"	
								if(location.isEmpty()) {
									location="VIRTUAL";
								}
								responseModel.setLocation(location);
								i=0;
								responseModels.add(responseModel);
								responseModel=new ResponseModel();
						}
					}
				}
			 }	
		}
		
		
		catch(IOException ex) {
			
			 ex.printStackTrace();
		}
	}
}
