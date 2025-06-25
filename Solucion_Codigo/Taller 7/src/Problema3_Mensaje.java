/***
 * Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes que se pueden enviar entre móviles, 
 **_mensajes de texto (SMS)_** y **_mensajes que contienen imágenes (MMS)_**. Por un lado, los mensajes de texto contienen 
 * un mensaje en caracteres que se desea enviar de un móvil a otro. Por otro lado, los mensajes que contienen imágenes almacenan 
 * información sobre la imagen a enviar, la cual se representará por el nombre del fichero que la contiene. Independientemente 
 * del tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. Ambos estarán definidos 
 * obligatoriamente por un número de móvil, y opcionalmente se podrá guardar información sobre su nombre. Además, los métodos 
 * enviarMensaje y visualizarMensaje deben estar definidos.
 * > [!Note]
 * > - Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor, con datos ficticios. 
 * @author Diego Guaman
 */
public class Problema3_Mensaje {

    public static void main(String[] args) {
        Contacto c1 = new Contacto("0903233818", "Luis");
        Contacto c3 = new Contacto("098338383", "Diego");
        Mensaje sms = new MensajeTexto(c1, c3, "Hola, donde estas");
        Mensaje mms = new MensajeImagen(c3, c1, "foto_vacaciones.jpg");
        System.out.println(sms.mandarMensaje());
        System.out.println(sms.visualizarMensaje());
        System.out.println("");
        System.out.println(mms.mandarMensaje());
        System.out.println(mms.visualizarMensaje());
    }
}

class Mensaje {

    public Contacto destino;
    public Contacto remitente;

    public Mensaje(Contacto destino, Contacto remitente) {
        this.destino = destino;
        this.remitente = remitente;
    }

    public String mandarMensaje() {
        return " ";
    }

    public String visualizarMensaje() {
        return " ";
    }

    @Override
    public String toString() {
        return "Mensaje de " + remitente + " a " + destino;
    }
}

class MensajeTexto extends Mensaje {

    public String mensaje;

    public MensajeTexto(Contacto remitente, Contacto destino, String mensaje) {
        super(destino, remitente);
        this.mensaje = mensaje;
    }

    @Override
    public String mandarMensaje() {
        return "Enviando SMS de " + remitente.nombre + " (" + remitente.numero + ") a " + destino.nombre + " (" + destino.numero + ")";
    }

    @Override
    public String visualizarMensaje() {
        return "SMS:\nDe: " + remitente.nombre + " (" + remitente.numero + ")\n"
                + "Para: " + destino.nombre + " (" + destino.numero + ")\n"
                + "Mensaje: " + mensaje;
    }
}

class MensajeImagen extends Mensaje {

    public String nombreArchivo;

    public MensajeImagen(Contacto remitente, Contacto destino, String nombreArchivo) {
        super(destino, remitente);
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public String mandarMensaje() {
        return "Enviando MMS de " + remitente.nombre + " (" + remitente.numero + ") a " + destino.nombre + " (" + destino.numero + ")";
    }

    @Override
    public String visualizarMensaje() {
        return "MMS:\nDe: " + remitente.nombre + " (" + remitente.numero + ")\n"
                + "Para: " + destino.nombre + " (" + destino.numero + ")\n"
                + "Imagen: " + nombreArchivo;
    }
}

class Contacto {

    public String nombre;
    public String numero;

    public Contacto(String numero) {
        this.numero = numero;
    }

    public Contacto(String numero, String nombre) {
        this.nombre = nombre;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return nombre + " (" + numero + ")";
    }
}
