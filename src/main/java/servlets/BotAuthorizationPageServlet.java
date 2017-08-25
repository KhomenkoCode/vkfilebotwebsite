package main.java.servlets;

import main.java.databasetables.BotUser;
import main.java.datamanage.JSONAuthorizationDataSerializingClass;
import main.java.datamanage.UserDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BotAuthorizationPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Map < [Session Identifier (generated from JSESSIONID)] , [Telegram's chat identifier (cid)] >
	private static HashMap<String, String> authData = new HashMap<String, String>();

	// Map < [chat identifier], [Telegram Username] >
	private static HashMap<String, String> usernameMap = new HashMap<String, String>();
	
	public BotAuthorizationPageServlet() {
		super();
	}

	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getContentType() != null)
			if (request.getContentType().equals("application/json"))
				doPost(request, response);

		response.getWriter().append("All auth info\n");

		Iterator iterator = authData.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			System.out.print("code: " + mapEntry.getKey() + " & cid: " + mapEntry.getValue());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		ObjectMapper mapper = new ObjectMapper();

		try {
			JSONAuthorizationDataSerializingClass currentData = mapper.readValue(request.getReader(),
					JSONAuthorizationDataSerializingClass.class);
			authData.put(currentData.getCode(), currentData.getChatID());
			out.println(authData.toString());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close();
	}

	@SuppressWarnings("rawtypes")
	protected static String getChatIdentifierbySessionId(String sessionId) {
		Map.Entry<String, String> mapEntry;
		Iterator iterator = authData.entrySet().iterator();

		while (iterator.hasNext()) {
			mapEntry = (Map.Entry<String, String>) iterator.next();
			if (mapEntry.getKey().equals(sessionId)) {
				return mapEntry.getValue();
			}
		}
		return null;
	}
	
	protected static boolean deleteChatIdentifierBySessionId(String sessionId) {
		
		String chatId = authData.get(sessionId);
		
		if(chatId != null)
		{
			usernameMap.remove(chatId);
		}
		
		if (null != authData.remove(sessionId))
			return true;
		return false;
	}
	
	
	protected static String getUsernameByChatId(Long chatId) {
		String username;
	
		username = usernameMap.get(chatId);
		if(username == null && chatId != null)
		{
			try {
				UserDAOImpl userDAO = new UserDAOImpl();
				BotUser user = userDAO.getUserByChatId(BigInteger.valueOf(chatId));
				if(user != null)
				{
					username = user.getFirstName() + " " + user.getLastName();
					if(username != null || username != "")
						usernameMap.put(chatId.toString(), username);
				}
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		if(username == null) username = "";
		return username;
	}
}
