package com.lunalvo.CorporatePhonebookServer;

import java.util.ArrayList;

import org.restlet.data.Form;
import org.restlet.resource.*;

/**
 * The server side implementation of the Restlet resource.
 */
public class PhonebookServerResource extends ServerResource implements PhonebookResource {
	
	@Get
    public ArrayList<Contact> retrieve() {
		
		String f = null;
		try {
			Form queryParams = getReference().getQueryAsForm();
			f = queryParams.getFirstValue("f");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    	if(f == null || !f.equals("password"))
    	{
    		return null;
    	} else { 		
    		return ContactDB.retrieve();
    	}
    }
 
}

