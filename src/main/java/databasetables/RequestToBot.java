package main.java.databasetables;

import java.sql.Timestamp;
import java.math.BigInteger;

public class RequestToBot {
	long requestId;
	BigInteger chatId;
	String searchRequest;
	int fileType;
	Timestamp dateTimeOfRequest;
	
	public RequestToBot(){}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public BigInteger getChatId() {
		return chatId;
	}

	public void setChatId(BigInteger chatId) {
		this.chatId = chatId;
	}

	public String getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(String searchRequest) {
		this.searchRequest = searchRequest;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public Timestamp getDateTimeOfRequest() {
		return dateTimeOfRequest;
	}

	public void setDateTimeOfRequest(Timestamp dateTimeOfRequest) {
		this.dateTimeOfRequest = dateTimeOfRequest;
	}


	
	
}
