package com.sfempresa.dao;

import com.sfempresa.modelo.*;
import java.sql.*;
import java.util.*;

public class RepartidorDAO {

    public void guardar(Repartidor r) {
        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNombre());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar repartidor");
            e.printStackTrace();
        }
    }

    public List<Repartidor> listarTodos() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor";

        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Repartidor(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar repartidores");
            e.printStackTrace();
        }

        return lista;
    }

    public void create(Repartidor repartidor) {
        guardar(repartidor);
    }

    public List<Repartidor> readAll() {
        return listarTodos();
    }

    public void update(Repartidor repartidor) {
        String sql = "UPDATE repartidor SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, repartidor.getNombre());
            stmt.setInt(2, repartidor.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar repartidor");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM repartidor WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar repartidor");
            e.printStackTrace();
        }
    }

}
