package modelo;

import java.util.List;

public class Libro {

    private String titulo;
    private String genero;
    private int anoPublicacion;
    private List<Autor> autores;

    public Libro(String titulo, String genero, int anoPublicacion, List<Autor> autores) {
        if (autores == null || autores.isEmpty()) {
            throw new IllegalArgumentException("El libro debe tener al menos un autor");
        }
        this.titulo = titulo;
        this.genero = genero;
        this.anoPublicacion = anoPublicacion;
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    @Override
    public String toString() {
        return titulo + " (" + anoPublicacion + "), Género: " + genero;
    }
}
