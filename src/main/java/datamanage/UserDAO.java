package main.java.datamanage;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import main.java.databasetables.BotUser;

public interface UserDAO {
	public BotUser getUserByChatId(BigInteger chatId) throws SQLException ;
}
