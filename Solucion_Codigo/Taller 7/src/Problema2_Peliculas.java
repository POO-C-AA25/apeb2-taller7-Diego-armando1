
/**
 * Un videoclub dispone de una serie de películas que pueden estar en DVD (con capacidad en Gb.)
 * o en VHS (una sola cinta por película y con cierto tipo de cinta magnetica). De las películas
 * interesa guardar el título, el autor, el año de edición y el idioma (o los idiomas, en caso de
 * DVD). El precio de alquiler de las películas varía en función del tipo de película. Las DVD
 * siempre son 10% mas caras que las de VHS.
 * Note
 * Analice los tipos de relación de las siguientes posibles clases: Pelicula, Dvd, Vhs, Soporte, etc, y justifique su diseño.
 * Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor (la-s clase-s respectiva-s), con datos aleatorios.
 * Los escenarios de prueba pueden darse para el alquiler de una o varias peliculas según la preferencia del usuario.
 *
 * @author Diego Guaman
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Problema2_Peliculas {

    public static void main(String[] args) {
        Pelicula pelicula1 = new Pelicula("La monja", "Rodrigo", 2023);
        Pelicula pelicula2 = new Pelicula("Stick", "Lucio", 2006);
        VHS vhs1 = new VHS("Español", pelicula1, 25.0);
        VHS vhs2 = new VHS("Español", pelicula2, 23.0);
        System.out.println(vhs1);
        System.out.println(vhs2);
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(pelicula1);
        peliculas.add(pelicula2);

        String[] idiomas = {"Español", "Inglés", "Francés"};
        DVD dvd1 = new DVD(idiomas, peliculas, 40.0);

        System.out.println("DVD:");
        for (int i = 0; i < dvd1.peliculas.size(); i++) {
            Pelicula peli = dvd1.peliculas.get(i);
            String idioma = (i < dvd1.idioma.length) ? dvd1.idioma[i] : "Idioma desconocido";
            double precioAlquiler = dvd1.calcularPrecioAlquiler();

            System.out.println(peli.toString() + " - Idioma: " + idioma + " - Precio alquiler: $" + precioAlquiler);
        }

    }
}

class Soporte {

    public double precioVenta;

    public Soporte(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        return "Soporte{" + "precioVenta=" + precioVenta + '}';
    }

}

class DVD extends Soporte {

    public String idioma[];
    public ArrayList<Pelicula> peliculas;

    public DVD(String[] idioma, ArrayList<Pelicula> peliculas, double precioVenta) {
        super(precioVenta);
        this.idioma = idioma;
        this.peliculas = peliculas;
    }

    public double calcularPrecioAlquiler() {
        return this.precioVenta += (this.precioVenta * 0.1);
    }

    @Override
    public String toString() {
        return "DVD{" + "idioma=" + Arrays.toString(idioma) + ", peliculas=" + peliculas + "} " + super.toString();
    }

}

class VHS extends Soporte {

    public String idioma;
    public Pelicula pelicula;

    public VHS(String idioma, Pelicula pelicula, double precioVenta) {
        super(precioVenta);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "VHS{" + "idioma=" + idioma + ", pelicula=" + pelicula + "} " + super.toString();
    }

}

class Pelicula {

    public String titulo;
    public String autor;
    public int fechaEdicion;

    public Pelicula(String titulo, String autor, int fechaEdicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.fechaEdicion = fechaEdicion;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", fechaEdicion=" + fechaEdicion + '}';
    }

}
