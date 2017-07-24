package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class IndexPageServlet
 */
public class HistoryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String mDatabaseURL = "jdbc:postgresql://ec2-54-246-108-119.eu-west-1.compute.amazonaws.com:5432/d7lqrtk9bonh0o?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private String mDatabaseUser = "flggsxcjmmkhtj";
    private String mDatabasePassword = "e35b60f396edbd05f2067e82317ca69f108e619962c64495f0113fd07ab146f6";
    private Connection mConnection = null;
    private static Statement mStatement = null;
    private static ResultSet theSet = null;
    
    private String mQuery = "SELECT * "
    		+ "FROM requests, data "
    		+ "WHERE requests.cid = data.cid";
 
    public HistoryPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		if(mConnection == null || mStatement == null)
			initDbConnection();
		
		try {
			Vector<String> requests = new Vector<>();
			
			theSet = mStatement.executeQuery(mQuery);
			while(theSet.next())
			{
				requests.add(Long.toString((theSet.getTimestamp("datetime").getTime())));
				requests.add(getFileTypePicture(theSet.getInt("fileType")));
				requests.add(theSet.getString("searchReq"));
				
				
				if(theSet.isLast())
					break;
			}
			request.setAttribute("numOfElements", requests.size()-1);
			request.setAttribute("requests", requests);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
	    RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("/history.jsp");
        dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void initDbConnection() {

    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		
		try {
			mConnection = DriverManager.getConnection(
					mDatabaseURL,
					mDatabaseUser,
					mDatabasePassword);
			mStatement = mConnection.createStatement();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	private String getFileTypePicture(int fileTypeIndex)
	{
		String result = "";
		switch(fileTypeIndex) {
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