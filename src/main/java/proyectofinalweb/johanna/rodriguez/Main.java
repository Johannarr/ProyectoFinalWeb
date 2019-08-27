package proyectofinalweb.johanna.rodriguez;

import proyectofinalweb.johanna.rodriguez.encapsulacion.Lenguaje;
import proyectofinalweb.johanna.rodriguez.encapsulacion.TiempoExpiracion;
import proyectofinalweb.johanna.rodriguez.encapsulacion.Usuario;
import proyectofinalweb.johanna.rodriguez.servicios.BootstrapService;
import proyectofinalweb.johanna.rodriguez.servicios.LenguajeService;
import proyectofinalweb.johanna.rodriguez.servicios.TiempoExpiracionService;
import proyectofinalweb.johanna.rodriguez.servicios.UsuarioService;

import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {

        try {
            BootstrapService.getInstancia().startDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsuarioService usuarioService = new UsuarioService();
        TiempoExpiracionService tiempoExpiracionService = new TiempoExpiracionService();
        LenguajeService lenguajeService = new LenguajeService();

        usuarioService.crearAdministrador(new Usuario("admin", "Administrador", "1234", true));
        if (tiempoExpiracionService.buscarTodos().size() < 1){

            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.MINUTES, 10, "10 minutos"));
            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.MINUTES, 15, "15 minutos"));
            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.MINUTES, 20, "20 minutos"));
            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.MINUTES, 30, "30 minutos"));
            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.HOURS, 1, "1 hora"));
            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.DAYS, 1, "1 dia"));
            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.WEEKS, 1, "1 semana"));
            tiempoExpiracionService.crearTiempoExpiracion(new TiempoExpiracion(ChronoUnit.FOREVER, 0, "Para siempre"));
        }

        if (lenguajeService.buscarTodos().size() < 1){

            lenguajeService.crearLenguaje(new Lenguaje("apache"));
            lenguajeService.crearLenguaje(new Lenguaje("bash"));
            lenguajeService.crearLenguaje(new Lenguaje("coffeescript"));
            lenguajeService.crearLenguaje(new Lenguaje("cpp"));
            lenguajeService.crearLenguaje(new Lenguaje("cs"));
            lenguajeService.crearLenguaje(new Lenguaje("css"));
            lenguajeService.crearLenguaje(new Lenguaje("diff"));
            lenguajeService.crearLenguaje(new Lenguaje("http"));
            lenguajeService.crearLenguaje(new Lenguaje("ini"));
            lenguajeService.crearLenguaje(new Lenguaje("java"));
            lenguajeService.crearLenguaje(new Lenguaje("javascript"));
            lenguajeService.crearLenguaje(new Lenguaje("json"));
            lenguajeService.crearLenguaje(new Lenguaje("makefile"));
            lenguajeService.crearLenguaje(new Lenguaje("xml"));
            lenguajeService.crearLenguaje(new Lenguaje("markdown"));
            lenguajeService.crearLenguaje(new Lenguaje("nginx"));
            lenguajeService.crearLenguaje(new Lenguaje("objectivec"));
            lenguajeService.crearLenguaje(new Lenguaje("perl"));
            lenguajeService.crearLenguaje(new Lenguaje("php"));
            lenguajeService.crearLenguaje(new Lenguaje("properties"));
            lenguajeService.crearLenguaje(new Lenguaje("python"));
            lenguajeService.crearLenguaje(new Lenguaje("ruby"));
            lenguajeService.crearLenguaje(new Lenguaje("shell"));
            lenguajeService.crearLenguaje(new Lenguaje("sql"));
            lenguajeService.crearLenguaje(new Lenguaje("yaml"));
            lenguajeService.crearLenguaje(new Lenguaje("dart"));
        }

        Rutas.crearRutas();

    }
}