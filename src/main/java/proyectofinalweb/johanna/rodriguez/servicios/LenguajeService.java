package proyectofinalweb.johanna.rodriguez.servicios;


import proyectofinalweb.johanna.rodriguez.encapsulacion.Lenguaje;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LenguajeService extends GestionDb<Lenguaje> {

    private static LenguajeService instancia;

    public LenguajeService() {
        super(Lenguaje.class);
    }

    public LenguajeService getInstancia() {
        if (instancia == null)
            instancia = new LenguajeService();

        return instancia;
    }


    public void crearLenguaje(Lenguaje lenguaje) {
        crear(lenguaje);
    }

    public List<Lenguaje> buscarTodos() {

        return findAll();
    }

    public Lenguaje buscarPorId(Long id){
        EntityManager entityManager = getEntityManager();
        Lenguaje lenguaje = null;
        Query query = entityManager.createNamedQuery("Lenguaje.ById");
        query.setParameter("id", id);
        try {

            lenguaje = (Lenguaje) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.INFO, "No hay lenguaje con ese id");
        }finally {
            entityManager.close();
        }
        return lenguaje;
    }


}
