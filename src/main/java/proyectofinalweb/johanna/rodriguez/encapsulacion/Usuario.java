package proyectofinalweb.johanna.rodriguez.encapsulacion;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NamedQueries({@NamedQuery(name = "Usuario.validateLogIn", query = "select u from Usuario u where u.username = :username and u.password = :pass")})
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String nombre;
    private String password;
    private boolean administrador;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Documento> documentos;

    public Usuario() {
    }

    public Usuario(String username, String nombre, String password, boolean administrador) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.administrador = administrador;
    }

    public Usuario(String username, String nombre, String password, boolean administrador, Set<Documento> documentos) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
        this.administrador = administrador;
        this.documentos = documentos;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrator) {
        this.administrador = administrator;
    }
}

