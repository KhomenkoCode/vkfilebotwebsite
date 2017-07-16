package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datamanage.JSONAuthorizationDataSerializingClass;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BotAuthorizationPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap<String,String> authData = new HashMap<String,String>();
 
    public BotAuthorizationPageServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		authData.put("vkf05C34D9" , "12121212" );
		
		if(request.getContentType() != null)
			if(request.getContentType().equals("application/json"))
				doPost(request, response);
			
		response.getWriter().append("All auth info\n");
		
		Iterator  iterator = authData.entrySet().iterator();
	    while(iterator.hasNext()) {
	    	Map.Entry mapEntry = (Map.Entry)iterator.next();
	        System.out.print("code: "+ mapEntry.getKey() + " & cid: " + mapEntry.getValue());
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		 
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JSONAuthorizationDataSerializingClass currentData = mapper.readValue(request.getReader(), JSONAuthorizationDataSerializingClass.class);
			authData.put(currentData.getCode(), currentData.getChatID());
		    out.println(authData.toString());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}
	
	public static HashMap<String,String> getAuthorizationMap(){
		return authData;
	}
}
