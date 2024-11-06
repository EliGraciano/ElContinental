package Continental.modelo;

public class Evento {

    private TipoEvento tipo;

    private String contenido;

    public Evento(TipoEvento tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }
    public Evento(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public TipoEvento getTipo() {
        return this.tipo;
    }

    public String getContenido() {
        return this.contenido;
    }


}
