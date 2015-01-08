package org.springfield.albright.filters;

import org.springfield.albright.AlbrightFilterInterface;

public class EP_Image implements AlbrightFilterInterface {
	
	public static String get(String uri) {
		System.out.println("EP_IMAGE CALLED2 = "+uri);
		// use springfield to find out more about the seed object (in this example luce video)
		// use some of these fields to create the query
		// call the europeana API
			// CALLCALLLCALL
		// body has to be in fsxml format
		String body = "some xml we need to return in fsxml format !";
		return body;
	}
}
