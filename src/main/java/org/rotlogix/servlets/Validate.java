package org.rotlogix.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.cedarsoftware.util.io.JsonReader;

/**
 * Servlet implementation class Validate
 */
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] requestCookies = request.getCookies();
		String uid = null;
		
		// Check for the uid cookie and grab its value
		if (requestCookies != null) {
			for (int i = 0; i < requestCookies.length; i++) {
				if(requestCookies[i].getName().equals("uid")) {
					uid = requestCookies[i].getValue();
					break;
				}
			}
		}
		
		if(requestCookies == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		
		// Decode cookie and serialize back to user object
		String decoded = new String(Base64.decodeBase64(uid.getBytes()));
		User user = (User) JsonReader.jsonToJava(decoded);
		
		if(user.getIsAdmin() == false) {
			request.setAttribute("Admin", false);
		} else {
			request.setAttribute("Admin", true);
		}
		
		request.setAttribute("Age", user.getMyage());
		request.setAttribute("Sex", user.getMysex());
		request.setAttribute("Location", user.getMylocation());
		
		RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
		rd.forward(request, response);
		
		
	}


}
