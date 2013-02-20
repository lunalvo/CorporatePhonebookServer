package com.lunalvo.CorporatePhonebookServer;

import java.io.IOException;

import java.util.logging.Logger;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CorporatePhonebookUploaderServlet extends HttpServlet {
	private static final Logger log = Logger.getLogger(CorporatePhonebookUploaderServlet.class.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();

			if (user == null || !user.getEmail().equals("lunalvo@gmail.com")) {
				res.sendRedirect("/index.jsp");
				return;
			} 


			ServletFileUpload upload = new ServletFileUpload();
			res.setContentType("text/plain");
			FileItemIterator iterator = upload.getItemIterator(req);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				
				if (!item.isFormField()) {
					InputStream stream = item.openStream();
					log.info("Got an uploaded file: " + item.getFieldName() +", name = " + item.getName());
					
					ContactDB.store(ContactParser.parse(stream));
					
					log.info("Contacts saved to DB");
					res.getWriter().println("Contacts Saved!");
					break;
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.sendRedirect("/index.jsp");
	}
}
