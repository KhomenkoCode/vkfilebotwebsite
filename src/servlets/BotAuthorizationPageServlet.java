package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datamanage.JSONAuthorizationDataSerializingClass;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BotAuthorizationPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<JSONAuthorizationDataSerializingClass> authData = new ArrayList<>();
 
    public BotAuthorizationPageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getContentType().equals("application/json"))
			doPost(request, response);
			
		response.getWriter().append("All auth info");
		
		if(!authData.isEmpty()) {
			for(JSONAuthorizationDataSerializingClass authorizationData : authData)
				response.getWriter().append(authorizationData.toString()).append("<hr/>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		 
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
			// read from file, convert it to user class
			JSONAuthorizationDataSerializingClass currentData = mapper.readValue(request.getReader(), JSONAuthorizationDataSerializingClass.class);
			authData.add(currentData);
			
			// display to console
		    out.println(authData.toString());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}
	
}
