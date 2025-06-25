/***
 * Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. Los datos personales de los trabajadores 
 * son nombre y apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:
 * - **Fijos Mensuales**: que cobran una cantidad fija al mes.
 * - **Comisionistas**: cobran un porcentaje fijo por las ventas que han realizado
 * - **Por Horas**: cobran un precio por cada una de las horas que han realizado durante el mes. El precio es fijo para las primeras 
 * 40 horas y es otro para las horas realizadas a partir de la 40 hora mensual.
 * - **Jefe**: cobra un sueldo fijo _(no hay que calcularlo)_. Cada empleado tiene obligatoriamente un jefe _(exceptuando los jefes 
 * que no tienen ninguno)_. El programa debe permitir dar de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina correspondiente al final de mes.
 * > [!Note]
 * > - Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor forma el escenario planteado. 
 * > - Para probar el diseño de clases, instancia en el clase de prueba Ejecutor _(la-s clase-s respectiva-s)_, con datos aleatorios. 
 * > - En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores. 
 * @author Diego Guaman
 */
public class Problema4_Trabajador {

    public static void main(String[] args) {
        Jefe jefe = new Jefe("Carlos Pérez", "Av. Quito", "1102345678", 2000.0);
        Fijos fijo1 = new Fijos(300.0, "David", "Loja", "2877627623", jefe);
        Comisionistas comisionista1 = new Comisionistas(2.0, "Luis", "calle Paris", "1893857483", jefe);
        comisionista1.registrarVentas(80);
        PorHora trabajador1 = new PorHora(50.0, 65.0, "Diego", "Estadio", "1836273628", jefe);
        trabajador1.registrarHoras(50);
        imprimirNomina(jefe);
        imprimirNomina(fijo1);
        imprimirNomina(comisionista1);
        imprimirNomina(trabajador1);
    }

    public static void imprimirNomina(Trabajador trabajador) {
        System.out.println(trabajador.toString());
        System.out.println("Sueldo a pagar: " + trabajador.calcularPago());
        System.out.println("--------------------------");
    }
}

class Trabajador {
    public String nombre;
    public String dirreccion;
    public String dni;
    public Jefe jefe;

    public Trabajador(String nombre, String dirreccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.dirreccion = dirreccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public double calcularPago() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
               ", DNI: " + dni +
               ", Direccion: " + dirreccion +
               (jefe != null ? ", Jefe: " + jefe.nombre : ", Jefe: Ninguno");
    }
}

class Fijos extends Trabajador {

    public double sueldoMensual;

    public Fijos(double sueldoMensual, String nombre, String dirreccion, String dni, Jefe jefe) {
        super(nombre, dirreccion, dni, jefe);
        this.sueldoMensual = sueldoMensual;
    }

    @Override
    public double calcularPago() {
        return sueldoMensual;
    }
}

class Comisionistas extends Trabajador {

    public int ventas;
    public double porcentaje;

    public Comisionistas(double porcentaje, String nombre, String dirreccion, String dni, Jefe jefe) {
        super(nombre, dirreccion, dni, jefe);
        this.porcentaje = porcentaje;
    }

    public void registrarVentas(int ventas) {
        this.ventas = ventas;
    }

    @Override
    public double calcularPago() {
        return ventas * porcentaje;
    }
}

class PorHora extends Trabajador {

    public int horas;
    public double pagoNormal;
    public double pagoExtra;

    public PorHora(double pagoNormal, double pagoExtra, String nombre, String dirreccion, String dni, Jefe jefe) {
        super(nombre, dirreccion, dni, jefe);
        this.pagoNormal = pagoNormal;
        this.pagoExtra = pagoExtra;
    }

    public void registrarHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public double calcularPago() {
        if (horas <= 40) {
            return pagoNormal * horas;
        } else {
            return 40 * pagoNormal + (horas - 40) * pagoExtra;
        }
    }
}

class Jefe extends Trabajador {

    public double sueldoFijo;

    public Jefe(String nombre, String dirreccion, String dni, double sueldoFijo) {
        super(nombre, dirreccion, dni, null);
        this.sueldoFijo = sueldoFijo;
    }

    @Override
    public double calcularPago() {
        return this.sueldoFijo;
    }
}
