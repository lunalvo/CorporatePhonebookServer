<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<body>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
      	pageContext.setAttribute("user", user);
      
      	if(!user.getEmail().equals("lunalvo@gmail.com"))
	  	{
      		%>
      		<p>Access Denied! (You can
      		<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
      		<%
	  	}
	  	else
	  	{
	  		%>
		    <h3>File Upload:</h3>
Select a file to upload: <br />
<form action="/uploader" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
		  </form>

	  		<p><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a></p>
	  		<%
	  	}

    } else {
%>
<p><a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a> to use the system.</p>
<%
    }
%>

  </body>
</html>