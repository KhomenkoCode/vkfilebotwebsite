package main.java.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.databasetables.RequestToBot;
import main.java.datamanage.RequestsDAOImpl;

/**
 * Servlet implementation class IndexPageServlet
 */
public class HistoryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HistoryPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestsDAOImpl requestsDAOImpl = new RequestsDAOImpl();
		RequestToBot requestToBot = new RequestToBot();
		List requestsFromDB = new ArrayList<RequestToBot>();
		Cookie[] cookies = request.getCookies();
		Long chatId = 0l;
		if (null != cookies)
			for (Cookie currentCookie : cookies)
				if (currentCookie.getName().equals("cid")) {
					chatId = Long.parseLong(currentCookie.getValue());
					break;
				}

		if (chatId == 0l) {
			response.sendRedirect("index");
			return;
		}
		
		String username = BotAuthorizationPageServlet.getUsernameByChatId(chatId);
		request.setAttribute("isAuthorized", 1);
		request.setAttribute("username", username);
		
		try {
			requestsFromDB = requestsDAOImpl.getRequestsByChatId(BigInteger.valueOf(chatId));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Vector<String> requests = new Vector<>();
		RequestToBot currentRequest;
		int numOfElements;
		for (numOfElements = 0; numOfElements < requestsFromDB.size(); numOfElements++) {
			currentRequest = (RequestToBot) requestsFromDB.get(numOfElements);

			requests.add(Long.toString((currentRequest.getDateTimeOfRequest().getTime())));
			requests.add(getFileTypePicture(currentRequest.getFileType()));
			requests.add(currentRequest.getSearchRequest());
			
		}
		
		if (numOfElements == 0)
			request.setAttribute("numOfElements", 0);
		else
			request.setAttribute("numOfElements", (numOfElements) * 3);
		
		request.setAttribute("requests", requests);

		response.setContentType("text/html");
		RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("/history.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getFileTypePicture(int fileTypeIndex) {
		String result = "";
		switch (fileTypeIndex) {
		case 1:
			result = "images/icons/text.png";
			break;
		case 2:
			result = "images/icons/archive.png";
			break;
		case 3:
			result = "images/icons/gif.png";
			break;
		case 4:
			result = "images/icons/image.png";
			break;
		case 5:
			result = "images/icons/music.png";
			break;
		case 6:
			result = "images/icons/video.png";
			break;
		case 8:
			result = "images/icons/book.png";
			break;
		case 9:
			result = "images/icons/all.png";
			break;
		default:
			result = "";
			break;
		}

		return result;
	}
}