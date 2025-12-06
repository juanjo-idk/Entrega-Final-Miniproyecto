package gui;

import logica.GestorColeccion;
import modelo.Libro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaEjemplo extends JFrame {

    private GestorColeccion gestor;
    private JTextArea areaResultados;
    private PanelAgregarLibro panelAgregar;

    public VentanaEjemplo() {
        super("Colección de Libros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        gestor = new GestorColeccion();

        JTabbedPane tabbedPane = new JTabbedPane();

        panelAgregar = new PanelAgregarLibro(gestor);

        tabbedPane.addTab("Buscar y Consultar", crearPanelConsulta());
        tabbedPane.addTab("Agregar Libro/Autor", panelAgregar);

        add(tabbedPane);
        setVisible(true);
    }

    public VentanaEjemplo(GestorColeccion gestor) throws HeadlessException {
        this.gestor = gestor;
    }

    private JPanel crearPanelConsulta() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField campoBusqueda = new JTextField(20);
        JButton botonBuscar = new JButton("Buscar");

        panelBusqueda.add(new JLabel("Buscar por Género o Autor:"));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(botonBuscar);

        panel.add(panelBusqueda, BorderLayout.NORTH);

        areaResultados = new JTextArea(15, 50);
        areaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultados);
        panel.add(scrollPane, BorderLayout.CENTER);

        botonBuscar.addActionListener((ActionEvent e) -> {
            buscarLibros(campoBusqueda.getText().trim());
        });

        return panel;
    }

    private void buscarLibros(String textoBuscado) {
        areaResultados.setText("");
        if (textoBuscado.isEmpty()) {
            areaResultados.setText("Por favor, introduce un término de búsqueda.");
            return;
        }

        List<Libro> resultadosGenero = gestor.consultarLibrosPorGenero(textoBuscado);
        if (!resultadosGenero.isEmpty()) {
            areaResultados.append("--- Resultados de Búsqueda por Género: ---\n");
            for (Libro libro : resultadosGenero) {
                areaResultados.append(libro.getTitulo() + " (" + libro.getAnoPublicacion() + ") - Género: " + libro.getGenero() + "\n");
            }
        }

        List<Libro> resultadosAutor = gestor.consultarLibrosPorAutor(textoBuscado);
        if (!resultadosAutor.isEmpty()) {
            areaResultados.append("\n--- Resultados de Búsqueda por Autor: ---\n");
            for (Libro libro : resultadosAutor) {

                areaResultados.append(libro.getTitulo() + " (" + libro.getAnoPublicacion() + ") - Autor: " + libro.getAutores().get(0).getNombre() + "\n");
            }
        }

        if (resultadosGenero.isEmpty() && resultadosAutor.isEmpty()) {
            areaResultados.append("No se encontraron resultados para: " + textoBuscado + "\n");
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(VentanaEjemplo::new);
    }

    public GestorColeccion getGestor() {
        return gestor;
    }

    public void setGestor(GestorColeccion gestor) {
        this.gestor = gestor;
    }
}
