package org.rotlogix.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.cedarsoftware.util.io.JsonWriter;


public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get parameter values from form
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String location = request.getParameter("location");
		
		
		// Create new user object with parameter values
		User user = new User(age, sex, location);
		
		// Serialize user object into JSON then base64 encode the string
		String json = JsonWriter.objectToJson(user);
		String encoded = new String(Base64.encodeBase64(json.getBytes()));
		
		// Create a cookie with the encoded object as our value
		Cookie cookie = new Cookie("uid", encoded);
		response.addCookie(cookie);
		
		// Forward request to the validation Servlet
		response.sendRedirect(request.getContextPath() + "/Validate");
				
		
	}

}
