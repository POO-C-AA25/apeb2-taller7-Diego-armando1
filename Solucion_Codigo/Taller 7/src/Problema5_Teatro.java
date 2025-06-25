
/** *
 * Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del teatro se divide en varias zonas,
 * cada una identificada por su nombre. Los datos de las zonas son los mostrados en la siguiente tabla:
 * | NOMBRE ZONA     | NÚMERO DE LOCALIDADES | PRECIO NORMA | PRECIO ABONADO  |
 * | --------------- | --------------------- | ------------ | --------------- |
 * | Principal       | 200                   | 25$          | 17.5$           |
 * | PalcoB          | 40                    | 70$          | 40$             |
 * | Central         | 400                   | 20$          | 14$             |
 * | Lateral         | 100                   | 15.5$        | 10$             |
 * Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el documento que justifique
 * que tiene algún tipo de descuento _(estudiante, abonado o pensionista)_. El vendedor sacará la entrada del tipo apropiado y de la zona
 * indicada, en el momento de la compra se asignará a la entrada un identificador _(un número entero)_ que permitirá la identificación de
 * la entrada en todas las operaciones que posteriormente se desee realizar con ella.
 * Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.
 * Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:
 * - **Entradas normales**: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
 * - **Entradas reducidas** (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
 * - **Entradas abonado**: su precio es el precio para abonados de la zona elegida.
 * La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.
 * > [!Note]
 * > Caso de uso “Vende entrada”:
 * >
 * > 1.	El vendedor elige la opción “vende entrada” e introduce la zona deseada, el nombre del espectador y el tipo (normal, abonado o
 * beneficiario de entrada reducida).
 * > 2.	Si la zona elegida no está completa, la aplicación genera una nueva entrada con los datos facilitados.
 * >
 * > 	- Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de uso sin generar la entrada.
 * > 	- Si la zona elegida está completa lo notifica y finaliza el caso de uno sin generar la entrada.
 * >
 * > 3.	La aplicación muestra el identificador y el precio de la entrada.
 * >
 * > Caso de uso “Consulta entrada”:
 * >
 * > 1.	El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
 * > 2.	La aplicación muestra los datos de la entrada: nombre del espectador, precio y nombre de la zona. Si no existe ninguna entrada
 * con ese identificador, lo notifica y finaliza el caso de uso
 */
import java.util.ArrayList;

public class Problema5_Teatro {

    public static ArrayList<Zona> zonas = new ArrayList<Zona>();
    public static ArrayList<EntradaNormal> entradasNormales = new ArrayList<>();
    static ArrayList<EntradaAbonado> entradasAbonados = new ArrayList<>();
    static ArrayList<EntradaReducida> entradasReducidas = new ArrayList<>();

    public static void main(String[] args) {
        zonas.add(new Zona("Principal", 200, 25.0, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        zonas.add(new Zona("Central", 400, 20.0, 14.0));
        zonas.add(new Zona("Lateral", 100, 15.5, 10.0));

        venderEntrada("Principal", "Diego", "abonado");
        venderEntrada("PalcoB", "Luis", "normal");
        venderEntrada("Central", "David", "reducida");
        venderEntrada("Lateral", "Paul", "normal");
        System.out.println("Entradas consultadas");
        consultarEntrada(1);
        consultarEntrada(2);
        consultarEntrada(3);
        consultarEntrada(4);
        consultarEntrada(99);
    }

    public static Zona buscarZona(String nombre) {
        for (Zona z : zonas) {
            if (z.nombre().equalsIgnoreCase(nombre));
            return z;
        }
        return null;
    }

    public static Entrada venderEntrada(String zonaNombre, String comprador, String tipo) {
        Zona zona = buscarZona(zonaNombre);
        if (zona == null) {
            System.out.println("Zona no encontrada");
            return null;
        }
        if (!zona.hayDisponibilidad()) {
            System.out.println("La zona esta completa");
        }
        if (tipo.equalsIgnoreCase("normal")) {
            EntradaNormal entrada = new EntradaNormal(comprador, zona);
            entradasNormales.add(entrada);
            System.out.println(entrada);
        } else if (tipo.equalsIgnoreCase("reducida")) {
            EntradaReducida entrada = new EntradaReducida(comprador, zona);
            entradasReducidas.add(entrada);
            System.out.println(entrada);
        } else if (tipo.equalsIgnoreCase("abonado")) {
            EntradaAbonado entrada = new EntradaAbonado(comprador, zona);
            entradasAbonados.add(entrada);
            System.out.println(entrada);
        } else {
            System.out.println("Tipo de entrada no válido");
        }
        return null;
    }

    public static void consultarEntrada(int id) {
        for (EntradaNormal e : entradasNormales) {
            if (e.id == id) {
                System.out.println(e);
                return;
            }
        }
        for (EntradaReducida e : entradasReducidas) {
            if (e.id == id) {
                System.out.println(e);
                return;
            }
        }
        for (EntradaAbonado e : entradasAbonados) {
            if (e.id == id) {
                System.out.println(e);
                return;
            }
        }
        System.out.println("No existe alguna entrada con ese id");
    }
}



class Entrada {

    public static int i = 1;
    public int id;
    public String comprador;
    public Zona zona;
    public double precio;

    public Entrada(String comprador, Zona zona) {
        this.id = i++;
        this.comprador = comprador;
        this.zona = zona;
    }

    public int id() {
        return this.id;
    }

    public String comprador() {
        return this.comprador;
    }

    public String nombreZona() {
        return zona.nombre;
    }

    public double precio() {
        return this.precio;
    }

    @Override
    public String toString() {
        return "Entrada{" + "id=" + id + ", comprador=" + comprador + ", zona=" + zona + ", precio=" + precio + '}';
    }

}

class EntradaNormal extends Entrada {

    public EntradaNormal(String comprador, Zona zona) {
        super(comprador, zona);
        this.precio = zona.precioNormal;
        zona.registrarVentas();
    }
}

class EntradaReducida extends Entrada {

    public EntradaReducida(String comprador, Zona zona) {
        super(comprador, zona);
        this.precio = zona.precioNormal * 0.85;
        zona.registrarVentas();
    }
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(String comprador, Zona zona) {
        super(comprador, zona);
        this.precio = zona.precioAbonado;
        zona.registrarVentas();
    }

}

class Zona {

    public String nombre;
    public int capacidad;
    public double precioNormal;
    public double precioAbonado;
    public int vendidas;

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.vendidas = 0;
    }

    public boolean hayDisponibilidad() {
        return this.vendidas < capacidad;
    }

    public void registrarVentas() {
        this.vendidas++;
    }

    public double precioNormal() {
        return this.precioNormal;
    }

    public double precioAbonado() {
        return this.precioAbonado;
    }

    public String nombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Zona{" + "nombre=" + nombre + ", capacidad=" + capacidad + ", precioNormal=" + precioNormal + ", precioAbonado=" + precioAbonado + ", vendidas=" + vendidas + '}';
    }

}
