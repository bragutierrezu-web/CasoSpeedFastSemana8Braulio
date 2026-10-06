package com.sfempresa.dao;

import com.sfempresa.modelo.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public void guardar(Entrega e) {
        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, e.getIdPedido());
            stmt.setInt(2, e.getIdRepartidor());
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setTime(4, Time.valueOf(LocalTime.now()));

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error al guardar entrega");
            ex.printStackTrace();
        }
    }
    public void create(Entrega entrega) {
        guardar(entrega);
    }

    public List<Entrega> readAll() {
        List<Entrega> lista = new ArrayList<>();
        String sql = "SELECT * FROM entrega";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entrega entrega = new Entrega(
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor")
                );

                lista.add(entrega);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar entregas");
            e.printStackTrace();
        }

        return lista;
    }

    public void update(Entrega entrega) {
        String sql = "UPDATE entrega SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setTime(4, Time.valueOf(LocalTime.now()));
            stmt.setInt(5, entrega.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar entrega");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM entrega WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar entrega");
            e.printStackTrace();
        }
    }
}
