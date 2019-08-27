package proyectofinalweb.johanna.rodriguez.servicios;


import proyectofinalweb.johanna.rodriguez.encapsulacion.Documento;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentoService extends GestionDb<Documento> {

    private static DocumentoService instancia;

    public DocumentoService() {
        super(Documento.class);
    }

    public DocumentoService getInstancia() {
        if (instancia == null)
            instancia = new DocumentoService();

        return instancia;
    }


    public void crearDocumento(Documento documento) {
        crear(documento);
    }

    public List<Documento> buscarTodos() {

        return findAll();
    }

    public Documento buscarPorDireccion(String direccion) {
        EntityManager entityManager = getEntityManager();
        Documento documento = null;
        Query query = entityManager.createNativeQuery("select * from DOCUMENTO d where d.Direccion = :direccion", Documento.class);
        query.setParameter("direccion", direccion);
        try {

            documento = (Documento) query.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.INFO, "No hay lenguaje con ese id");
        } finally {
            entityManager.close();
        }
        return documento;
    }

    public List<Documento> topDocumentos() {
        EntityManager entityManager = getEntityManager();
        List<Documento> documentos = null;
        Query query = entityManager.createNativeQuery("select * from DOCUMENTO d where d.TIPOEXPOSICION = :tipo and d.ELIMINADO = false order by d.VISITAS desc ", Documento.class);
        query.setParameter("tipo", "PUBLICO");
        try {
            documentos = (List<Documento>) query.getResultList();
        } catch (NoResultException e) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.INFO, "No hay lenguaje con ese id");
        } finally {
            entityManager.close();
        }
        return documentos;
    }


}
