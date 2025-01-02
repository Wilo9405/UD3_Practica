package DAO;

import Config.HibernateUtil;
import entities.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDao {

    // Crear un nuevo ticket
    public void createTicket(Ticket ticket) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // Obtener un ticket por su ID
    public Ticket getTicketById(int ticketId) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(Ticket.class, ticketId);
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // Obtener tickets por nombre de atracción
    public List<Ticket> getTicketsByAttraction(String attractionName) {
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM Ticket T WHERE T.nombreAtracción = :attractionName";
            return session.createQuery(hql, Ticket.class)
                    .setParameter("attractionName", attractionName)
                    .getResultList();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // Obtener tickets por ID de usuario
    public List<Ticket> getTicketsByUserId(int userId) {
        Session session = HibernateUtil.getSession();
        try {
            return session.createQuery("FROM Ticket t WHERE t.user.id = :userId", Ticket.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // Obtener todos los tickets
    public List<Ticket> getAllTickets() {
        Session session = HibernateUtil.getSession();
        try {
            return session.createQuery("FROM Ticket", Ticket.class).getResultList();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // Actualizar un ticket existente
    public void updateTicket(Ticket ticket) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // Eliminar un ticket por su ID
    public void deleteTicket(int ticketId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, ticketId);
            if (ticket != null) {
                session.delete(ticket);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}
