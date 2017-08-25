package main.java.datamanage;


import main.java.databasetables.RequestToBot;
import main.java.datamanage.HibernateUtil;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.Query;

@SuppressWarnings("deprecation")
public class RequestsDAOImpl implements RequestsDAO {

	public void addRequest(RequestToBot request) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(request);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
	}

	public void updateRequest(Long request_id, RequestToBot request) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(request);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public RequestToBot getRequestById(Long request_id) throws SQLException {
		Session session = null;
		RequestToBot request = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();
			request = (RequestToBot) session.load(RequestToBot.class, request_id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return request;
	}

	public Collection getAllRequests() throws SQLException {
		Session session = null;
		List requests = new ArrayList<RequestToBot>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			requests = session.createCriteria(RequestToBot.class).list();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return requests;
	}

	public void deleteRequest(RequestToBot request) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(request);
			session.getTransaction().commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List getRequestsByChatId(BigInteger chatId) throws SQLException {
		Session session = null;
		List requests = new ArrayList<RequestToBot>();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery(" from main.java.databasetables.RequestToBot r" + 
					" where r.chatId = :cid").setParameter("cid", chatId);
			requests = query.list();
			session.getTransaction().commit();

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return requests;
	}

}