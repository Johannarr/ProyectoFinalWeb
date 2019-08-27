package proyectofinalweb.johanna.rodriguez.encapsulacion.enums;

public enum TipoExposicion {

    PUBLICO("Publico"), PRIVADO("Privado"), SINLISTAR("SIN LISTAR");

    private String action;

    // getter method
    public String getAction() {
        return this.action;
    }

    // enum constructor - cannot be public or protected
    private TipoExposicion(String action) {
        this.action = action;
    }
}
