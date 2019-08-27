package proyectofinalweb.johanna.rodriguez.encapsulacion;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name = "Lenguaje.ById", query = "select l from Lenguaje l  where l.id = :id")})
public class Lenguaje  implements Comparable<Lenguaje>{

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    public Lenguaje() {
    }

    public Lenguaje(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int compareTo(Lenguaje other) {
        return nombre.compareTo(other.nombre);
    }
}
