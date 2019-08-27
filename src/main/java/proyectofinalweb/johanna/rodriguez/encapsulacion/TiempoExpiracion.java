package proyectofinalweb.johanna.rodriguez.encapsulacion;

import javax.persistence.*;
import java.time.temporal.ChronoUnit;

@Entity
@NamedQueries({@NamedQuery(name = "TiempoExpiracion.ById", query = "select t from TiempoExpiracion t  where t.id = :id")})
public class TiempoExpiracion {

    @Id
    @GeneratedValue
    private Long id;

    private ChronoUnit unit;
    private int tiempo;

    private String texto;

    public TiempoExpiracion() {
    }

    public TiempoExpiracion(ChronoUnit unit, int tiempo, String texto) {
        this.unit = unit;
        this.tiempo = tiempo;
        this.texto = texto;
    }

    public ChronoUnit getUnit() {
        return unit;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
