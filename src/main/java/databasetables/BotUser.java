package main.java.databasetables;

import java.util.HashSet;
import java.util.Set;
import java.math.BigInteger;
import java.sql.Timestamp;

public class BotUser {
	
	long userId;
	BigInteger chatId;
	String firstName;
	String lastName;
	Timestamp dateTimeOfLastJoin;
	
	//Needs to continue bot's work with user 
	//when user asked a question to bot and it was restarted without giving him a file. 
	String lastQuestion;
	int stepOfTheLastQuestion;
	////
	public Timestamp getDateTimeOfLastJoin() {
		return dateTimeOfLastJoin;
	}

	public void setDateTimeOfLastJoin(Timestamp dateTimeOfLastJoin) {
		this.dateTimeOfLastJoin = dateTimeOfLastJoin;
	}

	private Set requests = new HashSet();

	BotUser(){}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public BigInteger getChatId() {
		return chatId;
	}
	public void setChatId(BigInteger chatId) {
		this.chatId = chatId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastQuestion() {
		return lastQuestion;
	}
	public void setLastQuestion(String lastQuestion) {
		this.lastQuestion = lastQuestion;
	}
	public int getStepOfTheLastQuestion() {
		return stepOfTheLastQuestion;
	}
	public void setStepOfTheLastQuestion(int stepOfTheLastQuestion) {
		this.stepOfTheLastQuestion = stepOfTheLastQuestion;
	}
	
	public Set getRequests() {
		return requests;
	}

	public void setRequests(Set requests) {
		this.requests = requests;
	}
	
}
