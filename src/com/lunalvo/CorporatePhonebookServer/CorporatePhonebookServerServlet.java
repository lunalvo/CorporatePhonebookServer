package com.lunalvo.CorporatePhonebookServer;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class CorporatePhonebookServerServlet extends Application {

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new Resource
		
		Router router = new Router(getContext());
		
		getConnectorService().getClientProtocols().add(Protocol.FILE);
		router.attachDefault(PhonebookServerResource.class);
		
		return router;
	}
}
