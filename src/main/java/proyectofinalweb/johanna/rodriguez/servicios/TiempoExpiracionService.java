package proyectofinalweb.johanna.rodriguez.servicios;


import proyectofinalweb.johanna.rodriguez.encapsulacion.TiempoExpiracion;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TiempoExpiracionService extends GestionDb<TiempoExpiracion> {

    private static TiempoExpiracionService instancia;

    public TiempoExpiracionService() {
        super(TiempoExpiracion.class);
    }

    public TiempoExpiracionService getInstancia() {
        if (instancia == null)
            instancia = new TiempoExpiracionService();

        return instancia;
    }


    public void crearTiempoExpiracion(TiempoExpiracion tiempoExpiracion) {
        crear(tiempoExpiracion);
    }

    public List<TiempoExpiracion> buscarTodos() {

        return findAll();
    }

    public TiempoExpiracion buscarPorId(Long id){
        EntityManager entityManager = getEntityManager();
        TiempoExpiracion tiempoExpiracion = null;
        Query query = entityManager.createNamedQuery("TiempoExpiracion.ById");
        query.setParameter("id", id);
        try {

            tiempoExpiracion = (TiempoExpiracion) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.INFO, "No hay tiempo expiracion con ese id");
        }finally {
            entityManager.close();
        }
        return tiempoExpiracion;
    }


}
