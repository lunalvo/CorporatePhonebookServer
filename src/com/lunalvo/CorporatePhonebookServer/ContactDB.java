package com.lunalvo.CorporatePhonebookServer;

import java.util.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class ContactDB {
	public static void store(ArrayList<Contact> contacts){

		Key pbKey = KeyFactory.createKey("CorporatePhonebook", "contacts");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Date date = new Date();
		
		truncate();
		
		for(Contact contact: contacts){
			Entity contactEntity = new Entity("contactEntity", pbKey);
			contactEntity.setProperty("date", date);
			contactEntity.setProperty("team", contact.team);
			contactEntity.setProperty("name", contact.name);
			contactEntity.setProperty("companyEmail", contact.companyEmail);
			contactEntity.setProperty("personalEmail", contact.personalEmail);
			contactEntity.setProperty("workphone", contact.workphone);
			contactEntity.setProperty("cellphone", contact.cellphone);
			contactEntity.setProperty("msn", contact.msn);
			contactEntity.setProperty("skype", contact.skype);
			
			
			datastore.put(contactEntity);
		}
	}

	public static ArrayList<Contact> retrieve() {
		Key pbKey = KeyFactory.createKey("CorporatePhonebook", "contacts");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		ArrayList<Contact> returnContacts = new ArrayList<Contact>();

		Query query = new Query("contactEntity", pbKey).addSort("team", Query.SortDirection.ASCENDING).addSort("name", Query.SortDirection.ASCENDING);
		List<Entity> contacts = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		if (contacts.isEmpty()) {
			return null;
		} else {

			for (Entity contactEntity : contacts) {
				String team = contactEntity.getProperty("team").toString();
				String name = contactEntity.getProperty("name").toString();
				String companyEmail = contactEntity.getProperty("companyEmail").toString();
				String personalEmail = contactEntity.getProperty("personalEmail").toString();
				String workphone = contactEntity.getProperty("workphone").toString();
				String cellphone = contactEntity.getProperty("cellphone").toString();
				String msn = contactEntity.getProperty("msn").toString();
				String skype = contactEntity.getProperty("skype").toString();
				
				returnContacts.add(new Contact(team,name,companyEmail,personalEmail,workphone,cellphone,msn,skype));
			}

		}
		return returnContacts;
	}

	private static void truncate(){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query mydeleteq = new Query();
		PreparedQuery pq = datastore.prepare(mydeleteq);
		for (Entity result : pq.asIterable()) {
			try {
				datastore.delete(result.getKey());     
			} catch (Exception e) {
				// TODO: handle exception
			}
		} 
	}
}
