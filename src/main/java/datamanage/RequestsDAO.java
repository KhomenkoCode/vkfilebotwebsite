package main.java.datamanage;

import main.java.databasetables.RequestToBot;

import java.util.Collection;
import java.util.List;
import java.math.BigInteger;
import java.sql.SQLException;

public interface RequestsDAO {
  public void addRequest(RequestToBot request) throws SQLException;
  public void updateRequest(Long request_id, RequestToBot request) throws SQLException;
  public RequestToBot getRequestById(Long request_id) throws SQLException;
  public Collection getAllRequests() throws SQLException;
  public void deleteRequest(RequestToBot request) throws SQLException;
  public List getRequestsByChatId(BigInteger chatId) throws SQLException;
}
