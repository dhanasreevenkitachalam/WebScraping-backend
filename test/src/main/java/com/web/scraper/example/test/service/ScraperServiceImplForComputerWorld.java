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
/* ScraperServiceImplForComputerWorld uses Jsoup to get data from ComputerWorld Website
 * It extracts all the 'a' tag from website and extracts title and url from it.
 * then extracts 'td' from website and loop through it and set to corresponding field values in ResponseModel.
 * For each new <tr>, a ReponseModel Object is created.
 * */


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
			
			//Extracting all <a> tag from website, and retrieving title and url from it and added to LinkedList
			Elements arefs=document.getElementsByTag("a");
			LinkedList<String> titles=new LinkedList<String>();
			LinkedList<String> refs=new LinkedList<String>();
			
			for(Element aref:arefs) {
			     if (!StringUtils.isEmpty(aref.attr("title")) ) {
	                    titles.add(aref.attr("title"));
	                    refs.add(aref.attr("href"));
	                }
			}
			
				//Removing unwanted title	
				titles.pollFirst();
				//Removing unwanted refs	
		        refs.pollFirst();
		        
		     /*Extracting all <td> tags from website. Each <td> contains data ,it is extracted and added to private field
		        of ResponseModel using setter methods. Once set of <td> per each <tr> is handled, a new object of 
		        ResponseModel Created for next <tr>
		        */
		        
			 Elements tds=document.getElementsByTag("td");
			int i=0;
			ResponseModel  responseModel=new ResponseModel();
			for(Element el:tds) {
								i++;
								if(i==1) {
							   //	responseModel.setDescription(el.text());
								}
								else if(i==2) {
									//Setting start_date
									responseModel.setStart_date(el.text());
								}
								else if(i==3) {
									//Setting end_date
									responseModel.setEnd_date(el.text());
								}
								else if(i==4)  {
									//Setting location
									responseModel.setLocation(el.text());
									i=0;
									
									//Converting first Letter of title into uppercase for sorting funtionality in frontend.
									String title=titles.pollFirst();
									String titleToUpperCase=title.substring(0,1).toUpperCase() + title.substring(1).toLowerCase();
								    responseModel.setTitle(titleToUpperCase);
								    //Setting URL
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
