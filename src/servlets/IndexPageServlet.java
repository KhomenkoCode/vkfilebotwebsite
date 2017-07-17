package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class IndexPageServlet
 */
public class IndexPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession(true);
		
		System.out.println(session.getId());
		
		String sessionId = sessionIdGenerator(session.getId());
		request.setAttribute("isAuthorized", 0);
		request.setAttribute("sessionId", sessionId);
		
		boolean isCIDFounded = false;
		if(null != cookies)
			for(Cookie currentCookie: cookies)
				if(currentCookie.getName().equals("cid")) {
					request.setAttribute("userCID", currentCookie.getValue());
					isCIDFounded = true;
				}

		if(!isCIDFounded)
			if(!addCIDFromHashMapToCookiesAndSetAttribute(request, response, sessionId))
				request.setAttribute("userCID", "");
	
		if(null != cookies)
			if(cookies.length == 1)
			{
				request.setAttribute("isAuthorized", 0);
				request.setAttribute("username",  "");
			}
			else { 
				request.setAttribute("isAuthorized", 1);
				//TODO: connection to db and getting username
				request.setAttribute("username",  "USERNAME");
			}
		
		response.setContentType("text/html");
	    RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//return true if CID was founded
	@SuppressWarnings("rawtypes")
	private boolean addCIDFromHashMapToCookiesAndSetAttribute(HttpServletRequest request, HttpServletResponse response, String sessionId) {
		
		HashMap<String,String> authorizationDataMap = BotAuthorizationPageServlet.getAuthorizationMap();
		Map.Entry mapEntry;
		Iterator  iterator = authorizationDataMap.entrySet().iterator();
	
		
	    while(iterator.hasNext()) {
	    	mapEntry = (Map.Entry)iterator.next();
	    	if(mapEntry.getKey().equals(sessionId))
	    	{
	    		Cookie cidCookie = new Cookie("cid", mapEntry.getValue().toString());
	       		cidCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
	       		response.addCookie(cidCookie);
	       		request.setAttribute("userCID", mapEntry.getValue().toString());
	       		return true;
	    	}
	    }
	    return false;
	}
	
	private String sessionIdGenerator(String JSESSIONID) {
		String sessionId = "vkf";
		return (sessionId + JSESSIONID.substring(JSESSIONID.length()-7, JSESSIONID.length()));
	}
}
