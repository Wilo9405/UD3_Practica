package DAO;

import Config.HibernateUtil;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class UserDao {

    // Crear un nuevo usuario
    public void createUser(User user) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }


    // Obtener el gasto promedio de un usuario por su id
    public User getAvtUser(int userId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "SELECT u, AVG(t.precio) " +
                    "FROM User u " +
                    "LEFT JOIN Ticket t ON u.id = t.user.id " +
                    "GROUP BY u.id";

            List<Object[]> results = session.createQuery(hql).getResultList();

            for (Object[] result : results) {
                User user = (User) result[0];
                Double avgSpending = (Double) result[1]; // El promedio devuelto por AVG es Double
//                BigDecimal avgSpendingBD = avgSpending != null ? BigDecimal.valueOf(avgSpending) : BigDecimal.ZERO;
//
//                user.setGastoPromedio(avgSpendingBD);  // Asignamos el valor de gastoPromedio
//                transaction = session.beginTransaction();
//                session.update(user);  // Esto persiste la actualización del gasto_promedio
//                transaction.commit();  // Confirmar la transacciónç

////                return user;
//            } else {
//                return null;
                if (avgSpending != null) {
                    user.setGastoPromedio(BigDecimal.valueOf(avgSpending));
                } else {
                    user.setGastoPromedio(BigDecimal.ZERO);
                }
               session.update(user);
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // En caso de error, hacer rollback
            e.printStackTrace();

        } finally {
            HibernateUtil.closeSession(session);  // Cerrar la sesión
        }
        return null;
    }


    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSession();
        try {
            return session.createQuery("FROM User", User.class).getResultList();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // Otras operaciones CRUD...
}
