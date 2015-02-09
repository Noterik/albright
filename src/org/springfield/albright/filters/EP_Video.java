package org.springfield.albright.filters;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.springfield.albright.AlbrightFilterInterface;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;

import eu.europeana.api.client.EuropeanaApi2Client;
import eu.europeana.api.client.exception.EuropeanaApiProblem;
import eu.europeana.api.client.exception.EuropeanaClientException;
import eu.europeana.api.client.query.search.Api2Query;
import eu.europeana.api.client.query.search.EuropeanaComplexQuery;
import eu.europeana.api.client.result.EuropeanaApi2Item;
import eu.europeana.api.client.result.EuropeanaApi2Results;

public class EP_Video implements AlbrightFilterInterface {
	
	public static String get(String uri) {
		System.out.println("EP_VIDEO CALLED2 = "+uri);
		String newUri = uri.replace("/ep_videos/", "");
		// use springfield to find out more about the seed object (in this example luce video)
		// use some of these fields to create the query
		// call the europeana API
			// CALLCALLLCALL
		// body has to be in fsxml format
		FsNode videonode = Fs.getNode(newUri);
		if(videonode==null) {
			return null;
		}
		
		String genreProprty = videonode.getProperty("genre");
		String terms = videonode.getProperty("ThesaurusTerm");
		terms = terms.replaceAll(","," "); //Terms can be multiple separated by comma, so replace comma with space
		String all = genreProprty + " " + terms;
		all = URLEncoder.encode(all);
		String body = "";
		
		try {
			Api2Query europeanaQuery = new Api2Query();
			europeanaQuery.setQueryParams("query="+all+"&qf=TYPE%3A"+EuropeanaComplexQuery.TYPE.VIDEO);
	        //europeanaQuery.setTitle(genre);
	        //europeanaQuery.setType(EuropeanaComplexQuery.TYPE.IMAGE);
	        //europeanaQuery.setDataProvider("Deutsche Welle");
	   
	        //perform search
	        EuropeanaApi2Client europeanaClient = new EuropeanaApi2Client();
	        final int RESULTS_SIZE = 12;	
	        EuropeanaApi2Results res = europeanaClient.searchApi2(europeanaQuery, RESULTS_SIZE, 0);
	        
	        int count = 0;
	        body = "<fsxml>";
	        for (EuropeanaApi2Item item : res.getAllItems()) {
	        	if(item.getEdmPreview()==null) continue;
	        	body += "<ep_videos id=\"" + (count++ + 1) + "\"><properties>";
				body += "<title><![CDATA[" + item.getTitle().get(0) + "]]></title>";
				body += "<url><![CDATA[" + item.getObjectURL() + "]]></url>";
				body += "<type>" + item.getType() + "</type>";
				List<String> creators = item.getDcCreator();
				//Check if there is a creator set in the item and only then show it.
				if(creators!= null && creators.size()>0) {
				   body += "<creator><![CDATA[" + creators.get(0) + "]]></creator>";
				}
				body += "<thumbnail><![CDATA[" + item.getEdmPreview().get(0) + "]]></thumbnail>";
				body += "<provider><![CDATA[" + item.getDataProvider().get(0) + "]]></provider>";
				body += "</properties></ep_videos>";
	        	           
	        }
	        body += "</fsxml>";
	        	
        } catch(Exception e) {
        	e.printStackTrace();
        }

		return body;
	}
}