package proyectofinalweb.johanna.rodriguez;

import freemarker.template.Configuration;
import freemarker.template.Version;
import org.jasypt.util.text.StrongTextEncryptor;
import proyectofinalweb.johanna.rodriguez.encapsulacion.Documento;
import proyectofinalweb.johanna.rodriguez.encapsulacion.Lenguaje;
import proyectofinalweb.johanna.rodriguez.encapsulacion.TiempoExpiracion;
import proyectofinalweb.johanna.rodriguez.encapsulacion.Usuario;
import proyectofinalweb.johanna.rodriguez.encapsulacion.enums.TipoExposicion;
import proyectofinalweb.johanna.rodriguez.servicios.*;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

public class Rutas {

    private static Usuario usuarioSesion;


    public static void crearRutas() {


        staticFiles.location("/publico");

        final Configuration configuration = new Configuration(new Version(2, 3, 23));
        configuration.setClassForTemplateLoading(Main.class, "/publico");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        TiempoExpiracionService tiempoExpiracionService = new TiempoExpiracionService();
        DocumentoService documentoService = new DocumentoService();
        LenguajeService lenguajeService = new LenguajeService();
        UsuarioService usuarioService = new UsuarioService();


        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            List<Lenguaje> lenguajes = new ArrayList<>(lenguajeService.buscarTodos());

            Collections.sort(lenguajes);


            attributes.put("tiempoExpiracion", tiempoExpiracionService.buscarTodos());
            attributes.put("lenguajes", lenguajes);
            attributes.put("usuario", usuarioSesion);

            List<TipoExposicion> tipoExposicions = new ArrayList<>();
            tipoExposicions.add(TipoExposicion.PUBLICO);
            tipoExposicions.add(TipoExposicion.PRIVADO);
            tipoExposicions.add(TipoExposicion.SINLISTAR);

            attributes.put("tipoExposicion", tipoExposicions);

            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);

        get("/login", (request, response) -> new ModelAndView(null, "login.ftl"), freeMarkerEngine);

        get("/registro", (request, response) -> new ModelAndView(null, "registro.ftl"), freeMarkerEngine);
        get("/usuarios", (request, response) -> {

                    Map<String, Object> attributes = new HashMap<>();

                    attributes.put("usuarios", usuarioService.findAll());
                    return new ModelAndView(attributes, "usuarios.ftl");
                }

                , freeMarkerEngine);

        get("/top", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("documentos", documentoService.topDocumentos());
            attributes.put("usuario", usuarioSesion);

            return new ModelAndView(attributes, "top.ftl");
        }, freeMarkerEngine);

        get("/historial", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("documentos", usuarioSesion.getDocumentos());
            attributes.put("usuario", usuarioSesion);

            return new ModelAndView(attributes, "historial.ftl");
        }, freeMarkerEngine);

        get("/documento/:url", (request, response) -> {

            String direccion = request.params("url");
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("usuario", usuarioSesion);

            Documento documento;
            documento = documentoService.buscarPorDireccion(direccion);

            if (usuarioSesion == null && documento.getTipoExposicion().equals(TipoExposicion.PRIVADO) || documento.getEliminado()) {

                response.redirect("/");
            }

            int visitas = documento.getVisitas();
            documento.setVisitas(visitas + 1);
            documentoService.editar(documento);

            System.out.println("documento " + documento.getNombre());
            attributes.put("documento", documento);

            return new ModelAndView(attributes, "documento.ftl");
        }, freeMarkerEngine);


        get("/api/documentos", (request, response) -> documentoService.findAll(), JsonUtilidades.json());

        get("/salir", (request, response) ->
        {
            Session sesion = request.session(true);
            sesion.invalidate();

            response.redirect("/");

            return null;
        });

        post("/crearDocumento", (request, response) -> {


            String nombre = request.queryParams("nombre");
            String contenido = request.queryParams("contenido");
            String tipoExposicionSeleccionado = request.queryParams("exposicion");

            Long lenguajeSeleccionado = Long.valueOf(Integer.valueOf(request.queryParams("lenguaje")));
            Long tiempoExpiracionSeleccionado = (long) Integer.parseInt(request.queryParams("tiempoExpiracion"));

            TiempoExpiracion tiempoExpiracion = tiempoExpiracionService.buscarPorId(tiempoExpiracionSeleccionado);
            Lenguaje lenguaje = lenguajeService.buscarPorId(lenguajeSeleccionado);

            String uuid = UUID.randomUUID().toString();
            Documento documento = new Documento(tiempoExpiracion, nombre, TipoExposicion.valueOf(tipoExposicionSeleccionado), contenido, new Date(), false, uuid, lenguaje);
            documentoService.crearDocumento(documento);

            if (usuarioSesion != null) {
                usuarioSesion.getDocumentos().add(documento);
                usuarioService.editar(usuarioSesion);
            }

            LocalDateTime localDate = LocalDateTime.now();
            Duration duration = Duration.of(tiempoExpiracion.getTiempo(), tiempoExpiracion.getUnit());

            LocalDateTime localDate1 = localDate.plusSeconds(duration.getSeconds());
            System.out.println("ahora " + localDate + " despues " + localDate1 + " duracion " + duration);
            if (tiempoExpiracion.getUnit() != ChronoUnit.FOREVER) {

                long delay = ChronoUnit.MILLIS.between(localDate, localDate1);
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                scheduler.schedule(() -> {

                    documento.setEliminado(true);
                    documentoService.editar(documento);

                }, delay, TimeUnit.MILLISECONDS);
            }

            response.redirect("/documento/" + documento.getDireccion());

            return "creado";
        });

        post("/validarLogIn", (request, response) -> {

            String user = request.queryParams("usuario");
            String contra = request.queryParams("password");

            System.out.println("Usuario " + user + " contra: " + contra);

            Usuario usuario = usuarioService.loginUsuario(user, contra);
            usuarioSesion = usuario;

            try {
                if (usuario != null) {
                    request.session(true);
                    request.session().attribute("sesionUsuario", usuario);

                    if (request.queryParams("guardarSesion") != null) {
                        String sesionID = request.session().id();
                        StrongTextEncryptor encriptador = new StrongTextEncryptor();
                        encriptador.setPassword("final-web");
                        String sesionIDEncriptado = encriptador.encrypt(sesionID);

                        System.out.println("Sesión sin encriptar: " + sesionID);
                        System.out.println("Sesión encriptada: " + sesionIDEncriptado);

                        response.cookie("/", "sesionSemanal", sesionIDEncriptado, 604800, false);
                    }

                    response.redirect("/");
                } else {
                    response.redirect("/login");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "OK";
        });



        post("/registro", (request, response) -> {

            String nombre = request.queryParams("nombre");
            String user = request.queryParams("usuario");
            String contra = request.queryParams("password");


            long id = usuarioService.getInstancia().findAll().get(usuarioService.getInstancia().findAll().size() - 1).getId() + 1;

            Usuario usuario = new Usuario(user, nombre, contra, false);
            usuarioService.getInstancia().crear(usuario);

            response.redirect("/");

            return null;


        });


    }


}