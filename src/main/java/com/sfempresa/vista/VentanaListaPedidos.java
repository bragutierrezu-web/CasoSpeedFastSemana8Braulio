package com.sfempresa.vista;

import com.sfempresa.dao.PedidoDAO;
import com.sfempresa.modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    public VentanaListaPedidos() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnas = {"ID", "Dirección", "Tipo"};
        modelo = new DefaultTableModel(columnas, 0);

        tabla = new JTable(modelo);
        tabla.setRowHeight(25);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> cargarPedidos());
        btnEditar.addActionListener(e -> editarPedido());
        btnEliminar.addActionListener(e -> eliminarPedido());

        cargarPedidos();
    }

    private void cargarPedidos() {

        modelo.setRowCount(0);

        List<Pedido> pedidos = pedidoDAO.readAll();

        for (Pedido p : pedidos) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getDireccion(),
                    p.getTipo()
            });
        }
    }

    private void eliminarPedido() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un pedido de la tabla"
            );
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar este pedido?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            pedidoDAO.delete(id);
            cargarPedidos();

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido eliminado correctamente"
            );
        }
    }

    private void editarPedido() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un pedido de la tabla"
            );
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        String direccionActual = modelo.getValueAt(fila, 1).toString();
        String tipoActual = modelo.getValueAt(fila, 2).toString();

        String nuevaDireccion = JOptionPane.showInputDialog(
                this,
                "Dirección:",
                direccionActual
        );

        if (nuevaDireccion == null || nuevaDireccion.trim().isEmpty()) {
            return;
        }

        String[] tipos = {"Comida", "Encomienda", "Express"};

        String nuevoTipo = (String) JOptionPane.showInputDialog(
                this,
                "Tipo:",
                "Editar pedido",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tipos,
                tipoActual
        );

        if (nuevoTipo == null) {
            return;
        }

        Pedido pedido = new Pedido(
                id,
                nuevaDireccion.trim(),
                nuevoTipo
        );

        pedidoDAO.update(pedido);

        cargarPedidos();

        JOptionPane.showMessageDialog(
                this,
                "Pedido actualizado correctamente"
        );
    }
}
