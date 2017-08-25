package main.java.datamanage;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import main.java.databasetables.BotUser;
import main.java.databasetables.RequestToBot;

public class UserDAOImpl implements UserDAO {

	public BotUser getUserByChatId(BigInteger chatId) throws SQLException {
		Session session = null;
		List user = new ArrayList<BotUser>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery(" from main.java.databasetables.BotUser r" + " where r.chatId = :cid")
					.setParameter("cid", chatId);
			user = query.list();
			session.getTransaction().commit();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
		if(user.isEmpty())
			return null;
			
		return (BotUser) user.get(0);
	}
}
