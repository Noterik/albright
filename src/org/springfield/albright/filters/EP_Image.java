package org.springfield.albright.filters;

import java.io.IOException;

import org.springfield.albright.AlbrightFilterInterface;

import eu.europeana.api.client.EuropeanaApi2Client;
import eu.europeana.api.client.exception.EuropeanaApiProblem;
import eu.europeana.api.client.exception.EuropeanaClientException;
import eu.europeana.api.client.query.search.Api2Query;
import eu.europeana.api.client.query.search.EuropeanaComplexQuery;
import eu.europeana.api.client.result.EuropeanaApi2Item;
import eu.europeana.api.client.result.EuropeanaApi2Results;

public class EP_Image implements AlbrightFilterInterface {
	
	public static String get(String uri) {
		System.out.println("EP_IMAGE CALLED2 = "+uri);
		String newUri = uri.replace("/ep_images/", "");
		// use springfield to find out more about the seed object (in this example luce video)
		// use some of these fields to create the query
		// call the europeana API
			// CALLCALLLCALL
		// body has to be in fsxml format
		
		String body = "";
		
		try {
			Api2Query europeanaQuery = new Api2Query();
	        europeanaQuery.setTitle("bike");
	        europeanaQuery.setType(EuropeanaComplexQuery.TYPE.VIDEO);
	        europeanaQuery.setDataProvider("Deutsche Welle");
	   
	        //perform search
	        EuropeanaApi2Client europeanaClient = new EuropeanaApi2Client();
	        final int RESULTS_SIZE = 5;	
	        EuropeanaApi2Results res = europeanaClient.searchApi2(europeanaQuery, RESULTS_SIZE, 0);
	        
	        int count = 0;
	        for (EuropeanaApi2Item item : res.getAllItems()) {
	        	body += "<br>" + "**** " + (count++ + 1) + "<br>" +
	   		         "Title: " + item.getTitle() + "<br>" +
	   		         "Europeana URL: " + item.getObjectURL() + "<br>" +
	   		         "Type: " + item.getType() + "<br>" +
	   		         "Creator(s): " + item.getDcCreator() + "<br>" +
	   		         "Thumbnail(s): " + item.getEdmPreview() + "<br>" +
	   		         "Data provider: " + item.getDataProvider();
			}
        } catch(Exception e) {
        	e.printStackTrace();
        }

		return body;
	}
}
