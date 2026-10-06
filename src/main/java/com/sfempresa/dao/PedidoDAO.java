package com.sfempresa.dao;

import com.sfempresa.modelo.*;
import java.sql.*;
import java.util.*;

public class PedidoDAO {

    public void guardar(Pedido pedido) {
        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getDireccion());
            stmt.setString(2, pedido.getTipo());
            stmt.setString(3, "PENDIENTE");

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar pedido");
            e.printStackTrace();
        }
    }

    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getString("tipo")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos");
            e.printStackTrace();
        }

        return lista;
    }

    public void update(Pedido pedido) {
        String sql = "UPDATE pedido SET direccion = ?, tipo = ? WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getDireccion());
            stmt.setString(2, pedido.getTipo());
            stmt.setInt(3, pedido.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido");
            e.printStackTrace();
        }
    }

    public void create(Pedido pedido) {
        guardar(pedido);
    }

    public List<Pedido> readAll() {
        return listarTodos();
    }
}
