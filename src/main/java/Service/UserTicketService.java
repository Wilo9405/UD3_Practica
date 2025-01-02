package Service;

import DAO.TicketDao;
import DAO.UserDao;
import entities.Ticket;
import entities.User;

import java.math.BigDecimal;
import java.util.List;

public class UserTicketService {
    private final UserDao userDao;
    private final TicketDao ticketDao;

    // Constructor que recibe las dependencias de UserDao y TicketDao
    public UserTicketService(UserDao userDao, TicketDao ticketDao) {
        this.userDao = userDao;
        this.ticketDao = ticketDao;
    }

    // Obtener tickets de un usuario específico
    public List<Ticket> getTicketsByUserId(int userId) {
        return ticketDao.getTicketsByUserId(userId);  // Llamada a ticketDao
    }

    // Obtener tickets de una atracción específica
    public List<Ticket> getTicketsByAttraction(String attractionName) {
        return ticketDao.getTicketsByAttraction(attractionName);
    }

    // Obtener el gasto promedio de un usuario desde la base de datos
    public BigDecimal getAverageSpendingByUser(int userId) {
        // Obtener el gasto promedio desde la base de datos
        return userDao.getAvtUser(userId).getGastoPromedio();
    }

    // Crear un usuario con tickets asociados
    public void createUserWithTickets(String nombre, String email, List<Ticket> tickets) {
        User user = new User();
        user.setNombre(nombre);
        user.setEmail(email);

        // Asociar los tickets al usuario
        for (Ticket ticket : tickets) {
            ticket.setUser(user);
            ticketDao.createTicket(ticket); // Guardar ticket
        }
        userDao.createUser(user); // Guardar usuario
    }

    // Crear usuarios adicionales con tickets asociados
    public void createAdditionalUsers() {
        // Crear tickets para los usuarios
        Ticket ticket1 = new Ticket();
        ticket1.setNombreAtracción("Montaña Rusa");
        ticket1.setPrecio(BigDecimal.valueOf(25.50));

        Ticket ticket2 = new Ticket();
        ticket2.setNombreAtracción("Casa del Terror");
        ticket2.setPrecio(BigDecimal.valueOf(15.50));

        Ticket ticket3 = new Ticket();
        ticket3.setNombreAtracción("Rueda de la Fortuna");
        ticket3.setPrecio(BigDecimal.valueOf(20.00));

        Ticket ticket4 = new Ticket();
        ticket4.setNombreAtracción("Sillas Voladoras");
        ticket4.setPrecio(BigDecimal.valueOf(12.00));

        // Crear los usuarios con sus respectivos tickets
        createUserWithTickets("Juan", "juan@example.com", List.of(ticket1, ticket2));
        createUserWithTickets("Maria", "maria@example.com", List.of(ticket3, ticket4));
        createUserWithTickets("Carlos", "carlos@example.com", List.of(ticket1, ticket4));
    }

    // Método para mostrar el gasto promedio de todos los usuarios
    public void showAllUsersWithAverageSpending() {
        // Obtener todos los usuarios
        List<User> users = userDao.getAllUsers();

        // Imprimir los datos de cada usuario con su gasto promedio
        for (User user : users) {
            BigDecimal avgSpending = getAverageSpendingByUser(user.getId());
            System.out.println("Usuario: " + user.getNombre() + ", Gasto Promedio: " + avgSpending);
        }
    }
}
