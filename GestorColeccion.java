package logica;

import modelo.Autor;
import modelo.Libro;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorColeccion {

    private final List<Libro> coleccion;

    public GestorColeccion() {
        coleccion = new ArrayList<>();
        inicializarDatosDePrueba();
    }

    private void inicializarDatosDePrueba() {

        Autor gabo = new Autor(
                "Gabriel García Márquez",
                "06/03/1927",
                "Colombia",
                "No"
        );
        Libro libro1 = new Libro(
                "Cien años de soledad",
                "Realismo mágico",
                1967,
                new ArrayList<>(List.of(gabo))
        );

        Autor jane = new Autor(
                "Jane Austen",
                "16/12/1775",
                "Reino Unido",
                "No"
        );
        Libro libro2 = new Libro(
                "Orgullo y Prejuicio",
                "Novela romántica",
                1813,
                new ArrayList<>(List.of(jane))
        );

        coleccion.add(libro1);
        coleccion.add(libro2);
    }

    public void agregarLibro(Libro nuevoLibro) {
        coleccion.add(nuevoLibro);
    }

    public List<Libro> consultarLibrosPorAutor(String nombreAutor) {
        return coleccion.stream()
                .filter(libro
                        -> libro.getAutores().stream()
                        .anyMatch(autor -> autor.getNombre().equalsIgnoreCase(nombreAutor))
                )
                .collect(Collectors.toList());
    }

    public List<Libro> consultarLibrosPorGenero(String genero) {
        return coleccion.stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }

    public int getTamanoColeccion() {
        return coleccion.size();
    }
}
