package gui;

import logica.GestorColeccion;
import modelo.Autor;
import modelo.Libro;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class PanelAgregarLibro extends JPanel {

    private GestorColeccion gestor;
    private JTextField txtTitulo;
    private JTextField txtGenero;
    private JTextField txtAnio;
    private JTextField txtAutorNombre;
    private JTextField txtAutorPais;

    public PanelAgregarLibro(GestorColeccion gestor) {
        this.gestor = gestor;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Libro a la Colección"));

        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        txtTitulo = new JTextField(20);
        txtGenero = new JTextField(20);
        txtAnio = new JTextField(4);
        txtAutorNombre = new JTextField(20);
        txtAutorPais = new JTextField(20);
        JButton btnAgregarLibro = new JButton("Agregar Libro");

        panelFormulario.add(new JLabel("Título:"));
        panelFormulario.add(txtTitulo);
        panelFormulario.add(new JLabel("Género:"));
        panelFormulario.add(txtGenero);
        panelFormulario.add(new JLabel("Año Publicación:"));
        panelFormulario.add(txtAnio);
        panelFormulario.add(new JLabel("--- Datos del Autor Principal ---"));
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(new JLabel("Nombre Autor:"));
        panelFormulario.add(txtAutorNombre);
        panelFormulario.add(new JLabel("País Autor:"));
        panelFormulario.add(txtAutorPais);

        btnAgregarLibro.addActionListener(e -> agregarNuevoLibro());

        add(panelFormulario, BorderLayout.NORTH);
        add(btnAgregarLibro, BorderLayout.SOUTH);
    }

    @SuppressWarnings("UseSpecificCatch")
    private void agregarNuevoLibro() {
        try {
            if (txtTitulo.getText().trim().isEmpty() || txtAutorNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Título y Nombre de Autor son obligatorios.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Autor nuevoAutor = new Autor(
                    txtAutorNombre.getText().trim(),
                    txtAutorPais.getText().trim(), "Colombia", "No"
            );

            int anio = 0;
            try {
                anio = Integer.parseInt(txtAnio.getText().trim());
            } catch (NumberFormatException ex) {
            }

            Libro nuevoLibro = new Libro(
                    txtTitulo.getText().trim(),
                    txtGenero.getText().trim(),
                    anio,
                    new ArrayList<>(List.of(nuevoAutor))
            );

            gestor.agregarLibro(nuevoLibro);

            txtTitulo.setText("");
            txtGenero.setText("");
            txtAnio.setText("");
            txtAutorNombre.setText("");
            txtAutorPais.setText("");

            JOptionPane.showMessageDialog(this,
                    "¡Libro agregado! La colección ahora tiene " + gestor.getTamanoColeccion() + " libros.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al agregar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public GestorColeccion getGestor() {
        return gestor;
    }

    public void setGestor(GestorColeccion gestor) {
        this.gestor = gestor;
    }
}
