package proyectofinalweb.johanna.rodriguez.encapsulacion;


import proyectofinalweb.johanna.rodriguez.encapsulacion.enums.TipoExposicion;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Documento {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private TiempoExpiracion tiempoExpiracion;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoExposicion tipoExposicion;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private Date fechaCreacion;

    private Boolean eliminado;

    private String direccion;

    @Column(columnDefinition = "integer default 0")
    private int visitas;

    @OneToOne
    private Lenguaje lenguaje;

    public Documento() {
    }

    public Documento(TiempoExpiracion tiempoExpiracion, String nombre, TipoExposicion tipoExposicion, String contenido, Date fechaCreacion, Boolean eliminado, String direccion, Lenguaje lenguaje) {
        this.tiempoExpiracion = tiempoExpiracion;
        this.nombre = nombre;
        this.tipoExposicion = tipoExposicion;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.direccion = direccion;
        this.lenguaje = lenguaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TiempoExpiracion getTiempoExpiracion() {
        return tiempoExpiracion;
    }

    public void setTiempoExpiracion(TiempoExpiracion tiempoExpiracionl) {
        this.tiempoExpiracion = tiempoExpiracionl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoExposicion getTipoExposicion() {
        return tipoExposicion;
    }

    public void setTipoExposicion(TipoExposicion tipoExposicion) {
        this.tipoExposicion = tipoExposicion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }
}
