package com.sfempresa.vista;

import com.sfempresa.dao.RepartidorDAO;
import com.sfempresa.modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaRegistroRepartidor extends JPanel {

    private JTextField txtNombre;
    private JTable tabla;
    private DefaultTableModel modelo;
    private final RepartidorDAO dao = new RepartidorDAO();

    public VentanaRegistroRepartidor() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelRegistro = new JPanel(new FlowLayout());

        panelRegistro.add(new JLabel("Nombre:"));

        txtNombre = new JTextField(20);
        panelRegistro.add(txtNombre);

        JButton btnGuardar = new JButton("Guardar Repartidor");
        panelRegistro.add(btnGuardar);

        add(panelRegistro, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre"};
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

        btnGuardar.addActionListener(e -> guardarRepartidor());
        btnActualizar.addActionListener(e -> cargarRepartidores());
        btnEditar.addActionListener(e -> editarRepartidor());
        btnEliminar.addActionListener(e -> eliminarRepartidor());

        cargarRepartidores();
    }

    private void guardarRepartidor() {

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el nombre del repartidor"
            );
            return;
        }

        Repartidor repartidor = new Repartidor(nombre);
        dao.create(repartidor);

        JOptionPane.showMessageDialog(
                this,
                "Repartidor guardado correctamente"
        );

        txtNombre.setText("");
        cargarRepartidores();
    }

    private void cargarRepartidores() {

        modelo.setRowCount(0);

        List<Repartidor> repartidores = dao.readAll();

        for (Repartidor r : repartidores) {
            modelo.addRow(new Object[]{
                    r.getId(),
                    r.getNombre()
            });
        }
    }

    private void editarRepartidor() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un repartidor"
            );
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        String nombreActual = modelo.getValueAt(fila, 1).toString();

        String nuevoNombre = JOptionPane.showInputDialog(
                this,
                "Nombre:",
                nombreActual
        );

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            return;
        }

        Repartidor repartidor =
                new Repartidor(id, nuevoNombre.trim());

        dao.update(repartidor);
        cargarRepartidores();

        JOptionPane.showMessageDialog(
                this,
                "Repartidor actualizado correctamente"
        );
    }

    private void eliminarRepartidor() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un repartidor"
            );
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar este repartidor?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            dao.delete(id);
            cargarRepartidores();

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor eliminado correctamente"
            );
        }
    }
}