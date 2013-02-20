package com.lunalvo.CorporatePhonebookServer;

import java.util.ArrayList;
import org.restlet.resource.*;

public interface PhonebookResource {
	@Get
	public ArrayList<Contact> retrieve();

}
