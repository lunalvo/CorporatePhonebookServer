package com.lunalvo.CorporatePhonebookServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ContactParser {
	public static ArrayList<Contact> parse(InputStream stream) {
		ArrayList<Contact> returnList = new ArrayList<Contact>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		
			String line = null;
			while((line = br.readLine()) != null)
			{
				if(line.trim().length() > 0)
					returnList.add(new Contact(line.trim()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnList;
	}
}
