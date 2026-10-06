package com.sfempresa.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Clase encargada de gestionar la conexión JDBC con la base de datos MySQL.
 */
public class ConexionBD {

    private static String URL;
    private static String USER;
    private static String PASSWORD;


    static {
        try {

            Properties props = new Properties();
            props.load(new FileInputStream("src/config/db.properties"));


            URL = props.getProperty("url");
            USER = props.getProperty("user");
            PASSWORD = props.getProperty("password");

        } catch (IOException e) {
            System.out.println("Error al leer archivo db.properties");
            e.printStackTrace();
        }
    }

    /**
     * Retorna una conexión activa hacia la base de datos.
     *
     * @return Connection objeto de conexión JDBC
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
