package com.sfempresa.vista;

import com.sfempresa.dao.EntregaDAO;
import com.sfempresa.dao.PedidoDAO;
import com.sfempresa.dao.RepartidorDAO;
import com.sfempresa.modelo.Entrega;
import com.sfempresa.modelo.Pedido;
import com.sfempresa.modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaEntregas extends JPanel {

    private JComboBox<String> comboPedidos;
    private JComboBox<String> comboRepartidores;
    private JTable tabla;
    private DefaultTableModel modelo;

    private final EntregaDAO entregaDAO = new EntregaDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final RepartidorDAO repartidorDAO = new RepartidorDAO();

    private List<Pedido> pedidos;
    private List<Repartidor> repartidores;

    public VentanaEntregas() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelRegistro = new JPanel(new FlowLayout());

        panelRegistro.add(new JLabel("Pedido:"));

        comboPedidos = new JComboBox<>();
        panelRegistro.add(comboPedidos);

        panelRegistro.add(new JLabel("Repartidor:"));

        comboRepartidores = new JComboBox<>();
        panelRegistro.add(comboRepartidores);

        JButton btnGuardar = new JButton("Guardar Entrega");
        panelRegistro.add(btnGuardar);

        add(panelRegistro, BorderLayout.NORTH);

        String[] columnas = {"ID", "ID Pedido", "ID Repartidor"};
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

        btnGuardar.addActionListener(e -> guardarEntrega());

        btnActualizar.addActionListener(e -> {
            cargarCombos();
            cargarEntregas();
        });

        btnEditar.addActionListener(e -> editarEntrega());
        btnEliminar.addActionListener(e -> eliminarEntrega());

        cargarCombos();
        cargarEntregas();
    }

    private void cargarCombos() {

        comboPedidos.removeAllItems();
        comboRepartidores.removeAllItems();

        pedidos = pedidoDAO.readAll();
        repartidores = repartidorDAO.readAll();

        for (Pedido p : pedidos) {
            comboPedidos.addItem(
                    p.getId() + " - " + p.getDireccion()
            );
        }

        for (Repartidor r : repartidores) {
            comboRepartidores.addItem(
                    r.getId() + " - " + r.getNombre()
            );
        }
    }

    private void cargarEntregas() {

        modelo.setRowCount(0);

        List<Entrega> entregas = entregaDAO.readAll();

        for (Entrega e : entregas) {
            modelo.addRow(new Object[]{
                    e.getId(),
                    e.getIdPedido(),
                    e.getIdRepartidor()
            });
        }
    }

    private void guardarEntrega() {

        int indicePedido = comboPedidos.getSelectedIndex();
        int indiceRepartidor = comboRepartidores.getSelectedIndex();

        if (indicePedido == -1 || indiceRepartidor == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido y un repartidor"
            );
            return;
        }

        Pedido pedido = pedidos.get(indicePedido);
        Repartidor repartidor = repartidores.get(indiceRepartidor);

        Entrega entrega = new Entrega(
                0,
                pedido.getId(),
                repartidor.getId()
        );

        entregaDAO.create(entrega);
        cargarEntregas();

        JOptionPane.showMessageDialog(
                this,
                "Entrega guardada correctamente"
        );
    }

    private void editarEntrega() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una entrega"
            );
            return;
        }

        if (comboPedidos.getSelectedIndex() == -1 ||
                comboRepartidores.getSelectedIndex() == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido y un repartidor"
            );
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        Pedido pedido =
                pedidos.get(comboPedidos.getSelectedIndex());

        Repartidor repartidor =
                repartidores.get(comboRepartidores.getSelectedIndex());

        Entrega entrega = new Entrega(
                id,
                pedido.getId(),
                repartidor.getId()
        );

        entregaDAO.update(entrega);
        cargarEntregas();

        JOptionPane.showMessageDialog(
                this,
                "Entrega actualizada correctamente"
        );
    }

    private void eliminarEntrega() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una entrega"
            );
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar esta entrega?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            entregaDAO.delete(id);
            cargarEntregas();

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega eliminada correctamente"
            );
        }
    }
}
