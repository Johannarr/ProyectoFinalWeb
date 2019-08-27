package proyectofinalweb.johanna.rodriguez.servicios;


import proyectofinalweb.johanna.rodriguez.encapsulacion.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioService extends GestionDb<Usuario> {

    private static UsuarioService instancia;

    public UsuarioService() {
        super(Usuario.class);
    }

    public UsuarioService getInstancia() {
        if (instancia == null)
            instancia = new UsuarioService();

        return instancia;
    }

    public void crearAdministrador(Usuario usuario) {

        if (buscarAdministrador() == null) {
            crear(usuario);
        }

    }

    public void crearUsuario(Usuario usuario) {
        crear(usuario);
    }

    public Usuario buscarAdministrador() {
        EntityManager entityManager = getEntityManager();
        Usuario usuario = null;
        Query query = entityManager.createNativeQuery("select * from Usuario u where u.administrador = true limit 1", Usuario.class);

        try {

            usuario = (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.INFO, "No hay admin");
        }finally {
            entityManager.close();
        }
        return usuario;
    }

    public Usuario loginUsuario(String username, String password){
        EntityManager entityManager = getEntityManager();
        Usuario usuario = null;
        Query query = entityManager.createNamedQuery("Usuario.validateLogIn");
        query.setParameter("username", username);
        query.setParameter("pass", password);
        try {

            usuario = (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.INFO, "No hay usuario con este usuario/contraseña");
        }finally {
            entityManager.close();
        }
        return usuario;
    }
}
