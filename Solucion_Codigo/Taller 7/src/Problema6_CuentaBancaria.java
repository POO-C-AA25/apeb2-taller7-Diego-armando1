/***
 * El banco **UN BANCO** mantiene las cuentas de varios clientes. Los datos que describen a cada una de las cuentas consisten en el número 
 * de cuenta, el nombre del cliente y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. El método constructor 
 * debe aceptar como parámetros el _número de cuenta_ y el _nombre_. Debe proporcionarse métodos para depositar o retirar una cantidad de 
 * dinero y obtener el _balance actual_. 
 * El banco ofrece a sus clientes dos tipos de cuentas, una de **CHEQUES** y una de **AHORROS**. Una cuenta de cheques puede sobregirarse 
* _(el balance puede ser menor que cero)_, pero una cuenta de ahorros no. Al final de cada mes, se calcula el _interés_ sobre la cantidad 
* que tenga la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir cada uno de estos tipos de cuentas, 
* haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros debe proporcionar un método que sea invocado para calcular el 
* interés. Además, el banco está pensando en implementar una cuenta **PLATINO** que viene siendo similar a los otros dos tipos anteriores 
* de cuentas bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.
> [!Note]
> - Ud. debe implementar una clase de PRUEBA _(Clase de ejecución)_ desde la cual se pueda evidenciar el correcto funcionamiento de cada 
* clase con n clientes, y que además se pueda mostrar el balance _(estado de cuenta)_ para cada cliente. 
 */
import java.util.ArrayList;
public class Problema6_CuentaBancaria {
    public static void main(String[] args) {
        ArrayList<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
        cuentas.add(new Ahorros("001", "Diego Guaman"));
        cuentas.add(new Cheques("002", "Luis Guaman"));
        cuentas.add(new Platino("003", "Paul Alexander"));
        for (CuentaBancaria c : cuentas){
            c.depositar(1000.0);
        }
        cuentas.get(0).retirar(500.0);
        cuentas.get(1).retirar(2000.0);
        cuentas.get(2).retirar(200);
        ((Ahorros) cuentas.get(0)).calcularInteres();
        ((Platino) cuentas.get(2)).calcularInteres();
        for (CuentaBancaria c : cuentas){
            System.out.println(c);
        }
    }
}
class CuentaBancaria{
    public String numeroCuenta;
    public String nombreCliente;
    public double balance;
    public CuentaBancaria(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
    }
    public void depositar(double cantidad){
        if (cantidad > 0){
            this.balance += cantidad;
        }
    }
    public void retirar(double cantidad){
        this.balance -= cantidad;
    }
    public double getBalance(){
        return this.balance;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", nombreCliente=" + nombreCliente + ", balance=" + balance + '}';
    }
    
}
class Ahorros extends CuentaBancaria{
    public double tasaInteres = 0.03;
    public Ahorros(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }
    @Override
    public void retirar(double cantidad){
        if (cantidad <= balance){
            balance -= cantidad;
        }else{
            System.out.println("Fondos insuficientes");
        }
    }
    public void calcularInteres(){
        balance += (balance * tasaInteres);
    }
}
class Cheques extends CuentaBancaria{

    public Cheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }
    @Override
    public void retirar(double cantidad){
        balance -= cantidad;
    }
}
class Platino extends CuentaBancaria{
    public double tasaInteres = 0.10;

    public Platino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }
    public void calcularInteres(){
        balance += balance * tasaInteres;
    }
    @Override
    public void retirar(double cantidad){
        balance -= cantidad;
    }
}