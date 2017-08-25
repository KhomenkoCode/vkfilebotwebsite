package main.java.servlets;

import java.io.IOException;
import java.util.Enumeration;
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

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import main.java.datamanage.HibernateUtil;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession(true);
		boolean isCIDFounded = false;

		String sessionId = sessionIdGenerator(session.getId());
		Long ChatId = 0l;
		
		if (request.getParameter("logout") != null) {
			logoutUserBySessionId(response, sessionId);
			return;
		} 
		
		request.setAttribute("sessionId", sessionId);

		if(null != cookies)
			for(Cookie currentCookie: cookies)
				if(currentCookie.getName().equals("cid")) {
					isCIDFounded = true;
					ChatId = Long.parseLong(currentCookie.getValue());
				}
		
		if (!isCIDFounded)
			ChatId = addCIDFromHashMapToCookiesAndSetAttribute(request, response, sessionId);
		
		if (null != ChatId)
			isCIDFounded = true;
		
		if (null != cookies)
			if (cookies.length <= 1 && !isCIDFounded) {
				request.setAttribute("isAuthorized", 0);
				request.setAttribute("username", "");
			} else {
				String username = BotAuthorizationPageServlet.getUsernameByChatId(ChatId);
				request.setAttribute("isAuthorized", 1);

				request.setAttribute("username", username);
			}

		response.setContentType("text/html");
		RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("/startpage.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	
	private Long addCIDFromHashMapToCookiesAndSetAttribute(HttpServletRequest request, HttpServletResponse response,
			String sessionId) {
 
		String cid = BotAuthorizationPageServlet.getChatIdentifierbySessionId(sessionId);
		if (null != cid) {
			Cookie cidCookie = new Cookie("cid", cid);
			cidCookie.setMaxAge(60 * 15); // Browser will store this
										  // cookie for 15 minutes
			response.addCookie(cidCookie);
			request.setAttribute("userCID", cid);
			return Long.parseLong(cid);
		}
		return null;
	}

	protected static String sessionIdGenerator(String JSESSIONID) {
		String sessionId = "vkf";
		return (sessionId + JSESSIONID.substring(JSESSIONID.length() - 7, JSESSIONID.length()));
	}

	protected static void logoutUserBySessionId(HttpServletResponse response, String sessionId) throws IOException {
		Cookie cookie = new Cookie("cid", "");
		cookie.setValue("");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		cookie.setComment("EXPIRING COOKIE at " + System.currentTimeMillis());
		response.addCookie(cookie);
		response.sendRedirect("index");
		BotAuthorizationPageServlet.deleteChatIdentifierBySessionId(sessionId);
	}
}
