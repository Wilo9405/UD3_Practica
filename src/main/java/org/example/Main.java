package org.example;


import DAO.UserDao;
import entities.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UserDao userDao = new UserDao();

            // Obtener todos los usuarios con su gasto promedio
            List<User> users = userDao.getAllUsers();

            // Imprimir los usuarios y su gasto promedio
            for (User user : users) {
                System.out.println("Usuario: " + user.getNombre() + ", Email: " + user.getEmail() +
                        ", Gasto Promedio: " + user.getGastoPromedio());
            }

            // Obtener un usuario específico por ID (ejemplo: usuario con id 1)
            User userWithSpending = userDao.getAvtUser(1);
            if (userWithSpending != null) {
                System.out.println("Usuario con ID 1: " + userWithSpending.getNombre() +
                        ", Gasto Promedio: " + userWithSpending.getGastoPromedio());
            } else {
                System.out.println("Usuario con ID 1 no encontrado o no tiene tickets.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

