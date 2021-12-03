package bubbles;

public class Nodo {
    private Trabajador trabajador;
    private Nodo enlace;

    public Nodo(Trabajador trabajador) {
        this.trabajador = trabajador;
        this.enlace = null;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Nodo getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
}
